package com.comasw.ejb.catalog.type;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtFeeType;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface FeeTypeEJBLocal {

	/**
	 * Find all the fee type data stores in the system
	 * 
	 * @return list of all fee type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the fee type data stores in the system for the given searchDate
	 * 
	 * @param searchDate
	 * @return type data in the system for the given searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findDataBySearchDate(LocalDateTime searchDate) throws CoMaSwDataAccessException;

	/**
	 * Find the fee type data stores in the system for the given feeTypeId
	 * 
	 * @param feeTypeId
	 * @return type data in the system for the given feeTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findDataByFeeTypeId(Integer feeTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the fee type data stores in the system for the given feeTypeId and
	 * searchDate
	 * 
	 * @param searchDate
	 * @param feeTypeId
	 * @return type data in the system for the given feeTypeId and searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public CtFeeType findDataBySearchDateAndFeeTypeId(LocalDateTime searchDate, Integer feeTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the fee type data stores in the system for the given code of fee type
	 * 
	 * @param code
	 * @return fee type data in the system for the given code of fee type
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Find the fee type data stores in the system for the given code of fee type
	 * and searchDate
	 * 
	 * @param searchDate
	 * @param code
	 * @return fee type data in the system for the given code of fee type and
	 *         searchDate
	 * @throws CoMaSwDataAccessException
	 */
	public CtFeeType findDataBySearchDateAndCode(LocalDateTime searchDate, String code)
			throws CoMaSwDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelId
	 * 
	 * @param applicationLevelId
	 * @return list of all promotion type data in the system for the given
	 *         applicationLevelId
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findDataByApplicationLevelId(Integer applicationLevelId) throws CoMaSwDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given searchDate
	 * and applicationLevelId
	 * 
	 * @param searchDate
	 * @param applicationLevelId
	 * @return list of all promotion type data in the system for the given
	 *         applicationLevelId
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findDataBySearchDateAndApplicationLevelId(LocalDateTime searchDate,
			Integer applicationLevelId) throws CoMaSwDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given
	 * applicationLevelCode
	 * 
	 * @param applicationCode
	 * @return list of all promotion type data in the system for the given
	 *         applicationLevelCode
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findDataByApplicationLevelCode(String applicationLevelCode) throws CoMaSwDataAccessException;

	/**
	 * Find the promotion type data stores in the system for the given searchDate
	 * and applicationLevelCode
	 * 
	 * @param searchDate
	 * @param applicationCode
	 * @return list of all promotion type data in the system for the given
	 *         applicationLevelCode
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtFeeType> findDataBySearchDateAndApplicationLevelCode(LocalDateTime searchDate,
			String applicationLevelCode) throws CoMaSwDataAccessException;
	
	
	/**
	 * Gets a new Id from the sequence associates to the entity
	 * 
	 * @return Id
	 * @throws CoMaSwDataAccessException
	 */

	public Integer getNewId() throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given new fee type. It creates a new
	 * Id and inserts into the id table.
	 * 
	 * @param dataObject fee type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtFeeType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new fee type record.
	 * 
	 * @param dataObject fee type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertNewHistoricDataRecord(CtFeeType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) a new historic data record for existing
	 * fee type
	 * 
	 * @param dataObject fee type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void updateHistoricDataRecord(CtFeeType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given fee type
	 * 
	 * @param dataObject fee type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtFeeType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given fee type
	 * 
	 * @param dataObject fee type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtFeeType dataObject) throws CoMaSwDataAccessException;

}
