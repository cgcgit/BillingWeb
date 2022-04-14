/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.daos;


import com.billingweb.model.tables.CtCustomerType;
import com.billingweb.model.tables.records.CtCustomerTypeRecord;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * Table that stores the customer types of the catalog for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtCustomerTypeDao extends DAOImpl<CtCustomerTypeRecord, com.billingweb.model.tables.pojos.CtCustomerType, Integer> {

    /**
     * Create a new CtCustomerTypeDao without any configuration
     */
    public CtCustomerTypeDao() {
        super(CtCustomerType.CT_CUSTOMER_TYPE, com.billingweb.model.tables.pojos.CtCustomerType.class);
    }

    /**
     * Create a new CtCustomerTypeDao with an attached configuration
     */
    public CtCustomerTypeDao(Configuration configuration) {
        super(CtCustomerType.CT_CUSTOMER_TYPE, com.billingweb.model.tables.pojos.CtCustomerType.class, configuration);
    }

    @Override
    public Integer getId(com.billingweb.model.tables.pojos.CtCustomerType object) {
        return object.getCustomerTypeId();
    }

    /**
     * Fetch records that have <code>customer_type_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfCustomerTypeId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.CUSTOMER_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>customer_type_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByCustomerTypeId(Integer... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.CUSTOMER_TYPE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>customer_type_id = value</code>
     */
    public com.billingweb.model.tables.pojos.CtCustomerType fetchOneByCustomerTypeId(Integer value) {
        return fetchOne(CtCustomerType.CT_CUSTOMER_TYPE.CUSTOMER_TYPE_ID, value);
    }

    /**
     * Fetch records that have <code>entity_type_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfEntityTypeId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.ENTITY_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>entity_type_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByEntityTypeId(Integer... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.ENTITY_TYPE_ID, values);
    }

    /**
     * Fetch records that have <code>code BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfCode(String lowerInclusive, String upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.CODE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>code IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByCode(String... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.CODE, values);
    }

    /**
     * Fetch a unique record that has <code>code = value</code>
     */
    public com.billingweb.model.tables.pojos.CtCustomerType fetchOneByCode(String value) {
        return fetchOne(CtCustomerType.CT_CUSTOMER_TYPE.CODE, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByName(String... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public com.billingweb.model.tables.pojos.CtCustomerType fetchOneByName(String value) {
        return fetchOne(CtCustomerType.CT_CUSTOMER_TYPE.NAME, value);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByDescription(String... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.DESCRIPTION, values);
    }

    /**
     * Fetch records that have <code>status_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfStatusId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.STATUS_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByStatusId(Integer... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.STATUS_ID, values);
    }

    /**
     * Fetch records that have <code>input_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfInputDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.INPUT_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>input_date IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByInputDate(LocalDateTime... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.INPUT_DATE, values);
    }

    /**
     * Fetch records that have <code>input_user BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfInputUser(String lowerInclusive, String upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.INPUT_USER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>input_user IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByInputUser(String... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.INPUT_USER, values);
    }

    /**
     * Fetch records that have <code>modif_date BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfModifDate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.MODIF_DATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modif_date IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByModifDate(LocalDateTime... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.MODIF_DATE, values);
    }

    /**
     * Fetch records that have <code>modif_user BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchRangeOfModifUser(String lowerInclusive, String upperInclusive) {
        return fetchRange(CtCustomerType.CT_CUSTOMER_TYPE.MODIF_USER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modif_user IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.CtCustomerType> fetchByModifUser(String... values) {
        return fetch(CtCustomerType.CT_CUSTOMER_TYPE.MODIF_USER, values);
    }
}
