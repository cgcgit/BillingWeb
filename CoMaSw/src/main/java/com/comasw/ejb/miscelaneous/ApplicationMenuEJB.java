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
package com.comasw.ejb.miscelaneous;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.inline;
import static org.jooq.impl.DSL.val;

import com.comasw.model.tables.records.MtApplicationMenuRecord;
import com.comasw.exception.CoMaSwDataAccessException;

import static com.comasw.model.Tables.MT_APPLICATION_MENU;

/**
 * Session Bean implementation class ApplicationMenu
 */
@Stateless
@LocalBean
public class ApplicationMenuEJB implements ApplicationMenuEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ApplicationMenuEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 * 
	 * @return
	 */
	public void ApplicationMenu() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Result<MtApplicationMenuRecord> findMenuByProfileCode(String profileCode) {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		Result<MtApplicationMenuRecord> menu = null;
		String errorMessage;

		try {
			// search by like because the profile code can have more than one value
			menu = create.selectFrom(MT_APPLICATION_MENU)
					.where(MT_APPLICATION_MENU.PROFILE_CODE.like(concat(inline("%"), val(profileCode), inline("%"))))
					.orderBy(MT_APPLICATION_MENU.MENU_LEVEL, MT_APPLICATION_MENU.POSITION).fetch();

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the application menu for profile " + profileCode + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return menu;
	}

	@Override
	public Result<MtApplicationMenuRecord> findMenuByLevelAndProfileCode(String profileCode, Integer level) {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		Result<MtApplicationMenuRecord> menu = null;
		String errorMessage;

		try {
			menu = create.selectFrom(MT_APPLICATION_MENU)
					.where(MT_APPLICATION_MENU.PROFILE_CODE.like(concat(inline("%"), val(profileCode), inline("%"))))
					.and(MT_APPLICATION_MENU.MENU_LEVEL.eq(val(level)))
					.orderBy(MT_APPLICATION_MENU.MENU_LEVEL, MT_APPLICATION_MENU.POSITION).fetch();

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the application menu for profile " + profileCode + " and level "
					+ level + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return menu;

	}

	@Override
	public Result<MtApplicationMenuRecord> findRootItemMenuByProfileCode(String profileCode) {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		Integer rootLevel = 1;
		String errorMessage;

		Result<MtApplicationMenuRecord> menu = null;

		try {
			menu = create.selectFrom(MT_APPLICATION_MENU)
					.where(MT_APPLICATION_MENU.PROFILE_CODE.like(concat(inline("%"), val(profileCode), inline("%"))))
					.and(MT_APPLICATION_MENU.MENU_LEVEL.eq(val(rootLevel)))
					.orderBy(MT_APPLICATION_MENU.MENU_LEVEL, MT_APPLICATION_MENU.POSITION).fetch();
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the root item application menu for profile " + profileCode + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		return menu;
	}

	@Override
	public Result<MtApplicationMenuRecord> findChildrenItemMenuByProfileCode(String profileCode, Integer parentMenuId) {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		String errorMessage;

		Result<MtApplicationMenuRecord> menu = null;

		try {
			menu = create.selectFrom(MT_APPLICATION_MENU)
					.where(MT_APPLICATION_MENU.PROFILE_CODE.like(concat(inline("%"), val(profileCode), inline("%"))))
					.and(MT_APPLICATION_MENU.APPLICATION_PARENT_MENU_ID.eq(val(parentMenuId)))
					.orderBy(MT_APPLICATION_MENU.MENU_LEVEL, MT_APPLICATION_MENU.POSITION).fetch();
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the child item application menu for profile " + profileCode
					+ " and parent menu id " + parentMenuId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);

		}
		return menu;
	}

}
