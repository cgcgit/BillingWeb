package com.comasw.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtConsumptionType;
import com.comasw.model.tables.pojos.PtConsumptionClass;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ConsumptionTypeEJBLocal {
	

	/**
	 * Find all the consumption type data stores in the system
	 * @return list of all consumption type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtConsumptionType> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find all the entity type for the consumption type data stores in the system
	 * @return list of all entity type for the consumption type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtConsumptionClass> findEntityTypeForConsumptions () throws CoMaSwDataAccessException;

	
	
	/**
	 * Find the consumption type data stores in the system for the given consumptionTypeId
	 * @param consumptionTypeId
	 * @return consumption type data in the system for the given consumptionTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtConsumptionType findDataByConsumptionTypeId (Integer consumptionTypeId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the consumption type data stores in the system for the given code of consumption type
	 * @param code
	 * @return list of all consumption type data in the system for the given code of consumption type
	 * @throws CoMaSwDataAccessException
	 */
	public CtConsumptionType findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given consumption type 
	 * @param dataObject consumption type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtConsumptionType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given consumption type 
	 * @param dataObject consumption type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtConsumptionType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given consumption type 
	 * @param dataObject consumption type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtConsumptionType dataObject) throws CoMaSwDataAccessException;
	

}
