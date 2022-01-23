package com.billingweb.ejb.user;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.ItUsers;
import com.billingweb.model.tables.pojos.VwUsers;

@Local
public interface ApplicationUserEJBLocal {
	
	public VwUsers activeUser(String userName, String password);
	
	
	/**
	 * Find all the user data stores in the system
	 * @return list of all users in the system 
	 * @throws BillingWebDataAccessException
	 */
	public List <ItUsers> findData () throws BillingWebDataAccessException;
	
	/**
	 * Find the user data stores in the system for the given user code 
	 * @param code
	 * @return User in the system for the given user code
	 * @throws BillingWebDataAccessException
	 */
	public ItUsers findDataByUserCode (String code) throws BillingWebDataAccessException;
	
	/**
	 * Find the user data stores in the system for the given user_id 
	 * @param id
	 * @return User in the system for the given user_id
	 * @throws BillingWebDataAccessException
	 */
	public ItUsers findDataByUserId (Integer id) throws BillingWebDataAccessException;
	
	

	
	/**
	 * Find the user data stores in the system for the given profile_id
	 * @param code
	 * @return list of all users in the system for the given profile_id
	 * @throws BillingWebDataAccessException
	 */
	public List <ItUsers> findDataByProfileId (Integer id) throws BillingWebDataAccessException;
	
	
	/**
	 * Find the user data stores in the system for the given code of profile
	 * @param code
	 * @return list of all users in the system for the given code of profile
	 * @throws BillingWebDataAccessException
	 */
	public List <ItUsers> findDataByProfileCode (String code) throws BillingWebDataAccessException;
	
	
		
	/**
	 * Inserts into the system (database) the given user
	 * @param dataObject user object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (ItUsers dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given user 
	 * @param dataObject user object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (ItUsers dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given user
	 * @param dataObject user object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (ItUsers dataObject) throws BillingWebDataAccessException;
	
	

}
