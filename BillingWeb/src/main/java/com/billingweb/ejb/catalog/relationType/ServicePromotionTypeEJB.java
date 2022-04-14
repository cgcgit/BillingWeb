package com.billingweb.ejb.catalog.relationType;

import static com.billingweb.model.Tables.CT_PROMOTION_TYPE;
import static com.billingweb.model.Tables.CT_PROMO_SERV_TYPE;
import static com.billingweb.model.Tables.PT_APPLICATION_LEVEL;
import static com.billingweb.model.Tables.PT_STATUS;
import static com.billingweb.model.Tables.VW_PROMOTION_SERVICE_TYPE;
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
import com.billingweb.model.tables.daos.CtPromoServTypeDao;
import com.billingweb.model.tables.pojos.CtPromoServType;
import com.billingweb.model.tables.pojos.CtPromotionType;
import com.billingweb.model.tables.pojos.PtApplicationLevel;
import com.billingweb.model.tables.pojos.VwPromotionServiceType;

/**
 * Session Bean implementation class ServicePromotionType
 */
@Stateless
public class ServicePromotionTypeEJB implements ServicePromotionTypeEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(ServicePromotionTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.billingweb.properties.dataBaseDefinitions");

	
	protected static String APPLICATION_LEVEL_CODE_SERV = dbDefinitions.getString("APPLICATION_LEVEL_CODE_SERV");

    /**
     * Default constructor. 
     */
    public ServicePromotionTypeEJB() {
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
		com.billingweb.model.tables.CtPromoServType pst = CT_PROMO_SERV_TYPE.as("pst");
		com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(pt)
					.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_SERV))))	
					.whereNotExists(create.selectOne()
							.from(pst)
							.where(pt.PROMOTION_TYPE_ID.eq(pst.PROMOTION_TYPE_ID).and(pst.SERVICE_TYPE_ID.eq(parentId))))
					.orderBy(pt.CODE).fetchGroups(r-> r.into(pt).into(CtPromotionType.class),
			        		r -> r.into(al).into(PtApplicationLevel.class)
			        		);
			
			result =  new ArrayList<CtPromotionType>();  
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type candidates for the service_type_id : " + parentId
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
		com.billingweb.model.tables.CtPromoServType pst = CT_PROMO_SERV_TYPE.as("pst");
		com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(pt)
					.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_SERV))))	
					.whereNotExists(create.selectOne()
							.from(pst.join(st).on(pst.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(pt.PROMOTION_TYPE_ID.eq(pst.PROMOTION_TYPE_ID).and(pst.SERVICE_TYPE_ID.eq(parentId))))
					.orderBy(pt.CODE).fetchGroups(r-> r.into(pt).into(CtPromotionType.class),
			        		r -> r.into(al).into(PtApplicationLevel.class)
			        		);
			
			result =  new ArrayList<CtPromotionType>();  
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type candidates for the service_type_id : " + parentId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}


	@Override
	public List<VwPromotionServiceType> findRelatedEntityTypesView(Integer parentId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionServiceType> result = null;
		String errorMessage;
		try {
			result = create.select()
					.from(VW_PROMOTION_SERVICE_TYPE)
					.where(VW_PROMOTION_SERVICE_TYPE.SERVICE_TYPE_ID.eq(val(parentId)))
					.orderBy(VW_PROMOTION_SERVICE_TYPE.PROMOTION_TYPE_CODE)
                    .fetch().into(VwPromotionServiceType.class);							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of promotion types for the service_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromoServType findEntityRelationType(Integer entityRelationTypeId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoServType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_SERV_TYPE)
					.where(CT_PROMO_SERV_TYPE.PROMO_SERV_TYPE_ID.eq(val(entityRelationTypeId)))
					.fetch()
					.into(CtPromoServType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the service promotion type for the service_promotion_type_id : " + entityRelationTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service promotion type for the service_promotion_type_id : " + entityRelationTypeId  + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtPromoServType findEntityRelationType(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromoServType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROMO_SERV_TYPE)
					.where(CT_PROMO_SERV_TYPE.SERVICE_TYPE_ID.eq(val(parentId))).
					and(CT_PROMO_SERV_TYPE.PROMOTION_TYPE_ID.eq(val(childId)))					
					.fetch()
					.into(CtPromoServType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the service promotion type for the service_type_id : " + parentId
						+ " and promotion_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion types for the service_type_id : " + parentId + 
					" and promotion_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwPromotionServiceType findEntityRelationTypeView(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwPromotionServiceType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(VW_PROMOTION_SERVICE_TYPE)
					.where(VW_PROMOTION_SERVICE_TYPE.SERVICE_TYPE_ID.eq(val(parentId))).
					and(VW_PROMOTION_SERVICE_TYPE.PROMOTION_TYPE_ID.eq(val(childId)))
					.orderBy(VW_PROMOTION_SERVICE_TYPE.PROMO_SERV_TYPE_ID)
					.fetch()
					.into(VwPromotionServiceType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the view of service promotion type for the service_type_id : " + parentId
						+ " and promotion_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else
			{
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion types for the service_type_id : " + parentId + 
					" and promotion_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtPromoServType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoServTypeDao daoObject = new CtPromoServTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the service promotion type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtPromoServType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromoServTypeDao daoObject = new CtPromoServTypeDao(configuration);			
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the service promotion type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtPromoServType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtPromoServTypeDao daoObject = new CtPromoServTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the service promotion type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

}
