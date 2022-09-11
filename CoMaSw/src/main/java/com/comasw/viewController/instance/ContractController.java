/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

*/

package com.comasw.viewController.instance;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
import org.primefaces.event.RowEditEvent;

import com.comasw.ejb.instance.ContractEJBLocal;
import com.comasw.generalClass.BasicType;
import com.comasw.interfaces.IEditableTable;
import com.comasw.model.tables.pojos.ItContract;
import com.comasw.model.tables.pojos.VwUsers;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class ContractController extends BasicType<ItContract> implements Serializable, IEditableTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6702522253574328372L;

	Logger logger = (Logger) LogManager.getLogger(ContractController.class);

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	ContractEJBLocal contractEJB;
	
	String confirmCreateMessage;
	
	//--------------------
	// GETTERS AND SETTERS
	//--------------------
	/**
	 * @return the confirmCreateMessage
	 */
	public String getConfirmCreateMessage() {
		return confirmCreateMessage;
	}

	/**
	 * @param confirmCreateMessage the confirmCreateMessage to set
	 */
	public void setConfirmCreateMessage(String confirmCreateMessage) {
		this.confirmCreateMessage = confirmCreateMessage;
	}
	

	// -------------------
	// METHODS
	// -------------------



	/**
	 * Constructor
	 */
	public ContractController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<ItContract>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<ItContract>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new ItContract());
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
		}

	}

	@Override
	public void pushDeleteRowButton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pushCreateNewButton() {
		this.setSelectedData(new ItContract());
		PrimeFaces.current().executeScript("PF('createNewDialogWidget').show();");

	}

	@Override
	public void pushConfirmButtonCreateNewDialog() {
		String message = "INSERT NEW DATA";
		String messageDetail = "";
		Boolean error = false;
		Integer id;
		ItContract objectCreated;

		try {
			if (objectValidation(this.getSelectedData())) {
				
				this.getSelectedData().setInputUser(this.loggedUser.getUserCode());
				this.getSelectedData().setInputDate(LocalDateTime.now());
				
				id = contractEJB.insertData(this.getSelectedData());
				
				objectCreated = contractEJB.findDataByContractId(id).get(0);
				
				this.setConfirmCreateMessage("Created new contract - Contract Number: " + objectCreated.getContractNumber());
				
				messageDetail = "Data saves succesfully";
				logger.info("Create contract: " + objectCreated.getContractNumber() + " - " + messageDetail);
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
				this.loadDataList(); //
				PrimeFaces.current().executeScript("PF('createNewDialogWidget').hide();");
				Ajax.update("form:confirmCreateMessageDialog");
				PrimeFaces.current().executeScript("PF('confirmCreateMessageDialogWidget').show();");
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
			if (this.getSelectedData() != null) {
				contractEJB.deleteData(this.getSelectedData());
				// this.selectedData = null;
				messageDetail = "Data deletes succesfully";
				logger.info("Delete contract data: " + this.getSelectedData().toString() + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
			} else {
				error = true;
				messageDetail = "ERROR - Selected data is null";
				logger.fatal(
						"Delete contract data: " + this.getSelectedData().getContractNumber() + " - " + messageDetail);
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
		// nothing to validates
		return result;
	}

	@Override
	public void retrieveBackupData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadDataList() {
		this.setDataList(contractEJB.findAllData());

		if (this.getDataList().isEmpty()) {
			logger.info("No data to show");

		} else {
			logger.info("Load data sucessful");
		}

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

	@Override
	public void setInitVariablesToDefault() {
		this.editingMode = false;

	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();

	}

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		String message, messageDetail;
		ItContract dataObject;

		message = "EDIT ROW";
		
		// Gets the backup of the data to modify
		dataObject = (ItContract) event.getObject();

		// If we are editing a row, we must disabled all the other buttons
		this.editingMode = true;
	

		messageDetail = "Editing contract number: " + dataObject.getContractNumber();
		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

	}

	@Override
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException {
		ItContract dataObject;
		String message, messageDetail;
		boolean error = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (ItContract) event.getObject();

		try {
			// Validates the data
			if (this.objectValidation(dataObject)) {
				
				dataObject.setModifUser(this.loggedUser.getUserCode());
				dataObject.setModifDate(LocalDateTime.now());
				
				contractEJB.updateData(dataObject);
				messageDetail = "Data saves correctly";
				logger.info("Update application level: " + this.getSelectedData().toString() + " - " + messageDetail);
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
		messageDetail = "Cancelled the edition of the application level";

		this.refreshDataTable();
		this.setControlVariablesToDefault();

		logger.info(messageDetail);
		this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

	}

}
