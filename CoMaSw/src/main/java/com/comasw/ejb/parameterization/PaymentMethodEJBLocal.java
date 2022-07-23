package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtPaymentMethod;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface PaymentMethodEJBLocal {
	
	/**
	 * Find all the payment method data stores in the system
	 * @return list of all payment method data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <PtPaymentMethod> findAllData () throws CoMaSwDataAccessException;

	
	/**
	 * Find the payment method data stores in the system for the given applicationLevelId
	 * @param paymentMethodId
	 * @return payment method data in the system for the given applicationLevelId
	 * @throws CoMaSwDataAccessException
	 */
	public PtPaymentMethod findDataByPaymentMethodId (Integer paymentMethodId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the payment method data stores in the system for the given code of payment method
	 * @param code
	 * @return payment method data in the system for the given code of payment method
	 * @throws CoMaSwDataAccessException
	 */
	public PtPaymentMethod findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given payment method 
	 * @param dataObject payment method object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (PtPaymentMethod dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Updates into the system (database) the given payment method 
	 * @param dataObject payment method object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (PtPaymentMethod dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Deletes into the system (database) the given payment method 
	 * @param dataObject payment method object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (PtPaymentMethod dataObject) throws CoMaSwDataAccessException;
	

}
