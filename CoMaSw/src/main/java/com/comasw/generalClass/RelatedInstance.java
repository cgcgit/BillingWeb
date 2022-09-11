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

package com.comasw.generalClass;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.comasw.utilities.Formatter;

public class RelatedInstance <T, U, S, V> extends BasicListsForInstance<T> {

	Logger logger = (Logger) LogManager.getLogger(BasicInstance.class);

	protected static String DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("dataTableID");
	protected static String SELECTED_DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("selectedDataTableID");
	protected static String NEW_PANEL_DATA_ID = "form:accordionPanel:" + uiValues.getString("newPanelDataID");

	protected static String HISTORIC_DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("historicDataTableID");

	protected static String PARENT_ONE_DATA_TABLE_ID = "form:newParentPanel:parentOneSearchDataTable";
	protected static String PARENT_TWO_DATA_TABLE_ID = "form:newParentPanel:parentTwoSearchDataTable";
	
	protected static final String APPLICATION_LEVEL_PROD = dbDefinitions.getString("APPLICATION_LEVEL_CODE_PROD");
	protected static final String APPLICATION_LEVEL_SERV = dbDefinitions.getString("APPLICATION_LEVEL_CODE_SERV");
	
	protected static Integer STATUS_ID_PENDING_INSTANCE = Integer
			.valueOf(dbDefinitions.getString("STATUS_ID_PENDING_INSTANCE"));
	
	
	/**
	 * New data to create/new row to insert
	 */
	private U newData;

	/**
	 * Selected data list
	 */
	protected List<T> selectedDataList;

	/**
	 * Filtered data for the selected list
	 */
	protected List<T> filteredSelectedDataList;

	/**
	 * Backup object for the selected data list
	 */
	protected List<T> backupSelectedDataList;

	/**
	 * Historic data list
	 */
	protected List<U> historicDataList;

	/**
	 * Filtered data for the historic list
	 */
	protected List<U> filteredHistoricDataList;

	/**
	 * Backup object for the selected data list
	 */
	protected List<U> backupHistoricDataList;

	/**
	 * Selected data row in the table
	 */

	private U selectedHistoricData;
	
	
	/**
	 * selected row index of the historic table
	 */

	protected int rowHistoricSelected;
	
	
	/**
	 * Current row index of the historic table
	 */

	protected int currentHistoricRow;
	

	/**
	 * Parent type one search data List 
	 */
	private List<S> parentOneSearchDataList;
	
	/**
	 *  Parent type one filtered search data List 
	 */
	private List<S> filteredParentOneSearchDataList;
	
	/**
	 *  Selected parent type one data list 
	 */

	private S selectedParentOneSearchData;
	
	
	/**
	 * Parent type one search data List 
	 */
	private List<V> parentTwoSearchDataList;
	
	/**
	 *  Parent type one filtered search data List 
	 */
	private List<V> filteredParentTwoSearchDataList;
	
	/**
	 *  Selected parent type one data list 
	 */

	private V selectedParentTwoSearchData;
	
	/**
	 * Search date
	 */
	protected LocalDateTime searchDate;

	/**
	 * Message for the delete action
	 */
	protected String deleteMessageDialog;

	/**
	 * Message for the cancel action
	 */
	protected String cancelMessageDialog;

	/**
	 * Flag that indicates if exists data to show
	 */
	protected boolean showSelectedData;

	/**
	 * Indicates if the status change to Cancel
	 */
	protected boolean toCancel;
	
	/**
	 * Indicates if the active date is changed
	 */
	
	protected boolean activeDateChanged;
	
	
	/**
	 * Indicates if the cancelled date is changed
	 */
	
	protected boolean cancelledDateChanged;
	
	/**
	 * Indicates if the action is from adding new row
	 */
	protected boolean fromAddingRow;

	/**
	 * Previous status id to control the changes for the edition. Value = -1 --> the
	 * first change into the edition
	 */
	protected Integer prevStatusId;

	/**
	 * Text to shown in the dialog header
	 */
	protected String dialogHeaderText;

	/**
	 * Flag for the historic criteria to search
	 */

	protected boolean historicSearchDataCriteria;

	/**
	 * Text to shown in the search data table title
	 */
	protected String searchDataTableTitle;
	
