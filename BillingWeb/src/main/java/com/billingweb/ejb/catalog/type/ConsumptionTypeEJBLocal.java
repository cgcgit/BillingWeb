package com.billingweb.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.pojos.CtConsumptionType;
import com.billingweb.model.tables.pojos.PtConsumptionClass;

@Local
public interface ConsumptionTypeEJBLocal {
	

	/**
	 * Find all the consumption type data stores in the system
	 * @return list of all consumption type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List <CtConsumptionType> findAllData () throws BillingWebDataAccessException;

	
	/**
	 * Find all the entity type for the consumption type data stores in the system
	 * @return list of all entity type for the consumption type data in the system
	 * @throws BillingWebDataAccessException
	 */
	public List<PtConsumptionClass> findEntityTypeForConsumptions () throws BillingWebDataAccessException;

	
	
	/**
	 * Find the consumption type data stores in the system for the given consumptionTypeId
	 * @param consumptionTypeId
	 * @return consumption type data in the system for the given consumptionTypeId
	 * @throws BillingWebDataAccessException
	 */
	public CtConsumptionType findDataByConsumptionTypeId (Integer consumptionTypeId) throws BillingWebDataAccessException;
	
	/**
	 * Find the consumption type data stores in the system for the given code of consumption type
	 * @param code
	 * @return list of all consumption type data in the system for the given code of consumption type
	 * @throws BillingWebDataAccessException
	 */
	public CtConsumptionType findDataByCode (String code) throws BillingWebDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given consumption type 
	 * @param dataObject consumption type object to insert
	 * @throws BillingWebDataAccessException
	 */
	public void insertData (CtConsumptionType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Updates into the system (database) the given consumption type 
	 * @param dataObject consumption type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void updateData (CtConsumptionType dataObject) throws BillingWebDataAccessException;
	
	/**
	 * Deletes into the system (database) the given consumption type 
	 * @param dataObject consumption type object to update
	 * @throws BillingWebDataAccessException
	 */
	public void deleteData (CtConsumptionType dataObject) throws BillingWebDataAccessException;
	

}
