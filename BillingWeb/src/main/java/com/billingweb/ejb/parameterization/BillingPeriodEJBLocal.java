package com.billingweb.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.PtBillingPeriod;

@Local
public interface BillingPeriodEJBLocal {
	

	/**
	 * Find all the billing period data stores in the system
	 * @return list of all billing period data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <PtBillingPeriod> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the billing period data stores in the system for the given billingPeriodId
	 * @param billingPeriodId
	 * @return list of all billing period data in the system for the given billingPeriodId
	 * @throws BillingWebDataAccessException
	 */
	public List <PtBillingPeriod> findDataByBillingPeriodId (Integer billingPeriodId) throws BillingWebDataAccessException;
	
	/**
	 * Find the billing period data stores in the system for the given code of status
	 * @param code
	 * @return list of all billing period data in the system for the given code of status
	 * @throws BillingWebDataAccessException
	 */
	public List <PtBillingPeriod> findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given status 
	 * @param dataObject status object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (PtBillingPeriod dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (PtBillingPeriod dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (PtBillingPeriod dataObject) throws BillingWebDataAccessException;

}
