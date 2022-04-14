package com.billingweb.ejb.catalog.relationType;

import static com.billingweb.model.Tables.CT_FEE_TYPE;
import static com.billingweb.model.Tables.CT_PROMO_FEE_TYPE_DISCOUNT;
import static com.billingweb.model.Tables.PT_STATUS;
import static com.billingweb.model.Tables.VW_PROMOTION_FEE_TYPE_DISCOUNT;
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

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.daos.CtPromoFeeTypeDiscountDao;
import com.billingweb.model.tables.pojos.CtFeeType;
import com.billingweb.model.tables.pojos.CtPromoFeeTypeDiscount;
import com.billingweb.model.tables.pojos.VwPromotionFeeTypeDiscount;

/**
 * Session Bean implementation class PromotionFeeTypeEJB
 */
@Stateless
public class PromotionFeeTypeEJB implements PromotionFeeTypeEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(PromotionFeeTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.billingweb.properties.dataBaseDefinitions");

	

    /**
     * Default constructor. 
     */
    public PromotionFeeTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtFeeType> findEntityTypeCandidates(Integer parentId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);		
		List<CtFeeType> result = null;		
		String errorMessage;
		// aliases of tables		
		com.billingweb.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.billingweb.model.tables.CtPromoFeeTypeDiscount pft = CT_PROMO_FEE_TYPE_DISCOUNT.as("pft");
		
		try {
			result = create.select()
					.from(ft)												
					.whereNotExists(create.selectOne()
							.from(pft)
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PROMOTION_TYPE_ID.eq(parentId)).
									and (ft.APPLICATION_LEVEL_ID.eq(pft.APPLICATION_LEVEL_ID))))
					        .orderBy(ft.CODE).fetch().into(CtFeeType.class);
			
						
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates to discount for the promotion_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<CtFeeType> findEntityTypeCandidates(Integer parentId, String statusCode) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);		
		List<CtFeeType> result = null;		
		String errorMessage;
		// aliases of tables
		com.billingweb.model.tables.PtStatus st = PT_STATUS.as("st");
		com.billingweb.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.billingweb.model.tables.CtPromoFeeTypeDiscount pft = CT_PROMO_FEE_TYPE_DISCOUNT.as("pft");
		
		try {
			result = create.select()
					.from(ft)												
					.whereNotExists(create.selectOne()
							.from(pft.join(st).on(pft.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PROMOTION_TYPE_ID.eq(parentId)).
									and (ft.APPLICATION_LEVEL_ID.eq(pft.APPLICATION_LEVEL_ID))))
					        .orderBy(ft.CODE).fetch().into(CtFeeType.class);
			
						
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates to discount for the promotion_type_id : " + parentId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}


	@Override
	public List<VwPromotionFeeTypeDiscount> findRelatedEntityTypesView(Integer parentId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.select()
					.from(VW_PROMOTION_FEE_TYPE_DISCOUNT)
					.where(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentId)))
					.orderBy(VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_CODE)
                    .fetch().into(VwPromotionFeeTypeDiscount.class);							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of fee type candidates for the promotion_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromoFeeTypeDiscount findEntityRelationType(Integer entityRelationTypeId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_FEE_TYPE_DISCOUNT)
					.where(CT_PROMO_FEE_TYPE_DISCOUNT.PROMO_FEE_TYPE_DISCOUNT_ID.eq(val(entityRelationTypeId)))
					.fetch()
					.into(CtPromoFeeTypeDiscount.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the promotion fee type for the promotion_fee_type_id : " + entityRelationTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion fee type for the promotion_fee_type_id : " + entityRelationTypeId  + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtPromoFeeTypeDiscount findEntityRelationType(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_FEE_TYPE_DISCOUNT)
					.where(CT_PROMO_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentId))).
					and(CT_PROMO_FEE_TYPE_DISCOUNT.FEE_TYPE_ID.eq(val(childId)))					
					.fetch()
					.into(CtPromoFeeTypeDiscount.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the promotion fee type for the promotion_type_id : " + parentId
						+ " and fee_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee types for the promotion_type_id : " + parentId + 
					" and fee_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwPromotionFeeTypeDiscount findEntityRelationTypeView(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionFeeTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(VW_PROMOTION_FEE_TYPE_DISCOUNT)
					.where(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentId))).
					and(VW_PROMOTION_FEE_TYPE_DISCOUNT.FEE_TYPE_ID.eq(val(childId)))
					.orderBy(VW_PROMOTION_FEE_TYPE_DISCOUNT.PROMO_FEE_TYPE_DISCOUNT_ID)
					.fetch()
					.into(VwPromotionFeeTypeDiscount.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the view of promotion fee type for the promotion_type_id : " + parentId
						+ " and fee_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else
			{
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee types for the promotion_type_id : " + parentId + 
					" and fee_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtPromoFeeTypeDiscount dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoFeeTypeDiscountDao daoObject = new CtPromoFeeTypeDiscountDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the promotion fee type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtPromoFeeTypeDiscount dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoFeeTypeDiscountDao daoObject = new CtPromoFeeTypeDiscountDao(configuration);			
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the promotion fee type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtPromoFeeTypeDiscount dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtPromoFeeTypeDiscountDao daoObject = new CtPromoFeeTypeDiscountDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the promotion fee type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

}
