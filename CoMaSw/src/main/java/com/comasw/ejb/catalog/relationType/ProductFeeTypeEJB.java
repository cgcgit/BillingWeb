package com.comasw.ejb.catalog.relationType;

import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.PT_APPLICATION_LEVEL;
import static com.comasw.model.Tables.VW_PRODUCT_FEE_TYPE;
import static com.comasw.model.Tables.CT_FEE_TYPE;
import static com.comasw.model.Tables.CT_PROD_FEE_TYPE;

import static org.jooq.impl.DSL.val;
import static org.jooq.impl.DSL.min;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import com.comasw.model.tables.daos.CtProdFeeTypeDao;
import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.model.tables.pojos.CtProdFeeType;
import com.comasw.model.tables.pojos.PtApplicationLevel;
import com.comasw.model.tables.pojos.VwProductFeeType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class ProductTypeRelationsEJB
 */
@Stateless
public class ProductFeeTypeEJB implements ProductFeeTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ProductFeeTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.comasw.properties.dataBaseDefinitions");

	protected static String APPLICATION_LEVEL_CODE_PROD = dbDefinitions.getString("APPLICATION_LEVEL_CODE_PROD");

	/**
	 * Default constructor.
	 */
	public ProductFeeTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtFeeType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.CtFeeType ft2 = CT_FEE_TYPE.as("ft2");
		com.comasw.model.tables.CtProdFeeType pft = CT_PROD_FEE_TYPE.as("pft");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(ft)
					.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))
					.whereNotExists(create.selectOne().from(pft)
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.and(ft.START_DATE
							.eq(create.select(min(ft2.START_DATE)).from(ft2).where(ft.FEE_TYPE_ID.eq(ft2.FEE_TYPE_ID))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the product_type_id : " + parentTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	@Override
	public List<CtFeeType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.CtFeeType ft2 = CT_FEE_TYPE.as("ft2");
		com.comasw.model.tables.CtProdFeeType pft = CT_PROD_FEE_TYPE.as("pft");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(ft)
					.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))
					.whereExists(create.selectOne()
							.from(pft.join(st).on(pft.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.and(ft.START_DATE
							.eq(create.select(min(ft2.START_DATE)).from(ft2).where(ft.FEE_TYPE_ID.eq(ft2.FEE_TYPE_ID))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the product_type_id: " + parentTypeId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}


	@Override
	public List<CtFeeType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtFeeType> result = null;
		Map<CtFeeType, List<PtApplicationLevel>> record;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtFeeType ft = CT_FEE_TYPE.as("ft");
		com.comasw.model.tables.CtFeeType ft2 = CT_FEE_TYPE.as("ft2");
		com.comasw.model.tables.CtProdFeeType pft = CT_PROD_FEE_TYPE.as("pft");
		com.comasw.model.tables.PtApplicationLevel al = PT_APPLICATION_LEVEL.as("al");

		try {
			record = create.select().from(al).join(ft)
					.on(ft.APPLICATION_LEVEL_ID.eq(al.APPLICATION_LEVEL_ID)
							.and(al.CODE.eq(val(APPLICATION_LEVEL_CODE_PROD))))
					.whereNotExists(create.selectOne()
							.from(pft.join(st).on(pft.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(ft.FEE_TYPE_ID.eq(pft.FEE_TYPE_ID).and(pft.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.and(ft.START_DATE
							.eq(create.select(min(ft2.START_DATE)).from(ft2).where(ft.FEE_TYPE_ID.eq(ft2.FEE_TYPE_ID))))
					.orderBy(ft.CODE)
					.fetchGroups(r -> r.into(ft).into(CtFeeType.class), r -> r.into(al).into(PtApplicationLevel.class));

			result = new ArrayList<CtFeeType>();
			result.addAll(record.keySet());

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the product_type_id: " + parentTypeId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}
	
	
	@Override
	public List<VwProductFeeType> findHistoricRelatedEntityTypesView(Integer parentTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductFeeType> result = null;
		String errorMessage;
		try {
			result = create.select().from(VW_PRODUCT_FEE_TYPE)
					.where(VW_PRODUCT_FEE_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.orderBy(VW_PRODUCT_FEE_TYPE.FEE_TYPE_CODE, VW_PRODUCT_FEE_TYPE.FEE_TYPE_START_DATE).fetch()
					.into(VwProductFeeType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of fee types for the product_type_id : " + parentTypeId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwProductFeeType> findRelatedEntityTypesByDateView(Integer parentTypeId, LocalDateTime searchDate)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductFeeType> result = null;
		String errorMessage;
		try {
			result = create.select().from(VW_PRODUCT_FEE_TYPE)
					.where(VW_PRODUCT_FEE_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId))
							.and(val(searchDate).between(VW_PRODUCT_FEE_TYPE.FEE_TYPE_START_DATE,
									VW_PRODUCT_FEE_TYPE.FEE_TYPE_END_DATE)))
					.orderBy(VW_PRODUCT_FEE_TYPE.FEE_TYPE_CODE, VW_PRODUCT_FEE_TYPE.FEE_TYPE_START_DATE).fetch()
					.into(VwProductFeeType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of fee types for the product_type_id : " + parentTypeId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtProdFeeType findEntityRelationType(Integer entityRelationTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProdFeeType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROD_FEE_TYPE)
					.where(CT_PROD_FEE_TYPE.PROD_FEE_TYPE_ID.eq(val(entityRelationTypeId))).fetch()
					.into(CtProdFeeType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product fee type for the product_fee_type_id : "
						+ entityRelationTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the product fee type for the product_fee_type_id : "
					+ entityRelationTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtProdFeeType findEntityRelationType(Integer parentTypeId, Integer childTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProdFeeType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROD_FEE_TYPE).where(CT_PROD_FEE_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.and(CT_PROD_FEE_TYPE.FEE_TYPE_ID.eq(val(childTypeId))).fetch().into(CtProdFeeType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product fee type for the product_type_id : " + parentTypeId
						+ " and fee_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the fee types for the product_type_id : " + parentTypeId
					+ " and fee_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public VwProductFeeType findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductFeeType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(VW_PRODUCT_FEE_TYPE).where(VW_PRODUCT_FEE_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.and(VW_PRODUCT_FEE_TYPE.FEE_TYPE_ID.eq(val(childTypeId)))
					.orderBy(VW_PRODUCT_FEE_TYPE.FEE_TYPE_CODE, VW_PRODUCT_FEE_TYPE.FEE_TYPE_START_DATE).fetch()
					.into(VwProductFeeType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the view of product fee type for the product_type_id : "
						+ parentTypeId + " and fee_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the fee types for the product_type_id : " + parentTypeId
					+ " and fee_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertData(CtProdFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProdFeeTypeDao daoObject = new CtProdFeeTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the product fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtProdFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProdFeeTypeDao daoObject = new CtProdFeeTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the product fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtProdFeeType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProdFeeTypeDao daoObject = new CtProdFeeTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the product fee type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
