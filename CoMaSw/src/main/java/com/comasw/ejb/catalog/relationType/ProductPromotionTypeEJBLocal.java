/*
    CoMaSw - Contract Management Software is a software developed for 
    the final academic project of the Universidade da Coruña (UDC).

    Copyright (C) 2022  Catarina García Cal (catarina.garcia.cal@udc.es)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
 any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

*/

package com.comasw.ejb.catalog.relationType;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Local;

import com.comasw.model.tables.pojos.VwPromotionProductType;
import com.comasw.exception.CoMaSwDataAccessException;
import com.comasw.model.tables.pojos.CtPromoProdType;
import com.comasw.model.tables.pojos.CtPromotionType;

@Local
public interface ProductPromotionTypeEJBLocal {

	/**
	 * Find the promotion type candidates for given product type id. We select the
	 * candidate with the minimum start date.
	 * 
	 * @param parentTypeId parent type id of the relation to evaluated (product type id)
	 * @return list of the promotion type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findEntityTypeCandidates(Integer parentTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the promotion type related for given product type id and status code.
	 * We select the candidate with the minimum start date.
	 * 
	 * @param parentTypeId   parent type id of the relation to evaluated (product type
	 *                   id)
	 * @param statusCode status code of the entity's relation to evaluated (status
	 *                   code of the product type id)
	 * @return list of the promotion type related
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findEntityTypeRelated(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException;

	
	/**
	 * Find the promotion type candidates for given product type id and status code.
	 * We select the candidate with the minimum start date.
	 * 
	 * @param parentTypeId   parent type id of the relation to evaluated (product type
	 *                   id)
	 * @param statusCode status code of the entity's relation to evaluated (status
	 *                   code of the product type id)
	 * @return list of the promotion type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtPromotionType> findEntityTypeCandidates(Integer parentTypeId, String statusCode)
			throws CoMaSwDataAccessException;


	
	
	/**
	 * Find the view of all (historic data) promotion types related with the given
	 * product type id
	 * 
	 * @param parentTypeId parent type id of the relation to evaluated (product type id)
	 * @return list of the promotion types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromotionProductType> findHistoricRelatedEntityTypesView(Integer parentTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the view of promotion types related with the given product type id for
	 * the given date
	 * 
	 * @param parentTypeId parent type id of the relation to evaluated (product type id)
	 * @return list of the promotion types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromotionProductType> findRelatedEntityTypesByDateView(Integer parentTypeId, LocalDateTime searchDate)
			throws CoMaSwDataAccessException;

	/**
	 * Find the product-promotion type for the given product_promotion_type id
	 * 
	 * @param entityRelationTypeId product_promotion_type_id
	 * @return product_promotion_type for the product-promotion type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoProdType findEntityRelationType(Integer entityRelationTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the product-promotion type for the given product type id and promotion
	 * type id
	 * 
	 * @param parentTypeId parent type id of the relation (product type id)
	 * @param childTypeId  child type id of the relation (promotion type id)
	 * @return product_promotion_type for the product-promotion type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoProdType findEntityRelationType(Integer parentTypeId, Integer childTypeId) throws CoMaSwDataAccessException;

	/**
	 * Find the view of product-promotion type for the given product type id and
	 * promotion type id
	 * 
	 * @param parentTypeId parent type id of the relation (product type id)
	 * @param childTypeId  child type id of the relation (promotion type id)
	 * @return product_promotion_type for the product-promotion type relation
	 * @throws CoMaSwDataAccessException
	 */
	public VwPromotionProductType findEntityRelationTypeView(Integer parentTypeId, Integer childTypeId)
			throws CoMaSwDataAccessException;

	/**
	 * Inserts into the system (database) the given product-promotion type relation
	 * 
	 * @param dataObject product-promotion type object to insert
	 * @throws CoMaSwDataAccessException
	 */
	public void insertData(CtPromoProdType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Updates into the system (database) the given product-promotion type relation
	 * 
	 * @param dataObject product-promotion type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void updateData(CtPromoProdType dataObject) throws CoMaSwDataAccessException;

	/**
	 * Deletes into the system (database) the given product-promotion type relation
	 * 
	 * @param dataObject product-promotion type object to update
	 * @throws CoMaSwDataAccessException
	 */
	public void deleteData(CtPromoProdType dataObject) throws CoMaSwDataAccessException;

}
