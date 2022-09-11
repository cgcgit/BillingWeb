/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
 any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/

package com.comasw.ejb.catalog.type;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtBillCycleType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface BillCycleTypeEJBLocal {

	/**
	 * Find all the bill cycle type data stores in the system
	 * 
	 * @return list of all bill cycle type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtBillCycleType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find all the ordinary bill cycle type data stores in the system
	 * 
	 * @return list of all ordinary bill cycle type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtBillCycleType> findOrdinaryCycleType() throws CoMaSwDataAccessException;

	/**
	 * Find all the corrective bill cycle type data stores in the system
	 * 
	 * @return list of all corrective bill cycle type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtBillCycleType> findCorrectiveCycleType() throws CoMaSwDataAccessException;

	/**
	 * Find the bill cycle type data stores in the system for the given
	 * billCycleTypeId
	 * 
	 * @param billCycleTypeId
	 * @return bill cycle type data in the system for the given billCycleTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public CtBillCycleType findDataByBillCycleTypeId(Integer billCycleTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the bill cycle type data stores in the system for the given code of bill
	 * cycle type
	 * 
	 * @param code
	 * @return bill cycle type data in the system for the given code of bill cycle
	 *         type
	 * @throws CoMaSwDataAccessException
	 */
	public CtBillCycleType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given bill cycle type
	 * 
	 * @param dataObject bill cycle type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtBillCycleType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given bill cycle type
	 * 
	 * @param dataObject bill cycle type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtBillCycleType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given bill cycle type
	 * 
	 * @param dataObject bill cycle type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtBillCycleType dataObject) throws CoMaSwDataAccessException;

}
