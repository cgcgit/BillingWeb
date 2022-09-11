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

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtStatus;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface StatusEJBLocal {

	/**
	 * Find all the status data stores in the system
	 * 
	 * @return list of all status data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtStatus> findAllData() throws CoMaSwDataAccessException;
	
	
	/**
	 * Find all the status data stores in the system for catalog scope
	 * 
	 * @return list of all status data in the system for catalog scope
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtStatus> findDataForCatalog() throws CoMaSwDataAccessException;
	
	
	/**
	 * Find all the status data stores in the system for instance scope
	 * 
	 * @return list of all status data in the system for instance scope
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtStatus> findDataForInstance() throws CoMaSwDataAccessException;

	/**
	 * Find the status data stores in the system for the given statusId
	 * 
	 * @param statusId
	 * @return status data in the system for the given statusId
	 * @throws CoMaSwDataAccessException
	 */
	public PtStatus findDataByStatusId(Integer statusId) throws CoMaSwDataAccessException;

	/**
	 * Find the status data stores in the system for the given code of status
	 * 
	 * @param code
	 * @return status data in the system for the given code of status
	 * @throws CoMaSwDataAccessException
	 */
	public PtStatus findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given status
	 * 
	 * @param dataObject status object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtStatus dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtStatus dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtStatus dataObject) throws CoMaSwDataAccessException;

}
