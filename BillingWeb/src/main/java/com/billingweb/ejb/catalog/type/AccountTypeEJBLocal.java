package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtAccountType;

@Local
public interface AccountTypeEJBLocal {
	

	/**
	 * Find all the account type data stores in the system
	 * @return list of all account type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtAccountType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the account type data stores in the system for the given accountTypeId
	 * @param accountTypeId
	 * @return account type data in the system for the given accountTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtAccountType findDataByAccountTypeId (Integer accountTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the account type data stores in the system for the given code of account type
	 * @param code
	 * @return account type data in the system for the given code of account type
	 * @throws BillingWebDataAccessException
	 */
	public CtAccountType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given account type 
	 * @param dataObject account type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtAccountType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given account type 
	 * @param dataObject account type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtAccountType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given account type 
	 * @param dataObject account type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtAccountType dataObject) throws BillingWebDataAccessException;
	

}
