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

package com.comasw.ejb.instance;

import static com.comasw.model.Sequences.SEQ_SERVICE_ID;
import static com.comasw.model.Tables.IT_SERVICE;
import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.VW_SERVICE_INSTANCE;
import static org.jooq.impl.DSL.coalesce;
import static org.jooq.impl.DSL.exists;
import static org.jooq.impl.DSL.notExists;
import static org.jooq.impl.DSL.val;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.SelectQuery;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.daos.IdtServiceDao;
import com.comasw.model.tables.daos.ItServiceDao;
import com.comasw.model.tables.pojos.IdtService;
import com.comasw.model.tables.pojos.ItService;
import com.comasw.model.tables.pojos.VwServiceInstance;
import com.comasw.model.tables.records.ItServiceRecord;

/**
 * Session Bean implementation class ServiceEJB
 */
@Stateless
@LocalBean
public class ServiceEJB implements ServiceEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ServiceEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;
	
	com.comasw.model.tables.ItService p = IT_SERVICE.as("p");
	com.comasw.model.tables.ItService p2 = IT_SERVICE.as("p2");
	com.comasw.model.tables.PtStatus s = PT_STATUS.as("s");

	/**
	 * Default constructor.
	 */
	public ServiceEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ItService> findAllData(boolean includeCancelledData) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItService> result = null;

		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
						.where(p.SERVICE_ID.eq(p2.SERVICE_ID))));
			}

			query.addOrderBy(p.PRODUCT_ID, p.CODE, p.SERVICE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItService.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItService> findDataByServiceId(Integer serviceId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItService> result = null;
		com.comasw.model.tables.ItService p = IT_SERVICE.as("p");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(p.SERVICE_ID.eq(val(serviceId)));

			query.addOrderBy(p.PRODUCT_ID, p.CODE, p.SERVICE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItService.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItService> findDataByServiceNumber(String serviceNumber, boolean includeCancelledData)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItService> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(p.SERVICE_NUMBER.eq(val(serviceNumber)));

			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
						.where(p.SERVICE_ID.eq(p2.SERVICE_ID))));
			}

			query.addOrderBy(p.PRODUCT_ID, p.SERVICE_NUMBER, p.CODE,  p.SERVICE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItService.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;

	}

	@Override
	public List<ItService> findDataBySearchDate(LocalDateTime searchDate, boolean includeCancelledData)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItService> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(val(searchDate).between(p.START_DATE, p.END_DATE));

			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
						.where(p.SERVICE_ID.eq(p2.SERVICE_ID))));
			}

			query.addOrderBy(p.PRODUCT_ID, p.SERVICE_NUMBER, p.CODE, p.SERVICE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItService.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public ItService findDataBySearchDateAndServiceId(LocalDateTime searchDate, Integer serviceId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItService> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_SERVICE).where(IT_SERVICE.SERVICE_ID.eq(val(serviceId)))
					.and(val(searchDate).between(IT_SERVICE.START_DATE, IT_SERVICE.END_DATE))
					.orderBy(IT_SERVICE.PRODUCT_ID, IT_SERVICE.SERVICE_NUMBER, IT_SERVICE.CODE, IT_SERVICE.SERVICE_ID, IT_SERVICE.START_DATE,
							IT_SERVICE.END_DATE)
					.fetch().into(ItService.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the account for search date: " + searchDate.toString()
						+ " and service id : " + serviceId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	public List<ItService> findDataBySearchDateAndServiceNumber(LocalDateTime searchDate, String serviceNumber,
			boolean includeCancelledData) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItService> result = null;
		com.comasw.model.tables.ItService p = IT_SERVICE.as("p");
		com.comasw.model.tables.ItService p2 = IT_SERVICE.as("p2");
		com.comasw.model.tables.PtStatus s = PT_STATUS.as("s");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(p.SERVICE_NUMBER.eq(val(serviceNumber)));
			query.addConditions(val(searchDate).between(p.START_DATE, p.END_DATE));

			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
						.where(p.SERVICE_ID.eq(p2.SERVICE_ID))));
			}

			query.addOrderBy(p.PRODUCT_ID, p.SERVICE_NUMBER, p.CODE, p.SERVICE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItService.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItService> findInstanceWithParameters(Optional<LocalDateTime> searchDate, boolean includeCancelledData,
			Optional<Integer> serviceId, Optional<Integer> serviceTypeId, Optional<String> serviceNumber, Optional<Integer> statusId,
			Optional<Integer> productId, Optional<Integer> productTypeId, Optional<String> contractNr,
			Optional<Integer> accountId, Optional<String> accountIdentityCard, Optional<String> accountContactPhone,
			Optional<Integer> customerId, Optional<String> customerIdentityCard, Optional<String> customerContactPhone)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItService> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;


		Integer productTypeIdAux = null;
		Integer accountIdAux = null;
		String contractNrAux = null;
		String accountIdentityCardAux = null;
		String accountContactPhoneAux = null;
		Integer customerIdAux = null;
		String customerIdentityCardAux = null;
		String customerContactPhoneAux = null;

		if ((!searchDate.isPresent()) && (!serviceId.isPresent()) && (!serviceTypeId.isPresent())
				&& ((!serviceNumber.isPresent()) || serviceNumber.get().isEmpty()) && (!statusId.isPresent())
				&& (!productId.isPresent()) && ((!contractNr.isPresent()) || contractNr.get().isEmpty())
				&& (!accountId.isPresent())
				&& ((!accountIdentityCard.isPresent()) || accountIdentityCard.get().isEmpty())
				&& ((!accountContactPhone.isPresent()) || accountContactPhone.get().isEmpty())
				&& (!customerId.isPresent())
				&& ((!customerIdentityCard.isPresent()) || customerIdentityCard.get().isEmpty())
				&& ((!customerContactPhone.isPresent()) || customerContactPhone.get().isEmpty())) {
			logger.error("ERROR - none of the criteria search was defined");
			throw new CoMaSwDataAccessException("ERROR - none of the chriteria search was defined");
		} else {
			try {
				query.addFrom(p);

				if (searchDate.isPresent()) {
					query.addConditions(val(searchDate.get()).between(p.START_DATE, p.END_DATE));
				}

				if (!includeCancelledData) {
					query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
							.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
							.where(p.SERVICE_ID.eq(p2.SERVICE_ID))));
				}

				if (serviceId.isPresent()) {
					query.addConditions(p.SERVICE_ID.eq(val(serviceId.get())));
				}
				
				if (serviceTypeId.isPresent()) {
					query.addConditions(p.SERVICE_TYPE_ID.eq(val(serviceTypeId.get())));
				}

				if (serviceNumber.isPresent()) {
					query.addConditions(p.SERVICE_NUMBER.eq(val(serviceNumber.get())));
				}

				if (productId.isPresent()) {
					query.addConditions(p.PRODUCT_ID.eq(val(productId.get())));
				}

				if (statusId.isPresent()) {
					query.addConditions(p.STATUS_ID.eq(val(statusId.get())));
				}

				if (productTypeId.isPresent() && accountId.isPresent()
						&& (contractNr.isPresent() && !contractNr.get().isEmpty())
						|| (accountIdentityCard.isPresent() && !accountContactPhone.get().isEmpty())
						|| (accountContactPhone.isPresent() && !accountContactPhone.get().isEmpty())
						|| (customerId.isPresent()
								|| (customerIdentityCard.isPresent() && (!customerIdentityCard.get().isEmpty())))
						|| (customerContactPhone.isPresent() && (!customerContactPhone.get().isEmpty()))) {

					
					
					if (productTypeId.isPresent()) {
						productTypeIdAux = productTypeId.get();
					}
					
					if (accountId.isPresent()) {
						accountIdAux = accountId.get();
					}

					if (contractNr.isPresent() && !contractNr.get().isEmpty()) {
						contractNrAux = contractNr.get().trim().toUpperCase();
					}

					if (accountIdentityCard.isPresent() && !accountIdentityCard.get().isEmpty()) {
						accountIdentityCardAux = accountContactPhone.get().trim().toUpperCase();
					}

					if (accountContactPhone.isPresent() && !accountContactPhone.get().isEmpty()) {
						accountContactPhoneAux = accountContactPhone.get().trim().toUpperCase();
					}
					if (customerId.isPresent()) {
						customerIdAux = customerId.get();
					}

					if (customerIdentityCard.isPresent() && !customerIdentityCard.get().isEmpty()) {
						customerIdentityCardAux = customerIdentityCard.get().trim().toUpperCase();
					}

					if (customerContactPhone.isPresent() && !customerContactPhone.get().isEmpty()) {
						customerContactPhoneAux = customerContactPhone.get().trim().toUpperCase();
					}

					query.addConditions(exists(create.selectOne().from(VW_SERVICE_INSTANCE)
							.where(VW_SERVICE_INSTANCE.SERVICE_ID.eq(p.SERVICE_ID)
									.and(VW_SERVICE_INSTANCE.PRODUCT_ID.eq(p.PRODUCT_ID))
									.and(p.START_DATE.lessOrEqual(VW_SERVICE_INSTANCE.SERVICE_END_DATE))
									.and(p.END_DATE.lessOrEqual(VW_SERVICE_INSTANCE.SERVICE_START_DATE))
									.and(p.START_DATE.lessOrEqual(VW_SERVICE_INSTANCE.PRODUCT_END_DATE))
									.and(p.END_DATE.lessOrEqual(VW_SERVICE_INSTANCE.PRODUCT_START_DATE))
									.and(p.START_DATE.lessOrEqual(VW_SERVICE_INSTANCE.ACCOUNT_END_DATE))
									.and(p.END_DATE.lessOrEqual(VW_SERVICE_INSTANCE.ACCOUNT_START_DATE))
									.and(p.START_DATE.lessOrEqual(VW_SERVICE_INSTANCE.CUSTOMER_END_DATE))
									.and(p.END_DATE.lessOrEqual(VW_SERVICE_INSTANCE.CUSTOMER_START_DATE))
									.and(VW_SERVICE_INSTANCE.PRODUCT_TYPE_ID
											.eq(coalesce(val(productTypeIdAux), VW_SERVICE_INSTANCE.PRODUCT_TYPE_ID)))
									.and(VW_SERVICE_INSTANCE.ACCOUNT_ID
											.eq(coalesce(val(accountIdAux), VW_SERVICE_INSTANCE.ACCOUNT_ID)))
									.and(VW_SERVICE_INSTANCE.CONTRACT_NUMBER
											.eq(coalesce(val(contractNrAux), VW_SERVICE_INSTANCE.CONTRACT_NUMBER)))
									.and(VW_SERVICE_INSTANCE.ACCOUNT_IDENTITY_CARD.eq(coalesce(
											val(accountIdentityCardAux), VW_SERVICE_INSTANCE.ACCOUNT_IDENTITY_CARD)))
									.and(VW_SERVICE_INSTANCE.ACCOUNT_CONTACT_PHONE.eq(coalesce(
											val(accountContactPhoneAux), VW_SERVICE_INSTANCE.ACCOUNT_CONTACT_PHONE)))
									.and(VW_SERVICE_INSTANCE.CUSTOMER_ID
											.eq(coalesce(val(customerIdAux), VW_SERVICE_INSTANCE.CUSTOMER_ID)))
									.and(VW_SERVICE_INSTANCE.CUSTOMER_IDENTITY_CARD.eq(coalesce(
											val(customerIdentityCardAux), VW_SERVICE_INSTANCE.CUSTOMER_IDENTITY_CARD)))
									.and(VW_SERVICE_INSTANCE.CUSTOMER_CONTACT_PHONE
											.eq(coalesce(val(customerContactPhoneAux),
													VW_SERVICE_INSTANCE.CUSTOMER_CONTACT_PHONE))))));
				}

				query.addOrderBy(p.PRODUCT_ID, p.SERVICE_NUMBER, p.CODE, 
 p.SERVICE_ID, p.START_DATE, p.END_DATE);
				result = query.fetchInto(ItService.class);

			} catch (DataAccessException e) {
				errorMessage = "Error while try to find all the service - " + e.getMessage();
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage, e);
			}
		}

		return result;
	}

	@Override
	public List<VwServiceInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate,
			boolean includeCancelledData, Optional<Integer> serviceId, Optional<Integer> serviceTypeId, Optional<String> serviceNumber,
			Optional<Integer> statusId, Optional<Integer> productId, Optional<Integer> productTypeId,
			Optional<String> contractNr, Optional<Integer> accountId, Optional<String> accountIdentityCard,
			Optional<String> accountContactPhone, Optional<Integer> customerId, Optional<String> customerIdentityCard,
			Optional<String> customerContactPhone) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwServiceInstance> result = null;
		com.comasw.model.tables.VwServiceInstance p = VW_SERVICE_INSTANCE.as("p");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		if ((!searchDate.isPresent()) && (!serviceId.isPresent()) && (!serviceTypeId.isPresent())
				&& ((!serviceNumber.isPresent()) || serviceNumber.get().isEmpty()) && (!statusId.isPresent())
				&& (!productId.isPresent()) && (!productTypeId.isPresent())
				&& ((!contractNr.isPresent()) || contractNr.get().isEmpty()) && (!accountId.isPresent())
				&& ((!accountIdentityCard.isPresent()) || accountIdentityCard.get().isEmpty())
				&& ((!accountContactPhone.isPresent()) || accountContactPhone.get().isEmpty())
				&& (!customerId.isPresent())
				&& ((!customerIdentityCard.isPresent()) || customerIdentityCard.get().isEmpty())
				&& ((!customerContactPhone.isPresent()) || customerContactPhone.get().isEmpty())) {
			logger.error("ERROR - none of the criteria search was defined");
			throw new CoMaSwDataAccessException("ERROR - none of the chriteria search was defined");
		} else {
			try {
				query.addFrom(p);

				if (searchDate.isPresent()) {
					query.addConditions(val(searchDate.get()).between(p.SERVICE_START_DATE, p.SERVICE_END_DATE)
							.and(val(searchDate.get()).between(p.PRODUCT_START_DATE, p.PRODUCT_END_DATE))
							.and(val(searchDate.get()).between(p.CUSTOMER_START_DATE, p.CUSTOMER_END_DATE))
							.and(val(searchDate.get()).between(p.ACCOUNT_START_DATE, p.ACCOUNT_END_DATE)));
				}

				if (!includeCancelledData) {
					query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
							.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
							.where(p.SERVICE_ID.eq(p2.SERVICE_ID))));
				}

				if (serviceId.isPresent()) {
					query.addConditions(p.SERVICE_ID.eq(val(serviceId.get())));
				}
				if (serviceTypeId.isPresent()) {
					query.addConditions(p.SERVICE_TYPE_ID.eq(val(serviceTypeId.get())));
				}

				if (serviceNumber.isPresent()) {
					query.addConditions(p.SERVICE_NUMBER.eq(val(serviceNumber.get())));
				}

				if (productId.isPresent()) {
					query.addConditions(p.PRODUCT_ID.eq(val(productId.get())));
				}

				if (productTypeId.isPresent()) {
					query.addConditions(p.PRODUCT_TYPE_ID.eq(val(productTypeId.get())));
				}

				if (statusId.isPresent()) {
					query.addConditions(p.PRODUCT_STATUS_ID.eq(val(statusId.get())));
				}

				if (accountId.isPresent()) {
					query.addConditions(p.ACCOUNT_ID.eq(val(accountId.get())));
				}

				if (contractNr.isPresent() && !contractNr.get().isEmpty()) {
					query.addConditions(p.CONTRACT_NUMBER.eq(val(contractNr.get())));
				}

				if (accountIdentityCard.isPresent() && !accountIdentityCard.get().isEmpty()) {
					query.addConditions(p.ACCOUNT_IDENTITY_CARD.eq(val(accountIdentityCard.get())));
				}

				if (accountContactPhone.isPresent() && !accountContactPhone.get().isEmpty()) {
					query.addConditions(p.ACCOUNT_CONTACT_PHONE.eq(val(accountContactPhone.get())));
				}

				if (customerId.isPresent()) {
					query.addConditions(p.CUSTOMER_ID.eq(val(customerId.get())));
				}

				if (customerIdentityCard.isPresent() && !customerIdentityCard.get().isEmpty()) {
					query.addConditions(p.CUSTOMER_IDENTITY_CARD.eq(val(customerIdentityCard.get())));
				}

				if (customerContactPhone.isPresent() && !customerContactPhone.get().isEmpty()) {
					query.addConditions(p.CUSTOMER_CONTACT_PHONE.eq(val(customerContactPhone.get())));
				}

				query.addOrderBy(p.PRODUCT_ID, p.SERVICE_NUMBER, p.SERVICE_CODE, p.SERVICE_ID, p.SERVICE_START_DATE, p.SERVICE_END_DATE);
				result = query.fetchInto(VwServiceInstance.class);

			} catch (DataAccessException e) {
				errorMessage = "Error while try to find all the service - " + e.getMessage();
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage, e);
			}
		}

		return result;
	}

	@Override
	public Integer getNewId() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		String errorMessage;
		Integer id = 0;
		try {
			id = Math.toIntExact(create.nextval(SEQ_SERVICE_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new service " + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;
	}

	@Override
	public Integer insertData(ItService dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();

			if (id > 0) {
				// insert the new id value into the idType datatable
				IdtService objectId = new IdtService(id);
				IdtServiceDao daoObjectId = new IdtServiceDao(configuration);
				daoObjectId.insert(objectId);

				// insert the dataObject into the type datatable
				dataObject.setServiceId(id);
				ItServiceDao daoObject = new ItServiceDao(configuration);
				daoObject.insert(dataObject);
			}

			return id;

		} catch (Exception e) {
			errorMessage = "Error inserting the service object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertNewHistoricDataRecord(ItService dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItServiceDao daoObject = new ItServiceDao(configuration);
			daoObject.insert(dataObject);

		} catch (Exception e) {
			errorMessage = "Error inserting the service object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateHistoricDataRecord(ItService dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		ItServiceRecord record;
		try {
			record = create.newRecord(IT_SERVICE, dataObject);
			record.store();

		} catch (Exception e) {
			errorMessage = "Error inserting the service object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void updateData(ItService dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItServiceDao daoObject = new ItServiceDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the service object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(ItService dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItServiceDao daoObject = new ItServiceDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the service object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}
}