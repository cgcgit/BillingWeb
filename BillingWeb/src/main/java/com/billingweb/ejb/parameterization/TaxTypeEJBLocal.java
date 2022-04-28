package com.billingweb.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.PtTaxType;

@Local
public interface TaxTypeEJBLocal {
	

	/**
	 * Find all the tax type data stores in the system
	 * @return list of all tax type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <PtTaxType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the tax type data stores in the system for the given taxTypeId
	 * @param taxTypeId
	 * @return tax type data in the system for the given taxTypeId
	 * @throws BillingWebDataAccessException
	 */
	public PtTaxType findDataByTaxTypeId (Integer taxTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the tax type data stores in the system for the given code of tax type
	 * @param code
	 * @return tax type data in the system for the given code of tax type
	 * @throws BillingWebDataAccessException
	 */
	public PtTaxType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given tax type 
	 * @param dataObject tax type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (PtTaxType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given tax type 
	 * @param dataObject tax type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (PtTaxType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given tax type 
	 * @param dataObject tax type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (PtTaxType dataObject) throws BillingWebDataAccessException;
	

}
