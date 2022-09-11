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
package com.comasw.ejb.parameterization;

import javax.ejb.Local;

import java.util.List;

import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ApplicationLevelEJBLocal {

	/**
	 * Find all the application level data stores in the system
	 * 
	 * @return list of all application level data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtApplicationLevel> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the application level data stores in the system for the given
	 * applicationLevelId
	 * 
	 * @param applicationLevelId
	 * @return application level data in the system for the given applicationLevelId
	 * @throws CoMaSwDataAccessException
	 */
	public PtApplicationLevel findDataByApplicationLevelId(Integer applicationLevelId) throws CoMaSwDataAccessException;

	/**
	 * Find the application level data stores in the system for the given code of
	 * application level
	 * 
	 * @param code
	 * @return application level data in the system for the given code of
	 *         application level
	 * @throws CoMaSwDataAccessException
	 */
	public PtApplicationLevel findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given application level
	 * 
	 * @param dataObject application level object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given application level
	 * 
	 * @param dataObject application level object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given application level
	 * 
	 * @param dataObject application level object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException;

}
