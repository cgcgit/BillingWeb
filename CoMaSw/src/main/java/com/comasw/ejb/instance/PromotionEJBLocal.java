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
import com.comasw.model.tables.pojos.ItPromotion;
import com.comasw.model.tables.pojos.VwPromotionInstance;

@Local
public interface PromotionEJBLocal {
	

	/**
	 * Find all the promotion data stores in the system
	 * @param includeCancelledData true -> included cancelled data
	 * @return list of all promotion data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItPromotion> findAllData(boolean includeCancelledData) throws CoMaSwDataAccessException;
	
	/**
	 * Find the promotion data stores in the system for the given promotionId
	 * @param promotionId
	 * @return type data in the system for the given promotionId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItPromotion> findDataByPromotionId(Integer promotionId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the promotion data stores in the system for the given code
	 * @param code
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given code
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItPromotion> findDataByCode(String code, boolean includeCancelledData) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the promotion data stores in the system for the given searchDate
	 * @param searchDate
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItPromotion> findDataBySearchDate(LocalDateTime searchDate, boolean includeCancelledData) throws CoMaSwDataAccessException;


	

	
	/**
	 * Find the promotion data stores in the system for the given promotionId
	 * @param searchDate
	 * @param promotionId
	 * @return type data in the system for the given promotionId
	 * @throws CoMaSwDataAccessException
	 */
	public ItPromotion findDataBySearchDateAndPromotionId(LocalDateTime searchDate, Integer promotionId) throws CoMaSwDataAccessException;
	


	/**
	 * Find the promotion data stores in the system for the given code
	 * @param searchDate
	 * @param code
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given code
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItPromotion> findDataBySearchDateAndCode(LocalDateTime searchDate, String code, boolean includeCancelledData) throws CoMaSwDataAccessException;
	


	/**
	 * Return all data of the promotion instance in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	 * @param searchDate
	 * @param includeCancelledData
	 * @param promotionId
	 * @param promotionTypeId
	 * @param promotionCode
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
	public List<ItPromotion> findInstanceWithParameters(Optional<LocalDateTime> searchDate, 
			boolean includeCancelledData, Optional<Integer> promotionId, Optional<Integer> promotionTypeId, 
			Optional<String> promotionCode,
			Optional<Integer> statusId, 
			Optional<String> applicationLevelCode, 
			Optional<Integer> serviceId, Optional<Integer> serviceTypeId,  Optional<String> serviceNr,
			Optional<Integer> productId, Optional<Integer> productTypeId,
			Optional<String> contractNr,
			Optional<Integer> accountId, 
			Optional<String> accountIdentityCard,
			Optional<String> accountContactPhone, 
			Optional<Integer> customerId, 
			Optional<String> customerIdentityCard, 
			Optional<String> customerContactPhone) throws CoMaSwDataAccessException;
	
	

	
	/**
	 * Return all data view of the promotion instance in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	  * @param searchDate
	 * @param includeCancelledData
	 * @param promotionId
	 * @param promotionTypeId
	 * @param promotionCode
	 * @param statusId
	 * @param applicationLevelCode
	 * @param serviceId
	 * @param serviceTypeId,
	 * @param serviceNr,
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
	public List<VwPromotionInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate, 
			boolean includeCancelledData, Optional<Integer> promotionId, Optional<Integer> promotionTypeId, 
			Optional<String> promotionCode,
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
	 * Inserts into the system (database) the given new promotion. It creates a new
	 * Id and inserts into the id table.
	 * 
	 * @param dataObject promotion object to insert
	 * @return id of the new instance
	 * @throws CoMaSwDataAccessException
	 */
	public Integer insertData(ItPromotion dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new customer record.
	 * 
	 * @param dataObject promotion object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord(ItPromotion dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new historic data record for existing
	 * customer
	 * 
	 * @param dataObject promotion object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord(ItPromotion dataObject) throws CoMaSwDataAccessException;

	/**s
	 * Updates into the system (database) the given promotion
	 * 
	 * @param dataObject promotion object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItPromotion dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given promotion
	 * 
	 * @param dataObject promotion object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItPromotion dataObject) throws CoMaSwDataAccessException;

		

}


