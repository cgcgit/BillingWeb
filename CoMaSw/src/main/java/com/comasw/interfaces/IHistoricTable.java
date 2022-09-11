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

	
}
