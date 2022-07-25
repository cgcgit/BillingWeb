package com.comasw.interfaces;

import javax.faces.event.ValueChangeEvent;

public interface IHistoricTable extends ITableAdd {

	// -------------- DIRECT ACTIONS ON THE TABLES

	/**
	 * Gets the historic data from database and put them into a list.
	 */
	public void loadHistoricalDataList();

	/**
	 * Action to push the search button
	 */
	public void pushSearchButton();
	
	
	/**
	 * Action to push the add new row button
	 */
	public void pushAddNewRowButton();
	

	/**
	 * Action to push the row button which shows the detail data (i.e. the historic
	 * data)
	 */
	public void pushShowDetailRowButton();


	/**
	 * Reset the filter from historic data table
	 */
	public void resetFilterHistoricDataTable();

	/**
	 * Refresh the historic data table
	 */
	public void refreshHistoricDataTable();
	
	 /**
	 * Action to do when changes the search date
	 * @param e - change event
	 */	
	public void changeSearchDate(ValueChangeEvent e);
	

}
