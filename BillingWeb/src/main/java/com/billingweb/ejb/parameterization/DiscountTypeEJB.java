package com.billingweb.ejb.parameterization;

import static com.billingweb.model.Tables.PT_DISCOUNT_TYPE;
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
import com.billingweb.model.tables.daos.PtDiscountTypeDao;
import com.billingweb.model.tables.daos.PtStatusDao;
import com.billingweb.model.tables.pojos.PtDiscountType;


/**
 * Session Bean implementation class DiscountTypeEJB
 */
@Stateless
public class DiscountTypeEJB implements DiscountTypeEJBLocal {


	Logger logger = (Logger) LogManager.getLogger(StatusEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;
    
	/**
     * Default constructor. 
     */
    public DiscountTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<PtDiscountType> findAllData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtDiscountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_DISCOUNT_TYPE).
					orderBy(PT_DISCOUNT_TYPE.CODE).
					fetch().into(PtDiscountType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the discount types - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<PtDiscountType> findDataByDiscounTypeId(Integer discounTypeId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtDiscountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_DISCOUNT_TYPE).
					where(PT_DISCOUNT_TYPE.DISCOUNT_TYPE_ID.eq(val(discounTypeId))).
					orderBy(PT_DISCOUNT_TYPE.CODE)
					.fetch().into(PtDiscountType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the discount type for discount_type_id: " + discounTypeId + " - "
					+ e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<PtDiscountType> findDataByCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtDiscountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_DISCOUNT_TYPE).
					where(PT_DISCOUNT_TYPE.CODE.eq(val(code))).
					orderBy(PT_DISCOUNT_TYPE.CODE).fetch()
					.into(PtDiscountType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the discount type for code " + code + " - " + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public void insertData(PtDiscountType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			PtDiscountTypeDao daoObject = new PtDiscountTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the discount type object (value: "
					+ dataObject.toString() + ")" + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(PtDiscountType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtDiscountTypeDao daoObject = new PtDiscountTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the discount type object (value: " + dataObject.toString() + ")" + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

	@Override
	public void deleteData(PtDiscountType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtDiscountTypeDao daoObject = new PtDiscountTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the discount type object (value: " + dataObject.toString() + ")" + e.getCause().toString();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

}
