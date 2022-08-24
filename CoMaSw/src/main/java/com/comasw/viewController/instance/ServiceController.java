/**
 * 
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
import org.primefaces.event.RowEditEvent;

import com.comasw.ejb.instance.ProductEJBLocal;
import com.comasw.ejb.instance.ServiceEJBLocal;
import com.comasw.generalClass.BasicInstance;
import com.comasw.interfaces.IInstanceTable;
import com.comasw.model.tables.pojos.ItProduct;
import com.comasw.model.tables.pojos.ItService;
import com.comasw.model.tables.pojos.VwProductInstance;
import com.comasw.model.tables.pojos.VwServiceInstance;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.utilities.Formatter;
import com.comasw.utilities.Utilities;

@Named
@ViewScoped
/**
 * @author catuxa
 *
 */
public class ServiceController extends BasicInstance<VwServiceInstance, ItService, VwProductInstance>
		implements Serializable, IInstanceTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1324598933864474047L;

	Logger logger = (Logger) LogManager.getLogger(ServiceController.class);

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private ServiceEJBLocal serviceEJB;

	@EJB
	private ProductEJBLocal productEJB;

	// ---- SEARCH CRITERIA ----\\

	private Integer searchServiceId;

	private Integer searchServiceTypeId;
	
	private String searchServiceNr;

	private Integer searchProductId;
	
	private Integer searchProductTypeId;

	private Integer searchAccountId;

	private String searchAccountIdentityCard;

	private String searchContractNr;

	private Integer searchCustomerId;

	private String searchCustomerIdentityCard;

	

	/** lists for the parent search data for create new form **/

	/**
	 * Search criteria for the new form
	 */

	private Integer searchProductIdForNew;
	
	private Integer searchProductTypeIdForNew;	

	private String searchContractNrForNew;

	private Integer searchAccountIdForNew;

	private String searchAccountIdentityCardForNew;

	private Integer searchCustomerIdForNew;

	private String searchCustomerIdentityCardForNew;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the searchServiceId
	 */
	public Integer getSearchServiceId() {
		return searchServiceId;
	}

	/**
	 * @param searchServiceId the searchServiceId to set
	 */
	public void setSearchServiceId(Integer searchServiceId) {
		this.searchServiceId = searchServiceId;
	}
	
	
	

	/**
	 * @return the searchServiceTypeId
	 */
	public Integer getSearchServiceTypeId() {
		return searchServiceTypeId;
	}

	/**
	 * @param searchServiceTypeId the searchServiceTypeId to set
	 */
	public void setSearchServiceTypeId(Integer searchServiceTypeId) {
		this.searchServiceTypeId = searchServiceTypeId;
	}

	/**
	 * @return the searchServiceNr
	 */
	public String getSearchServiceNr() {
		return searchServiceNr;
	}

	/**
	 * @return the searchProductId
	 */
	public Integer getSearchProductId() {
		return searchProductId;
	}

	
	
	
	/**
	 * @return the searchProductTypeId
	 */
	public Integer getSearchProductTypeId() {
		return searchProductTypeId;
	}

	/**
	 * @param searchProductTypeId the searchProductTypeId to set
	 */
	public void setSearchProductTypeId(Integer searchProductTypeId) {
		this.searchProductTypeId = searchProductTypeId;
	}

	/**
	 * @param searchServiceNr the searchServiceNr to set
	 */
	public void setSearchServiceNr(String searchServiceNr) {
		this.searchServiceNr = searchServiceNr;
	}

	/**
	 * @param searchProductId the searchProductId to set
	 */
	public void setSearchProductId(Integer searchProductId) {
		this.searchProductId = searchProductId;
	}

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
	 * @return the searchProductIdForNew
	 */
	public Integer getSearchProductIdForNew() {
		return searchProductIdForNew;
	}

	/**
	 * @param searchProductIdForNew the searchProductIdForNew to set
	 */
	public void setSearchProductIdForNew(Integer searchProductIdForNew) {
		this.searchProductIdForNew = searchProductIdForNew;
	}
	
	

	/**
	 * @return the searchProductTypeIdForNew
	 */
	public Integer getSearchProductTypeIdForNew() {
		return searchProductTypeIdForNew;
	}

	/**
	 * @param searchProductTypeIdForNew the searchProductTypeIdForNew to set
	 */
	public void setSearchProductTypeIdForNew(Integer searchProductTypeIdForNew) {
		this.searchProductTypeIdForNew = searchProductTypeIdForNew;
	}

	/**
	 * @return the searchContractNrForNew
	 */
	public String getSearchContractNrForNew() {
		return searchContractNrForNew;
	}

	/**
	 * @param searchContractNrForNew the searchContractNrForNew to set
	 */
	public void setSearchContractNrForNew(String searchContractNrForNew) {
		this.searchContractNrForNew = searchContractNrForNew;
	}

	/**
	 * @return the searchAccountIdForNew
	 */
	public Integer getSearchAccountIdForNew() {
		return searchAccountIdForNew;
	}

	/**
	 * @param searchAccountIdForNew the searchAccountIdForNew to set
	 */
	public void setSearchAccountIdForNew(Integer searchAccountIdForNew) {
		this.searchAccountIdForNew = searchAccountIdForNew;
	}

	/**
	 * @return the searchAccountIdentityCardForNew
	 */
	public String getSearchAccountIdentityCardForNew() {
		return searchAccountIdentityCardForNew;
	}

	/**
	 * @param searchAccountIdentityCardForNew the searchAccountIdentityCardForNew to
	 *                                        set
	 */
	public void setSearchAccountIdentityCardForNew(String searchAccountIdentityCardForNew) {
		this.searchAccountIdentityCardForNew = searchAccountIdentityCardForNew;
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

	// -------------------
	// METHODS
	// -------------------

	/**
	 * 
	 */
	public ServiceController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getNewData() == null) {
			this.setNewData(new ItService());
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<VwServiceInstance>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<VwServiceInstance>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new VwServiceInstance());
		}

		if (this.getSelectedDataList() == null) {
			this.setSelectedDataList(new ArrayList<VwServiceInstance>());
		}

		if (this.getFilteredSelectedDataList() == null) {
			this.setFilteredSelectedDataList(new ArrayList<VwServiceInstance>());
		}

		if (this.getHistoricDataList() == null) {
			this.setHistoricDataList(new ArrayList<ItService>());
		}

		if (this.getFilteredHistoricDataList() == null) {
			this.setFilteredHistoricDataList(new ArrayList<ItService>());
		}

		if (this.getBackupHistoricDataList() == null) {
			this.setBackupHistoricDataList(new ArrayList<ItService>());
		}

		if (this.getSelectedHistoricData() == null) {
			this.setSelectedHistoricData(new ItService());
		}

		if (this.getParentSearchDataList() == null) {
			this.setParentSearchDataList(new ArrayList<VwProductInstance>());
		}

		if (this.getFilteredParentSearchDataList() == null) {
			this.setFilteredParentSearchDataList(new ArrayList<VwProductInstance>());
		}

		if (this.getSelectedParentSearchData() == null) {
			this.setSelectedParentSearchData(new VwProductInstance());
		}

		if (this.getLoggedUser() == null) {
			this.setLoggedUser((VwUsers) externalContext.getSessionMap().get("applicationUser"));
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
		ItService data = (ItService) dataTable.getRowData();

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
		ItService currentObject;

		dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
		lastPos = dataTable.getRowCount() - 1;
		currentPos = this.getCurrentHistoricRow();

		if (this.isToCancel()) {

			// System.out.println("actualStatusId: " + currentUser.getStatusId());
			if (this.isFromAddingRow()) {
				// The old value was equal to the previous row
				currentObject = (ItService) dataTable.getRowData();
				currentObject.setStatusId(((ItService) this.backupHistoricDataList.get(currentPos - 1)).getStatusId());
				Ajax.updateRow(dataTable, currentPos);
			}

			for (int i = currentPos + 1; i <= lastPos; i++) {
				dataTable.setRowIndex(i);

				currentObject = (ItService) dataTable.getRowData();
				if (!STATUS_ID_CANC.equals(currentObject.getStatusId())) {
					// the status of the row is not cancel ==> updates the status
					// and the modif values

					currentObject.setStatusId(((ItService) this.backupHistoricDataList.get(i)).getStatusId());
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
						((ItService) dataTable.getRowData()).setStatusId(STATUS_ID_CANC);
						Ajax.updateRow(dataTable, i);

					}

					dataTable.setRowIndex(currentPos);

					messageDetail = "Subsequent status change succesfully";
					logger.info("Change status service: " + this.getSelectedHistoricData().toString() + " - "
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
						((ItService) dataTable.getRowData()).setCancelledDate(cancelledDate);
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
			this.setDialogHeaderText("Add New Historic Row for Service");
		} else {
			this.setDialogHeaderText("Create new Service");
		}

	}

	@Override
	public void changeDeleteMessage() {
		this.setDeleteMessageDialog("Service " + this.getSelectedHistoricData().getCode()
				+ " - Do you really want to delete historic period ["
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.getSelectedHistoricData().getEndDate()) + "]? ");

	}

	@Override
	public void changeStatusMessage() {
		this.setCancelMessageDialog("Service " + this.getSelectedHistoricData().getCode()
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
		ItService newObject, originalPreviousDataRow, previousDataRow, subsequentDataRow;

		try {

			newObject = (ItService) newRow;

			Integer totalRows = dataTable.getRowCount();

			// The previous row from new data is the current row on the data table
			previousDataRow = this.getBackupHistoricDataList().get(row);
			// Gets original values from previous row --> backup
			originalPreviousDataRow = (ItService) Utilities.deepClone(previousDataRow);

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
					logger.info("Create service: " + newObject.toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);
					error = true;

				}
				if (newObject.getEndDate().isEqual(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE))
						&& row != (totalRows - 1)) {
					// new end date = maxDate for a row different from last row ==> error
					messageDetail = "Error in dates - Only the last row can sets the end date to the maximum allowed date.";
					logger.info("Create service: " + newObject.toString() + " - " + messageDetail);
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

							serviceEJB.insertNewHistoricDataRecord(newObject);

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

									serviceEJB.insertNewHistoricDataRecord(newObject);
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
										serviceEJB.insertNewHistoricDataRecord(newObject);

										// delete the original record (to eliminate overlapping)
										serviceEJB.deleteData(originalPreviousDataRow);

										// set the modified values
										previousDataRow.setModifUser(this.loggedUser.getUserCode());
										previousDataRow.setModifDate(LocalDateTime.now());
										// set second section of the record with the original value (except
										// dates)
										previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));

										serviceEJB.updateHistoricDataRecord(previousDataRow);

									} else {
										// no consecutive records ==> error
										messageDetail = "Error in dates - The new and current dates are not consecutives.";
										logger.info("Create service: " + newObject.toString() + " - " + messageDetail);
										this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO,
												message, messageDetail);
										error = true;
									}
								}

							} else {
								// not the first row ==> not allowed
								messageDetail = "Error in dates - The new end date can not be less than current start date.";
								logger.info("Create service: " + newObject.toString() + " - " + messageDetail);
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
								serviceEJB.deleteData(originalPreviousDataRow);

								// split the record --> set first section of the record with the new value
								previousDataRow = (ItService) Utilities.deepClone(newObject);
								previousDataRow.setInputDate(LocalDateTime.now());
								previousDataRow.setInputUser(this.getLoggedUser().getUserCode());

								serviceEJB.updateHistoricDataRecord(previousDataRow);

								// split the record --> set second section of the record with the original value
								// (except dates)
								previousDataRow = (ItService) Utilities.deepClone(originalPreviousDataRow);
								// set the modified values
								previousDataRow.setModifUser(this.loggedUser.getUserCode());
								previousDataRow.setModifDate(LocalDateTime.now());
								// set the new start date
								previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));
								serviceEJB.updateHistoricDataRecord(previousDataRow);

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
									serviceEJB.deleteData(originalPreviousDataRow);

									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new endDate
									previousDataRow.setEndDate(newObject.getStartDate().minusDays(1));
									serviceEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set second section of the record with the new value
									previousDataRow = (ItService) Utilities.deepClone(newObject);
									previousDataRow.setInputDate(LocalDateTime.now());
									previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
									serviceEJB.updateHistoricDataRecord(previousDataRow);

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
									serviceEJB.deleteData(originalPreviousDataRow);

									// split the record --> set first section of the record with the original
									// value
									// (except dates)
									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new endDate
									previousDataRow.setEndDate(newObject.getStartDate().minusDays(1));
									serviceEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set second section of the record with the new value
									previousDataRow = (ItService) Utilities.deepClone(newObject);
									previousDataRow.setInputDate(LocalDateTime.now());
									previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
									serviceEJB.updateHistoricDataRecord(previousDataRow);

									// split the record --> set third section of the record with the original
									// values
									// (except dates)
									previousDataRow = (ItService) Utilities.deepClone(originalPreviousDataRow);
									// set the modified values
									previousDataRow.setModifUser(this.loggedUser.getUserCode());
									previousDataRow.setModifDate(LocalDateTime.now());
									// set the new startDate and endDate
									previousDataRow.setStartDate(newObject.getEndDate().plusDays(1));
									previousDataRow.setEndDate(originalPreviousDataRow.getEndDate());
									serviceEJB.updateHistoricDataRecord(previousDataRow);

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

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		ItService dataObject;
		String message, message_detail;

		message = "EDIT ROW";

		// Gets the object to modifyItAccount
		dataObject = (ItService) event.getObject();

		// If we are editing a row, we must disabled all the other buttons
		this.setEditingMode(true);

		try {
			// The user was pushed the edit button
			message_detail = "Editing service: " + dataObject.toString();
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
		ItService dataObject;
		String message, messageDetail;
		boolean validation = false;
		ItService originalDataObject;
		ItService otherRecordDataObject;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (ItService) event.getObject();

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
									otherRecordDataObject = (ItService) dataTable.getRowData();
									dataTable.setRowIndex(pos);

									serviceEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos - 1);
									serviceEJB.deleteData(otherRecordDataObject);
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
									otherRecordDataObject = (ItService) dataTable.getRowData();
									dataTable.setRowIndex(pos);
									serviceEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos + 1);
									serviceEJB.deleteData(otherRecordDataObject);
								}
							}
						}
					}
				}

				// delete the original data of the record
				serviceEJB.deleteData(originalDataObject);
				// update the current data record
				serviceEJB.updateHistoricDataRecord(dataObject);

				// Check if the status was changed to cancel or if activeDateChanged --> if so,
				// we must update other rows
				if (this.isToCancel() || this.isActiveDateChanged() || this.isCancelledDateChanged()) {
					int i;
					if (this.isActiveDateChanged() || this.isCancelledDateChanged()) {
						// update all the data table
						for (i = 0; i <= lastRow; i++) {
							dataTable.setRowIndex(i);
							dataObject = (ItService) dataTable.getRowData();
							// accountEJB.updateHistoricDataRecord(dataObject);
							serviceEJB.updateData(dataObject);
						}
					} else { // only the subsequent rows
						for (i = pos + 1; i <= lastRow; i++) {
							dataTable.setRowIndex(i);
							dataObject = (ItService) dataTable.getRowData();
							// accountEJB.updateHistoricDataRecord(dataObject);
							serviceEJB.updateData(dataObject);
						}
					}

					logger.info("Updated the status to cancel for the subsequent rows");

				}

				// set the row index to the current position
				dataTable.setRowIndex(pos);

				// reload the list
				this.loadHistoricalDataList();

				// Evaluates if the data was by adding or update
				messageDetail = "The changes for service " + dataObject.toString() + " has done";
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
		ItService dataObject;
		String message, messageDetail;

		message = "CANCEL UPDATE ROW";

		// Retrieved the data that was modified
		dataObject = (ItService) event.getObject();

		try {
			this.refreshHistoricDataTable();
			messageDetail = "The changes for service " + dataObject.toString() + " was cancelled";
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
		ItService currentData;

		message = "DELETE ROW";

		try {
			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (ItService) dataTable.getRowData();
			this.setSelectedHistoricData((ItService) Utilities.deepClone(currentData));

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
		this.setNewData(new ItService());
		this.getNewData().setStartDate(LocalDateTime.now());
		this.getNewData().setEndDate(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE));
		this.getNewData().setInputUser(this.loggedUser.getUserCode());
		this.getNewData().setInputDate(LocalDateTime.now());
		if (!this.isFromAddingRow()) {
			this.getNewData().setStatusId(STATUS_ID_PENDING_INSTANCE);
		}
		
		this.setSearchProductIdForNew(null);
		this.setSearchProductTypeIdForNew(null);

		this.setSearchContractNrForNew(null);

		this.setSearchAccountIdForNew(null);

		this.setSearchAccountIdentityCardForNew(null);

		this.setSearchCustomerIdForNew(null);

		this.setSearchCustomerIdentityCardForNew(null);

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
					id = serviceEJB.insertData(this.getNewData());
					VwServiceInstance object = serviceEJB.findInstanceViewWithParameters(
							Optional.ofNullable(this.getSearchDate()), true, Optional.ofNullable(id), Optional.empty(),  Optional.empty(),
							Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
							Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()).get(0);

					messageDetail = "Data saves succesfully";
					logger.info("Create service: " + this.getNewData().toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

					this.setSelectedData(object);
					this.getSelectedDataList().clear();
					this.getSelectedDataList().add(object);
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
					messageDetail = "Shown data for product: ";
					logger.info(message + " - " + messageDetail + this.getNewData().toString());
					createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail + this.getNewData().getCode());
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
					ItService previousRecord = (ItService) dataTable.getRowData();
					ItService previousRecordOriginalData = (ItService) Utilities.deepClone(previousRecord);

					// Subsequent row data
					dataTable.setRowIndex(currentPos + 1);
					ItService subsequentRecord = (ItService) dataTable.getRowData();

					// Set the endDate of the previows record to the startDate minus one date of the
					// subsequent record
					previousRecord.setEndDate(subsequentRecord.getStartDate().minusDays(1));
					previousRecord.setModifDate(LocalDateTime.now());
					previousRecord.setModifUser(this.getLoggedUser().getUserCode());

					// delete the selected data
					serviceEJB.deleteData(this.getSelectedHistoricData());

					// delete the original previous record (to eliminate overlaps)
					serviceEJB.deleteData(previousRecordOriginalData);

					// Update the previous record with the new endDate
					serviceEJB.updateHistoricDataRecord(previousRecord);

					messageDetail = "Data deletes succesfully";
					logger.info("Delete service: " + this.getSelectedHistoricData().toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

					logger.info("Update the previous record end date sucessfully");
				} else {

					// delete the selected data
					serviceEJB.deleteData(this.getSelectedHistoricData());

					messageDetail = "Data deletes succesfully";
					logger.info("Delete service: " + this.getSelectedHistoricData().toString() + " - " + messageDetail);
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
		ItService objectToValidate;
		boolean validation = true;
		List<ItProduct> list;

		LocalDateTime minDate = Formatter.stringToLocalDateTime("01/01/1900");
		LocalDateTime maxDate = Formatter.stringToLocalDateTime("31/12/9999");

		if (this.isFromAddingRow()) {
			message = "ADD NEW SERVICE ROW VALIDATION";
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY SERVICE VALIDATION";
			} else {
				message = "NEW SERVICE VALIDATION";
			}
		}

		objectToValidate = (ItService) dataObject;

		if (objectToValidate != null) {

			if (objectToValidate.getProductId() == null) {
				messageDetail = "Error - The product id is null.";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else {
				list = productEJB.findDataByProductId(objectToValidate.getProductId());
				if (list.isEmpty()) {
					messageDetail = "Error - The product for id " + objectToValidate.getProductId()
							+ " not exists in the system.";
					logger.error(messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message,
							messageDetail);
					validation = false;
				}
			}

			if (objectToValidate.getStartDate() == null) {
				messageDetail = "ERROR - The start date of the service can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getStartDate().compareTo(minDate) < 0) {
				messageDetail = "ERROR - The start date of the service can not be less than "
						+ Formatter.localDateTimeToString(minDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getStartDate().compareTo(maxDate) > 0) {
				messageDetail = "ERROR - The start date of the service can not be greater than "
						+ Formatter.localDateTimeToString(maxDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getEndDate() == null) {
				messageDetail = "ERROR - The end date of the service can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getEndDate().compareTo(minDate) < 0) {
				messageDetail = "ERROR - The end date of the service can not be less than "
						+ Formatter.localDateTimeToString(minDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getEndDate().compareTo(maxDate) > 0) {
				messageDetail = "ERROR - The end date of the service can not be greater than "
						+ Formatter.localDateTimeToString(maxDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getServiceTypeId() == null) {
				messageDetail = "ERROR - The service type of the service can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getServiceNumber() == null || objectToValidate.getServiceNumber().isEmpty()) {
				messageDetail = "ERROR - The service number of the service can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else {
				objectToValidate.setServiceNumber(objectToValidate.getServiceNumber().trim().toUpperCase());
			}

			if (objectToValidate.getGeneral_1() != null && !objectToValidate.getGeneral_1().isEmpty()) {

				objectToValidate.setGeneral_1(objectToValidate.getGeneral_1().trim().toUpperCase());
			}

			if (objectToValidate.getGeneral_2() != null && !objectToValidate.getGeneral_2().isEmpty()) {

				objectToValidate.setGeneral_2(objectToValidate.getGeneral_2().trim().toUpperCase());
			}

			if (objectToValidate.getGeneral_3() != null && !objectToValidate.getGeneral_3().isEmpty()) {

				objectToValidate.setGeneral_3(objectToValidate.getGeneral_3().trim().toUpperCase());
			}

			if (objectToValidate.getStatusId() == null) {
				messageDetail = "ERROR - The status of the service can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
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
			this.setDataList(serviceEJB.findInstanceViewWithParameters(Optional.ofNullable(this.getSearchDate()),
					this.getIncludeCanceledDataFlag(), Optional.ofNullable(this.getSearchServiceId()),
					 Optional.ofNullable(this.getSearchServiceTypeId()),
					Optional.ofNullable(this.getSearchServiceNr()), Optional.empty(),
					Optional.ofNullable(this.getSearchProductId()), 
					Optional.ofNullable(this.getSearchProductTypeId()), 
					Optional.ofNullable(this.getSearchContractNr()),
					Optional.ofNullable(this.getSearchAccountId()),
					Optional.ofNullable(this.getSearchAccountIdentityCard()), Optional.empty(),
					Optional.ofNullable(this.getSearchCustomerId()),
					Optional.ofNullable(this.getSearchCustomerIdentityCard()), Optional.empty()));

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
		this.setIncludeCanceledDataFlag(true);

	}

	@Override
	public void setControlVariablesToDefault() {
		this.setInitVariablesToDefault();

	}

	@Override
	public void loadHistoricalDataList() {
		Integer id;
		if (this.getSelectedData() == null) {
			logger.error("The service select is null");
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "LOAD HISTORIC DATA",
					"The service select is null");
		} else {
			id = this.getSelectedData().getServiceId();
			this.setHistoricDataList(serviceEJB.findDataByServiceId(id));
			if (this.getHistoricDataList().isEmpty()) {
				logger.info("No historical data data to show for service id " + id);

			} else {
				logger.info("Load historical data sucessful for service id " + id);
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
		ItService currentData = null;

		message = "ADD ROW";

		try {

			// Store the current data
			Utilities.copyGenericList(this.historicDataList, this.backupHistoricDataList);

			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (ItService) dataTable.getRowData();
			this.setSelectedHistoricData((ItService) Utilities.deepClone(currentData));

			this.setNewData(new ItService());
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
		this.setSelectedData((VwServiceInstance) dataTable.getRowData());

		if (this.getSelectedData() == null || this.getSelectedData().getServiceId() == null
				|| this.getSelectedData().getServiceId() == 0) {
			messageDetail = "The service is null";
			logger.error(message + " - " + messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.setShowSelectedData(true);
			this.getSelectedDataList().clear();
			this.getSelectedDataList().add(this.getSelectedData());

			this.refreshHistoricDataTable();

			messageDetail = "Shown data for service: ";
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
	public void pushSearchParentButton() {
		String message = "SEARCH PRODUCT";
		String messageDetail = "";

		// Gets the data form
		if ((this.getSearchProductIdForNew() == null || this.getSearchProductIdForNew() == 0)
				&& (this.getSearchProductTypeIdForNew() == null || this.getSearchProductTypeIdForNew() == 0)
				&& (this.getSearchCustomerIdForNew() == null || this.getSearchCustomerIdForNew() == 0)
				&& (this.getSearchCustomerIdentityCardForNew() == null
						|| this.getSearchCustomerIdentityCardForNew().isEmpty())
				&& (this.getSearchContractNrForNew() == null || this.getSearchContractNrForNew().isEmpty())
				&& (this.getSearchAccountIdentityCardForNew() == null
						|| this.getSearchAccountIdentityCardForNew().isEmpty())
				&& (this.getSearchAccountIdForNew() == null || this.getSearchAccountIdForNew() == 0)) {
			messageDetail = "The fields for the search criteria are null";
			logger.error(message + " - " + messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			facesContext.validationFailed();

		} else {
			try {
				this.getParentSearchDataList().clear();
				this.setParentSearchDataList(
						productEJB.findInstanceViewWithParameters(Optional.ofNullable(this.getSearchDate()), true,
								Optional.ofNullable(this.getSearchProductIdForNew()), 
								Optional.ofNullable(this.getSearchProductTypeIdForNew()), 
								Optional.empty(),
								Optional.ofNullable(this.getSearchContractNrForNew()),
								Optional.ofNullable(this.getSearchAccountIdForNew()),
								Optional.ofNullable(this.getSearchAccountIdentityCardForNew()), Optional.empty(),
								Optional.ofNullable(this.getSearchCustomerIdForNew()),
								Optional.ofNullable(this.getSearchCustomerIdentityCardForNew()), Optional.empty()));

				PrimeFaces current = PrimeFaces.current();
				current.executeScript("PF('parentDataTableWidget').clearFilters()");

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
				.findComponent(PARENT_DATA_TABLE_ID);

		if (this.getSelectedParentSearchData() == null) {
			this.setSelectedParentSearchData(new VwProductInstance());
		}

		this.setSelectedParentSearchData((VwProductInstance) dataTable.getRowData());

		this.getNewData().setProductId(this.getSelectedParentSearchData().getProductId());
		
		this.setSearchProductIdForNew(this.getSelectedParentSearchData().getProductId());
		this.setSearchProductTypeIdForNew(this.getSelectedParentSearchData().getProductTypeId());

		this.setSearchContractNrForNew(this.getSelectedParentSearchData().getContractNumber());
		this.setSearchAccountIdForNew(this.getSelectedParentSearchData().getAccountId());
		this.setSearchAccountIdentityCardForNew(this.getSelectedParentSearchData().getAccountIdentityCard());

		this.setSearchCustomerIdentityCardForNew(this.getSelectedParentSearchData().getCustomerIdentityCard());
		this.setSearchCustomerIdForNew(this.getSelectedParentSearchData().getCustomerId());

		PrimeFaces.current().executeScript("PF('searchParentListWidget').hide();");
		PrimeFaces.current().executeScript("PF('newParentPanelWidget').refresh();");

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
		ItService data = (ItService) dataTable.getRowData();

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
				((ItService) dataTable.getRowData()).setActiveDate(newCancelledDate);
				;
				Ajax.updateRow(dataTable, i);

			}

		}
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
		ItService data = (ItService) dataTable.getRowData();

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
				((ItService) dataTable.getRowData()).setActiveDate(newActiveDate);
				;
				Ajax.updateRow(dataTable, i);

			}

		}
	}

}
