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

import static com.comasw.model.Sequences.SEQ_CUSTOMER_ID;
import static com.comasw.model.Tables.IT_CUSTOMER;
import static com.comasw.model.Tables.PT_STATUS;
import static com.comasw.model.Tables.VW_CUSTOMER_INSTANCE;

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
import com.comasw.model.tables.daos.ItCustomerDao;
import com.comasw.model.tables.daos.IdtCustomerDao;
import com.comasw.model.tables.pojos.ItCustomer;
import com.comasw.model.tables.pojos.VwCustomerInstance;
import com.comasw.model.tables.pojos.IdtCustomer;
import com.comasw.model.tables.records.ItCustomerRecord;

/**
 * Session Bean implementation class CustomerEJB
 */
@Stateless
@LocalBean
public class CustomerEJB implements CustomerEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(CustomerEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;
	
    /**
     * Default constructor. 
     */
    public CustomerEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<ItCustomer> findAllData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItCustomer> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_CUSTOMER)
					.orderBy(IT_CUSTOMER.GIVEN_NAME, IT_CUSTOMER.FIRST_SURNAME, IT_CUSTOMER.SECOND_SURNAME, IT_CUSTOMER.CUSTOMER_ID, IT_CUSTOMER.START_DATE)
					.fetch().into(ItCustomer.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the customers - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItCustomer> findDataBySearchDate(LocalDateTime searchDate) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItCustomer> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_CUSTOMER)
					.where(val(searchDate).between(IT_CUSTOMER.START_DATE, IT_CUSTOMER.END_DATE))
					.orderBy(IT_CUSTOMER.GIVEN_NAME, IT_CUSTOMER.FIRST_SURNAME, IT_CUSTOMER.SECOND_SURNAME, IT_CUSTOMER.CUSTOMER_ID, IT_CUSTOMER.START_DATE)
					.fetch().into(ItCustomer.class);

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the customers - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public List<ItCustomer> findDataByCustomerId(Integer customerId) throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItCustomer> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_CUSTOMER).where(IT_CUSTOMER.CUSTOMER_ID.eq(val(customerId)))
					.orderBy(IT_CUSTOMER.GIVEN_NAME, IT_CUSTOMER.FIRST_SURNAME, IT_CUSTOMER.SECOND_SURNAME, IT_CUSTOMER.CUSTOMER_ID, IT_CUSTOMER.START_DATE)
					.fetch().into(ItCustomer.class);

			return result;

		} catch (DataAccessException e) {
			errorMessage = "Error while try to find the customer for customer id: " + customerId + " - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	@Override
	public ItCustomer findDataBySearchDateAndCustomerId(LocalDateTime searchDate, Integer customerId)
			throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItCustomer> result = null;
		String errorMessage;

		try {
			result = create.selectFrom(IT_CUSTOMER).where(IT_CUSTOMER.CUSTOMER_ID.eq(val(customerId)))
					.and(val(searchDate).between(IT_CUSTOMER.START_DATE, IT_CUSTOMER.END_DATE))
					.orderBy(IT_CUSTOMER.GIVEN_NAME, IT_CUSTOMER.FIRST_SURNAME, IT_CUSTOMER.SECOND_SURNAME, IT_CUSTOMER.CUSTOMER_ID, IT_CUSTOMER.START_DATE)
					.fetch().into(ItCustomer.class);

			if (result.size() > 1) {
				errorMessage = "Error while try to find the customer for search date: " + searchDate.toString()
						+ " and customer id : " + customerId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the fee type for  for search date: " + searchDate.toString()
					+ " and customer id: " + customerId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
	}

	
	@Override	
	public ItCustomer findActiveDataBySearchDateAndCustomerId(LocalDateTime searchDate, Integer customerId)
			throws CoMaSwDataAccessException{
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItCustomer> result = null;
		String errorMessage;
		
		try {			
			result = create.selectFrom(IT_CUSTOMER)
					.where(IT_CUSTOMER.CUSTOMER_ID.eq(val(customerId))
							.and(val(searchDate).between(IT_CUSTOMER.START_DATE, IT_CUSTOMER.END_DATE)))
					.and(notExists(create.selectOne().from(PT_STATUS)
					.where(PT_STATUS.CODE.eq(val("CANC")).and(PT_STATUS.STATUS_ID.eq(IT_CUSTOMER.STATUS_ID)))))
					.orderBy(IT_CUSTOMER.GIVEN_NAME, IT_CUSTOMER.FIRST_SURNAME, IT_CUSTOMER.SECOND_SURNAME, IT_CUSTOMER.CUSTOMER_ID, IT_CUSTOMER.START_DATE)
					.fetch().into(ItCustomer.class);
			
			if (result.size() > 1) {
				errorMessage = "Error while try to find the customer for search date: " + searchDate.toString()
						+ " and customer id : " + customerId + " - The query returns a distinct number of rows (" + result.size()
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
			errorMessage = "Error while try to find the fee type for  for search date: " + searchDate.toString()
					+ " and customer id: " + customerId + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}
	
	@Override
	public List<ItCustomer> findInstanceWithParameters(Optional<LocalDateTime> searchDate, Optional<Integer> customerId, Optional<Integer> customerTypeId, Optional<Integer> statusId, 
			Optional<String> identityCard, Optional<String> contactPhone, Optional<String> givenName, Optional<String> firstSurname, Optional<String> secondSurname) throws CoMaSwDataAccessException {
		
		
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
        SelectQuery<Record> query = create.selectQuery();
        List<ItCustomer> result = null;
        String error = "";

        if ((!searchDate.isPresent()) && (!customerId.isPresent()) && (!customerTypeId.isPresent()) && (statusId == null)
        		&& (!identityCard.isPresent()) && (!contactPhone.isPresent())&& (!givenName.isPresent())
                && (!firstSurname.isPresent()) && (!secondSurname.isPresent())) {
            logger.error("ERROR - none of the criteria search was defined");
            throw new CoMaSwDataAccessException("ERROR - none of the chriteria search was defined");
        } else {

            try {
                query.addFrom(IT_CUSTOMER);
                if (searchDate.isPresent()) {
                    query.addConditions(val(searchDate.get()).between(IT_CUSTOMER.START_DATE).and(IT_CUSTOMER.END_DATE));
                }
                if (customerId.isPresent()) {
                    query.addConditions(IT_CUSTOMER.CUSTOMER_ID.eq(val(customerId.get())));
                }
                if (customerTypeId.isPresent()) {
                    query.addConditions(IT_CUSTOMER.CUSTOMER_TYPE_ID.eq(val(customerTypeId.get())));
                }
                if (statusId.isPresent()) {
                    query.addConditions(IT_CUSTOMER.STATUS_ID.eq(val(statusId.get())));
                }
                if (identityCard.isPresent()) {
                    if (identityCard.get().length() > 0) {
                        query.addConditions(upper(IT_CUSTOMER.IDENTITY_CARD).eq(upper(val(identityCard.get().trim()))));
                    }
                }
                if (givenName.isPresent()) {
                    if (givenName.get().length() > 0) {
                        query.addConditions(upper(IT_CUSTOMER.GIVEN_NAME).like(upper(val('%' + givenName.get().trim() + '%'))));
                    }
                }
                if (firstSurname.isPresent()) {
                    if (firstSurname.get().length() > 0) {
                        query.addConditions(upper(IT_CUSTOMER.FIRST_SURNAME).like(upper(val('%' + firstSurname.get().trim() + '%'))));
                    }
                }
                if (secondSurname.isPresent()) {
                    if (secondSurname.get().length() > 0) {
                        query.addConditions(upper(IT_CUSTOMER.SECOND_SURNAME).like(upper(val('%' + secondSurname.get().trim() + '%'))));
                    }
                }
                if (contactPhone.isPresent()) {
                    query.addConditions(IT_CUSTOMER.CONTACT_PHONE.eq(val(contactPhone.get().trim())));
                }

                query.addOrderBy(IT_CUSTOMER.GIVEN_NAME, IT_CUSTOMER.FIRST_SURNAME, IT_CUSTOMER.SECOND_SURNAME, IT_CUSTOMER.CUSTOMER_ID, IT_CUSTOMER.START_DATE);

                result = query.fetchInto(ItCustomer.class);
                
                logger.info("List of customers returns sucessfully ");
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
	public List<VwCustomerInstance> findInstanceViewWithParameters(Optional<LocalDateTime> searchDate, Optional<Integer> customerId, Optional<Integer> customerTypeId, Optional<Integer> statusId, 
			Optional<String> identityCard, Optional<String> contactPhone, Optional<String> givenName, Optional<String> firstSurname, Optional<String> secondSurname) throws CoMaSwDataAccessException{
		
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
        SelectQuery<Record> query = create.selectQuery();
        List<VwCustomerInstance> result = null;
        String error = "";

        if ((!searchDate.isPresent()) && (!customerId.isPresent()) && (!customerTypeId.isPresent()) && (statusId == null)
        		&& (!identityCard.isPresent()) && (!contactPhone.isPresent())&& (!givenName.isPresent())
                && (!firstSurname.isPresent()) && (!secondSurname.isPresent())) {
            logger.error("ERROR - none of the criteria search was defined");
            throw new CoMaSwDataAccessException("ERROR - none of the chriteria search was defined");
        } else {

            try {
                query.addFrom(VW_CUSTOMER_INSTANCE);
                if (searchDate.isPresent()) {
                    query.addConditions(val(searchDate.get()).between(VW_CUSTOMER_INSTANCE.CUSTOMER_START_DATE).and(VW_CUSTOMER_INSTANCE.CUSTOMER_END_DATE));
                }
                if (customerId.isPresent()) {
                    query.addConditions(VW_CUSTOMER_INSTANCE.CUSTOMER_ID.eq(val(customerId.get())));
                }
                if (customerTypeId.isPresent()) {
                    query.addConditions(VW_CUSTOMER_INSTANCE.CUSTOMER_TYPE_ID.eq(val(customerTypeId.get())));
                }
                if (statusId.isPresent()) {
                    query.addConditions(VW_CUSTOMER_INSTANCE.CUSTOMER_STATUS_ID.eq(val(statusId.get())));
                }
                if (identityCard.isPresent()) {
                    if (identityCard.get().length() > 0) {
                        query.addConditions(upper(VW_CUSTOMER_INSTANCE.CUSTOMER_IDENTITY_CARD).eq(upper(val(identityCard.get().trim()))));
                    }
                }
                if (givenName.isPresent()) {
                    if (givenName.get().length() > 0) {
                        query.addConditions(upper(VW_CUSTOMER_INSTANCE.CUSTOMER_GIVEN_NAME).like(upper(val('%' + givenName.get().trim() + '%'))));
                    }
                }
                if (firstSurname.isPresent()) {
                    if (firstSurname.get().length() > 0) {
                        query.addConditions(upper(VW_CUSTOMER_INSTANCE.CUSTOMER_FIRST_SURNAME).like(upper(val('%' + firstSurname.get().trim() + '%'))));
                    }
                }
                if (secondSurname.isPresent()) {
                    if (secondSurname.get().length() > 0) {
                        query.addConditions(upper(VW_CUSTOMER_INSTANCE.CUSTOMER_SECOND_SURNAME).like(upper(val('%' + secondSurname.get().trim() + '%'))));
                    }
                }
                if (contactPhone.isPresent()) {
                    query.addConditions(VW_CUSTOMER_INSTANCE.CUSTOMER_CONTACT_PHONE.eq(val(contactPhone.get().trim())));
                }

                query.addOrderBy(VW_CUSTOMER_INSTANCE.CUSTOMER_GIVEN_NAME, VW_CUSTOMER_INSTANCE.CUSTOMER_FIRST_SURNAME,
                		VW_CUSTOMER_INSTANCE.CUSTOMER_SECOND_SURNAME, VW_CUSTOMER_INSTANCE.CUSTOMER_ID, VW_CUSTOMER_INSTANCE.CUSTOMER_START_DATE);

                result = query.fetchInto(VwCustomerInstance.class);
                
                logger.info("List of customers returns sucessfully ");
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
			id = Math.toIntExact(create.nextval(SEQ_CUSTOMER_ID));
		} catch (Exception e) {
			errorMessage = "Error obtaining the id for the new customer " + " - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return id;
	}

	@Override
	public Integer insertData(ItCustomer dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		Integer id = 0;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);

			// Gets the id for the new data
			id = getNewId();

			if (id > 0) {
				// insert the new id value into the idType datatable
				IdtCustomer objectId = new IdtCustomer(id);
				IdtCustomerDao daoObjectId = new IdtCustomerDao(configuration);
				daoObjectId.insert(objectId);

				// insert the dataObject into the type datatable
				dataObject.setCustomerId(id);
				ItCustomerDao daoObject = new ItCustomerDao(configuration);
				daoObject.insert(dataObject);
			}
			return id;
			
		} catch (Exception e) {
			errorMessage = "Error inserting the customer object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void insertNewHistoricDataRecord(ItCustomer dataObject) throws CoMaSwDataAccessException {
		String errorMessage;

		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItCustomerDao daoObject = new ItCustomerDao(configuration);
			daoObject.insert(dataObject);

		} catch (Exception e) {
			errorMessage = "Error inserting the customer object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		
	}

	@Override
	public void updateHistoricDataRecord(ItCustomer dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		ItCustomerRecord record;
		try {
			record = create.newRecord(IT_CUSTOMER, dataObject);
			record.store();

		} catch (Exception e) {
			errorMessage = "Error inserting the customer object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		
	}

	@Override
	public void updateData(ItCustomer dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItCustomerDao daoObject = new ItCustomerDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the customer object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}
		
	}

	@Override
	public void deleteData(ItCustomer dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItCustomerDao daoObject = new ItCustomerDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the customer object (value: " + dataObject.toString() + ") - "
					+ e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		
	}

}
