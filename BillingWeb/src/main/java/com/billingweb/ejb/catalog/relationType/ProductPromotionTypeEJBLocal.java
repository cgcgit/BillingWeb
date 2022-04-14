package com.billingweb.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.VwPromotionProductType;
import com.billingweb.model.tables.pojos.CtPromoProdType;
import com.billingweb.model.tables.pojos.CtPromotionType;

@Local
public interface ProductPromotionTypeEJBLocal {
	


	/**
	 * Find the promotion type candidates to the given product type id
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @return list of the promotion type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtPromotionType> findEntityTypeCandidates (Integer parentId) throws BillingWebDataAccessException;
	
	/**
	 * Find the promotion type candidates for given product type id and status code
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the product type id)
	 * @return list of the promotion type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtPromotionType> findEntityTypeCandidates (Integer parentId, String statusCode) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the view of promotion types related with the given product type id
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @return list of the promotion types related with the given parent type id
	 * @throws BillingWebDataAccessException
	 */
	public List<VwPromotionProductType> findRelatedEntityTypesView (Integer parentId) throws BillingWebDataAccessException;
	
	/**	 
	 * Find the product-promotion type for the given product_service_type id
	 * @param entityRelationTypeId product_service_type_id	 * 
	 * @return product_service_type for the product-promotion type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoProdType findEntityRelationType (Integer entityRelationTypeId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Find the product-promotion type for the given product type id and promotion type id
	 * @param parentId parent type id of the relation (product type id)
	 * @param childId child type id of the relation (promotion type id)
	 * @return product_service_type for the product-promotion type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoProdType findEntityRelationType (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	/**	 
	 * Find the view of product-promotion type for the given product type id and promotion type id
	 * @param parentId parent type id of the relation (product type id)
	 * @param childId child type id of the relation (promotion type id)
	 * @return product_service_type for the product-promotion type relation
	 * @throws BillingWebDataAccessException
	 */
	public VwPromotionProductType findEntityRelationTypeView (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given product-promotion type relation
	 * @param dataObject product-promotion type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtPromoProdType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given product-promotion type relation
	 * @param dataObject product-promotion type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtPromoProdType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given product-promotion type relation
	 * @param dataObject product-promotion type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtPromoProdType dataObject) throws BillingWebDataAccessException;
	
}
