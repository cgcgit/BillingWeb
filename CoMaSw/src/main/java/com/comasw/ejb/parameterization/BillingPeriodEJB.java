package com.comasw.ejb.parameterization;

import static com.comasw.model.Tables.PT_BILLING_PERIOD;
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

import com.comasw.model.tables.daos.PtBillingPeriodDao;
import com.comasw.model.tables.pojos.PtBillingPeriod;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class BillingPeriodEJB
 */
@Stateless
public class BillingPeriodEJB implements BillingPeriodEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(StatusEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public BillingPeriodEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PtBillingPeriod> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtBillingPeriod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_BILLING_PERIOD).orderBy(PT_BILLING_PERIOD.CODE).fetch()
					.into(PtBillingPeriod.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the billing periods - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtBillingPeriod findDataByBillingPeriodId(Integer billingPeriodId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtBillingPeriod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_BILLING_PERIOD)
					.where(PT_BILLING_PERIOD.BILLING_PERIOD_ID.eq(val(billingPeriodId))).orderBy(PT_BILLING_PERIOD.CODE)
					.fetch().into(PtBillingPeriod.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the billing period for billing_period_id : " + billingPeriodId
						+ " - The query returns a distinct number of rows (" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				if (result.size() == 0) {
					return null;
				} else {
					return result.get(0);
				}
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the billing period for billing_period_id: " + billingPeriodId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public PtBillingPeriod findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtBillingPeriod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_BILLING_PERIOD).where(PT_BILLING_PERIOD.CODE.eq(val(code)))
					.orderBy(PT_BILLING_PERIOD.CODE).fetch().into(PtBillingPeriod.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the billing period for code : " + code
						+ " - The query returns a distinct number of rows (" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				if (result.size() == 0) {
					return null;
				} else {
					return result.get(0);
				}
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the billing period for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(PtBillingPeriod dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtBillingPeriodDao daoObject = new PtBillingPeriodDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the billing period object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(PtBillingPeriod dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtBillingPeriodDao daoObject = new PtBillingPeriodDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the billing period object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(PtBillingPeriod dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtBillingPeriodDao daoObject = new PtBillingPeriodDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the billing period object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
