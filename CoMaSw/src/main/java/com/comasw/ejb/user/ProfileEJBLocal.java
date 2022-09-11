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

package com.comasw.ejb.user;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.ItProfiles;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ProfileEJBLocal {

	/**
	 * Find all the user data stores in the system
	 * 
	 * @return list of all users in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItProfiles> findData() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given user
	 * 
	 * @param dataObject user object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(ItProfiles dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given user
	 * 
	 * @param dataObject user object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItProfiles dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given user
	 * 
	 * @param dataObject user object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItProfiles dataObject) throws CoMaSwDataAccessException;

}
