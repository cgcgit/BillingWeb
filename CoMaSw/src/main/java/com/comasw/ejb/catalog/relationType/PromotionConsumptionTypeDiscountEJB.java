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

package com.comasw.ejb.catalog.relationType;

import static com.comasw.model.Tables.CT_PROMO_CONSUM_TYPE_DISCOUNT;
import static com.comasw.model.Tables.CT_CONSUMPTION_TYPE;
import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.VW_PROMO_CONSUM_TYPE_DISCOUNT;
import static org.jooq.impl.DSL.val;

import java.util.List;
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

import com.comasw.model.tables.daos.CtPromoConsumTypeDiscountDao;
import com.comasw.model.tables.pojos.CtConsumptionType;
import com.comasw.model.tables.pojos.CtPromoConsumTypeDiscount;
import com.comasw.model.tables.pojos.VwPromoConsumTypeDiscount;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class PromotionConsumptionTypeEJB
 */
@Stateless
public class PromotionConsumptionTypeDiscountEJB implements PromotionConsumptionTypeDiscountEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(PromotionConsumptionTypeDiscountEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.comasw.properties.dataBaseDefinitions");

	/**
	 * Default constructor.
	 */
	public PromotionConsumptionTypeDiscountEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtConsumptionType> result = null;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtConsumptionType ct = CT_CONSUMPTION_TYPE.as("ct");
		com.comasw.model.tables.CtPromoConsumTypeDiscount pct = CT_PROMO_CONSUM_TYPE_DISCOUNT.as("pct");

		try {
			result = create.select().from(ct)
					.whereNotExists(create.selectOne().from(pct).where(
							ct.CONSUMPTION_TYPE_ID.eq(pct.CONSUMPTION_TYPE_ID).and(pct.PROMOTION_TYPE_ID.eq(parentTypeId))))
					.orderBy(ct.CODE).fetch().into(CtConsumptionType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption type candidates to discount for the promotion_type_id : "
					+ parentTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<CtConsumptionType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtConsumptionType> result = null;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtConsumptionType ct = CT_CONSUMPTION_TYPE.as("ct");
		com.comasw.model.tables.CtPromoConsumTypeDiscount pct = CT_PROMO_CONSUM_TYPE_DISCOUNT.as("pct");

		try {
			result = create.select().from(ct)
					.whereExists(create.selectOne()
							.from(pct.join(st).on(pct.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ct.CONSUMPTION_TYPE_ID.eq(pct.CONSUMPTION_TYPE_ID)
									.and(pct.PROMOTION_TYPE_ID.eq(parentTypeId))))
					.orderBy(ct.CODE).fetch().into(CtConsumptionType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption type candidates for the product_type_id: "
					+ parentTypeId + " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtConsumptionType> result = null;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtConsumptionType ct = CT_CONSUMPTION_TYPE.as("ct");
		com.comasw.model.tables.CtPromoConsumTypeDiscount pct = CT_PROMO_CONSUM_TYPE_DISCOUNT.as("pct");

		try {
			result = create.select().from(ct)
					.whereNotExists(create.selectOne()
							.from(pct.join(st).on(pct.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ct.CONSUMPTION_TYPE_ID.eq(pct.CONSUMPTION_TYPE_ID)
									.and(pct.PROMOTION_TYPE_ID.eq(parentTypeId))))
					.orderBy(ct.CODE).fetch().into(CtConsumptionType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption type candidates for the product_type_id: "
					+ parentTypeId + " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwPromoConsumTypeDiscount> findRelatedEntityTypesView(Integer parentTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.select().from(VW_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(VW_PROMO_CONSUM_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.orderBy(VW_PROMO_CONSUM_TYPE_DISCOUNT.CONSUMPTION_TYPE_CODE).fetch()
					.into(VwPromoConsumTypeDiscount.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of consumption type candidates for the promotion_type_id : "
					+ parentTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromoConsumTypeDiscount findEntityRelationType(Integer entityRelationTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(CT_PROMO_CONSUM_TYPE_DISCOUNT.PROMO_CONSUM_TYPE_DISCOUNT_ID.eq(val(entityRelationTypeId)))
					.fetch().into(CtPromoConsumTypeDiscount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion consumption type for the promotion_consumption_type_id : "
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
			errorMessage = "Error while try to find the promotion consumption type for the promotion_consumption_type_id : "
					+ entityRelationTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtPromoConsumTypeDiscount findEntityRelationType(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(CT_PROMO_CONSUM_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.and(CT_PROMO_CONSUM_TYPE_DISCOUNT.CONSUMPTION_TYPE_ID.eq(val(childTypeId))).fetch()
					.into(CtPromoConsumTypeDiscount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion consumption type for the promotion_type_id : "
						+ parentTypeId + " and consumption_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the consumption types for the promotion_type_id : " + parentTypeId
					+ " and consumption_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwPromoConsumTypeDiscount findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(VW_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(VW_PROMO_CONSUM_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.and(VW_PROMO_CONSUM_TYPE_DISCOUNT.CONSUMPTION_TYPE_ID.eq(val(childTypeId)))
					.orderBy(VW_PROMO_CONSUM_TYPE_DISCOUNT.PROMO_CONSUM_TYPE_DISCOUNT_ID).fetch()
					.into(VwPromoConsumTypeDiscount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the view of promotion consumption type for the promotion_type_id : "
						+ parentTypeId + " and consumption_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the consumption types for the promotion_type_id : " + parentTypeId
					+ " and fee_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtPromoConsumTypeDiscount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoConsumTypeDiscountDao daoObject = new CtPromoConsumTypeDiscountDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the promotion consumption type object (value: " + dataObject.toString()
					+ ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtPromoConsumTypeDiscount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoConsumTypeDiscountDao daoObject = new CtPromoConsumTypeDiscountDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the promotion consumption type object (value: " + dataObject.toString()
					+ ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtPromoConsumTypeDiscount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoConsumTypeDiscountDao daoObject = new CtPromoConsumTypeDiscountDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the promotion consumption type object (value: " + dataObject.toString()
					+ ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

}
