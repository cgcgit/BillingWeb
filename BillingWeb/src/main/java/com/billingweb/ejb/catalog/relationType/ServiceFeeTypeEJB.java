package com.billingweb.ejb.catalog.relationType;

import static com.billingweb.model.Tables.CT_FEE_TYPE;
import static com.billingweb.model.Tables.CT_SERV_FEE_TYPE;
import static com.billingweb.model.Tables.PT_APPLICATION_LEVEL;
import static com.billingweb.model.Tables.PT_STATUS;
import static com.billingweb.model.Tables.VW_SERVICE_FEE_TYPE;
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
import com.billingweb.model.tables.daos.CtServFeeTypeDao;
import com.billingweb.model.tables.pojos.CtFeeType;
import com.billingweb.model.tables.pojos.CtServFeeType;
import com.billingweb.model.tables.pojos.PtApplicationLevel;
import com.billingweb.model.tables.pojos.VwServiceFeeType;

/**
 * Session Bean implementation class ServiceFeeTypeEJB
 */
@Stateless
public class ServiceFeeTypeEJB implements ServiceFeeTypeEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(ServiceFeeTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.billingweb.properties.dataBaseDefinitions");

	protected static String APPLICATION_LEVEL_CODE_SERV = dbDefinitions.getString("APPLICATION_LEVEL_CODE_SERV");
	
	
    /**
     * Default constructor. 
     */
    public ServiceFeeTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtFeeType> findEntityTypeCandidates(Integer parentId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);		
		List<CtFeeType> result = null;
		Map<CtFeeType, List<PtApplicationLevel>> record; 
		String errorMessage;
		// aliases of tables		
		com.billingweb.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.billingweb.model.tables.CtServFeeType sft = CT_SERV_FEE_TYPE.as("pft");
		com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(ft)
							.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_SERV))))								
					.whereNotExists(create.selectOne()
							.from(sft)
							.where(ft.FEE_TYPE_ID.eq(sft.FEE_TYPE_ID).and(sft.SERVICE_TYPE_ID.eq(parentId))))
					        .orderBy(ft.CODE).fetchGroups(r-> r.into(ft).into(CtFeeType.class),
					        		r -> r.into(al).into(PtApplicationLevel.class)
					        		);
			
			result =  new ArrayList<CtFeeType>();  
			result.addAll(record.keySet());
			
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the service_type_id : " + parentId
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
		Map<CtFeeType, List<PtApplicationLevel>> record; 
		String errorMessage;
		// aliases of tables
		com.billingweb.model.tables.PtStatus st = PT_STATUS.as("st");
		com.billingweb.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.billingweb.model.tables.CtServFeeType sft = CT_SERV_FEE_TYPE.as("pft");
		com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(ft)
							.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_SERV))))								
					.whereNotExists(create.selectOne()
							.from(sft.join(st).on(sft.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ft.FEE_TYPE_ID.eq(sft.FEE_TYPE_ID).and(sft.SERVICE_TYPE_ID.eq(parentId))))
					        .orderBy(ft.CODE).fetchGroups(r-> r.into(ft).into(CtFeeType.class),
					        		r -> r.into(al).into(PtApplicationLevel.class)
					        		);
			
			result =  new ArrayList<CtFeeType>();  
			result.addAll(record.keySet());
			
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the service_type_id : " + parentId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	
	@Override
	public List<VwServiceFeeType> findRelatedEntityTypesView(Integer parentId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwServiceFeeType> result = null;
		String errorMessage;
		try {
			result = create.select()
					.from(VW_SERVICE_FEE_TYPE)
					.where(VW_SERVICE_FEE_TYPE.SERVICE_TYPE_ID.eq(val(parentId)))
					.orderBy(VW_SERVICE_FEE_TYPE.FEE_TYPE_CODE)
                    .fetch().into(VwServiceFeeType.class);							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of fee types for the service_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtServFeeType findEntityRelationType(Integer entityRelationTypeId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServFeeType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_SERV_FEE_TYPE)
					.where(CT_SERV_FEE_TYPE.SERV_FEE_TYPE_ID.eq(val(entityRelationTypeId)))
					.fetch()
					.into(CtServFeeType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the service fee type for the service_fee_type_id : " + entityRelationTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service fee type for the service_fee_type_id : " + entityRelationTypeId  + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtServFeeType findEntityRelationType(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServFeeType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_SERV_FEE_TYPE)
					.where(CT_SERV_FEE_TYPE.SERVICE_TYPE_ID.eq(val(parentId))).
					and(CT_SERV_FEE_TYPE.FEE_TYPE_ID.eq(val(childId)))					
					.fetch()
					.into(CtServFeeType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the service fee type for the service_type_id : " + parentId
						+ " and fee_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee types for the service_type_id : " + parentId + 
					" and fee_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwServiceFeeType findEntityRelationTypeView(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwServiceFeeType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(VW_SERVICE_FEE_TYPE)
					.where(VW_SERVICE_FEE_TYPE.SERVICE_TYPE_ID.eq(val(parentId))).
					and(VW_SERVICE_FEE_TYPE.FEE_TYPE_ID.eq(val(childId)))
					.orderBy(VW_SERVICE_FEE_TYPE.SERV_FEE_TYPE_ID)
					.fetch()
					.into(VwServiceFeeType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the view of service fee type for the service_type_id : " + parentId
						+ " and fee_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else
			{
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee types for the service_type_id : " + parentId + 
					" and fee_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtServFeeType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtServFeeTypeDao daoObject = new CtServFeeTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the service fee type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtServFeeType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtServFeeTypeDao daoObject = new CtServFeeTypeDao(configuration);			
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the service fee type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
		
	}

	@Override
	public void deleteData(CtServFeeType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtServFeeTypeDao daoObject = new CtServFeeTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the service fee type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
		
	}

}
