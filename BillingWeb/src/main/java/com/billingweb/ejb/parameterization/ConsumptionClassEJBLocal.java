package com.billingweb.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.PtConsumptionClass;

@Local
public interface ConsumptionClassEJBLocal {


	/**
	 * Find all the consumption class data stores in the system
	 * @return list of all consumption class data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <PtConsumptionClass> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find the consumption class data stores in the system for the given entityTypeId
	 * @param consumptionClassId
	 * @return consumption class data in the system for the given entityTypeId
	 * @throws BillingWebDataAccessException
	 */
	public PtConsumptionClass findDataByConsumptionClassId (Integer consumptionClassId) throws BillingWebDataAccessException;
	
	/**
	 * Find the consumption class data stores in the system for the given code of consumption class
	 * @param code
	 * @return consumption class data in the system for the given code of consumption class
	 * @throws BillingWebDataAccessException
	 */
	public PtConsumptionClass findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given consumption class 
	 * @param dataObject consumption class object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (PtConsumptionClass dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given consumption class 
	 * @param dataObject consumption class object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (PtConsumptionClass dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given consumption class 
	 * @param dataObject consumption class object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (PtConsumptionClass dataObject) throws BillingWebDataAccessException;
	

}

