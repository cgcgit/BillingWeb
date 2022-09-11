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
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.comasw.ejb.catalog.type.AccountTypeEJBLocal;
import com.comasw.ejb.catalog.type.CustomerTypeEJBLocal;
import com.comasw.ejb.catalog.type.FeeTypeEJBLocal;
import com.comasw.ejb.catalog.type.ProductTypeEJBLocal;
import com.comasw.ejb.catalog.type.PromotionTypeEJBLocal;
import com.comasw.ejb.catalog.type.ServiceTypeEJBLocal;
import com.comasw.ejb.parameterization.IdentityCardTypeEJBLocal;
import com.comasw.ejb.parameterization.StatusEJBLocal;
import com.comasw.model.tables.pojos.CtAccountType;
import com.comasw.model.tables.pojos.CtCustomerType;
import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtProductType;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.CtServiceType;
import com.comasw.model.tables.pojos.PtIdentityCardType;
import com.comasw.model.tables.pojos.PtStatus;
import com.comasw.utilities.Formatter;

/**
 * @author catuxa
 *
 */
public class BasicInstanceWithViews<T,U> extends BasicTypeWithLists<T> {
	
	protected static String DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("dataTableID");
	protected static String SELECTED_PARENT_DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("selectedParentDataTableID");
	protected static String SELECTED_DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("selectedDataTableID");
	protected static String NEW_PANEL_DATA_ID = "form:accordionPanel:" + uiValues.getString("newPanelDataID");

	protected static String HISTORIC_DATA_TABLE_ID = "form:accordionPanel:" + uiValues.getString("historicDataTableID");

	protected static Integer STATUS_ID_PENDING_INSTANCE = Integer
			.valueOf(dbDefinitions.getString("STATUS_ID_PENDING_INSTANCE"));
	

	@EJB
	private StatusEJBLocal statusEJB;
		
	@EJB
	private IdentityCardTypeEJBLocal identityCardTypeEJB;
	
	@EJB
	private CustomerTypeEJBLocal customerTypeEJB;
	
	@EJB
	private AccountTypeEJBLocal accountTypeEJB;
	
	@EJB
	private ProductTypeEJBLocal productTypeEJB;
	
	@EJB
	private ServiceTypeEJBLocal serviceTypeEJB;
	
	@EJB
	private PromotionTypeEJBLocal promotionTypeEJB;
	
	@EJB 
	private FeeTypeEJBLocal feeTypeEJB;
	
	
	/**
	 * Selected parent data list
	 */
	protected List<T> parentSelectedDataList;

	/**
	 * Filtered parent data for the selected list
	 */
	protected List<T> filteredParentSelectedDataList;
	
	/**
	 * Selected parent data
	 */
	
	protected T parentSelectedData;
	
	

	/**
	 * New data to create/new row to insert
	 */	
	private U newData;

	
	/**
	 * Selected data list
	 */
	protected List<U> selectedDataList;

	/**
	 * Filtered data for the selected list
	 */
	protected List<U> filteredSelectedDataList;

	/**
	 * Backup object for the selected data list
	 */
	protected List<U> backupSelectedDataList;

	
	protected U selectedDataFromSelectedDataList;
	
	

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
	
	

	// ---------------------\\
	// GETTERS AND SETTERs \\
	// ---------------------\\
	
	
	/**
	 * @return the parentSelectedDataList
	 */
	public List<T> getParentSelectedDataList() {
		return parentSelectedDataList;
	}

	/**
	 * @param parentSelectedDataList the parentSelectedDataList to set
	 */
	public void setParentSelectedDataList(List<T> parentSelectedDataList) {
		this.parentSelectedDataList = parentSelectedDataList;
	}




	/**
	 * @return the selectedDataFromSelectedDataList
	 */
	public U getSelectedDataFromSelectedDataList() {
		return selectedDataFromSelectedDataList;
	}

	/**
	 * @param selectedDataFromSelectedDataList the selectedDataFromSelectedDataList to set
	 */
	public void setSelectedDataFromSelectedDataList(U selectedDataFromSelectedDataList) {
		this.selectedDataFromSelectedDataList = selectedDataFromSelectedDataList;
	}

	
	/**
	 * @return the filteredParentSelectedDataList
	 */
	public List<T> getFilteredParentSelectedDataList() {
		return filteredParentSelectedDataList;
	}

