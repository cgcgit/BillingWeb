package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtCustomerType;

@Local
public interface CustomerTypeEJBLocal {
	

	/**
	 * Find all the customer type data stores in the system
	 * @return list of all customer type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtCustomerType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the customer type data stores in the system for the given customerTypeId
	 * @param customerTypeId
	 * @return customer type data in the system for the given customerTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtCustomerType findDataByCustomerTypeId (Integer customerTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the customer type data stores in the system for the given code of customer type
	 * @param code
	 * @return customer type data in the system for the given code of customer type
	 * @throws BillingWebDataAccessException
	 */
	public CtCustomerType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given customer type 
	 * @param dataObject customer type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtCustomerType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given customer type 
	 * @param dataObject customer type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtCustomerType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given customer type 
	 * @param dataObject customer type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtCustomerType dataObject) throws BillingWebDataAccessException;
	

}
