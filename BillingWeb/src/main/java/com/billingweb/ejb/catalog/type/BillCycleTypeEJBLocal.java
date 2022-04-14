package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtBillCycleType;

@Local
public interface BillCycleTypeEJBLocal {
	

	/**
	 * Find all the bill cycle type data stores in the system
	 * @return list of all bill cycle type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtBillCycleType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find all the ordinary bill cycle type data stores in the system
	 * @return list of all ordinary bill cycle type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtBillCycleType> findOrdinaryCycleType () throws BillingWebDataAccessException;
	
	
	/**
	 * Find all the corrective bill cycle type data stores in the system
	 * @return list of all corrective bill cycle type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtBillCycleType> findCorrectiveCycleType () throws BillingWebDataAccessException;


	
	/**
	 * Find the bill cycle type data stores in the system for the given billCycleTypeId
	 * @param billCycleTypeId
	 * @return bill cycle type data in the system for the given billCycleTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtBillCycleType findDataByBillCycleTypeId (Integer billCycleTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the bill cycle type data stores in the system for the given code of bill cycle type
	 * @param code
	 * @return bill cycle type data in the system for the given code of bill cycle type
	 * @throws BillingWebDataAccessException
	 */
	public CtBillCycleType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given bill cycle type 
	 * @param dataObject bill cycle type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtBillCycleType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given bill cycle type 
	 * @param dataObject bill cycle type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtBillCycleType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given bill cycle type 
	 * @param dataObject bill cycle type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtBillCycleType dataObject) throws BillingWebDataAccessException;
	

}