	/**
	 * @param filteredParentSelectedDataList the filteredParentSelectedDataList to set
	 */
	public void setFilteredParentSelectedDataList(List<T> filteredParentSelectedDataList) {
		this.filteredParentSelectedDataList = filteredParentSelectedDataList;
	}
	
	
	/**
	 * @return the parentSelectedData
	 */
	public T getParentSelectedData() {
		return parentSelectedData;
	}

	/**
	 * @param parentSelectedData the parentSelectedData to set
	 */
	public void setParentSelectedData(T parentSelectedData) {
		this.parentSelectedData = parentSelectedData;
	}


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
	public List<U> getSelectedDataList() {
		return selectedDataList;
	}

	/**
	 * @param selectedDataList the selectedDataList to set
	 */
	public void setSelectedDataList(List<U> selectedDataList) {
		this.selectedDataList = selectedDataList;
	}

	/**
	 * @return the filteredSelectedDataList
	 */
	public List<U> getFilteredSelectedDataList() {
		return filteredSelectedDataList;
	}

	/**
	 * @param filteredSelectedDataList the filteredSelectedDataList to set
	 */
	public void setFilteredSelectedDataList(List<U> filteredSelectedDataList) {
		this.filteredSelectedDataList = filteredSelectedDataList;
	}

	/**
	 * @return the backupSelectedDataList
	 */
	public List<U> getBackupSelectedDataList() {
		return backupSelectedDataList;
	}

	/**
	 * @param backupSelectedDataList the backupSelectedDataList to set
	 */
	public void setBackupSelectedDataList(List<U> backupSelectedDataList) {
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
	 * @return the rowIndexHistoricSelected
	 */
	public int getRowHistoricSelected() {
		return rowHistoricSelected;
	}

	/**
	 * @param rowIndexHistoricSelected the rowIndexHistoricSelected to set
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
	 * @return the headerText
	 */
	public String getDialogHeaderText() {
		return dialogHeaderText;
	}

	/**
	 * @param headerText the headerText to set
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

	
	// --------\\
	// METHODS \\
	// --------\\


	/*
	 * Return the list of select items with the status data
	 */
	public List<SelectItem> statusInstanceSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtStatus> list = statusEJB.findDataForInstance();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find status list");
		} else {
			for (PtStatus p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getStatusId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	

	/*
	 * Return the list of select items with the identity card type data
	 */
	public List<SelectItem> identityCardTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<PtIdentityCardType> list = identityCardTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find identity card type list");
		} else {
			for (PtIdentityCardType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getIdentityCardTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	

	/*
	 * Return the list of select items with the customer type data
	 */
	public List<SelectItem> customerTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtCustomerType> list = customerTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find customer type list");
		} else {
			for (CtCustomerType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getCustomerTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}

	/*
	 * Return the list of select items with the account type data
	 */
	public List<SelectItem> accountTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtAccountType> list = accountTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find account type list");
		} else {
			for (CtAccountType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getAccountTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	
	/*
	 * Return the list of select items with the product type data
	 */
	public List<SelectItem> productTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtProductType> list = productTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find product type list");
		} else {
			for (CtProductType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getProductTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	/*
	 * Return the list of select items with the service type data
	 */
	public List<SelectItem> serviceTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtServiceType> list = serviceTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find service type list");
		} else {
			for (CtServiceType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getServiceTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	/*
	 * Return the list of select items with the promotion type data
	 */
	public List<SelectItem> promotionTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtPromotionType> list = promotionTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find promotion type list");
		} else {
			for (CtPromotionType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getPromotionTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	/*
	 * Return the list of select items with the fee type data
	 */
	public List<SelectItem> feeTypeSelectItems() {
		List<SelectItem> selectItem = new ArrayList<>();
		List<CtFeeType> list = feeTypeEJB.findAllData();

		SelectItem nullItem = new SelectItem();
		nullItem.setLabel("Select One... ");
		nullItem.setValue(null);
		selectItem.add(nullItem);

		if (list.isEmpty()) {
			logger.error("ERROR - Not find promotion type list");
		} else {
			for (CtFeeType p : list) {
				SelectItem item = new SelectItem();
				item.setLabel(p.getCode());
				item.setValue(p.getFeeTypeId());
				item.setDescription(p.getDescription());
				selectItem.add(item);
			}
		}
		return selectItem;
	}
	
	
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
	 * @return true if grant conditions
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
