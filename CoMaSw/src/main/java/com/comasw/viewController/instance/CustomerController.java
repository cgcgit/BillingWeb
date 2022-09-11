/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
 any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/

package com.comasw.viewController.instance;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.omnifaces.util.Ajax;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import com.comasw.ejb.instance.CustomerEJBLocal;
import com.comasw.generalClass.BasicInstance;
import com.comasw.interfaces.IInstanceTable;
import com.comasw.model.tables.pojos.ItCustomer;
import com.comasw.model.tables.pojos.VwCustomerInstance;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.utilities.Formatter;
import com.comasw.utilities.Utilities;

@Named
@ViewScoped
public class CustomerController extends BasicInstance<VwCustomerInstance, ItCustomer, VwCustomerInstance>
		implements Serializable, IInstanceTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5656501787981937971L;

	Logger logger = (Logger) LogManager.getLogger(CustomerController.class);

	private static Integer GIVEN_NAME_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_GIVEN_NAME_LENGTH_MAX"));
	private static Integer FIRST_SURNAME_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_FIRST_SURNAME_LENGTH_MAX"));
	private static Integer SECOND_SURNAME_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_SECOND_SURNAME_LENGTH_MAX"));
	private static Integer IDENTITY_CARD_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_IDENTITY_CARD_LENGTH_MAX"));
	private static Integer CONTACT_PHONE_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_CONTACT_PHONE_LENGTH_MAX"));
	private static Integer E_MAIL_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CUSTOMER_E_MAIL_LENGTH_MAX"));
	private static Integer ADDRESS_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CUSTOMER_ADDRESS_LENGTH_MAX"));
	private static Integer CITY_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CUSTOMER_CITY_LENGTH_MAX"));
	private static Integer STATE_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CUSTOMER_STATE_LENGTH_MAX"));
	private static Integer COUNTRY_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CUSTOMER_COUNTRY_LENGTH_MAX"));
	private static Integer POST_CODE_LENGTH_MAX = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_POST_CODE_LENGTH_MAX"));
	private static Integer IBAN_LENGTH = Integer.valueOf(dbDefinitions.getString("CUSTOMER_IBAN_LENGTH"));
	private static Integer BANK_ENTITY_LENGTH = Integer.valueOf(dbDefinitions.getString("CUSTOMER_BANK_ENTITY_LENGTH"));
	private static Integer BANK_BRANCH_LENGTH = Integer.valueOf(dbDefinitions.getString("CUSTOMER_BANK_BRANCH_LENGTH"));
	private static Integer BANK_CONTROL_DIGIT_LENGTH = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_BANK_CONTROL_DIGIT_LENGTH"));
	private static Integer BANK_ACCOUNT_NUMBER_LENGTH = Integer
			.valueOf(dbDefinitions.getString("CUSTOMER_BANK_ACCOUNT_NUMBER_LENGTH"));

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private CustomerEJBLocal customerEJB;

	// ----- SELECTED DATA -----\\

	// ---- SEARCH CRITERIA ----\\

	private Integer searchCustomerId;

	private String searchIdentityCard;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the searchCustomerId
	 */
	public Integer getSearchCustomerId() {
		return searchCustomerId;
	}

	/**
	 * @param searchCustomerId the searchCustomerId to set
	 */
	public void setSearchCustomerId(Integer searchCustomerId) {
		this.searchCustomerId = searchCustomerId;
	}

	/**
	 * @return the searchIdentityCard
	 */
	public String getSearchIdentityCard() {
		return searchIdentityCard;
	}

	/**
	 * @param searchIdentityCard the searchIdentityCard to set
	 */
	public void setSearchIdentityCard(String searchIdentityCard) {
		this.searchIdentityCard = searchIdentityCard;
	}

	// -------------------
	// METHODS
	// -------------------

	public CustomerController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getNewData() == null) {
			this.setNewData(new ItCustomer());
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<VwCustomerInstance>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<VwCustomerInstance>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new VwCustomerInstance());
		}

		if (this.getSelectedDataList() == null) {
			this.setSelectedDataList(new ArrayList<VwCustomerInstance>());
		}

		if (this.getFilteredSelectedDataList() == null) {
			this.setFilteredSelectedDataList(new ArrayList<VwCustomerInstance>());
		}

		if (this.getHistoricDataList() == null) {
			this.setHistoricDataList(new ArrayList<ItCustomer>());
		}

		if (this.getFilteredHistoricDataList() == null) {
			this.setFilteredHistoricDataList(new ArrayList<ItCustomer>());
		}

		if (this.getBackupHistoricDataList() == null) {
			this.setBackupHistoricDataList(new ArrayList<ItCustomer>());
		}

		if (this.getSelectedHistoricData() == null) {
			this.setSelectedHistoricData(new ItCustomer());
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
		}

	}

	@Override
	public void loadDataList() {
		String message = "LOAD DATA";
		String messageDetail = "";
		if (this.getSearchDate() == null) {
			messageDetail = "The date value to search is null. Please fill the search date field";
			logger.error(message + " - " + messageDetail);
			FacesContext.getCurrentInstance().validationFailed();
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, message, messageDetail));
		} else {
			this.setDataList(customerEJB.findInstanceViewWithParameters(Optional.ofNullable(this.getSearchDate()),
					Optional.ofNullable(this.getSearchCustomerId()), Optional.empty(), Optional.empty(),
					Optional.ofNullable(this.getSearchIdentityCard()), Optional.empty(), Optional.empty(),
					Optional.empty(), Optional.empty()));
			if (this.getDataList().isEmpty()) {
				logger.info("No data to show");

			} else {
				logger.info("Load data sucessful");
			}
		}

	}

	@Override
	public void loadHistoricalDataList() {
		Integer id;
		if (this.getSelectedData() == null) {
			logger.error("The customer select is null");
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "LOAD HISTORIC DATA",
					"The customer select is null");
		} else {
			id = this.getSelectedData().getCustomerId();
			this.setHistoricDataList(customerEJB.findDataByCustomerId(id));
			if (this.getHistoricDataList().isEmpty()) {
				logger.info("No historical data data to show for customer id " + id);

			} else {
				logger.info("Load historical data sucessful for customer id " + id);
				System.out.println("datalist: " + historicDataList.toString());
			}

		}
	}

	@Override
	public void pushSearchButton() {
		String message = "SEARCH DATA";
		String messageDetail = "";

		if (this.getSearchDate() == null) {
			messageDetail = "The date value to search is null. Please fill the search date field";
			logger.error(message + " - " + messageDetail);
			FacesContext.getCurrentInstance().validationFailed();
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, message, messageDetail));
		} else {
			this.loadDataList();
			if (this.getDataList().isEmpty()) {
				messageDetail = "No data to show for search date:" + Formatter.localDateTimeToString(searchDate);
				logger.info(message + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

			} else {
				this.resetFilterDataTable();
				PrimeFaces.current().executeScript("PF('searchListWidget').show();");
				// Ajax.update(SEARCH_DATA_TABLE_ID);

				messageDetail = "Shown data for the search date:" + Formatter.localDateTimeToString(searchDate);
				logger.info(message + " - " + messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
			}

		}

	}

	@Override
	public void pushShowDetailRowButton() {
		String message = "SHOWN HISTORIC DATA";
		String messageDetail = "";

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);

		// Gets the selected data
		this.setSelectedData((VwCustomerInstance) dataTable.getRowData());

		if (this.getSelectedData() == null || this.getSelectedData().getCustomerId() == null
				|| this.getSelectedData().getCustomerId() == 0) {
			messageDetail = "The customer is null";
			logger.error(message + " - " + messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.setShowSelectedData(true);
			this.getSelectedDataList().clear();
			this.getSelectedDataList().add(this.getSelectedData());
			this.refreshHistoricDataTable();

			messageDetail = "Shown data for customer: ";
			logger.info(message + " - " + messageDetail + this.getSelectedData().toString());
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
					messageDetail + this.getSelectedData().getCustomerId().toString());

			PrimeFaces.current().executeScript("PF('searchListWidget').hide();");
			PrimeFaces.current().executeScript("PF('multipleAccordionPanelWidget').selectAll();");

		}

	}

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		ItCustomer dataObject;
		String message, message_detail;

		message = "EDIT ROW";

		// Gets the object to modify
		dataObject = (ItCustomer) event.getObject();

		// If we are editing a row, we must disabled all the other buttons
		this.setEditingMode(true);

		try {
			// The user was pushed the edit button
			message_detail = "Editing customer: " + dataObject.toString();
			logger.info(message_detail);

			// Store backup from the current data of the table
			Utilities.copyGenericList(this.historicDataList, this.backupHistoricDataList);
			// Sets the modified fields to the new values
			dataObject.setModifDate(LocalDateTime.now());
			dataObject.setModifUser(loggedUser.getUserCode());
			// Show an informative message
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, message_detail);

		} catch (EJBException e) {
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				message_detail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(message_detail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, message_detail);

			} else {
				message_detail = "ERROR - " + ne.getMessage();
				logger.fatal(message_detail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, message_detail);
			}

		} catch (Exception e) {
			message_detail = "ERROR - " + e.getCause().toString();
			logger.fatal(message_detail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, message_detail);
		}

	}

	@Override
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException {
		ItCustomer dataObject;
		String message, messageDetail;
		boolean validation = false;
		ItCustomer originalDataObject;
		ItCustomer otherRecordDataObject;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (ItCustomer) event.getObject();

		// NOTE: the table contains the modified data for both the directly modified
		// record data
		// and the endDate and startDate for the previous and subsequent records
		// respectively (if applicable)
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(HISTORIC_DATA_TABLE_ID);

		int pos = dataTable.getRowIndex();
		int lastRow = dataTable.getRowCount() - 1;

		// Validates the data
		validation = objectValidation(dataObject);

		try {
			if (validation) {
				// Check if the dates are changed
				originalDataObject = this.backupHistoricDataList.get(pos);
				if (lastRow != 0) {
					// There are more than one row, so if some date is changed, the previous or
					// subsequent data row could be also modified

					if (!originalDataObject.getStartDate().isEqual(dataObject.getStartDate())
							|| !originalDataObject.getEndDate().isEqual(dataObject.getEndDate())) {

						if (!originalDataObject.getStartDate().isEqual(dataObject.getStartDate())) {
							if (pos != 0) {
								// Change it's start date and it's not the first record ==> the previous end
								// date record could be also changed

								// Retrieve the original previous record
								otherRecordDataObject = this.backupHistoricDataList.get(pos - 1);
								if (!otherRecordDataObject.getEndDate()
										.isEqual(dataObject.getStartDate().minusDays(1))) {
									// The original previous record and the current new (modified) record are not
									// consecutives ==> modify the previous record
									dataTable.setRowIndex(pos - 1);
									otherRecordDataObject = (ItCustomer) dataTable.getRowData();
									dataTable.setRowIndex(pos);

									customerEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos - 1);
									customerEJB.deleteData(otherRecordDataObject);
								}

							}
						}
						if (!originalDataObject.getEndDate().isEqual(dataObject.getEndDate())) {
							if (pos != lastRow) {
								// Change it's start date and it's not the last record ==> the subsequent end
								// date record could be also changed

								// Retrieve the original subsequent record
								otherRecordDataObject = this.backupHistoricDataList.get(pos + 1);
								if (!dataObject.getEndDate()
										.isEqual(otherRecordDataObject.getStartDate().minusDays(1))) {
									// The current new (modified) record and the original subsequent record are not
									// consecutives ==> modify the subsequent record
									dataTable.setRowIndex(pos + 1);
									otherRecordDataObject = (ItCustomer) dataTable.getRowData();
									dataTable.setRowIndex(pos);
									customerEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos + 1);
									customerEJB.deleteData(otherRecordDataObject);

								}
							}
						}
					}
				}

				// delete the original data of the record
				customerEJB.deleteData(originalDataObject);
				// update the current data record
				customerEJB.updateHistoricDataRecord(dataObject);

				// Check if the status was changed to cancel or if activeDateChanged --> if so,
				// we must update other rows
				if (this.isToCancel() || this.isActiveDateChanged() || this.isCancelledDateChanged()) {
					int i;
					if (this.isActiveDateChanged() || this.isCancelledDateChanged()) {
						// update all the data table
						for (i = 0; i <= lastRow; i++) {
							dataTable.setRowIndex(i);
							dataObject = (ItCustomer) dataTable.getRowData();
							// accountEJB.updateHistoricDataRecord(dataObject);
							customerEJB.updateData(dataObject);
						}
					} else { // only the subsequent rows
						for (i = pos + 1; i <= lastRow; i++) {
							dataTable.setRowIndex(i);
							dataObject = (ItCustomer) dataTable.getRowData();
							// accountEJB.updateHistoricDataRecord(dataObject);
							customerEJB.updateData(dataObject);
						}
					}

					logger.info("Updated the status to cancel for the subsequent rows");

				}

				// set the row index to the current position
				dataTable.setRowIndex(pos);

				// reload the list
				this.loadHistoricalDataList();

				// Evaluates if the data was by adding or update
				messageDetail = "The changes for customer " + dataObject.toString() + " has done";
				logger.info(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

				// return the default values of the control variables
				this.setControlVariablesToDefault();

				messageDetail = "Storage the modified data sucessfully";
				logger.info(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
			} else {
				messageDetail = "ERROR - Data values are incorrect";
				logger.info(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
				FacesContext.getCurrentInstance().validationFailed();

			}

		} catch (EJBException e) {
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				FacesContext.getCurrentInstance().validationFailed();

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				FacesContext.getCurrentInstance().validationFailed();
			}

		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getCause().toString();
			logger.fatal(messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			FacesContext.getCurrentInstance().validationFailed();
		}

	}

	@Override
	public void onRowCancel(RowEditEvent<?> event) {
		ItCustomer dataObject;
		String message, messageDetail;

		message = "CANCEL UPDATE ROW";

		// Retrieved the data that was modified
		dataObject = (ItCustomer) event.getObject();

		try {
			this.refreshHistoricDataTable();
			messageDetail = "The changes for custom " + dataObject.toString() + " was cancelled";
			logger.info(messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);
			// return the default values of the control variables
			this.setControlVariablesToDefault();

		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getCause().toString();
			logger.fatal(messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}

	}

	@Override
	public void pushAddNewRowButton() {
		String message, messageDetail;
		DataTable dataTable;
		ItCustomer currentData = null;

		message = "ADD ROW";

		try {

			// Store the current data
			Utilities.copyGenericList(this.historicDataList, this.backupHistoricDataList);

			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (ItCustomer) dataTable.getRowData();
			this.setSelectedHistoricData((ItCustomer) Utilities.deepClone(currentData));

			this.setNewData(new ItCustomer());
			this.getNewData().from(currentData);

			// Sets the fromAddingRow value to true
			this.setFromAddingRow(true);
			this.changeNewDialogHeader();

			PrimeFaces.current().executeScript("PF('createNewDialogWidget').show();");

		} catch (EJBException e) {
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}

		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getCause().toString();
			logger.fatal(messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}
	}

	@Override
	public void pushDeleteRowButton() {
		String message, messageDetail;

		DataTable dataTable;
		ItCustomer currentData;

		message = "DELETE ROW";

		try {
			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (ItCustomer) dataTable.getRowData();
			this.setSelectedHistoricData((ItCustomer) Utilities.deepClone(currentData));

			this.changeDeleteMessage();

			PrimeFaces.current().executeScript("PF('deleteRowDialogWidget').show();");

		} catch (EJBException e) {
			Exception ne = (Exception) e.getCause();
			if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
				messageDetail = "PARSE ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

			} else {
				messageDetail = "ERROR - " + ne.getMessage();
				logger.fatal(messageDetail);
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}

		} catch (Exception e) {
			messageDetail = "ERROR - " + e.getCause().toString();
			logger.fatal(messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}

	}

	@Override
	public void pushCreateNewButton() {

		// Set the default dates and input values for the new data
		this.setNewData(new ItCustomer());
		this.getNewData().setStartDate(LocalDateTime.now());
		this.getNewData().setEndDate(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE));
		this.getNewData().setInputUser(this.loggedUser.getUserCode());
		this.getNewData().setInputDate(LocalDateTime.now());
		if (!this.isFromAddingRow()) {
			this.getNewData().setStatusId(STATUS_ID_PENDING_INSTANCE);
		}

		// Change the header to the new dialog
		this.changeNewDialogHeader();

		PrimeFaces.current().executeScript("PF('createNewDialogWidget').show();");

	}

	@Override
	public void pushConfirmButtonCreateNewDialog() {
		String message = "INSERT NEW DATA";
		String messageDetail = "";
		boolean error = false;
		Integer row = this.getCurrentHistoricRow();
		Integer id;

		try {
			if (this.objectValidation(this.getNewData())) {
				if (this.isFromAddingRow()) {
					// Gets the table
					DataTable dataTable = (DataTable) facesContext.getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
					error = splitRecords(dataTable, row, this.getNewData());
				} else {
					// create a new object
					id = customerEJB.insertData(this.getNewData());
					VwCustomerInstance object = customerEJB
							.findInstanceViewWithParameters(Optional.ofNullable(this.getNewData().getStartDate()),
									Optional.ofNullable(id), Optional.empty(), Optional.empty(), Optional.empty(),
									Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty())
							.get(0);

					this.setSelectedData(object);
					this.getSelectedDataList().clear();
					this.getSelectedDataList().add(this.getSelectedData());
					// this.setInjectSelectedData(object);

					messageDetail = "Data saves succesfully";
					logger.info("Create customer: " + this.getNewData().getCustomerId().toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);
				}

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

				// this.refreshSelectedDataAttribute();

				if (this.isFromAddingRow()) {
					this.refreshHistoricDataTable();
					// Ajax.update(HISTORIC_DATA_TABLE_ID);
					this.setFromAddingRow(false);

				} else {
					// Ajax.update(DATA_TABLE_ID);
					this.refreshHistoricDataTable();
					this.setShowSelectedData(true);
					PrimeFaces.current().executeScript("PF('multipleAccordionPanelWidget').selectAll();");
					messageDetail = "Shown data for customer: ";
					logger.info(message + " - " + messageDetail + this.getNewData().toString());
					//createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,	messageDetail + this.getNewData().getCustomerId());
				}
				PrimeFaces.current().executeScript("PF('createNewDialogWidget').hide();");

			}
		}

	}

	@Override
	public void pushCancelButtonCreateNewDialog() {
		if (this.isFromAddingRow()) {
			this.refreshHistoricDataTable();
			this.setFromAddingRow(false);
		}
		this.changeNewDialogHeader();
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(HISTORIC_DATA_TABLE_ID);
		dataTable.resetRows();

	}

	@Override
	public void pushCleanButtonCreateNewDialog() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pushConfirmButtonDeleteDialog() {
		String message = "DELETE ROW";
		String messageDetail = "";
		int currentPos, lastPos;
		boolean error = false;

		currentPos = -1;
		lastPos = -1;

		try {
			if (this.getSelectedHistoricData() != null) {
				// Gets the data
				DataTable dataTable = (DataTable) facesContext.getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
				currentPos = this.getRowHistoricSelected();
				lastPos = dataTable.getRowCount() - 1;

				if ((currentPos != 0) && (currentPos != lastPos)) {
					// middleRow ==> the previous end date and the subsequent start date must be
					// consecutive records ==> modify the previous end date to the subsequent start
					// date minus 1 day

					// Previous row data
					dataTable.setRowIndex(currentPos - 1);
					ItCustomer previousRecord = (ItCustomer) dataTable.getRowData();
					ItCustomer previousRecordOriginalData = (ItCustomer) Utilities.deepClone(previousRecord);

					// Subsequent row data
					dataTable.setRowIndex(currentPos + 1);
					ItCustomer subsequentRecord = (ItCustomer) dataTable.getRowData();

					// Set the endDate of the previows record to the startDate minus one date of the
					// subsequent record
					previousRecord.setEndDate(subsequentRecord.getStartDate().minusDays(1));
					previousRecord.setModifDate(LocalDateTime.now());
					previousRecord.setModifUser(this.getLoggedUser().getUserCode());

					// delete the selected data
					customerEJB.deleteData(this.getSelectedHistoricData());

					// delete the original previous record (to eliminate overlaps)
					customerEJB.deleteData(previousRecordOriginalData);

					// Update the previous record with the new endDate
					customerEJB.updateHistoricDataRecord(previousRecord);

					messageDetail = "Data deletes succesfully";
					logger.info(
							"Delete customer: " + this.getSelectedHistoricData().toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

					logger.info("Update the previous record end date sucessfully");
				} else {

					// delete the selected data
					customerEJB.deleteData(this.getSelectedHistoricData());

					messageDetail = "Data deletes succesfully";
					logger.info(
							"Delete customer: " + this.getSelectedHistoricData().toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);
				}

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

				if (lastPos == 0) {
					// if it was only one row, reset the selected data
					this.setSelectedData(null);
					this.getSelectedDataList().clear();
					this.getHistoricDataList().clear();
				} else {
					this.refreshHistoricDataTable();
				}
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

		String message, messageDetail;
		ItCustomer objectToValidate;
		boolean validation = true;

		if (this.isFromAddingRow()) {
			message = "ADD NEW CUSTOMER ROW VALIDATION";
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER VALIDATION";
			} else {
				message = "NEW CUSTOMER VALIDATION";
			}
		}

		objectToValidate = (ItCustomer) dataObject;

		if (objectToValidate != null) {

			if (!this.validateNewInstance(dataObject)) {
				validation = false;
			}

			if (!this.validateNewPersonal(dataObject)) {
				validation = false;
			}

			if (!this.validateNewAddress(dataObject)) {
				validation = false;
			}

			if (!this.validateNewContact(dataObject)) {
				validation = false;
			}

			if (!this.validateNewBankAccount(dataObject)) {
				validation = false;
			}

			if (validation) {
				// no error --> update panel
				Ajax.update(NEW_PANEL_DATA_ID);
			}

		} else {
			messageDetail = "ERROR - Empty values";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}
		return validation;
	}

	@Override
	public void retrieveBackupData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInitVariablesToDefault() {

		this.setEditingMode(false);
		this.setSearchDate(LocalDate.now().atStartOfDay());
		this.setFromAddingRow(false);
		this.setToCancel(false);
		this.setActiveDateChanged(false);
		this.setCancelledDateChanged(false);
		this.setPrevStatusId(-1);
		this.setShowSelectedData(false);

	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();

	}

	@Override
	public void resetFilterDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dataTableWidget').clearFilters()");
	}

	@Override
	public void resetFilterHistoricDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('historicTableWidget').clearFilters()");

	}

	@Override
	public void refreshDataTable() {
		this.resetFilterDataTable();
		this.loadDataList();
		Ajax.update(DATA_TABLE_ID);
	}

	@Override
	public void refreshHistoricDataTable() {
		if (this.getSelectedData() == null) {
			// recover the selected data from the selected table
			if (this.getSelectedDataList().get(0) != null) {
				this.setSelectedData(this.getSelectedDataList().get(0));
			}
		}
		this.resetFilterHistoricDataTable();
		this.loadHistoricalDataList();
		Ajax.update(HISTORIC_DATA_TABLE_ID);
	}

	@Override
	public void changeStatus(ValueChangeEvent e) {
		// Integer oldStatusId = (Integer) e.getOldValue();
		Integer newStatusId = (Integer) e.getNewValue();
		Integer originalStatusId;

		// Gets the current User
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(HISTORIC_DATA_TABLE_ID);
		ItCustomer data = (ItCustomer) dataTable.getRowData();

		this.setSelectedHistoricData(data);

		// Gets the row of the current User
		String row = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("currentHistoricalRow");
		this.setCurrentHistoricRow(Integer.parseInt(row));

		// Gets the status Id previous to the edition of the row
		originalStatusId = this.getBackupHistoricDataList().get(this.getCurrentRow()).getStatusId();

		if (STATUS_ID_CANC.equals(originalStatusId)) {
			// The original status was cancel status --> change status is not permitted
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "STATUS ERROR",
					"The status CANCEL can not be changed");
			data.setStatusId(originalStatusId);
			Ajax.updateColumn(dataTable, dataTable.getRowIndex());
		} else {
			// The original status was not cancel --> could be change
			if (STATUS_ID_CANC.equals(newStatusId)) {
				// New status = cancel
				// Set cancelFlag to true
				this.setToCancel(true);
				// Show confirmation dialog to change to cancel status (if confirm we need to
				// change
				// all subsequent rows to cancel status)
				this.changeStatusMessage();
				// Ajax.update("form:cancelStatusDialog");
				PrimeFaces.current().executeScript("PF('cancelStatusDialogWidget').show();");
			} else {
				// Other status than cancel
				if (this.getPrevStatusId() > -1) {
					// There is a previous change status in the same modification
					if (STATUS_ID_CANC.equals(this.prevStatusId)) {
						// the previous selection into the edition was cancel --> revert changes
						this.revertSubsequentCancelStatus();
					}
				}
			}

			// set the previous status id to the new status Id
			this.setPrevStatusId(newStatusId);
		}
	}

	@Override
	public void pushConfirmButtonChangeStatus() {
		String message = "CHANGE STATUS";
		String messageDetail = "";
		int i, currentPos, lastPos;
		boolean error = false;
		LocalDateTime cancelledDate;

		try {
			if (this.getSelectedHistoricData() != null) {

				// Gets the data
				DataTable dataTable = (DataTable) facesContext.getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
				currentPos = this.getCurrentHistoricRow();
				lastPos = dataTable.getRowCount() - 1;

				this.setToCancel(true);

				if (currentPos != lastPos) {
					// change status from subsequent rows
					for (i = (currentPos + 1); i <= lastPos; i++) {
						dataTable.setRowIndex(i);
						((ItCustomer) dataTable.getRowData()).setStatusId(STATUS_ID_CANC);
						Ajax.updateRow(dataTable, i);

					}

					dataTable.setRowIndex(currentPos);

					messageDetail = "Subsequent status change succesfully";
					logger.info("Change status customer: " + this.getSelectedHistoricData().toString() + " - "
							+ messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);
				}
				// set the cancelled date for all the records
				if (this.getSelectedHistoricData().getCancelledDate() == null) {
					cancelledDate = LocalDateTime.now();
					this.setCancelledDateChanged(true);
					// change status from subsequent rows
					for (i = 0; i <= lastPos; i++) {
						dataTable.setRowIndex(i);
						((ItCustomer) dataTable.getRowData()).setCancelledDate(cancelledDate);
						;
						Ajax.updateRow(dataTable, i);

					}
				}

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
			}
		}

	}

	@Override
	public void revertSubsequentCancelStatus() {
		DataTable dataTable;
		int currentPos, lastPos;
		ItCustomer currentObject;

		dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
		lastPos = dataTable.getRowCount() - 1;
		currentPos = this.getCurrentHistoricRow();

		if (this.isToCancel()) {

			// System.out.println("actualStatusId: " + currentUser.getStatusId());
			if (this.isFromAddingRow()) {
				// The old value was equal to the previous row
				currentObject = (ItCustomer) dataTable.getRowData();
				currentObject.setStatusId(((ItCustomer) this.backupHistoricDataList.get(currentPos - 1)).getStatusId());
				Ajax.updateRow(dataTable, currentPos);
			}

			for (int i = currentPos + 1; i <= lastPos; i++) {
				dataTable.setRowIndex(i);

				currentObject = (ItCustomer) dataTable.getRowData();
				if (!STATUS_ID_CANC.equals(currentObject.getStatusId())) {
					// the status of the row is not cancel ==> updates the status
					// and the modif values

					currentObject.setStatusId(((ItCustomer) this.backupHistoricDataList.get(i)).getStatusId());
					currentObject.setInputDate(LocalDateTime.now());
					currentObject.setInputUser(this.getLoggedUser().getUserCode());
					Ajax.updateRow(dataTable, i);
				}
			}

			dataTable.setRowIndex(currentPos);

			dataTable.setRowIndex(currentPos);

			// Set the previous status Id to a invalid value
			this.setPrevStatusId(-1);

			// Set cancelFlag to false
			this.setToCancel(false);
		}
	}

	@Override
	public void changeNewDialogHeader() {
		if (this.isFromAddingRow()) {
			this.setDialogHeaderText("Add New Historic Row for Customer");
		} else {
			this.setDialogHeaderText("Create new Customer");
		}
	}

	@Override
	public void changeDeleteMessage() {
		this.setDeleteMessageDialog("Customer " + this.getSelectedHistoricData().getGivenName() + " "
				+ this.getSelectedHistoricData().getFirstSurname() + " "
				+ this.getSelectedHistoricData().getSecondSurname()
				+ " - Do you really want to delete historic period ["
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getEndDate()) + "]? ");
	}

	@Override
	public void changeStatusMessage() {
		this.setCancelMessageDialog("Customer " + this.getSelectedHistoricData().getGivenName() + " "
				+ this.getSelectedHistoricData().getFirstSurname() + " "
				+ this.getSelectedHistoricData().getSecondSurname()
				+ " - Do you really want to set the status to cancel from historic period ["
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getEndDate()) + "] onwards?");
	}

	/**
	 * Validates the instance tab of the wizard
	 */
	public boolean validateNewInstance(Object object) {
		String message = "INSTANCE DATA VALIDATION";
		String messageDetail;
		boolean validation = true;
		
		ItCustomer objectToValidate= (ItCustomer) object;

		LocalDateTime minDate = Formatter.stringToLocalDateTime("01/01/1900");
		LocalDateTime maxDate = Formatter.stringToLocalDateTime("31/12/9999");

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (objectToValidate.getCustomerTypeId() == null) {
			messageDetail = "ERROR - The customer type of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getStartDate() == null) {
			messageDetail = "ERROR - The start date of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getStartDate().compareTo(minDate) < 0) {
			messageDetail = "ERROR - The start date of the customer can not be less than "
					+ Formatter.localDateTimeToString(minDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getStartDate().compareTo(maxDate) > 0) {
			messageDetail = "ERROR - The start date of the customer can not be greater than "
					+ Formatter.localDateTimeToString(maxDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getEndDate() == null) {
			messageDetail = "ERROR - The end date of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getEndDate().compareTo(minDate) < 0) {
			messageDetail = "ERROR - The end date of the customer can not be less than "
					+ Formatter.localDateTimeToString(minDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getEndDate().compareTo(maxDate) > 0) {
			messageDetail = "ERROR - The end date of the customer can not be greater than "
					+ Formatter.localDateTimeToString(maxDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		return validation;

	}

	/**
	 * Validates the personal data tab of the wizard
	 */
	public boolean validateNewPersonal(Object object) {
		String message = "PERSONAL DATA VALIDATION";
		String messageDetail;
		boolean validation = true;

		ItCustomer objectToValidate= (ItCustomer) object;
		
		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (objectToValidate.getGivenName() != null) {
			objectToValidate.setGivenName(objectToValidate.getGivenName().trim());
		}

		if (objectToValidate.getFirstSurname() != null) {
			objectToValidate.setFirstSurname(objectToValidate.getFirstSurname().trim());
		}

		if (objectToValidate.getSecondSurname() != null) {
			objectToValidate.setSecondSurname(objectToValidate.getSecondSurname().trim());
		}

		if (objectToValidate.getIdentityCard() != null) {
			objectToValidate.setIdentityCard(objectToValidate.getIdentityCard().toUpperCase().trim());
		}

		if (objectToValidate.getGivenName() == null || objectToValidate.getGivenName().isEmpty()) {
			messageDetail = "ERROR - The given name of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getGivenName().length())
				.compareTo(CustomerController.GIVEN_NAME_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The given name of the customer (" + objectToValidate.getGivenName().length()
					+ " characters) exceeds the limit of " + CustomerController.GIVEN_NAME_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getFirstSurname() == null || objectToValidate.getFirstSurname().isEmpty()) {
			messageDetail = "ERROR - The first surname of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getFirstSurname().length())
				.compareTo(CustomerController.FIRST_SURNAME_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The first surname of the customer (" + objectToValidate.getFirstSurname().length()
					+ " characters) exceeds the limit of " + CustomerController.FIRST_SURNAME_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getSecondSurname() != null && (!objectToValidate.getSecondSurname().isEmpty())) {
			if (((Integer) objectToValidate.getSecondSurname().length())
					.compareTo(CustomerController.SECOND_SURNAME_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The second surname of the customer ("
						+ objectToValidate.getSecondSurname().length() + " characters) exceeds the limit of "
						+ CustomerController.SECOND_SURNAME_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}
		}

		if (objectToValidate.getIdentityCardTypeId() == null) {
			messageDetail = "ERROR - The identity card type of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getIdentityCard() == null || objectToValidate.getIdentityCard().isEmpty()) {
			messageDetail = "ERROR - The identity card of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getIdentityCard().length())
				.compareTo(CustomerController.IDENTITY_CARD_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The identity card of the customer (" + objectToValidate.getIdentityCard().length()
					+ " characters) exceeds the limit of " + CustomerController.SECOND_SURNAME_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getContactPhone() != null && !objectToValidate.getContactPhone().isEmpty()) {
			if (((Integer) objectToValidate.getContactPhone().length())
					.compareTo(CustomerController.CONTACT_PHONE_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The contact phone of the customer ("
						+ objectToValidate.getContactPhone().length() + " characters) exceeds the limit of "
						+ CustomerController.CONTACT_PHONE_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}
		}
		return validation;

	}

	/**
	 * Validates the address tab of the wizard
	 */
	public boolean validateNewAddress(Object object) {
		String message = "ADDRESS DATA VALIDATION";
		String messageDetail;
		boolean validation = true;
		
		ItCustomer objectToValidate= (ItCustomer) object;

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (objectToValidate != null) {
			objectToValidate.setAddress(objectToValidate.getAddress().trim());
		}

		if (objectToValidate != null) {
			objectToValidate.setCity(objectToValidate.getCity().trim());
		}

		if (objectToValidate.getState() != null) {
			objectToValidate.setState(objectToValidate.getState().trim());
		}

		if (objectToValidate.getCountry() != null) {
			objectToValidate.setCountry(objectToValidate.getCountry().trim());
		}

		if (objectToValidate.getPostCode() != null) {
			objectToValidate.setPostCode(objectToValidate.getPostCode().trim());
		}

		if (objectToValidate.getAddress() == null || objectToValidate.getAddress().isEmpty()) {
			messageDetail = "ERROR - The address of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getAddress().length())
				.compareTo(CustomerController.ADDRESS_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The address of the customer (" + objectToValidate.getAddress().length()
					+ " characters) exceeds the limit of " + CustomerController.ADDRESS_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getCity() == null || objectToValidate.getCity().isEmpty()) {
			messageDetail = "ERROR - The city of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getCity().length()).compareTo(CustomerController.CITY_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The city of the customer (" + objectToValidate.getCity().length()
					+ " characters) exceeds the limit of " + CustomerController.CITY_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getState() == null || objectToValidate.getState().isEmpty()) {
			messageDetail = "ERROR - The state of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getState().length())
				.compareTo(CustomerController.STATE_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The state of the customer (" + objectToValidate.getState().length()
					+ " characters) exceeds the limit of " + CustomerController.STATE_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getCountry() == null || objectToValidate.getCountry().isEmpty()) {
			messageDetail = "ERROR - The country of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getCountry().length())
				.compareTo(CustomerController.COUNTRY_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The country of the customer (" + objectToValidate.getCountry().length()
					+ " characters) exceeds the limit of " + CustomerController.COUNTRY_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getPostCode() == null || objectToValidate.getPostCode().isEmpty()) {
			messageDetail = "ERROR - The post code of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getPostCode().length())
				.compareTo(CustomerController.POST_CODE_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The post code of the customer (" + objectToValidate.getPostCode().length()
					+ " characters) exceeds the limit of " + CustomerController.POST_CODE_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		return validation;

	}

	/**
	 * Validates the contact tab of the wizard
	 */
	public boolean validateNewContact(Object object) {
		String message = "CONTACT DATA VALIDATION";
		String messageDetail;
		boolean validation = true;
		
		ItCustomer objectToValidate= (ItCustomer) object;

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (objectToValidate.getContactPhone() != null) {
			objectToValidate.setContactPhone(objectToValidate.getContactPhone().trim());
		}

		if (objectToValidate.getEMail() != null) {
			objectToValidate.setEMail(objectToValidate.getEMail().trim());
		}
		if (objectToValidate.getContactPhone() != null && !objectToValidate.getContactPhone().isEmpty()) {
			if (((Integer) objectToValidate.getContactPhone().length())
					.compareTo(CustomerController.CONTACT_PHONE_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The contact phone of the customer ("
						+ objectToValidate.getContactPhone().length() + " characters) exceeds the limit of "
						+ CustomerController.CONTACT_PHONE_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else {
				if (!Utilities.phoneNumberValidation(objectToValidate.getContactPhone())) {
					messageDetail = "Error - Contact phone wrong format";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					validation = false;
				}
			}
		}

		if (objectToValidate.getEMail() != null && !objectToValidate.getEMail().isEmpty()) {
			if (((Integer) objectToValidate.getEMail().length()).compareTo(CustomerController.E_MAIL_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The e-mail of the customer (" + objectToValidate.getEMail().length()
						+ " characters) exceeds the limit of " + CustomerController.E_MAIL_LENGTH_MAX.toString()
						+ " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else {
				if (!Utilities.emailValidation(objectToValidate.getEMail())) {
					messageDetail = "Error - E-mail wrong format";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					validation = false;
				}
			}
		}

		return validation;

	}

	/**
	 * Validates the bank account tab of the wizard
	 */
	public boolean validateNewBankAccount(Object object) {
		String message = "BANK ACCOUNT DATA VALIDATION";
		String messageDetail;
		boolean validation = true;
		boolean filledBankData = true;
		
		ItCustomer objectToValidate= (ItCustomer) object;

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (objectToValidate.getIban() != null) {
			objectToValidate.setIban(objectToValidate.getIban().toUpperCase().trim());
		}

		if ((objectToValidate.getIban() == null || objectToValidate.getIban().isEmpty())
				&& (objectToValidate.getBankEntity() == null || objectToValidate.getBankEntity().isEmpty())
				&& (objectToValidate.getBankControlDigit() == null
						|| objectToValidate.getBankControlDigit().isEmpty())
				&& (objectToValidate.getBankAccountNumber() == null
						|| objectToValidate.getBankAccountNumber().isEmpty())) {
			// no one of data bank field was filled
			filledBankData = false;
		} else {
			if (objectToValidate.getIban() != null && (!objectToValidate.getIban().isEmpty())
					&& objectToValidate.getBankEntity() != null && (!objectToValidate.getBankEntity().isEmpty())
					&& objectToValidate.getBankBranch() != null && (!objectToValidate.getBankBranch().isEmpty())
					&& objectToValidate.getBankControlDigit() != null
					&& (!objectToValidate.getBankControlDigit().isEmpty())
					&& objectToValidate.getBankAccountNumber() != null
					&& (!objectToValidate.getBankAccountNumber().isEmpty())) {
				// all data bank field was filled
				filledBankData = true;
			} else {
				// no all of data bank field was filled (but at least one field)
				messageDetail = "Error - The bank data must be all filled or all unfilled";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
				filledBankData = false;
			}
		}

		if (filledBankData) {
			// Validates the bank data
			if (((Integer) objectToValidate.getIban().length()).compareTo(CustomerController.IBAN_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The IBAN of the customer (" + objectToValidate.getIban().length()
						+ " characters) must be " + CustomerController.IBAN_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankEntity().length())
					.compareTo(CustomerController.BANK_ENTITY_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank entity of the customer (" + objectToValidate.getBankEntity().length()
						+ " characters) must be " + CustomerController.BANK_ENTITY_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankBranch().length())
					.compareTo(CustomerController.BANK_BRANCH_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank branch of the customer (" + objectToValidate.getBankBranch().length()
						+ " characters) must be " + CustomerController.BANK_BRANCH_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankControlDigit().length())
					.compareTo(CustomerController.BANK_CONTROL_DIGIT_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank control digit of the customer ("
						+ objectToValidate.getBankControlDigit().length() + " characters) must be "
						+ CustomerController.BANK_CONTROL_DIGIT_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankAccountNumber().length())
					.compareTo(CustomerController.BANK_ACCOUNT_NUMBER_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank account number of the customer ("
						+ objectToValidate.getBankAccountNumber().length() + " characters) must be "
						+ CustomerController.BANK_ACCOUNT_NUMBER_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}
		}

		return validation;

	}

	public String onFlowProcess(FlowEvent event) {

		String currentStep = event.getOldStep();
		String stepToGo = event.getNewStep();
		boolean validate = true;

		switch (currentStep) {
		case "instanceDataTab":

			validate = this.validateNewInstance(this.getNewData());

			break;
		case "personalDataTab":
			if (!stepToGo.equals("instanceDataTab")) {
				validate = this.validateNewPersonal(this.getNewData());
			}
			break;
		case "addressTab":
			if (!stepToGo.equals("personalDataTab")) {
				validate = this.validateNewAddress(this.getNewData());
			}
			break;
		case "contactTab":
			if (!stepToGo.equals("addressTab")) {
				validate = this.validateNewContact(this.getNewData());
			}
			break;

		case "bankAccountTab":
			if (!stepToGo.equals("contactTab")) {
				validate = this.validateNewBankAccount(this.getNewData());
			}
			break;
		}

		if (validate) {
			return stepToGo;
		} else {
			return currentStep;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public boolean splitRecords(DataTable dataTable, int row, Object newRow) {
		String message = "INSERT NEW DATA";
		String messageDetail = "";
		boolean error = false;
		boolean coverGap = false;
		ItCustomer newObject, originalPreviousDataRow, previousDataRow, subsequentDataRow;

		try {

			newObject = (ItCustomer) newRow;

			Integer totalRows = dataTable.getRowCount();

			// The previous row from new data is the current row on the data table
			previousDataRow = this.getBackupHistoricDataList().get(row);
			// Gets original values from previous row --> backup
			originalPreviousDataRow = (ItCustomer) Utilities.deepClone(previousDataRow);

			// new historic version from existing object
			if (!this.rangeDateValidation(facesContext, externalContext, previousDataRow.getStartDate(),
					previousDataRow.getEndDate(), newObject.getStartDate(), newObject.getEndDate())) {
				// not valid dates
				error = true;
			} else {
				// validates other date condition
				if (newObject.getStartDate().isEqual(Formatter.stringToLocalDateTime(Formatter.DEFAULT_START_DATE))
						&& (row != 0)) {
					// new start date = minDate for a row different from first row ==> error
					messageDetail = "Error in dates - Only the first row can sets the start date to the minimum allowed date.";
					logger.info("Create customer: " + newObject.toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);
					error = true;

				}
				if (newObject.getEndDate().isEqual(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE))
						&& row != (totalRows - 1)) {
					// new end date = maxDate for a row different from last row ==> error
					messageDetail = "Error in dates - Only the last row can sets the end date to the maximum allowed date.";
					logger.info("Create custom: " + newObject.toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);
					error = true;

				}

				if (!error) {
					// dates are OK

					if (row != totalRows - 1) {
						// Check if de data to insert is to cover a gap
						// Retrieve the subsequent record data
						subsequentDataRow = this.backupHistoricDataList.get(row + 1);

						if (previousDataRow.getEndDate().isEqual(newObject.getStartDate().minusDays(1))
								&& newObject.getEndDate().isEqual(subsequentDataRow.getStartDate().minusDays(1))) {
							// exceptional case: there is a gap between the records and the new record comes
							// to cover it
							// ==> inserts only the new record
							// ...currentSD ............ currentED.....subsequentSD ........... subsequentED
							// ...v .................... v.............v ...................... v
							// ...[-----currentValue----]..............[-----subsequentValue----]
							// ..........................[--newValue--]
							// ..........................^ .......... ^
							// .........................newSD ....... newED

							customerEJB.insertNewHistoricDataRecord(newObject);

							coverGap = true;
						}
					}

					if (!coverGap) {
						// normal case: the new record and the exist record are consecutives or overlaps

						if ((newObject.getEndDate().isEqual(previousDataRow.getStartDate()))
								|| (newObject.getEndDate().isBefore(previousDataRow.getStartDate()))) {
							// .............currentSD ............ currentED
							// .............v ................... v
							// .............[-----currentValue----]
							// [--newValue--]]
							// ^ ......... ^^
							// newSD ..... newED
							if (row == 0) {
								// first row ==> can be a record before the first record

								if (previousDataRow.getStartDate().isEqual(newObject.getEndDate().plusDays(1))) {
									// the current record not to be modify ==> inserts only the new record <p>
									// ..............currentSD ............ currentED <p>
									// ..............v ................... v <p>
									// ..............[-----currentValue----] <p>
									// [--newValue--] <p>
									// ^ .......... ^ <p>
									// newSD ..... newED <p>

									customerEJB.insertNewHistoricDataRecord(newObject);
								} else {
									if (newObject.getEndDate().isEqual(previousDataRow.getStartDate())) {
										// [current start date, current end date] <p>
										// becomes to: <p>
										// [new start date, new end date] <p>
										// [new end date + 1, current end date] <p>

										// ....currentSD'=newED+1 ............ currentED <p>
										// ............. v ................... v <p>
										// ..............[-----currentValue----] <p>
										// [--newValue--] <p>
										// ^ .......... ^ <p>
										// newS ....... newED <p>

										// set first section of the record with the new value
										customerEJB.insertNewHistoricDataRecord(newObject);

										// delete the original record (to eliminate overlapping)
										customerEJB.deleteData(originalPreviousDataRow);

										// set the modified values
										previousDataRow.setModifUser(this.loggedUser.getUserCode());
										previousDataRow.setModifDate(LocalDateTime.now());
										// set second section of the record with the original value (except
										// dates)
										previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));

										customerEJB.updateHistoricDataRecord(previousDataRow);

									} else {
										// no consecutive records ==> error
										messageDetail = "Error in dates - The new and current dates are not consecutives.";
										logger.info("Create custom: " + newObject.toString() + " - " + messageDetail);
										this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO,
												message, messageDetail);
										error = true;
									}
								}

							} else {
								// not the first row ==> not allowed
								messageDetail = "Error in dates - The new end date can not be less than current start date.";
								logger.info("Create custom: " + newObject.toString() + " - " + messageDetail);
								this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
										messageDetail);
								error = true;
							}
						} else {
							if (newObject.getStartDate().isEqual(previousDataRow.getStartDate())) {
								// ..............currentSD .................. currentED <p>
								// ..............v ........................... v <p>
								// ..............[-----------------------------] <p>
								// ..............[-----------] <p>
								// ..............^ ......... ^ <p>
								// ..............newSD ..... newED <p>
								// becomes to: <p>
								// ..................currentSD'=newED+1 ....... currentED <p>
								// ...........................v ............... v <p>
								// ...........................[--.currentValue--] <p>
								// .............[--newValue--] <p>
								// .............^ .......... ^ <p>
								// .............newSD ...... newED <p>

								// delete the original record (to eliminate overlapping)
								customerEJB.deleteData(originalPreviousDataRow);

								// split the record --> set first section of the record with the new value
								previousDataRow = (ItCustomer) Utilities.deepClone(newObject);
								previousDataRow.setInputDate(LocalDateTime.now());
								previousDataRow.setInputUser(this.getLoggedUser().getUserCode());

								customerEJB.updateHistoricDataRecord(previousDataRow);

								// split the record --> set second section of the record with the original value
								// (except dates)
								previousDataRow = (ItCustomer) Utilities.deepClone(originalPreviousDataRow);
								// set the modified values
								previousDataRow.setModifUser(this.loggedUser.getUserCode());
								previousDataRow.setModifDate(LocalDateTime.now());
								// set the new start date
								previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));
								customerEJB.updateHistoricDataRecord(previousDataRow);

							} else {
								if (newObject.getEndDate().isEqual(previousDataRow.getEndDate())) {
									// .............currentSD .................. currentED <p>
									// .............v ........................... v <p>
									// .............[-----------------------------] <p>
									// ...............................[-----------] <p>
									// ...............................^ ......... ^ <p>
									// ...............................newSD ..... newED <p>
									// becomes to: <p>
									// .......currentSD........... currentED'=newSD-1 <p>
									// .............v .............. v <p>
									// .............[--currentValue--] <p>
									// ...............................[--newValue--] <p>
									// ...............................^ .......... ^ <p>
									// ...............................newSD ...... newED <p>

									// split the record --> set first section of the record with the original
									// value
									// (except dates)

									// delete the original record (to eliminate overlapping)
									customerEJB.deleteData(originalPreviousDataRow);

									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new endDate
									previousDataRow.setEndDate(newObject.getStartDate().minusDays(1));
									customerEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set second section of the record with the new value
									previousDataRow = (ItCustomer) Utilities.deepClone(newObject);
									previousDataRow.setInputDate(LocalDateTime.now());
									previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
									customerEJB.updateHistoricDataRecord(previousDataRow);

								} else {
									// ........currentSD ..................................... currentED <p>
									// .........v ............................................. v <p>
									// .........[-----------------------------------------------] <p>
									// .....................[-----------] <p>
									// .....................^ ......... ^
									// .....................newSD ..... newED <p>
									// becomes to: <p>
									// ....currentSD............ currentED'=newSD-1 <p>
									// .........v .............. v <p>
									// .........[--currentValue--] <p>
									// ...........................[--newValue--] <p>
									// ...........................^ .......... ^ <p>
									// ...........................newSD. ..... newED <p>
									// .........................................[--currentValue--] <p>
									// .........................................^ .............. ^ <p>
									// ......................................otherSD=newED+1 ...
									// otherED=currentED <p>
									//

									// delete the original record (to eliminate overlapping)
									customerEJB.deleteData(originalPreviousDataRow);

									// split the record --> set first section of the record with the original
									// value
									// (except dates)
									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new endDate
									previousDataRow.setEndDate(newObject.getStartDate().minusDays(1));
									customerEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set second section of the record with the new value
									previousDataRow = (ItCustomer) Utilities.deepClone(newObject);
									previousDataRow.setInputDate(LocalDateTime.now());
									previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
									customerEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set third section of the record with the original
									// values
									// (except dates)
									previousDataRow = (ItCustomer) Utilities.deepClone(originalPreviousDataRow);
									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new startDate and endDate
									previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));
									previousDataRow.setEndDate(originalPreviousDataRow.getEndDate());
									customerEJB.updateHistoricDataRecord(previousDataRow);

								}
							}

						}
					}

				}
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
			return error;
		}
	}

	@Override
	public void changeSearchDataTableTitle() {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * Action to do when push the search customer button in create new account form
	 */
	@Override
	public void pushSearchParentButton() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void addParentToInstanceRowButton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeActiveDate(ValueChangeEvent e) {
		// Integer oldStatusId = (Integer) e.getOldValue();
		LocalDateTime newActiveDate = (LocalDateTime) e.getNewValue();
		LocalDateTime originalActiveDate;
		int i;

		// Gets the current User
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(HISTORIC_DATA_TABLE_ID);
		ItCustomer data = (ItCustomer) dataTable.getRowData();

		this.setSelectedHistoricData(data);

		// Gets the row of the current User
		String row = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("currentHistoricalRow");
		this.setCurrentHistoricRow(Integer.parseInt(row));

		// Gets the status Id previous to the edition of the row
		originalActiveDate = this.getBackupHistoricDataList().get(this.getCurrentRow()).getActiveDate();

		if ((originalActiveDate == null) || ((newActiveDate == null) && !(originalActiveDate == null))
				|| (!originalActiveDate.isEqual(newActiveDate))) {

			this.setActiveDateChanged(true);

			// the active date was changed
			for (i = 0; i < dataTable.getRowCount(); i++) {
				dataTable.setRowIndex(i);
				((ItCustomer) dataTable.getRowData()).setActiveDate(newActiveDate);
				;
				Ajax.updateRow(dataTable, i);

			}

		}
	}

	@Override
	public void changeCancelledDate(ValueChangeEvent e) {
		// Integer oldStatusId = (Integer) e.getOldValue();
		LocalDateTime newCancelledDate = (LocalDateTime) e.getNewValue();
		LocalDateTime originalCancelledDate;
		int i;

		// Gets the current User
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(HISTORIC_DATA_TABLE_ID);
		ItCustomer data = (ItCustomer) dataTable.getRowData();

		this.setSelectedHistoricData(data);

		// Gets the row of the current User
		String row = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("currentHistoricalRow");
		this.setCurrentHistoricRow(Integer.parseInt(row));

		// Gets the status Id previous to the edition of the row
		originalCancelledDate = this.getBackupHistoricDataList().get(this.getCurrentRow()).getActiveDate();

		if ((originalCancelledDate == null) || ((newCancelledDate == null) && !(originalCancelledDate == null))
				|| (!originalCancelledDate.isEqual(newCancelledDate))) {

			this.setCancelledDateChanged(true);

			// the active date was changed
			for (i = 0; i < dataTable.getRowCount(); i++) {
				dataTable.setRowIndex(i);
				((ItCustomer) dataTable.getRowData()).setActiveDate(newCancelledDate);
				;
				Ajax.updateRow(dataTable, i);

			}

		}
	}

}
