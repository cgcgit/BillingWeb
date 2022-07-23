package com.comasw.ejb.catalog.relationType;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtPromoFeeTypeDiscount;
import com.comasw.model.tables.pojos.VwPromotionFeeTypeDiscount;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface PromotionFeeTypeDiscountEJBLocal {
	


	/**
	 * Find the fee type candidates to the given promotion type id
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @return list of the fee type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the fee type candidates for given promotion type id and status code
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the promotion type id)
	 * @return list of the fee type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId, String statusCode) throws CoMaSwDataAccessException;
	

	/**
	 * Find the view of all (historic data) fee types related with the given product type id
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromotionFeeTypeDiscount> findHistoricRelatedEntityTypesView (Integer parentId) throws CoMaSwDataAccessException;
	
	
	
	/**
	 * Find the view of fee types related with the given product type id for the given date
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromotionFeeTypeDiscount> findRelatedEntityTypesByDateView (Integer parentId, LocalDateTime searchDate) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the view of fee types related with the given promotion type id
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromotionFeeTypeDiscount> findRelatedEntityTypesView (Integer parentId) throws CoMaSwDataAccessException;
	
	/**	 
	 * Find the promotion-fee type for the given product_service_type id
	 * @param entityRelationTypeId product_service_type_id	 * 
	 * @return product_service_type for the promotion-fee type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoFeeTypeDiscount findEntityRelationType (Integer entityRelationTypeId) throws CoMaSwDataAccessException;
	
	
	
	/**	 
	 * Find the promotion-fee type for the given promotion type id and fee type id
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the promotion-fee type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoFeeTypeDiscount findEntityRelationType (Integer parentId, Integer childId) throws CoMaSwDataAccessException;
	
	
	/**	 
	 * Find the view of promotion-fee type for the given promotion type id and fee type id
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the promotion-fee type relation
	 * @throws CoMaSwDataAccessException
	 */
	public VwPromotionFeeTypeDiscount findEntityRelationTypeView (Integer parentId, Integer childId) throws CoMaSwDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given promotion-fee type relation
	 * @param dataObject promotion-fee type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtPromoFeeTypeDiscount dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given promotion-fee type relation
	 * @param dataObject promotion-fee type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtPromoFeeTypeDiscount dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given promotion-fee type relation
	 * @param dataObject promotion-fee type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtPromoFeeTypeDiscount dataObject) throws CoMaSwDataAccessException;
	
}

