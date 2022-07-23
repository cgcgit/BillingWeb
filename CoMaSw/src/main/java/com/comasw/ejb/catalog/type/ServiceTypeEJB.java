package com.comasw.ejb.catalog.type;

import static com.comasw.model.Tables.CT_SERVICE_TYPE;
import static org.jooq.impl.DSL.val;

import java.util.List;

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

import com.comasw.model.tables.daos.CtServiceTypeDao;
import com.comasw.model.tables.pojos.CtServiceType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class ServiceTypeEJB
 */
@Stateless
public class ServiceTypeEJB implements ServiceTypeEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(ServiceTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

    /**
     * Default constructor. 
     */
    public ServiceTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtServiceType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_SERVICE_TYPE)
					.orderBy(CT_SERVICE_TYPE.CODE)
					.fetch()
					.into(CtServiceType.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtServiceType findDataByServiceTypeId(Integer serviceTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;
		
		try {
			result = create.selectFrom(CT_SERVICE_TYPE)
					.where(CT_SERVICE_TYPE.SERVICE_TYPE_ID.eq(val(serviceTypeId)))
					.orderBy(CT_SERVICE_TYPE.CODE)
					.fetch()
					.into(CtServiceType.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the service type for service_type_id : " + serviceTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service type for service_type_id: " + serviceTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);		
		}

		
	}

	@Override
	public CtServiceType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;
		

		try {
			result = create.selectFrom(CT_SERVICE_TYPE)
					.where(CT_SERVICE_TYPE.CODE.eq(val(code)))
					.orderBy(CT_SERVICE_TYPE.CODE)
					.fetch()
					.into(CtServiceType.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the service type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void insertData(CtServiceType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;		
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtServiceTypeDao daoObject = new CtServiceTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the service type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtServiceType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtServiceTypeDao daoObject = new CtServiceTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the service type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtServiceType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtServiceTypeDao daoObject = new CtServiceTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the service type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);			
		}
		
	}

}
