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

package com.comasw.ejb.instance;

import static com.comasw.model.Sequences.SEQ_FEE_ID;
import static com.comasw.model.Tables.IT_FEE;
import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.VW_FEE_INSTANCE;
import static org.jooq.impl.DSL.coalesce;
import static org.jooq.impl.DSL.exists;
import static org.jooq.impl.DSL.notExists;
import static org.jooq.impl.DSL.val;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
import com.comasw.model.tables.daos.IdtFeeDao;
import com.comasw.model.tables.daos.ItFeeDao;
import com.comasw.model.tables.pojos.IdtFee;
import com.comasw.model.tables.pojos.ItFee;
import com.comasw.model.tables.pojos.VwFeeInstance;
import com.comasw.model.tables.records.ItFeeRecord;

/**
 * Session Bean implementation class FeeEJB
 */
@Stateless
@LocalBean
public class FeeEJB implements FeeEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(FeeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	private static ResourceBundle dbDefinitions = ResourceBundle.getBundle("com.comasw.properties.dataBaseDefinitions");

	private static final String APPLICATION_LEVEL_PROD = dbDefinitions.getString("APPLICATION_LEVEL_CODE_PROD");
	private static final String APPLICATION_LEVEL_SERV = dbDefinitions.getString("APPLICATION_LEVEL_CODE_SERV");

	com.comasw.model.tables.ItFee p = IT_FEE.as("p");
	com.comasw.model.tables.ItFee p2 = IT_FEE.as("p2");
	com.comasw.model.tables.PtStatus s = PT_STATUS.as("s");

	/**
	 * Default constructor.
	 */
	public FeeEJB() {
		// TODO Auto-generated method stub
	}

	@Override
	public List<ItFee> findAllData(boolean includeCancelledData) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItFee> result = null;

		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'")))).where(p.FEE_ID.eq(p2.FEE_ID))));
			}

			query.addOrderBy(p.PARENT_INSTANCE_ID, p.CODE, p.FEE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItFee.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the fee - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItFee> findDataByFeeId(Integer feeId) throws CoMaSwDataAccessException {

		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItFee> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(p.FEE_ID.eq(val(feeId)));

			query.addOrderBy(p.PARENT_INSTANCE_ID, p.CODE, p.FEE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItFee.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the fee - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItFee> findDataByCode(String code, boolean includeCancelledData) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItFee> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(p.CODE.eq(val(code)));

			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'")))).where(p.FEE_ID.eq(p2.FEE_ID))));
			}

			query.addOrderBy(p.PARENT_INSTANCE_ID, p.CODE, p.FEE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItFee.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;

	}

	@Override
	public List<ItFee> findDataBySearchDate(LocalDateTime searchDate, boolean includeCancelledData)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItFee> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(val(searchDate).between(p.START_DATE, p.END_DATE));

			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'")))).where(p.FEE_ID.eq(p2.FEE_ID))));
			}

			query.addOrderBy(p.PARENT_INSTANCE_ID, p.CODE, p.FEE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItFee.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public ItFee findDataBySearchDateAndFeeId(LocalDateTime searchDate, Integer feeId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItFee> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_FEE).where(IT_FEE.FEE_ID.eq(val(feeId)))
					.and(val(searchDate).between(IT_FEE.START_DATE, IT_FEE.END_DATE))
					.orderBy(IT_FEE.PARENT_INSTANCE_ID, IT_FEE.CODE, IT_FEE.FEE_ID, IT_FEE.START_DATE, IT_FEE.END_DATE)
					.fetch().into(ItFee.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the account for search date: " + searchDate.toString()
						+ " and fee id : " + feeId + " - The query returns a distinct number of rows (" + result.size()
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

	@Override
	public List<ItFee> findDataBySearchDateAndCode(LocalDateTime searchDate, String code, boolean includeCancelledData)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItFee> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(p.CODE.eq(val(code)));
			query.addConditions(val(searchDate).between(p.START_DATE, p.END_DATE));

			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'")))).where(p.FEE_ID.eq(p2.FEE_ID))));
			}

			query.addOrderBy(p.PARENT_INSTANCE_ID, p.CODE, p.FEE_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItFee.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the service - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItFee> findInstanceWithParameters(Optional<LocalDateTime> searchDate, boolean includeCancelledData,
			Optional<Integer> feeId, Optional<Integer> feeTypeId, Optional<String> feeCode, Optional<Integer> statusId,
			Optional<String> applicationLevelCode, Optional<Integer> serviceId, Optional<Integer> serviceTypeId, Optional<String> serviceNr,
			Optional<Integer> productId, Optional<Integer> productTypeId, Optional<String> contractNr,
			Optional<Integer> accountId, Optional<String> accountIdentityCard, Optional<String> accountContactPhone,
			Optional<Integer> customerId, Optional<String> customerIdentityCard, Optional<String> customerContactPhone)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItFee> result = null;
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		String applicatioLevelCodeAux = null;
		Integer serviceTypeIdAux = null;
		String serviceNrAux = null;
		Integer productTypeIdAux = null;
		Integer accountIdAux = null;
		String contractNrAux = null;
		String accountIdentityCardAux = null;
		String accountContactPhoneAux = null;
		Integer customerIdAux = null;
		String customerIdentityCardAux = null;
		String customerContactPhoneAux = null;

		if ((!searchDate.isPresent()) && (!feeId.isPresent()) && (!feeTypeId.isPresent())
				&& ((!feeCode.isPresent()) || feeCode.get().isEmpty()) && (!statusId.isPresent())
				&& (!applicationLevelCode.isPresent() || applicationLevelCode.get().isEmpty())
				&& (!serviceId.isPresent()) && (!serviceTypeId.isPresent())
				&& ((!serviceNr.isPresent()) || serviceNr.get().isEmpty())
				&& (!productId.isPresent())
				&& (!productTypeId.isPresent()) && ((!contractNr.isPresent()) || contractNr.get().isEmpty())
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
							.where(p.FEE_ID.eq(p2.FEE_ID))));
				}

				if (feeId.isPresent()) {
					query.addConditions(p.FEE_ID.eq(val(feeId.get())));
				}

				if (feeTypeId.isPresent()) {
					query.addConditions(p.FEE_TYPE_ID.eq(val(feeTypeId.get())));
				}

				if (feeCode.isPresent()) {
					query.addConditions(p.CODE.eq(val(feeCode.get())));
				}

				if (applicationLevelCode.isPresent()
						|| applicationLevelCode.get().equals(FeeEJB.APPLICATION_LEVEL_PROD)) {
					if (productId.isPresent()) {
						query.addConditions(p.PARENT_INSTANCE_ID.eq(val(productId.get())));
					}
				}

				if (applicationLevelCode.isPresent()
						|| applicationLevelCode.get().equals(FeeEJB.APPLICATION_LEVEL_SERV)) {
					if (serviceId.isPresent()) {
						query.addConditions(p.PARENT_INSTANCE_ID.eq(val(serviceId.get())));
					}
				}

				if (statusId.isPresent()) {
					query.addConditions(p.STATUS_ID.eq(val(statusId.get())));
				}

				if (serviceTypeId.isPresent() && productTypeId.isPresent() && accountId.isPresent()
						&& (serviceNr.isPresent() && !serviceNr.get().isEmpty())
						&& (contractNr.isPresent() && !contractNr.get().isEmpty())
						|| (accountIdentityCard.isPresent() && !accountContactPhone.get().isEmpty())
						|| (accountContactPhone.isPresent() && !accountContactPhone.get().isEmpty())
						|| (customerId.isPresent()
								|| (customerIdentityCard.isPresent() && (!customerIdentityCard.get().isEmpty())))
						|| (customerContactPhone.isPresent() && (!customerContactPhone.get().isEmpty()))) {

					if (applicationLevelCode.isPresent()) {
						applicatioLevelCodeAux = applicationLevelCode.get();
					}

					if (serviceTypeId.isPresent()) {
						serviceTypeIdAux = serviceTypeId.get();
					}
					
					if (serviceNr.isPresent() && !serviceNr.get().isEmpty()) {
						serviceNrAux = serviceNr.get().trim().toUpperCase();
					}


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

					query.addConditions(exists(create.selectOne().from(VW_FEE_INSTANCE).where(VW_FEE_INSTANCE.FEE_ID
							.eq(p.FEE_ID).and(VW_FEE_INSTANCE.PARENT_INSTANCE_ID.eq(p.PARENT_INSTANCE_ID))
							.and(p.START_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.SERVICE_END_DATE, p.START_DATE)))
							.and(p.END_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.SERVICE_START_DATE, p.END_DATE)))
							.and(p.START_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.PRODUCT_END_DATE, p.START_DATE)))
							.and(p.END_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.PRODUCT_START_DATE, p.END_DATE)))
							.and(p.START_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.ACCOUNT_END_DATE, p.START_DATE)))
							.and(p.END_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.ACCOUNT_START_DATE,p.END_DATE)))
							.and(p.START_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.CUSTOMER_END_DATE, p.START_DATE)))
							.and(p.END_DATE.lessOrEqual(coalesce(VW_FEE_INSTANCE.CUSTOMER_START_DATE, p.END_DATE)))
							.and(VW_FEE_INSTANCE.APPLICATION_LEVEL_CODE
									.eq(coalesce(val(applicatioLevelCodeAux), VW_FEE_INSTANCE.APPLICATION_LEVEL_CODE)))
							.and(VW_FEE_INSTANCE.PRODUCT_TYPE_ID
									.eq(coalesce(val(productTypeIdAux), VW_FEE_INSTANCE.PRODUCT_TYPE_ID)))
							.and(VW_FEE_INSTANCE.SERVICE_TYPE_ID
									.eq(coalesce(val(serviceTypeIdAux), VW_FEE_INSTANCE.SERVICE_TYPE_ID)))
							.and(VW_FEE_INSTANCE.SERVICE_NUMBER
									.eq(coalesce(val(serviceNrAux), VW_FEE_INSTANCE.SERVICE_NUMBER)))
							.and(VW_FEE_INSTANCE.ACCOUNT_ID.eq(coalesce(val(accountIdAux), VW_FEE_INSTANCE.ACCOUNT_ID)))
							.and(VW_FEE_INSTANCE.CONTRACT_NUMBER
									.eq(coalesce(val(contractNrAux), VW_FEE_INSTANCE.CONTRACT_NUMBER)))
							.and(VW_FEE_INSTANCE.ACCOUNT_IDENTITY_CARD
									.eq(coalesce(val(accountIdentityCardAux), VW_FEE_INSTANCE.ACCOUNT_IDENTITY_CARD)))
							.and(VW_FEE_INSTANCE.ACCOUNT_CONTACT_PHONE
									.eq(coalesce(val(accountContactPhoneAux), VW_FEE_INSTANCE.ACCOUNT_CONTACT_PHONE)))
							.and(VW_FEE_INSTANCE.CUSTOMER_ID
									.eq(coalesce(val(customerIdAux), VW_FEE_INSTANCE.CUSTOMER_ID)))
							.and(VW_FEE_INSTANCE.CUSTOMER_IDENTITY_CARD
									.eq(coalesce(val(customerIdentityCardAux), VW_FEE_INSTANCE.CUSTOMER_IDENTITY_CARD)))
							.and(VW_FEE_INSTANCE.CUSTOMER_CONTACT_PHONE.eq(
									coalesce(val(customerContactPhoneAux), VW_FEE_INSTANCE.CUSTOMER_CONTACT_PHONE))))));
				}

				query.addOrderBy(p.PARENT_INSTANCE_ID, p.CODE, p.CODE, p.FEE_ID, p.START_DATE, p.END_DATE);
				result = query.fetchInto(ItFee.class);

			} catch (DataAccessException e) {
				errorMessage = "Error while try to find all the fee - " + e.getMessage();
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage, e);
			}
		}

		return result;
	}

	@Override
	public List<VwFeeInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate,
			boolean includeCancelledData, Optional<Integer> feeId, Optional<Integer> feeTypeId,
			Optional<String> feeCode, Optional<Integer> statusId, Optional<String> applicationLevelCode,
			Optional<Integer> serviceId, Optional<Integer> serviceTypeId, Optional<String> serviceNr,
			Optional<Integer> productId,
			Optional<Integer> productTypeId, Optional<String> contractNr, Optional<Integer> accountId,
			Optional<String> accountIdentityCard, Optional<String> accountContactPhone, Optional<Integer> customerId,
			Optional<String> customerIdentityCard, Optional<String> customerContactPhone)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwFeeInstance> result = null;
		com.comasw.model.tables.VwFeeInstance p = VW_FEE_INSTANCE.as("p");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		if ((!searchDate.isPresent()) && (!feeId.isPresent()) && (!feeTypeId.isPresent())
				&& ((!feeCode.isPresent()) || feeCode.get().isEmpty()) && (!statusId.isPresent())
				&& (!applicationLevelCode.isPresent() || applicationLevelCode.get().isEmpty())
				&& (!serviceId.isPresent()) && (!serviceTypeId.isPresent()) 
				&& ((!serviceNr.isPresent()) || serviceNr.get().isEmpty())
				&& (!productId.isPresent())
				&& (!productTypeId.isPresent()) && ((!contractNr.isPresent()) || contractNr.get().isEmpty())
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
					query.addConditions((val(searchDate.get()).between(p.FEE_START_DATE, p.FEE_END_DATE))
							.and(val(searchDate.get()).between(coalesce(p.SERVICE_START_DATE, val(searchDate.get())), coalesce(p.SERVICE_END_DATE, val(searchDate.get()))))
							.and(val(searchDate.get()).between(coalesce(p.PRODUCT_START_DATE, val(searchDate.get())), coalesce(p.PRODUCT_END_DATE,val(searchDate.get()))))
							.and(val(searchDate.get()).between(coalesce(p.CUSTOMER_START_DATE, val(searchDate.get())), coalesce(p.CUSTOMER_END_DATE, val(searchDate.get()))))
							.and(val(searchDate.get()).between(coalesce(p.ACCOUNT_START_DATE, val(searchDate.get())), coalesce(p.ACCOUNT_END_DATE,val(searchDate.get())))));
				}

				if (!includeCancelledData) {
					query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
							.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
							.where(p.FEE_ID.eq(p2.FEE_ID))));
				}

				if (feeId.isPresent()) {
					query.addConditions(p.FEE_ID.eq(val(feeId.get())));
				}
				if (feeTypeId.isPresent()) {
					query.addConditions(p.FEE_TYPE_ID.eq(val(feeTypeId.get())));
				}

				if (feeCode.isPresent()) {
					query.addConditions(p.FEE_CODE.eq(val(feeCode.get().trim().toUpperCase())));
				}

				if (statusId.isPresent()) {
					query.addConditions(p.PRODUCT_STATUS_ID.eq(val(statusId.get())));
				}

				if (applicationLevelCode.isPresent()) {
					query.addConditions(p.APPLICATION_LEVEL_CODE.eq(val(applicationLevelCode.get().trim().toUpperCase())));

				}

				if (serviceId.isPresent()) {
					query.addConditions(p.SERVICE_ID.eq(val(serviceId.get())));
				}
				if (serviceTypeId.isPresent()) {
					query.addConditions(p.SERVICE_TYPE_ID.eq(val(serviceTypeId.get())));
				}
				if (serviceNr.isPresent() && !serviceNr.get().isEmpty()) {
					query.addConditions(p.SERVICE_NUMBER.eq(val(serviceNr.get().trim().toUpperCase())));
				}

				if (productId.isPresent()) {
					query.addConditions(p.PRODUCT_ID.eq(val(productId.get())));
				}
				if (productTypeId.isPresent()) {
					query.addConditions(p.PRODUCT_TYPE_ID.eq(val(productTypeId.get())));
				}

				if (contractNr.isPresent() && !contractNr.get().isEmpty()) {
					query.addConditions(p.CONTRACT_NUMBER.eq(val(contractNr.get().trim().toUpperCase())));
				}

				if (accountId.isPresent()) {
					query.addConditions(p.ACCOUNT_ID.eq(val(accountId.get())));
				}

				if (accountIdentityCard.isPresent() && !accountIdentityCard.get().isEmpty()) {
					query.addConditions(p.ACCOUNT_IDENTITY_CARD.eq(val(accountIdentityCard.get().trim().toUpperCase())));
				}

				if (accountContactPhone.isPresent() && !accountContactPhone.get().isEmpty()) {
					query.addConditions(p.ACCOUNT_CONTACT_PHONE.eq(val(accountContactPhone.get().trim().toUpperCase())));
				}

				if (customerId.isPresent()) {
					query.addConditions(p.CUSTOMER_ID.eq(val(customerId.get())));
				}

				if (customerIdentityCard.isPresent() && !customerIdentityCard.get().isEmpty()) {
					query.addConditions(p.CUSTOMER_IDENTITY_CARD.eq(val(customerIdentityCard.get().trim().toUpperCase())));
				}

				if (customerContactPhone.isPresent() && !customerContactPhone.get().isEmpty()) {
					query.addConditions(p.CUSTOMER_CONTACT_PHONE.eq(val(customerContactPhone.get().trim().toUpperCase())));
				}

				query.addOrderBy(p.PARENT_INSTANCE_ID, p.FEE_CODE, p.FEE_ID, p.FEE_START_DATE, p.FEE_END_DATE);
				result = query.fetchInto(VwFeeInstance.class);

			} catch (DataAccessException e) {
				errorMessage = "Error while try to find all the fee - " + e.getMessage();
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
			id = Math.toIntExact(create.nextval(SEQ_FEE_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new fee " + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;
	}

	@Override
	public Integer insertData(ItFee dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();

			if (id > 0) {
				// insert the new id value into the idType datatable
				IdtFee objectId = new IdtFee(id);
				IdtFeeDao daoObjectId = new IdtFeeDao(configuration);
				daoObjectId.insert(objectId);

				// insert the dataObject into the type datatable
				dataObject.setFeeId(id);
				ItFeeDao daoObject = new ItFeeDao(configuration);
				daoObject.insert(dataObject);
			}

			return id;

		} catch (Exception e) {
			errorMessage = "Error inserting the fee object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void insertNewHistoricDataRecord(ItFee dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItFeeDao daoObject = new ItFeeDao(configuration);
			daoObject.insert(dataObject);

		} catch (Exception e) {
			errorMessage = "Error inserting the fee object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void updateHistoricDataRecord(ItFee dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		ItFeeRecord record;
		try {
			record = create.newRecord(IT_FEE, dataObject);
			record.store();

		} catch (Exception e) {
			errorMessage = "Error inserting the fee object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(ItFee dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItFeeDao daoObject = new ItFeeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the fee object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void deleteData(ItFee dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItFeeDao daoObject = new ItFeeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the fee object (value: " + dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

}
