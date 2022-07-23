package com.comasw.ejb.user;

import static com.comasw.model.Tables.IT_PROFILES;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
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

import com.comasw.model.tables.daos.ItProfilesDao;
import com.comasw.model.tables.pojos.ItProfiles;
import com.comasw.exception.CoMaSwDataAccessException;


/**
 * Session Bean implementation class ProfileEJB
 */
@Stateless
@LocalBean
public class ProfileEJB implements ProfileEJBLocal {

	Logger logger = (Logger) LogManager.getLogger(ProfileEJB.class);
	
	@Resource(lookup = "java:jboss/datasources/db_comasw")
	private DataSource ds;
	
    /**
     * Default constructor. 
     */
    public ProfileEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<ItProfiles> findData() throws CoMaSwDataAccessException {
		DSLContext create = DSL.using(ds, SQLDialect.POSTGRES);
		List<ItProfiles> result;
		String errorMessage;
		
		try {
			// Gets the user from the database
			result = create.selectFrom(IT_PROFILES).orderBy(IT_PROFILES.PROFILE_CODE).fetchInto(ItProfiles.class);
		} catch (DataAccessException e) {
			errorMessage = "Error while try to find all the system profiles - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		} catch (Exception e) {
			errorMessage = "Error while try to find all the system profiles - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
		}

		return result;
	}

	@Override
	public void insertData(ItProfiles dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItProfilesDao daoObject = new ItProfilesDao(configuration);
			daoObject.insert(dataObject);
		} catch (Exception e) {			
			errorMessage = "Error inserting the profile object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);	
		}

		
	}

	@Override
	public void updateData(ItProfiles dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItProfilesDao daoObject = new ItProfilesDao(configuration);
			daoObject.update(dataObject);
		} catch (Exception e) {
			errorMessage = "Error updating the profile object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);
			
		}
		
	}

	@Override
	public void deleteData(ItProfiles dataObject) throws CoMaSwDataAccessException {
		String errorMessage;
		
		try {
			Configuration configuration = new DefaultConfiguration().set(ds.getConnection()).set(SQLDialect.POSTGRES);
			ItProfilesDao daoObject = new ItProfilesDao(configuration);
			daoObject.delete(dataObject);
		} catch (Exception e) {
			errorMessage = "Error deleting the profile object (value: "
					+ dataObject.toString() + ") - " + e.getMessage();
			logger.error(errorMessage);
			throw new CoMaSwDataAccessException(errorMessage, e);	
		}
		
	}

}
