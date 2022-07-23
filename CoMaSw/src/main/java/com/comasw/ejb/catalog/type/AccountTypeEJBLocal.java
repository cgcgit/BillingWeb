package com.comasw.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtAccountType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface AccountTypeEJBLocal {
	

	/**
	 * Find all the account type data stores in the system
	 * @return list of all account type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtAccountType> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find the account type data stores in the system for the given accountTypeId
	 * @param accountTypeId
	 * @return account type data in the system for the given accountTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtAccountType findDataByAccountTypeId (Integer accountTypeId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the account type data stores in the system for the given code of account type
	 * @param code
	 * @return account type data in the system for the given code of account type
	 * @throws CoMaSwDataAccessException
	 */
	public CtAccountType findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given account type 
	 * @param dataObject account type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtAccountType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given account type 
	 * @param dataObject account type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtAccountType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given account type 
	 * @param dataObject account type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtAccountType dataObject) throws CoMaSwDataAccessException;
	

}
