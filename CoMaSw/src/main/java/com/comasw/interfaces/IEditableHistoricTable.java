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

import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.datatable.DataTable;


/**
 * @author catuxa
 *
 */
public interface IEditableHistoricTable extends IEditableTable, IHistoricTable {

	/**
	 * Action to change the status of the historic value
	 */
	public void changeStatus(ValueChangeEvent e);

	/**
	 * Revert the cancelled status changed of subsequent rows
	 */

	public void revertSubsequentCancelStatus();

	/**
	 * Action to push the confirm change status button
	 */
	public void pushConfirmButtonChangeStatus();

	/**
	 * Defines the proper header for the current action
	 */
	public void changeNewDialogHeader();

	/**
	 * Defines the proper message for the current delete action
	 */
	public void changeDeleteMessage();

	/**
	 * Defines the proper message for the current change status Action
	 */
	public void changeStatusMessage();
	
	/**
	 * Function that split a row to insert a new row in the data table
	 * 
	 * @param dataTable
	 * @param row
	 * @param newRow
	 * @return
	 */
	public boolean splitRecords(DataTable dataTable, int row, Object newRow);
	
	/**
	 * Defines the proper message for the result data search data table
	 */
	public void changeSearchDataTableTitle();

}
