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

package com.comasw.ejb.instance;

import java.util.List;


import javax.ejb.Local;


import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.ItContract;

@Local
public interface ContractEJBLocal {
	
	
	/**
	 * Find all the contract data stores in the system
	 * 
	 * @return list of all contract data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItContract> findAllData() throws CoMaSwDataAccessException;
	
	/**
	 * Find the contract data stores in the system for the given customerId
	 * 
	 * @param contractId
	 * @return type data in the system for the given contractId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItContract> findDataByContractId(Integer contractId) throws CoMaSwDataAccessException;
	

	/**
	 * Find the contract data stores in the system for the given contractNr
	 * 
	 * @param contractNr
	 * @return type data in the system for the given contractNr
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItContract> findDataByContractNumber(String contractNr) throws CoMaSwDataAccessException;
	
	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;


	/**
	 * Inserts into the system (database) the given new contract. It creates a new
	 * Id and inserts into the id table.
	 * 
	 * @param dataObject contract object to insert
	 * @return integer with the id of the contract object
	 * @throws CoMaSwDataAccessException
	 */
	public Integer insertData(ItContract dataObject) throws CoMaSwDataAccessException;

		/**
	 * Updates into the system (database) the given contract
	 * 
	 * @param dataObject contract object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItContract dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given contract
	 * 
	 * @param dataObject contract object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItContract dataObject) throws CoMaSwDataAccessException;

}
