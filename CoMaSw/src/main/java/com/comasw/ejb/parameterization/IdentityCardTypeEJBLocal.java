package com.comasw.ejb.parameterization;

import java.util.List;

import javax.ejb.Local;

import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.PtIdentityCardType;

@Local
public interface IdentityCardTypeEJBLocal {

	/**
	 * Find all the identity card type data stores in the system
	 * 
	 * @return list of all identity card type data in the system
	 * @throws CoMaSwDataAccessException
	 */
	public List<PtIdentityCardType> findAllData() throws CoMaSwDataAccessException;

	/**
	 * Find the identity card type data stores in the system for the given
	 * identityCardTypeId
	 * 
	 * @param identityCardTypeId
	 * @return identity card type data in the system for the given identityCardTypeId
	 * @throws CoMaSwDataAccessException
	 */
	public PtIdentityCardType findDataByidCardTypeId(Integer identityCardTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the identity card type data stores in the system for the given code of
	 * identity card type
	 * 
	 * @param code
	 * @return identity card type data in the system for the given code of
	 *         identity card type
	 * @throws CoMaSwDataAccessException
	 */
	public PtIdentityCardType findDataByCode(String code) throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given identity card type
	 * 
	 * @param dataObject identity card type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given identity card type
	 * 
	 * @param dataObject identity card type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given identity card type
	 * 
	 * @param dataObject identity card type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(PtIdentityCardType dataObject) throws CoMaSwDataAccessException;



}
