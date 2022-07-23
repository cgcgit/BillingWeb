package com.comasw.ejb.catalog.type;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtPromotionType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface PromotionTypeEJBLocal {
	


	/**
	 * Find all the promotion type data stores in the system
	 * @return list of all promotion type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtPromotionType> findAllData () throws CoMaSwDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given searchDate
	 * @param searchDate
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List <CtPromotionType> findDataBySearchDate (LocalDateTime searchDate) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the promotion type data stores in the system for the given promotionTypeId
	 * @param promotionTypeId
	 * @return type data in the system for the given promotionTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findDataByPromotionTypeId (Integer promotionTypeId) throws CoMaSwDataAccessException;
	
	/**
	 * Find the promotion type data stores in the system for the given promotionTypeId and searchDate
	 * @param searchDate
	 * @param promotionTypeId
	 * @return type data in the system for the given promotionTypeId and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromotionType findDataBySearchDateAndPromotionTypeId (LocalDateTime searchDate, Integer promotionTypeId) throws CoMaSwDataAccessException;
			
	
	/**
	 * Find the promotion type data stores in the system for the given code of promotion type
	 * @param code
	 * @return promotion type data in the system for the given code of promotion type
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findDataByCode (String code) throws CoMaSwDataAccessException;
	
	
	/**
	 * Find the promotion type data stores in the system for the given code of promotion type and searchDate
	 * @param searchDate
	 * @param code
	 * @return promotion type data in the system for the given code of promotion type and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromotionType findDataBySearchDateAndCode (LocalDateTime searchDate,String code) throws CoMaSwDataAccessException;
	
	
	
	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelId 
	 * @param applicationLevelId
	 * @return list of all promotion type data in the system for the given applicationLevelId
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findDataByApplicationLevelId(Integer applicationLevelId) throws CoMaSwDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given searchDate and
	 * applicationLevelId
	 * @param searchDate
	 * @param applicationLevelId
	 * @return list of all promotion type data in the system for the given applicationLevelId
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findDataBySearchDateAndApplicationLevelId(LocalDateTime searchDate,Integer applicationLevelId) throws CoMaSwDataAccessException;

	
	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelCode
	 * @param applicationCode
	 * @return list of all promotion type data in the system for the given applicationLevelCode
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findDataByApplicationLevelCode(String applicationLevelCode) throws CoMaSwDataAccessException;


	/**
	 * Find the promotion type data stores in the system for the given searchDate and
	 * applicationLevelCode
	 * @param searchDate
	 * @param applicationCode
	 * @return list of all promotion type data in the system for the given applicationLevelCode
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findDataBySearchDateAndApplicationLevelCode(LocalDateTime searchDate,String applicationLevelCode) throws CoMaSwDataAccessException;

	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId () throws CoMaSwDataAccessException;
	
	
	/**
	 * Inserts into the system (database) the given new promotion type. It creates a new Id and inserts into the id table.
	 * @param dataObject promotion type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData (CtPromotionType dataObject) throws CoMaSwDataAccessException;
	
	/**
	 * Inserts into the system (database) a new promotion type record.
	 * @param dataObject promotion type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord (CtPromotionType dataObject) throws CoMaSwDataAccessException;
	
	
	
	/**
	 * Inserts into the system (database) a new historic data record for existing promotion type 
	 * @param dataObject promotion type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord (CtPromotionType dataObject) throws CoMaSwDataAccessException;
	
	
	/**
	 * Updates into the system (database) the given promotion type 
	 * @param dataObject promotion type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData (CtPromotionType dataObject) throws CoMaSwDataAccessException;
	
	
	/**
	 * Deletes into the system (database) the given promotion type 
	 * @param dataObject promotion type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData (CtPromotionType dataObject) throws CoMaSwDataAccessException;

}
