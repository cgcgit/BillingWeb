package com.comasw.ejb.catalog.relationType;

import static com.comasw.model.Tables.CT_FEE_TYPE;
import static com.comasw.model.Tables.CT_PROMOTION_TYPE;
import static com.comasw.model.Tables.CT_PROMO_FEE_TYPE_DISCOUNT;
import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.VW_PROMOTION_FEE_TYPE_DISCOUNT;
import static org.jooq.impl.DSL.val;
import static org.jooq.impl.DSL.min;

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

import com.comasw.model.tables.daos.CtPromoFeeTypeDiscountDao;
import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtPromoFeeTypeDiscount;
import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.model.tables.pojos.VwPromotionFeeTypeDiscount;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class PromotionFeeTypeEJB
 */
@Stateless
public class PromotionFeeTypeDiscountEJB implements PromotionFeeTypeDiscountEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(PromotionFeeTypeDiscountEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.comasw.properties.dataBaseDefinitions");

	/**
	 * Default constructor.
	 */
	public PromotionFeeTypeDiscountEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtFeeType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<CtPromotionType>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.CtFeeType ft2 = CT_FEE_TYPE.as("ft2");
		com.comasw.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.comasw.model.tables.CtPromotionType pt2 = CT_PROMOTION_TYPE.as("pt2");
		com.comasw.model.tables.CtPromoFeeTypeDiscount pft = CT_PROMO_FEE_TYPE_DISCOUNT.as("pft");

		try {
			record = create.select().from(pt)
					.join(ft).on(pt.APPLICATION_LEVEL_ID.eq(ft.APPLICATION_LEVEL_ID)
					    .and(ft.START_DATE.eq(create.select(min(ft2.START_DATE)).from(ft2).where(ft2.FEE_TYPE_ID.eq(ft.FEE_TYPE_ID)))))					    		
					.where(pt.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.and(pt.START_DATE.eq(create.select(min(pt2.START_DATE)).from(pt2).where(pt2.PROMOTION_TYPE_ID.eq(pt.PROMOTION_TYPE_ID))))
					.andNotExists(create.selectOne().from(pft)
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PROMOTION_TYPE_ID.eq(parentTypeId))
									.and(ft.APPLICATION_LEVEL_ID.eq(pft.APPLICATION_LEVEL_ID))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(pt).into(CtPromotionType.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates to discount for the promotion_type_id : "
					+ parentTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<CtFeeType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<CtPromotionType>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.comasw.model.tables.CtPromoFeeTypeDiscount pft = CT_PROMO_FEE_TYPE_DISCOUNT.as("pft");

		try {
			record = create.select().from(pt).join(ft).on(pt.APPLICATION_LEVEL_ID.eq(ft.APPLICATION_LEVEL_ID))
					.where(pt.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.andExists(create.selectOne()
							.from(pft.join(st).on(pft.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PROMOTION_TYPE_ID.eq(parentTypeId))
									.and(ft.APPLICATION_LEVEL_ID.eq(pft.APPLICATION_LEVEL_ID))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(pt).into(CtPromotionType.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates to discount for the promotion_type_id : "
					+ parentTypeId + " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtFeeType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<CtPromotionType>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.comasw.model.tables.CtPromoFeeTypeDiscount pft = CT_PROMO_FEE_TYPE_DISCOUNT.as("pft");

		try {
			record = create.select().from(pt).join(ft).on(pt.APPLICATION_LEVEL_ID.eq(ft.APPLICATION_LEVEL_ID))
					.where(pt.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.andNotExists(create.selectOne()
							.from(pft.join(st).on(pft.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PROMOTION_TYPE_ID.eq(parentTypeId))
									.and(ft.APPLICATION_LEVEL_ID.eq(pft.APPLICATION_LEVEL_ID))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(pt).into(CtPromotionType.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates to discount for the promotion_type_id : "
					+ parentTypeId + " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwPromotionFeeTypeDiscount> findHistoricRelatedEntityTypesView(Integer parentTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionFeeTypeDiscount> result = null;
		String errorMessage;
		try {
			result = create.select().from(VW_PROMOTION_FEE_TYPE_DISCOUNT)
					.where(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.orderBy(VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_CODE,
							VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_START_DATE)
					.fetch().into(VwPromotionFeeTypeDiscount.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of fee types for the promotion_type_id : " + parentTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwPromotionFeeTypeDiscount> findRelatedEntityTypesView(Integer parentTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.select().from(VW_PROMOTION_FEE_TYPE_DISCOUNT)
					.where(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.orderBy(VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_CODE).fetch()
					.into(VwPromotionFeeTypeDiscount.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of fee type candidates for the promotion_type_id : "
					+ parentTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwPromotionFeeTypeDiscount> findRelatedEntityTypesByDateView(Integer parentTypeId, LocalDateTime searchDate)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.select().from(VW_PROMOTION_FEE_TYPE_DISCOUNT)
					.where(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.and(val(searchDate).between(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_START_DATE,
							VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_END_DATE))
					.orderBy(VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_CODE,
							VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_START_DATE,
							VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_END_DATE,
							VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_START_DATE,
							VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_END_DATE)
					.fetch().into(VwPromotionFeeTypeDiscount.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of fee type candidates for the promotion_type_id : "
					+ parentTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromoFeeTypeDiscount findEntityRelationType(Integer entityRelationTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROMO_FEE_TYPE_DISCOUNT)
					.where(CT_PROMO_FEE_TYPE_DISCOUNT.PROMO_FEE_TYPE_DISCOUNT_ID.eq(val(entityRelationTypeId))).fetch()
					.into(CtPromoFeeTypeDiscount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion fee type for the promotion_fee_type_id : "
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
			errorMessage = "Error while try to find the promotion fee type for the promotion_fee_type_id : "
					+ entityRelationTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtPromoFeeTypeDiscount findEntityRelationType(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROMO_FEE_TYPE_DISCOUNT)
					.where(CT_PROMO_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.and(CT_PROMO_FEE_TYPE_DISCOUNT.FEE_TYPE_ID.eq(val(childTypeId))).fetch()
					.into(CtPromoFeeTypeDiscount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion fee type for the promotion_type_id : " + parentTypeId
						+ " and fee_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the fee types for the promotion_type_id : " + parentTypeId
					+ " and fee_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwPromotionFeeTypeDiscount findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(VW_PROMOTION_FEE_TYPE_DISCOUNT)
					.where(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentTypeId)))
					.and(VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_ID.eq(val(childTypeId)))
					.orderBy(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMO_FEE_TYPE_DISCOUNT_ID).fetch()
					.into(VwPromotionFeeTypeDiscount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the view of promotion fee type for the promotion_type_id : "
						+ parentTypeId + " and fee_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the fee types for the promotion_type_id : " + parentTypeId
					+ " and fee_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtPromoFeeTypeDiscount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoFeeTypeDiscountDao daoObject = new CtPromoFeeTypeDiscountDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the promotion fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtPromoFeeTypeDiscount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoFeeTypeDiscountDao daoObject = new CtPromoFeeTypeDiscountDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the promotion fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtPromoFeeTypeDiscount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtPromoFeeTypeDiscountDao daoObject = new CtPromoFeeTypeDiscountDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the promotion fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
