package com.billingweb.ejb.user;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.ItProfiles;

@Local
public interface ProfileEJBLocal {

	/**
	 * Find all the user data stores in the system
	 * 
	 * @return list of all users in the system
	 * @throws BillingWebDataAccessException
	 */
	public List<ItProfiles> findData() throws BillingWebDataAccessException;

	/**
	 * Inserts into the system (database) the given user
	 * 
	 * @param dataObject user object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData(ItProfiles dataObject) throws BillingWebDataAccessException;

	/**
	 * Updates into the system (database) the given user
	 * 
	 * @param dataObject user object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData(ItProfiles dataObject) throws BillingWebDataAccessException;

	/**
	 * Deletes into the system (database) the given user
	 * 
	 * @param dataObject user object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData(ItProfiles dataObject) throws BillingWebDataAccessException;

}
