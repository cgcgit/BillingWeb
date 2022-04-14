package com.billingweb.ejb.catalog.relationType;

import static com.billingweb.model.Tables.CT_SERVICE_TYPE;
import static com.billingweb.model.Tables.VW_PRODUCT_SERVICE_TYPE;
import static com.billingweb.model.Tables.CT_PROD_SERV_TYPE;
import static com.billingweb.model.Tables.PT_STATUS;
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
import com.billingweb.model.tables.daos.CtProdServTypeDao;
import com.billingweb.model.tables.pojos.CtProdServType;
import com.billingweb.model.tables.pojos.CtServiceType;
import com.billingweb.model.tables.pojos.VwProductServiceType;

/**
 * Session Bean implementation class ProductServiceTypeEJB
 */
@Stateless
public class ProductServiceTypeEJB implements ProductServiceTypeEJBLocal {
	

	Logger logger = (Logger) LogManager.getLogger(ProductServiceTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.billingweb.properties.dataBaseDefinitions");

	
    /**
     * Default constructor. 
     */
    public ProductServiceTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtServiceType> findEntityTypeCandidates(Integer parentId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;
		// aliases of tables		
		com.billingweb.model.tables.CtServiceType svt = CT_SERVICE_TYPE.as("svt");
		com.billingweb.model.tables.CtProdServType pst = CT_PROD_SERV_TYPE.as("pst");

		try {
			result = create.select()
					.from(svt)
					.whereNotExists(create.selectOne()
							.from(pst)
							.where(svt.SERVICE_TYPE_ID.eq(pst.SERVICE_TYPE_ID).and(pst.PRODUCT_TYPE_ID.eq(parentId))))
					.orderBy(svt.CODE).fetch().into(CtServiceType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service type candidates for the product_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtServiceType> findEntityTypeCandidates(Integer parentId, String statusCode) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;
		// aliases of tables
		com.billingweb.model.tables.PtStatus st = PT_STATUS.as("st");
		com.billingweb.model.tables.CtServiceType svt = CT_SERVICE_TYPE.as("svt");
		com.billingweb.model.tables.CtProdServType pst = CT_PROD_SERV_TYPE.as("pst");

		try {
			result = create.select()
					.from(svt)
					.whereNotExists(create.selectOne()
							.from(pst.join(st).on(pst.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(svt.SERVICE_TYPE_ID.eq(pst.SERVICE_TYPE_ID).and(pst.PRODUCT_TYPE_ID.eq(parentId))))
					.orderBy(svt.CODE).fetch().into(CtServiceType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the product_type_id: " + parentId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<VwProductServiceType> findRelatedEntityTypesView(Integer parentId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductServiceType> result = null;
		String errorMessage;
		try {
			result = create.select()
					.from(VW_PRODUCT_SERVICE_TYPE)
					.where(VW_PRODUCT_SERVICE_TYPE.PRODUCT_TYPE_ID.eq(val(parentId)))
					.orderBy(VW_PRODUCT_SERVICE_TYPE.SERVICE_TYPE_CODE)
                    .fetch().into(VwProductServiceType.class);							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of service types for the product_type_id : " + parentId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public CtProdServType findEntityRelationType (Integer entityRelationTypeId) throws BillingWebDataAccessException{
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProdServType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROD_SERV_TYPE)
					.where(CT_PROD_SERV_TYPE.PROD_SERV_TYPE_ID.eq(val(entityRelationTypeId)))
					.fetch()
					.into(CtProdServType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the product service type for the product_service_type_id : " + entityRelationTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the product service type for the product_service_type_id : " + entityRelationTypeId  + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtProdServType findEntityRelationType(Integer parentId, Integer childId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProdServType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(CT_PROD_SERV_TYPE)
					.where(CT_PROD_SERV_TYPE.PRODUCT_TYPE_ID.eq(val(parentId))).
					and(CT_PROD_SERV_TYPE.SERVICE_TYPE_ID.eq(val(childId)))					
					.fetch()
					.into(CtProdServType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the product service type for the product_type_id : " + parentId
						+ " and service_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service types for the product_type_id : " + parentId + 
					" and fee_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		
	}
	
	@Override
	public VwProductServiceType findEntityRelationTypeView (Integer parentId, Integer childId) throws BillingWebDataAccessException{
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductServiceType> result = null;
		String errorMessage;
		// aliases of tables
		
		try {
			result = create.selectFrom(VW_PRODUCT_SERVICE_TYPE)
					.where(VW_PRODUCT_SERVICE_TYPE.PRODUCT_TYPE_ID.eq(val(parentId))).
					and(VW_PRODUCT_SERVICE_TYPE.SERVICE_TYPE_ID.eq(val(childId)))
					.orderBy(VW_PRODUCT_SERVICE_TYPE.PROD_SERV_TYPE_ID)
					.fetch()
					.into(VwProductServiceType.class);
			
			if (result.size()>1) {
				errorMessage = "Error while try to find the view of product fee type for the product_type_id : " + parentId
						+ " and service_type_id: " + childId + " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else
			{
				return result.get(0);
			}
							

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service types for thhe product_type_id : " + parentId + 
					" and service_type_id: " + childId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		
	}

	@Override
	public void insertData(CtProdServType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtProdServTypeDao daoObject = new CtProdServTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the product service type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtProdServType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {			
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtProdServTypeDao daoObject = new CtProdServTypeDao(configuration);			
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the product service type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtProdServType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtProdServTypeDao daoObject = new CtProdServTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the product service type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

}
