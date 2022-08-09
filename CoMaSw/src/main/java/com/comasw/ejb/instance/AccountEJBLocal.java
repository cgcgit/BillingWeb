package com.comasw.ejb.instance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.ItAccount;

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
	 * Find the account data stores in the system for the given contractId
	 * 
	 * @param contractId
	 * @return type data in the system for the given contractId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findDataByContactId(Integer contractId) throws CoMaSwDataAccessException;
	

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
	 * Find the account data stores in the system for the given contractId and
	 * searchDate
	 * 
	 * @param searchDate
	 * @param contractId
	 * @return type data in the system for the given contractId and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public ItAccount findDataBySearchDateAndContractId(LocalDateTime searchDate, Integer contractId)
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
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	 * @param searchDate date criteria to search
	 * @param accountId account id criteria to search
	 * @param accountTypeId account type id criteria to search
	 * @param contractId contract id criteria to search
	 * @param customerId customer id criteria to search 
	 * @param accountStatusId status id criteria to search
	 * @param accountIdCard account identity card criteria to search
	 * @param customerIdCard customer identity card criteria to search
	 * @return account list for the criteria
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItAccount> findInstanceWithParameters(Optional<LocalDateTime> searchDate,
			Optional<Integer> accountId, Optional<Integer> accountTypeId, Optional<Integer> contractId,
			Optional<Integer> customerId, Optional<Integer> accountStatusId, 
			Optional<String> accountIdCard,
			Optional<String> customerIdCard) throws CoMaSwDataAccessException;
	
		
	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given new account. It creates a new
	 * Id and inserts into the id table.
	 * 
	 * @param dataObject account object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(ItAccount dataObject) throws CoMaSwDataAccessException;

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
