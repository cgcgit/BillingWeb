package com.comasw.interfaces;


public interface ISimpleHistoricRelationsTable extends IRelationsTable {

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
	 * Defines the proper message for the result data search data table
	 */
	public void changeSearchDataTableTitle();

}
