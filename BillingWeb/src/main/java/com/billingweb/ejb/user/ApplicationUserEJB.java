package com.billingweb.ejb.user;

import static com.billingweb.model.Tables.VW_USERS;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.daos.ItProfilesDao;
import com.billingweb.model.tables.daos.ItUsersDao;
import com.billingweb.model.tables.pojos.ItUsers;
import com.billingweb.model.tables.pojos.VwUsers;
import com.billingweb.model.tables.records.VwUsersRecord;

import static com.billingweb.model.Tables.IT_USERS;

/**
 * Session Bean implementation class ApplicationUser
 */
@Stateless
public class ApplicationUserEJB implements ApplicationUserEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ApplicationUserEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public ApplicationUserEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public VwUsers activeUser(String userName, String password) {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		VwUsers user = null;
		String errorMessage;

		try {
			// Gets the user from the database
			Result<VwUsersRecord> result = create.selectFrom(VW_USERS)
					.where(VW_USERS.USER_CODE.eq(userName).and(VW_USERS.PASSWORD.eq(password))).fetch();
			if (result.isNotEmpty()) {
				return result.get(0).into(VwUsers.class);

			}
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the user with username " + userName + " and password " + password
					+ " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		return user;
	}

	@Override

	public List<ItUsers> findData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItUsers> result;
		String errorMessage;
		try {
			// Gets the user from the database
			result = create.selectFrom(IT_USERS).orderBy(IT_USERS.USER_CODE).fetchInto(ItUsers.class);
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the system users - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		} catch (Exception e) {
			errorMessage = "Error while try to find all the system users - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);

		}

		return result;
	}

	@Override
	public ItUsers findDataByUserCode(String code) throws BillingWebDataAccessException {
		ItUsers result;
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItUsersDao daoObject = new ItUsersDao(configuration);
			result = daoObject.fetchOneByUserCode(code);
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the user with user code " + code + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		} catch (Exception e) {
			errorMessage = "Error while try to find the user with user code " + code + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;

	}

	@Override
	public ItUsers findDataByUserId(Integer id) throws BillingWebDataAccessException {
		ItUsers result;
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItUsersDao daoObject = new ItUsersDao(configuration);
			result = daoObject.fetchOneByUserId(id);
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the user with user id " + id + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		} catch (Exception e) {
			errorMessage = "Error while try to find the user with user id " + id + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItUsers> findDataByProfileId(Integer id) throws BillingWebDataAccessException {		// 
		List<ItUsers> result = null;
		String errorMessage;
		try {

			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItUsersDao daoObject = new ItUsersDao(configuration);
			result = daoObject.fetchByProfileId(id);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find users with profile id " + id + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		} catch (Exception e) {
			errorMessage = "Error while try to find users with profile id " + id + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItUsers> findDataByProfileCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItUsers> result = null;
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItProfilesDao daoObjectProfile = new ItProfilesDao(configuration);
			ItUsersDao daoObject = new ItUsersDao(configuration);

			result = daoObject.fetchByProfileId(daoObjectProfile.fetchOneByProfileCode(code).getProfileId());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find users with profile code " + code + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		} catch (Exception e) {
			errorMessage = "Error while try to find users with profile code " + code + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}

		return result;
	}

	@Override
	public void insertData(ItUsers dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItUsersDao daoObject = new ItUsersDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the user object (value: " + dataObject.toString() + ")" + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);	
		}

	}

	@Override
	public void updateData(ItUsers dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItUsersDao daoObject = new ItUsersDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the user object (value: " + dataObject.toString() + ")" + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}

	}

	@Override
	public void deleteData(ItUsers dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItUsersDao daoObject = new ItUsersDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the user object (value: " + dataObject.toString() + ")" + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

	}

}
