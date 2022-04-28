package com.billingweb.ejb.catalog.relationType;

import static com.billingweb.model.Tables.CT_PROMO_CONSUM_TYPE_DISCOUNT;
import static com.billingweb.model.Tables.CT_CONSUMPTION_TYPE;
import static com.billingweb.model.Tables.PT_STATUS;
import static com.billingweb.model.Tables.VW_PROMO_CONSUM_TYPE_DISCOUNT;
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
import com.billingweb.model.tables.daos.CtPromoConsumTypeDiscountDao;
import com.billingweb.model.tables.pojos.CtConsumptionType;
import com.billingweb.model.tables.pojos.CtPromoConsumTypeDiscount;
import com.billingweb.model.tables.pojos.VwPromoConsumTypeDiscount;


/**
 * Session Bean implementation class PromotionConsumptionTypeEJB
 */
@Stateless
public class PromotionConsumptionTypeDiscountEJB implements PromotionConsumptionTypeDiscountEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(PromotionConsumptionTypeDiscountEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.billingweb.properties.dataBaseDefinitions");
			

    /**
     * Default constructor. 
     */
    public PromotionConsumptionTypeDiscountEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentId) throws BillingWebDataAccessException {
		
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);		
		List<CtConsumptionType> result = null;		
		String errorMessage;
		// aliases of tables		
		com.billingweb.model.tables.CtConsumptionType ct = CT_CONSUMPTION_TYPE.as("ct");
		com.billingweb.model.tables.CtPromoConsumTypeDiscount pct = CT_PROMO_CONSUM_TYPE_DISCOUNT.as("pct");
		
		try {
			result = create.select()
					.from(ct)												
					.whereNotExists(create.selectOne()
							.from(pct)
							.where(ct.CONSUMPTION_TYPE_ID.eq(pct.CONSUMPTION_TYPE_ID).and(pct.PROMOTION_TYPE_ID.eq(parentId))))
					        .orderBy(ct.CODE).fetch().into(CtConsumptionType.class);
			
						
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption type candidates to discount for the promotion_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentId, String statusCode) throws BillingWebDataAccessException {
		
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);		
		List<CtConsumptionType> result = null;		
		String errorMessage;
		// aliases of tables
		com.billingweb.model.tables.PtStatus st = PT_STATUS.as("st");
		com.billingweb.model.tables.CtConsumptionType ct = CT_CONSUMPTION_TYPE.as("ct");
		com.billingweb.model.tables.CtPromoConsumTypeDiscount pct = CT_PROMO_CONSUM_TYPE_DISCOUNT.as("pct");
		
		try {
			result = create.select()
					.from(ct)												
					.whereNotExists(create.selectOne()
							.from(pct.join(st).on(pct.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ct.CONSUMPTION_TYPE_ID.eq(pct.CONSUMPTION_TYPE_ID).and(pct.PROMOTION_TYPE_ID.eq(parentId))))
					        .orderBy(ct.CODE).fetch().into(CtConsumptionType.class);
			
						
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption type candidates for the product_type_id: " + parentId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<VwPromoConsumTypeDiscount> findRelatedEntityTypesView(Integer parentId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.select()
					.from(VW_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(VW_PROMO_CONSUM_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentId)))
					.orderBy(VW_PROMO_CONSUM_TYPE_DISCOUNT.CONSUMPTION_TYPE_CODE)
                    .fetch().into(VwPromoConsumTypeDiscount.class);							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of consumption type candidates for the promotion_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromoConsumTypeDiscount findEntityRelationType(Integer entityRelationTypeId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(CT_PROMO_CONSUM_TYPE_DISCOUNT.PROMO_CONSUM_TYPE_DISCOUNT_ID.eq(val(entityRelationTypeId)))
					.fetch()
					.into(CtPromoConsumTypeDiscount.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the promotion consumption type for the promotion_consumption_type_id : " + entityRelationTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion consumption type for the promotion_consumption_type_id : " + entityRelationTypeId  + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtPromoConsumTypeDiscount findEntityRelationType(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(CT_PROMO_CONSUM_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentId))).
					and(CT_PROMO_CONSUM_TYPE_DISCOUNT.CONSUMPTION_TYPE_ID.eq(val(childId)))					
					.fetch()
					.into(CtPromoConsumTypeDiscount.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the promotion consumption type for the promotion_type_id : " + parentId
						+ " and consumption_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption types for the promotion_type_id : " + parentId + 
					" and consumption_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwPromoConsumTypeDiscount findEntityRelationTypeView(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromoConsumTypeDiscount> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(VW_PROMO_CONSUM_TYPE_DISCOUNT)
					.where(VW_PROMO_CONSUM_TYPE_DISCOUNT.PROMOTION_TYPE_ID.eq(val(parentId))).
					and(VW_PROMO_CONSUM_TYPE_DISCOUNT.CONSUMPTION_TYPE_ID.eq(val(childId)))
					.orderBy(VW_PROMO_CONSUM_TYPE_DISCOUNT.PROMO_CONSUM_TYPE_DISCOUNT_ID)
					.fetch()
					.into(VwPromoConsumTypeDiscount.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the view of promotion consumption type for the promotion_type_id : " + parentId
						+ " and consumption_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else
			{
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption types for the promotion_type_id : " + parentId + 
					" and fee_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtPromoConsumTypeDiscount dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoConsumTypeDiscountDao daoObject = new CtPromoConsumTypeDiscountDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the promotion consumption type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtPromoConsumTypeDiscount dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoConsumTypeDiscountDao daoObject = new CtPromoConsumTypeDiscountDao(configuration);			
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the promotion consumption type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtPromoConsumTypeDiscount dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtPromoConsumTypeDiscountDao daoObject = new CtPromoConsumTypeDiscountDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the promotion consumption type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
	}

}
