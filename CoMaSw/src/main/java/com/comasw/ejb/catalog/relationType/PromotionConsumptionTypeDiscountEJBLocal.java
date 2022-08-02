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
	 * @param parentId parent type id of the relation to evaluated (promotion type
	 *                 id)
	 * @return list of the consumption type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentId) throws CoMaSwDataAccessException;

	/**
	 * Find the consumption type candidates for given promotion type id and status
	 * code
	 * 
	 * @param parentId   parent type id of the relation to evaluated (promotion type
	 *                   id)
	 * @param statusCode status code of the entity's relation to evaluated (status
	 *                   code of the promotion type id)
	 * @return list of the consumption type candidates
	 * @throws CoMaSwDataAccessException
	 */
	public List<CtConsumptionType> findEntityTypeCandidates(Integer parentId, String statusCode)
			throws CoMaSwDataAccessException;

	/**
	 * Find the view of consumption types related with the given promotion type id
	 * 
	 * @param parentId parent type id of the relation to evaluated (promotion type
	 *                 id)
	 * @return list of the consumption types related with the given parent type id
	 * @throws CoMaSwDataAccessException
	 */
	public List<VwPromoConsumTypeDiscount> findRelatedEntityTypesView(Integer parentId)
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
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId  child type id of the relation (consumption type id)
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws CoMaSwDataAccessException
	 */
	public CtPromoConsumTypeDiscount findEntityRelationType(Integer parentId, Integer childId)
			throws CoMaSwDataAccessException;

	/**
	 * Find the view of promotion-consumption type for the given promotion type id
	 * and consumption type id
	 * 
	 * @param parentId parent type id of the relation (promotion type id)
	 * @param childId  child type id of the relation (consumption type id)
	 * @return product_service_type for the promotion-consumption type relation
	 * @throws CoMaSwDataAccessException
	 */
	public VwPromoConsumTypeDiscount findEntityRelationTypeView(Integer parentId, Integer childId)
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
