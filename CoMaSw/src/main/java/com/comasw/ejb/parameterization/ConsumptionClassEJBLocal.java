package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtConsumptionClass;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ConsumptionClassEJBLocal {


	/**
	 * Find all the consumption class data stores in the system
	 * @return list of all consumption class data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <PtConsumptionClass> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find the consumption class data stores in the system for the given entityTypeId
	 * @param consumptionClassId
	 * @return consumption class data in the system for the given entityTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtConsumptionClass findDataByConsumptionClassId (Integer consumptionClassId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the consumption class data stores in the system for the given code of consumption class
	 * @param code
	 * @return consumption class data in the system for the given code of consumption class
	 * @throws CoMaSwDataAccessException
	 */
	public PtConsumptionClass findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given consumption class 
	 * @param dataObject consumption class object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (PtConsumptionClass dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given consumption class 
	 * @param dataObject consumption class object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (PtConsumptionClass dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given consumption class 
	 * @param dataObject consumption class object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (PtConsumptionClass dataObject) throws CoMaSwDataAccessException;
	

}

