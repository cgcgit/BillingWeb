package com.comasw.ejb.catalog.type;


import static com.comasw.model.Tables.CT_CONSUMPTION_TYPE;
import static com.comasw.model.Tables.PT_CONSUMPTION_CLASS;
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

import com.comasw.model.tables.daos.CtConsumptionTypeDao;
import com.comasw.model.tables.pojos.CtConsumptionType;
import com.comasw.model.tables.pojos.PtConsumptionClass;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class ConsumptionTypeEJB
 */
@Stateless
public class ConsumptionTypeEJB implements ConsumptionTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ConsumptionTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	
    /**
     * Default constructor. 
     */
    public ConsumptionTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtConsumptionType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtConsumptionType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_CONSUMPTION_TYPE)
					.orderBy(CT_CONSUMPTION_TYPE.CODE)
					.fetch()
					.into(CtConsumptionType.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the consumption types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<PtConsumptionClass> findEntityTypeForConsumptions () throws CoMaSwDataAccessException{
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtConsumptionClass> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_CONSUMPTION_CLASS)
					.orderBy(PT_CONSUMPTION_CLASS.CODE)
					.fetch()
					.into(PtConsumptionClass.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the consumption class for the consumption types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
		
	}

	@Override
	public CtConsumptionType findDataByConsumptionTypeId(Integer consumptionTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtConsumptionType> result = null;
		String errorMessage;
		
		try {
			result = create.selectFrom(CT_CONSUMPTION_TYPE)
					.where(CT_CONSUMPTION_TYPE.CONSUMPTION_TYPE_ID.eq(val(consumptionTypeId)))
					.orderBy(CT_CONSUMPTION_TYPE.CODE)
					.fetch()
					.into(CtConsumptionType.class);
			

			if (result.size() > 1) {
				errorMessage = "Error while try to find the consumption type for consumption_type_id : " + consumptionTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
				
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption type for consumption_type_id: " + consumptionTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);		
		}
	}

	@Override
	public CtConsumptionType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtConsumptionType> result = null;
		String errorMessage;
		

		try {
			result = create.selectFrom(CT_CONSUMPTION_TYPE)
					.where(CT_CONSUMPTION_TYPE.CODE.eq(val(code)))
					.orderBy(CT_CONSUMPTION_TYPE.CODE)
					.fetch()
					.into(CtConsumptionType.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the consumption type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}


		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the consumption type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void insertData(CtConsumptionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;		
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtConsumptionTypeDao daoObject = new CtConsumptionTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the consumption type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtConsumptionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtConsumptionTypeDao daoObject = new CtConsumptionTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the consumption type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtConsumptionType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtConsumptionTypeDao daoObject = new CtConsumptionTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the consumption type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);			
		}
		
	}

}
