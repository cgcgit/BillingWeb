package com.billingweb.ejb.catalog.type;

import static com.billingweb.model.Tables.CT_BILL_CYCLE_TYPE;
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
import com.billingweb.model.tables.daos.CtBillCycleTypeDao;
import com.billingweb.model.tables.pojos.CtBillCycleType;


/**
 * Session Bean implementation class BillCycleTypeEJB
 */
@Stateless
public class BillCycleTypeEJB implements BillCycleTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(BillCycleTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

    /**
     * Default constructor. 
     */
    public BillCycleTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtBillCycleType> findAllData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE)
					.orderBy(CT_BILL_CYCLE_TYPE.CODE)
					.fetch()
					.into(CtBillCycleType.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the bill cycle types - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<CtBillCycleType> findOrdinaryCycleType() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE)
					.where(CT_BILL_CYCLE_TYPE.CORRECTIVE.eq(false))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE)
					.fetch()
					.into(CtBillCycleType.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the ordinary bill cycle types - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtBillCycleType> findCorrectiveCycleType() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE)
					.where(CT_BILL_CYCLE_TYPE.CORRECTIVE.eq(true))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE)
					.fetch()
					.into(CtBillCycleType.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the corrective bill cycle types - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}
	

	@Override
	public CtBillCycleType findDataByBillCycleTypeId(Integer billCycleTypeId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;
		
		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE)
					.where(CT_BILL_CYCLE_TYPE.BILL_CYCLE_TYPE_ID.eq(val(billCycleTypeId)))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE)
					.fetch()
					.into(CtBillCycleType.class);
			

			if (result.size() > 1) {
				errorMessage = "Error while try to find the bill cycle type for bill_cycle_type_id : " + billCycleTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the bill cycle type for bill_cycle_type_id: " + billCycleTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);		
		}

		
	}

	@Override
	public CtBillCycleType findDataByCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;
		

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE)
					.where(CT_BILL_CYCLE_TYPE.CODE.eq(val(code)))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE)
					.fetch()
					.into(CtBillCycleType.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the bill cycle type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the bill cycle type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void insertData(CtBillCycleType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtBillCycleTypeDao daoObject = new CtBillCycleTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the bill cycle type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtBillCycleType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtBillCycleTypeDao daoObject = new CtBillCycleTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the bill cycle type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtBillCycleType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtBillCycleTypeDao daoObject = new CtBillCycleTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the bill cycle type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}

	

}
