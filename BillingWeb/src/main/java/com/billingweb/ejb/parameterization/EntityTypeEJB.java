package com.billingweb.ejb.parameterization;


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

import com.billingweb.exception.BillingWebDataAccessException;
import com.billingweb.model.tables.daos.PtEntityTypeDao;
import com.billingweb.model.tables.pojos.PtEntityType;

import static com.billingweb.model.Tables.PT_ENTITY_TYPE;
import static org.jooq.impl.DSL.val;;

/**
 * Session Bean implementation class EntityTypeEJB
 */
@Stateless
public class EntityTypeEJB implements EntityTypeEJBLocal {


	Logger logger = (Logger) LogManager.getLogger(EntityTypeEJB.class);

	@Resource(lookup = "java:jboss/datasources/db_billing")
	private DataSource ds;

	
	
    /**
     * Default constructor. 
     */
    public EntityTypeEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<PtEntityType> findAllData() throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtEntityType> result = null;

		try {
			result = create.selectFrom(PT_ENTITY_TYPE)
					.orderBy(PT_ENTITY_TYPE.CODE)
					.fetch()
					.into(PtEntityType.class);		

		} catch (DataAccessException e) {
			throw new BillingWebDataAccessException(
					"Error while try to find all the entity types - " + e.getCause().toString(), e);
		}

		return result;
	}

	@Override
	public List<PtEntityType> findDataByEntityTypeId(Integer entityTypeId) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtEntityType> result = null;

		try {
			result = create.selectFrom(PT_ENTITY_TYPE)
					.where(PT_ENTITY_TYPE.ENTITY_TYPE_ID.eq(val(entityTypeId)))
					.orderBy(PT_ENTITY_TYPE.CODE)
					.fetch()
					.into(PtEntityType.class);

		} catch (DataAccessException e) {
			throw new BillingWebDataAccessException(
					"Error while try to find the entity type for entity_type_id: " + entityTypeId + " - " + e.getCause().toString(), e);
		}

		return result;
	}

	@Override
	public List<PtEntityType> findDataByCode(String code) throws BillingWebDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<PtEntityType> result = null;

		try {
			result = create.selectFrom(PT_ENTITY_TYPE)
					.where(PT_ENTITY_TYPE.CODE.eq(val(code)))
					.orderBy(PT_ENTITY_TYPE.CODE)
					.fetch()
					.into(PtEntityType.class);

		} catch (DataAccessException e) {
			throw new BillingWebDataAccessException(
					"Error while try to find the entity type for code " + code + " - " + e.getCause().toString(), e);
		}

		return result;
	}

	@Override
	public void insertData(PtEntityType dataObject) throws BillingWebDataAccessException {
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);			
			PtEntityTypeDao daoObject = new PtEntityTypeDao(configuration);			
			daoObject.insert(dataObject);
		} catch (Exception e) {
			logger.error("ERROR - " + e.getMessage());
			throw new BillingWebDataAccessException("Error inserting the entity type object (value: "
					+ dataObject.toString() + ")" + e.getCause().toString(), e);
		}
		
	}

	@Override
	public void updateData(PtEntityType dataObject) throws BillingWebDataAccessException {
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			PtEntityTypeDao daoObject = new PtEntityTypeDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			logger.error("ERROR - " + e.getMessage());
			throw new BillingWebDataAccessException("Error updating the entity type object (value: "
					+ dataObject.toString() + ")" + e.getCause().toString(), e);
		}
		
	}

	@Override
	public void deleteData(PtEntityType dataObject) throws BillingWebDataAccessException {
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);		
			PtEntityTypeDao daoObject = new PtEntityTypeDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			logger.error("ERROR - " + e.getMessage());
			throw new BillingWebDataAccessException("Error deleting the entity type object (value: "
					+ dataObject.toString() + ")" + e.getCause().toString(), e);
		}
		
	}

}
