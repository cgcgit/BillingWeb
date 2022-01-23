/**
 * 
 */
package com.billingweb.interfaces;

import javax.faces.validator.ValidatorException;

import org.primefaces.event.RowEditEvent;

/**
 * @author catuxa
 * 
 *         Manages the editable tables for the entities
 *
 */
public interface IEditableTable {

	/**
	 * Gets the data from database and put them into a list.
	 */
	public void loadDataList();

	// -------------- EVENT MANAGEMENT ON THE TABLES

	/**
	 * Initializes the row edition of the table
	 *
	 * @param event
	 */
	public void onRowInit(RowEditEvent<?> event);

	/**
	 * Storages the data for the row edited. If the data has some invalid value, it
	 * throws a ValidatorException
	 *
	 * @param event
	 */
	public void onRowEdit(RowEditEvent<?> event) throws ValidatorException;

	/**
	 * Cancels the editing row of the table
	 *
	 * @param event
	 */
	public void onRowCancel(RowEditEvent<?> event);

	// -------------- DIRECT ACTIONS ON THE TABLES

	/**
	 * Action to push the add new row button
	 */
	public void pushAddNewRowButton();

	/**
	 * Action to push the delete row button
	 */
	public void pushDeleteRowButton();

	/**
	 * Action to push the update row button
	 */
	public void pushUpdateRowButton();

	// -------------- INDIRECT ACTIONS ON THE TABLES

	/**
	 * Action to push the add new button (creates new entity)
	 */
	public void pushAddNewButton();

	/**
	 * Action to push the confirm button for the add new operation
	 */
	public void pushConfirmButtonAddNewDialog();

	/**
	 * Action to push the cancel button for the add new operation
	 */
	public void pushCancelButtonAddNewDialog();

	/**
	 * Action to push the clean button for the add new operation
	 */
	public void pushCleanButtonAddNewDialog();

	/**
	 * Operation to push the confirm button on confirm delete dialog
	 */
	public void pushConfirmButtonDeleteDialog();

	/**
	 * Operation to push the cancel button on confirm delete dialog
	 */
	public void pushCancelButtonDeleteDialog();

	// -------------- OTHERS

	/**
	 * Reset the values of the object to null values except those that must have a
	 * default value
	 */
	public void resetObjectValues();

	/**
	 * Validates if the object has all the correct fields.
	 *
	 * @param object object to validate
	 * @return true: the entity is correct false: otherwise
	 */
	public boolean objectValidation(Object dataObject);

	/**
	 * Retrieves the backup data
	 */
	public void retrieveBackupData();

}
