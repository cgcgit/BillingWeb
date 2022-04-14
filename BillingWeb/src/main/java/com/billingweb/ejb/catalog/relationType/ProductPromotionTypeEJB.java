package com.billingweb.ejb.catalog.relationType;

import static com.billingweb.model.Tables.PT_STATUS;
import static com.billingweb.model.Tables.VW_PROMOTION_PRODUCT_TYPE;
import static com.billingweb.model.Tables.CT_PROMOTION_TYPE;
import static com.billingweb.model.Tables.CT_PROMO_PROD_TYPE;
import static com.billingweb.model.Tables.PT_APPLICATION_LEVEL;
import static org.jooq.impl.DSL.val;

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

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.daos.CtPromoProdTypeDao;
import com.billingweb.model.tables.pojos.CtPromotionType;
import com.billingweb.model.tables.pojos.PtApplicationLevel;
import com.billingweb.model.tables.pojos.CtPromoProdType;
import com.billingweb.model.tables.pojos.VwPromotionProductType;


/**
 * Session Bean implementation class ProductTypeRelationsEJB
 */
@Stateless
public class ProductPromotionTypeEJB implements ProductPromotionTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ProductFeeTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.billingweb.properties.dataBaseDefinitions");

	
	protected static String APPLICATION_LEVEL_CODE_PROD = dbDefinitions.getString("APPLICATION_LEVEL_CODE_PROD");

	/**
	 * Default constructor.
	 */
	public ProductPromotionTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<CtPromotionType> findEntityTypeCandidates(Integer parentId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record; 
		String errorMessage;
		// aliases of tables		
		com.billingweb.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.billingweb.model.tables.CtPromoProdType ppt = CT_PROMO_PROD_TYPE.as("ppt");
		com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(pt)
					.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))	
					.whereNotExists(create.selectOne()
							.from(ppt)
							.where(pt.PROMOTION_TYPE_ID.eq(ppt.PROMOTION_TYPE_ID).and(ppt.PRODUCT_TYPE_ID.eq(parentId))))
					.orderBy(pt.CODE).fetchGroups(r-> r.into(pt).into(CtPromotionType.class),
			        		r -> r.into(al).into(PtApplicationLevel.class)
			        		);
			
			result =  new ArrayList<CtPromotionType>();  
			result.addAll(record.keySet());
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type candidates for the product_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<CtPromotionType> findEntityTypeCandidates(Integer parentId, String statusCode) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record; 
		String errorMessage;
		// aliases of tables
		com.billingweb.model.tables.PtStatus st = PT_STATUS.as("st");
		com.billingweb.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
		com.billingweb.model.tables.CtPromoProdType ppt = CT_PROMO_PROD_TYPE.as("ppt");
		com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(pt)
					.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))	
					.whereNotExists(create.selectOne()
							.from(ppt.join(st).on(ppt.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(pt.PROMOTION_TYPE_ID.eq(ppt.PROMOTION_TYPE_ID).and(ppt.PRODUCT_TYPE_ID.eq(parentId))))
					.orderBy(pt.CODE).fetchGroups(r-> r.into(pt).into(CtPromotionType.class),
			        		r -> r.into(al).into(PtApplicationLevel.class)
			        		);
			
			result =  new ArrayList<CtPromotionType>();  
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type candidates for the product_type_id : " + parentId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwPromotionProductType> findRelatedEntityTypesView(Integer parentId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionProductType> result = null;
		String errorMessage;
		try {
			result = create.select()
					.from(VW_PROMOTION_PRODUCT_TYPE)
					.where(VW_PROMOTION_PRODUCT_TYPE.PRODUCT_TYPE_ID.eq(val(parentId)))
					.orderBy(VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_CODE)
                    .fetch().into(VwPromotionProductType.class);							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of promotion types for the product_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromoProdType findEntityRelationType(Integer entityRelationTypeId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoProdType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_PROD_TYPE)
					.where(CT_PROMO_PROD_TYPE.PROMO_PROD_TYPE_ID.eq(val(entityRelationTypeId)))
					.fetch()
					.into(CtPromoProdType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the product promotion type for the product_promotion_type_id : " + entityRelationTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the product promotion type for the product_promotion_type_id : " + entityRelationTypeId  + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtPromoProdType findEntityRelationType(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoProdType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_PROD_TYPE)
					.where(CT_PROMO_PROD_TYPE.PRODUCT_TYPE_ID.eq(val(parentId))).
					and(CT_PROMO_PROD_TYPE.PROMOTION_TYPE_ID.eq(val(childId)))					
					.fetch()
					.into(CtPromoProdType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the product promotion type for the product_type_id : " + parentId
						+ " and promotion_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion types for the product_type_id : " + parentId + 
					" and promotion_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwPromotionProductType findEntityRelationTypeView(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionProductType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(VW_PROMOTION_PRODUCT_TYPE)
					.where(VW_PROMOTION_PRODUCT_TYPE.PRODUCT_TYPE_ID.eq(val(parentId))).
					and(VW_PROMOTION_PRODUCT_TYPE.PROMOTION_TYPE_ID.eq(val(childId)))
					.orderBy(VW_PROMOTION_PRODUCT_TYPE.PROMO_PROD_TYPE_ID)
					.fetch()
					.into(VwPromotionProductType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the view of product promotion type for the product_type_id : " + parentId
						+ " and promotion_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else
			{
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion types for the product_type_id : " + parentId + 
					" and promotion_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtPromoProdType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoProdTypeDao daoObject = new CtPromoProdTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the product promotion type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtPromoProdType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoProdTypeDao daoObject = new CtPromoProdTypeDao(configuration);			
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the product promotion type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtPromoProdType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtPromoProdTypeDao daoObject = new CtPromoProdTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the product promotion type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

}
