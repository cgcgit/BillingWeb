/**
 * 
 */
package com.comasw.interfaces;

import javax.faces.validator.ValidatorException;

import org.primefaces.event.RowEditEvent;

/**
 * @author catuxa
 *
 */
public interface IRelationsTable {
	
	/**
	 * Gets the data from database and put them into a list.
	 */
	public void loadDataList();
	
	/**
	 * Load related data
	 */
	public void loadRelatedDataList();
	
		
	/**
	 * Load candidate data to relate
	 */
	public void loadCandidateDataList();
		
		
	/**
	 * Action to push the search button
	 */
	
	
	public void pushSearchButton();
	
	/**
	 * Action to do when push the selected button
	 */
	
	public void pushSelectedButton();
	

	/**
	 * Action to do when push the add related data button
	 */
	
	public void pushAddButtonCandidateToRelatedData();
	
	/**
	 * Action to do when push the remove related data button
	 */
	
	public void pushRemoveButtonFromRelatedData();
	
	/**
	 * Reset the filter of the data table
	 */
	public void resetFilterDataTable();
	
	/**
	 * Refresh the data table
	 */
	public void refreshDataTable();
	
		
	/**
	 * Reset the filter of the candidate data table
	 */
	public void resetFilterCandidateDataTable();
	
	/**
	 * Refresh the candidate data table
	 */
	public void refreshCandidateDataTable();
	
	
	/**
	 * Reset the filter of the related data table
	 */
	public void resetFilterRelatedDataTable();
	
	/**
	 * Refresh the related data table
	 */
	public void refreshRelatedDataTable();

	
	/**
	 * Action to edit related row 
	 * 
	 * @param event
	 */
	public void onRelatedRowInit(RowEditEvent<?> event);
	
	
	/**
	 * Action to save the edit related row
	 * 
	 * @param event
	 * @throws ValidatorException
	 */
	public void onRelatedRowEdit(RowEditEvent<?> event) throws ValidatorException ;
	
	/**
	 * Action to cancel the edit related row
	 * 
	 * @param event
	 */
	public void onRelatedRowCancel(RowEditEvent<?> event);
	
	//public void onCandidateRowSelect(SelectEvent<?> event);
	
	
	/**
	 * Sets the init variables to default value
	 */
	public void setInitVariablesToDefault();

	/**
	 * Sets the control variables to default value
	 */
	public void setControlVariablesToDefault();
	
}
