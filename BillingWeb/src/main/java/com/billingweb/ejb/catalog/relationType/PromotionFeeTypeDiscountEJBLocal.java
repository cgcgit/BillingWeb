package com.billingweb.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtFeeType;
import com.billingweb.model.tables.pojos.CtPromoFeeTypeDiscount;
import com.billingweb.model.tables.pojos.VwPromotionFeeTypeDiscount;

@Local
public interface PromotionFeeTypeDiscountEJBLocal {
	


	/**
	 * Find the fee type candidates to the given promotion type id
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @return list of the fee type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId) throws BillingWebDataAccessException;
	
	/**
	 * Find the fee type candidates for given promotion type id and status code
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the promotion type id)
	 * @return list of the fee type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId, String statusCode) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the view of fee types related with the given promotion type id
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws BillingWebDataAccessException
	 */
	public List<VwPromotionFeeTypeDiscount> findRelatedEntityTypesView (Integer parentId) throws BillingWebDataAccessException;
	
	/**	 
	 * Find the promotion-fee type for the given product_service_type id
	 * @param entityRelationTypeId product_service_type_id	 * 
	 * @return product_service_type for the promotion-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoFeeTypeDiscount findEntityRelationType (Integer entityRelationTypeId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Find the promotion-fee type for the given promotion type id and fee type id
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the promotion-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoFeeTypeDiscount findEntityRelationType (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	/**	 
	 * Find the view of promotion-fee type for the given promotion type id and fee type id
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the promotion-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public VwPromotionFeeTypeDiscount findEntityRelationTypeView (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given promotion-fee type relation
	 * @param dataObject promotion-fee type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtPromoFeeTypeDiscount dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given promotion-fee type relation
	 * @param dataObject promotion-fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtPromoFeeTypeDiscount dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given promotion-fee type relation
	 * @param dataObject promotion-fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtPromoFeeTypeDiscount dataObject) throws BillingWebDataAccessException;
	
}

