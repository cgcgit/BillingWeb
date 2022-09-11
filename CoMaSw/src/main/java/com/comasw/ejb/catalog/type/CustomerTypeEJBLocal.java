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

import com.comasw.model.tables.pojos.CtCustomerType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface CustomerTypeEJBLocal {

	/**
	 * Find all the customer type data stores in the system
	 * 
	 * @return list of all customer type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtCustomerType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the customer type data stores in the system for the given customerTypeId
	 * 
	 * @param customerTypeId
	 * @return customer type data in the system for the given customerTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtCustomerType findDataByCustomerTypeId(Integer customerTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the customer type data stores in the system for the given code of
	 * customer type
	 * 
	 * @param code
	 * @return customer type data in the system for the given code of customer type
	 * @throws CoMaSwDataAccessException
	 */
	public CtCustomerType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given customer type
	 * 
	 * @param dataObject customer type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtCustomerType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given customer type
	 * 
	 * @param dataObject customer type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtCustomerType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given customer type
	 * 
	 * @param dataObject customer type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtCustomerType dataObject) throws CoMaSwDataAccessException;

}
