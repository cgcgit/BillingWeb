/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.records;


import com.billingweb.model.tables.CtPromoServType;
import com.billingweb.model.tables.interfaces.ICtPromoServType;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the promotion service relation types of the catalog for 
 * the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtPromoServTypeRecord extends UpdatableRecordImpl<CtPromoServTypeRecord> implements Record8<Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String>, ICtPromoServType {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.ct_promo_serv_type.promo_serv_type_id</code>. Internal identifier of the promotion-service relation type
     */
    @Override
    public void setPromoServTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.promo_serv_type_id</code>. Internal identifier of the promotion-service relation type
     */
    @Override
    public Integer getPromoServTypeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.promotion_type_id</code>. Promotion_type_id for the promotion-service relation
     */
    @Override
    public void setPromotionTypeId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.promotion_type_id</code>. Promotion_type_id for the promotion-service relation
     */
    @Override
    public Integer getPromotionTypeId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.service_type_id</code>. Service_type_id for the promotion-service relation
     */
    @Override
    public void setServiceTypeId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.service_type_id</code>. Service_type_id for the promotion-service relation
     */
    @Override
    public Integer getServiceTypeId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.status_id</code>. Status id for the promotion-service relation type
     */
    @Override
    public void setStatusId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.status_id</code>. Status id for the promotion-service relation type
     */
    @Override
    public Integer getStatusId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.input_date</code>. Date on which the record was created
     */
    @Override
    public void setInputDate(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.input_date</code>. Date on which the record was created
     */
    @Override
    public LocalDateTime getInputDate() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.input_user</code>. User who was modified the record
     */
    @Override
    public void setInputUser(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.input_user</code>. User who was modified the record
     */
    @Override
    public String getInputUser() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public void setModifDate(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public LocalDateTime getModifDate() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.modif_user</code>.
     */
    @Override
    public void setModifUser(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.modif_user</code>.
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
        return CtPromoServType.CT_PROMO_SERV_TYPE.PROMO_SERV_TYPE_ID;
    }

    @Override
    public Field<Integer> field2() {
        return CtPromoServType.CT_PROMO_SERV_TYPE.PROMOTION_TYPE_ID;
    }

    @Override
    public Field<Integer> field3() {
        return CtPromoServType.CT_PROMO_SERV_TYPE.SERVICE_TYPE_ID;
    }

    @Override
    public Field<Integer> field4() {
        return CtPromoServType.CT_PROMO_SERV_TYPE.STATUS_ID;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return CtPromoServType.CT_PROMO_SERV_TYPE.INPUT_DATE;
    }

    @Override
    public Field<String> field6() {
        return CtPromoServType.CT_PROMO_SERV_TYPE.INPUT_USER;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return CtPromoServType.CT_PROMO_SERV_TYPE.MODIF_DATE;
    }

    @Override
    public Field<String> field8() {
        return CtPromoServType.CT_PROMO_SERV_TYPE.MODIF_USER;
    }

    @Override
    public Integer component1() {
        return getPromoServTypeId();
    }

    @Override
    public Integer component2() {
        return getPromotionTypeId();
    }

    @Override
    public Integer component3() {
        return getServiceTypeId();
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
        return getPromoServTypeId();
    }

    @Override
    public Integer value2() {
        return getPromotionTypeId();
    }

    @Override
    public Integer value3() {
        return getServiceTypeId();
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
    public CtPromoServTypeRecord value1(Integer value) {
        setPromoServTypeId(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord value2(Integer value) {
        setPromotionTypeId(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord value3(Integer value) {
        setServiceTypeId(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord value4(Integer value) {
        setStatusId(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord value5(LocalDateTime value) {
        setInputDate(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord value6(String value) {
        setInputUser(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord value7(LocalDateTime value) {
        setModifDate(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord value8(String value) {
        setModifUser(value);
        return this;
    }

    @Override
    public CtPromoServTypeRecord values(Integer value1, Integer value2, Integer value3, Integer value4, LocalDateTime value5, String value6, LocalDateTime value7, String value8) {
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
    public void from(ICtPromoServType from) {
        setPromoServTypeId(from.getPromoServTypeId());
        setPromotionTypeId(from.getPromotionTypeId());
        setServiceTypeId(from.getServiceTypeId());
        setStatusId(from.getStatusId());
        setInputDate(from.getInputDate());
        setInputUser(from.getInputUser());
        setModifDate(from.getModifDate());
        setModifUser(from.getModifUser());
    }

    @Override
    public <E extends ICtPromoServType> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CtPromoServTypeRecord
     */
    public CtPromoServTypeRecord() {
        super(CtPromoServType.CT_PROMO_SERV_TYPE);
    }

    /**
     * Create a detached, initialised CtPromoServTypeRecord
     */
    public CtPromoServTypeRecord(Integer promoServTypeId, Integer promotionTypeId, Integer serviceTypeId, Integer statusId, LocalDateTime inputDate, String inputUser, LocalDateTime modifDate, String modifUser) {
        super(CtPromoServType.CT_PROMO_SERV_TYPE);

        setPromoServTypeId(promoServTypeId);
        setPromotionTypeId(promotionTypeId);
        setServiceTypeId(serviceTypeId);
        setStatusId(statusId);
        setInputDate(inputDate);
        setInputUser(inputUser);
        setModifDate(modifDate);
        setModifUser(modifUser);
    }
}
