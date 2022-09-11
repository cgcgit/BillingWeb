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

package com.comasw.ejb.catalog.relationType;

import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.VW_PROMOTION_PRODUCT_TYPE;
import static com.comasw.model.Tables.CT_PROMOTION_TYPE;
import static com.comasw.model.Tables.CT_PROMO_PROD_TYPE;
import static com.comasw.model.Tables.PT_APPLICATION_LEVEL;
import static org.jooq.impl.DSL.val;
import static org.jooq.impl.DSL.max;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import com.comasw.model.tables.daos.CtPromoProdTypeDao;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.model.tables.pojos.CtPromoProdType;
import com.comasw.model.tables.pojos.VwPromotionProductType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class ProductTypeRelationsEJB
 */
@Stateless
public class ProductPromotionTypeEJB implements ProductPromotionTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ProductFeeTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.comasw.properties.dataBaseDefinitions");

	protected static String APPLICATION_LEVEL_CODE_PROD = dbDefinitions.getString("APPLICATION_LEVEL_CODE_PROD");

	/**
	 * Default constructor.
	 */
	public ProductPromotionTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtPromotionType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.comasw.model.tables.CtPromotionType pt2 = CT_PROMOTION_TYPE.as("pt2");
		com.comasw.model.tables.CtPromoProdType ppt = CT_PROMO_PROD_TYPE.as("ppt");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(pt)
					.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))
					.whereNotExists(create.selectOne().from(ppt).where(
							pt.PROMOTION_TYPE_ID.eq(ppt.PROMOTION_TYPE_ID).and(ppt.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.and(pt.START_DATE.eq(create.select(max(pt2.START_DATE)).from(pt2)
							.where(pt.PROMOTION_TYPE_ID.eq(pt2.PROMOTION_TYPE_ID))))
					.orderBy(pt.CODE).fetchGroups(r -> r.into(pt).into(CtPromotionType.class),
							r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtPromotionType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type candidates for the product_type_id : " + parentTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	
	@Override
	public List<CtPromotionType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.comasw.model.tables.CtPromotionType pt2 = CT_PROMOTION_TYPE.as("pt2");
		com.comasw.model.tables.CtPromoProdType ppt = CT_PROMO_PROD_TYPE.as("ppt");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(pt)
					.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))
					.whereExists(create.selectOne()
							.from(ppt.join(st).on(ppt.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(pt.PROMOTION_TYPE_ID.eq(ppt.PROMOTION_TYPE_ID)
									.and(ppt.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.and(pt.START_DATE.eq(create.select(max(pt2.START_DATE)).from(pt2)
							.where(pt.PROMOTION_TYPE_ID.eq(pt2.PROMOTION_TYPE_ID))))
					.orderBy(pt.CODE).fetchGroups(r -> r.into(pt).into(CtPromotionType.class),
							r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtPromotionType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type candidates for the product_type_id: " + parentTypeId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	

	@Override
	public List<CtPromotionType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.comasw.model.tables.CtPromotionType pt2 = CT_PROMOTION_TYPE.as("pt2");
		com.comasw.model.tables.CtPromoProdType ppt = CT_PROMO_PROD_TYPE.as("ppt");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(pt)
					.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))
					.whereNotExists(create.selectOne()
							.from(ppt.join(st).on(ppt.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(pt.PROMOTION_TYPE_ID.eq(ppt.PROMOTION_TYPE_ID)
									.and(ppt.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.and(pt.START_DATE.eq(create.select(max(pt2.START_DATE)).from(pt2)
							.where(pt.PROMOTION_TYPE_ID.eq(pt2.PROMOTION_TYPE_ID))))
					.orderBy(pt.CODE).fetchGroups(r -> r.into(pt).into(CtPromotionType.class),
							r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtPromotionType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type candidates for the product_type_id: " + parentTypeId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwPromotionProductType> findHistoricRelatedEntityTypesView(Integer parentTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionProductType> result = null;
		String errorMessage;
		try {
			result = create.select().from(VW_PROMOTION_PRODUCT_TYPE)
					.where(VW_PROMOTION_PRODUCT_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.orderBy(VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_CODE,
							VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_START_DATE)
					.fetch().into(VwPromotionProductType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of promotion types for the product_type_id : " + parentTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwPromotionProductType> findRelatedEntityTypesByDateView(Integer parentTypeId, LocalDateTime searchDate)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionProductType> result = null;
		String errorMessage;
		try {
			result = create.select().from(VW_PROMOTION_PRODUCT_TYPE)
					.where(VW_PROMOTION_PRODUCT_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId))
							.and(val(searchDate).between(VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_START_DATE,
									VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_END_DATE)))
					.orderBy(VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_CODE,
							VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_START_DATE)
					.fetch().into(VwPromotionProductType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of promotion types for the product_type_id : " + parentTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromoProdType findEntityRelationType(Integer entityRelationTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoProdType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROMO_PROD_TYPE)
					.where(CT_PROMO_PROD_TYPE.PROMO_PROD_TYPE_ID.eq(val(entityRelationTypeId))).fetch()
					.into(CtPromoProdType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product promotion type for the product_promotion_type_id : "
						+ entityRelationTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the product promotion type for the product_promotion_type_id : "
					+ entityRelationTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtPromoProdType findEntityRelationType(Integer parentTypeId, Integer childTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoProdType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROMO_PROD_TYPE).where(CT_PROMO_PROD_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.and(CT_PROMO_PROD_TYPE.PROMOTION_TYPE_ID.eq(val(childTypeId))).fetch().into(CtPromoProdType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product promotion type for the product_type_id : "
						+ parentTypeId + " and promotion_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the promotion types for the product_type_id : " + parentTypeId
					+ " and promotion_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwPromotionProductType findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionProductType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(VW_PROMOTION_PRODUCT_TYPE)
					.where(VW_PROMOTION_PRODUCT_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.and(VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_ID.eq(val(childTypeId)))
					.orderBy(VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_CODE,
							VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_START_DATE)
					.fetch().into(VwPromotionProductType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the view of product promotion type for the product_type_id : "
						+ parentTypeId + " and promotion_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the promotion types for the product_type_id : " + parentTypeId
					+ " and promotion_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtPromoProdType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoProdTypeDao daoObject = new CtPromoProdTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the product promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtPromoProdType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoProdTypeDao daoObject = new CtPromoProdTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the product promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtPromoProdType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoProdTypeDao daoObject = new CtPromoProdTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the product promotion type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
