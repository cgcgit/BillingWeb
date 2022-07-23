package com.comasw.ejb.catalog.relationType;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;


import com.comasw.model.tables.pojos.CtPromoServType;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.VwPromotionServiceType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ServicePromotionTypeEJBLocal {



	/**
	 * Find the promotion type candidates for given service type id. We select the candidate with the minimum start date.
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the promotion type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtPromotionType> findEntityTypeCandidates (Integer parentId) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the promotion type candidates for given service type id and status code. We select the candidate with the minimum start date.
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the service type id)
	 * @return list of the promotion type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtPromotionType> findEntityTypeCandidates (Integer parentId, String statusCode) throws CoMaSwDataAccessException;
	
	
	
	/**
	 * Find the view of all (historic data) promotion types related with the given service type id
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the promotion types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromotionServiceType> findHistoricRelatedEntityTypesView (Integer parentId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the view of promotion types related with the given service type id for the given date
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the promotion types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromotionServiceType> findRelatedEntityTypesByDateView (Integer parentId, LocalDateTime searchDate) throws CoMaSwDataAccessException;
	
	
	/**	 
	 * Find the service-promotion type for the given service_promotion_type id
	 * @param entityRelationTypeId service_promotion_type_id	 
	 * @return service_promotion_type for the service-promotion type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoServType findEntityRelationType (Integer entityRelationTypeId) throws CoMaSwDataAccessException;
	
	
	
	/**	 
	 * Find the service-promotion type for the given service type id and promotion type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (promotion type id)
	 * @return service_promotion_type for the service-promotion type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoServType findEntityRelationType (Integer parentId, Integer childId) throws CoMaSwDataAccessException;
	
	
	/**	 
	 * Find the view of service-promotion type for the given service type id and promotion type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (promotion type id)
	 * @return service_promotion_type for the service-promotion type relation
	 * @throws CoMaSwDataAccessException
	 */
	public VwPromotionServiceType findEntityRelationTypeView (Integer parentId, Integer childId) throws CoMaSwDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given service-promotion type relation
	 * @param dataObject service-promotion type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtPromoServType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given service-promotion type relation
	 * @param dataObject service-promotion type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtPromoServType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given service-promotion type relation
	 * @param dataObject service-promotion type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtPromoServType dataObject) throws CoMaSwDataAccessException;
	


}
