/**
 * 
 */
package com.comasw.interfaces;

/**
 * @author catuxa
 *
 */
public interface ITable {

	/**
	 * Gets the data from database and put them into a list.
	 */
	public void loadDataList();

	/**
	 * Reset the filter of the data table
	 */
	public void resetFilterDataTable();

	/**
	 * Refresh the data table
	 */
	public void refreshDataTable();

	/**
	 * Sets the init variables to default value
	 */
	public void setInitVariablesToDefault();

	/**
	 * Sets the control variables to default value
	 */
	public void setControlVariablesToDefault();

}
