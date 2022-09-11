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
import com.comasw.model.tables.pojos.ItAccount;
import com.comasw.model.tables.pojos.VwAccountInstance;

@Local
public interface AccountEJBLocal {

	/**
	 * Find all the account data stores in the system
	 * 
	 * @return list of all account data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the account data stores in the system for the given accountId
	 * 
	 * @param accountId
	 * @return type data in the system for the given accountId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findDataByAccountId(Integer accountId) throws CoMaSwDataAccessException;

	/**
	 * Find the account data stores in the system for the given customerId
	 * 
	 * @param customerId
	 * @return type data in the system for the given customerId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findDataByCustomerId(Integer customerId) throws CoMaSwDataAccessException;

	/**
	 * Find the account data stores in the system for the given contractNr
	 * 
	 * @param contractNr
	 * @return type data in the system for the given contractNr
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findDataByContractNr(String contractNr) throws CoMaSwDataAccessException;

	/**
	 * Find the account data stores in the system for the given searchDate
	 * 
	 * @param searchDate
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findDataBySearchDate(LocalDateTime searchDate) throws CoMaSwDataAccessException;

	/**
	 * Find the account data stores in the system for the given accountId and
	 * searchDate
	 * 
	 * @param searchDate
	 * @param accountId
	 * @return type data in the system for the given accountId and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public ItAccount findDataBySearchDateAndAccountId(LocalDateTime searchDate, Integer accountId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the account data stores in the system for the given contractNr and
	 * searchDate
	 * 
	 * @param searchDate
	 * @param contractNr
	 * @return type data in the system for the given contractNr and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public ItAccount findDataBySearchDateAndContractNr(LocalDateTime searchDate, String contractNr)
			throws CoMaSwDataAccessException;

	/**
	 * Find the account data stores in the system for the given customerId and
	 * searchDate
	 * 
	 * @param searchDate
	 * @param customerId
	 * @return type data in the system for the given customerId and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public ItAccount findDataBySearchDateAndCustomerId(LocalDateTime searchDate, Integer customerId)
			throws CoMaSwDataAccessException;

	/**
	 * Return all data of the account instance in the system for a giving search
	 * criteria. The parameters are optional, but there must be at least one
	 * parameter specified.
	 * 
	 * @param searchDate      date criteria to search
	 * @param accountId       account id criteria to search
	 * @param accountTypeId   account type id criteria to search
	 * @param contractNr      contract number criteria to search
	 * @param customerId      customer id criteria to search
	 * @param accountStatusId status id criteria to search
	 * @param accountIdCard   account identity card criteria to search
	 * @param customerIdCard  customer identity card criteria to search
	 * @return account list for the criteria
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findInstanceWithParameters(Optional<LocalDateTime> searchDate, Optional<Integer> accountId,
			Optional<Integer> accountTypeId, Optional<String> contractNr,
			Optional<Integer> customerId, Optional<Integer> accountStatusId, Optional<String> accountIdCard,
			Optional<String> customerIdCard) throws CoMaSwDataAccessException;

	
	/**
	 * Return all data of the account instance view in the system for a giving search
	 * criteria. The parameters are optional, but there must be at least one
	 * parameter specified.
	 * 
	 * @param searchDate      date criteria to search
	 * @param contractNr      contract number criteria to search
	 * @param accountId       account id criteria to search
	 * @param accountTypeId   account type id criteria to search
	 * @param accountIdCard   account identity card criteria to search
	 * @param customerId      customer id criteria to search
	 * @param customerTypeId  customer type id criteria to search
	 * @param customerIdCard  customer identity card criteria to search
	 * @return account list for the criteria
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwAccountInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate, 
			Optional<String> contractNr,
			Optional<Integer> accountId,
			Optional<Integer> accountTypeId, 
			Optional<String> accountIdCard,
			Optional<Integer> customerId, 
			Optional<Integer> customerTypeId,
			Optional<String> customerIdCard) throws CoMaSwDataAccessException;
	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given new account. It creates a new Id
	 * and inserts into the id table.
	 * 
	 * @param dataObject account object to insert
	 * @return integer with the id of the account object
	 * @throws CoMaSwDataAccessException
	 */
	public Integer insertData(ItAccount dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new account record.
	 * 
	 * @param dataObject account object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord(ItAccount dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new historic data record for existing
	 * account
	 * 
	 * @param dataObject account object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord(ItAccount dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given account
	 * 
	 * @param dataObject account object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItAccount dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given account
	 * 
	 * @param dataObject account object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItAccount dataObject) throws CoMaSwDataAccessException;

}
