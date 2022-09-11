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

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.PtIdentityCardType;

@Local
public interface IdentityCardTypeEJBLocal {

	/**
	 * Find all the identity card type data stores in the system
	 * 
	 * @return list of all identity card type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtIdentityCardType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the identity card type data stores in the system for the given
	 * identityCardTypeId
	 * 
	 * @param identityCardTypeId
	 * @return identity card type data in the system for the given identityCardTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtIdentityCardType findDataByidCardTypeId(Integer identityCardTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the identity card type data stores in the system for the given code of
	 * identity card type
	 * 
	 * @param code
	 * @return identity card type data in the system for the given code of
	 *         identity card type
	 * @throws CoMaSwDataAccessException
	 */
	public PtIdentityCardType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given identity card type
	 * 
	 * @param dataObject identity card type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given identity card type
	 * 
	 * @param dataObject identity card type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given identity card type
	 * 
	 * @param dataObject identity card type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException;



}
