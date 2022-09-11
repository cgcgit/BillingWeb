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

import static com.comasw.model.Tables.PT_IDENTITY_CARD_TYPE;
import static org.jooq.impl.DSL.val;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.daos.PtIdentityCardTypeDao;
import com.comasw.model.tables.pojos.PtIdentityCardType;

/**
 * Session Bean implementation class IdCardEJB
 */
@Stateless
@LocalBean
public class IdentityCardTypeEJB implements IdentityCardTypeEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(IdentityCardTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;


    /**
     * Default constructor. 
     */
    public IdentityCardTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<PtIdentityCardType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtIdentityCardType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_IDENTITY_CARD_TYPE).orderBy(PT_IDENTITY_CARD_TYPE.CODE).fetch()
					.into(PtIdentityCardType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the identity card types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtIdentityCardType findDataByidCardTypeId(Integer identitCardTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtIdentityCardType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_IDENTITY_CARD_TYPE)
					.where(PT_IDENTITY_CARD_TYPE.IDENTITY_CARD_TYPE_ID.eq(val(identitCardTypeId)))
					.orderBy(PT_IDENTITY_CARD_TYPE.CODE).fetch().into(PtIdentityCardType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the identity card type for id_card_type_id : "
						+ identitCardTypeId + " - The query returns a distinct number of rows (" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				if (result.size() == 0) {
					return null;
				} else {
					return result.get(0);
				}
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the identity card type for id_card_type_id: "
					+ identitCardTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public PtIdentityCardType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtIdentityCardType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_IDENTITY_CARD_TYPE).where(PT_IDENTITY_CARD_TYPE.CODE.eq(val(code)))
					.orderBy(PT_IDENTITY_CARD_TYPE.CODE).fetch().into(PtIdentityCardType.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the identity card type for code : " + code
						+ " - The query returns a distinct number of rows (" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				if (result.size() == 0) {
					return null;
				} else {
					return result.get(0);
				}
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the identity card type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtIdentityCardTypeDao daoObject = new PtIdentityCardTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the identity card type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtIdentityCardTypeDao daoObject = new PtIdentityCardTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the identity card type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtIdentityCardTypeDao daoObject = new PtIdentityCardTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the identity card type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

}
