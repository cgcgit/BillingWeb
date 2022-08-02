package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtDiscountType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface DiscountTypeEJBLocal {

	/**
	 * Find all the discount type data stores in the system
	 * 
	 * @return list of all discount type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtDiscountType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the discount type data stores in the system for the given discountTypeId
	 * 
	 * @param discountTypeId
	 * @return discount type data in the system for the given discountTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtDiscountType findDataByDiscounTypeId(Integer discountTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the discount type data stores in the system for the given code of status
	 * 
	 * @param code
	 * @return discount type data in the system for the given code of status
	 * @throws CoMaSwDataAccessException
	 */
	public PtDiscountType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given status
	 * 
	 * @param dataObject status object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtDiscountType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtDiscountType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtDiscountType dataObject) throws CoMaSwDataAccessException;

}
