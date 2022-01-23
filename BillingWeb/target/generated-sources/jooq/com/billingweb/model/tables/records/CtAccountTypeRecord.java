/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.records;


import com.billingweb.model.tables.CtAccountType;
import com.billingweb.model.tables.interfaces.ICtAccountType;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the account types of the catalog for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtAccountTypeRecord extends UpdatableRecordImpl<CtAccountTypeRecord> implements Record12<Integer, Integer, String, String, String, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String>, ICtAccountType {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.ct_account_type.account_type_id</code>. Internal identifier of the account type
     */
    @Override
    public void setAccountTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.ct_account_type.account_type_id</code>. Internal identifier of the account type
     */
    @Override
    public Integer getAccountTypeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.ct_account_type.entity_type_id</code>. Entity_id for the account type
     */
    @Override
    public void setEntityTypeId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.ct_account_type.entity_type_id</code>. Entity_id for the account type
     */
    @Override
    public Integer getEntityTypeId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.ct_account_type.code</code>. Code of the account type
     */
    @Override
    public void setCode(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.ct_account_type.code</code>. Code of the account type
     */
    @Override
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.ct_account_type.name</code>. Name of the account type
     */
    @Override
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.ct_account_type.name</code>. Name of the account type
     */
    @Override
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.ct_account_type.description</code>. Description for the account type
     */
    @Override
    public void setDescription(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.ct_account_type.description</code>. Description for the account type
     */
    @Override
    public String getDescription() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.ct_account_type.ordinary_bill_cycle_type_id</code>. Ordinary cycle type id for the ordinary billing of the account
     */
    @Override
    public void setOrdinaryBillCycleTypeId(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.ct_account_type.ordinary_bill_cycle_type_id</code>. Ordinary cycle type id for the ordinary billing of the account
     */
    @Override
    public Integer getOrdinaryBillCycleTypeId() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>public.ct_account_type.corrective_bill_cycle_type_id</code>. Bill cycle type id for the ordinary billing of the account
     */
    @Override
    public void setCorrectiveBillCycleTypeId(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.ct_account_type.corrective_bill_cycle_type_id</code>. Bill cycle type id for the ordinary billing of the account
     */
    @Override
    public Integer getCorrectiveBillCycleTypeId() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>public.ct_account_type.status_id</code>. Status id for the account type
     */
    @Override
    public void setStatusId(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.ct_account_type.status_id</code>. Status id for the account type
     */
    @Override
    public Integer getStatusId() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>public.ct_account_type.input_date</code>. Date on which the record was created
     */
    @Override
    public void setInputDate(LocalDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.ct_account_type.input_date</code>. Date on which the record was created
     */
    @Override
    public LocalDateTime getInputDate() {
        return (LocalDateTime) get(8);
    }

    /**
     * Setter for <code>public.ct_account_type.input_user</code>. User who was modified the record
     */
    @Override
    public void setInputUser(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.ct_account_type.input_user</code>. User who was modified the record
     */
    @Override
    public String getInputUser() {
        return (String) get(9);
    }

    /**
     * Setter for <code>public.ct_account_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public void setModifDate(LocalDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.ct_account_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public LocalDateTime getModifDate() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>public.ct_account_type.modif_user</code>.
     */
    @Override
    public void setModifUser(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.ct_account_type.modif_user</code>.
     */
    @Override
    public String getModifUser() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<Integer, Integer, String, String, String, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<Integer, Integer, String, String, String, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return CtAccountType.CT_ACCOUNT_TYPE.ACCOUNT_TYPE_ID;
    }

    @Override
    public Field<Integer> field2() {
        return CtAccountType.CT_ACCOUNT_TYPE.ENTITY_TYPE_ID;
    }

    @Override
    public Field<String> field3() {
        return CtAccountType.CT_ACCOUNT_TYPE.CODE;
    }

    @Override
    public Field<String> field4() {
        return CtAccountType.CT_ACCOUNT_TYPE.NAME;
    }

    @Override
    public Field<String> field5() {
        return CtAccountType.CT_ACCOUNT_TYPE.DESCRIPTION;
    }

    @Override
    public Field<Integer> field6() {
        return CtAccountType.CT_ACCOUNT_TYPE.ORDINARY_BILL_CYCLE_TYPE_ID;
    }

    @Override
    public Field<Integer> field7() {
        return CtAccountType.CT_ACCOUNT_TYPE.CORRECTIVE_BILL_CYCLE_TYPE_ID;
    }

    @Override
    public Field<Integer> field8() {
        return CtAccountType.CT_ACCOUNT_TYPE.STATUS_ID;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return CtAccountType.CT_ACCOUNT_TYPE.INPUT_DATE;
    }

    @Override
    public Field<String> field10() {
        return CtAccountType.CT_ACCOUNT_TYPE.INPUT_USER;
    }

    @Override
    public Field<LocalDateTime> field11() {
        return CtAccountType.CT_ACCOUNT_TYPE.MODIF_DATE;
    }

    @Override
    public Field<String> field12() {
        return CtAccountType.CT_ACCOUNT_TYPE.MODIF_USER;
    }

    @Override
    public Integer component1() {
        return getAccountTypeId();
    }

    @Override
    public Integer component2() {
        return getEntityTypeId();
    }

    @Override
    public String component3() {
        return getCode();
    }

    @Override
    public String component4() {
        return getName();
    }

    @Override
    public String component5() {
        return getDescription();
    }

    @Override
    public Integer component6() {
        return getOrdinaryBillCycleTypeId();
    }

    @Override
    public Integer component7() {
        return getCorrectiveBillCycleTypeId();
    }

    @Override
    public Integer component8() {
        return getStatusId();
    }

    @Override
    public LocalDateTime component9() {
        return getInputDate();
    }

    @Override
    public String component10() {
        return getInputUser();
    }

    @Override
    public LocalDateTime component11() {
        return getModifDate();
    }

    @Override
    public String component12() {
        return getModifUser();
    }

    @Override
    public Integer value1() {
        return getAccountTypeId();
    }

    @Override
    public Integer value2() {
        return getEntityTypeId();
    }

    @Override
    public String value3() {
        return getCode();
    }

    @Override
    public String value4() {
        return getName();
    }

    @Override
    public String value5() {
        return getDescription();
    }

    @Override
    public Integer value6() {
        return getOrdinaryBillCycleTypeId();
    }

    @Override
    public Integer value7() {
        return getCorrectiveBillCycleTypeId();
    }

    @Override
    public Integer value8() {
        return getStatusId();
    }

    @Override
    public LocalDateTime value9() {
        return getInputDate();
    }

    @Override
    public String value10() {
        return getInputUser();
    }

    @Override
    public LocalDateTime value11() {
        return getModifDate();
    }

    @Override
    public String value12() {
        return getModifUser();
    }

    @Override
    public CtAccountTypeRecord value1(Integer value) {
        setAccountTypeId(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value2(Integer value) {
        setEntityTypeId(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value3(String value) {
        setCode(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value5(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value6(Integer value) {
        setOrdinaryBillCycleTypeId(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value7(Integer value) {
        setCorrectiveBillCycleTypeId(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value8(Integer value) {
        setStatusId(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value9(LocalDateTime value) {
        setInputDate(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value10(String value) {
        setInputUser(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value11(LocalDateTime value) {
        setModifDate(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord value12(String value) {
        setModifUser(value);
        return this;
    }

    @Override
    public CtAccountTypeRecord values(Integer value1, Integer value2, String value3, String value4, String value5, Integer value6, Integer value7, Integer value8, LocalDateTime value9, String value10, LocalDateTime value11, String value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ICtAccountType from) {
        setAccountTypeId(from.getAccountTypeId());
        setEntityTypeId(from.getEntityTypeId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
        setOrdinaryBillCycleTypeId(from.getOrdinaryBillCycleTypeId());
        setCorrectiveBillCycleTypeId(from.getCorrectiveBillCycleTypeId());
        setStatusId(from.getStatusId());
        setInputDate(from.getInputDate());
        setInputUser(from.getInputUser());
        setModifDate(from.getModifDate());
        setModifUser(from.getModifUser());
    }

    @Override
    public <E extends ICtAccountType> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CtAccountTypeRecord
     */
    public CtAccountTypeRecord() {
        super(CtAccountType.CT_ACCOUNT_TYPE);
    }

    /**
     * Create a detached, initialised CtAccountTypeRecord
     */
    public CtAccountTypeRecord(Integer accountTypeId, Integer entityTypeId, String code, String name, String description, Integer ordinaryBillCycleTypeId, Integer correctiveBillCycleTypeId, Integer statusId, LocalDateTime inputDate, String inputUser, LocalDateTime modifDate, String modifUser) {
        super(CtAccountType.CT_ACCOUNT_TYPE);

        setAccountTypeId(accountTypeId);
        setEntityTypeId(entityTypeId);
        setCode(code);
        setName(name);
        setDescription(description);
        setOrdinaryBillCycleTypeId(ordinaryBillCycleTypeId);
        setCorrectiveBillCycleTypeId(correctiveBillCycleTypeId);
        setStatusId(statusId);
        setInputDate(inputDate);
        setInputUser(inputUser);
        setModifDate(modifDate);
        setModifUser(modifUser);
    }
}
