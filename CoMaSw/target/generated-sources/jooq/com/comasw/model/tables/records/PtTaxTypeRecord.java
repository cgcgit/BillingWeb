/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.records;


import com.comasw.model.tables.PtTaxType;
import com.comasw.model.tables.interfaces.IPtTaxType;

import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the tax type of the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtTaxTypeRecord extends UpdatableRecordImpl<PtTaxTypeRecord> implements Record5<Integer, String, String, String, BigDecimal>, IPtTaxType {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.pt_tax_type.tax_type_id</code>. Internal identifier of the tax type
     */
    @Override
    public void setTaxTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.pt_tax_type.tax_type_id</code>. Internal identifier of the tax type
     */
    @Override
    public Integer getTaxTypeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.pt_tax_type.code</code>. Code of the tax type
     */
    @Override
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.pt_tax_type.code</code>. Code of the tax type
     */
    @Override
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.pt_tax_type.name</code>. Name of the tax type
     */
    @Override
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.pt_tax_type.name</code>. Name of the tax type
     */
    @Override
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.pt_tax_type.description</code>. Description for the tax type
     */
    @Override
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.pt_tax_type.description</code>. Description for the tax type
     */
    @Override
    public String getDescription() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.pt_tax_type.percent_value</code>. Percent value for the tax type
     */
    @Override
    public void setPercentValue(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.pt_tax_type.percent_value</code>. Percent value for the tax type
     */
    @Override
    public BigDecimal getPercentValue() {
        return (BigDecimal) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, String, String, BigDecimal> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, String, String, String, BigDecimal> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return PtTaxType.PT_TAX_TYPE.TAX_TYPE_ID;
    }

    @Override
    public Field<String> field2() {
        return PtTaxType.PT_TAX_TYPE.CODE;
    }

    @Override
    public Field<String> field3() {
        return PtTaxType.PT_TAX_TYPE.NAME;
    }

    @Override
    public Field<String> field4() {
        return PtTaxType.PT_TAX_TYPE.DESCRIPTION;
    }

    @Override
    public Field<BigDecimal> field5() {
        return PtTaxType.PT_TAX_TYPE.PERCENT_VALUE;
    }

    @Override
    public Integer component1() {
        return getTaxTypeId();
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
    public BigDecimal component5() {
        return getPercentValue();
    }

    @Override
    public Integer value1() {
        return getTaxTypeId();
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
    public BigDecimal value5() {
        return getPercentValue();
    }

    @Override
    public PtTaxTypeRecord value1(Integer value) {
        setTaxTypeId(value);
        return this;
    }

    @Override
    public PtTaxTypeRecord value2(String value) {
        setCode(value);
        return this;
    }

    @Override
    public PtTaxTypeRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public PtTaxTypeRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public PtTaxTypeRecord value5(BigDecimal value) {
        setPercentValue(value);
        return this;
    }

    @Override
    public PtTaxTypeRecord values(Integer value1, String value2, String value3, String value4, BigDecimal value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IPtTaxType from) {
        setTaxTypeId(from.getTaxTypeId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
        setPercentValue(from.getPercentValue());
    }

    @Override
    public <E extends IPtTaxType> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PtTaxTypeRecord
     */
    public PtTaxTypeRecord() {
        super(PtTaxType.PT_TAX_TYPE);
    }

    /**
     * Create a detached, initialised PtTaxTypeRecord
     */
    public PtTaxTypeRecord(Integer taxTypeId, String code, String name, String description, BigDecimal percentValue) {
        super(PtTaxType.PT_TAX_TYPE);

        setTaxTypeId(taxTypeId);
        setCode(code);
        setName(name);
        setDescription(description);
        setPercentValue(percentValue);
    }
}