package com.billingweb.ejb.parameterization;

import static com.billingweb.model.Tables.PT_BILLING_PERIOD;
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
import com.billingweb.model.tables.daos.PtBillingPeriodDao;
import com.billingweb.model.tables.pojos.PtBillingPeriod;


/**
 * Session Bean implementation class BillingPeriodEJB
 */
@Stateless
public class BillingPeriodEJB implements BillingPeriodEJBLocal {
	
	Logger logger = (Logger) LogManager.getLogger(StatusEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;
    

    /**
     * Default constructor. 
     */
    public BillingPeriodEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<PtBillingPeriod> findAllData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtBillingPeriod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_BILLING_PERIOD).
					orderBy(PT_BILLING_PERIOD.CODE).
					fetch().into(PtBillingPeriod.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the billing periods - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtBillingPeriod findDataByBillingPeriodId(Integer billingPeriodId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtBillingPeriod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_BILLING_PERIOD).
					where(PT_BILLING_PERIOD.BILLING_PERIOD_ID.eq(val(billingPeriodId))).
					orderBy(PT_BILLING_PERIOD.CODE)
					.fetch().into(PtBillingPeriod.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the billing period for billing_period_id : " + billingPeriodId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the billing period for billing_period_id: " + billingPeriodId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
	}
		

	@Override
	public PtBillingPeriod findDataByCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtBillingPeriod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_BILLING_PERIOD).
					where(PT_BILLING_PERIOD.CODE.eq(val(code))).
					orderBy(PT_BILLING_PERIOD.CODE).fetch()
					.into(PtBillingPeriod.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the billing period for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the billing period for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(PtBillingPeriod dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			PtBillingPeriodDao daoObject = new PtBillingPeriodDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the billing period object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(PtBillingPeriod dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtBillingPeriodDao daoObject = new PtBillingPeriodDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the billing period object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

	@Override
	public void deleteData(PtBillingPeriod dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtBillingPeriodDao daoObject = new PtBillingPeriodDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the billing period object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

}
