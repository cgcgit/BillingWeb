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

package com.comasw.ejb.catalog.relationType;

import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.CtConsumptionType;
import com.comasw.model.tables.pojos.CtPromoConsumTypeDiscount;
import com.comasw.model.tables.pojos.VwPromoConsumTypeDiscount;
import com.comasw.exception.CoMaSwDataAccessException;

@Local
public interface PromotionConsumptionTypeDiscountEJBLocal {

	/**
	 * Find the consumption type candidates to the given promotion type id
	 * 
	 * @param parentTypeId parent type id of the relation to evaluated (promotion type
	 *                 id)
	 * @return list of the consumption type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException;


	/**
	 * Find the consumption type related for given promotion type id and status
	 * code
	 * 
	 * @param parentTypeId   parent type id of the relation to evaluated (promotion type
	 *                   id)
	 * @param statusCode status code of the entity's relation to evaluated (status
	 *                   code of the promotion type id)
	 * @return list of the consumption type related
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtConsumptionType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException;

	
	/**
	 * Find the consumption type candidates for given promotion type id and status
	 * code
	 * 
	 * @param parentTypeId   parent type id of the relation to evaluated (promotion type
	 *                   id)
	 * @param statusCode status code of the entity's relation to evaluated (status
	 *                   code of the promotion type id)
	 * @return list of the consumption type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException;

	/**
	 * Find the view of consumption types related with the given promotion type id
	 * 
	 * @param parentTypeId parent type id of the relation to evaluated (promotion type
	 *                 id)
	 * @return list of the consumption types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromoConsumTypeDiscount> findRelatedEntityTypesView(Integer parentTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the promotion-consumption type for the given product_service_type id
	 * 
	 * @param entityRelationTypeId product_service_type_id *
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoConsumTypeDiscount findEntityRelationType(Integer entityRelationTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the promotion-consumption type for the given promotion type id and
	 * consumption type id
	 * 
	 * @param parentTypeId parent type id of the relation (promotion type id)
	 * @param childTypeId  child type id of the relation (consumption type id)
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoConsumTypeDiscount findEntityRelationType(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the view of promotion-consumption type for the given promotion type id
	 * and consumption type id
	 * 
	 * @param parentTypeId parent type id of the relation (promotion type id)
	 * @param childTypeId  child type id of the relation (consumption type id)
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws CoMaSwDataAccessException
	 */
	public VwPromoConsumTypeDiscount findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given promotion-consumption type
	 * relation
	 * 
	 * @param dataObject promotion-consumption type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtPromoConsumTypeDiscount dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given promotion-consumption type
	 * relation
	 * 
	 * @param dataObject promotion-consumption type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtPromoConsumTypeDiscount dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given promotion-consumption type
	 * relation
	 * 
	 * @param dataObject promotion-consumption type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtPromoConsumTypeDiscount dataObject) throws CoMaSwDataAccessException;

}
