package com.comasw.ejb.instance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.ItService;
import com.comasw.model.tables.pojos.VwServiceInstance;

@Local
public interface ServiceEJBLocal {

	/**
	 * Find all the service data stores in the system
	 * 
	 * @param includeCancelledData true -> included cancelled data
	 * @return list of all service data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItService> findAllData(boolean includeCancelledData) throws CoMaSwDataAccessException;

	/**
	 * Find the service data stores in the system for the given serviceId
	 * 
	 * @param serviceId
	 * @return type data in the system for the given serviceId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItService> findDataByServiceId(Integer serviceId) throws CoMaSwDataAccessException;

	/**
	 * Find the service data stores in the system for the given serviceNumber
	 * 
	 * @param serviceNumber
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given serviceNumber
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItService> findDataByServiceNumber(String serviceNumber, boolean includeCancelledData)
			throws CoMaSwDataAccessException;

	/**
	 * Find the customer data stores in the system for the given searchDate
	 * 
	 * @param searchDate
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItService> findDataBySearchDate(LocalDateTime searchDate, boolean includeCancelledData)
			throws CoMaSwDataAccessException;

	/**
	 * Find the customer data stores in the system for the given serviceId
	 * 
	 * @param searchDate
	 * @param serviceId
	 * @return type data in the system for the given serviceId
	 * @throws CoMaSwDataAccessException
	 */
	public ItService findDataBySearchDateAndServiceId(LocalDateTime searchDate, Integer serviceId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the customer data stores in the system for the given serviceNumber
	 * 
	 * @param searchDate
	 * @param serviceId
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given serviceNumber
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItService> findDataBySearchDateAndServiceNumber(LocalDateTime searchDate, String serviceNumber,
			boolean includeCancelledData) throws CoMaSwDataAccessException;


	/**
	 * Return all data of the service instance in the system for a giving search
	 * criteria. The parameters are optional, but there must be at least one
	 * parameter specified.
	 * 
	 * @param searchDate
	 * @param includeCancelledData
	 * @param serviceId
	 * @param serviceTypeId
	 * @param serviceNumber
	 * @param statusId
	 * @param productId
	 * @param productTypeId
	 * @param contractNr
	 * @param accountId
	 * @param accountIdentityCard
	 * @param accountContactPhone
	 * @param customerId
	 * @param customerIdentityCard
	 * @param customerContactPhone
	 * @return
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItService> findInstanceWithParameters(Optional<LocalDateTime> searchDate, boolean includeCancelledData,
			Optional<Integer> serviceId, Optional<Integer> serviceTypeId, Optional<String> serviceNumber,
			Optional<Integer> statusId, Optional<Integer> productId, Optional<Integer> productTypeId,
			Optional<String> contractNr, Optional<Integer> accountId, Optional<String> accountIdentityCard,
			Optional<String> accountContactPhone, Optional<Integer> customerId, Optional<String> customerIdentityCard,
			Optional<String> customerContactPhone) throws CoMaSwDataAccessException;

	/**
	 * Return all data view of the service instance in the system for a giving
	 * search criteria. The parameters are optional, but there must be at least one
	 * parameter specified.
	 * 
	 * @param searchDate
	 * @param includeCancelledData
	 * @param serviceId
	 * @param serviceTypeId
	 * @param serviceNumber
	 * @param statusId
	 * @param productId
	 * @param productTypeId
	 * @param contractNr
	 * @param accountId
	 * @param accountIdentityCard
	 * @param accountContactPhone
	 * @param customerId
	 * @param customerIdentityCard
	 * @param customerContactPhone
	 * @return
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwServiceInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate,
			boolean includeCancelledData, Optional<Integer> serviceId, Optional<Integer> serviceTypeId,
			Optional<String> serviceNumber, Optional<Integer> statusId, Optional<Integer> productId,
			Optional<Integer> productTypeId, Optional<String> contractNr, Optional<Integer> accountId,
			Optional<String> accountIdentityCard, Optional<String> accountContactPhone, Optional<Integer> customerId,
			Optional<String> customerIdentityCard, Optional<String> customerContactPhone)
			throws CoMaSwDataAccessException;

	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given new service. It creates a new Id
	 * and inserts into the id table.
	 * 
	 * @param dataObject service object to insert
	 * @return id of the new instance
	 * @throws CoMaSwDataAccessException
	 */
	public Integer insertData(ItService dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new customer record.
	 * 
	 * @param dataObject service object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord(ItService dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new historic data record for existing
	 * customer
	 * 
	 * @param dataObject service object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord(ItService dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given service
	 * 
	 * @param dataObject service object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItService dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given service
	 * 
	 * @param dataObject service object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItService dataObject) throws CoMaSwDataAccessException;

}
