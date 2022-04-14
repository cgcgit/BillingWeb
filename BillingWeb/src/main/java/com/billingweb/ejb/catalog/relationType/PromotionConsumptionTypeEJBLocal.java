package com.billingweb.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtConsumptionType;
import com.billingweb.model.tables.pojos.CtPromoConsumTypeDiscount;
import com.billingweb.model.tables.pojos.VwPromoConsumTypeDiscount;

@Local
public interface PromotionConsumptionTypeEJBLocal {


	/**
	 * Find the consumption type candidates to the given promotion type id
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @return list of the consumption type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtConsumptionType> findEntityTypeCandidates (Integer parentId) throws BillingWebDataAccessException;
	
	/**
	 * Find the consumption type candidates for given promotion type id and status code
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the promotion type id)
	 * @return list of the consumption type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtConsumptionType> findEntityTypeCandidates (Integer parentId, String statusCode) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the view of consumption types related with the given promotion type id
	 * @param parentId parent type id of the relation to evaluated (promotion type id)
	 * @return list of the consumption types related with the given parent type id
	 * @throws BillingWebDataAccessException
	 */
	public List<VwPromoConsumTypeDiscount> findRelatedEntityTypesView (Integer parentId) throws BillingWebDataAccessException;
	
	/**	 
	 * Find the promotion-consumption type for the given product_service_type id
	 * @param entityRelationTypeId product_service_type_id	 * 
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoConsumTypeDiscount findEntityRelationType (Integer entityRelationTypeId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Find the promotion-consumption type for the given promotion type id and consumption type id
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId child type id of the relation (consumption type id)
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoConsumTypeDiscount findEntityRelationType (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	/**	 
	 * Find the view of promotion-consumption type for the given promotion type id and consumption type id
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId child type id of the relation (consumption type id)
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws BillingWebDataAccessException
	 */
	public VwPromoConsumTypeDiscount findEntityRelationTypeView (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given promotion-consumption type relation
	 * @param dataObject promotion-consumption type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtPromoConsumTypeDiscount dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given promotion-consumption type relation
	 * @param dataObject promotion-consumption type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtPromoConsumTypeDiscount dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given promotion-consumption type relation
	 * @param dataObject promotion-consumption type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtPromoConsumTypeDiscount dataObject) throws BillingWebDataAccessException;
	
}

