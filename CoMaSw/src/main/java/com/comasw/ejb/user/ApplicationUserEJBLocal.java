package com.comasw.ejb.user;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.ItUsers;
import com.comasw.model.tables.pojos.VwUsers;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ApplicationUserEJBLocal {
	
	public VwUsers activeUser(String userName, String password);
	
	
	/**
	 * Find all the user data stores in the system
	 * @return list of all users in the system 
	 * @throws CoMaSwDataAccessException
	 */
	public List <ItUsers> findData () throws CoMaSwDataAccessException;
	
	/**
	 * Find the user data stores in the system for the given user code 
	 * @param code
	 * @return User in the system for the given user code
	 * @throws CoMaSwDataAccessException
	 */
	public ItUsers findDataByUserCode (String code) throws CoMaSwDataAccessException;
	
	/**
	 * Find the user data stores in the system for the given user_id 
	 * @param id
	 * @return User in the system for the given user_id
	 * @throws CoMaSwDataAccessException
	 */
	public ItUsers findDataByUserId (Integer id) throws CoMaSwDataAccessException;
	
	

	
	/**
	 * Find the user data stores in the system for the given profile_id
	 * @param code
	 * @return list of all users in the system for the given profile_id
	 * @throws CoMaSwDataAccessException
	 */
	public List <ItUsers> findDataByProfileId (Integer id) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the user data stores in the system for the given code of profile
	 * @param code
	 * @return list of all users in the system for the given code of profile
	 * @throws CoMaSwDataAccessException
	 */
	public List <ItUsers> findDataByProfileCode (String code) throws CoMaSwDataAccessException;
	
	
		
	/**
	 * Inserts into the system (database) the given user
	 * @param dataObject user object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (ItUsers dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given user 
	 * @param dataObject user object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (ItUsers dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given user
	 * @param dataObject user object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (ItUsers dataObject) throws CoMaSwDataAccessException;
	
	

}