	/**
	 * Flag to search include cancelled data (true) or not (false)
	 */
	protected boolean includeCanceledDataFlag;

	// ---------------------\\
	// GETTERS AND SETTERs \\
	// ---------------------\\


	/**
	 * @return the newData
	 */
	public U getNewData() {
		return newData;
	}

	/**
	 * @param newData the newData to set
	 */
	public void setNewData(U newData) {
		this.newData = newData;
	}

	/**
	 * @return the selectedDataList
	 */
	public List<T> getSelectedDataList() {
		return selectedDataList;
	}

	/**
	 * @param selectedDataList the selectedDataList to set
	 */
	public void setSelectedDataList(List<T> selectedDataList) {
		this.selectedDataList = selectedDataList;
	}

	/**
	 * @return the filteredSelectedDataList
	 */
	public List<T> getFilteredSelectedDataList() {
		return filteredSelectedDataList;
	}

	/**
	 * @param filteredSelectedDataList the filteredSelectedDataList to set
	 */
	public void setFilteredSelectedDataList(List<T> filteredSelectedDataList) {
		this.filteredSelectedDataList = filteredSelectedDataList;
	}

	/**
	 * @return the backupSelectedDataList
	 */
	public List<T> getBackupSelectedDataList() {
		return backupSelectedDataList;
	}

	/**
	 * @param backupSelectedDataList the backupSelectedDataList to set
	 */
	public void setBackupSelectedDataList(List<T> backupSelectedDataList) {
		this.backupSelectedDataList = backupSelectedDataList;
	}

	/**
	 * @return the historicDataList
	 */
	public List<U> getHistoricDataList() {
		return historicDataList;
	}

	/**
	 * @param historicDataList the historicDataList to set
	 */
	public void setHistoricDataList(List<U> historicDataList) {
		this.historicDataList = historicDataList;
	}

	/**
	 * @return the filteredHistoricDataList
	 */
	public List<U> getFilteredHistoricDataList() {
		return filteredHistoricDataList;
	}

	/**
	 * @param filteredHistoricDataList the filteredHistoricDataList to set
	 */
	public void setFilteredHistoricDataList(List<U> filteredHistoricDataList) {
		this.filteredHistoricDataList = filteredHistoricDataList;
	}

	/**
	 * @return the backupHistoricDataList
	 */
	public List<U> getBackupHistoricDataList() {
		return backupHistoricDataList;
	}

	/**
	 * @param backupHistoricDataList the backupHistoricDataList to set
	 */
	public void setBackupHistoricDataList(List<U> backupHistoricDataList) {
		this.backupHistoricDataList = backupHistoricDataList;
	}

	/**
	 * @return the selectedHistoricData
	 */
	public U getSelectedHistoricData() {
		return selectedHistoricData;
	}

	/**
	 * @param selectedHistoricData the selectedHistoricData to set
	 */
	public void setSelectedHistoricData(U selectedHistoricData) {
		this.selectedHistoricData = selectedHistoricData;
	}

	/**
	 * @return the rowHistoricSelected
	 */
	public int getRowHistoricSelected() {
		return rowHistoricSelected;
	}

	/**
	 * @param rowHistoricSelected the rowHistoricSelected to set
	 */
	public void setRowHistoricSelected(int rowHistoricSelected) {
		this.rowHistoricSelected = rowHistoricSelected;
	}

	/**
	 * @return the currentHistoricRow
	 */
	public int getCurrentHistoricRow() {
		return currentHistoricRow;
	}

	/**
	 * @param currentHistoricRow the currentHistoricRow to set
	 */
	public void setCurrentHistoricRow(int currentHistoricRow) {
		this.currentHistoricRow = currentHistoricRow;
	}

	/**
	 * @return the parentOneSearchDataList
	 */
	public List<S> getParentOneSearchDataList() {
		return parentOneSearchDataList;
	}

	/**
	 * @param parentOneSearchDataList the parentOneSearchDataList to set
	 */
	public void setParentOneSearchDataList(List<S> parentOneSearchDataList) {
		this.parentOneSearchDataList = parentOneSearchDataList;
	}

