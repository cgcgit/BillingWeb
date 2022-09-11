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
import com.comasw.model.tables.pojos.ItProduct;
import com.comasw.model.tables.pojos.VwProductInstance;

@Local
public interface ProductEJBLocal {
	
	/**
	 * Find all the product data stores in the system
	 * @param includeCancelledData true -> included cancelled data
	 * @return list of all product data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItProduct> findAllData(Optional<Boolean> includeCancelledData) throws CoMaSwDataAccessException;
	
	/**
	 * Find the product data stores in the system for the given productId
	 * @param productId
	 * @return type data in the system for the given productId
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItProduct> findDataByProductId(Integer productId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the customer data stores in the system for the given searchDate
	 * @param searchDate
	 * @param includeCancelledData true -> included cancelled data
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItProduct> findDataBySearchDate(LocalDateTime searchDate, boolean includeCancelledData) throws CoMaSwDataAccessException;


	
	/**
	 * Find the customer data stores in the system for the given productId
	 * @param searchDate
	 * @param productId
	 * @return type data in the system for the given productId
	 * @throws CoMaSwDataAccessException
	 */
	public ItProduct findDataBySearchDateAndProductId(LocalDateTime searchDate, Integer productId) throws CoMaSwDataAccessException;
	

	


	/**
	 * Return all data of the product instance in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	 * @param searchDate date criteria to search
	 * @param includeCancelledData true -> included cancelled data
	 * @param productId criteria to search
	 * @param productTypeId criteria to search
	 * @param statusId criteria to search
	 * @param contractNr criteria to search
	 * @param accountId account id criteria to search
	 * @param accountIdentityCard account identity card criteria to search
	 * @param accountContactPhone account contact phone criteria to search
	 * @param customerId customer id criteria to search
	 * @param customerIdentityCard customer identity card criteria to search
	 * @param customerContactPhone customer contact phone criteria to search
	 * @return product list for the criteria
	 * @throws CoMaSwDataAccessException
	 */
	public List<ItProduct> findInstanceWithParameters(Optional<LocalDateTime> searchDate, 
			boolean includeCancelledData, Optional<Integer> productId, Optional<Integer> productTypeId,
			Optional<Integer> statusId, Optional<String> contractNr,
			Optional<Integer> accountId, 
			Optional<String> accountIdentityCard, Optional<String> accountContactPhone, 
			Optional<Integer> customerId, 
			Optional<String> customerIdentityCard, Optional<String> customerContactPhone) throws CoMaSwDataAccessException;
	
	

	/**
	 * Return all data view of the product instance in the system for a giving search
     * criteria. The parameters are optional, but there must be at least one parameter specified.
	 * @param searchDate date criteria to search
	 * @param includeCancelledData true -> included cancelled data
	 * @param productId criteria to search
	 * @param productTypeId criteria to search
	 * @param statusId criteria to search
	 * @param contractNr  criteria to search
	 * @param accountId account id criteria to search
	 * @param accountIdentityCard account identity card criteria to search
	 * @param accountContactPhone account contact phone criteria to search
	 * @param customerId customer id criteria to search
	 * @param customerIdentityCard customer identity card criteria to search
	 * @param customerContactPhone customer contact phone criteria to search
	 * @return product list for the criteria
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwProductInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate, 
			boolean includeCancelledData, Optional<Integer> productId, Optional<Integer> productTypeId,
			Optional<Integer> statusId, Optional<String> contractNr,
			Optional<Integer> accountId, 
			Optional<String> accountIdentityCard, Optional<String> accountContactPhone, 
			Optional<Integer> customerId, 
			Optional<String> customerIdentityCard, Optional<String> customerContactPhone) throws CoMaSwDataAccessException;
	
	
	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given new product. It creates a new
	 * Id and inserts into the id table.
	 * 
	 * @param dataObject product object to insert
	 * @return id of the new instance
	 * @throws CoMaSwDataAccessException
	 */
	public Integer insertData(ItProduct dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new customer record.
	 * 
	 * @param dataObject product object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord(ItProduct dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new historic data record for existing
	 * customer
	 * 
	 * @param dataObject product object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord(ItProduct dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given product
	 * 
	 * @param dataObject product object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(ItProduct dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given product
	 * 
	 * @param dataObject product object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(ItProduct dataObject) throws CoMaSwDataAccessException;

		

}
