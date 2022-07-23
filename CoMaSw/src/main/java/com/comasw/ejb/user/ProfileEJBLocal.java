package com.comasw.ejb.user;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.ItProfiles;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ProfileEJBLocal {

	/**
	 * Find all the user data stores in the system
	 * 
	 * @return list of all users in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItProfiles> findData() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given user
	 * 
	 * @param dataObject user object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(ItProfiles dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given user
	 * 
	 * @param dataObject user object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItProfiles dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given user
	 * 
	 * @param dataObject user object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItProfiles dataObject) throws CoMaSwDataAccessException;

}