	/**
	 * @return the filteredParentOneSearchDataList
	 */
	public List<S> getFilteredParentOneSearchDataList() {
		return filteredParentOneSearchDataList;
	}

	/**
	 * @param filteredParentOneSearchDataList the filteredParentOneSearchDataList to set
	 */
	public void setFilteredParentOneSearchDataList(List<S> filteredParentOneSearchDataList) {
		this.filteredParentOneSearchDataList = filteredParentOneSearchDataList;
	}

	/**
	 * @return the selectedParentOneSearchData
	 */
	public S getSelectedParentOneSearchData() {
		return selectedParentOneSearchData;
	}

	/**
	 * @param selectedParentOneSearchData the selectedParentOneSearchData to set
	 */
	public void setSelectedParentOneSearchData(S selectedParentOneSearchData) {
		this.selectedParentOneSearchData = selectedParentOneSearchData;
	}

	/**
	 * @return the parentTwoSearchDataList
	 */
	public List<V> getParentTwoSearchDataList() {
		return parentTwoSearchDataList;
	}

	/**
	 * @param parentTwoSearchDataList the parentTwoSearchDataList to set
	 */
	public void setParentTwoSearchDataList(List<V> parentTwoSearchDataList) {
		this.parentTwoSearchDataList = parentTwoSearchDataList;
	}

	/**
	 * @return the filteredParentTwoSearchDataList
	 */
	public List<V> getFilteredParentTwoSearchDataList() {
		return filteredParentTwoSearchDataList;
	}

	/**
	 * @param filteredParentTwoSearchDataList the filteredParentTwoSearchDataList to set
	 */
	public void setFilteredParentTwoSearchDataList(List<V> filteredParentTwoSearchDataList) {
		this.filteredParentTwoSearchDataList = filteredParentTwoSearchDataList;
	}

	/**
	 * @return the selectedParentTwoSearchData
	 */
	public V getSelectedParentTwoSearchData() {
		return selectedParentTwoSearchData;
	}

	/**
	 * @param selectedParentTwoSearchData the selectedParentTwoSearchData to set
	 */
	public void setSelectedParentTwoSearchData(V selectedParentTwoSearchData) {
		this.selectedParentTwoSearchData = selectedParentTwoSearchData;
	}

	/**
	 * @return the searchDate
	 */
	public LocalDateTime getSearchDate() {
		return searchDate;
	}

	/**
	 * @param searchDate the searchDate to set
	 */
	public void setSearchDate(LocalDateTime searchDate) {
		this.searchDate = searchDate;
	}

	/**
	 * @return the deleteMessageDialog
	 */
	public String getDeleteMessageDialog() {
		return deleteMessageDialog;
	}

	/**
	 * @param deleteMessageDialog the deleteMessageDialog to set
	 */
	public void setDeleteMessageDialog(String deleteMessageDialog) {
		this.deleteMessageDialog = deleteMessageDialog;
	}

	/**
	 * @return the cancelMessageDialog
	 */
	public String getCancelMessageDialog() {
		return cancelMessageDialog;
	}

	/**
	 * @param cancelMessageDialog the cancelMessageDialog to set
	 */
	public void setCancelMessageDialog(String cancelMessageDialog) {
		this.cancelMessageDialog = cancelMessageDialog;
	}

	/**
	 * @return the showSelectedData
	 */
	public boolean isShowSelectedData() {
		return showSelectedData;
	}

	/**
	 * @param showSelectedData the showSelectedData to set
	 */
	public void setShowSelectedData(boolean showSelectedData) {
		this.showSelectedData = showSelectedData;
	}

	/**
	 * @return the toCancel
	 */
	public boolean isToCancel() {
		return toCancel;
	}

	/**
	 * @param toCancel the toCancel to set
	 */
	public void setToCancel(boolean toCancel) {
		this.toCancel = toCancel;
	}

	/**
	 * @return the activeDateChanged
	 */
	public boolean isActiveDateChanged() {
		return activeDateChanged;
	}

	/**
	 * @param activeDateChanged the activeDateChanged to set
	 */
	public void setActiveDateChanged(boolean activeDateChanged) {
		this.activeDateChanged = activeDateChanged;
	}

	/**
	 * @return the cancelledDateChanged
	 */
	public boolean isCancelledDateChanged() {
		return cancelledDateChanged;
	}

