/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.daos;


import com.comasw.model.tables.PtEntityType;
import com.comasw.model.tables.records.PtEntityTypeRecord;

import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * Table that stores the entity type of the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtEntityTypeDao extends DAOImpl<PtEntityTypeRecord, com.comasw.model.tables.pojos.PtEntityType, Integer> {

    /**
     * Create a new PtEntityTypeDao without any configuration
     */
    public PtEntityTypeDao() {
        super(PtEntityType.PT_ENTITY_TYPE, com.comasw.model.tables.pojos.PtEntityType.class);
    }

    /**
     * Create a new PtEntityTypeDao with an attached configuration
     */
    public PtEntityTypeDao(Configuration configuration) {
        super(PtEntityType.PT_ENTITY_TYPE, com.comasw.model.tables.pojos.PtEntityType.class, configuration);
    }

    @Override
    public Integer getId(com.comasw.model.tables.pojos.PtEntityType object) {
        return object.getEntityTypeId();
    }

    /**
     * Fetch records that have <code>entity_type_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchRangeOfEntityTypeId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(PtEntityType.PT_ENTITY_TYPE.ENTITY_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>entity_type_id IN (values)</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchByEntityTypeId(Integer... values) {
        return fetch(PtEntityType.PT_ENTITY_TYPE.ENTITY_TYPE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>entity_type_id = value</code>
     */
    public com.comasw.model.tables.pojos.PtEntityType fetchOneByEntityTypeId(Integer value) {
        return fetchOne(PtEntityType.PT_ENTITY_TYPE.ENTITY_TYPE_ID, value);
    }

    /**
     * Fetch records that have <code>code BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchRangeOfCode(String lowerInclusive, String upperInclusive) {
        return fetchRange(PtEntityType.PT_ENTITY_TYPE.CODE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>code IN (values)</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchByCode(String... values) {
        return fetch(PtEntityType.PT_ENTITY_TYPE.CODE, values);
    }

    /**
     * Fetch a unique record that has <code>code = value</code>
     */
    public com.comasw.model.tables.pojos.PtEntityType fetchOneByCode(String value) {
        return fetchOne(PtEntityType.PT_ENTITY_TYPE.CODE, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(PtEntityType.PT_ENTITY_TYPE.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchByName(String... values) {
        return fetch(PtEntityType.PT_ENTITY_TYPE.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public com.comasw.model.tables.pojos.PtEntityType fetchOneByName(String value) {
        return fetchOne(PtEntityType.PT_ENTITY_TYPE.NAME, value);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(PtEntityType.PT_ENTITY_TYPE.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.comasw.model.tables.pojos.PtEntityType> fetchByDescription(String... values) {
        return fetch(PtEntityType.PT_ENTITY_TYPE.DESCRIPTION, values);
    }
}