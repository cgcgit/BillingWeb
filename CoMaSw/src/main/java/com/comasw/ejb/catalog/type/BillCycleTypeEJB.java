/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
 any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/

package com.comasw.ejb.catalog.type;

import static com.comasw.model.Tables.CT_BILL_CYCLE_TYPE;
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

import com.comasw.model.tables.daos.CtBillCycleTypeDao;
import com.comasw.model.tables.pojos.CtBillCycleType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class BillCycleTypeEJB
 */
@Stateless
public class BillCycleTypeEJB implements BillCycleTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(BillCycleTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public BillCycleTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtBillCycleType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE).orderBy(CT_BILL_CYCLE_TYPE.CODE).fetch()
					.into(CtBillCycleType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the bill cycle types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtBillCycleType> findOrdinaryCycleType() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE).where(CT_BILL_CYCLE_TYPE.CORRECTIVE.eq(false))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE).fetch().into(CtBillCycleType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the ordinary bill cycle types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<CtBillCycleType> findCorrectiveCycleType() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE).where(CT_BILL_CYCLE_TYPE.CORRECTIVE.eq(true))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE).fetch().into(CtBillCycleType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the corrective bill cycle types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtBillCycleType findDataByBillCycleTypeId(Integer billCycleTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE)
					.where(CT_BILL_CYCLE_TYPE.BILL_CYCLE_TYPE_ID.eq(val(billCycleTypeId)))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE).fetch().into(CtBillCycleType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the bill cycle type for bill_cycle_type_id : " + billCycleTypeId
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
			errorMessage = "Error while try to find the bill cycle type for bill_cycle_type_id: " + billCycleTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public CtBillCycleType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtBillCycleType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_BILL_CYCLE_TYPE).where(CT_BILL_CYCLE_TYPE.CODE.eq(val(code)))
					.orderBy(CT_BILL_CYCLE_TYPE.CODE).fetch().into(CtBillCycleType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the bill cycle type for code : " + code
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
			errorMessage = "Error while try to find the bill cycle type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(CtBillCycleType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtBillCycleTypeDao daoObject = new CtBillCycleTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the bill cycle type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtBillCycleType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtBillCycleTypeDao daoObject = new CtBillCycleTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the bill cycle type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtBillCycleType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtBillCycleTypeDao daoObject = new CtBillCycleTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the bill cycle type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
