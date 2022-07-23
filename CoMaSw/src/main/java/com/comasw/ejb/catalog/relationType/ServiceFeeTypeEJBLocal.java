package com.comasw.ejb.catalog.relationType;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtServFeeType;
import com.comasw.model.tables.pojos.VwServiceFeeType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ServiceFeeTypeEJBLocal {
	


	/**
	 * Find the fee type candidates for given service type id. We select the candidate with the minimum start date.
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the fee type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the fee type candidates for given service type id and status code. We select the candidate with the minimum start date.
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @param statusCode status code of the entity's relation to evaluated (status code of the service type id)
	 * @return list of the fee type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtFeeType> findEntityTypeCandidates (Integer parentId, String statusCode) throws CoMaSwDataAccessException;
	
	
	
	/**
	 * Find the view of all (historic data) fee types related with the given service type id
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwServiceFeeType> findHistoricRelatedEntityTypesView (Integer parentId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the view of fee types related with the given service type id for the given date
	 * @param parentId parent type id of the relation to evaluated (service type id)
	 * @return list of the fee types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwServiceFeeType> findRelatedEntityTypesByDateView (Integer parentId, LocalDateTime searchDate) throws CoMaSwDataAccessException;
	
	
	/**	 
	 * Find the service-fee type for the given service_fee_type id
	 * @param entityRelationTypeId service_fee_type_id	 
	 * @return service_fee_type for the service-fee type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtServFeeType findEntityRelationType (Integer entityRelationTypeId) throws CoMaSwDataAccessException;
	
	
	
	/**	 
	 * Find the service-fee type for the given service type id and fee type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return service_fee_type for the service-fee type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtServFeeType findEntityRelationType (Integer parentId, Integer childId) throws CoMaSwDataAccessException;
	
	
	/**	 
	 * Find the view of service-fee type for the given service type id and fee type id
	 * @param parentId parent type id of the relation (service type id)
	 * @param childId child type id of the relation (fee type id)
	 * @return service_fee_type for the service-fee type relation
	 * @throws CoMaSwDataAccessException
	 */
	public VwServiceFeeType findEntityRelationTypeView (Integer parentId, Integer childId) throws CoMaSwDataAccessException;
	
	
	
	/**	 
	 * Inserts into the system (database) the given service-fee type relation
	 * @param dataObject service-fee type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtServFeeType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given service-fee type relation
	 * @param dataObject service-fee type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtServFeeType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given service-fee type relation
	 * @param dataObject service-fee type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtServFeeType dataObject) throws CoMaSwDataAccessException;
	

}
