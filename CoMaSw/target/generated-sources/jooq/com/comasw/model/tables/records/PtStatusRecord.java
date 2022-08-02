/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.records;


import com.comasw.model.tables.PtStatus;
import com.comasw.model.tables.interfaces.IPtStatus;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the status for the entities of the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtStatusRecord extends UpdatableRecordImpl<PtStatusRecord> implements Record6<Integer, String, String, String, Boolean, Boolean>, IPtStatus {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.pt_status.status_id</code>. Internal identifier of the status
     */
    @Override
    public void setStatusId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.pt_status.status_id</code>. Internal identifier of the status
     */
    @Override
    public Integer getStatusId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.pt_status.code</code>. Code of the status
     */
    @Override
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.pt_status.code</code>. Code of the status
     */
    @Override
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.pt_status.name</code>. Name of the status
     */
    @Override
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.pt_status.name</code>. Name of the status
     */
    @Override
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.pt_status.description</code>. Description for the status
     */
    @Override
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.pt_status.description</code>. Description for the status
     */
    @Override
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.pt_status.catalog</code>. Indicates if the status applies to catalog entities
     */
    @Override
    public void setCatalog(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.pt_status.catalog</code>. Indicates if the status applies to catalog entities
     */
    @Override
    public Boolean getCatalog() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>public.pt_status.instance</code>. Indicates if the status applies to instance entities
     */
    @Override
    public void setInstance(Boolean value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.pt_status.instance</code>. Indicates if the status applies to instance entities
     */
    @Override
    public Boolean getInstance() {
        return (Boolean) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, String, Boolean, Boolean> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, String, String, Boolean, Boolean> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return PtStatus.PT_STATUS.STATUS_ID;
    }

    @Override
    public Field<String> field2() {
        return PtStatus.PT_STATUS.CODE;
    }

    @Override
    public Field<String> field3() {
        return PtStatus.PT_STATUS.NAME;
    }

    @Override
    public Field<String> field4() {
        return PtStatus.PT_STATUS.DESCRIPTION;
    }

    @Override
    public Field<Boolean> field5() {
        return PtStatus.PT_STATUS.CATALOG;
    }

    @Override
    public Field<Boolean> field6() {
        return PtStatus.PT_STATUS.INSTANCE;
    }

    @Override
    public Integer component1() {
        return getStatusId();
    }

    @Override
    public String component2() {
        return getCode();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public String component4() {
        return getDescription();
    }

    @Override
    public Boolean component5() {
        return getCatalog();
    }

    @Override
    public Boolean component6() {
        return getInstance();
    }

    @Override
    public Integer value1() {
        return getStatusId();
    }

    @Override
    public String value2() {
        return getCode();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public String value4() {
        return getDescription();
    }

    @Override
    public Boolean value5() {
        return getCatalog();
    }

    @Override
    public Boolean value6() {
        return getInstance();
    }

    @Override
    public PtStatusRecord value1(Integer value) {
        setStatusId(value);
        return this;
    }

    @Override
    public PtStatusRecord value2(String value) {
        setCode(value);
        return this;
    }

    @Override
    public PtStatusRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public PtStatusRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public PtStatusRecord value5(Boolean value) {
        setCatalog(value);
        return this;
    }

    @Override
    public PtStatusRecord value6(Boolean value) {
        setInstance(value);
        return this;
    }

    @Override
    public PtStatusRecord values(Integer value1, String value2, String value3, String value4, Boolean value5, Boolean value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IPtStatus from) {
        setStatusId(from.getStatusId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
        setCatalog(from.getCatalog());
        setInstance(from.getInstance());
    }

    @Override
    public <E extends IPtStatus> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PtStatusRecord
     */
    public PtStatusRecord() {
        super(PtStatus.PT_STATUS);
    }

    /**
     * Create a detached, initialised PtStatusRecord
     */
    public PtStatusRecord(Integer statusId, String code, String name, String description, Boolean catalog, Boolean instance) {
        super(PtStatus.PT_STATUS);

        setStatusId(statusId);
        setCode(code);
        setName(name);
        setDescription(description);
        setCatalog(catalog);
        setInstance(instance);
    }
}
