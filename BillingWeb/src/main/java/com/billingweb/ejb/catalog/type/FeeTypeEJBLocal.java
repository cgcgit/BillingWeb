package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtFeeType;

@Local
public interface FeeTypeEJBLocal {
	


	/**
	 * Find all the fee type data stores in the system
	 * @return list of all fee type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtFeeType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the fee type data stores in the system for the given feeTypeId
	 * @param feeTypeId
	 * @return type data in the system for the given feeTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtFeeType findDataByFeeTypeId (Integer feeTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the fee type data stores in the system for the given code of fee type
	 * @param code
	 * @return fee type data in the system for the given code of fee type
	 * @throws BillingWebDataAccessException
	 */
	public CtFeeType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelId
	 * 
	 * @param applicationLevelId
	 * @return list of all promotion type data in the system for the given applicationLevelId
	 * @throws BillingWebDataAccessException
	 */
	public List<CtFeeType> findDataByApplicationLevelId(Integer applicationLevelId) throws BillingWebDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelCode
	 * 
	 * @param applicationCode
	 * @return list of all promotion type data in the system for the given applicationLevelCode
	 * @throws BillingWebDataAccessException
	 */
	public List<CtFeeType> findDataByApplicationLevelCode(String applicationLevelCode) throws BillingWebDataAccessException;


	
	
	/**
	 * Inserts into the system (database) the given fee type 
	 * @param dataObject fee type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtFeeType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given fee type 
	 * @param dataObject fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtFeeType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given fee type 
	 * @param dataObject fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtFeeType dataObject) throws BillingWebDataAccessException;

}
