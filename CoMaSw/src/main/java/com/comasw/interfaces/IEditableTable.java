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
 *         Manages the editable tables for the entities
 *
 */
public interface IEditableTable extends ITableAdd {

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

}
