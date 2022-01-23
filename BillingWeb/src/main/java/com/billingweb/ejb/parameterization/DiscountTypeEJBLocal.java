package com.billingweb.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.PtDiscountType;

@Local
public interface DiscountTypeEJBLocal {

	/**
	 * Find all the discount type data stores in the system
	 * @return list of all discount type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <PtDiscountType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the discount type data stores in the system for the given discounTypeId
	 * @param discounTypeId
	 * @return list of all discount type data in the system for the given discounTypeId
	 * @throws BillingWebDataAccessException
	 */
	public List <PtDiscountType> findDataByDiscounTypeId (Integer discounTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the discount type data stores in the system for the given code of status
	 * @param code
	 * @return list of all discount type data in the system for the given code of status
	 * @throws BillingWebDataAccessException
	 */
	public List <PtDiscountType> findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given status 
	 * @param dataObject status object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (PtDiscountType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (PtDiscountType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (PtDiscountType dataObject) throws BillingWebDataAccessException;
	
	
}
