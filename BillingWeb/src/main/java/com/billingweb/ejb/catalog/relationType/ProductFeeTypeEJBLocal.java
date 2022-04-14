package com.billingweb.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.VwProductFeeType;
import com.billingweb.model.tables.pojos.CtProdFeeType;
import com.billingweb.model.tables.pojos.CtFeeType;

@Local
public interface ProductFeeTypeEJBLocal {
	


	/**
	 * Find the fee type candidates for given product type id
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @return list of the fee type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the fee type candidates for given product type id and status code
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the product type id)
	 * @return list of the fee type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId, String statusCode) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the view of fee types related with the given product type id
	 * @param parentId parent type id of the relation to evaluated (product type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws BillingWebDataAccessException
	 */
	public List<VwProductFeeType> findRelatedEntityTypesView (Integer parentId) throws BillingWebDataAccessException;
	
	/**	 
	 * Find the product-fee type for the given product_service_type id
	 * @param entityRelationTypeId product_service_type_id	 * 
	 * @return product_service_type for the product-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtProdFeeType findEntityRelationType (Integer entityRelationTypeId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Find the product-fee type for the given product type id and fee type id
	 * @param parentId parent type id of the relation (product type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the product-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtProdFeeType findEntityRelationType (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	/**	 
	 * Find the view of product-fee type for the given product type id and fee type id
	 * @param parentId parent type id of the relation (product type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the product-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public VwProductFeeType findEntityRelationTypeView (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given product-fee type relation
	 * @param dataObject product-fee type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtProdFeeType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given product-fee type relation
	 * @param dataObject product-fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtProdFeeType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given product-fee type relation
	 * @param dataObject product-fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtProdFeeType dataObject) throws BillingWebDataAccessException;
	
}
