package com.comasw.ejb.parameterization;

import static com.comasw.model.Tables.PT_DISCOUNT_TYPE;
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

import com.comasw.model.tables.daos.PtDiscountTypeDao;
import com.comasw.model.tables.pojos.PtDiscountType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class DiscountTypeEJB
 */
@Stateless
public class DiscountTypeEJB implements DiscountTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(StatusEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public DiscountTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PtDiscountType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtDiscountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_DISCOUNT_TYPE).orderBy(PT_DISCOUNT_TYPE.CODE).fetch()
					.into(PtDiscountType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the discount types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public PtDiscountType findDataByDiscounTypeId(Integer discountTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtDiscountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_DISCOUNT_TYPE)
					.where(PT_DISCOUNT_TYPE.DISCOUNT_TYPE_ID.eq(val(discountTypeId))).orderBy(PT_DISCOUNT_TYPE.CODE)
					.fetch().into(PtDiscountType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the discount type for discount_type_id : " + discountTypeId
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the discount type for discount_type_id: " + discountTypeId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public PtDiscountType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtDiscountType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(PT_DISCOUNT_TYPE).where(PT_DISCOUNT_TYPE.CODE.eq(val(code)))
					.orderBy(PT_DISCOUNT_TYPE.CODE).fetch().into(PtDiscountType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the discount type for code : " + code
						+ " - The query returns more rows(" + result.size() + ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the discount type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(PtDiscountType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtDiscountTypeDao daoObject = new PtDiscountTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the discount type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(PtDiscountType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtDiscountTypeDao daoObject = new PtDiscountTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the discount type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(PtDiscountType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			PtDiscountTypeDao daoObject = new PtDiscountTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the discount type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
