package com.comasw.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.VwProductServiceType;
import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.CtServiceType;
import com.comasw.model.tables.pojos.CtProdServType;

@Local
public interface ProductServiceTypeEJBLocal {

	/**
	 * Find the service type candidates to the given product type id
	 * 
	 * @param parentTypeId parent type id of the relation to evaluated (product type id)
	 * @return list of the service type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtServiceType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the service type related for given product type id and status code
	 * 
	 * @param parentTypeId   parent type id of the relation to evaluated (product type
	 *                   id)
	 * @param statusCode status code of the entity's relation to evaluated (status
	 *                   code of the product type id)
	 * @return list of the service type related
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtServiceType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException;
	
	/**
	 * Find the service type candidates for given product type id and status code
	 * 
	 * @param parentTypeId   parent type id of the relation to evaluated (product type
	 *                   id)
	 * @param statusCode status code of the entity's relation to evaluated (status
	 *                   code of the product type id)
	 * @return list of the service type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtServiceType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException;

	/**
	 * Find the view of service types related with the given product type id
	 * 
	 * @param parentTypeId parent type id of the relation to evaluated (product type id)
	 * @return list of the service types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwProductServiceType> findRelatedEntityTypesView(Integer parentTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the product-service type for the given product_service_type id
	 * 
	 * @param entityRelationTypeId product_service_type_id *
	 * @return product_service_type for the product-service type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtProdServType findEntityRelationType(Integer entityRelationTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the product-service type for the given product type id and service type
	 * id
	 * 
	 * @param parentTypeId parent type id of the relation (product type id)
	 * @param childTypeId  child type id of the relation (service type id)
	 * @return product_service_type for the product-service type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtProdServType findEntityRelationType(Integer parentTypeId, Integer childTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the view of product-service type for the given product type id and
	 * service type id
	 * 
	 * @param parentTypeId parent type id of the relation (product type id)
	 * @param childTypeId  child type id of the relation (service type id)
	 * @return product_service_type for the product-service type relation
	 * @throws CoMaSwDataAccessException
	 */
	public VwProductServiceType findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given product-service type relation
	 * 
	 * @param dataObject product-service type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtProdServType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given product-service type relation
	 * 
	 * @param dataObject product-service type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtProdServType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given product-service type relation
	 * 
	 * @param dataObject product-service type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtProdServType dataObject) throws CoMaSwDataAccessException;

}
