package com.billingweb.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.PtEntityType;


@Local
public interface EntityTypeEJBLocal {
	
	/**
	 * Find all the entity type data stores in the system
	 * @return list of all entity type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <PtEntityType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the entity type data stores in the system for the given entityTypeId
	 * @param entityTypeId
	 * @return list of all entity type data in the system for the given entityTypeId
	 * @throws BillingWebDataAccessException
	 */
	public List <PtEntityType> findDataByEntityTypeId (Integer entityTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the entity type data stores in the system for the given code of entity type
	 * @param code
	 * @return list of all entity type data in the system for the given code of entity type
	 * @throws BillingWebDataAccessException
	 */
	public List <PtEntityType> findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given entity type 
	 * @param dataObject entity type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (PtEntityType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given entity type 
	 * @param dataObject entity type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (PtEntityType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given entity type 
	 * @param dataObject entity type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (PtEntityType dataObject) throws BillingWebDataAccessException;
	

}
