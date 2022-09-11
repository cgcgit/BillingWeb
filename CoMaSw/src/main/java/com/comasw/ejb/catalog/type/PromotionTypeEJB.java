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

import static com.comasw.model.Sequences.SEQ_PROMOTION_TYPE_ID;
import static com.comasw.model.Tables.CT_PROMOTION_TYPE;
import static com.comasw.model.Tables.PT_APPLICATION_LEVEL;
import static org.jooq.impl.DSL.val;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.comasw.model.tables.daos.CtPromotionTypeDao;
import com.comasw.model.tables.daos.IdtPromotionTypeDao;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.IdtPromotionType;
import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.model.tables.records.CtPromotionTypeRecord;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class PromotiontTypeEJB
 */
@Stateless
public class PromotionTypeEJB implements PromotionTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(PromotionTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public PromotionTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtPromotionType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE).orderBy(CT_PROMOTION_TYPE.CODE).fetch()
					.into(CtPromotionType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the promotion types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtPromotionType> findDataBySearchDate(LocalDateTime searchDate) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(val(searchDate).between(CT_PROMOTION_TYPE.START_DATE, CT_PROMOTION_TYPE.END_DATE))
					.orderBy(CT_PROMOTION_TYPE.CODE).fetch().into(CtPromotionType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the promotion types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtPromotionType> findDataByPromotionTypeId(Integer promotionTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(CT_PROMOTION_TYPE.PROMOTION_TYPE_ID.eq(val(promotionTypeId)))
					.orderBy(CT_PROMOTION_TYPE.START_DATE, CT_PROMOTION_TYPE.CODE).fetch().into(CtPromotionType.class);

			return result;

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for promotion_type_id: " + promotionTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public CtPromotionType findDataBySearchDateAndPromotionTypeId(LocalDateTime searchDate, Integer promotionTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(CT_PROMOTION_TYPE.PROMOTION_TYPE_ID.eq(val(promotionTypeId)))
					.and(val(searchDate).between(CT_PROMOTION_TYPE.START_DATE, CT_PROMOTION_TYPE.END_DATE))
					.orderBy(CT_PROMOTION_TYPE.CODE).fetch().into(CtPromotionType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion type for search date: " + searchDate.toString()
						+ " and promotion_type_id : " + promotionTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the promotion type for  for search date: " + searchDate.toString()
					+ " and promotion_type_id : " + promotionTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public List<CtPromotionType> findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE).where(CT_PROMOTION_TYPE.CODE.eq(val(code)))
					.orderBy(CT_PROMOTION_TYPE.CODE).fetch().into(CtPromotionType.class);
			return result;

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public CtPromotionType findDataBySearchDateAndCode(LocalDateTime searchDate, String code)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE).where(CT_PROMOTION_TYPE.CODE.eq(val(code)))
					.and(val(searchDate).between(CT_PROMOTION_TYPE.START_DATE, CT_PROMOTION_TYPE.END_DATE))
					.orderBy(CT_PROMOTION_TYPE.CODE).fetch().into(CtPromotionType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion type for search date: " + searchDate.toString()
						+ " and code : " + code + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the promotion type for search date: " + searchDate.toString()
					+ " and code : " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public List<CtPromotionType> findDataByApplicationLevelId(Integer applicationLevelId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(CT_PROMOTION_TYPE.APPLICATION_LEVEL_ID.eq(val(applicationLevelId)))
					.orderBy(CT_PROMOTION_TYPE.CODE).fetch().into(CtPromotionType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for application_level_id " + applicationLevelId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtPromotionType> findDataBySearchDateAndApplicationLevelId(LocalDateTime searchDate,
			Integer applicationLevelId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(CT_PROMOTION_TYPE.APPLICATION_LEVEL_ID.eq(val(applicationLevelId)))
					.and(val(searchDate).between(CT_PROMOTION_TYPE.START_DATE, CT_PROMOTION_TYPE.END_DATE))
					.orderBy(CT_PROMOTION_TYPE.CODE).fetch().into(CtPromotionType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for search date: " + searchDate.toString()
					+ " and application_level_id " + applicationLevelId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtPromotionType> findDataByApplicationLevelCode(String applicationLevelCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtPromotionType ft = CT_PROMOTION_TYPE.as("ft");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(ft)
					.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID).and(al.CODE.eq(val(applicationLevelCode))))
					.orderBy(ft.CODE).fetchGroups(r -> r.into(ft).into(CtPromotionType.class),
							r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtPromotionType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for the application_level_code "
					+ applicationLevelCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtPromotionType> findDataBySearchDateAndApplicationLevelCode(LocalDateTime searchDate,
			String applicationLevelCode) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtPromotionType ft = CT_PROMOTION_TYPE.as("ft");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(ft)
					.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(val(searchDate).between(ft.START_DATE, ft.END_DATE))
							.and(al.CODE.eq(val(applicationLevelCode))))
					.orderBy(ft.CODE).fetchGroups(r -> r.into(ft).into(CtPromotionType.class),
							r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtPromotionType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for search date: " + searchDate.toString()
					+ " and application_level_code " + applicationLevelCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public Integer getNewId() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		String errorMessage;
		Integer id = 0;
		try {
			id = Math.toIntExact(create.nextval(SEQ_PROMOTION_TYPE_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new promotion type" + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;

	}

	@Override
	public void insertData(CtPromotionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();

			if (id > 0) {
				// insert the new id value into the idType datatable
				IdtPromotionType objectId = new IdtPromotionType(id);
				IdtPromotionTypeDao daoObjectId = new IdtPromotionTypeDao(configuration);
				daoObjectId.insert(objectId);

				// insert the dataObject into the type datatable
				dataObject.setPromotionTypeId(id);
				CtPromotionTypeDao daoObject = new CtPromotionTypeDao(configuration);
				daoObject.insert(dataObject);
			}
		} catch (Exception e) {
			errorMessage = "Error inserting the promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertNewHistoricDataRecord(CtPromotionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromotionTypeDao daoObject = new CtPromotionTypeDao(configuration);
			daoObject.insert(dataObject);

		} catch (Exception e) {
			errorMessage = "Error inserting the promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateHistoricDataRecord(CtPromotionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		CtPromotionTypeRecord record;
		try {
			record = create.newRecord(CT_PROMOTION_TYPE, dataObject);
			record.store();

		} catch (Exception e) {
			errorMessage = "Error inserting the promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtPromotionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromotionTypeDao daoObject = new CtPromotionTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtPromotionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromotionTypeDao daoObject = new CtPromotionTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
