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

import static com.comasw.model.Tables.CT_PRODUCT_TYPE;
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

import com.comasw.model.tables.daos.CtProductTypeDao;
import com.comasw.model.tables.pojos.CtProductType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class ProductTypeEJB
 */
@Stateless
public class ProductTypeEJB implements ProductTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ProductTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public ProductTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtProductType> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProductType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PRODUCT_TYPE).orderBy(CT_PRODUCT_TYPE.CODE).fetch().into(CtProductType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the product types - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtProductType findDataByProductTypeId(Integer productTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProductType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PRODUCT_TYPE).where(CT_PRODUCT_TYPE.PRODUCT_TYPE_ID.eq(val(productTypeId)))
					.orderBy(CT_PRODUCT_TYPE.CODE).fetch().into(CtProductType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product type for product_type_id : " + productTypeId
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
			errorMessage = "Error while try to find the product type for product_type_id: " + productTypeId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public CtProductType findDataByCode(String code) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProductType> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(CT_PRODUCT_TYPE).where(CT_PRODUCT_TYPE.CODE.eq(val(code)))
					.orderBy(CT_PRODUCT_TYPE.CODE).fetch().into(CtProductType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product type for code : " + code
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
			errorMessage = "Error while try to find the product type for code " + code + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(CtProductType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProductTypeDao daoObject = new CtProductTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the product type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtProductType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProductTypeDao daoObject = new CtProductTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the product type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtProductType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProductTypeDao daoObject = new CtProductTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the product type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
