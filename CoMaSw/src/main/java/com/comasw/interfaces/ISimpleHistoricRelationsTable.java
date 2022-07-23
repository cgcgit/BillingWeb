package com.comasw.interfaces;


import javax.faces.event.ValueChangeEvent;

public interface ISimpleHistoricRelationsTable extends IRelationsTable{

	/**
	 * Gets the historic candidate data from database and put them into a list.
	 */
	public void loadHistoricCandidateDataList();
	
	/**
	 * Action to push the button to show the historic data from candidate
	 */
	public void pushShowHistoricCandidateRowButton();
	
	/**
	 * Reset the filter of the historic candidate data table
	 */
	public void resetFilterHistoricCandidateDataTable();	

	/**
	 * Action to do when changes the search date
	 * @param e - change event
	 */
	public void changeSearchDate(ValueChangeEvent e) ;
	
	
}
