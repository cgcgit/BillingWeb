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
