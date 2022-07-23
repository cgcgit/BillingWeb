/**
 * 
 */
package com.comasw.interfaces;

import javax.faces.validator.ValidatorException;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 * @author catuxa
 *
 */
public interface IRelatedCatalogType {
	
	/**
	 * Load main data of the relation
	 */
	public void loadSearchData();
	
	/**
	 * Load candidate data to relate
	 */
	public void loadCandidateData();
	
	/**
	 * Load related data
	 */
	public void loadRelatedData();
	
	/**
	 * Action to do when push the search button
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
	

	// -------------- EVENT MANAGEMENT ON THE TABLES
	
	/**
	 * Action for row select 
	 *
	 * @param event
	 */
	public void onRelatedRowSelect(SelectEvent<?> event);
	

	/**
	 * Initial action for row edition 
	 *
	 * @param event
	 */
	public void onRelatedRowInit(RowEditEvent<?> event);

	/**
	 * Action for row edition
	 *
	 * @param event
	 */
	public void onRelatedRowEdit(RowEditEvent<?> event) throws ValidatorException;

	/**
	 * Action for cancel row edition
	 *
	 * @param event
	 */
	public void onRelatedRowCancel(RowEditEvent<?> event);
	
	
	/**
	 * Action for row select 
	 *
	 * @param event
	 */
	public void onCandidateRowSelect(SelectEvent<?> event);
	
	
	/**
	 * Sets the init variables to default value
	 */
	public void setInitVariablesToDefault();
	
	/**
	 * Reset related table filter
	 */
	public void resetFilterRelatedDataTable();
	
	/**
	 * Refresh candidate table filter
	 */
	public void resetFilterCandidateDataTable();

	
	/**
	 * Refresh related data table 
	 */
	public void refreshRelatedDataTable();
	
	/**
	 * Refresh candidate data table 
	 */
	public void refreshCandidateDataTable();
	
	
	
}
