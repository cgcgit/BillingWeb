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
import java.util.List;
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

import com.comasw.ejb.instance.AccountEJBLocal;
import com.comasw.ejb.instance.ContractEJBLocal;
import com.comasw.ejb.instance.CustomerEJBLocal;
import com.comasw.generalClass.BasicInstance;
import com.comasw.interfaces.IInstanceTable;
import com.comasw.model.tables.pojos.ItContract;
import com.comasw.model.tables.pojos.ItAccount;
import com.comasw.model.tables.pojos.ItCustomer;
import com.comasw.model.tables.pojos.VwAccountInstance;
import com.comasw.model.tables.pojos.VwCustomerInstance;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.utilities.Formatter;
import com.comasw.utilities.Utilities;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class AccountController extends BasicInstance<VwAccountInstance, ItAccount, VwCustomerInstance>
		implements Serializable, IInstanceTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2039286101048665711L;

	Logger logger = (Logger) LogManager.getLogger(AccountController.class);

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

	private static Integer CODE_FIELD_LENGTH_MAX = Integer.valueOf(dbDefinitions.getString("CODE_FIELD_LENGTH_MAX"));

	private static Integer DEBIT_ACCOUNT_TYPE_ID = Integer.valueOf(dbDefinitions.getString("DEBIT_ACCOUNT_TYPE"));

	private static String CUSTOMER_DATA_TABLE_ID = "form:createNewTab:customerSearchDataTable";

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private AccountEJBLocal accountEJB;

	@EJB
	private CustomerEJBLocal customerEJB;

	@EJB
	private ContractEJBLocal contractEJB;

	// ---- SEARCH CRITERIA ----\\

	private Integer searchAccountId;

	private String searchAccountIdentityCard;

	private String searchContractNr;

	private Integer searchCustomerId;

	private String searchCustomerIdentityCard;

	/**
	 * Search criteria for the new form
	 */

	private Integer searchCustomerIdForNew;

	private String searchCustomerIdentityCardForNew;

	/**
	 * For new data - if is true --> copy the personal, address and bank data from
	 * parente customer
	 */
	private boolean copyDataFromCustomerFlag;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the searchAccountId
	 */
	public Integer getSearchAccountId() {
		return searchAccountId;
	}

	/**
	 * @param searchAccountId the searchAccountId to set
	 */
	public void setSearchAccountId(Integer searchAccountId) {
		this.searchAccountId = searchAccountId;
	}

	/**
	 * @return the searchAccountIdentityCard
	 */
	public String getSearchAccountIdentityCard() {
		return searchAccountIdentityCard;
	}

	/**
	 * @param searchAccountIdentityCard the searchAccountIdentityCard to set
	 */
	public void setSearchAccountIdentityCard(String searchAccountIdentityCard) {
		this.searchAccountIdentityCard = searchAccountIdentityCard;
	}

	/**
	 * @return the searchContractNr
	 */
	public String getSearchContractNr() {
		return searchContractNr;
	}

	/**
	 * @param searchContractNr the searchContractNr to set
	 */
	public void setSearchContractNr(String searchContractNr) {
		this.searchContractNr = searchContractNr;
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
	 * @return the searchCustomerIdentityCard
	 */
	public String getSearchCustomerIdentityCard() {
		return searchCustomerIdentityCard;
	}

	/**
	 * @param searchCustomerIdentityCard the searchCustomerIdentityCard to set
	 */
	public void setSearchCustomerIdentityCard(String searchCustomerIdentityCard) {
		this.searchCustomerIdentityCard = searchCustomerIdentityCard;
	}

	/**
	 * @return the searchCustomerIdForNew
	 */
	public Integer getSearchCustomerIdForNew() {
		return searchCustomerIdForNew;
	}

	/**
	 * @param searchCustomerIdForNew the searchCustomerIdForNew to set
	 */
	public void setSearchCustomerIdForNew(Integer searchCustomerIdForNew) {
		this.searchCustomerIdForNew = searchCustomerIdForNew;
	}

	/**
	 * @return the searchCustomerIdentityCardForNew
	 */
	public String getSearchCustomerIdentityCardForNew() {
		return searchCustomerIdentityCardForNew;
	}

	/**
	 * @param searchCustomerIdentityCardForNew the searchCustomerIdentityCardForNew
	 *                                         to set
	 */
	public void setSearchCustomerIdentityCardForNew(String searchCustomerIdentityCardForNew) {
		this.searchCustomerIdentityCardForNew = searchCustomerIdentityCardForNew;
	}

	/**
	 * @return the copyDataFromCustomerFlag
	 */
	public boolean isCopyDataFromCustomerFlag() {
		return copyDataFromCustomerFlag;
	}

	/**
	 * @param copyDataFromCustomerFlag the copyDataFromCustomerFlag to set
	 */
	public void setCopyDataFromCustomerFlag(boolean copyDataFromCustomerFlag) {
		this.copyDataFromCustomerFlag = copyDataFromCustomerFlag;
	}

	/**
	 * @return the createNewContractFlag
	 */
	/*
	 * public boolean isCreateNewContractFlag() { return createNewContractFlag; } /*
	 * 
	 * /**
	 * 
	 * @param createNewContractFlag the createNewContractFlag to set
	 */
	/*
	 * public void setCreateNewContractFlag(boolean createNewContractFlag) {
	 * this.createNewContractFlag = createNewContractFlag; }
	 */

	// -------------------
	// METHODS
	// -------------------

	/**
	 * 
	 */
	public AccountController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getNewData() == null) {
			this.setNewData(new ItAccount());
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<VwAccountInstance>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<VwAccountInstance>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new VwAccountInstance());
		}

		if (this.getSelectedDataList() == null) {
			this.setSelectedDataList(new ArrayList<VwAccountInstance>());
		}

		if (this.getFilteredSelectedDataList() == null) {
			this.setFilteredSelectedDataList(new ArrayList<VwAccountInstance>());
		}

		if (this.getHistoricDataList() == null) {
			this.setHistoricDataList(new ArrayList<ItAccount>());
		}

		if (this.getFilteredHistoricDataList() == null) {
			this.setFilteredHistoricDataList(new ArrayList<ItAccount>());
		}

		if (this.getBackupHistoricDataList() == null) {
			this.setBackupHistoricDataList(new ArrayList<ItAccount>());
		}

		if (this.getSelectedHistoricData() == null) {
			this.setSelectedHistoricData(new ItAccount());
		}

		if (this.getParentSearchDataList() == null) {
			this.setParentSearchDataList(new ArrayList<VwCustomerInstance>());

		}

		if (this.getFilteredParentSearchDataList() == null) {
			this.setFilteredParentSearchDataList(new ArrayList<VwCustomerInstance>());
		}

		if (this.getSelectedParentSearchData() == null) {
			this.setSelectedParentSearchData(new VwCustomerInstance());
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
		}

	}

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		ItAccount dataObject;
		String message, message_detail;

		message = "EDIT ROW";

		// Gets the object to modifyItAccount
		dataObject = (ItAccount) event.getObject();

		// If we are editing a row, we must disabled all the other buttons
		this.setEditingMode(true);

		try {
			// The user was pushed the edit button
			message_detail = "Editing account: " + dataObject.toString();
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
		ItAccount dataObject;
		String message, messageDetail;
		boolean validation = false;
		ItAccount originalDataObject;
		ItAccount otherRecordDataObject;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (ItAccount) event.getObject();

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
									otherRecordDataObject = (ItAccount) dataTable.getRowData();
									dataTable.setRowIndex(pos);

									accountEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos - 1);
									accountEJB.deleteData(otherRecordDataObject);

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
									otherRecordDataObject = (ItAccount) dataTable.getRowData();
									dataTable.setRowIndex(pos);
									accountEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos + 1);
									accountEJB.deleteData(otherRecordDataObject);

								}
							}
						}
					}
				}

				// delete the original data of the record
				accountEJB.deleteData(originalDataObject);
				// update the current data record
				accountEJB.updateHistoricDataRecord(dataObject);

				// Check if the status was changed to cancel or if activeDateChanged --> if so,
				// we must update other rows
				if (this.isToCancel() || this.isActiveDateChanged() || this.isCancelledDateChanged()) {
					int i;
					if (this.isActiveDateChanged() || this.isCancelledDateChanged()) {
						// update all the data table
						for (i = 0; i <= lastRow; i++) {
							dataTable.setRowIndex(i);
							dataObject = (ItAccount) dataTable.getRowData();
							// accountEJB.updateHistoricDataRecord(dataObject);
							accountEJB.updateData(dataObject);
						}
					} else { // only the subsequent rows
						for (i = pos + 1; i <= lastRow; i++) {
							dataTable.setRowIndex(i);
							dataObject = (ItAccount) dataTable.getRowData();
							// accountEJB.updateHistoricDataRecord(dataObject);
							accountEJB.updateData(dataObject);
						}
					}

					logger.info("Updated the status to cancel for the subsequent rows");

				}

				// set the row index to the current position
				dataTable.setRowIndex(pos);

				// reload the list
				this.loadHistoricalDataList();

				// Evaluates if the data was by adding or update
				messageDetail = "The changes for account " + dataObject.toString() + " has done";
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
				createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
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
		ItAccount dataObject;
		String message, messageDetail;

		message = "CANCEL UPDATE ROW";

		// Retrieved the data that was modified
		dataObject = (ItAccount) event.getObject();

		try {
			this.refreshHistoricDataTable();
			messageDetail = "The changes for account " + dataObject.toString() + " was cancelled";
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
	public void pushDeleteRowButton() {
		String message, messageDetail;

		DataTable dataTable;
		ItAccount currentData;

		message = "DELETE ROW";

		try {
			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (ItAccount) dataTable.getRowData();
			this.setSelectedHistoricData((ItAccount) Utilities.deepClone(currentData));

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
		this.setFromAddingRow(false);
		// Set the default dates and input values for the new data
		this.setNewData(new ItAccount());
		this.getNewData().setStartDate(LocalDateTime.now());
		this.getNewData().setEndDate(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE));
		this.getNewData().setInputUser(this.loggedUser.getUserCode());
		this.getNewData().setInputDate(LocalDateTime.now());
		if (!this.isFromAddingRow()) {
			this.getNewData().setStatusId(STATUS_ID_PENDING_INSTANCE);
		}

		this.setSearchCustomerIdentityCardForNew(null);
		this.setSearchCustomerIdForNew(null);

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
					id = accountEJB.insertData(this.getNewData());
					VwAccountInstance object = accountEJB
							.findInstanceViewWithParameters(Optional.ofNullable(this.getSearchDate()), Optional.empty(),
									Optional.ofNullable(id), Optional.empty(), Optional.empty(), Optional.empty(),
									Optional.empty(), Optional.empty())
							.get(0);

					messageDetail = "Data saves succesfully";
					logger.info("Create account: " + this.getNewData().getAccountId().toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

					this.setSelectedData(object);
					this.getSelectedDataList().clear();
					this.getSelectedDataList().add(object);
					// this.setInjectSelectedData(object);

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
					messageDetail = "Shown data for account: ";
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
			this.setFromAddingRow(false);
			this.refreshHistoricDataTable();
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
					ItAccount previousRecord = (ItAccount) dataTable.getRowData();
					ItAccount previousRecordOriginalData = (ItAccount) Utilities.deepClone(previousRecord);

					// Subsequent row data
					dataTable.setRowIndex(currentPos + 1);
					ItAccount subsequentRecord = (ItAccount) dataTable.getRowData();

					// Set the endDate of the previows record to the startDate minus one date of the
					// subsequent record
					previousRecord.setEndDate(subsequentRecord.getStartDate().minusDays(1));
					previousRecord.setModifDate(LocalDateTime.now());
					previousRecord.setModifUser(this.getLoggedUser().getUserCode());

					// delete the selected data
					accountEJB.deleteData(this.getSelectedHistoricData());

					// delete the original previous record (to eliminate overlaps)
					accountEJB.deleteData(previousRecordOriginalData);

					// Update the previous record with the new endDate
					accountEJB.updateHistoricDataRecord(previousRecord);

					messageDetail = "Data deletes succesfully";
					logger.info("Delete account: " + this.getSelectedHistoricData().toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

					logger.info("Update the previous record end date sucessfully");
				} else {

					// delete the selected data
					accountEJB.deleteData(this.getSelectedHistoricData());

					messageDetail = "Data deletes succesfully";
					logger.info("Delete account: " + this.getSelectedHistoricData().toString() + " - " + messageDetail);
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
		ItAccount objectToValidate;
		boolean validation = true;

		if (this.isFromAddingRow()) {
			message = "ADD NEW ACCOUNT ROW VALIDATION";
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY ACCOUNT VALIDATION";
			} else {
				message = "NEW ACCOUNT VALIDATION";
			}
		}

		objectToValidate = (ItAccount) dataObject;

		if (objectToValidate != null) {

			if (!this.validateNewInstance(objectToValidate)) {
				validation = false;
			}

			if (!this.validateNewPersonal(objectToValidate)) {
				validation = false;
			}

			if (!this.validateNewAddress(objectToValidate)) {
				validation = false;
			}

			if (!this.validateNewContact(objectToValidate)) {
				validation = false;
			}

			if (!this.validateNewBankAccount(objectToValidate)) {
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
	public void loadDataList() {
		String message = "LOAD DATA";
		String messageDetail = "";
		if (this.getSearchDate() == null) {
			messageDetail = "The date value to search is null. Please fill the search date field";
			logger.error(message + " - " + messageDetail);
			FacesContext.getCurrentInstance().validationFailed();
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, message, messageDetail));
		} else {
			this.setDataList(accountEJB.findInstanceViewWithParameters(Optional.ofNullable(this.getSearchDate()),
					Optional.ofNullable(this.getSearchContractNr()), Optional.ofNullable(this.getSearchAccountId()),
					Optional.empty(), Optional.ofNullable(this.getSearchAccountIdentityCard()),
					Optional.ofNullable(this.getSearchCustomerId()), Optional.empty(),
					Optional.ofNullable(this.getSearchCustomerIdentityCard())));
			if (this.getDataList().isEmpty()) {
				logger.info("No data to show");

			} else {
				logger.info("Load data sucessful");
			}
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
		this.setEditingMode(false);
		this.setSearchDate(LocalDate.now().atStartOfDay());
		this.setFromAddingRow(false);
		this.setToCancel(false);
		this.setActiveDateChanged(false);
		this.setCancelledDateChanged(false);
		this.setPrevStatusId(-1);
		this.setShowSelectedData(false);
		// this.setCreateNewContractFlag(true);
		this.setCopyDataFromCustomerFlag(true);

	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();

	}

	@Override
	public void loadHistoricalDataList() {
		Integer id;
		if (this.getSelectedData() == null) {
			logger.error("The account select is null");
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "LOAD HISTORIC DATA",
					"The account select is null");
		} else {
			id = this.getSelectedData().getAccountId();
			this.setHistoricDataList(accountEJB.findDataByAccountId(id));
			if (this.getHistoricDataList().isEmpty()) {
				logger.info("No historical data data to show for account id " + id);

			} else {
				logger.info("Load historical data sucessful for account id " + id);
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
	public void pushAddNewRowButton() {
		String message, messageDetail;
		DataTable dataTable;
		ItAccount currentData = null;

		message = "ADD ROW";

		try {

			// Store the current data
			Utilities.copyGenericList(this.historicDataList, this.backupHistoricDataList);

			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (ItAccount) dataTable.getRowData();
			this.setSelectedHistoricData((ItAccount) Utilities.deepClone(currentData));

			this.setNewData(new ItAccount());
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
	public void pushShowDetailRowButton() {
		String message = "SHOWN HISTORIC DATA";
		String messageDetail = "";

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(DATA_TABLE_ID);

		// Gets the selected data
		this.setSelectedData((VwAccountInstance) dataTable.getRowData());

		if (this.getSelectedData() == null || this.getSelectedData().getAccountId() == null
				|| this.getSelectedData().getAccountId() == 0) {
			messageDetail = "The account is null";
			logger.error(message + " - " + messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.setShowSelectedData(true);
			this.getSelectedDataList().clear();
			this.getSelectedDataList().add(this.getSelectedData());

			this.refreshHistoricDataTable();

			messageDetail = "Shown data for account: ";
			logger.info(message + " - " + messageDetail + this.getSelectedData().toString());
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
					messageDetail + this.getSelectedData().getCustomerId().toString());

			PrimeFaces.current().executeScript("PF('searchListWidget').hide();");
			PrimeFaces.current().executeScript("PF('multipleAccordionPanelWidget').selectAll();");

		}

	}

	@Override
	public void resetFilterHistoricDataTable() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('historicTableWidget').clearFilters()");

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
		ItAccount data = (ItAccount) dataTable.getRowData();

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
	public void revertSubsequentCancelStatus() {
		DataTable dataTable;
		int currentPos, lastPos;
		ItAccount currentObject;

		dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
		lastPos = dataTable.getRowCount() - 1;
		currentPos = this.getCurrentHistoricRow();

		if (this.isToCancel()) {

			// System.out.println("actualStatusId: " + currentUser.getStatusId());
			if (this.isFromAddingRow()) {
				// The old value was equal to the previous row
				currentObject = (ItAccount) dataTable.getRowData();
				currentObject.setStatusId(((ItAccount) this.backupHistoricDataList.get(currentPos - 1)).getStatusId());
				Ajax.updateRow(dataTable, currentPos);
			}

			for (int i = currentPos + 1; i <= lastPos; i++) {
				dataTable.setRowIndex(i);

				currentObject = (ItAccount) dataTable.getRowData();
				if (!STATUS_ID_CANC.equals(currentObject.getStatusId())) {
					// the status of the row is not cancel ==> updates the status
					// and the modif values

					currentObject.setStatusId(((ItAccount) this.backupHistoricDataList.get(i)).getStatusId());
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
						((ItAccount) dataTable.getRowData()).setStatusId(STATUS_ID_CANC);
						Ajax.updateRow(dataTable, i);

					}

					dataTable.setRowIndex(currentPos);

					messageDetail = "Subsequent status change succesfully";
					logger.info("Change status account: " + this.getSelectedHistoricData().toString() + " - "
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
						((ItAccount) dataTable.getRowData()).setCancelledDate(cancelledDate);
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
	public void changeNewDialogHeader() {
		if (this.isFromAddingRow()) {
			this.setDialogHeaderText("Add New Historic Row for Account");
		} else {
			this.setDialogHeaderText("Create new Account");
		}
	}

	@Override
	public void changeDeleteMessage() {
		this.setDeleteMessageDialog("Account " + this.getSelectedHistoricData().getGivenName() + " "
				+ this.getSelectedHistoricData().getFirstSurname() + " "
				+ this.getSelectedHistoricData().getSecondSurname()
				+ " - Do you really want to delete historic period ["
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getEndDate()) + "]? ");

	}

	@Override
	public void changeStatusMessage() {
		this.setCancelMessageDialog("Account " + this.getSelectedHistoricData().getGivenName() + " "
				+ this.getSelectedHistoricData().getFirstSurname() + " "
				+ this.getSelectedHistoricData().getSecondSurname()
				+ " - Do you really want to set the status to cancel from historic period ["
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getEndDate()) + "] onwards?");

	}

	@SuppressWarnings("finally")
	@Override
	public boolean splitRecords(DataTable dataTable, int row, Object newRow) {
		String message = "INSERT NEW DATA";
		String messageDetail = "";
		boolean error = false;
		boolean coverGap = false;
		ItAccount newObject, originalPreviousDataRow, previousDataRow, subsequentDataRow;

		try {

			newObject = (ItAccount) newRow;

			Integer totalRows = dataTable.getRowCount();

			// The previous row from new data is the current row on the data table
			previousDataRow = this.getBackupHistoricDataList().get(row);
			// Gets original values from previous row --> backup
			originalPreviousDataRow = (ItAccount) Utilities.deepClone(previousDataRow);

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
					logger.info("Create account: " + newObject.toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);
					error = true;

				}
				if (newObject.getEndDate().isEqual(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE))
						&& row != (totalRows - 1)) {
					// new end date = maxDate for a row different from last row ==> error
					messageDetail = "Error in dates - Only the last row can sets the end date to the maximum allowed date.";
					logger.info("Create account: " + newObject.toString() + " - " + messageDetail);
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

							accountEJB.insertNewHistoricDataRecord(newObject);

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

									accountEJB.insertNewHistoricDataRecord(newObject);
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
										accountEJB.insertNewHistoricDataRecord(newObject);

										// delete the original record (to eliminate overlapping)
										accountEJB.deleteData(originalPreviousDataRow);

										// set the modified values
										previousDataRow.setModifUser(this.loggedUser.getUserCode());
										previousDataRow.setModifDate(LocalDateTime.now());
										// set second section of the record with the original value (except
										// dates)
										previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));

										accountEJB.updateHistoricDataRecord(previousDataRow);

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
								logger.info("Create account: " + newObject.toString() + " - " + messageDetail);
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
								accountEJB.deleteData(originalPreviousDataRow);

								// split the record --> set first section of the record with the new value
								previousDataRow = (ItAccount) Utilities.deepClone(newObject);
								previousDataRow.setInputDate(LocalDateTime.now());
								previousDataRow.setInputUser(this.getLoggedUser().getUserCode());

								accountEJB.updateHistoricDataRecord(previousDataRow);

								// split the record --> set second section of the record with the original value
								// (except dates)
								previousDataRow = (ItAccount) Utilities.deepClone(originalPreviousDataRow);
								// set the modified values
								previousDataRow.setModifUser(this.loggedUser.getUserCode());
								previousDataRow.setModifDate(LocalDateTime.now());
								// set the new start date
								previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));
								accountEJB.updateHistoricDataRecord(previousDataRow);

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
									accountEJB.deleteData(originalPreviousDataRow);

									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new endDate
									previousDataRow.setEndDate(newObject.getStartDate().minusDays(1));
									accountEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set second section of the record with the new value
									previousDataRow = (ItAccount) Utilities.deepClone(newObject);
									previousDataRow.setInputDate(LocalDateTime.now());
									previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
									accountEJB.updateHistoricDataRecord(previousDataRow);

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
									accountEJB.deleteData(originalPreviousDataRow);

									// split the record --> set first section of the record with the original
									// value
									// (except dates)
									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new endDate
									previousDataRow.setEndDate(newObject.getStartDate().minusDays(1));
									accountEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set second section of the record with the new value
									previousDataRow = (ItAccount) Utilities.deepClone(newObject);
									previousDataRow.setInputDate(LocalDateTime.now());
									previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
									accountEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set third section of the record with the original
									// values
									// (except dates)
									previousDataRow = (ItAccount) Utilities.deepClone(originalPreviousDataRow);
									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new startDate and endDate
									previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));
									previousDataRow.setEndDate(originalPreviousDataRow.getEndDate());
									accountEJB.updateHistoricDataRecord(previousDataRow);

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
	 * Validates the related data (customer and contract)
	 */
	public boolean validateRelatedData(Object object) {
		String message = "RELATED DATA VALIDATION - CUSTOMER AND CONTRACT";
		String messageDetail;
		boolean validation = true;

		ItAccount objectToValidate = (ItAccount) object;

		if (objectToValidate.getContractNumber() == null || objectToValidate.getContractNumber().isEmpty()) {
			messageDetail = "ERROR - The contract number for the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else {
			// Validates if exists the contractNr

			List<ItContract> contractList = contractEJB.findDataByContractNumber(objectToValidate.getContractNumber());

			if (contractList == null || contractList.isEmpty()) {
				messageDetail = "ERROR - The contract number for the account not exists in the system";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			List<ItAccount> accountList = accountEJB.findDataByContractNr(objectToValidate.getContractNumber());

			if (!accountList.isEmpty() && accountList.get(0).getAccountId() != objectToValidate.getAccountId()) {
				messageDetail = "ERROR - The selected contract number was asigned to another account. Please select another contract number";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;

			}

		}

		if (objectToValidate.getCustomerId() == null || objectToValidate.getCustomerId() == 0) {
			messageDetail = "ERROR - The customer Id for the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else {
			// Validates if exists the customer

			ItCustomer customer = customerEJB.findDataBySearchDateAndCustomerId(this.getSearchDate(),
					objectToValidate.getCustomerId());

			if (customer == null || customer.getCustomerId() == 0) {
				messageDetail = "ERROR - The customer id for the account not exists in the system";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

		}

		return validation;

	}

	/**
	 * Validates the instance tab of the wizard
	 */
	public boolean validateNewInstance(Object object) {
		String message = "INSTANCE DATA VALIDATION";
		String messageDetail;
		ItCustomer parent;
		boolean validation = true;

		LocalDateTime minDate = Formatter.stringToLocalDateTime("01/01/1900");
		LocalDateTime maxDate = Formatter.stringToLocalDateTime("31/12/9999");

		ItAccount objectToValidate = (ItAccount) object;

		if (this.isFromAddingRow()) {
			message = "NEW ACCOUNT ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY ACCOUNT " + message;
			} else {
				message = "NEW ACCOUNT " + message;
			}
		}

		if (objectToValidate.getCode() != null) {
			objectToValidate.setCode(objectToValidate.getCode().toUpperCase().trim());
		}

		if (objectToValidate.getAccountTypeId() == null) {
			messageDetail = "ERROR - The account type of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getStartDate() == null) {
			messageDetail = "ERROR - The start date of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getStartDate().compareTo(minDate) < 0) {
			messageDetail = "ERROR - The start date of the account can not be less than "
					+ Formatter.localDateTimeToString(minDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getStartDate().compareTo(maxDate) > 0) {
			messageDetail = "ERROR - The start date of the account can not be greater than "
					+ Formatter.localDateTimeToString(maxDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getEndDate() == null) {
			messageDetail = "ERROR - The end date of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getEndDate().compareTo(minDate) < 0) {
			messageDetail = "ERROR - The end date of the account can not be less than "
					+ Formatter.localDateTimeToString(minDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (objectToValidate.getEndDate().compareTo(maxDate) > 0) {
			messageDetail = "ERROR - The end date of the account can not be greater than "
					+ Formatter.localDateTimeToString(maxDate);
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getCode() == null || objectToValidate.getCode().isEmpty()) {
			messageDetail = "ERROR - The code of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getCode().length())
				.compareTo(AccountController.CODE_FIELD_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The code of the account (" + objectToValidate.getGivenName().length()
					+ " characters) exceeds the limit of " + AccountController.CODE_FIELD_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		parent = customerEJB.findActiveDataBySearchDateAndCustomerId(objectToValidate.getStartDate(),
				objectToValidate.getCustomerId());
		if (parent == null) {
			messageDetail = "Error - Not exist an active record for the selected customer in these dates.";
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

		ItAccount objectToValidate = (ItAccount) object;

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
			messageDetail = "ERROR - The given name of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getGivenName().length())
				.compareTo(AccountController.GIVEN_NAME_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The given name of the account (" + objectToValidate.getGivenName().length()
					+ " characters) exceeds the limit of " + AccountController.GIVEN_NAME_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getFirstSurname() == null || objectToValidate.getFirstSurname().isEmpty()) {
			messageDetail = "ERROR - The first surname of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getFirstSurname().length())
				.compareTo(AccountController.FIRST_SURNAME_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The first surname of the account (" + objectToValidate.getFirstSurname().length()
					+ " characters) exceeds the limit of " + AccountController.FIRST_SURNAME_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getSecondSurname() != null && (!objectToValidate.getSecondSurname().isEmpty())) {
			if (((Integer) objectToValidate.getSecondSurname().length())
					.compareTo(AccountController.SECOND_SURNAME_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The second surname of the account ("
						+ objectToValidate.getSecondSurname().length() + " characters) exceeds the limit of "
						+ AccountController.SECOND_SURNAME_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}
		}

		if (objectToValidate.getIdentityCardTypeId() == null) {
			messageDetail = "ERROR - The identity card type of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getIdentityCard() == null || objectToValidate.getIdentityCard().isEmpty()) {
			messageDetail = "ERROR - The identity card of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getIdentityCard().length())
				.compareTo(AccountController.IDENTITY_CARD_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The identity card of the account (" + objectToValidate.getIdentityCard().length()
					+ " characters) exceeds the limit of " + AccountController.SECOND_SURNAME_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getContactPhone() != null && !objectToValidate.getContactPhone().isEmpty()) {
			if (((Integer) objectToValidate.getContactPhone().length())
					.compareTo(AccountController.CONTACT_PHONE_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The contact phone of the account ("
						+ objectToValidate.getContactPhone().length() + " characters) exceeds the limit of "
						+ AccountController.CONTACT_PHONE_LENGTH_MAX.toString() + " characters";
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

		ItAccount objectToValidate = (ItAccount) object;

		if (this.isFromAddingRow()) {
			message = "NEW ACCOUNT ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY ACCOUNT " + message;
			} else {
				message = "NEW ACCOUNT " + message;
			}
		}

		if (objectToValidate.getAddress() != null) {
			objectToValidate.setAddress(objectToValidate.getAddress().trim());
		}

		if (objectToValidate.getCity() != null) {
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
			messageDetail = "ERROR - The address of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getAddress().length())
				.compareTo(AccountController.ADDRESS_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The address of the account (" + objectToValidate.getAddress().length()
					+ " characters) exceeds the limit of " + AccountController.ADDRESS_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getCity() == null || objectToValidate.getCity().isEmpty()) {
			messageDetail = "ERROR - The city of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getCity().length()).compareTo(AccountController.CITY_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The city of the account (" + objectToValidate.getCity().length()
					+ " characters) exceeds the limit of " + AccountController.CITY_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getState() == null || objectToValidate.getState().isEmpty()) {
			messageDetail = "ERROR - The state of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getState().length()).compareTo(AccountController.STATE_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The state of the account (" + objectToValidate.getState().length()
					+ " characters) exceeds the limit of " + AccountController.STATE_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getCountry() == null || objectToValidate.getCountry().isEmpty()) {
			messageDetail = "ERROR - The country of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getCountry().length())
				.compareTo(AccountController.COUNTRY_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The country of the account (" + objectToValidate.getCountry().length()
					+ " characters) exceeds the limit of " + AccountController.COUNTRY_LENGTH_MAX.toString()
					+ " characters";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		}

		if (objectToValidate.getPostCode() == null || objectToValidate.getPostCode().isEmpty()) {
			messageDetail = "ERROR - The post code of the account can not be null";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			validation = false;
		} else if (((Integer) objectToValidate.getPostCode().length())
				.compareTo(AccountController.POST_CODE_LENGTH_MAX) > 0) {
			// length characters exceeds the maximum length
			messageDetail = "Error - The post code of the account (" + objectToValidate.getPostCode().length()
					+ " characters) exceeds the limit of " + AccountController.POST_CODE_LENGTH_MAX.toString()
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

		ItAccount objectToValidate = (ItAccount) object;

		if (this.isFromAddingRow()) {
			message = "NEW ACCOUNT ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY ACCOUNT " + message;
			} else {
				message = "NEW ACCOUNT " + message;
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
					.compareTo(AccountController.CONTACT_PHONE_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The contact phone of the account ("
						+ objectToValidate.getContactPhone().length() + " characters) exceeds the limit of "
						+ AccountController.CONTACT_PHONE_LENGTH_MAX.toString() + " characters";
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
			if (((Integer) objectToValidate.getEMail().length()).compareTo(AccountController.E_MAIL_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The e-mail of the account (" + objectToValidate.getEMail().length()
						+ " characters) exceeds the limit of " + AccountController.E_MAIL_LENGTH_MAX.toString()
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

		ItAccount objectToValidate = (ItAccount) object;

		if (this.isFromAddingRow()) {
			message = "NEW ACCOUNT ROW " + message;
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY ACCOUNT " + message;
			} else {
				message = "NEW ACCOUNT " + message;
			}
		}

		if (objectToValidate.getIban() != null) {
			objectToValidate.setIban(objectToValidate.getIban().toUpperCase().trim());
		}

		if ((objectToValidate.getIban() == null || objectToValidate.getIban().isEmpty())
				&& (objectToValidate.getBankEntity() == null || objectToValidate.getBankEntity().isEmpty())
				&& (objectToValidate.getBankControlDigit() == null || objectToValidate.getBankControlDigit().isEmpty())
				&& (objectToValidate.getBankAccountNumber() == null
						|| objectToValidate.getBankAccountNumber().isEmpty())) {
			// no one of data bank field was filled
			if (objectToValidate.getAccountTypeId().equals(AccountController.DEBIT_ACCOUNT_TYPE_ID)) {
				// the account is a debit account --> It must have bank account defined
				// no all of data bank field was filled (but at least one field)
				messageDetail = "Error - The account type selected is a debit account, so the bank data must be filled.";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
				filledBankData = false;// nothing to validates
			} else {
				// the account is not a debit account --> It can no have bank account
				filledBankData = false; // noting to validates
			}
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
			if (((Integer) objectToValidate.getIban().length()).compareTo(AccountController.IBAN_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The IBAN of the account (" + objectToValidate.getIban().length()
						+ " characters) must be " + AccountController.IBAN_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankEntity().length())
					.compareTo(AccountController.BANK_ENTITY_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank entity of the account (" + objectToValidate.getBankEntity().length()
						+ " characters) must be " + AccountController.BANK_ENTITY_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankBranch().length())
					.compareTo(AccountController.BANK_BRANCH_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank branch of the account (" + objectToValidate.getBankBranch().length()
						+ " characters) must be " + AccountController.BANK_BRANCH_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankControlDigit().length())
					.compareTo(AccountController.BANK_CONTROL_DIGIT_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank control digit of the account ("
						+ objectToValidate.getBankControlDigit().length() + " characters) must be "
						+ AccountController.BANK_CONTROL_DIGIT_LENGTH.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (((Integer) objectToValidate.getBankAccountNumber().length())
					.compareTo(AccountController.BANK_ACCOUNT_NUMBER_LENGTH) != 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The bank account number of the account ("
						+ objectToValidate.getBankAccountNumber().length() + " characters) must be "
						+ AccountController.BANK_ACCOUNT_NUMBER_LENGTH.toString() + " characters";
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

		case "relatedDataTab":

			validate = this.validateRelatedData(this.getNewData());

			if (validate && this.isCopyDataFromCustomerFlag()) {
				copyDataFromCustomer();
			}

			break;

		case "instanceDataTab":

			if (this.isFromAddingRow()) {
				validate = this.validateNewInstance(this.getNewData());

			} else {
				if (!stepToGo.equals("relatedDataTab")) {
					validate = this.validateNewInstance(this.getNewData());
				}
			}
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

	public void copyDataFromCustomer() {
		String message, messageDetail;

		message = "COPY DATA FROM CUSTOMER";

		if (this.isCopyDataFromCustomerFlag()) {
			if (this.getNewData().getCustomerId() != null) {
				ItCustomer customer = customerEJB.findDataBySearchDateAndCustomerId(this.getSearchDate(),
						this.getNewData().getCustomerId());

				if (customer != null && customer.getCustomerId() != null && customer.getCustomerId() != 0) {

					// Copy the data from parent customer

					// Personal Info
					this.getNewData().setGivenName(customer.getGivenName());
					this.getNewData().setFirstSurname(customer.getFirstSurname());
					this.getNewData().setSecondSurname(customer.getSecondSurname());
					this.getNewData().setIdentityCard(customer.getIdentityCard());
					this.getNewData().setIdentityCardTypeId(customer.getIdentityCardTypeId());

					// Address info
					this.getNewData().setAddress(customer.getAddress());
					this.getNewData().setCity(customer.getCity());
					this.getNewData().setState(customer.getState());
					this.getNewData().setCountry(customer.getCountry());
					this.getNewData().setPostCode(customer.getPostCode());

					// Contact info
					this.getNewData().setContactPhone(customer.getContactPhone());
					this.getNewData().setEMail(customer.getEMail());

					// Bank Info
					this.getNewData().setIban(customer.getIban());
					this.getNewData().setBankEntity(customer.getBankEntity());
					this.getNewData().setBankBranch(customer.getBankBranch());
					this.getNewData().setBankControlDigit(customer.getBankControlDigit());
					this.getNewData().setBankAccountNumber(customer.getBankAccountNumber());
				}
			}
		} else {
			messageDetail = "ERROR - Not exists a customer with the customer id: " + this.getNewData().getCustomerId()
					+ ". Please type an existing customer id";
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}

	}

	/**
	 * Action to do when change the copy from customer flag value in create account
	 * form
	 * 
	 * @param e
	 */
	public void changeCopyFromCustomer(ValueChangeEvent e) {
		boolean newcopyFromCustomerFlagValue = (boolean) e.getNewValue();
		this.setCopyDataFromCustomerFlag(newcopyFromCustomerFlagValue);
		this.copyDataFromCustomer();
	}

	/**
	 * Creates a new contract
	 */
	public void createNewContract() {
		String message, messageDetail;

		message = "NEW CONTRACT";

		// if (this.isCreateNewContractFlag()) {
		if (this.getNewData().getContractNumber() != null && !this.getNewData().getContractNumber().trim().isEmpty()) {
			messageDetail = "ERROR - There is a contract number specified. If you want create a new contract number, please delete the exists.";
			logger.error(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		} else {
			try {
				ItContract contract = new ItContract();
				contract.setInputUser(this.getLoggedUser().getUserCode());
				contract.setInputDate(LocalDate.now().atStartOfDay());
				Integer contractId = contractEJB.insertData(contract);
				contract = contractEJB.findDataByContractId(contractId).get(0);
				if (contract == null) {
					messageDetail = "ERROR - Error creating new contract.";
					logger.fatal(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
				} else {
					this.getNewData().setContractNumber(contract.getContractNumber());
				}

			} catch (EJBException e) {
				Exception ne = (Exception) e.getCause();
				if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
					messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
					logger.fatal(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message,
							messageDetail);

				} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
					messageDetail = "PARSE ERROR - " + ne.getMessage();
					logger.fatal(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message,
							messageDetail);

				} else {
					messageDetail = "ERROR - " + ne.getMessage();
					logger.fatal(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message,
							messageDetail);
				}

			} catch (Exception e) {
				messageDetail = "ERROR - " + e.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
			}
		}
		// }
	}

	/**
	 * Action to do when push the search customer button in create new account form
	 */
	@Override
	public void pushSearchParentButton() {

		String message = "SEARCH CUSTOMER";
		String messageDetail = "";

		// Gets the data form
		if ((this.getNewData().getCustomerId() == null || this.getNewData().getCustomerId() == 0)
				&& (this.getSearchCustomerIdentityCardForNew() == null
						|| this.getSearchCustomerIdentityCardForNew().isEmpty())) {
			messageDetail = "The customerId or customer Identity card is null";
			logger.error(message + " - " + messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			facesContext.validationFailed();

		} else {
			try {
				this.getParentSearchDataList().clear();
				this.setParentSearchDataList(
						customerEJB.findInstanceViewWithParameters(Optional.ofNullable(this.getSearchDate()),
								Optional.ofNullable(this.getNewData().getCustomerId()), Optional.empty(),
								Optional.empty(), Optional.ofNullable(this.getSearchCustomerIdentityCardForNew()),
								Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

				PrimeFaces current = PrimeFaces.current();
				current.executeScript("PF('customerDataTableWidget').clearFilters()");

			} catch (EJBException e) {
				Exception ne = (Exception) e.getCause();
				if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwDataAccessException")) {
					messageDetail = "DATA ACCES ERROR - " + ne.getMessage();
					logger.fatal(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message,
							messageDetail);

				} else if (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException")) {
					messageDetail = "PARSE ERROR - " + ne.getMessage();
					logger.fatal(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message,
							messageDetail);

				} else {
					messageDetail = "ERROR - " + ne.getMessage();
					logger.fatal(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message,
							messageDetail);
				}

			} catch (Exception e) {
				messageDetail = "ERROR - " + e.getMessage();
				logger.fatal(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_FATAL, message, messageDetail);
			}

		}

	}

	@Override
	public void addParentToInstanceRowButton() {

		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(CUSTOMER_DATA_TABLE_ID);

		if (this.getSelectedParentSearchData() == null) {
			this.setSelectedParentSearchData(new VwCustomerInstance());
		}

		this.setSelectedParentSearchData((VwCustomerInstance) dataTable.getRowData());

		this.getNewData().setCustomerId(this.getSelectedParentSearchData().getCustomerId());

		this.setSearchCustomerIdForNew(this.getSelectedParentSearchData().getCustomerId());
		this.setSearchCustomerIdentityCardForNew(this.getSelectedParentSearchData().getCustomerIdentityCard());

		this.copyDataFromCustomer();

		PrimeFaces.current().executeScript("PF('searchCustomerListWidget').hide();");
		PrimeFaces.current().executeScript("PF('newCustomerPanelWidget').refresh();");

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
		ItAccount data = (ItAccount) dataTable.getRowData();

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
				((ItAccount) dataTable.getRowData()).setActiveDate(newActiveDate);
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
		ItAccount data = (ItAccount) dataTable.getRowData();

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
				((ItAccount) dataTable.getRowData()).setActiveDate(newCancelledDate);
				;
				Ajax.updateRow(dataTable, i);

			}

		}
	}

}
