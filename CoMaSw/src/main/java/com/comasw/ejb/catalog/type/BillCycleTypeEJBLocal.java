package com.comasw.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtBillCycleType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface BillCycleTypeEJBLocal {
	

	/**
	 * Find all the bill cycle type data stores in the system
	 * @return list of all bill cycle type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtBillCycleType> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find all the ordinary bill cycle type data stores in the system
	 * @return list of all ordinary bill cycle type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtBillCycleType> findOrdinaryCycleType () throws CoMaSwDataAccessException;
	
	
	/**
	 * Find all the corrective bill cycle type data stores in the system
	 * @return list of all corrective bill cycle type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtBillCycleType> findCorrectiveCycleType () throws CoMaSwDataAccessException;


	
	/**
	 * Find the bill cycle type data stores in the system for the given billCycleTypeId
	 * @param billCycleTypeId
	 * @return bill cycle type data in the system for the given billCycleTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtBillCycleType findDataByBillCycleTypeId (Integer billCycleTypeId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the bill cycle type data stores in the system for the given code of bill cycle type
	 * @param code
	 * @return bill cycle type data in the system for the given code of bill cycle type
	 * @throws CoMaSwDataAccessException
	 */
	public CtBillCycleType findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given bill cycle type 
	 * @param dataObject bill cycle type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtBillCycleType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given bill cycle type 
	 * @param dataObject bill cycle type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtBillCycleType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given bill cycle type 
	 * @param dataObject bill cycle type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtBillCycleType dataObject) throws CoMaSwDataAccessException;
	

}
