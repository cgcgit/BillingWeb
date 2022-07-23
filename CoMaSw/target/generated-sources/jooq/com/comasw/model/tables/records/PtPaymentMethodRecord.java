/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.records;


import com.comasw.model.tables.PtPaymentMethod;
import com.comasw.model.tables.interfaces.IPtPaymentMethod;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the payment types for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtPaymentMethodRecord extends UpdatableRecordImpl<PtPaymentMethodRecord> implements Record4<Integer, String, String, String>, IPtPaymentMethod {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.pt_payment_method.payment_method_id</code>. Internal identifier of the payment method
     */
    @Override
    public void setPaymentMethodId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.pt_payment_method.payment_method_id</code>. Internal identifier of the payment method
     */
    @Override
    public Integer getPaymentMethodId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.pt_payment_method.code</code>. Code of the payment method
     */
    @Override
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.pt_payment_method.code</code>. Code of the payment method
     */
    @Override
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.pt_payment_method.name</code>. Name of the payment method
     */
    @Override
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.pt_payment_method.name</code>. Name of the payment method
     */
    @Override
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.pt_payment_method.description</code>. Description for the payment method
     */
    @Override
    public void setDescription(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.pt_payment_method.description</code>. Description for the payment method
     */
    @Override
    public String getDescription() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return PtPaymentMethod.PT_PAYMENT_METHOD.PAYMENT_METHOD_ID;
    }

    @Override
    public Field<String> field2() {
        return PtPaymentMethod.PT_PAYMENT_METHOD.CODE;
    }

    @Override
    public Field<String> field3() {
        return PtPaymentMethod.PT_PAYMENT_METHOD.NAME;
    }

    @Override
    public Field<String> field4() {
        return PtPaymentMethod.PT_PAYMENT_METHOD.DESCRIPTION;
    }

    @Override
    public Integer component1() {
        return getPaymentMethodId();
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
    public Integer value1() {
        return getPaymentMethodId();
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
    public PtPaymentMethodRecord value1(Integer value) {
        setPaymentMethodId(value);
        return this;
    }

    @Override
    public PtPaymentMethodRecord value2(String value) {
        setCode(value);
        return this;
    }

    @Override
    public PtPaymentMethodRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public PtPaymentMethodRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public PtPaymentMethodRecord values(Integer value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IPtPaymentMethod from) {
        setPaymentMethodId(from.getPaymentMethodId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
    }

    @Override
    public <E extends IPtPaymentMethod> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PtPaymentMethodRecord
     */
    public PtPaymentMethodRecord() {
        super(PtPaymentMethod.PT_PAYMENT_METHOD);
    }

    /**
     * Create a detached, initialised PtPaymentMethodRecord
     */
    public PtPaymentMethodRecord(Integer paymentMethodId, String code, String name, String description) {
        super(PtPaymentMethod.PT_PAYMENT_METHOD);

        setPaymentMethodId(paymentMethodId);
        setCode(code);
        setName(name);
        setDescription(description);
    }
}
