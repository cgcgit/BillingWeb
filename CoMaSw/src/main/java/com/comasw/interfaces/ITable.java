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

/**
 * @author catuxa
 *
 */
public interface ITable {

	/**
	 * Gets the data from database and put them into a list.
	 */
	public void loadDataList();

	/**
	 * Reset the filter of the data table
	 */
	public void resetFilterDataTable();

	/**
	 * Refresh the data table
	 */
	public void refreshDataTable();

	/**
	 * Sets the init variables to default value
	 */
	public void setInitVariablesToDefault();

	/**
	 * Sets the control variables to default value
	 */
	public void setControlVariablesToDefault();

}
