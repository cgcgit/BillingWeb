package com.billingweb.ejb.parameterization;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;

import java.util.List;

import com.billingweb.model.tables.pojos.PtApplicationLevel;


@Local
public interface ApplicationLevelEJBLocal {
	
	/**
	 * Find all the application level data stores in the system
	 * @return list of all application level data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <PtApplicationLevel> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the application level data stores in the system for the given applicationLevelId
	 * @param applicationLevelId
	 * @return list of all application level data in the system for the given applicationLevelId
	 * @throws BillingWebDataAccessException
	 */
	public List <PtApplicationLevel> findDataByApplicationLevelId (Integer applicationLevelId) throws BillingWebDataAccessException;
	
	/**
	 * Find the application level data stores in the system for the given code of application level
	 * @param code
	 * @return list of all application level data in the system for the given code of application level
	 * @throws BillingWebDataAccessException
	 */
	public List <PtApplicationLevel> findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given application level 
	 * @param dataObject application level object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (PtApplicationLevel dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given application level 
	 * @param dataObject application level object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (PtApplicationLevel dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given application level 
	 * @param dataObject application level object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (PtApplicationLevel dataObject) throws BillingWebDataAccessException;
	
	
	
}
