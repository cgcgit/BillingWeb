package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtBillingPeriod;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface BillingPeriodEJBLocal {

	/**
	 * Find all the billing period data stores in the system
	 * 
	 * @return list of all billing period data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtBillingPeriod> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the billing period data stores in the system for the given
	 * billingPeriodId
	 * 
	 * @param billingPeriodId
	 * @return billing period data in the system for the given billingPeriodId
	 * @throws CoMaSwDataAccessException
	 */
	public PtBillingPeriod findDataByBillingPeriodId(Integer billingPeriodId) throws CoMaSwDataAccessException;

	/**
	 * Find the billing period data stores in the system for the given code of
	 * status
	 * 
	 * @param code
	 * @return billing period data in the system for the given code of status
	 * @throws CoMaSwDataAccessException
	 */
	public PtBillingPeriod findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given status
	 * 
	 * @param dataObject status object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtBillingPeriod dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtBillingPeriod dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtBillingPeriod dataObject) throws CoMaSwDataAccessException;

}
