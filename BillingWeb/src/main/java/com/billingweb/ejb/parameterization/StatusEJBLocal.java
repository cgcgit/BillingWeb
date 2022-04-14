package com.billingweb.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.PtStatus;

@Local
public interface StatusEJBLocal {

	/**
	 * Find all the status data stores in the system
	 * @return list of all status data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <PtStatus> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the status data stores in the system for the given statusId
	 * @param statusId
	 * @return status data in the system for the given statusId
	 * @throws BillingWebDataAccessException
	 */
	public PtStatus findDataByStatusId (Integer statusId) throws BillingWebDataAccessException;
	
	/**
	 * Find the status data stores in the system for the given code of status
	 * @param code
	 * @return status data in the system for the given code of status
	 * @throws BillingWebDataAccessException
	 */
	public PtStatus findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given status 
	 * @param dataObject status object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (PtStatus dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (PtStatus dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (PtStatus dataObject) throws BillingWebDataAccessException;
	
}
