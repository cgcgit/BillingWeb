package com.comasw.interfaces;

public interface ITableAdd extends ITable {

	// -------------- DIRECT ACTIONS ON THE TABLES

	/**
	 * Action to push the delete row button
	 */
	public void pushDeleteRowButton();

	// -------------- INDIRECT ACTIONS ON THE TABLES

	/**
	 * Action to push the create new button (creates new entity)
	 */
	public void pushCreateNewButton();

	/**
	 * Action to push the confirm button for the add new operation
	 */
	public void pushConfirmButtonCreateNewDialog();

	/**
	 * Action to push the cancel button for the add new operation
	 */
	public void pushCancelButtonCreateNewDialog();

	/**
	 * Action to push the clean button for the add new operation
	 */
	public void pushCleanButtonCreateNewDialog();

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
