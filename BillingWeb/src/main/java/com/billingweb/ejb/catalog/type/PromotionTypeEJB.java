package com.billingweb.ejb.catalog.type;

import static com.billingweb.model.Tables.CT_PROMOTION_TYPE;
import static com.billingweb.model.Tables.PT_APPLICATION_LEVEL;
import static org.jooq.impl.DSL.val;

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

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.daos.CtPromotionTypeDao;
import com.billingweb.model.tables.pojos.CtPromotionType;
import com.billingweb.model.tables.pojos.PtApplicationLevel;

/**
 * Session Bean implementation class PromotiontTypeEJB
 */
@Stateless
public class PromotionTypeEJB implements PromotionTypeEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(PromotionTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

    /**
     * Default constructor. 
     */
    public PromotionTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtPromotionType> findAllData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.orderBy(CT_PROMOTION_TYPE.CODE)
					.fetch()
					.into(CtPromotionType.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the promotion types - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtPromotionType findDataByPromotionTypeId(Integer promotionTypeId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;
		
		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(CT_PROMOTION_TYPE.PROMOTION_TYPE_ID.eq(val(promotionTypeId)))
					.orderBy(CT_PROMOTION_TYPE.CODE)
					.fetch()
					.into(CtPromotionType.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion type for promotion_type_id : " + promotionTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for promotion_type_id: " + promotionTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);		
		}
		
	}

	@Override
	public CtPromotionType findDataByCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;
		

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(CT_PROMOTION_TYPE.CODE.eq(val(code)))
					.orderBy(CT_PROMOTION_TYPE.CODE)
					.fetch()
					.into(CtPromotionType.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the promotion type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}
	
	@Override
	public List<CtPromotionType> findDataByApplicationLevelId(Integer applicationLevelId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtPromotionType> result = null;
		String errorMessage;
		

		try {
			result = create.selectFrom(CT_PROMOTION_TYPE)
					.where(CT_PROMOTION_TYPE.APPLICATION_LEVEL_ID.eq(val(applicationLevelId)))
					.orderBy(CT_PROMOTION_TYPE.CODE)
					.fetch()
					.into(CtPromotionType.class);
				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for application_level_id " + applicationLevelId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
		return result;
	}

	@Override
	public List<CtPromotionType> findDataByApplicationLevelCode(String applicationLevelCode)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);		
		List<CtPromotionType> result = null;
		Map<CtPromotionType, List<PtApplicationLevel>> record; 
		String errorMessage;
		// aliases of tables		
		com.billingweb.model.tables.CtPromotionType pt = CT_PROMOTION_TYPE.as("pt");
				com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(pt)
							.on(pt.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(applicationLevelCode))))		
					        .orderBy(pt.CODE).fetchGroups(r-> r.into(pt).into(CtPromotionType.class),
					        		r -> r.into(al).into(PtApplicationLevel.class)
					        		);
			
			result =  new ArrayList<CtPromotionType>();  
			result.addAll(record.keySet());
			
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the promotion type for the application_level_code " + applicationLevelCode
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}


	@Override
	public void insertData(CtPromotionType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtPromotionTypeDao daoObject = new CtPromotionTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the promotion type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
		
	}

	@Override
	public void updateData(CtPromotionType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtPromotionTypeDao daoObject = new CtPromotionTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the promotion type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtPromotionType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtPromotionTypeDao daoObject = new CtPromotionTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the promotion type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

	
}
