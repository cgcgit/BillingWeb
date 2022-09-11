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

package com.comasw.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtProductType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface ProductTypeEJBLocal {

	/**
	 * Find all the product type data stores in the system
	 * 
	 * @return list of all product type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtProductType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the product type data stores in the system for the given productTypeId
	 * 
	 * @param productTypeId
	 * @return product type data in the system for the given productTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtProductType findDataByProductTypeId(Integer productTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the product type data stores in the system for the given code of product
	 * type
	 * 
	 * @param code
	 * @return product type data in the system for the given code of product type
	 * @throws CoMaSwDataAccessException
	 */
	public CtProductType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given product type
	 * 
	 * @param dataObject product type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtProductType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given product type
	 * 
	 * @param dataObject product type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtProductType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given product type
	 * 
	 * @param dataObject product type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtProductType dataObject) throws CoMaSwDataAccessException;

}
