/**
 * 
 */
package com.billingweb.viewController.parameterization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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

import com.billingweb.ejb.parameterization.DiscountTypeEJBLocal;
import com.billingweb.generalClass.SimpleTableBasicClass;
import com.billingweb.interfaces.IGeneralController;
import com.billingweb.model.tables.pojos.PtDiscountType;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class DiscountTypeController extends SimpleTableBasicClass implements Serializable, IGeneralController {


	private static final long serialVersionUID = 2143390534598584457L;

	Logger logger = (Logger) LogManager.getLogger(DiscountTypeController.class);

	String DATA_TABLE_ID = "form:" + DiscountTypeController.uiValues.getString("dataTableID");
	String NEW_PANEL_DATA_ID = "form:" + DiscountTypeController.uiValues.getString("newPanelDataID");

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private DiscountTypeEJBLocal discountTypeEJB;

	/**
	 * Application level data list
	 */
	private List<PtDiscountType> dataList;

	/**
	 * Application level filtered data list
	 */
	private List<PtDiscountType> filteredDataList;

	/**
	 * Selected data row in the table
	 */
	@Inject
	private PtDiscountType selectedData;

	/**
	 * Backup object for entity type(delete/modify operations)
	 */
	private PtDiscountType backupData;

	
	// --------------------
	// GETTERS AND SETTERS
	// -------------------
	
	
	/**
	 * @return the dataList
	 */
	public List<PtDiscountType> getDataList() {
		return dataList;
	}


	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<PtDiscountType> dataList) {
		this.dataList = dataList;
	}


	/**
	 * @return the filteredDataList
	 */
	public List<PtDiscountType> getFilteredDataList() {
		return filteredDataList;
	}


	/**
	 * @param filteredDataList the filteredDataList to set
	 */
	public void setFilteredDataList(List<PtDiscountType> filteredDataList) {
		this.filteredDataList = filteredDataList;
	}


	/**
	 * @return the selectedData
	 */
	public PtDiscountType getSelectedData() {
		return selectedData;
	}


	/**
	 * @param selectedData the selectedData to set
	 */
	public void setSelectedData(PtDiscountType selectedData) {
		this.selectedData = selectedData;
	}


	/**
	 * @return the backupData
	 */
	public PtDiscountType getBackupData() {
		return backupData;
	}


	/**
	 * @param backupData the backupData to set
	 */
	public void setBackupData(PtDiscountType backupData) {
		this.backupData = backupData;
	}

	
	
	

	// -------------------
	// METHODS
	// -------------------
	

	
	public DiscountTypeController() {
		// TODO Auto-generated constructor stub
	}


	@PostConstruct
	public void init() {

		if (selectedData == null) {
			selectedData = new PtDiscountType();
		}

		if (this.backupData == null) {
			this.backupData = new PtDiscountType();
		}

	}
	

	@Override
	public void loadDataList() {
		dataList = discountTypeEJB.findAllData();
		if (dataList.isEmpty()) {
			logger.info("No data to show");

		} else {
			logger.info("Load data sucessful");
		}
		
	}

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		String message, messageDetail;

		PtDiscountType dataObject;

		message = "EDIT ROW";

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);
		int rowPosition = dataTable.getRowIndex();

		// Gets the backup of the data to modify
		dataObject = (PtDiscountType) event.getObject();
		this.backupData.from(dataObject);

		// If we are editing a row, we must disabled all the other buttons
		this.editingMode = true;

		// Due to the update of the entire table to show the password column
		// the edition of the row set to unselect --> force to expand the edit button
		PrimeFaces.current().executeScript(
				"jQuery('span.ui-icon-pencil').eq(" + rowPosition + ").each(function(){jQuery(this).click()});");

		// If we are modifing a row we can't add a new row --> Disable all the other
		// buttons

		messageDetail = "Editing discount type: " + dataObject.getCode();
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		
	}

	@Override
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException {
		PtDiscountType dataObject;
		String message, messageDetail;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (PtDiscountType) event.getObject();

				
		try {

			// Validates the data
			if (this.objectValidation(dataObject)) {
				discountTypeEJB.updateData(dataObject);
				this.setControlVariablesToDefault();
			} else {
				messageDetail = "ERROR - Data values are incorrect";
				error = true;
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, message, messageDetail));
			}
		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			} else if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebParseException")) {
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
				messageDetail = "Data saves correctly";
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
				this.loadDataList();
			}
		}
		
	}

	@Override
	public void onRowCancel(RowEditEvent<?> event) {
		PtDiscountType dataObject;
		String message, messageDetail;

		message = "CANCEL UPDATE ROW";

		dataObject = (PtDiscountType) event.getObject();
		dataObject.from(this.backupData);

		messageDetail = "Cancelled the edition of the discount type";
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		this.setControlVariablesToDefault();
		
	}

	@Override
	public void pushAddNewRowButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushDeleteRowButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushUpdateRowButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushAddNewButton() {
		this.selectedData = new PtDiscountType();
		PrimeFaces.current().executeScript("PF('createNewDialogWidget').show();");
		
	}

	@Override
	public void pushConfirmButtonAddNewDialog() {
		String message = "INSERT NEW DATA";
		String messageDetail = "";
		Boolean error = false;

		try {
			if (objectValidation(this.selectedData)) {
				discountTypeEJB.insertData(this.selectedData);
				messageDetail = "Data saves succesfully";
				logger.info(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

			} else {
				error = true;
			}
		} catch (EJBException e) {
			error = true;
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebParseException")) {
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
				this.loadDataList();
				PrimeFaces.current().executeScript("PF('createNewDialogWidget').hide();"); 
			}
		}
		
	}

	@Override
	public void pushCancelButtonAddNewDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushCleanButtonAddNewDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushConfirmButtonDeleteDialog() {
		String message = "DELETE ROW";
		String messageDetail = "";
		Boolean error = false;

		try {
			if (this.selectedData != null) {
				discountTypeEJB.deleteData(this.selectedData);
				messageDetail = "Data deletes succesfully";
				logger.info(messageDetail);
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
			if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebDataAccessException")) {
				messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);

			} else if (ne.getClass().getName().equals("es.billingweb.exception.BillingWebParseException")) {
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
		PtDiscountType objectToValidate;
		String message = "DATA VALIDATION";
		String messageDetail = "";

		objectToValidate = (PtDiscountType) dataObject;

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
				messageDetail = "ERROR - The code of the discount type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getCode().length())
					.compareTo(ApplicationLevelController.CODE_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The code of the discount type (" + objectToValidate.getCode().length()
						+ " characters) exceeds the limit of "
						+ ApplicationLevelController.CODE_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}
			if (objectToValidate.getName() == null || objectToValidate.getName().length() == 0) {
				messageDetail = "ERROR - The name of the discount type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getName().length())
					.compareTo(ApplicationLevelController.NAME_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The name of the discount type (" + objectToValidate.getName().length()
						+ " characters) exceeds the limit of "
						+ ApplicationLevelController.NAME_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}

			if (objectToValidate.getDescription() == null || objectToValidate.getDescription().length() == 0) {
				messageDetail = "ERROR - The description of the discount type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getDescription().length())
					.compareTo(ApplicationLevelController.DESCRIPTION_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The description of the discount type ("
						+ objectToValidate.getDescription().length()
						+ " characters) exceeds the limit of "
						+ ApplicationLevelController.DESCRIPTION_FIELD_LENGTH_MAX.toString() + " characters";

				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
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
	
}
