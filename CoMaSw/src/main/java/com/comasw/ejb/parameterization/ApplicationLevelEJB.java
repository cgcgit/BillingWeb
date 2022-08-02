package com.comasw.ejb.parameterization;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jooq.DSLContext;
//import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import static org.jooq.impl.DSL.val;
import org.jooq.Configuration;

import java.util.List;

//import com.comasw.model.Sequences;
import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.daos.PtApplicationLevelDao;

import static com.comasw.model.Tables.PT_APPLICATION_LEVEL;

/**
 * Session Bean implementation class applicationLevelEJB
 */
@Stateless
public class ApplicationLevelEJB implements ApplicationLevelEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ApplicationLevelEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public ApplicationLevelEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PtApplicationLevel> findAllData() throws CoMaSwDataAccessException {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtApplicationLevel> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_APPLICATION_LEVEL).orderBy(PT_APPLICATION_LEVEL.CODE).fetch()
					.into(PtApplicationLevel.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the application levels - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtApplicationLevel findDataByApplicationLevelId(Integer applicationLevelId)
			throws CoMaSwDataAccessException {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtApplicationLevel> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_APPLICATION_LEVEL)
					.where(PT_APPLICATION_LEVEL.APPLICATION_LEVEL_ID.eq(val(applicationLevelId)))
					.orderBy(PT_APPLICATION_LEVEL.CODE).fetch().into(PtApplicationLevel.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the application level for application_level_id : "
						+ applicationLevelId + " - The query returns more rows(" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the application level for application_level_id: "
					+ applicationLevelId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public PtApplicationLevel findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtApplicationLevel> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_APPLICATION_LEVEL).where(PT_APPLICATION_LEVEL.CODE.eq(val(code)))
					.orderBy(PT_APPLICATION_LEVEL.CODE).fetch().into(PtApplicationLevel.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the application level for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the application level for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtApplicationLevelDao daoObject = new PtApplicationLevelDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the application level object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtApplicationLevelDao daoObject = new PtApplicationLevelDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the application level object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void deleteData(PtApplicationLevel dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtApplicationLevelDao daoObject = new PtApplicationLevelDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the application level object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

}
