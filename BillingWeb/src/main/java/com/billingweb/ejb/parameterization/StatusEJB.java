package com.billingweb.ejb.parameterization;

import static com.billingweb.model.Tables.PT_STATUS;
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

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.daos.PtStatusDao;
import com.billingweb.model.tables.pojos.PtStatus;

/**
 * Session Bean implementation class StatusEJB
 */
@Stateless
public class StatusEJB implements StatusEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(StatusEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public StatusEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PtStatus> findAllData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtStatus> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_STATUS).orderBy(PT_STATUS.CODE).fetch().into(PtStatus.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the status - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<PtStatus> findDataByStatusId(Integer statusId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtStatus> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_STATUS).where(PT_STATUS.STATUS_ID.eq(val(statusId))).orderBy(PT_STATUS.CODE)
					.fetch().into(PtStatus.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the status for status_id: " + statusId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<PtStatus> findDataByCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtStatus> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_STATUS).where(PT_STATUS.CODE.eq(val(code))).orderBy(PT_STATUS.CODE).fetch()
					.into(PtStatus.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the status for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public void insertData(PtStatus dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			PtStatusDao daoObject = new PtStatusDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the status object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(PtStatus dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtStatusDao daoObject = new PtStatusDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the status object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}

	}

	@Override
	public void deleteData(PtStatus dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtStatusDao daoObject = new PtStatusDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the status object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

	}

}
