package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtProductType;

@Local
public interface ProductTypeEJBLocal {
	

	/**
	 * Find all the product type data stores in the system
	 * @return list of all product type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtProductType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the product type data stores in the system for the given productTypeId
	 * @param productTypeId
	 * @return product type data in the system for the given productTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtProductType findDataByProductTypeId (Integer productTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the product type data stores in the system for the given code of product type
	 * @param code
	 * @return product type data in the system for the given code of product type
	 * @throws BillingWebDataAccessException
	 */
	public CtProductType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given product type 
	 * @param dataObject product type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtProductType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given product type 
	 * @param dataObject product type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtProductType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given product type 
	 * @param dataObject product type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtProductType dataObject) throws BillingWebDataAccessException;
	

}
