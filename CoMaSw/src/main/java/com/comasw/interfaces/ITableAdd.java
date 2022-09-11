/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

*/

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
