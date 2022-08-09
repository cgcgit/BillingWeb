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
