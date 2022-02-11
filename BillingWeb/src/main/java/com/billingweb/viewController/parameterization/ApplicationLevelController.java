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
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
//import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.event.RowEditEvent;

import com.billingweb.ejb.parameterization.ApplicationLevelEJBLocal;
import com.billingweb.generalClass.SimpleTableBasicClass;
import com.billingweb.interfaces.IGeneralController;
import com.billingweb.model.tables.pojos.PtApplicationLevel;
import com.billingweb.utilities.Messages;

@Named("applicationLevelController")
@ViewScoped
/**
 * @author catuxa
 *
 */
public class ApplicationLevelController extends SimpleTableBasicClass implements Serializable, IGeneralController {

	private static final long serialVersionUID = -6405489696326286188L;

	Logger logger = (Logger) LogManager.getLogger(ApplicationLevelController.class);

	String DATA_TABLE_ID = "form:" + ApplicationLevelController.uiValues.getString("dataTableID");
	String NEW_PANEL_DATA_ID = "form:" + ApplicationLevelController.uiValues.getString("newPanelDataID");

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private ApplicationLevelEJBLocal applicationLevelEJB;

	/**
	 * Application level data list
	 */
	private List<PtApplicationLevel> dataList;

	/**
	 * Application level filtered data list
	 */
	private List<PtApplicationLevel> filteredDataList;

	/**
	 * Selected data row in the table
	 */
	@Inject
	private PtApplicationLevel selectedData;

	/**
	 * Backup object for application level(delete/modify operations)
	 */
	private PtApplicationLevel backupData;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	public List<PtApplicationLevel> getDataList() {
		return dataList;
	}

	public void setDataList(List<PtApplicationLevel> dataList) {
		this.dataList = dataList;
	}

	public List<PtApplicationLevel> getFilteredDataList() {
		return filteredDataList;
	}

	public void setFilteredDataList(List<PtApplicationLevel> filteredDataList) {
		this.filteredDataList = filteredDataList;
	}

	public PtApplicationLevel getSelectedData() {
		return selectedData;
	}

	public void setSelectedData(PtApplicationLevel selectedData) {
		this.selectedData = selectedData;
	}

	public PtApplicationLevel getBackupData() {
		return backupData;
	}

	public void setBackupData(PtApplicationLevel backupData) {
		this.backupData = backupData;
	}

	// -------------------
	// METHODS
	// -------------------

	/**
	 * Constructor
	 */
	public ApplicationLevelController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (selectedData == null) {
			selectedData = new PtApplicationLevel();
		}

		if (this.backupData == null) {
			this.backupData = new PtApplicationLevel();
		}

	}

	@Override
	public void loadDataList() {
		dataList = applicationLevelEJB.findAllData();
		if (dataList.isEmpty()) {
			logger.info("No data to show");

		} else {
			logger.info("Load data sucessful");
		}
	}

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		String message, messageDetail;

		PtApplicationLevel dataObject;

		message = "EDIT ROW";

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);
		int rowPosition = dataTable.getRowIndex();

		// Gets the backup of the data to modify
		dataObject = (PtApplicationLevel) event.getObject();
		this.backupData.from(dataObject);

		// If we are editing a row, we must disabled all the other buttons
		this.editingMode = true;

		// int totalRows = dataList.size();

		// Due to the update of the entire table to show the password column
		// the edition of the row set to unselect --> force to expand the edit button
		PrimeFaces.current().executeScript(
				"jQuery('span.ui-icon-pencil').eq(" + rowPosition + ").each(function(){jQuery(this).click()});");

		// If we are modifing a row we can't add a new row --> Disable all the other
		// buttons

		/*
		 * 
		 * for (int i = 0; i < totalRows; i++) {
		 * 
		 * Ajax.updateRow(dataTable, i); }
		 */

		messageDetail = "Editing application level: " + dataObject.getCode();
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

	}

	@Override
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException {
		PtApplicationLevel dataObject;
		String message, messageDetail;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (PtApplicationLevel) event.getObject();

		try {

			// Validates the data
			if (this.objectValidation(dataObject)) {
				applicationLevelEJB.updateData(dataObject);
				messageDetail = "Data saves correctly";
				logger.info("Update application level: " + this.selectedData.toString() + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
						messageDetail);
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
				this.loadDataList();
			}
		}

	}

	@Override
	public void onRowCancel(RowEditEvent<?> event) {
		PtApplicationLevel dataObject;
		String message, messageDetail;
		boolean error = false;

		message = "CANCEL UPDATE ROW";

		dataObject = (PtApplicationLevel) event.getObject();
		dataObject.from(this.backupData);

		messageDetail = "Cancelled the edition of the application level";
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

		this.setControlVariablesToDefault();

		// Ajax.update(DATA_TABLE_ID);

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

	}

	@Override
	public void pushAddNewButton() {
		this.selectedData = new PtApplicationLevel();
		PrimeFaces.current().executeScript("PF('createNewDialogWidget').show();");
	}

	@Override
	public void pushConfirmButtonAddNewDialog() {

		String message = "INSERT NEW DATA";
		String messageDetail = "";
		Boolean error = false;

		try {
			if (objectValidation(this.selectedData)) {
				applicationLevelEJB.insertData(this.selectedData);
				messageDetail = "Data saves succesfully";
				logger.info("Create application level: " + this.selectedData.toString() + " - " +messageDetail);
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
				// Ajax.update(DATA_TABLE_ID);
				PrimeFaces.current().executeScript("PF('createNewDialogWidget').hide();");
				// PrimeFaces.current().ajax().update("form:mainTable");
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
				applicationLevelEJB.deleteData(this.selectedData);
				// this.selectedData = null;
				messageDetail = "Data deletes succesfully";
				logger.info("Delete application data: " + this.selectedData.toString() + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
			} else {
				error = true;
				messageDetail = "ERROR - Selected data is null";
				logger.fatal("Delete application data: " + this.selectedData.getCode() + " - " + messageDetail);
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
		PtApplicationLevel objectToValidate;
		String message = "DATA VALIDATION";
		String messageDetail = "";

		objectToValidate = (PtApplicationLevel) dataObject;

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
				messageDetail = "ERROR - The code of the application level can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getCode().length())
					.compareTo(ApplicationLevelController.CODE_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The code of the application level (" + objectToValidate.getCode().length()
						+ " characters) exceeds the limit of "
						+ ApplicationLevelController.CODE_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}
			if (objectToValidate.getName() == null || objectToValidate.getName().length() == 0) {
				messageDetail = "ERROR - The name of the application level can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getName().length())
					.compareTo(ApplicationLevelController.NAME_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The name of the application level (" + objectToValidate.getName().length()
						+ " characters) exceeds the limit of "
						+ ApplicationLevelController.NAME_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			}

			if (objectToValidate.getDescription() == null || objectToValidate.getDescription().length() == 0) {
				messageDetail = "ERROR - The description of the application level can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				result = false;
			} else if (((Integer) objectToValidate.getDescription().length())
					.compareTo(ApplicationLevelController.DESCRIPTION_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The description of the application level ("
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
