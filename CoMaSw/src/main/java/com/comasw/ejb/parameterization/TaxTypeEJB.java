package com.comasw.ejb.parameterization;

import static com.comasw.model.Tables.PT_TAX_TYPE;
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

import com.comasw.model.tables.daos.PtTaxTypeDao;
import com.comasw.model.tables.pojos.PtTaxType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class TaxTypeEJB
 */
@Stateless
public class TaxTypeEJB implements TaxTypeEJBLocal {
	
	


	Logger logger = (Logger) LogManager.getLogger(TaxTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;


    /**
     * Default constructor. 
     */
    public TaxTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<PtTaxType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtTaxType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_TAX_TYPE)
					.orderBy(PT_TAX_TYPE.CODE)
					.fetch()
					.into(PtTaxType.class);		

		} catch (DataAccessException e) {
			errorMessage= "Error while try to find all the tax types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtTaxType findDataByTaxTypeId(Integer taxTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtTaxType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_TAX_TYPE)
					.where(PT_TAX_TYPE.TAX_TYPE_ID.eq(val(taxTypeId)))
					.orderBy(PT_TAX_TYPE.CODE)
					.fetch()
					.into(PtTaxType.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the tax type for entity_type_id : " + taxTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage= "Error while try to find the tax type for tax_type_id: " + taxTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public PtTaxType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtTaxType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_TAX_TYPE)
					.where(PT_TAX_TYPE.CODE.eq(val(code)))
					.orderBy(PT_TAX_TYPE.CODE)
					.fetch()
					.into(PtTaxType.class);
			if (result.size() > 1) {
				errorMessage = "Error while try to find the tax type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}				

		} catch (DataAccessException e) {
			errorMessage= "Error while try to find the tax type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(PtTaxType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			PtTaxTypeDao daoObject = new PtTaxTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage= "Error inserting the tax type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(PtTaxType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			PtTaxTypeDao daoObject = new PtTaxTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage= "Error updating the tax type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(PtTaxType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			PtTaxTypeDao daoObject = new PtTaxTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage= "Error deleting the tax type object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

}
