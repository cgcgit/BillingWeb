/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

*/

package com.comasw.ejb.instance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.ItFee;
import com.comasw.model.tables.pojos.VwFeeInstance;

@Local
public interface FeeEJBLocal {
	

	/**
	 * Find all the fee data stores in the system
	 * @param includeCancelledData true -> included cancelled data
	 * @return list of all fee data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItFee> findAllData(boolean includeCancelledData) throws CoMaSwDataAccessException;
	
	/**
	 * Find the fee data stores in the system for the given feeId
	 * @param feeId
	 * @return type data in the system for the given feeId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItFee> findDataByFeeId(Integer feeId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the fee data stores in the system for the given code
	 * @param code
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given code
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItFee> findDataByCode(String code, boolean includeCancelledData) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the fee data stores in the system for the given searchDate
	 * @param searchDate
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItFee> findDataBySearchDate(LocalDateTime searchDate, boolean includeCancelledData) throws CoMaSwDataAccessException;


	

	
	/**
	 * Find the fee data stores in the system for the given feeId
	 * @param searchDate
	 * @param feeId
	 * @return type data in the system for the given feeId
	 * @throws CoMaSwDataAccessException
	 */
	public ItFee findDataBySearchDateAndFeeId(LocalDateTime searchDate, Integer feeId) throws CoMaSwDataAccessException;
	


	/**
	 * Find the fee data stores in the system for the given code
	 * @param searchDate
	 * @param code
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given code
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItFee> findDataBySearchDateAndCode(LocalDateTime searchDate, String code, boolean includeCancelledData) throws CoMaSwDataAccessException;
	


	/**
	 * Return all data of the fee instance in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	 * @param searchDate
	 * @param includeCancelledData
	 * @param feeId
	 * @param feeTypeId
	 * @param feeCode
	 * @param statusId
	 * @param applicationLevelCode
	 * @param serviceId
	 * @param serviceTypeId
	 * @param serviceNr
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
	public List<ItFee> findInstanceWithParameters(Optional<LocalDateTime> searchDate, 
			boolean includeCancelledData, Optional<Integer> feeId, Optional<Integer> feeTypeId, 
			Optional<String> feeCode,
			Optional<Integer> statusId, 
			Optional<String> applicationLevelCode, 
			Optional<Integer> serviceId, Optional<Integer> serviceTypeId, Optional<String> serviceNr,
			Optional<Integer> productId, Optional<Integer> productTypeId, 
			Optional<String> contractNr,
			Optional<Integer> accountId, 
			Optional<String> accountIdentityCard,
			Optional<String> accountContactPhone, 
			Optional<Integer> customerId, 
			Optional<String> customerIdentityCard, 
			Optional<String> customerContactPhone) throws CoMaSwDataAccessException;
	
	

	
	/**
	 * Return all data view of the fee instance in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	  * @param searchDate
	 * @param includeCancelledData
	 * @param feeId
	 * @param feeTypeId
	 * @param feeCode
	 * @param statusId
	 * @param applicationLevelCode
	 * @param serviceId
	 * @param serviceTypeId
	 * @param serviceNr
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
	public List<VwFeeInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate, 
			boolean includeCancelledData, Optional<Integer> feeId, Optional<Integer> feeTypeId, 
			Optional<String> feeCode,
			Optional<Integer> statusId, 
			Optional<String> applicationLevelCode, 
			Optional<Integer> serviceId, Optional<Integer> serviceTypeId, Optional<String> serviceNr,
			Optional<Integer> productId, Optional<Integer> productTypeId,
			Optional<String> contractNr,
			Optional<Integer> accountId, 
			Optional<String> accountIdentityCard,
			Optional<String> accountContactPhone, 
			Optional<Integer> customerId, 
			Optional<String> customerIdentityCard, 
			Optional<String> customerContactPhone) throws CoMaSwDataAccessException;
	
	
	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given new fee. It creates a new
	 * Id and inserts into the id table.
	 * 
	 * @param dataObject fee object to insert
	 * @return id of the new instance
	 * @throws CoMaSwDataAccessException
	 */
	public Integer insertData(ItFee dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new customer record.
	 * 
	 * @param dataObject fee object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord(ItFee dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new historic data record for existing
	 * customer
	 * 
	 * @param dataObject fee object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord(ItFee dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given fee
	 * 
	 * @param dataObject fee object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItFee dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given fee
	 * 
	 * @param dataObject fee object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItFee dataObject) throws CoMaSwDataAccessException;

		

}
