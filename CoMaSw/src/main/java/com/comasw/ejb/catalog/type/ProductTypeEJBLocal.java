package com.comasw.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtProductType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ProductTypeEJBLocal {
	

	/**
	 * Find all the product type data stores in the system
	 * @return list of all product type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtProductType> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find the product type data stores in the system for the given productTypeId
	 * @param productTypeId
	 * @return product type data in the system for the given productTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtProductType findDataByProductTypeId (Integer productTypeId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the product type data stores in the system for the given code of product type
	 * @param code
	 * @return product type data in the system for the given code of product type
	 * @throws CoMaSwDataAccessException
	 */
	public CtProductType findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given product type 
	 * @param dataObject product type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtProductType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given product type 
	 * @param dataObject product type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtProductType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given product type 
	 * @param dataObject product type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtProductType dataObject) throws CoMaSwDataAccessException;
	

}
