package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtEntityType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface EntityTypeEJBLocal {

	/**
	 * Find all the entity type data stores in the system
	 * 
	 * @return list of all entity type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtEntityType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the entity type data stores in the system for the given entityTypeId
	 * 
	 * @param entityTypeId
	 * @return entity type data in the system for the given entityTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtEntityType findDataByEntityTypeId(Integer entityTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the entity type data stores in the system for the given code of entity
	 * type
	 * 
	 * @param code
	 * @return entity type data in the system for the given code of entity type
	 * @throws CoMaSwDataAccessException
	 */
	public PtEntityType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given entity type
	 * 
	 * @param dataObject entity type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtEntityType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given entity type
	 * 
	 * @param dataObject entity type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtEntityType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given entity type
	 * 
	 * @param dataObject entity type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtEntityType dataObject) throws CoMaSwDataAccessException;

}
