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
import com.comasw.generalClass.BasicHistoricWithLists;
import com.comasw.interfaces.IEditableHistoricTable;
import com.comasw.model.tables.pojos.ItCustomer;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.utilities.Formatter;
import com.comasw.utilities.Utilities;

@Named
@ViewScoped
public class CustomerController extends BasicHistoricWithLists<ItCustomer>
		implements Serializable, IEditableHistoricTable {

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
	
	private static Integer STATUS_ID_PENDING_INSTANCE = Integer
			.valueOf(dbDefinitions.getString("STATUS_ID_PENDING_INSTANCE"));

	/**
	 * 
	 */
	private static final long serialVersionUID = 5656501787981937971L;

	Logger logger = (Logger) LogManager.getLogger(CustomerController.class);

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private CustomerEJBLocal customerEJB;

	// ----- SELECTED DATA -----\\

	
	/**
	 * Selected data from dataList
	 */
	private ItCustomer injectSelectedData;

	/**
	 * Selected data row in the table
	 */
	
	private ItCustomer injectSelectedHistoricData;

	// ----- NEW DATA -----\\

	
	private ItCustomer injectNewData;

	// ---- SEARCH CRITERIA ----\\

	private Integer searchCustomerId;

	private String searchIdentityCard;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the selectedData
	 */
	public ItCustomer getInjectSelectedData() {
		return injectSelectedData;
	}

	/**
	 * @param selection the selectedData to set
	 */
	public void setInjectSelectedData(ItCustomer injectSelectedData) {
		this.injectSelectedData = injectSelectedData;
	}

	/**
	 * @return the selectedHistoricData
	 */
	public ItCustomer getInjectSelectedHistoricData() {
		return injectSelectedHistoricData;
	}

	/**
	 * @param selectedHistoricData the selectedHistoricData to set
	 */
	public void setInjectSelectedHistoricData(ItCustomer injectSelectedHistoricData) {
		this.injectSelectedHistoricData = injectSelectedHistoricData;
	}

	/**
	 * @return the newData
	 */
	public ItCustomer getInjectNewData() {
		return injectNewData;
	}

	/**
	 * @param newData the newData to set
	 */
	public void setInjectNewData(ItCustomer injectNewData) {
		this.injectNewData = injectNewData;
	}

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

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<ItCustomer>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<ItCustomer>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new ItCustomer());
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
			this.setDataList(customerEJB.findInstanceWithParameters(Optional.ofNullable(this.getSearchDate()),
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
		Integer customerId;
		if (this.getSelectedData() == null) {
			logger.error("The customer select is null");
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "LOAD HISTORIC DATA",
					"The customer select is null");
		} else {
			customerId = this.getSelectedData().getCustomerId();
			this.setHistoricDataList(customerEJB.findDataByCustomerId(customerId));
			if (this.getHistoricDataList().isEmpty()) {
				logger.info("No historical data data to show for customer id " + customerId);

			} else {
				logger.info("Load historical data sucessful for customer id " + customerId);
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

		if (this.getInjectSelectedData() == null) {
			this.setInjectSelectedData(new ItCustomer());
		}

		// Gets the selected data
		this.setInjectSelectedData((ItCustomer) dataTable.getRowData());

		if (this.getInjectSelectedData() == null || this.getInjectSelectedData().getCustomerId() == null
				|| this.getInjectSelectedData().getCustomerId() == 0) {
			messageDetail = "The customer is null";
			logger.error(message + " - " + messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.setShowSelectedData(true);
			this.setSelectedData(this.getInjectSelectedData());
			loadHistoricalDataList();

			messageDetail = "Shown data for customer: ";
			logger.info(message + " - " + messageDetail + this.getInjectSelectedData().toString());
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
					messageDetail + injectSelectedData.getCustomerId().toString());

			PrimeFaces.current().executeScript("PF('searchListWidget').hide();");
			PrimeFaces.current().executeScript("PF('multipleAccordionPanelWidget').selectAll();");
			resetFilterHistoricDataTable();

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
		boolean changeHistoricRecords = false;

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

									changeHistoricRecords = true;
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

									changeHistoricRecords = true;
								}
							}
						}
					}
				}

				if (changeHistoricRecords) {

					// update the current data record
					customerEJB.updateHistoricDataRecord(dataObject);
					// delete the original data of the record
					customerEJB.deleteData(originalDataObject);
				} else {
					customerEJB.updateData(dataObject);
				}

				// Check if the status was changed to cancel --> if so, we must update the
				// status of the subsequent rows
				if (this.isToCancel()) {
					int i;
					for (i = pos + 1; i <= lastRow; i++) {
						dataTable.setRowIndex(i);
						dataObject = (ItCustomer) dataTable.getRowData();
						// customerEJB.updateHistoricDataRecord(dataObject);
						customerEJB.updateData(dataObject);
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
			this.loadHistoricalDataList();

			messageDetail = "The changes for customer " + dataObject.toString() + " was cancelled";
			logger.info(messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message, messageDetail);

			// return the default values of the control variables
			this.setControlVariablesToDefault();

			// update the historic table
			// Ajax.update(HISTORIC_DATA_TABLE_ID);

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
			this.setInjectSelectedHistoricData((ItCustomer) Utilities.deepClone(currentData));

			this.injectNewData = new ItCustomer();
			injectNewData.from(currentData);

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
			this.setInjectSelectedHistoricData((ItCustomer) Utilities.deepClone(currentData));

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
		this.injectNewData = new ItCustomer();
		this.injectNewData.setStartDate(Formatter.stringToLocalDateTime(Formatter.DEFAULT_START_DATE));
		this.injectNewData.setEndDate(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE));
		this.injectNewData.setInputUser(this.loggedUser.getUserCode());
		this.injectNewData.setInputDate(LocalDateTime.now());		
		if (!this.isFromAddingRow()) {
			this.injectNewData.setStatusId(STATUS_ID_PENDING_INSTANCE);
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
		boolean coverGap = false;
		Integer row = this.getCurrentHistoricRow();
		ItCustomer originalPreviousDataRow, previousDataRow, subsequentDataRow;

		try {
			if (this.objectValidation(injectNewData)) {

				if (this.isFromAddingRow()) {
					// Gets the table
					DataTable dataTable = (DataTable) facesContext.getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
					Integer totalRows = dataTable.getRowCount();

					// The previous row from new data is the current row on the data table
					previousDataRow = this.getBackupHistoricDataList().get(row);
					// Gets original values from previous row --> backup
					originalPreviousDataRow = (ItCustomer) Utilities.deepClone(previousDataRow);

					// new historic version from existing object
					if (!this.rangeDateValidation(facesContext, externalContext, previousDataRow.getStartDate(),
							previousDataRow.getEndDate(), injectNewData.getStartDate(), injectNewData.getEndDate())) {
						// not valid dates
						error = true;
					} else {
						// validates other date condition
						if (this.injectNewData.getStartDate()
								.isEqual(Formatter.stringToLocalDateTime(Formatter.DEFAULT_START_DATE)) && (row != 0)) {
							// new start date = minDate for a row different from first row ==> error
							messageDetail = "Error in dates - Only the first row can sets the start date to the minimum allowed date.";
							logger.info("Create customer: " + this.injectNewData.toString() + " - " + messageDetail);
							this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
									messageDetail);
							error = true;

						}
						if (injectNewData.getEndDate()
								.isEqual(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE))
								&& row != (totalRows - 1)) {
							// new end date = maxDate for a row different from last row ==> error
							messageDetail = "Error in dates - Only the last row can sets the end date to the maximum allowed date.";
							logger.info("Create customer: " + this.injectNewData.toString() + " - " + messageDetail);
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

								if (previousDataRow.getEndDate().isEqual(injectNewData.getStartDate().minusDays(1))
										&& injectNewData.getEndDate()
												.isEqual(subsequentDataRow.getStartDate().minusDays(1))) {
									// exceptional case: there is a gap between the records and the new record comes
									// to cover it
									// ==> inserts only the new record
									// ...currentSD ............ currentED.....subsequentSD ........... subsequentED
									// ...v .................... v.............v ...................... v
									// ...[-----currentValue----]..............[-----subsequentValue----]
									// ..........................[--newValue--]
									// ..........................^ .......... ^
									// .........................newSD ....... newED

									customerEJB.insertNewHistoricDataRecord(injectNewData);

									coverGap = true;
								}
							}

							if (!coverGap) {
								// normal case: the new record and the exist record are consecutives or overlaps

								if ((injectNewData.getEndDate().isEqual(previousDataRow.getStartDate()))
										|| (injectNewData.getEndDate().isBefore(previousDataRow.getStartDate()))) {
									// .............currentSD ............ currentED
									// .............v ................... v
									// .............[-----currentValue----]
									// [--newValue--]]
									// ^ ......... ^^
									// newSD ..... newED
									if (row == 0) {
										// first row ==> can be a record before the first record

										if (previousDataRow.getStartDate()
												.isEqual(injectNewData.getEndDate().plusDays(1))) {
											// the current record not to be modify ==> inserts only the new record <p>
											// ..............currentSD ............ currentED <p>
											// ..............v ................... v <p>
											// ..............[-----currentValue----] <p>
											// [--newValue--] <p>
											// ^ .......... ^ <p>
											// newSD ..... newED <p>

											customerEJB.insertNewHistoricDataRecord(injectNewData);
										} else {
											if (injectNewData.getEndDate().isEqual(previousDataRow.getStartDate())) {
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
												customerEJB.insertNewHistoricDataRecord(injectNewData);

												// set the modified values
												previousDataRow.setModifUser(this.loggedUser.getUserCode());
												previousDataRow.setModifDate(LocalDateTime.now());
												// set second section of the record with the original value (except
												// dates)
												previousDataRow.setStartDate(injectNewData.getEndDate().plusDays(1));

												customerEJB.updateHistoricDataRecord(previousDataRow);

												// delete the original record
												customerEJB.deleteData(originalPreviousDataRow);
											} else {
												// no consecutive records ==> error
												messageDetail = "Error in dates - The new and current dates are not consecutives.";
												logger.info("Create customer: " + this.injectNewData.toString() + " - "
														+ messageDetail);
												this.createMessage(facesContext, externalContext,
														FacesMessage.SEVERITY_INFO, message, messageDetail);
												error = true;
											}
										}

									} else {
										// not the first row ==> not allowed
										messageDetail = "Error in dates - The new end date can not be less than current start date.";
										logger.info("Create customer: " + this.injectNewData.toString() + " - "
												+ messageDetail);
										this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO,
												message, messageDetail);
										error = true;
									}
								} else {
									if (injectNewData.getStartDate().isEqual(previousDataRow.getStartDate())) {
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

										// split the record --> set first section of the record with the new value
										previousDataRow = (ItCustomer) Utilities.deepClone(injectNewData);
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
										previousDataRow.setStartDate(injectNewData.getEndDate().plusDays(1));
										customerEJB.updateHistoricDataRecord(previousDataRow);

										// delete the original record
										customerEJB.deleteData(originalPreviousDataRow);

									} else {
										if (injectNewData.getEndDate().isEqual(previousDataRow.getEndDate())) {
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

											// set the modified values
											previousDataRow.setModifUser(this.loggedUser.getUserCode());
											previousDataRow.setModifDate(LocalDateTime.now());
											// set the new endDate
											previousDataRow.setEndDate(injectNewData.getStartDate().minusDays(1));
											customerEJB.updateHistoricDataRecord(previousDataRow);

											// split the record --> set second section of the record with the new value
											previousDataRow = (ItCustomer) Utilities.deepClone(injectNewData);
											previousDataRow.setInputDate(LocalDateTime.now());
											previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
											customerEJB.updateHistoricDataRecord(previousDataRow);

											// delete the original record
											customerEJB.deleteData(originalPreviousDataRow);

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

											// split the record --> set first section of the record with the original
											// value
											// (except dates)
											// set the modified values
											previousDataRow.setModifUser(this.loggedUser.getUserCode());
											previousDataRow.setModifDate(LocalDateTime.now());
											// set the new endDate
											previousDataRow.setEndDate(injectNewData.getStartDate().minusDays(1));
											customerEJB.updateHistoricDataRecord(previousDataRow);

											// split the record --> set second section of the record with the new value
											previousDataRow = (ItCustomer) Utilities.deepClone(injectNewData);
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
											previousDataRow.setStartDate(injectNewData.getEndDate().plusDays(1));
											previousDataRow.setEndDate(originalPreviousDataRow.getEndDate());
											customerEJB.updateHistoricDataRecord(previousDataRow);

											// delete the original record
											customerEJB.deleteData(originalPreviousDataRow);
										}
									}

								}
							}

						}
					}
				} else {
					// create a new object
					customerEJB.insertData(injectNewData);

					ItCustomer object = customerEJB.findDataBySearchDateAndCustomerId(this.injectNewData.getStartDate(),
							this.injectNewData.getCustomerId());

					messageDetail = "Data saves succesfully";
					logger.info("Create customer: " + this.injectNewData.toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

					// Show the data for the new object
					this.setShowSelectedData(true);
					this.setSelectedData(object);
					loadHistoricalDataList();

					messageDetail = "Shown data for customer: ";
					logger.info(message + " - " + messageDetail + this.injectNewData.toString());
					createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail + this.injectNewData.getCustomerId().toString());

					this.resetFilterDataTable();
					this.resetFilterHistoricDataTable();
					PrimeFaces.current().executeScript("PF('multipleAccordionPanelWidget').selectAll();");

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
				if (this.isFromAddingRow()) {
					this.resetFilterHistoricDataTable();
					this.loadHistoricalDataList();
					Ajax.update(HISTORIC_DATA_TABLE_ID);
					this.setFromAddingRow(false);					

				} else {
					this.resetFilterDataTable();
					this.loadDataList();
					Ajax.update(DATA_TABLE_ID);
				}
				PrimeFaces.current().executeScript("PF('createNewDialogWidget').hide();");
				

			}
		}

	}

	@Override
	public void pushCancelButtonCreateNewDialog() {
		if (this.isFromAddingRow()) {
			this.resetFilterHistoricDataTable();
			this.setFromAddingRow(false);
			this.loadHistoricalDataList();
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
			if (this.injectSelectedHistoricData != null) {

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

					// Update the previous record with the new endDate
					customerEJB.updateHistoricDataRecord(previousRecord);
					// delete the original previous record
					customerEJB.deleteData(previousRecordOriginalData);

					logger.info("Update the previous record end date sucessfully");
				}

				// delete the selected data
				customerEJB.deleteData(this.injectSelectedHistoricData);

				messageDetail = "Data deletes succesfully";
				logger.info("Delete customer: " + this.injectSelectedHistoricData.toString() + " - " + messageDetail);
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
				this.resetFilterHistoricDataTable();
				this.loadHistoricalDataList();

				if (lastPos == 0) {
					// if it was only one row, reset the selected data
					this.setSelectedData(null);
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
			
			if (!this.validateNewInstance()) {
				validation=false;
			}
			
			if (!this.validateNewPersonal()) {
				validation=false;
			}
			
			if (!this.validateNewAddress()){
				validation=false;
			}
			
			if (!this.validateNewContact()) {
				validation=false;
			}

			if (!this.validateNewBankAccount()) {
				validation=false;
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
		if (this.getSelectedData() != null) {
			this.resetFilterHistoricDataTable();
			this.loadHistoricalDataList();
			Ajax.update(HISTORIC_DATA_TABLE_ID);
		}
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

		this.injectSelectedHistoricData = data;

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

		try {
			if (this.injectSelectedHistoricData != null) {

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
					logger.info("Change status customer: " + this.injectSelectedHistoricData.toString() + " - "
							+ messageDetail);
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
		this.setDeleteMessageDialog("Customer " + this.injectSelectedHistoricData.getGivenName() + " "
				+ this.injectSelectedHistoricData.getFirstSurname() + " "
				+ this.injectSelectedHistoricData.getSecondSurname()
				+ " - Do you really want to delete historic period ["
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getEndDate()) + "]? ");
	}

	@Override
	public void changeStatusMessage() {
		this.setCancelMessageDialog("Customer " + this.injectSelectedHistoricData.getGivenName() + " "
				+ this.injectSelectedHistoricData.getFirstSurname() + " "
				+ this.injectSelectedHistoricData.getSecondSurname()
				+ " - Do you really want to set the status to cancel from historic period ["
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getEndDate()) + "] onwards?");
	}

	@Override
	public void changeSearchDate(ValueChangeEvent e) {
		LocalDateTime newSearchDate = (LocalDateTime) e.getNewValue();
		String message, messageDetail;

		message = "CHANGE SEARCH DATE";

		if (newSearchDate != null) {
			this.setSearchDate(newSearchDate);
		} else {
			messageDetail = "ERROR - The search date can not be null";
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}
	}

	/**
	 * Validates the instance tab of the wizard
	 */
	public boolean validateNewInstance() {
		String message = "INSTANCE DATA VALIDATION";
		String messageDetail;
		boolean validation = true;

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

		if (this.injectNewData.getCustomerTypeId() == null) {
			messageDetail = "ERROR - The customer type of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getStartDate() == null) {
			messageDetail = "ERROR - The start date of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (this.injectNewData.getStartDate().compareTo(minDate) < 0) {
			messageDetail = "ERROR - The start date of the customer can not be less than "
					+ Formatter.localDateTimeToString(minDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (this.injectNewData.getStartDate().compareTo(maxDate) > 0) {
			messageDetail = "ERROR - The start date of the customer can not be greater than "
					+ Formatter.localDateTimeToString(maxDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getEndDate() == null) {
			messageDetail = "ERROR - The end date of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (this.injectNewData.getEndDate().compareTo(minDate) < 0) {
			messageDetail = "ERROR - The end date of the customer can not be less than "
					+ Formatter.localDateTimeToString(minDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (this.injectNewData.getEndDate().compareTo(maxDate) > 0) {
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
	public boolean validateNewPersonal() {
		String message = "PERSONAL DATA VALIDATION";
		String messageDetail;
		boolean validation = true;

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (this.injectNewData.getGivenName() != null) {
			this.injectNewData.setGivenName(this.injectNewData.getGivenName().trim());
		}

		if (this.injectNewData.getFirstSurname() != null) {
			this.injectNewData.setFirstSurname(this.injectNewData.getFirstSurname().trim());
		}

		if (this.injectNewData.getSecondSurname() != null) {
			this.injectNewData.setSecondSurname(this.injectNewData.getSecondSurname().trim());
		}

		if (this.injectNewData.getIdentityCard() != null) {
			this.injectNewData.setIdentityCard(this.injectNewData.getIdentityCard().toUpperCase().trim());
		}

		if (this.injectNewData.getGivenName() == null || this.injectNewData.getGivenName().isEmpty()) {
			messageDetail = "ERROR - The given name of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getGivenName().length())
				.compareTo(CustomerController.GIVEN_NAME_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The given name of the customer (" + this.injectNewData.getGivenName().length()
					+ " characters) exceeds the limit of " + CustomerController.GIVEN_NAME_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getFirstSurname() == null || this.injectNewData.getFirstSurname().isEmpty()) {
			messageDetail = "ERROR - The first surname of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getFirstSurname().length())
				.compareTo(CustomerController.FIRST_SURNAME_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The first surname of the customer ("
					+ this.injectNewData.getFirstSurname().length() + " characters) exceeds the limit of "
					+ CustomerController.FIRST_SURNAME_LENGTH_MAX.toString() + " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getSecondSurname() != null && (!this.injectNewData.getSecondSurname().isEmpty())) {
			if (((Integer) this.injectNewData.getSecondSurname().length())
					.compareTo(CustomerController.SECOND_SURNAME_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The second surname of the customer ("
						+ this.injectNewData.getSecondSurname().length() + " characters) exceeds the limit of "
						+ CustomerController.SECOND_SURNAME_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}
		}

		if (this.injectNewData.getIdentityCardTypeId() == null) {
			messageDetail = "ERROR - The identity card type of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getIdentityCard() == null || this.injectNewData.getIdentityCard().isEmpty()) {
			messageDetail = "ERROR - The identity card of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getIdentityCard().length())
				.compareTo(CustomerController.IDENTITY_CARD_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The identity card of the customer ("
					+ this.injectNewData.getIdentityCard().length() + " characters) exceeds the limit of "
					+ CustomerController.SECOND_SURNAME_LENGTH_MAX.toString() + " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getContactPhone() != null && !this.injectNewData.getContactPhone().isEmpty()) {
			if (((Integer) this.injectNewData.getContactPhone().length())
					.compareTo(CustomerController.CONTACT_PHONE_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The contact phone of the customer ("
						+ this.injectNewData.getContactPhone().length() + " characters) exceeds the limit of "
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
	public boolean validateNewAddress() {
		String message = "ADDRESS DATA VALIDATION";
		String messageDetail;
		boolean validation = true;

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (this.injectNewData.getAddress() != null) {
			this.injectNewData.setAddress(this.injectNewData.getAddress().trim());
		}

		if (this.injectNewData.getCity() != null) {
			this.injectNewData.setCity(this.injectNewData.getCity().trim());
		}

		if (this.injectNewData.getState() != null) {
			this.injectNewData.setState(this.injectNewData.getState().trim());
		}

		if (this.injectNewData.getCountry() != null) {
			this.injectNewData.setCountry(this.injectNewData.getCountry().trim());
		}

		if (this.injectNewData.getPostCode() != null) {
			this.injectNewData.setPostCode(this.injectNewData.getPostCode().trim());
		}

		if (this.injectNewData.getAddress() == null || this.injectNewData.getAddress().isEmpty()) {
			messageDetail = "ERROR - The address of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getAddress().length())
				.compareTo(CustomerController.ADDRESS_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The address of the customer (" + this.injectNewData.getAddress().length()
					+ " characters) exceeds the limit of " + CustomerController.ADDRESS_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getCity() == null || this.injectNewData.getCity().isEmpty()) {
			messageDetail = "ERROR - The city of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getCity().length())
				.compareTo(CustomerController.CITY_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The city of the customer (" + this.injectNewData.getCity().length()
					+ " characters) exceeds the limit of " + CustomerController.CITY_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getState() == null || this.injectNewData.getState().isEmpty()) {
			messageDetail = "ERROR - The state of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getState().length())
				.compareTo(CustomerController.STATE_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The state of the customer (" + this.injectNewData.getState().length()
					+ " characters) exceeds the limit of " + CustomerController.STATE_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getCountry() == null || this.injectNewData.getCountry().isEmpty()) {
			messageDetail = "ERROR - The country of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getCountry().length())
				.compareTo(CustomerController.COUNTRY_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The country of the customer (" + this.injectNewData.getCountry().length()
					+ " characters) exceeds the limit of " + CustomerController.COUNTRY_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (this.injectNewData.getPostCode() == null || this.injectNewData.getPostCode().isEmpty()) {
			messageDetail = "ERROR - The post code of the customer can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) this.injectNewData.getPostCode().length())
				.compareTo(CustomerController.POST_CODE_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The post code of the customer (" + this.injectNewData.getPostCode().length()
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
	public boolean validateNewContact() {
		String message = "CONTACT DATA VALIDATION";
		String messageDetail;
		boolean validation = true;

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (this.injectNewData.getContactPhone() != null) {
			this.injectNewData.setContactPhone(this.injectNewData.getContactPhone().trim());
		}

		if (this.injectNewData.getEMail() != null) {
			this.injectNewData.setEMail(this.injectNewData.getEMail().trim());
		}
		if (this.injectNewData.getContactPhone() != null && !this.injectNewData.getContactPhone().isEmpty()) {
			if (((Integer) this.injectNewData.getContactPhone().length())
					.compareTo(CustomerController.CONTACT_PHONE_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The contact phone of the customer ("
						+ this.injectNewData.getContactPhone().length() + " characters) exceeds the limit of "
						+ CustomerController.CONTACT_PHONE_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else {
				if (!Utilities.phoneNumberValidation(this.injectNewData.getContactPhone())) {
					messageDetail = "Error - Contact phone wrong format";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					validation = false;
				}
			}
		}

		if (this.injectNewData.getEMail() != null && !this.injectNewData.getEMail().isEmpty()) {
			if (((Integer) this.injectNewData.getEMail().length())
					.compareTo(CustomerController.E_MAIL_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The e-mail of the customer (" + this.injectNewData.getEMail().length()
						+ " characters) exceeds the limit of " + CustomerController.E_MAIL_LENGTH_MAX.toString()
						+ " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else {
				if (!Utilities.emailValidation(this.injectNewData.getEMail())) {
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
	public boolean validateNewBankAccount() {
		String message = "BANK ACCOUNT DATA VALIDATION";
		String messageDetail;
		boolean validation = true;
		boolean filledBankData = true;

		if (this.isFromAddingRow()) {
			message = "NEW CUSTOMER ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY CUSTOMER " + message;
			} else {
				message = "NEW CUSTOMER " + message;
			}
		}

		if (this.injectNewData.getIban() != null) {
			this.injectNewData.setIban(this.injectNewData.getIban().toUpperCase().trim());
		}

		if ((this.injectNewData.getIban() == null || this.injectNewData.getIban().isEmpty())
				&& (this.injectNewData.getBankEntity() == null || this.injectNewData.getBankEntity().isEmpty())
				&& (this.injectNewData.getBankControlDigit() == null
						|| this.injectNewData.getBankControlDigit().isEmpty())
				&& (this.injectNewData.getBankAccountNumber() == null
						|| this.injectNewData.getBankAccountNumber().isEmpty())) {
			// no one of data bank field was filled
			filledBankData = false;
		} else {
			if (this.injectNewData.getIban() != null && (! this.injectNewData.getIban().isEmpty()) 
					&& this.injectNewData.getBankEntity() != null && (! this.injectNewData.getBankEntity().isEmpty())
					&& this.injectNewData.getBankBranch() != null && (! this.injectNewData.getBankBranch().isEmpty())
					&& this.injectNewData.getBankControlDigit() != null && (! this.injectNewData.getBankControlDigit().isEmpty())
					&& this.injectNewData.getBankAccountNumber() != null && (! this.injectNewData.getBankAccountNumber().isEmpty())) {
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
			if (((Integer) this.injectNewData.getIban().length()).compareTo(CustomerController.IBAN_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The IBAN of the customer (" + this.injectNewData.getIban().length()
						+ " characters) must be " + CustomerController.IBAN_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) this.injectNewData.getBankEntity().length())
					.compareTo(CustomerController.BANK_ENTITY_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank entity of the customer ("
						+ this.injectNewData.getBankEntity().length() + " characters) must be "
						+ CustomerController.BANK_ENTITY_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) this.injectNewData.getBankBranch().length())
					.compareTo(CustomerController.BANK_BRANCH_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank branch of the customer ("
						+ this.injectNewData.getBankBranch().length() + " characters) must be "
						+ CustomerController.BANK_BRANCH_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) this.injectNewData.getBankControlDigit().length())
					.compareTo(CustomerController.BANK_CONTROL_DIGIT_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank control digit of the customer ("
						+ this.injectNewData.getBankControlDigit().length() + " characters) must be "
						+ CustomerController.BANK_CONTROL_DIGIT_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) this.injectNewData.getBankAccountNumber().length())
					.compareTo(CustomerController.BANK_ACCOUNT_NUMBER_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank account number of the customer ("
						+ this.injectNewData.getBankAccountNumber().length() + " characters) must be "
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

			validate = this.validateNewInstance();

			break;
		case "personalDataTab":
			if (!stepToGo.equals("instanceDataTab")) {
				validate = this.validateNewPersonal();
			}
			break;
		case "addressTab":
			if (!stepToGo.equals("personalDataTab")) {
				validate = this.validateNewAddress();
			}
			break;
		case "contactTab":
			if (!stepToGo.equals("addressTab")) {
				validate = this.validateNewContact();
			}
			break;

		case "bankAccountTab":
			if (!stepToGo.equals("contactTab")) {
				validate = this.validateNewBankAccount();
			}
			break;
		}

		if (validate) {
			return stepToGo;
		} else {
			return currentStep;
		}
	}

	@Override
	public boolean splitRecords(DataTable dataTable, int row, Object newRow) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changeSearchDataTableTitle() {
		// TODO Auto-generated method stub
		
	}

}
