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

package com.comasw.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtServiceType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ServiceTypeEJBLocal {

	/**
	 * Find all the service type data stores in the system
	 * 
	 * @return list of all service type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtServiceType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the service type data stores in the system for the given serviceTypeId
	 * 
	 * @param serviceTypeId
	 * @return list of all service type data in the system for the given
	 *         serviceTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtServiceType findDataByServiceTypeId(Integer serviceTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the service type data stores in the system for the given code of service
	 * type
	 * 
	 * @param code
	 * @return list of all service type data in the system for the given code of
	 *         service type
	 * @throws CoMaSwDataAccessException
	 */
	public CtServiceType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given service type
	 * 
	 * @param dataObject service type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtServiceType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given service type
	 * 
	 * @param dataObject service type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtServiceType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given service type
	 * 
	 * @param dataObject service type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtServiceType dataObject) throws CoMaSwDataAccessException;

}
