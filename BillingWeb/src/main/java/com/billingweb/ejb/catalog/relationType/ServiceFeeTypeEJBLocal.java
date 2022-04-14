package com.billingweb.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtFeeType;
import com.billingweb.model.tables.pojos.CtServFeeType;
import com.billingweb.model.tables.pojos.VwServiceFeeType;

@Local
public interface ServiceFeeTypeEJBLocal {
	

	/**
	 * Find the fee type candidates to the given service type id
	 * @param parentId parent type id of the relation to evaluated (fee type id)
	 * @return list of the fee type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List<CtFeeType> findEntityTypeCandidates (Integer parentId) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the fee type candidates for given service type id and status code
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the service type id)
	 * @return list of the service type candidates
	 * @throws BillingWebDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId, String statusCode) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the view of fee types related with the given fee type id
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws BillingWebDataAccessException
	 */
	public List<VwServiceFeeType> findRelatedEntityTypesView (Integer parentId) throws BillingWebDataAccessException;
	
	/**	 
	 * Find the service-fee type for the given product_service_type id
	 * @param entityRelationTypeId product_service_type_id	 * 
	 * @return product_service_type for the service-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtServFeeType findEntityRelationType (Integer entityRelationTypeId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Find the service-fee type for the given service type id and fee type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the service-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public CtServFeeType findEntityRelationType (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	/**	 
	 * Find the view of service-fee type for the given service type id and fee type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return product_service_type for the service-fee type relation
	 * @throws BillingWebDataAccessException
	 */
	public VwServiceFeeType findEntityRelationTypeView (Integer parentId, Integer childId) throws BillingWebDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given service-fee type relation
	 * @param dataObject service-fee type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtServFeeType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given service-fee type relation
	 * @param dataObject service-fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtServFeeType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given service-fee type relation
	 * @param dataObject service-fee type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtServFeeType dataObject) throws BillingWebDataAccessException;


}
