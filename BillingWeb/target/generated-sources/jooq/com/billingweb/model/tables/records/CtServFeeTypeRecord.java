/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.records;


import com.billingweb.model.tables.CtServFeeType;
import com.billingweb.model.tables.interfaces.ICtServFeeType;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the fee service relation types of the catalog for the 
 * application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtServFeeTypeRecord extends UpdatableRecordImpl<CtServFeeTypeRecord> implements Record8<Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String>, ICtServFeeType {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.ct_serv_fee_type.serv_fee_type_id</code>. Internal identifier of the service-fee relation type
     */
    @Override
    public void setServFeeTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.serv_fee_type_id</code>. Internal identifier of the service-fee relation type
     */
    @Override
    public Integer getServFeeTypeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.ct_serv_fee_type.service_type_id</code>. Product_type_id for the service-fee relation
     */
    @Override
    public void setServiceTypeId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.service_type_id</code>. Product_type_id for the service-fee relation
     */
    @Override
    public Integer getServiceTypeId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.ct_serv_fee_type.fee_type_id</code>. Service_type_id for the service-fee relation
     */
    @Override
    public void setFeeTypeId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.fee_type_id</code>. Service_type_id for the service-fee relation
     */
    @Override
    public Integer getFeeTypeId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.ct_serv_fee_type.status_id</code>. Status id for the service-fee relation type
     */
    @Override
    public void setStatusId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.status_id</code>. Status id for the service-fee relation type
     */
    @Override
    public Integer getStatusId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.ct_serv_fee_type.input_date</code>. Date on which the record was created
     */
    @Override
    public void setInputDate(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.input_date</code>. Date on which the record was created
     */
    @Override
    public LocalDateTime getInputDate() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>public.ct_serv_fee_type.input_user</code>. User who was modified the record
     */
    @Override
    public void setInputUser(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.input_user</code>. User who was modified the record
     */
    @Override
    public String getInputUser() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.ct_serv_fee_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public void setModifDate(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public LocalDateTime getModifDate() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>public.ct_serv_fee_type.modif_user</code>.
     */
    @Override
    public void setModifUser(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.ct_serv_fee_type.modif_user</code>.
     */
    @Override
    public String getModifUser() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return CtServFeeType.CT_SERV_FEE_TYPE.SERV_FEE_TYPE_ID;
    }

    @Override
    public Field<Integer> field2() {
        return CtServFeeType.CT_SERV_FEE_TYPE.SERVICE_TYPE_ID;
    }

    @Override
    public Field<Integer> field3() {
        return CtServFeeType.CT_SERV_FEE_TYPE.FEE_TYPE_ID;
    }

    @Override
    public Field<Integer> field4() {
        return CtServFeeType.CT_SERV_FEE_TYPE.STATUS_ID;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return CtServFeeType.CT_SERV_FEE_TYPE.INPUT_DATE;
    }

    @Override
    public Field<String> field6() {
        return CtServFeeType.CT_SERV_FEE_TYPE.INPUT_USER;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return CtServFeeType.CT_SERV_FEE_TYPE.MODIF_DATE;
    }

    @Override
    public Field<String> field8() {
        return CtServFeeType.CT_SERV_FEE_TYPE.MODIF_USER;
    }

    @Override
    public Integer component1() {
        return getServFeeTypeId();
    }

    @Override
    public Integer component2() {
        return getServiceTypeId();
    }

    @Override
    public Integer component3() {
        return getFeeTypeId();
    }

    @Override
    public Integer component4() {
        return getStatusId();
    }

    @Override
    public LocalDateTime component5() {
        return getInputDate();
    }

    @Override
    public String component6() {
        return getInputUser();
    }

    @Override
    public LocalDateTime component7() {
        return getModifDate();
    }

    @Override
    public String component8() {
        return getModifUser();
    }

    @Override
    public Integer value1() {
        return getServFeeTypeId();
    }

    @Override
    public Integer value2() {
        return getServiceTypeId();
    }

    @Override
    public Integer value3() {
        return getFeeTypeId();
    }

    @Override
    public Integer value4() {
        return getStatusId();
    }

    @Override
    public LocalDateTime value5() {
        return getInputDate();
    }

    @Override
    public String value6() {
        return getInputUser();
    }

    @Override
    public LocalDateTime value7() {
        return getModifDate();
    }

    @Override
    public String value8() {
        return getModifUser();
    }

    @Override
    public CtServFeeTypeRecord value1(Integer value) {
        setServFeeTypeId(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord value2(Integer value) {
        setServiceTypeId(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord value3(Integer value) {
        setFeeTypeId(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord value4(Integer value) {
        setStatusId(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord value5(LocalDateTime value) {
        setInputDate(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord value6(String value) {
        setInputUser(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord value7(LocalDateTime value) {
        setModifDate(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord value8(String value) {
        setModifUser(value);
        return this;
    }

    @Override
    public CtServFeeTypeRecord values(Integer value1, Integer value2, Integer value3, Integer value4, LocalDateTime value5, String value6, LocalDateTime value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ICtServFeeType from) {
        setServFeeTypeId(from.getServFeeTypeId());
        setServiceTypeId(from.getServiceTypeId());
        setFeeTypeId(from.getFeeTypeId());
        setStatusId(from.getStatusId());
        setInputDate(from.getInputDate());
        setInputUser(from.getInputUser());
        setModifDate(from.getModifDate());
        setModifUser(from.getModifUser());
    }

    @Override
    public <E extends ICtServFeeType> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CtServFeeTypeRecord
     */
    public CtServFeeTypeRecord() {
        super(CtServFeeType.CT_SERV_FEE_TYPE);
    }

    /**
     * Create a detached, initialised CtServFeeTypeRecord
     */
    public CtServFeeTypeRecord(Integer servFeeTypeId, Integer serviceTypeId, Integer feeTypeId, Integer statusId, LocalDateTime inputDate, String inputUser, LocalDateTime modifDate, String modifUser) {
        super(CtServFeeType.CT_SERV_FEE_TYPE);

        setServFeeTypeId(servFeeTypeId);
        setServiceTypeId(serviceTypeId);
        setFeeTypeId(feeTypeId);
        setStatusId(statusId);
        setInputDate(inputDate);
        setInputUser(inputUser);
        setModifDate(modifDate);
        setModifUser(modifUser);
    }
}
