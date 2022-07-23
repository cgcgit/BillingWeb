package com.comasw.ejb.parameterization;

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

import com.comasw.model.tables.daos.PtConsumptionClassDao;
import com.comasw.model.tables.pojos.PtConsumptionClass;
import com.comasw.exception.CoMaSwDataAccessException;


/**
 * Session Bean implementation class ConsumptionClassEJB
 */
@Stateless
public class ConsumptionClassEJB implements ConsumptionClassEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ConsumptionClassEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	
    /**
     * Default constructor. 
     */
    public ConsumptionClassEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<PtConsumptionClass> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtConsumptionClass> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_CONSUMPTION_CLASS)
					.orderBy(PT_CONSUMPTION_CLASS.CODE)
					.fetch()
					.into(PtConsumptionClass.class);		

		} catch (DataAccessException e) {
			errorMessage= "Error while try to find all the consumption class - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtConsumptionClass findDataByConsumptionClassId(Integer consumptionClassId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtConsumptionClass> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_CONSUMPTION_CLASS)
					.where(PT_CONSUMPTION_CLASS.CONSUMPTION_CLASS_ID.eq(val(consumptionClassId)))
					.orderBy(PT_CONSUMPTION_CLASS.CODE)
					.fetch()
					.into(PtConsumptionClass.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the consumption class for consumption_class_id : " + consumptionClassId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage= "Error while try to find the consumption class for consumption_class_id: " + consumptionClassId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public PtConsumptionClass findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtConsumptionClass> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_CONSUMPTION_CLASS)
					.where(PT_CONSUMPTION_CLASS.CODE.eq(val(code)))
					.orderBy(PT_CONSUMPTION_CLASS.CODE)
					.fetch()
					.into(PtConsumptionClass.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the consumption class for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage= "Error while try to find the consumption class for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(PtConsumptionClass dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			PtConsumptionClassDao daoObject = new PtConsumptionClassDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage= "Error inserting the consumption class object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(PtConsumptionClass dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			PtConsumptionClassDao daoObject = new PtConsumptionClassDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage= "Error updating the consumption class object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(PtConsumptionClass dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			PtConsumptionClassDao daoObject = new PtConsumptionClassDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage= "Error deleting the consumption class object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

}
