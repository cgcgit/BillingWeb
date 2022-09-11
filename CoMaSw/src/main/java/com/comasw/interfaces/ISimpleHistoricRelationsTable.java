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
