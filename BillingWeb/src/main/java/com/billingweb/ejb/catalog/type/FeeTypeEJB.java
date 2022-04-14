package com.billingweb.ejb.catalog.type;

import static com.billingweb.model.Tables.CT_FEE_TYPE;
import static com.billingweb.model.Tables.PT_APPLICATION_LEVEL;
import static org.jooq.impl.DSL.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.billingweb.model.tables.daos.CtFeeTypeDao;
import com.billingweb.model.tables.pojos.CtFeeType;
import com.billingweb.model.tables.pojos.PtApplicationLevel;

/**
 * Session Bean implementation class FeeTypeEJB
 */
@Stateless
public class FeeTypeEJB implements FeeTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(FeeTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	
	/**
     * Default constructor. 
     */
    public FeeTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<CtFeeType> findAllData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE)
					.orderBy(CT_FEE_TYPE.CODE)
					.fetch()
					.into(CtFeeType.class);		

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the fee types - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtFeeType findDataByFeeTypeId(Integer feeTypeId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;
		
		try {
			result = create.selectFrom(CT_FEE_TYPE)
					.where(CT_FEE_TYPE.FEE_TYPE_ID.eq(val(feeTypeId)))
					.orderBy(CT_FEE_TYPE.CODE)
					.fetch()
					.into(CtFeeType.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the fee type for fee_type_id : " + feeTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}					

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for fee_type_id: " + feeTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);		
		}

		
	}

	@Override
	public CtFeeType findDataByCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;
		

		try {
			result = create.selectFrom(CT_FEE_TYPE)
					.where(CT_FEE_TYPE.CODE.eq(val(code)))
					.orderBy(CT_FEE_TYPE.CODE)
					.fetch()
					.into(CtFeeType.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the fee type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new BillingWebDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}
	
	@Override
	public List<CtFeeType> findDataByApplicationLevelId(Integer applicationLevelId)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;
		

		try {
			result = create.selectFrom(CT_FEE_TYPE)
					.where(CT_FEE_TYPE.APPLICATION_LEVEL_ID.eq(val(applicationLevelId)))
					.orderBy(CT_FEE_TYPE.CODE)
					.fetch()
					.into(CtFeeType.class);
				

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for application_level_id " + applicationLevelId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
		return result;
	}

	@Override
	public List<CtFeeType> findDataByApplicationLevelCode(String applicationLevelCode)
			throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);		
		List<CtFeeType> result = null;
		Map<CtFeeType, List<PtApplicationLevel>> record; 
		String errorMessage;
		// aliases of tables		
		com.billingweb.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
				com.billingweb.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select()
					.from(al).join(ft)
							.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(applicationLevelCode))))		
					        .orderBy(ft.CODE).fetchGroups(r-> r.into(ft).into(CtFeeType.class),
					        		r -> r.into(al).into(PtApplicationLevel.class)
					        		);
			
			result =  new ArrayList<CtFeeType>();  
			result.addAll(record.keySet());
			
			
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for the application_level_code " + applicationLevelCode
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}

		return result;
	}

	

	@Override
	public void insertData(CtFeeType dataObject) throws BillingWebDataAccessException {
		String errorMessage;		
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			CtFeeTypeDao daoObject = new CtFeeTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the fee type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(CtFeeType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtFeeTypeDao daoObject = new CtFeeTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the fee type object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(CtFeeType dataObject) throws BillingWebDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			CtFeeTypeDao daoObject = new CtFeeTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the fee type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new BillingWebDataAccessException(errorMessage, e);			
		}
		
	}


}
