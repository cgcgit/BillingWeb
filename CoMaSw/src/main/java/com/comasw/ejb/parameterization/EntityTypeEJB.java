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
package com.comasw.ejb.parameterization;

import java.util.List;

import javax.annotation.Resource;
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

import com.comasw.model.tables.daos.PtEntityTypeDao;
import com.comasw.model.tables.pojos.PtEntityType;
import com.comasw.exception.CoMaSwDataAccessException;

import static com.comasw.model.Tables.PT_ENTITY_TYPE;
import static org.jooq.impl.DSL.val;;

/**
 * Session Bean implementation class EntityTypeEJB
 */
@Stateless
public class EntityTypeEJB implements EntityTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(EntityTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public EntityTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PtEntityType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtEntityType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_ENTITY_TYPE).orderBy(PT_ENTITY_TYPE.CODE).fetch().into(PtEntityType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the entity types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtEntityType findDataByEntityTypeId(Integer entityTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtEntityType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_ENTITY_TYPE).where(PT_ENTITY_TYPE.ENTITY_TYPE_ID.eq(val(entityTypeId)))
					.orderBy(PT_ENTITY_TYPE.CODE).fetch().into(PtEntityType.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the entity type for entity_type_id : " + entityTypeId 
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
			errorMessage = "Error while try to find the entity type for entity_type_id: " + entityTypeId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public PtEntityType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtEntityType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_ENTITY_TYPE).where(PT_ENTITY_TYPE.CODE.eq(val(code)))
					.orderBy(PT_ENTITY_TYPE.CODE).fetch().into(PtEntityType.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the entity type for code : " + code
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
			errorMessage = "Error while try to find the entity type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(PtEntityType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtEntityTypeDao daoObject = new PtEntityTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the entity type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(PtEntityType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtEntityTypeDao daoObject = new PtEntityTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the entity type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(PtEntityType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtEntityTypeDao daoObject = new PtEntityTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the entity type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
