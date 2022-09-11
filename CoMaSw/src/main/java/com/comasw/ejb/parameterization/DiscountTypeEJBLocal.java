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

package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.PtDiscountType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface DiscountTypeEJBLocal {

	/**
	 * Find all the discount type data stores in the system
	 * 
	 * @return list of all discount type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtDiscountType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the discount type data stores in the system for the given discountTypeId
	 * 
	 * @param discountTypeId
	 * @return discount type data in the system for the given discountTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtDiscountType findDataByDiscounTypeId(Integer discountTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the discount type data stores in the system for the given code of status
	 * 
	 * @param code
	 * @return discount type data in the system for the given code of status
	 * @throws CoMaSwDataAccessException
	 */
	public PtDiscountType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given status
	 * 
	 * @param dataObject status object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtDiscountType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtDiscountType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given status
	 * 
	 * @param dataObject status object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtDiscountType dataObject) throws CoMaSwDataAccessException;

}
