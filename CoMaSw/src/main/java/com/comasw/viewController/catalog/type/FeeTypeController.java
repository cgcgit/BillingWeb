package com.comasw.viewController.catalog.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.ejb.catalog.type.FeeTypeEJBLocal;
import com.comasw.generalClass.BasicHistoricWithLists;
import com.comasw.interfaces.IEditableHistoricTable;
import com.comasw.utilities.Formatter;
import com.comasw.utilities.Utilities;

@Named
@ViewScoped
public class FeeTypeController extends BasicHistoricWithLists<CtFeeType>
		implements Serializable, IEditableHistoricTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6789853537280113269L;

	Logger logger = (Logger) LogManager.getLogger(FeeTypeController.class);

	private static final Integer ENTITY_TYPE_ID = Integer.valueOf(dbDefinitions.getString("ENTITY_TYPE_ID_FEE"));

	@Inject
	private ExternalContext externalContext;
	@Inject
	private FacesContext facesContext;

	@EJB
	private FeeTypeEJBLocal feeTypeEJB;

	// ----- SELECTED DATA -----\\

	@Inject
	/**
	 * Selected data from dataList
	 */
	private CtFeeType injectSelectedData;

	/**
	 * Selected data row in the table
	 */
	@Inject
	private CtFeeType injectSelectedHistoricData;

	// ----- NEW DATA -----\\

	@Inject
	private CtFeeType injectNewData;

	// --------------------
	// GETTERS AND SETTERS
	// -------------------

	/**
	 * @return the selectedData
	 */
	public CtFeeType getInjectSelectedData() {
		return injectSelectedData;
	}

	/**
	 * @param selection the selectedData to set
	 */
	public void setInjectSelectedData(CtFeeType injectSelectedData) {
		this.injectSelectedData = injectSelectedData;
	}

	/**
	 * @return the selectedHistoricData
	 */
	public CtFeeType getInjectSelectedHistoricData() {
		return injectSelectedHistoricData;
	}

	/**
	 * @param selectedHistoricData the selectedHistoricData to set
	 */
	public void setInjectSelectedHistoricData(CtFeeType injectSelectedHistoricData) {
		this.injectSelectedHistoricData = injectSelectedHistoricData;
	}

	/**
	 * @return the newData
	 */
	public CtFeeType getInjectNewData() {
		return injectNewData;
	}

	/**
	 * @param newData the newData to set
	 */
	public void setInjectNewData(CtFeeType injectNewData) {
		this.injectNewData = injectNewData;
	}

	// -------------------
	// METHODS
	// -------------------

	/**
	 * 
	 */
	public FeeTypeController() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		if (this.getSearchDate() == null) {
			this.setSearchDate(LocalDate.now().atStartOfDay());
		}

		if (this.getDataList() == null) {
			this.setDataList(new ArrayList<CtFeeType>());
		}

		if (this.getFilteredDataList() == null) {
			this.setFilteredDataList(new ArrayList<CtFeeType>());
		}

		if (this.getSelectedData() == null) {
			this.setSelectedData(new CtFeeType());
		}

		if (this.getHistoricDataList() == null) {
			this.setHistoricDataList(new ArrayList<CtFeeType>());
		}

		if (this.getFilteredHistoricDataList() == null) {
			this.setFilteredHistoricDataList(new ArrayList<CtFeeType>());
		}

		if (this.getBackupHistoricDataList() == null) {
			this.setBackupHistoricDataList(new ArrayList<CtFeeType>());
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
			this.setDataList(feeTypeEJB.findDataBySearchDate(searchDate));
			if (this.getDataList().isEmpty()) {
				logger.info("No data to show");

			} else {
				logger.info("Load data sucessful");
			}
		}

	}

	@Override
	public void loadHistoricalDataList() {
		Integer feeTypeId;
		if (this.getSelectedData() == null) {
			logger.error("The fee type select is null");
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, "LOAD HISTORIC DATA",
					"The fee type select is null");
		} else {
			feeTypeId = this.getSelectedData().getFeeTypeId();
			this.setHistoricDataList(feeTypeEJB.findDataByFeeTypeId(feeTypeId));
			if (this.getHistoricDataList().isEmpty()) {
				logger.info("No historical data data to show for fee_type_id " + feeTypeId);

			} else {
				logger.info("Load historical data sucessful for fee_type_id " + feeTypeId);
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
			this.setDataList(feeTypeEJB.findDataBySearchDate(searchDate));
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
			this.setInjectSelectedData(new CtFeeType());
		}

		// Gets the selected data
		this.setInjectSelectedData((CtFeeType) dataTable.getRowData());

		if (this.getInjectSelectedData() == null || this.getInjectSelectedData().getFeeTypeId() == null
				|| this.getInjectSelectedData().getFeeTypeId() == 0) {
			messageDetail = "The fee type is null";
			logger.error(message + " - " + messageDetail);
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);

		} else {
			this.setShowSelectedData(true);
			this.setSelectedData(this.getInjectSelectedData());
			loadHistoricalDataList();

			messageDetail = "Shown data for fee: ";
			logger.info(message + " - " + messageDetail + this.getInjectSelectedData().toString());
			createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
					messageDetail + injectSelectedData.getCode());

			PrimeFaces.current().executeScript("PF('searchListWidget').hide();");
			PrimeFaces.current().executeScript("PF('multipleAccordionPanelWidget').selectAll();");
			resetFilterHistoricDataTable();

		}

	}

	@Override
	public void onRowInit(RowEditEvent<?> event) {
		CtFeeType dataObject;
		String message, message_detail;

		message = "EDIT ROW";

		// Gets the object to modify
		dataObject = (CtFeeType) event.getObject();

		// If we are editing a row, we must disabled all the other buttons
		this.setEditingMode(true);

		try {
			// The user was pushed the edit button
			message_detail = "Editing fee type: " + dataObject.toString();
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
		CtFeeType dataObject;
		String message, messageDetail;
		boolean validation = false;
		CtFeeType originalDataObject;
		CtFeeType otherRecordDataObject;
		boolean changeHistoricRecords = false;

		message = "SAVE EDIT ROW";

		// Retrieved the data that was modified
		dataObject = (CtFeeType) event.getObject();

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
									otherRecordDataObject = (CtFeeType) dataTable.getRowData();
									dataTable.setRowIndex(pos);

									feeTypeEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos - 1);
									feeTypeEJB.deleteData(otherRecordDataObject);

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
									otherRecordDataObject = (CtFeeType) dataTable.getRowData();
									dataTable.setRowIndex(pos);
									feeTypeEJB.updateHistoricDataRecord(otherRecordDataObject);
									// deletes the original data for this record
									otherRecordDataObject = this.backupHistoricDataList.get(pos + 1);
									feeTypeEJB.deleteData(otherRecordDataObject);

									changeHistoricRecords = true;
								}
							}
						}
					}
				}

				if (changeHistoricRecords) {

					// update the current data record
					feeTypeEJB.updateHistoricDataRecord(dataObject);
					// delete the original data of the record
					feeTypeEJB.deleteData(originalDataObject);
				} else {
					feeTypeEJB.updateData(dataObject);
				}

				// Check if the status was changed to cancel --> if so, we must update the
				// status of the subsequent rows
				if (this.isToCancel()) {
					int i;
					for (i = pos + 1; i <= lastRow; i++) {
						dataTable.setRowIndex(i);
						dataObject = (CtFeeType) dataTable.getRowData();
						// feeTypeEJB.updateHistoricDataRecord(dataObject);
						feeTypeEJB.updateData(dataObject);
					}

					logger.info("Updated the status to cancel for the subsequent rows");

				}

				// set the row index to the current position
				dataTable.setRowIndex(pos);

				// reload the list
				this.loadHistoricalDataList();

				// Evaluates if the data was by adding or update
				messageDetail = "The changes for fee" + dataObject.toString() + " has done";
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
		CtFeeType dataObject;
		String message, messageDetail;

		message = "CANCEL UPDATE ROW";

		// Retrieved the data that was modified
		dataObject = (CtFeeType) event.getObject();

		try {
			this.loadHistoricalDataList();

			messageDetail = "The changes for fee type " + dataObject.toString() + " was cancelled";
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

	/*
	 * 
	 * public void pushAddNewRowButton_old() { String message, messageDetail;
	 * DataTable dataTable; CtFeeType currentData = null;
	 * 
	 * message = "ADD ROW";
	 * 
	 * try {
	 * 
	 * // Store the current data Utilities.copyGenericList(this.historicDataList,
	 * this.backupHistoricDataList);
	 * 
	 * // Gets the current data dataTable = (DataTable)
	 * FacesContext.getCurrentInstance().getViewRoot()
	 * .findComponent(HISTORIC_DATA_TABLE_ID); // pos = dataTable.getRowIndex();
	 * currentHistoricRow = dataTable.getRowIndex(); currentData = (CtFeeType)
	 * dataTable.getRowData(); // dataTable.setRowIndex(currentHistoricRow + 1);
	 * 
	 * // newData = new CtFeeType(); // newData.from(currentData); //
	 * historicDataList.add(currentHistoricRow, newData);
	 * 
	 * PrimeFaces.current().executeScript("PF('historicTableWidget').addRow();");
	 * PrimeFaces.current().executeScript("jQuery('span.ui-icon-pencil').eq(" +
	 * (currentHistoricRow + 1) + ").each(function(){jQuery(this).click()});");
	 * 
	 * // Sets the fromAddingRow value to true this.fromAddingRow = true;
	 * 
	 * } catch (EJBException e) { Exception ne = (Exception) e.getCause(); if
	 * (ne.getClass().getName().equals("es.comasw.exception.CoMaSwParseException"))
	 * { messageDetail = "PARSE ERROR - " + ne.getMessage();
	 * logger.fatal(messageDetail); createMessage(facesContext, externalContext,
	 * FacesMessage.SEVERITY_ERROR, message, messageDetail);
	 * 
	 * } else { messageDetail = "ERROR - " + ne.getMessage();
	 * logger.fatal(messageDetail); createMessage(facesContext, externalContext,
	 * FacesMessage.SEVERITY_ERROR, message, messageDetail); }
	 * 
	 * } catch (Exception e) { messageDetail = "ERROR - " + e.getCause().toString();
	 * logger.fatal(messageDetail); createMessage(facesContext, externalContext,
	 * FacesMessage.SEVERITY_ERROR, message, messageDetail); }
	 * 
	 * }
	 */
	@Override
	public void pushAddNewRowButton() {
		String message, messageDetail;
		DataTable dataTable;
		CtFeeType currentData = null;

		message = "ADD ROW";

		try {

			// Store the current data
			Utilities.copyGenericList(this.historicDataList, this.backupHistoricDataList);

			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (CtFeeType) dataTable.getRowData();
			this.setInjectSelectedHistoricData((CtFeeType) Utilities.deepClone(currentData));

			this.injectNewData = new CtFeeType();
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
		CtFeeType currentData;

		message = "DELETE ROW";

		try {
			// Gets the current data
			dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent(HISTORIC_DATA_TABLE_ID);
			// pos = dataTable.getRowIndex();
			this.setCurrentHistoricRow(dataTable.getRowIndex());
			currentData = (CtFeeType) dataTable.getRowData();
			this.setInjectSelectedHistoricData((CtFeeType) Utilities.deepClone(currentData));

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
		this.injectNewData = new CtFeeType();
		this.injectNewData.setEntityTypeId(ENTITY_TYPE_ID);
		this.injectNewData.setStartDate(Formatter.stringToLocalDateTime(Formatter.DEFAULT_START_DATE));
		this.injectNewData.setEndDate(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE));
		this.injectNewData.setInputUser(this.loggedUser.getUserCode());
		this.injectNewData.setInputDate(LocalDateTime.now());

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
		CtFeeType originalPreviousDataRow, previousDataRow, subsequentDataRow;

		try {
			if (this.objectValidation(injectNewData)) {

				if (this.isFromAddingRow()) {
					// Gets the table
					DataTable dataTable = (DataTable) facesContext.getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
					Integer totalRows = dataTable.getRowCount();

					// The previous row from new data is the current row on the data table
					previousDataRow = this.getBackupHistoricDataList().get(row);
					// Gets original values from previous row --> backup
					originalPreviousDataRow = (CtFeeType) Utilities.deepClone(previousDataRow);

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
							logger.info("Create fee type: " + this.injectNewData.toString() + " - " + messageDetail);
							this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
									messageDetail);
							error = true;

						}
						if (injectNewData.getEndDate()
								.isEqual(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE))
								&& row != (totalRows - 1)) {
							// new end date = maxDate for a row different from last row ==> error
							messageDetail = "Error in dates - Only the last row can sets the end date to the maximum allowed date.";
							logger.info("Create fee type: " + this.injectNewData.toString() + " - " + messageDetail);
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

									feeTypeEJB.insertNewHistoricDataRecord(injectNewData);

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

											feeTypeEJB.insertNewHistoricDataRecord(injectNewData);
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
												feeTypeEJB.insertNewHistoricDataRecord(injectNewData);

												// set the modified values
												previousDataRow.setModifUser(this.loggedUser.getUserCode());
												previousDataRow.setModifDate(LocalDateTime.now());
												// set second section of the record with the original value (except
												// dates)
												previousDataRow.setStartDate(injectNewData.getEndDate().plusDays(1));

												feeTypeEJB.updateHistoricDataRecord(previousDataRow);

												// delete the original record
												feeTypeEJB.deleteData(originalPreviousDataRow);
											} else {
												// no consecutive records ==> error
												messageDetail = "Error in dates - The new and current dates are not consecutives.";
												logger.info("Create fee type: " + this.injectNewData.toString() + " - "
														+ messageDetail);
												this.createMessage(facesContext, externalContext,
														FacesMessage.SEVERITY_INFO, message, messageDetail);
												error = true;
											}
										}

									} else {
										// not the first row ==> not allowed
										messageDetail = "Error in dates - The new end date can not be less than current start date.";
										logger.info("Create fee type: " + this.injectNewData.toString() + " - "
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
										previousDataRow = (CtFeeType) Utilities.deepClone(injectNewData);
										previousDataRow.setInputDate(LocalDateTime.now());
										previousDataRow.setInputUser(this.getLoggedUser().getUserCode());

										feeTypeEJB.updateHistoricDataRecord(previousDataRow);

										// split the record --> set second section of the record with the original value
										// (except dates)
										previousDataRow = (CtFeeType) Utilities.deepClone(originalPreviousDataRow);
										// set the modified values
										previousDataRow.setModifUser(this.loggedUser.getUserCode());
										previousDataRow.setModifDate(LocalDateTime.now());
										// set the new start date
										previousDataRow.setStartDate(injectNewData.getEndDate().plusDays(1));
										feeTypeEJB.updateHistoricDataRecord(previousDataRow);

										// delete the original record
										feeTypeEJB.deleteData(originalPreviousDataRow);

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
											feeTypeEJB.updateHistoricDataRecord(previousDataRow);

											// split the record --> set second section of the record with the new value
											previousDataRow = (CtFeeType) Utilities.deepClone(injectNewData);
											previousDataRow.setInputDate(LocalDateTime.now());
											previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
											feeTypeEJB.updateHistoricDataRecord(previousDataRow);

											// delete the original record
											feeTypeEJB.deleteData(originalPreviousDataRow);

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
											feeTypeEJB.updateHistoricDataRecord(previousDataRow);

											// split the record --> set second section of the record with the new value
											previousDataRow = (CtFeeType) Utilities.deepClone(injectNewData);
											previousDataRow.setInputDate(LocalDateTime.now());
											previousDataRow.setInputUser(this.getLoggedUser().getUserCode());
											feeTypeEJB.updateHistoricDataRecord(previousDataRow);

											// split the record --> set third section of the record with the original
											// values
											// (except dates)
											previousDataRow = (CtFeeType) Utilities.deepClone(originalPreviousDataRow);
											// set the modified values
											previousDataRow.setModifUser(this.loggedUser.getUserCode());
											previousDataRow.setModifDate(LocalDateTime.now());
											// set the new startDate and endDate
											previousDataRow.setStartDate(injectNewData.getEndDate().plusDays(1));
											previousDataRow.setEndDate(originalPreviousDataRow.getEndDate());
											feeTypeEJB.updateHistoricDataRecord(previousDataRow);

											// delete the original record
											feeTypeEJB.deleteData(originalPreviousDataRow);
										}
									}

								}
							}

						}
					}
				} else {
					// create a new object
					feeTypeEJB.insertData(injectNewData);

					CtFeeType object = feeTypeEJB.findDataBySearchDateAndCode(this.injectNewData.getStartDate(),
							this.injectNewData.getCode());

					messageDetail = "Data saves succesfully";
					logger.info("Create fee type: " + this.injectNewData.toString() + " - " + messageDetail);
					this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail);

					// Show the data for the new object
					this.setShowSelectedData(true);
					this.setSelectedData(object);
					loadHistoricalDataList();

					messageDetail = "Shown data for fee: ";
					logger.info(message + " - " + messageDetail + this.injectNewData.toString());
					createMessage(facesContext, externalContext, FacesMessage.SEVERITY_INFO, message,
							messageDetail + this.injectNewData.getCode());

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
		
		currentPos=-1;
		lastPos=-1;

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
					CtFeeType previousRecord = (CtFeeType) dataTable.getRowData();
					CtFeeType previousRecordOriginalData = (CtFeeType) Utilities.deepClone(previousRecord);

					// Subsequent row data
					dataTable.setRowIndex(currentPos + 1);
					CtFeeType subsequentRecord = (CtFeeType) dataTable.getRowData();

					// Set the endDate of the previows record to the startDate minus one date of the
					// subsequent record
					previousRecord.setEndDate(subsequentRecord.getStartDate().minusDays(1));
					previousRecord.setModifDate(LocalDateTime.now());
					previousRecord.setModifUser(this.getLoggedUser().getUserCode());

					// Update the previous record with the new endDate
					feeTypeEJB.updateHistoricDataRecord(previousRecord);
					// delete the original previous record
					feeTypeEJB.deleteData(previousRecordOriginalData);

					logger.info("Update the previous record end date sucessfully");
				}

				// delete the selected data
				feeTypeEJB.deleteData(this.injectSelectedHistoricData);

				messageDetail = "Data deletes succesfully";
				logger.info("Delete fee type: " + this.injectSelectedHistoricData.toString() + " - " + messageDetail);
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
		CtFeeType objectToValidate;
		boolean validation = true;
		LocalDateTime minDate = Formatter.stringToLocalDateTime("01/01/1900");
		LocalDateTime maxDate = Formatter.stringToLocalDateTime("31/12/9999");

		if (this.isFromAddingRow()) {
			message = "ADD NEW FEE TYPE ROW VALIDATION";
		} else {
			if (this.isEditingMode()) {
				message = "MODIFY FEE TYPE VALIDATION";
			} else {
				message = "NEW FEE TYPE VALIDATION";
			}
		}

		objectToValidate = (CtFeeType) dataObject;

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

			if (objectToValidate.getStartDate() == null) {
				messageDetail = "ERROR - The start date of the fee type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getStartDate().compareTo(minDate) < 0) {
				messageDetail = "ERROR - The start date of the fee type can not be less than "
						+ Formatter.localDateTimeToString(minDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getStartDate().compareTo(maxDate) > 0) {
				messageDetail = "ERROR - The start date of the fee type can not be greater than "
						+ Formatter.localDateTimeToString(maxDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getEndDate() == null) {
				messageDetail = "ERROR - The end date of the fee type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getEndDate().compareTo(minDate) < 0) {
				messageDetail = "ERROR - The end date of the fee type can not be less than "
						+ Formatter.localDateTimeToString(minDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (objectToValidate.getEndDate().compareTo(maxDate) > 0) {
				messageDetail = "ERROR - The end date of the fee type can not be greater than "
						+ Formatter.localDateTimeToString(maxDate);
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getCode() == null || objectToValidate.getCode().length() == 0) {
				messageDetail = "ERROR - The code of the fee type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (((Integer) objectToValidate.getCode().length())
					.compareTo(BasicHistoricWithLists.CODE_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The code of the fee type (" + objectToValidate.getCode().length()
						+ " characters) exceeds the limit of "
						+ BasicHistoricWithLists.CODE_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}
			if (objectToValidate.getName() == null || objectToValidate.getName().length() == 0) {
				messageDetail = "ERROR - The name of the fee type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (((Integer) objectToValidate.getName().length())
					.compareTo(BasicHistoricWithLists.NAME_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The name of the fee type (" + objectToValidate.getName().length()
						+ " characters) exceeds the limit of "
						+ BasicHistoricWithLists.NAME_FIELD_LENGTH_MAX.toString() + " characters";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getDescription() == null || objectToValidate.getDescription().length() == 0) {
				messageDetail = "ERROR - The description of the fee type can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			} else if (((Integer) objectToValidate.getDescription().length())
					.compareTo(BasicHistoricWithLists.DESCRIPTION_FIELD_LENGTH_MAX) > 0) {
				// length characters exceeds the maximum length
				messageDetail = "Error - The description of the fee type (" + objectToValidate.getDescription().length()
						+ " characters) exceeds the limit of "
						+ BasicHistoricWithLists.DESCRIPTION_FIELD_LENGTH_MAX.toString() + " characters";

				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getApplicationLevelId() == null) {
				validation = false;
				messageDetail = "ERROR - The application level can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}

			if (objectToValidate.getProrate() == null) {
				validation = false;
				messageDetail = "ERROR - The prorate flag can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
			}

			if (objectToValidate.getPrice().compareTo(BigDecimal.valueOf(0)) < 0) {
				messageDetail = "ERROR - The price must be a positive number";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (objectToValidate.getStatusId() == null) {
				validation = false;
				messageDetail = "ERROR - The status can not be null";
				logger.error(messageDetail);
				this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
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
		CtFeeType data = (CtFeeType) dataTable.getRowData();

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
						((CtFeeType) dataTable.getRowData()).setStatusId(STATUS_ID_CANC);
						Ajax.updateRow(dataTable, i);

					}

					dataTable.setRowIndex(currentPos);

					messageDetail = "Subsequent status change succesfully";
					logger.info("Change status fee type: " + this.injectSelectedHistoricData.toString() + " - "
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
		CtFeeType currentObject;

		dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(HISTORIC_DATA_TABLE_ID);
		lastPos = dataTable.getRowCount() - 1;
		currentPos = this.getCurrentHistoricRow();

		if (this.isToCancel()) {

			// System.out.println("actualStatusId: " + currentUser.getStatusId());
			if (this.isFromAddingRow()) {
				// The old value was equal to the previous row
				currentObject = (CtFeeType) dataTable.getRowData();
				currentObject.setStatusId(((CtFeeType) this.backupHistoricDataList.get(currentPos - 1)).getStatusId());
				Ajax.updateRow(dataTable, currentPos);
			}

			for (int i = currentPos + 1; i <= lastPos; i++) {
				dataTable.setRowIndex(i);

				currentObject = (CtFeeType) dataTable.getRowData();
				if (!STATUS_ID_CANC.equals(currentObject.getStatusId())) {
					// the status of the row is not cancel ==> updates the status
					// and the modif values

					currentObject.setStatusId(((CtFeeType) this.backupHistoricDataList.get(i)).getStatusId());
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
			this.setDialogHeaderText("Add New Historic Row for Fee Type");
		} else {
			this.setDialogHeaderText("Create new Fee Type");
		}
	}

	@Override
	public void changeDeleteMessage() {
		this.setDeleteMessageDialog("Fee type " + this.injectSelectedHistoricData.getCode()
				+ " - Do you really want to delete historic period ["
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getEndDate()) + "]? ");
	}

	@Override
	public void changeStatusMessage() {
		this.setCancelMessageDialog("Fee type " + this.injectSelectedHistoricData.getCode()
				+ " - Do you really want to set the status to cancel from historic period ["
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getStartDate()) + ", "
				+ Formatter.localDateTimeToString(this.injectSelectedHistoricData.getEndDate()) + "] onwards?");
	}
	
	@Override
	public void changeSearchDate(ValueChangeEvent e) {
		LocalDateTime newSearchDate = (LocalDateTime) e.getNewValue();
		String message, messageDetail;
		
		message="CHANGE SEARCH DATE";
		
		if (newSearchDate != null) {
			this.setSearchDate(newSearchDate);			
		} else {
			messageDetail = "ERROR - The search date can not be null";
			logger.fatal(messageDetail);
			this.createMessage(facesContext, externalContext, FacesMessage.SEVERITY_ERROR, message, messageDetail);
		}
	}

}