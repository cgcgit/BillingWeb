/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.daos;


import com.billingweb.model.tables.CtPromoProdType;
import com.billingweb.model.tables.records.CtPromoProdTypeRecord;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * Table that stores the promotion product relation types of the catalog for 
 * the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtPromoProdTypeDao extends DAOImpl<CtPromoProdTypeRecord, com.billingweb.model.tables.pojos.CtPromoProdType, Integer> {

    /**
     * Create a new CtPromoProdTypeDao without any configuration
     */
    public CtPromoProdTypeDao() {
        super(CtPromoProdType.CT_PROMO_PROD_TYPE, com.billingweb.model.tables.pojos.CtPromoProdType.class);
    }

    /**
     * Create a new CtPromoProdTypeDao with an attached configuration
     */
    public CtPromoProdTypeDao(Configuration configuration) {
        super(CtPromoProdType.CT_PROMO_PROD_TYPE, com.billingweb.model.tables.pojos.CtPromoProdType.class, configuration);
    }

    @Override
    public Integer getId(com.billingweb.model.tables.pojos.CtPromoProdType object) {
        return object.getPromoProdTypeId();
    }

    /**
     * Fetch records that have <code>promo_prod_type_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfPromoProdTypeId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.PROMO_PROD_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>promo_prod_type_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByPromoProdTypeId(Integer... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.PROMO_PROD_TYPE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>promo_prod_type_id = value</code>
     */
    public com.billingweb.model.tables.pojos.CtPromoProdType fetchOneByPromoProdTypeId(Integer value) {
        return fetchOne(CtPromoProdType.CT_PROMO_PROD_TYPE.PROMO_PROD_TYPE_ID, value);
    }

    /**
     * Fetch records that have <code>promotion_type_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfPromotionTypeId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.PROMOTION_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>promotion_type_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByPromotionTypeId(Integer... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.PROMOTION_TYPE_ID, values);
    }

    /**
     * Fetch records that have <code>product_type_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfProductTypeId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.PRODUCT_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>product_type_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByProductTypeId(Integer... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.PRODUCT_TYPE_ID, values);
    }

    /**
     * Fetch records that have <code>status_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfStatusId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.STATUS_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByStatusId(Integer... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.STATUS_ID, values);
    }

    /**
     * Fetch records that have <code>input_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfInputDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.INPUT_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>input_date IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByInputDate(LocalDateTime... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.INPUT_DATE, values);
    }

    /**
     * Fetch records that have <code>input_user BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfInputUser(String lowerInclusive, String upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.INPUT_USER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>input_user IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByInputUser(String... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.INPUT_USER, values);
    }

    /**
     * Fetch records that have <code>modif_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfModifDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.MODIF_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modif_date IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByModifDate(LocalDateTime... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.MODIF_DATE, values);
    }

    /**
     * Fetch records that have <code>modif_user BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchRangeOfModifUser(String lowerInclusive, String upperInclusive) {
        return fetchRange(CtPromoProdType.CT_PROMO_PROD_TYPE.MODIF_USER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modif_user IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtPromoProdType> fetchByModifUser(String... values) {
        return fetch(CtPromoProdType.CT_PROMO_PROD_TYPE.MODIF_USER, values);
    }
}
