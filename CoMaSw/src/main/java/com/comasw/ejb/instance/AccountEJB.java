package com.comasw.ejb.instance;

import static com.comasw.model.Sequences.SEQ_ACCOUNT_ID;
import static com.comasw.model.Tables.IT_ACCOUNT;
import static com.comasw.model.Tables.IT_CUSTOMER;
import static org.jooq.impl.DSL.upper;
import static org.jooq.impl.DSL.val;
import static org.jooq.impl.DSL.exists;

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
import com.comasw.model.tables.daos.IdtAccountDao;
import com.comasw.model.tables.daos.ItAccountDao;
import com.comasw.model.tables.pojos.IdtAccount;
import com.comasw.model.tables.pojos.ItAccount;
import com.comasw.model.tables.records.ItAccountRecord;
import com.comasw.utilities.Formatter;

/**
 * Session Bean implementation class AccountEJB
 */
@Stateless
@LocalBean
public class AccountEJB implements AccountEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(AccountEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;
	
    /**
     * Default constructor. 
     */
    public AccountEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<ItAccount> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT)
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the accounts - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItAccount> findDataByAccountId(Integer accountId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT).where(IT_ACCOUNT.ACCOUNT_ID.eq(val(accountId)))
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find account with id " + accountId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItAccount> findDataByCustomerId(Integer customerId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT).where(IT_ACCOUNT.CUSTOMER_ID.eq(val(customerId)))
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

			return result;

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the account for customer id: " + customerId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public List<ItAccount> findDataByContactId(Integer contractId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT).where(IT_ACCOUNT.CONTRACT_ID.eq(val(contractId)))
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

			return result;

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the account for contract id: " + contractId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public List<ItAccount> findDataBySearchDate(LocalDateTime searchDate) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT)
					.where(val(searchDate).between(IT_ACCOUNT.START_DATE, IT_ACCOUNT.END_DATE))
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the accounts for the date " + Formatter.localDateTimeToString(searchDate) +" - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public ItAccount findDataBySearchDateAndAccountId(LocalDateTime searchDate, Integer accountId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT).where(IT_ACCOUNT.ACCOUNT_ID.eq(val(accountId)))
					.and(val(searchDate).between(IT_ACCOUNT.START_DATE, IT_ACCOUNT.END_DATE))
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the account for search date: " + searchDate.toString()
						+ " and account id : " + accountId + " - The query returns more rows(" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for  for search date: " + searchDate.toString()
					+ " and account id: " + accountId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public ItAccount findDataBySearchDateAndContractId(LocalDateTime searchDate, Integer contractId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT).where(IT_ACCOUNT.CONTRACT_ID.eq(val(contractId)))
					.and(val(searchDate).between(IT_ACCOUNT.START_DATE, IT_ACCOUNT.END_DATE))
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the account for search date: " + searchDate.toString()
						+ " and contract id : " + contractId + " - The query returns more rows(" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for  for search date: " + searchDate.toString()
					+ " and contract id: " + contractId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public ItAccount findDataBySearchDateAndCustomerId(LocalDateTime searchDate, Integer customerId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItAccount> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_ACCOUNT).where(IT_ACCOUNT.CUSTOMER_ID.eq(val(customerId)))
					.and(val(searchDate).between(IT_ACCOUNT.START_DATE, IT_ACCOUNT.END_DATE))
					.orderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE)
					.fetch().into(ItAccount.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the account for search date: " + searchDate.toString()
						+ " and customer id : " + customerId + " - The query returns more rows(" + result.size()
						+ ") than expected (1) ";
				logger.error(errorMessage);
				throw new CoMaSwDataAccessException(errorMessage);
			} else {
				return result.get(0);
			}

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the fee type for  for search date: " + searchDate.toString()
					+ " and customer id: " + customerId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public List<ItAccount> findInstanceWithParameters(Optional<LocalDateTime> searchDate, Optional<Integer> accountId,
			Optional<Integer> accountTypeId, Optional<Integer> contractId, Optional<Integer> customerId,
			Optional<Integer> accountStatusId, Optional<String> accountIdCard, Optional<String> customerIdCard)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
        SelectQuery<Record> query = create.selectQuery();
        List<ItAccount> result = null;
        String error = "";

        if ((!searchDate.isPresent()) && (!accountId.isPresent()) && (!accountTypeId.isPresent()) && (!contractId.isPresent())
        		&& (!customerId.isPresent()) && (accountStatusId == null)
        		&& (!accountIdCard.isPresent()) && (!customerIdCard.isPresent())) {
            logger.error("ERROR - none of the criteria search was defined");
            throw new CoMaSwDataAccessException("ERROR - none of the chriteria search was defined");
        } else {

            try {
                query.addFrom(IT_ACCOUNT);
                if (searchDate.isPresent()) {
                    query.addConditions(val(searchDate.get()).between(IT_ACCOUNT.START_DATE).and(IT_ACCOUNT.END_DATE));
                }
                if (accountId.isPresent()) {
                    query.addConditions(IT_ACCOUNT.ACCOUNT_ID.eq(val(accountId.get())));
                }
                if (accountTypeId.isPresent()) {
                    query.addConditions(IT_ACCOUNT.ACCOUNT_TYPE_ID.eq(val(accountTypeId.get())));
                }
                if (contractId.isPresent()) {
                    query.addConditions(IT_ACCOUNT.CONTRACT_ID.eq(val(contractId.get())));
                }
                if (customerId.isPresent()) {
                    query.addConditions(IT_ACCOUNT.CUSTOMER_ID.eq(val(customerId.get())));
                }
                if (accountStatusId.isPresent()) {
                    query.addConditions(IT_ACCOUNT.STATUS_ID.eq(val(accountStatusId.get())));
                }
                if (accountIdCard.isPresent()) {
                    if (accountIdCard.get().length() > 0) {
                        query.addConditions(upper(IT_ACCOUNT.IDENTITY_CARD).eq(upper(val(accountIdCard.get().trim()))));
                    }
                }                                
                if (customerIdCard.isPresent()) {
                    if (customerIdCard.get().length() > 0) {
                        query.addConditions(exists(create.selectOne().from(IT_CUSTOMER).where((IT_CUSTOMER.CUSTOMER_ID).eq(IT_ACCOUNT.CUSTOMER_ID)
                        		.and(upper(IT_CUSTOMER.IDENTITY_CARD).eq(upper(val(customerIdCard.get().trim())))))));
                    }
                }

                query.addOrderBy(IT_ACCOUNT.GIVEN_NAME, IT_ACCOUNT.FIRST_SURNAME, IT_ACCOUNT.SECOND_SURNAME, IT_ACCOUNT.CUSTOMER_ID, IT_ACCOUNT.START_DATE);

                result = query.fetchInto(ItAccount.class);
                
                logger.info("List of accounts returns sucessfully ");
                return result;

            } catch (DataAccessException e) {
                logger.error("ERROR - " + e.getMessage());
                throw new CoMaSwDataAccessException(error + e.getCause().toString(), e);
            } finally {
            	query.close();
            }
        }
	}

	@Override
	public Integer getNewId() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		String errorMessage;
		Integer id = 0;
		try {
			id = Math.toIntExact(create.nextval(SEQ_ACCOUNT_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new account " + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;
	}

	@Override
	public void insertData(ItAccount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();

			if (id > 0) {
				// insert the new id value into the idType datatable
				IdtAccount objectId = new IdtAccount(id);
				IdtAccountDao daoObjectId = new IdtAccountDao(configuration);
				daoObjectId.insert(objectId);

				// insert the dataObject into the type datatable
				dataObject.setCustomerId(id);
				ItAccountDao daoObject = new ItAccountDao(configuration);
				daoObject.insert(dataObject);
			}
		} catch (Exception e) {
			errorMessage = "Error inserting the account object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
		
	}

	@Override
	public void insertNewHistoricDataRecord(ItAccount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItAccountDao daoObject = new ItAccountDao(configuration);
			daoObject.insert(dataObject);

		} catch (Exception e) {
			errorMessage = "Error inserting the account object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		
	}

	@Override
	public void updateHistoricDataRecord(ItAccount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		ItAccountRecord record;
		try {
			record = create.newRecord(IT_ACCOUNT, dataObject);
			record.store();

		} catch (Exception e) {
			errorMessage = "Error inserting the account object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void updateData(ItAccount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItAccountDao daoObject = new ItAccountDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the account object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(ItAccount dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItAccountDao daoObject = new ItAccountDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the account object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		
	}
    
    

}
