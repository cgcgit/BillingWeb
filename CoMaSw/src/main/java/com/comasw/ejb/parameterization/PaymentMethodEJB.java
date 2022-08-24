package com.comasw.ejb.parameterization;

import static com.comasw.model.Tables.PT_PAYMENT_METHOD;
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

import com.comasw.model.tables.daos.PtPaymentMethodDao;
import com.comasw.model.tables.pojos.PtPaymentMethod;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class PaymentMethod
 */
@Stateless
public class PaymentMethodEJB implements PaymentMethodEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(PaymentMethodEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public PaymentMethodEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PtPaymentMethod> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtPaymentMethod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_PAYMENT_METHOD).orderBy(PT_PAYMENT_METHOD.CODE).fetch()
					.into(PtPaymentMethod.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the payment methods - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtPaymentMethod findDataByPaymentMethodId(Integer paymentMethodId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtPaymentMethod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_PAYMENT_METHOD)
					.where(PT_PAYMENT_METHOD.PAYMENT_METHOD_ID.eq(val(paymentMethodId))).orderBy(PT_PAYMENT_METHOD.CODE)
					.fetch().into(PtPaymentMethod.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the payment method for payment_method_id : " + paymentMethodId
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
			errorMessage = "Error while try to find the payment method for payment_method_id: " + paymentMethodId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public PtPaymentMethod findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtPaymentMethod> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_PAYMENT_METHOD).where(PT_PAYMENT_METHOD.CODE.eq(val(code)))
					.orderBy(PT_PAYMENT_METHOD.CODE).fetch().into(PtPaymentMethod.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the payment method for code : " + code
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
			errorMessage = "Error while try to find the payment method for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(PtPaymentMethod dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtPaymentMethodDao daoObject = new PtPaymentMethodDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the payment method object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(PtPaymentMethod dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtPaymentMethodDao daoObject = new PtPaymentMethodDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the payment method object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(PtPaymentMethod dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtPaymentMethodDao daoObject = new PtPaymentMethodDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the payment method object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

}