	/**
	 * @param cancelledDateChanged the cancelledDateChanged to set
	 */
	public void setCancelledDateChanged(boolean cancelledDateChanged) {
		this.cancelledDateChanged = cancelledDateChanged;
	}

	/**
	 * @return the fromAddingRow
	 */
	public boolean isFromAddingRow() {
		return fromAddingRow;
	}

	/**
	 * @param fromAddingRow the fromAddingRow to set
	 */
	public void setFromAddingRow(boolean fromAddingRow) {
		this.fromAddingRow = fromAddingRow;
	}

	/**
	 * @return the prevStatusId
	 */
	public Integer getPrevStatusId() {
		return prevStatusId;
	}

	/**
	 * @param prevStatusId the prevStatusId to set
	 */
	public void setPrevStatusId(Integer prevStatusId) {
		this.prevStatusId = prevStatusId;
	}

	/**
	 * @return the dialogHeaderText
	 */
	public String getDialogHeaderText() {
		return dialogHeaderText;
	}

	/**
	 * @param dialogHeaderText the dialogHeaderText to set
	 */
	public void setDialogHeaderText(String dialogHeaderText) {
		this.dialogHeaderText = dialogHeaderText;
	}

	/**
	 * @return the historicSearchDataCriteria
	 */
	public boolean isHistoricSearchDataCriteria() {
		return historicSearchDataCriteria;
	}

	/**
	 * @param historicSearchDataCriteria the historicSearchDataCriteria to set
	 */
	public void setHistoricSearchDataCriteria(boolean historicSearchDataCriteria) {
		this.historicSearchDataCriteria = historicSearchDataCriteria;
	}

	/**
	 * @return the searchDataTableTitle
	 */
	public String getSearchDataTableTitle() {
		return searchDataTableTitle;
	}

	/**
	 * @param searchDataTableTitle the searchDataTableTitle to set
	 */
	public void setSearchDataTableTitle(String searchDataTableTitle) {
		this.searchDataTableTitle = searchDataTableTitle;
	}
	

	/**
	 * @return the includeCanceledDataFlag
	 */
	public boolean getIncludeCanceledDataFlag() {
		return includeCanceledDataFlag;
	}

	/**
	 * @param includeCanceledDataFlag the includeCanceledDataFlag to set
	 */
	public void setIncludeCanceledDataFlag(boolean includeCanceledDataFlag) {
		this.includeCanceledDataFlag = includeCanceledDataFlag;
	}

	
	
	// --------\\
		// METHODS \\
		// --------\\

