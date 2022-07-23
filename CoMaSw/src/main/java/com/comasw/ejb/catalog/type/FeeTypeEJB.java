package com.comasw.ejb.catalog.type;

import static com.comasw.model.Tables.CT_FEE_TYPE;
import static com.comasw.model.Sequences.SEQ_FEE_TYPE_ID;
import static com.comasw.model.Tables.PT_APPLICATION_LEVEL;
import static org.jooq.impl.DSL.val;

import java.time.LocalDateTime;
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

import com.comasw.model.tables.daos.IdtFeeTypeDao;
import com.comasw.model.tables.daos.CtFeeTypeDao;
import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.IdtFeeType;
import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.model.tables.records.CtFeeTypeRecord;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class FeeTypeEJB
 */
@Stateless
public class FeeTypeEJB implements FeeTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(FeeTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public FeeTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtFeeType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE).orderBy(CT_FEE_TYPE.CODE).fetch().into(CtFeeType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the fee types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtFeeType> findDataBySearchDate(LocalDateTime searchDate) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE)
					.where(val(searchDate).between(CT_FEE_TYPE.START_DATE, CT_FEE_TYPE.END_DATE))
					.orderBy(CT_FEE_TYPE.CODE).fetch().into(CtFeeType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the fee types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtFeeType> findDataByFeeTypeId(Integer feeTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE).where(CT_FEE_TYPE.FEE_TYPE_ID.eq(val(feeTypeId)))
					.orderBy(CT_FEE_TYPE.START_DATE, CT_FEE_TYPE.CODE).fetch().into(CtFeeType.class);

			return result;

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for fee_type_id: " + feeTypeId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public CtFeeType findDataBySearchDateAndFeeTypeId(LocalDateTime searchDate, Integer feeTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE).where(CT_FEE_TYPE.FEE_TYPE_ID.eq(val(feeTypeId)))
					.and(val(searchDate).between(CT_FEE_TYPE.START_DATE, CT_FEE_TYPE.END_DATE))
					.orderBy(CT_FEE_TYPE.CODE).fetch().into(CtFeeType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the fee type for search date: " + searchDate.toString()
						+ " and fee_type_id : " + feeTypeId + " - The query returns more rows(" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for  for search date: " + searchDate.toString()
					+ " and fee_type_id : " + feeTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public List<CtFeeType> findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE).where(CT_FEE_TYPE.CODE.eq(val(code))).orderBy(CT_FEE_TYPE.CODE)
					.fetch().into(CtFeeType.class);
			return result;

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public CtFeeType findDataBySearchDateAndCode(LocalDateTime searchDate, String code)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE).where(CT_FEE_TYPE.CODE.eq(val(code)))
					.and(val(searchDate).between(CT_FEE_TYPE.START_DATE, CT_FEE_TYPE.END_DATE))
					.orderBy(CT_FEE_TYPE.CODE).fetch().into(CtFeeType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the fee type for search date: " + searchDate.toString()
						+ " and code : " + code + " - The query returns more rows(" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for search date: " + searchDate.toString()
					+ " and code : " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public List<CtFeeType> findDataByApplicationLevelId(Integer applicationLevelId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE).where(CT_FEE_TYPE.APPLICATION_LEVEL_ID.eq(val(applicationLevelId)))
					.orderBy(CT_FEE_TYPE.CODE).fetch().into(CtFeeType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for application_level_id " + applicationLevelId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtFeeType> findDataBySearchDateAndApplicationLevelId(LocalDateTime searchDate,
			Integer applicationLevelId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_FEE_TYPE).where(CT_FEE_TYPE.APPLICATION_LEVEL_ID.eq(val(applicationLevelId)))
					.and(val(searchDate).between(CT_FEE_TYPE.START_DATE, CT_FEE_TYPE.END_DATE))
					.orderBy(CT_FEE_TYPE.CODE).fetch().into(CtFeeType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for search date: " + searchDate.toString()
					+ " and application_level_id " + applicationLevelId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtFeeType> findDataByApplicationLevelCode(String applicationLevelCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(ft)
					.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID).and(al.CODE.eq(val(applicationLevelCode))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for the application_level_code " + applicationLevelCode
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtFeeType> findDataBySearchDateAndApplicationLevelCode(LocalDateTime searchDate,
			String applicationLevelCode) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(ft)
					.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(val(searchDate).between(ft.START_DATE, ft.END_DATE))
							.and(al.CODE.eq(val(applicationLevelCode))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for search date: " + searchDate.toString()
					+ " and application_level_code " + applicationLevelCode + " - " + e.getMessage();
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
			id = Math.toIntExact(create.nextval(SEQ_FEE_TYPE_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new fee type" + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;

	}

	@Override
	public void insertData(CtFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();

			if (id > 0) {
				// insert the new id value into the idType datatable
				IdtFeeType objectId = new IdtFeeType(id);
				IdtFeeTypeDao daoObjectId = new IdtFeeTypeDao(configuration);
				daoObjectId.insert(objectId);

				// insert the dataObject into the type datatable
				dataObject.setFeeTypeId(id);
				CtFeeTypeDao daoObject = new CtFeeTypeDao(configuration);
				daoObject.insert(dataObject);
			}
		} catch (Exception e) {
			errorMessage = "Error inserting the fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertNewHistoricDataRecord(CtFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;		

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtFeeTypeDao daoObject = new CtFeeTypeDao(configuration);
			daoObject.insert(dataObject);

		} catch (Exception e) {
			errorMessage = "Error inserting the fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateHistoricDataRecord(CtFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		CtFeeTypeRecord record;
		try {
			record = create.newRecord(CT_FEE_TYPE, dataObject);
			record.store();

		} catch (Exception e) {
			errorMessage = "Error inserting the fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtFeeTypeDao daoObject = new CtFeeTypeDao(configuration);			
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtFeeTypeDao daoObject = new CtFeeTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}	

}
