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

package com.comasw.ejb.miscelaneous;

import javax.ejb.Local;

import org.jooq.Result;

import com.comasw.model.tables.records.MtApplicationMenuRecord;

@Local
public interface ApplicationMenuEJBLocal {

	/**
	 * Return all data from applicationMenu table for a gived profile
	 * 
	 * @param profileCode profile Code for the menu
	 * @return Result<MtApplicationMenuRecord> with all the menu data for the
	 *         chriteria
	 */
	Result<MtApplicationMenuRecord> findMenuByProfileCode(String profileCode);

	/**
	 * Return all data from applicationMenu table for a gived level and profile code
	 * 
	 * @param profileCode profile Code for the menu
	 * @param level       level of the menu
	 * @return Result<MtApplicationMenuRecord> with all the menu data for the
	 *         chriteria
	 */
	Result<MtApplicationMenuRecord> findMenuByLevelAndProfileCode(String profileCode, Integer level);

	/**
	 * Return root item menu from applicationMenu table for a gived profile code
	 * 
	 * @param profileCode profile chriteria to search
	 * @return Result<MtApplicationMenuRecord> with root item menu
	 *         (APPLICATION_PARENT_MENU_ID is null)
	 */

	Result<MtApplicationMenuRecord> findRootItemMenuByProfileCode(String profileCode);

	/**
	 * Return children item menu from applicationMenu table for a giving parent item
	 * menu and profile code
	 * 
	 * @param profileCode  profile chriteria to search
	 * @param parentMenuId parent menu id
	 * @return Result<MtApplicationMenuRecord> with root item menu
	 *         (APPLICATION_PARENT_MENU_ID is null)
	 */

	Result<MtApplicationMenuRecord> findChildrenItemMenuByProfileCode(String profileCode, Integer parentMenuId);

}
