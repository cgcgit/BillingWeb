package com.comasw.ejb.catalog.type;

import static com.comasw.model.Tables.CT_ACCOUNT_TYPE;
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

import com.comasw.model.tables.daos.CtAccountTypeDao;
import com.comasw.model.tables.pojos.CtAccountType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class AccountTypeEJB
 */
@Stateless
public class AccountTypeEJB implements AccountTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(AccountTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public AccountTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtAccountType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtAccountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_ACCOUNT_TYPE).orderBy(CT_ACCOUNT_TYPE.CODE).fetch().into(CtAccountType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the account types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtAccountType findDataByAccountTypeId(Integer accountTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtAccountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_ACCOUNT_TYPE).where(CT_ACCOUNT_TYPE.ACCOUNT_TYPE_ID.eq(val(accountTypeId)))
					.orderBy(CT_ACCOUNT_TYPE.CODE).fetch().into(CtAccountType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the account type for account_type_id : " + accountTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the account type for account_type_id: " + accountTypeId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public CtAccountType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtAccountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_ACCOUNT_TYPE).where(CT_ACCOUNT_TYPE.CODE.eq(val(code)))
					.orderBy(CT_ACCOUNT_TYPE.CODE).fetch().into(CtAccountType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the account type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the account type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		
	}

	@Override
	public void insertData(CtAccountType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtAccountTypeDao daoObject = new CtAccountTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the account type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtAccountType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtAccountTypeDao daoObject = new CtAccountTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the account type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtAccountType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtAccountTypeDao daoObject = new CtAccountTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the account type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

}
