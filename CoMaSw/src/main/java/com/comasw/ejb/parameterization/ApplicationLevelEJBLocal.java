package com.comasw.ejb.parameterization;

import javax.ejb.Local;

import java.util.List;

import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ApplicationLevelEJBLocal {

	/**
	 * Find all the application level data stores in the system
	 * 
	 * @return list of all application level data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtApplicationLevel> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the application level data stores in the system for the given
	 * applicationLevelId
	 * 
	 * @param applicationLevelId
	 * @return application level data in the system for the given applicationLevelId
	 * @throws CoMaSwDataAccessException
	 */
	public PtApplicationLevel findDataByApplicationLevelId(Integer applicationLevelId) throws CoMaSwDataAccessException;

	/**
	 * Find the application level data stores in the system for the given code of
	 * application level
	 * 
	 * @param code
	 * @return application level data in the system for the given code of
	 *         application level
	 * @throws CoMaSwDataAccessException
	 */
	public PtApplicationLevel findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given application level
	 * 
	 * @param dataObject application level object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given application level
	 * 
	 * @param dataObject application level object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given application level
	 * 
	 * @param dataObject application level object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException;

}