		/**
		 * Validates if the dates are valid to create new historic according to create
		 * consecutive records. NOT VALID conditions:
		 * 
		 * <p>
		 * currentStartDate > currentEndDate
		 * <p>
		 * newStartDate > newEndDate
		 * <p>
		 * newStartDate < minDate
		 * <p>
		 * newEndDate > maxEndDate
		 * <p>
		 * newStartDate = currentStartDate && newEndDate = currentEndDate
		 * <p>
		 * newStartDate < currentStartDate && newEndDate > currentEndDate
		 * <p>
		 * if newStartDate >= currentStartDate && newEndDate <= currentEndDate ==>
		 * (currentStartDate - currentEndDate) < 1
		 * <p>
		 * 
		 * @param currentStartDate current start date of the record
		 * @param currentEndDate   current end date of the record
		 * @param newStartDate     start date of the new data
		 * @param newEndDate       end of the new data
		 * @return true if grant contitions
		 */
		public boolean rangeDateValidation(FacesContext fc, ExternalContext ec, LocalDateTime currentStartDate,
				LocalDateTime currentEndDate, LocalDateTime newStartDate, LocalDateTime newEndDate) {
			String message = "Data validation";
			String messageDetail = "";
			boolean validation = true;

			if (currentStartDate.isAfter(currentEndDate)) {
				// currentStartDate > currentEndDate ==> out of range ==> error
				messageDetail = "Error in dates- The current start date can not be greather than current end date";
				logger.error(messageDetail + " - current dates: [" + Formatter.localDateTimeToString(currentStartDate)
						+ ", " + Formatter.localDateTimeToString(currentEndDate) + "] " + "new dates ["
						+ Formatter.localDateTimeToString(newStartDate) + ", " + Formatter.localDateTimeToString(newEndDate)
						+ "] ");
				createMessage(fc, ec, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (newStartDate.isAfter(newEndDate)) {
				// newStartDate > newEndDate ==> out of range ==> error
				messageDetail = "Error in dates- The new start date can not be greather than new end date";
				logger.error(messageDetail + " - current dates: [" + Formatter.localDateTimeToString(currentStartDate)
						+ ", " + Formatter.localDateTimeToString(currentEndDate) + "] " + "new dates ["
						+ Formatter.localDateTimeToString(newStartDate) + ", " + Formatter.localDateTimeToString(newEndDate)
						+ "] ");
				createMessage(fc, ec, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (newStartDate.isBefore(Formatter.stringToLocalDateTime(Formatter.DEFAULT_START_DATE))) {
				// newStartDate < minDate ==> error
				messageDetail = "Error in dates- The new start date can not be less than minimun data allowed ("
						+ Formatter.stringToLocalDateTime(Formatter.DEFAULT_START_DATE) + ").";
				logger.error(messageDetail + " - current dates: [" + Formatter.localDateTimeToString(currentStartDate)
						+ ", " + Formatter.localDateTimeToString(currentEndDate) + "] " + "new dates ["
						+ Formatter.localDateTimeToString(newStartDate) + ", " + Formatter.localDateTimeToString(newEndDate)
						+ "] ");
				createMessage(fc, ec, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (newEndDate.isAfter(Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE))) {
				// newEndDate > maxEndDate ==> error
				messageDetail = "Error in dates- The new end date can not be greather than maximum data allowed ("
						+ Formatter.stringToLocalDateTime(Formatter.DEFAULT_END_DATE) + ").";
				logger.error(messageDetail + " - current dates: [" + Formatter.localDateTimeToString(currentStartDate)
						+ ", " + Formatter.localDateTimeToString(currentEndDate) + "] " + "new dates ["
						+ Formatter.localDateTimeToString(newStartDate) + ", " + Formatter.localDateTimeToString(newEndDate)
						+ "] ");
				createMessage(fc, ec, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (newStartDate.isEqual(currentStartDate) && newEndDate.isEqual(currentEndDate)) {
				// newStartDate = currentStartDate && newEndDate = currentEndDate ==> no sense
				// to create new record histori ==> error
				messageDetail = "Error in dates- The new and current new dates can not be the same";
				logger.error(messageDetail + " - current dates: [" + Formatter.localDateTimeToString(currentStartDate)
						+ ", " + Formatter.localDateTimeToString(currentEndDate) + "] " + "new dates ["
						+ Formatter.localDateTimeToString(newStartDate) + ", " + Formatter.localDateTimeToString(newEndDate)
						+ "] ");
				createMessage(fc, ec, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (newStartDate.isBefore(currentStartDate) && newEndDate.isAfter(currentEndDate)) {
				// newStartDate < currentStartDate && newEndDate > currentEndDate ==> out of
				// range ==> error
				messageDetail = "Error in dates- The new dates are out of range from current dates";
				logger.error(messageDetail + " - current dates: [" + Formatter.localDateTimeToString(currentStartDate)
						+ ", " + Formatter.localDateTimeToString(currentEndDate) + "] " + "new dates ["
						+ Formatter.localDateTimeToString(newStartDate) + ", " + Formatter.localDateTimeToString(newEndDate)
						+ "] ");
				createMessage(fc, ec, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}

			if (Duration.between(currentStartDate, currentEndDate).toDays() < 1) {
				// not enough data margin to split the row
				messageDetail = "Error in dates - The period between current dates must be at leas one day.";
				logger.error(messageDetail + " - current dates: [" + Formatter.localDateTimeToString(currentStartDate)
						+ ", " + Formatter.localDateTimeToString(currentEndDate) + "] " + "new dates ["
						+ Formatter.localDateTimeToString(newStartDate) + ", " + Formatter.localDateTimeToString(newEndDate)
						+ "] ");
				createMessage(fc, ec, FacesMessage.SEVERITY_ERROR, message, messageDetail);
				validation = false;
			}
			return validation;

		}

}
