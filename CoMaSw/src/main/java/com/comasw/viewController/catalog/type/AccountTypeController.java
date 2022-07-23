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

import com.comasw.model.tables.pojos.CtAccountType;
import com.comasw.model.tables.pojos.CtBillCycleType;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.ejb.catalog.type.AccountTypeEJBLocal;
import com.comasw.ejb.catalog.type.BillCycleTypeEJBLocal;
import com.comasw.generalClass.BasicTypeWithLists;
import com.comasw.interfaces.IEditableTable;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class AccountTypeController extends BasicTypeWithLists<CtAccountType> implements Serializable, IEditableTable {

	private static final long serialVersionUID = -5548297716636677290L;

	Logger logger = (Logger) LogManager.getLogger(AccountTypeController.class);

	private static final Integer ENTITY_TYPE_ID = Integer.valueOf(dbDefinitions.getString("ENTITY_TYPE_ID_ACC"));

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private AccountTypeEJBLocal accountTypeEJB;
	
	@EJB
	private BillCycleTypeEJBLocal billCycleTypeEJB;

	
	/**
	 * Selected data row in the table
	 */
	@Inject
	private CtAccountType selectedData;

	
	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	
	/**
	 * @return the selectedData
	 */
	public CtAccountType getSelectedData() {
		return selectedData;
	}

	/**
	 * @param selectedData the selectedData to set
	 */
	public void setSelectedData(CtAccountType selectedData) {
		this.selectedData = selectedData;
	}


	

	// -------------------
	// METHODS
	// -------------------

	/**
	 * 
	 */
	public AccountTypeController() {
		// TODO Auto-generated constructor stub
	}
	

	@PostConstruct
	public void init() {

		if (selectedData == null) {
			selectedData = new CtAccountType();
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
		}

	}

	@Override
	public void loadDataList() {
		this.setDataList(accountTypeEJB.findAllData());
		if (this.getDataList().isEmpty()) {
			logger.info("No data to show");

		} else {
			logger.info("Load data sucessful");
		}
		
		
	}

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		String message, messageDetail;

		CtAccountType dataObject;

		message = "EDIT ROW";

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);
		int rowPosition = dataTable.getRowIndex();

		// Gets the backup of the data to modify
		dataObject = (CtAccountType) event.getObject();		

		// If we are editing a row, we must disabled all the other buttons
		this.editingMode = true;

		// Due to the update of the entire table to show the password column
		// the edition of the row set to unselect --> force to expand the edit button
		PrimeFaces.current().executeScript(
				"jQuery('span.ui-icon-pencil').eq(" + rowPosition + ").each(function(){jQuery(this).click()});");

		// If we are modifing a row we can't add a new row --> Disable all the other
		// buttons

		messageDetail = "Editing account type: " + dataObject.getCode();
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
		
		
	}

	@Override
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException {
		CtAccountType dataObject;
		String message, messageDetail;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (CtAccountType) event.getObject();

				
		try {

			// Validates the data
			if (this.objectValidation(dataObject)) {
				//sets the modif data
				this.selectedData.setModifUser(this.loggedUser.getUserCode());
				this.selectedData.setModifDate(LocalDateTime.now());
				
				accountTypeEJB.updateData(dataObject);
				messageDetail = "Data saves correctly";
				logger.info("Update account type: " + this.selectedData.toString() + " - " +messageDetail);
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
		messageDetail = "Cancelled the edition of the account type";

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
		this.selectedData = new CtAccountType();
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
				
				this.selectedData.setEntityTypeId(ENTITY_TYPE_ID);
				this.selectedData.setInputUser(this.loggedUser.getUserCode());
				this.selectedData.setInputDate(LocalDateTime.now());
				
				accountTypeEJB.insertData(this.selectedData);
				messageDetail = "Data saves succesfully";
				logger.info("Create account type: " + this.selectedData.toString() + " - " +messageDetail);
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
				accountTypeEJB.deleteData(this.selectedData);
				messageDetail = "Data deletes succesfully";
				logger.info("Delete account type: " + this.selectedData.toString() + " - " + messageDetail);
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
		CtAccountType objectToValidate;
		String message = "DATA VALIDATION";
		String messageDetail = "";

		objectToValidate = (CtAccountType) dataObject;

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
			
			if (objectToValidate.getCode() == null || objectToValidate.getCode().length() == 0) {
				messageDetail = "ERROR - The code of the account type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getCode().length())
					.compareTo(BasicTypeWithLists.CODE_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The code of the account type (" + objectToValidate.getCode().length()
						+ " characters) exceeds the limit of "
						+ BasicTypeWithLists.CODE_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}
			if (objectToValidate.getName() == null || objectToValidate.getName().length() == 0) {
				messageDetail = "ERROR - The name of the account type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getName().length())
					.compareTo(BasicTypeWithLists.NAME_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The name of the account type (" + objectToValidate.getName().length()
						+ " characters) exceeds the limit of "
						+ BasicTypeWithLists.NAME_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}

			if (objectToValidate.getDescription() == null || objectToValidate.getDescription().length() == 0) {
				messageDetail = "ERROR - The description of the account type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getDescription().length())
					.compareTo(BasicTypeWithLists.DESCRIPTION_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The description of the account type ("
						+ objectToValidate.getDescription().length()
						+ " characters) exceeds the limit of "
						+ BasicTypeWithLists.DESCRIPTION_FIELD_LENGTH_MAX.toString() + " characters";

				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}
			
			if (objectToValidate.getOrdinaryBillCycleTypeId() == null) {
				result = false;
				messageDetail = "ERROR - The ordirary bill cycle can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}
			
			if (objectToValidate.getCorrectiveBillCycleTypeId() == null) {
				result = false;
				messageDetail = "ERROR - The ordirary bill cycle can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
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
	
	public List<SelectItem> ordinaryBillCycleSelectItemsMenu() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtBillCycleType> list = billCycleTypeEJB.findOrdinaryCycleType();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find profile list");
		} else {
			for (CtBillCycleType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getBillCycleTypeId());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	public List<SelectItem> correctiveBillCycleSelectItemsMenu() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtBillCycleType> list = billCycleTypeEJB.findCorrectiveCycleType();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find profile list");
		} else {
			for (CtBillCycleType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getBillCycleTypeId());
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
