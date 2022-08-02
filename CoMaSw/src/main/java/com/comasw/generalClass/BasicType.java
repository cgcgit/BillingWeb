/**
 * 
 */
package com.comasw.generalClass;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * @author catuxa
 *
 */
public class BasicType<T> extends BasicClass {

	Logger logger = (Logger) LogManager.getLogger(BasicType.class);

	protected static String DATA_TABLE_ID = "form:" + uiValues.getString("dataTableID");
	protected static String NEW_PANEL_DATA_ID = "form:" + uiValues.getString("newPanelDataID");

	protected static Integer STATUS_ID_CANC = Integer.valueOf(dbDefinitions.getString("STATUS_ID_CANC"));

	protected static String ACTIVE_STATUS_CODE = dbDefinitions.getString("STATUS_CODE_ACT");

	/**
	 * List with the data of type T
	 */
	private List<T> dataList;

	/**
	 * Filtered data list of type T
	 */
	private List<T> filteredDataList;

	/**
	 * Selected data
	 */
	private T selectedData;

	/**
	 * Indicates if there are any rows in edit mode
	 */
	protected boolean editingMode = false;

	/**
	 * Current row of the table
	 */
	protected int currentRow;

	// ---------------------\\
	// GETTERS AND SETTERs \\
	// ---------------------\\

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public List<T> getFilteredDataList() {
		return filteredDataList;
	}

	public void setFilteredDataList(List<T> filteredDataList) {
		this.filteredDataList = filteredDataList;
	}

	/**
	 * @return the selectedData
	 */
	public T getSelectedData() {
		return selectedData;
	}

	/**
	 * @param selectedData the selectedData to set
	 */
	public void setSelectedData(T selectedData) {
		this.selectedData = selectedData;
	}

	/**
	 * @return the editingMode
	 */
	public boolean isEditingMode() {
		return editingMode;
	}

	/**
	 * @param editingMode the editingMode to set
	 */
	public void setEditingMode(boolean editingMode) {
		this.editingMode = editingMode;
	}

	/**
	 * @return the currentRow
	 */
	public int getCurrentRow() {
		return currentRow;
	}

	/**
	 * @param currentRow the currentRow to set
	 */
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	// --------\\
	// METHODS \\
	// --------\\

}
