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

package com.comasw.interfaces;

import javax.faces.validator.ValidatorException;

import org.primefaces.event.RowEditEvent;

/**
 * @author catuxa
 *
 */
public interface IRelationsTable extends ITable {

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
	public void onRelatedRowEdit(RowEditEvent<?> event) throws ValidatorException;

	/**
	 * Action to cancel the edit related row
	 * 
	 * @param event
	 */
	public void onRelatedRowCancel(RowEditEvent<?> event);

	// public void onCandidateRowSelect(SelectEvent<?> event);

}
