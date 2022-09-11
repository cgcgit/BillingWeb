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

import javax.faces.event.ValueChangeEvent;

public interface IInstanceTable extends IEditableHistoricTable {
	
	/**
	 * Action to do when push the search customer button in create new account form
	 */
	public void pushSearchParentButton() ;
	
	
	public void addParentToInstanceRowButton() ;
	
	/**
	 * Action to change the cancelled date (all dates of the records must be the same)
	 */
	public void changeCancelledDate(ValueChangeEvent e);
	
	/**
	 * Action to change the active date (all dates of the records must be the same)
	 */
	public void changeActiveDate(ValueChangeEvent e);

}
