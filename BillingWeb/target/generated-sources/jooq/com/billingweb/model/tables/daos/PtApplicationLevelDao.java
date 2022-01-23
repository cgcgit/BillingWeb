/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.daos;


import com.billingweb.model.tables.PtApplicationLevel;
import com.billingweb.model.tables.records.PtApplicationLevelRecord;

import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * Table that stores the application level for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtApplicationLevelDao extends DAOImpl<PtApplicationLevelRecord, com.billingweb.model.tables.pojos.PtApplicationLevel, Integer> {

    /**
     * Create a new PtApplicationLevelDao without any configuration
     */
    public PtApplicationLevelDao() {
        super(PtApplicationLevel.PT_APPLICATION_LEVEL, com.billingweb.model.tables.pojos.PtApplicationLevel.class);
    }

    /**
     * Create a new PtApplicationLevelDao with an attached configuration
     */
    public PtApplicationLevelDao(Configuration configuration) {
        super(PtApplicationLevel.PT_APPLICATION_LEVEL, com.billingweb.model.tables.pojos.PtApplicationLevel.class, configuration);
    }

    @Override
    public Integer getId(com.billingweb.model.tables.pojos.PtApplicationLevel object) {
        return object.getApplicationLevelId();
    }

    /**
     * Fetch records that have <code>application_level_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchRangeOfApplicationLevelId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(PtApplicationLevel.PT_APPLICATION_LEVEL.APPLICATION_LEVEL_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>application_level_id IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchByApplicationLevelId(Integer... values) {
        return fetch(PtApplicationLevel.PT_APPLICATION_LEVEL.APPLICATION_LEVEL_ID, values);
    }

    /**
     * Fetch a unique record that has <code>application_level_id = value</code>
     */
    public com.billingweb.model.tables.pojos.PtApplicationLevel fetchOneByApplicationLevelId(Integer value) {
        return fetchOne(PtApplicationLevel.PT_APPLICATION_LEVEL.APPLICATION_LEVEL_ID, value);
    }

    /**
     * Fetch records that have <code>code BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchRangeOfCode(String lowerInclusive, String upperInclusive) {
        return fetchRange(PtApplicationLevel.PT_APPLICATION_LEVEL.CODE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>code IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchByCode(String... values) {
        return fetch(PtApplicationLevel.PT_APPLICATION_LEVEL.CODE, values);
    }

    /**
     * Fetch a unique record that has <code>code = value</code>
     */
    public com.billingweb.model.tables.pojos.PtApplicationLevel fetchOneByCode(String value) {
        return fetchOne(PtApplicationLevel.PT_APPLICATION_LEVEL.CODE, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(PtApplicationLevel.PT_APPLICATION_LEVEL.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchByName(String... values) {
        return fetch(PtApplicationLevel.PT_APPLICATION_LEVEL.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public com.billingweb.model.tables.pojos.PtApplicationLevel fetchOneByName(String value) {
        return fetchOne(PtApplicationLevel.PT_APPLICATION_LEVEL.NAME, value);
    }

    /**
     * Fetch records that have <code>description BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchRangeOfDescription(String lowerInclusive, String upperInclusive) {
        return fetchRange(PtApplicationLevel.PT_APPLICATION_LEVEL.DESCRIPTION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>description IN (values)</code>
     */
    public List<com.billingweb.model.tables.pojos.PtApplicationLevel> fetchByDescription(String... values) {
        return fetch(PtApplicationLevel.PT_APPLICATION_LEVEL.DESCRIPTION, values);
    }
}
