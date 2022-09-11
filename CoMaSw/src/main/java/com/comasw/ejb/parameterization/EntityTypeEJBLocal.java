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

import com.comasw.model.tables.pojos.PtEntityType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface EntityTypeEJBLocal {

	/**
	 * Find all the entity type data stores in the system
	 * 
	 * @return list of all entity type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtEntityType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the entity type data stores in the system for the given entityTypeId
	 * 
	 * @param entityTypeId
	 * @return entity type data in the system for the given entityTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtEntityType findDataByEntityTypeId(Integer entityTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the entity type data stores in the system for the given code of entity
	 * type
	 * 
	 * @param code
	 * @return entity type data in the system for the given code of entity type
	 * @throws CoMaSwDataAccessException
	 */
	public PtEntityType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given entity type
	 * 
	 * @param dataObject entity type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtEntityType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given entity type
	 * 
	 * @param dataObject entity type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtEntityType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given entity type
	 * 
	 * @param dataObject entity type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtEntityType dataObject) throws CoMaSwDataAccessException;

}
