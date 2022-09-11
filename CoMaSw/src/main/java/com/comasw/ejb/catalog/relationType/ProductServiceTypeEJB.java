/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

*/

package com.comasw.ejb.catalog.relationType;

import static com.comasw.model.Tables.CT_SERVICE_TYPE;
import static com.comasw.model.Tables.VW_PRODUCT_SERVICE_TYPE;
import static com.comasw.model.Tables.CT_PROD_SERV_TYPE;
import static com.comasw.model.Tables.PT_STATUS;
import static org.jooq.impl.DSL.val;

import java.util.List;
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

import com.comasw.model.tables.daos.CtProdServTypeDao;
import com.comasw.model.tables.pojos.CtProdServType;
import com.comasw.model.tables.pojos.CtServiceType;
import com.comasw.model.tables.pojos.VwProductServiceType;
import com.comasw.exception.CoMaSwDataAccessException;

/**
 * Session Bean implementation class ProductServiceTypeEJB
 */
@Stateless
public class ProductServiceTypeEJB implements ProductServiceTypeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ProductServiceTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	protected static ResourceBundle dbDefinitions = ResourceBundle
			.getBundle("com.comasw.properties.dataBaseDefinitions");

	/**
	 * Default constructor.
	 */
	public ProductServiceTypeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CtServiceType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.CtServiceType svt = CT_SERVICE_TYPE.as("svt");
		com.comasw.model.tables.CtProdServType pst = CT_PROD_SERV_TYPE.as("pst");

		try {
			result = create.select().from(svt)
					.whereNotExists(create.selectOne().from(pst)
							.where(svt.SERVICE_TYPE_ID.eq(pst.SERVICE_TYPE_ID).and(pst.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.orderBy(svt.CODE).fetch().into(CtServiceType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the service type candidates for the product_type_id : " + parentTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	
	@Override
	public List<CtServiceType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtServiceType svt = CT_SERVICE_TYPE.as("svt");
		com.comasw.model.tables.CtProdServType pst = CT_PROD_SERV_TYPE.as("pst");

		try {
			result = create.select().from(svt)
					.whereExists(create.selectOne()
							.from(pst.join(st).on(pst.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(svt.SERVICE_TYPE_ID.eq(pst.SERVICE_TYPE_ID).and(pst.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.orderBy(svt.CODE).fetch().into(CtServiceType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the product_type_id: " + parentTypeId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	
	@Override
	public List<CtServiceType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtServiceType> result = null;
		String errorMessage;
		// aliases of tables
		com.comasw.model.tables.PtStatus st = PT_STATUS.as("st");
		com.comasw.model.tables.CtServiceType svt = CT_SERVICE_TYPE.as("svt");
		com.comasw.model.tables.CtProdServType pst = CT_PROD_SERV_TYPE.as("pst");

		try {
			result = create.select().from(svt)
					.whereNotExists(create.selectOne()
							.from(pst.join(st).on(pst.STATUS_ID.eq(st.STATUS_ID).and(st.CODE.eq(val(statusCode)))))
							.where(svt.SERVICE_TYPE_ID.eq(pst.SERVICE_TYPE_ID).and(pst.PRODUCT_TYPE_ID.eq(parentTypeId))))
					.orderBy(svt.CODE).fetch().into(CtServiceType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type candidates for the product_type_id: " + parentTypeId
					+ " and status code: " + statusCode + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<VwProductServiceType> findRelatedEntityTypesView(Integer parentTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductServiceType> result = null;
		String errorMessage;
		try {
			result = create.select().from(VW_PRODUCT_SERVICE_TYPE)
					.where(VW_PRODUCT_SERVICE_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.orderBy(VW_PRODUCT_SERVICE_TYPE.SERVICE_TYPE_CODE).fetch().into(VwProductServiceType.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the view of service types for the product_type_id : " + parentTypeId
					+ " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public CtProdServType findEntityRelationType(Integer entityRelationTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProdServType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROD_SERV_TYPE)
					.where(CT_PROD_SERV_TYPE.PROD_SERV_TYPE_ID.eq(val(entityRelationTypeId))).fetch()
					.into(CtProdServType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product service type for the product_service_type_id : "
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
			errorMessage = "Error while try to find the product service type for the product_service_type_id : "
					+ entityRelationTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public CtProdServType findEntityRelationType(Integer parentTypeId, Integer childTypeId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<CtProdServType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(CT_PROD_SERV_TYPE).where(CT_PROD_SERV_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.and(CT_PROD_SERV_TYPE.SERVICE_TYPE_ID.eq(val(childTypeId))).fetch().into(CtProdServType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the product service type for the product_type_id : " + parentTypeId
						+ " and service_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the service types for the product_type_id : " + parentTypeId
					+ " and fee_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public VwProductServiceType findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductServiceType> result = null;
		String errorMessage;
		// aliases of tables

		try {
			result = create.selectFrom(VW_PRODUCT_SERVICE_TYPE)
					.where(VW_PRODUCT_SERVICE_TYPE.PRODUCT_TYPE_ID.eq(val(parentTypeId)))
					.and(VW_PRODUCT_SERVICE_TYPE.SERVICE_TYPE_ID.eq(val(childTypeId)))
					.orderBy(VW_PRODUCT_SERVICE_TYPE.PROD_SERV_TYPE_ID).fetch().into(VwProductServiceType.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the view of product fee type for the product_type_id : "
						+ parentTypeId + " and service_type_id: " + childTypeId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the service types for thhe product_type_id : " + parentTypeId
					+ " and service_type_id: " + childTypeId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertData(CtProdServType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProdServTypeDao daoObject = new CtProdServTypeDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {
			errorMessage = "Error inserting the product service type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(CtProdServType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProdServTypeDao daoObject = new CtProdServTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the product service type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(CtProdServType dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			CtProdServTypeDao daoObject = new CtProdServTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the product service type object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

}
