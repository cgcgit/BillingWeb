/**
 * 
 */
package com.comasw.viewController.catalog.type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;

import com.comasw.model.tables.pojos.CtBillCycleType;
import com.comasw.model.tables.pojos.PtBillingPeriod;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.ejb.catalog.type.BillCycleTypeEJBLocal;
import com.comasw.ejb.parameterization.BillingPeriodEJBLocal;
import com.comasw.generalClass.BasicTypeWithLists;
import com.comasw.interfaces.IEditableTable;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class BillCycleTypeController extends BasicTypeWithLists<CtBillCycleType> implements Serializable, IEditableTable {

	private static final long serialVersionUID = -1578875974294232064L;
	
	Logger logger = (Logger) LogManager.getLogger(BillCycleTypeController.class);

	
	private static final Integer CODENUM_FIELD_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("BILL_CYCLE_TYPE_CODENUM_FIELD_LENGTH_MAX"));
	
	

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private BillCycleTypeEJBLocal billCycleTypeEJB;
	
	@EJB
	private BillingPeriodEJBLocal billingPeriodEJB;

	
	/**
	 * Selected data row in the table
	 */
	@Inject
	private CtBillCycleType selectedData;

	
	// --------------------
	// GETTERS AND SETTERS
	// -------------------
	
	/**
	 * @return the selectedData
	 */
	public CtBillCycleType getSelectedData() {
		return selectedData;
	}


	/**
	 * @param selectedData the selectedData to set
	 */
	public void setSelectedData(CtBillCycleType selectedData) {
		this.selectedData = selectedData;
	}



	
	// -------------------
	// METHODS
	// -------------------
	

	

	/**
	 * 
	 */
	public BillCycleTypeController() {
		// TODO Auto-generated constructor stub
	}

	
	@PostConstruct
	public void init() {

		if (selectedData == null) {
			selectedData = new CtBillCycleType();
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
		}


	}


	@Override
	public void loadDataList() {
		this.setDataList(billCycleTypeEJB.findAllData());
		if (this.getDataList().isEmpty()) {
			logger.info("No data to show");

		} else {
			logger.info("Load data sucessful");
		}
		
		
	}



	@Override
	public void onRowInit(RowEditEvent<?> event) {
		String message, messageDetail;

		CtBillCycleType dataObject;

		message = "EDIT ROW";

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);
		int rowPosition = dataTable.getRowIndex();

		// Gets the backup of the data to modify
		dataObject = (CtBillCycleType) event.getObject();
		
		// If we are editing a row, we must disabled all the other buttons
		this.editingMode = true;

		// Due to the update of the entire table to show the password column
		// the edition of the row set to unselect --> force to expand the edit button
		PrimeFaces.current().executeScript(
				"jQuery('span.ui-icon-pencil').eq(" + rowPosition + ").each(function(){jQuery(this).click()});");

		// If we are modifing a row we can't add a new row --> Disable all the other
		// buttons

		messageDetail = "Editing bill cycle type: " + dataObject.getCode();
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
		
	}



	@Override
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException {
		CtBillCycleType dataObject;
		String message, messageDetail;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (CtBillCycleType) event.getObject();

				
		try {

			// Validates the data
			if (this.objectValidation(dataObject)) {
				//sets the modif data
				this.selectedData.setModifUser(this.loggedUser.getUserCode());
				this.selectedData.setModifDate(LocalDateTime.now());
				
				billCycleTypeEJB.updateData(dataObject);
				messageDetail = "Data saves correctly";
				logger.info("Update bill cycle type: " + this.selectedData.toString() + " - " +messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
				this.setControlVariablesToDefault();
			} else {
				messageDetail = "ERROR - Data values are incorrect";
				error = true;
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, message, messageDetail));
			}
		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}

		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		} finally {
			if (error) {
				FacesContext.getCurrentInstance().validationFailed();
			} else {				
				this.loadDataList();
			}
		}
		
	}



	@Override
	public void onRowCancel(RowEditEvent<?> event) {	
		String message, messageDetail;

		message = "CANCEL UPDATE ROW";		
		messageDetail = "Cancelled the edition of the bill cycle type";
		
		this.refreshDataTable();
		this.setControlVariablesToDefault();
		
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		
	}



	@Override
	public void pushDeleteRowButton() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void pushCreateNewButton() {
		this.selectedData = new CtBillCycleType();
		PrimeFaces.current().executeScript("PF('createNewDialogWidget').show();");
		
	}



	@Override
	public void pushConfirmButtonCreateNewDialog() {
		String message = "INSERT NEW DATA";
		String messageDetail = "";
		Boolean error = false;

		try {
			if (objectValidation(this.selectedData)) {
				//Set create values
				
				this.selectedData.setInputUser(this.loggedUser.getUserCode());
				this.selectedData.setInputDate(LocalDateTime.now());
				
				billCycleTypeEJB.insertData(this.selectedData);
				messageDetail = "Data saves succesfully";
				logger.info("Create bill cycle type: " + this.selectedData.toString() + " - " +messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

			} else {
				error = true;
			}
		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
			}

		} catch (Exception e) {
			error = true;
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
		} finally {
			if (error) {
				facesContext.validationFailed();
			} else {
				this.resetFilterDataTable();
				this.loadDataList();
				PrimeFaces.current().executeScript("PF('createNewDialogWidget').hide();"); 
			}
		}
		
	}



	@Override
	public void pushCancelButtonCreateNewDialog() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void pushCleanButtonCreateNewDialog() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void pushConfirmButtonDeleteDialog() {
		String message = "DELETE ROW";
		String messageDetail = "";
		Boolean error = false;

		try {
			if (this.selectedData != null) {
				billCycleTypeEJB.deleteData(this.selectedData);
				messageDetail = "Data deletes succesfully";
				logger.info("Delete bill cycle type: " + this.selectedData.toString() + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
			} else {
				error = true;
				messageDetail = "ERROR - Selected data is null";
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			}
		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
			}

		} catch (Exception e) {
			error = true;
			messageDetail = "ERROR - " + e.getMessage();
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
		} finally {
			if (error) {
				facesContext.validationFailed();
			} else {
				this.resetFilterDataTable();
				this.loadDataList();
			}
		}
		
		
	}



	@Override
	public void pushCancelButtonDeleteDialog() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void resetObjectValues() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean objectValidation(Object dataObject) {
		boolean result = true;
		CtBillCycleType objectToValidate;
		String message = "DATA VALIDATION";
		String messageDetail = "";

		objectToValidate = (CtBillCycleType) dataObject;

		if (objectToValidate != null) {

			if (objectToValidate.getCode() != null) {
				objectToValidate.setCode(objectToValidate.getCode().toUpperCase().trim());
			}

			if (objectToValidate.getName() != null) {
				objectToValidate.setName(objectToValidate.getName().toUpperCase().trim());
			}

			if (objectToValidate.getDescription() != null) {
				objectToValidate.setDescription(objectToValidate.getDescription().trim());
			}		
			
			if (objectToValidate.getBillCycleCodenum() != null) {
				objectToValidate.setBillCycleCodenum(objectToValidate.getBillCycleCodenum().trim());
			}			
			
			if (objectToValidate.getCode() == null || objectToValidate.getCode().length() == 0) {
				messageDetail = "ERROR - The code of the bill cycle type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getCode().length())
					.compareTo(BasicTypeWithLists.CODE_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The code of the bill cycle type (" + objectToValidate.getCode().length()
						+ " characters) exceeds the limit of "
						+ BasicTypeWithLists.CODE_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}
			if (objectToValidate.getName() == null || objectToValidate.getName().length() == 0) {
				messageDetail = "ERROR - The name of the bill cycle type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getName().length())
					.compareTo(BasicTypeWithLists.NAME_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The name of the bill cycle type (" + objectToValidate.getName().length()
						+ " characters) exceeds the limit of "
						+ BasicTypeWithLists.NAME_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}

			if (objectToValidate.getDescription() == null || objectToValidate.getDescription().length() == 0) {
				messageDetail = "ERROR - The description of the bill cycle type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getDescription().length())
					.compareTo(BasicTypeWithLists.DESCRIPTION_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The description of the bill cycle type ("
						+ objectToValidate.getDescription().length()
						+ " characters) exceeds the limit of "
						+ BasicTypeWithLists.DESCRIPTION_FIELD_LENGTH_MAX.toString() + " characters";

				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}
			
			if (objectToValidate.getCorrective() == null) {
				result = false;
				messageDetail = "ERROR - The corrective flag can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}

			
			if (objectToValidate.getBillingPeriodId() == null) {
				result = false;
				messageDetail = "ERROR - The associated billing period can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}
			
			if ((objectToValidate.getBillCycleDay() == null) || (objectToValidate.getBillCycleDay()<= 0) || (objectToValidate.getBillCycleDay()> 31)){
				messageDetail = "ERROR - The cicle day must be a valid day (from 1 to 31)";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} 
			
			if (objectToValidate.getBillCycleCodenum() == null || objectToValidate.getBillCycleCodenum().length() == 0) {
				messageDetail = "ERROR - The code num can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getBillCycleCodenum().length())
					.compareTo(BillCycleTypeController.CODENUM_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The codenum of the bill cycle type ("
						+ objectToValidate.getBillCycleCodenum().length()
						+ " characters) exceeds the limit of "
						+ BillCycleTypeController.CODENUM_FIELD_LENGTH_MAX.toString() + " characters";

				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}
			
			if (objectToValidate.getStatusId() == null) {
				result = false;
				messageDetail = "ERROR - The status can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}
			

			if (result) {
				// no error --> update panel
				Ajax.update(NEW_PANEL_DATA_ID);
			}

		} else {
			messageDetail = "ERROR - Empty values";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			result = false;
		}
		return result;
	}



	@Override
	public void retrieveBackupData() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInitVariablesToDefault() {
		this.editingMode = false;
	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();
	}	
	
	public List<SelectItem> billingPeriodSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtBillingPeriod> list = billingPeriodEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find billing period list");
		} else {
			for (PtBillingPeriod p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getBillingPeriodId());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	@Override
	public void resetFilterDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dataTableWidget').clearFilters()");

	}

	@Override
	public void refreshDataTable() {
		this.resetFilterDataTable();
		this.loadDataList();
		Ajax.update(DATA_TABLE_ID);
	}


	
}
