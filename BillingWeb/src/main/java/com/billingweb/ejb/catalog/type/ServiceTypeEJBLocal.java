package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtServiceType;

@Local
public interface ServiceTypeEJBLocal {
	

	/**
	 * Find all the service type data stores in the system
	 * @return list of all service type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtServiceType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the service type data stores in the system for the given serviceTypeId
	 * @param serviceTypeId
	 * @return list of all service type data in the system for the given serviceTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtServiceType findDataByServiceTypeId (Integer serviceTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the service type data stores in the system for the given code of service type
	 * @param code
	 * @return list of all service type data in the system for the given code of service type
	 * @throws BillingWebDataAccessException
	 */
	public CtServiceType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given service type 
	 * @param dataObject service type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtServiceType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given service type 
	 * @param dataObject service type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtServiceType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given service type 
	 * @param dataObject service type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtServiceType dataObject) throws BillingWebDataAccessException;
	

}
