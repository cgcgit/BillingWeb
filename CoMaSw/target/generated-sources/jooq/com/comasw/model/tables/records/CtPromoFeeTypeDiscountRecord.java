/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.records;


import com.comasw.model.tables.CtPromoFeeTypeDiscount;
import com.comasw.model.tables.interfaces.ICtPromoFeeTypeDiscount;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the promotion fee discount relation types of the catalog 
 * for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtPromoFeeTypeDiscountRecord extends UpdatableRecordImpl<CtPromoFeeTypeDiscountRecord> implements Record9<Integer, Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String>, ICtPromoFeeTypeDiscount {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.promo_fee_type_discount_id</code>. Internal identifier of the promotion-fee discount relation type
     */
    @Override
    public void setPromoFeeTypeDiscountId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.promo_fee_type_discount_id</code>. Internal identifier of the promotion-fee discount relation type
     */
    @Override
    public Integer getPromoFeeTypeDiscountId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.promotion_type_id</code>. Promotion_type_id for the promotion-fee discount relation
     */
    @Override
    public void setPromotionTypeId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.promotion_type_id</code>. Promotion_type_id for the promotion-fee discount relation
     */
    @Override
    public Integer getPromotionTypeId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.fee_type_id</code>. Fee_type_id for the promotion-fee discount relation
     */
    @Override
    public void setFeeTypeId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.fee_type_id</code>. Fee_type_id for the promotion-fee discount relation
     */
    @Override
    public Integer getFeeTypeId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.application_level_id</code>. Application_level_id for the promotion-fee discount relation
     */
    @Override
    public void setApplicationLevelId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.application_level_id</code>. Application_level_id for the promotion-fee discount relation
     */
    @Override
    public Integer getApplicationLevelId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.status_id</code>. Status id for the promotion-fee discount relation type
     */
    @Override
    public void setStatusId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.status_id</code>. Status id for the promotion-fee discount relation type
     */
    @Override
    public Integer getStatusId() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.input_date</code>. Date on which the record was created
     */
    @Override
    public void setInputDate(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.input_date</code>. Date on which the record was created
     */
    @Override
    public LocalDateTime getInputDate() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.input_user</code>. User who was modified the record
     */
    @Override
    public void setInputUser(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.input_user</code>. User who was modified the record
     */
    @Override
    public String getInputUser() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public void setModifDate(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public LocalDateTime getModifDate() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>public.ct_promo_fee_type_discount.modif_user</code>.
     */
    @Override
    public void setModifUser(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.ct_promo_fee_type_discount.modif_user</code>.
     */
    @Override
    public String getModifUser() {
        return (String) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Integer, Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.PROMO_FEE_TYPE_DISCOUNT_ID;
    }

    @Override
    public Field<Integer> field2() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.PROMOTION_TYPE_ID;
    }

    @Override
    public Field<Integer> field3() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.FEE_TYPE_ID;
    }

    @Override
    public Field<Integer> field4() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.APPLICATION_LEVEL_ID;
    }

    @Override
    public Field<Integer> field5() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.STATUS_ID;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.INPUT_DATE;
    }

    @Override
    public Field<String> field7() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.INPUT_USER;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.MODIF_DATE;
    }

    @Override
    public Field<String> field9() {
        return CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT.MODIF_USER;
    }

    @Override
    public Integer component1() {
        return getPromoFeeTypeDiscountId();
    }

    @Override
    public Integer component2() {
        return getPromotionTypeId();
    }

    @Override
    public Integer component3() {
        return getFeeTypeId();
    }

    @Override
    public Integer component4() {
        return getApplicationLevelId();
    }

    @Override
    public Integer component5() {
        return getStatusId();
    }

    @Override
    public LocalDateTime component6() {
        return getInputDate();
    }

    @Override
    public String component7() {
        return getInputUser();
    }

    @Override
    public LocalDateTime component8() {
        return getModifDate();
    }

    @Override
    public String component9() {
        return getModifUser();
    }

    @Override
    public Integer value1() {
        return getPromoFeeTypeDiscountId();
    }

    @Override
    public Integer value2() {
        return getPromotionTypeId();
    }

    @Override
    public Integer value3() {
        return getFeeTypeId();
    }

    @Override
    public Integer value4() {
        return getApplicationLevelId();
    }

    @Override
    public Integer value5() {
        return getStatusId();
    }

    @Override
    public LocalDateTime value6() {
        return getInputDate();
    }

    @Override
    public String value7() {
        return getInputUser();
    }

    @Override
    public LocalDateTime value8() {
        return getModifDate();
    }

    @Override
    public String value9() {
        return getModifUser();
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value1(Integer value) {
        setPromoFeeTypeDiscountId(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value2(Integer value) {
        setPromotionTypeId(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value3(Integer value) {
        setFeeTypeId(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value4(Integer value) {
        setApplicationLevelId(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value5(Integer value) {
        setStatusId(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value6(LocalDateTime value) {
        setInputDate(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value7(String value) {
        setInputUser(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value8(LocalDateTime value) {
        setModifDate(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord value9(String value) {
        setModifUser(value);
        return this;
    }

    @Override
    public CtPromoFeeTypeDiscountRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Integer value5, LocalDateTime value6, String value7, LocalDateTime value8, String value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ICtPromoFeeTypeDiscount from) {
        setPromoFeeTypeDiscountId(from.getPromoFeeTypeDiscountId());
        setPromotionTypeId(from.getPromotionTypeId());
        setFeeTypeId(from.getFeeTypeId());
        setApplicationLevelId(from.getApplicationLevelId());
        setStatusId(from.getStatusId());
        setInputDate(from.getInputDate());
        setInputUser(from.getInputUser());
        setModifDate(from.getModifDate());
        setModifUser(from.getModifUser());
    }

    @Override
    public <E extends ICtPromoFeeTypeDiscount> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CtPromoFeeTypeDiscountRecord
     */
    public CtPromoFeeTypeDiscountRecord() {
        super(CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT);
    }

    /**
     * Create a detached, initialised CtPromoFeeTypeDiscountRecord
     */
    public CtPromoFeeTypeDiscountRecord(Integer promoFeeTypeDiscountId, Integer promotionTypeId, Integer feeTypeId, Integer applicationLevelId, Integer statusId, LocalDateTime inputDate, String inputUser, LocalDateTime modifDate, String modifUser) {
        super(CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT);

        setPromoFeeTypeDiscountId(promoFeeTypeDiscountId);
        setPromotionTypeId(promotionTypeId);
        setFeeTypeId(feeTypeId);
        setApplicationLevelId(applicationLevelId);
        setStatusId(statusId);
        setInputDate(inputDate);
        setInputUser(inputUser);
        setModifDate(modifDate);
        setModifUser(modifUser);
    }
}
