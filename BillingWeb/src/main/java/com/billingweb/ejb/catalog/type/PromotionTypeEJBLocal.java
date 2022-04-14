package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtPromotionType;

@Local
public interface PromotionTypeEJBLocal {

	/**
	 * Find all the promotion type data stores in the system
	 * 
	 * @return list of all promotion type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List<CtPromotionType> findAllData() throws BillingWebDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given
	 * promotionTypeId
	 * 
	 * @param promotionTypeId
	 * @return promotion type data in the system for the given promotionTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtPromotionType findDataByPromotionTypeId(Integer promotionTypeId) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the promotion type data stores in the system for the given code of
	 * promotion type
	 * 
	 * @param code
	 * @return promotion type data in the system for the given code of
	 *         promotion type
	 * @throws BillingWebDataAccessException
	 */
	public CtPromotionType findDataByCode(String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelId
	 * 
	 * @param applicationLevelId
	 * @return list of all promotion type data in the system for the given applicationLevelId
	 * @throws BillingWebDataAccessException
	 */
	public List<CtPromotionType> findDataByApplicationLevelId(Integer applicationLevelId) throws BillingWebDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelCode
	 * 
	 * @param applicationCode
	 * @return list of all promotion type data in the system for the given applicationLevelCode
	 * @throws BillingWebDataAccessException
	 */
	public List<CtPromotionType> findDataByApplicationLevelCode(String applicationLevelCode) throws BillingWebDataAccessException;


	
	/**
	 * Inserts into the system (database) the given promotion type
	 * 
	 * @param dataObject promotion type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData(CtPromotionType dataObject) throws BillingWebDataAccessException;

	/**
	 * Updates into the system (database) the given promotion type
	 * 
	 * @param dataObject promotion type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData(CtPromotionType dataObject) throws BillingWebDataAccessException;

	/**
	 * Deletes into the system (database) the given promotion type
	 * 
	 * @param dataObject promotion type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData(CtPromotionType dataObject) throws BillingWebDataAccessException;

}
