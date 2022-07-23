/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.daos;


import com.comasw.model.tables.IdtPromotionType;
import com.comasw.model.tables.records.IdtPromotionTypeRecord;

import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IdtPromotionTypeDao extends DAOImpl<IdtPromotionTypeRecord, com.comasw.model.tables.pojos.IdtPromotionType, Integer> {

    /**
     * Create a new IdtPromotionTypeDao without any configuration
     */
    public IdtPromotionTypeDao() {
        super(IdtPromotionType.IDT_PROMOTION_TYPE, com.comasw.model.tables.pojos.IdtPromotionType.class);
    }

    /**
     * Create a new IdtPromotionTypeDao with an attached configuration
     */
    public IdtPromotionTypeDao(Configuration configuration) {
        super(IdtPromotionType.IDT_PROMOTION_TYPE, com.comasw.model.tables.pojos.IdtPromotionType.class, configuration);
    }

    @Override
    public Integer getId(com.comasw.model.tables.pojos.IdtPromotionType object) {
        return object.getPromotionTypeId();
    }

    /**
     * Fetch records that have <code>promotion_type_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.comasw.model.tables.pojos.IdtPromotionType> fetchRangeOfPromotionTypeId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(IdtPromotionType.IDT_PROMOTION_TYPE.PROMOTION_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>promotion_type_id IN (values)</code>
     */
    public List<com.comasw.model.tables.pojos.IdtPromotionType> fetchByPromotionTypeId(Integer... values) {
        return fetch(IdtPromotionType.IDT_PROMOTION_TYPE.PROMOTION_TYPE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>promotion_type_id = value</code>
     */
    public com.comasw.model.tables.pojos.IdtPromotionType fetchOneByPromotionTypeId(Integer value) {
        return fetchOne(IdtPromotionType.IDT_PROMOTION_TYPE.PROMOTION_TYPE_ID, value);
    }
}
