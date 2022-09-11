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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.ItCustomer;
import com.comasw.model.tables.pojos.VwCustomerInstance;

@Local
public interface CustomerEJBLocal {

	/**
	 * Find all the customer data stores in the system
	 * 
	 * @return list of all customer data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItCustomer> findAllData() throws CoMaSwDataAccessException;
	
	/**
	 * Find the customer data stores in the system for the given customerId
	 * 
	 * @param customerId
	 * @return type data in the system for the given customerId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItCustomer> findDataByCustomerId(Integer customerId) throws CoMaSwDataAccessException;
	

	/**
	 * Find the customer data stores in the system for the given searchDate
	 * 
	 * @param searchDate
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItCustomer> findDataBySearchDate(LocalDateTime searchDate) throws CoMaSwDataAccessException;

	

	/**
	 * Find the customer data stores in the system for the given customerId and
	 * searchDate
	 * 
	 * @param searchDate
	 * @param customerId
	 * @return type data in the system for the given customerId and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public ItCustomer findDataBySearchDateAndCustomerId(LocalDateTime searchDate, Integer customerId)
			throws CoMaSwDataAccessException;
	
	

	/**
	 * Find the not canceled customer data stores in the system for the given customerId and
	 * searchDate
	 * 
	 * @param searchDate
	 * @param customerId
	 * @return type data in the system for the given customerId and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public ItCustomer findActiveDataBySearchDateAndCustomerId(LocalDateTime searchDate, Integer customerId)
			throws CoMaSwDataAccessException;	

	
	/**
	 * Return all data of the customer instance in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	 * @param searchDate date criteria to search
	 * @param customerId customer id criteria to search
	 * @param customerTypeId customer type id criteria to search
	 * @param statusId status id criteria to search
	 * @param identityCard identity card criteria to search
	 * @param contactPhone contact phone criteria to search
	 * @param givenName given name criteria to search
	 * @param firstSurname first surname to search
	 * @param secondSurname second surname to search
	 * @return customer list for the criteria
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItCustomer> findInstanceWithParameters(Optional<LocalDateTime> searchDate, Optional<Integer> customerId, Optional<Integer> customerTypeId, Optional<Integer> statusId, 
			Optional<String> identityCard, Optional<String> contactPhone, Optional<String> givenName, Optional<String> firstSurname, Optional<String> secondSurname) throws CoMaSwDataAccessException;
	
		
	
	/**
	 * Return all data of the customer instance view in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	 * @param searchDate date criteria to search
	 * @param customerId customer id criteria to search
	 * @param customerTypeId customer type id criteria to search
	 * @param statusId status id criteria to search
	 * @param identityCard identity card criteria to search
	 * @param contactPhone contact phone criteria to search
	 * @param givenName given name criteria to search
	 * @param firstSurname first surname to search
	 * @param secondSurname second surname to search
	 * @return customer list for the criteria
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwCustomerInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate, Optional<Integer> customerId, Optional<Integer> customerTypeId, Optional<Integer> statusId, 
			Optional<String> identityCard, Optional<String> contactPhone, Optional<String> givenName, Optional<String> firstSurname, Optional<String> secondSurname) throws CoMaSwDataAccessException;
	
			
	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given new customer. It creates a new
	 * Id and inserts into the id table.
	 * @param dataObject customer object to insert
	 * @return integer with the id of the account object
	 * @throws CoMaSwDataAccessException
	 */
	public Integer insertData(ItCustomer dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new customer record.
	 * 
	 * @param dataObject customer object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord(ItCustomer dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new historic data record for existing
	 * customer
	 * 
	 * @param dataObject customer object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord(ItCustomer dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given customer
	 * 
	 * @param dataObject customer object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItCustomer dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given customer
	 * 
	 * @param dataObject customer object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItCustomer dataObject) throws CoMaSwDataAccessException;

}
