package com.comasw.ejb.instance;

import static com.comasw.model.Sequences.SEQ_CONTRACT_ID;
import static com.comasw.model.Tables.IT_CONTRACT;

import static org.jooq.impl.DSL.val;
import static org.jooq.impl.DSL.upper;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
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

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.daos.ItContractDao;
import com.comasw.model.tables.pojos.ItContract;

/**
 * Session Bean implementation class ContractEJB
 */
@Stateless
@LocalBean
public class ContractEJB implements ContractEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ContractEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public ContractEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ItContract> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItContract> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_CONTRACT).orderBy(IT_CONTRACT.CONTRACT_NUMBER).fetch().into(ItContract.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the contracts - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItContract> findDataByContractId(Integer contractId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItContract> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_CONTRACT).where(IT_CONTRACT.CONTRACT_ID.eq(val(contractId)))
					.orderBy(IT_CONTRACT.CONTRACT_NUMBER).fetch().into(ItContract.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the contract with contractId: " + contractId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItContract> findDataByContractNumber(String contractNr) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItContract> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_CONTRACT).where(IT_CONTRACT.CONTRACT_NUMBER.eq(upper(val(contractNr))))
					.orderBy(IT_CONTRACT.CONTRACT_NUMBER).fetch().into(ItContract.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the contract with contractNr: " + contractNr + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public Integer getNewId() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		String errorMessage;
		Integer id = 0;
		try {
			id = Math.toIntExact(create.nextval(SEQ_CONTRACT_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new contract " + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;
	}
	

	@Override
	public Integer insertData(ItContract dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {

			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();
			// insert the dataObject into the type datatable
			dataObject.setContractId(id);
			ItContractDao daoObject = new ItContractDao(configuration);
			daoObject.insert(dataObject);
			
			return id;
			

		} catch (Exception e) {
			errorMessage = "Error inserting the contract object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(ItContract dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItContractDao daoObject = new ItContractDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the contract object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(ItContract dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItContractDao daoObject = new ItContractDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the contract object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
