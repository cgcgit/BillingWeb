package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtStatus;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface StatusEJBLocal {

	/**
	 * Find all the status data stores in the system
	 * @return list of all status data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <PtStatus> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find the status data stores in the system for the given statusId
	 * @param statusId
	 * @return status data in the system for the given statusId
	 * @throws CoMaSwDataAccessException
	 */
	public PtStatus findDataByStatusId (Integer statusId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the status data stores in the system for the given code of status
	 * @param code
	 * @return status data in the system for the given code of status
	 * @throws CoMaSwDataAccessException
	 */
	public PtStatus findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given status 
	 * @param dataObject status object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (PtStatus dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (PtStatus dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given status 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (PtStatus dataObject) throws CoMaSwDataAccessException;
	
}
