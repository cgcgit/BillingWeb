package com.billingweb.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtPromoServType;
import com.billingweb.model.tables.pojos.CtPromotionType;
import com.billingweb.model.tables.pojos.VwPromotionServiceType;

@Local
public interface ServicePromotionTypeEJBLocal {



	/**
	 * Find the promotion type candidates to the given service type id
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the promotion type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtPromotionType> findEntityTypeCandidates (Integer parentId) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the promotion type candidates for given service type id and status code
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the service type id)
	 * @return list of the promotion type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtPromotionType> findEntityTypeCandidates (Integer parentId, String statusCode) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the view of promotion types related with the given service type id
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the promotion types related with the given parent type id
	 * @throws BillingWebDataAccessException
	 */
	public List<VwPromotionServiceType> findRelatedEntityTypesView (Integer parentId) throws BillingWebDataAccessException;
	
	/**	 
	 * Find the service-promotion type for the given product_service_type id
	 * @param entityRelationTypeId product_service_type_id	 * 
	 * @return product_service_type for the service-promotion type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoServType findEntityRelationType (Integer entityRelationTypeId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Find the service-promotion type for the given service type id and promotion type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (promotion type id)
	 * @return product_service_type for the service-promotion type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtPromoServType findEntityRelationType (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	/**	 
	 * Find the view of service-promotion type for the given service type id and promotion type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (promotion type id)
	 * @return product_service_type for the service-promotion type relation
	 * @throws BillingWebDataAccessException
	 */
	public VwPromotionServiceType findEntityRelationTypeView (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given service-promotion type relation
	 * @param dataObject service-promotion type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtPromoServType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given service-promotion type relation
	 * @param dataObject service-promotion type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtPromoServType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given service-promotion type relation
	 * @param dataObject service-promotion type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtPromoServType dataObject) throws BillingWebDataAccessException;
	

}
