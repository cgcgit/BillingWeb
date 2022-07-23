package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtTaxType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface TaxTypeEJBLocal {
	

	/**
	 * Find all the tax type data stores in the system
	 * @return list of all tax type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <PtTaxType> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find the tax type data stores in the system for the given taxTypeId
	 * @param taxTypeId
	 * @return tax type data in the system for the given taxTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtTaxType findDataByTaxTypeId (Integer taxTypeId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the tax type data stores in the system for the given code of tax type
	 * @param code
	 * @return tax type data in the system for the given code of tax type
	 * @throws CoMaSwDataAccessException
	 */
	public PtTaxType findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given tax type 
	 * @param dataObject tax type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (PtTaxType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given tax type 
	 * @param dataObject tax type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (PtTaxType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given tax type 
	 * @param dataObject tax type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (PtTaxType dataObject) throws CoMaSwDataAccessException;
	

}
