package com.comasw.ejb.instance;


import static com.comasw.model.Sequences.SEQ_PRODUCT_ID;
import static com.comasw.model.Tables.IT_CUSTOMER;
import static com.comasw.model.Tables.IT_PRODUCT;
import static com.comasw.model.Tables.IT_ACCOUNT;
import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.VW_PRODUCT_INSTANCE;

import static org.jooq.impl.DSL.val;
import static org.jooq.impl.DSL.upper;
import static org.jooq.impl.DSL.*;

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
import com.comasw.model.tables.daos.IdtProductDao;
import com.comasw.model.tables.daos.ItProductDao;
import com.comasw.model.tables.pojos.IdtProduct;
import com.comasw.model.tables.pojos.ItProduct;
import com.comasw.model.tables.pojos.VwProductInstance;
import com.comasw.model.tables.records.ItProductRecord;

/**
 * Session Bean implementation class ProductEJB
 */
@Stateless
@LocalBean
public class ProductEJB implements ProductEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ProductEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;

	// com.comasw.model.tables.CtProdFeeType pft = CT_PROD_FEE_TYPE.as("pft");
	// com.comasw.model.tables.PtApplicationLevel al =
	// PT_APPLICATION_LEVEL.as("al");

	/**
	 * Default constructor.
	 */
	public ProductEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ItProduct> findAllData(Optional<Boolean> includeCancelledData) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItProduct> result = null;
		com.comasw.model.tables.ItProduct p = IT_PRODUCT.as("p");
		com.comasw.model.tables.ItProduct p2 = IT_PRODUCT.as("p2");
		com.comasw.model.tables.PtStatus s = PT_STATUS.as("s");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			if (includeCancelledData.isPresent() && (!includeCancelledData.get())) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
						.where(p.PRODUCT_ID.eq(p2.PRODUCT_ID))));
			}

			query.addOrderBy(p.ACCOUNT_ID, p.CODE, p.PRODUCT_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItProduct.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the product - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItProduct> findDataByProductId(Integer productId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItProduct> result = null;
		com.comasw.model.tables.ItProduct p = IT_PRODUCT.as("p");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(p.PRODUCT_ID.eq(val(productId)));
			
			query.addOrderBy(p.ACCOUNT_ID, p.CODE, p.PRODUCT_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItProduct.class);
			

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the product - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItProduct> findDataBySearchDate(LocalDateTime searchDate, boolean includeCancelledData)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItProduct> result = null;
		com.comasw.model.tables.ItProduct p = IT_PRODUCT.as("p");
		com.comasw.model.tables.ItProduct p2 = IT_PRODUCT.as("p2");
		com.comasw.model.tables.PtStatus s = PT_STATUS.as("s");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		try {
			query.addFrom(p);
			query.addConditions(val(searchDate).between(p.START_DATE, p.END_DATE));

			if (!includeCancelledData) {
				query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
						.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
						.where(p.PRODUCT_ID.eq(p2.PRODUCT_ID))));
			}

			query.addOrderBy(p.ACCOUNT_ID, p.CODE, p.PRODUCT_ID, p.START_DATE, p.END_DATE);
			result = query.fetchInto(ItProduct.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the product - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public ItProduct findDataBySearchDateAndProductId(LocalDateTime searchDate, Integer productId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItProduct> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_PRODUCT).where(IT_PRODUCT.PRODUCT_ID.eq(val(productId)))
					.and(val(searchDate).between(IT_PRODUCT.START_DATE, IT_PRODUCT.END_DATE))
					.orderBy(IT_PRODUCT.ACCOUNT_ID, IT_PRODUCT.CODE, IT_PRODUCT.PRODUCT_ID, IT_PRODUCT.START_DATE, IT_PRODUCT.END_DATE)
					.fetch().into(ItProduct.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the account for search date: " + searchDate.toString()
						+ " and account id : " + productId + " - The query returns more rows(" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the product - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public List<ItProduct> findInstanceWithParameters(Optional<LocalDateTime> searchDate,
			boolean includeCancelledData, Optional<Integer> productId, Optional<Integer> statusId,
			Optional<String> contractNr, Optional<Integer> accountId, Optional<String> accountIdentityCard,
			Optional<String> accountContactPhone, Optional<Integer> customerId, Optional<String> customerIdentityCard,
			Optional<String> customerContactPhone) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItProduct> result = null;
		com.comasw.model.tables.ItProduct p = IT_PRODUCT.as("p");
		com.comasw.model.tables.ItProduct p2 = IT_PRODUCT.as("p2");
		com.comasw.model.tables.PtStatus s = PT_STATUS.as("s");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		String contractNrAux = null;
		String accountIdentityCardAux = null;
		String accountContactPhoneAux = null;
		Integer customerIdAux = null;
		String customerIdentityCardAux = null;
		String customerContactPhoneAux = null;

		if ((!searchDate.isPresent()) && (!productId.isPresent()) && (!statusId.isPresent())
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
					query.addConditions(val(searchDate.get()).between(p.START_DATE, p.END_DATE));
				}

				if (!includeCancelledData) {
					query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
							.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
							.where(p.PRODUCT_ID.eq(p2.PRODUCT_ID))));
				}

				if (productId.isPresent()) {
					query.addConditions(p.PRODUCT_ID.eq(val(productId.get())));
				}

				if (statusId.isPresent()) {
					query.addConditions(p.STATUS_ID.eq(val(statusId.get())));
				}

				if (accountId.isPresent()) {
					query.addConditions(p.ACCOUNT_ID.eq(val(accountId.get())));
				}

				if ((contractNr.isPresent() && !contractNr.get().isEmpty())
						|| (accountIdentityCard.isPresent() && !accountContactPhone.get().isEmpty())
						|| (accountContactPhone.isPresent() && !accountContactPhone.get().isEmpty())
						|| (customerId.isPresent()
								|| (customerIdentityCard.isPresent() && (!customerIdentityCard.get().isEmpty())))
						|| (customerContactPhone.isPresent() && (!customerContactPhone.get().isEmpty()))) {

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

					query.addConditions(
							p.ACCOUNT_ID.in(create.select(IT_ACCOUNT.ACCOUNT_ID).from(IT_ACCOUNT).innerJoin(IT_CUSTOMER)
									.on((IT_ACCOUNT.CUSTOMER_ID.eq(IT_CUSTOMER.CUSTOMER_ID))
											.and(IT_ACCOUNT.START_DATE.lessOrEqual(IT_CUSTOMER.END_DATE))
											.and(IT_ACCOUNT.END_DATE.lessOrEqual(IT_CUSTOMER.START_DATE)))
									.where(p.START_DATE.lessOrEqual(IT_ACCOUNT.END_DATE))
									.and(p.END_DATE.lessOrEqual(IT_ACCOUNT.START_DATE))
									.and(IT_ACCOUNT.CUSTOMER_ID
											.eq(coalesce(val(customerIdAux), IT_ACCOUNT.CUSTOMER_ID)))
									.and(upper(IT_ACCOUNT.IDENTITY_CARD)
											.eq(coalesce(val(accountIdentityCardAux), IT_ACCOUNT.IDENTITY_CARD)))
									.and(upper(IT_ACCOUNT.CONTACT_PHONE)
											.eq(coalesce(val(accountContactPhoneAux), IT_ACCOUNT.CONTACT_PHONE)))
									.and(upper(IT_ACCOUNT.CONTRACT_NUMBER)
											.eq(coalesce(val(contractNrAux), IT_ACCOUNT.CONTRACT_NUMBER)))
									.and(upper(IT_CUSTOMER.IDENTITY_CARD)
											.eq(coalesce(val(customerIdentityCardAux), IT_CUSTOMER.IDENTITY_CARD)))
									.and(upper(IT_CUSTOMER.CONTACT_PHONE)
											.eq(coalesce(val(customerContactPhoneAux), IT_CUSTOMER.CONTACT_PHONE)))));

				}

				query.addOrderBy(p.ACCOUNT_ID, p.CODE, p.PRODUCT_ID, p.START_DATE, p.END_DATE);
				result = query.fetchInto(ItProduct.class);

			} catch (DataAccessException e) {
				errorMessage = "Error while try to find all the product - " + e.getMessage();
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage, e);
			}
		}

		return result;
	}
	
	
	@Override
	public List<VwProductInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate, 
			boolean includeCancelledData, Optional<Integer> productId,
			Optional<Integer> statusId, Optional<String> contractNr,
			Optional<Integer> accountId, 
			Optional<String> accountIdentityCard, Optional<String> accountContactPhone, 
			Optional<Integer> customerId, 
			Optional<String> customerIdentityCard, Optional<String> customerContactPhone) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<VwProductInstance> result = null;
		com.comasw.model.tables.VwProductInstance p = VW_PRODUCT_INSTANCE.as("p");
		com.comasw.model.tables.ItProduct p2 = IT_PRODUCT.as("p2");
		com.comasw.model.tables.PtStatus s = PT_STATUS.as("s");
		SelectQuery<Record> query = create.selectQuery();
		String errorMessage;

		if ((!searchDate.isPresent()) && (!productId.isPresent()) && (!statusId.isPresent())
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
					query.addConditions(val(searchDate.get()).between(p.PRODUCT_START_DATE, p.PRODUCT_END_DATE)
							.and(val(searchDate.get()).between(p.CUSTOMER_START_DATE, p.CUSTOMER_END_DATE))
							.and(val(searchDate.get()).between(p.ACCOUNT_START_DATE, p.ACCOUNT_END_DATE)));
				}

				if (!includeCancelledData) {
					query.addConditions(notExists(create.selectOne().from(p2).innerJoin(s)
							.on(p2.STATUS_ID.eq(s.STATUS_ID).and(s.CODE.eq(val("'CANC'"))))
							.where(p.PRODUCT_ID.eq(p2.PRODUCT_ID))));
				}

				if (productId.isPresent()) {
					query.addConditions(p.PRODUCT_ID.eq(val(productId.get())));
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
				
				query.addOrderBy(p.ACCOUNT_ID, p.PRODUCT_CODE, p.PRODUCT_ID, p.PRODUCT_START_DATE, p.PRODUCT_END_DATE);
				result = query.fetchInto(VwProductInstance.class);

			} catch (DataAccessException e) {
				errorMessage = "Error while try to find all the product - " + e.getMessage();
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
			id = Math.toIntExact(create.nextval(SEQ_PRODUCT_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new product " + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;
	}

	@Override
	public Integer insertData(ItProduct dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();

			if (id > 0) {
				// insert the new id value into the idType datatable
				IdtProduct objectId = new IdtProduct(id);
				IdtProductDao daoObjectId = new IdtProductDao(configuration);
				daoObjectId.insert(objectId);

				// insert the dataObject into the type datatable
				dataObject.setProductId(id);
				ItProductDao daoObject = new ItProductDao(configuration);
				daoObject.insert(dataObject);
			}
			
			return id;
			
		} catch (Exception e) {
			errorMessage = "Error inserting the product object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void insertNewHistoricDataRecord(ItProduct dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItProductDao daoObject = new ItProductDao(configuration);
			daoObject.insert(dataObject);

		} catch (Exception e) {
			errorMessage = "Error inserting the product object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public void updateHistoricDataRecord(ItProduct dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		ItProductRecord record;
		try {
			record = create.newRecord(IT_PRODUCT, dataObject);
			record.store();

		} catch (Exception e) {
			errorMessage = "Error inserting the product object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void updateData(ItProduct dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItProductDao daoObject = new ItProductDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the product object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

	}

	@Override
	public void deleteData(ItProduct dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItProductDao daoObject = new ItProductDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the product object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

}
