/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.pojos;


import com.billingweb.model.tables.interfaces.ICtPromoConsumDiscountType;

import java.time.LocalDateTime;


/**
 * Table that stores the promotion fee discount relation types of the catalog 
 * for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtPromoConsumDiscountType implements ICtPromoConsumDiscountType {

    private static final long serialVersionUID = 1L;

    private Integer       promoConsumDiscountTypeId;
    private Integer       promotionTypeId;
    private Integer       consumptionTypeId;
    private Integer       entityConceptId;
    private Integer       statusId;
    private LocalDateTime inputDate;
    private String        inputUser;
    private LocalDateTime modifDate;
    private String        modifUser;

    public CtPromoConsumDiscountType() {}

    public CtPromoConsumDiscountType(ICtPromoConsumDiscountType value) {
        this.promoConsumDiscountTypeId = value.getPromoConsumDiscountTypeId();
        this.promotionTypeId = value.getPromotionTypeId();
        this.consumptionTypeId = value.getConsumptionTypeId();
        this.entityConceptId = value.getEntityConceptId();
        this.statusId = value.getStatusId();
        this.inputDate = value.getInputDate();
        this.inputUser = value.getInputUser();
        this.modifDate = value.getModifDate();
        this.modifUser = value.getModifUser();
    }

    public CtPromoConsumDiscountType(
        Integer       promoConsumDiscountTypeId,
        Integer       promotionTypeId,
        Integer       consumptionTypeId,
        Integer       entityConceptId,
        Integer       statusId,
        LocalDateTime inputDate,
        String        inputUser,
        LocalDateTime modifDate,
        String        modifUser
    ) {
        this.promoConsumDiscountTypeId = promoConsumDiscountTypeId;
        this.promotionTypeId = promotionTypeId;
        this.consumptionTypeId = consumptionTypeId;
        this.entityConceptId = entityConceptId;
        this.statusId = statusId;
        this.inputDate = inputDate;
        this.inputUser = inputUser;
        this.modifDate = modifDate;
        this.modifUser = modifUser;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.promo_consum_discount_type_id</code>. Internal identifier of the promotion-consumption discount relation type
     */
    @Override
    public Integer getPromoConsumDiscountTypeId() {
        return this.promoConsumDiscountTypeId;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.promo_consum_discount_type_id</code>. Internal identifier of the promotion-consumption discount relation type
     */
    @Override
    public void setPromoConsumDiscountTypeId(Integer promoConsumDiscountTypeId) {
        this.promoConsumDiscountTypeId = promoConsumDiscountTypeId;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.promotion_type_id</code>. Promotion_type_id for the promotion-consumption discount relation
     */
    @Override
    public Integer getPromotionTypeId() {
        return this.promotionTypeId;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.promotion_type_id</code>. Promotion_type_id for the promotion-consumption discount relation
     */
    @Override
    public void setPromotionTypeId(Integer promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.consumption_type_id</code>. Consumption_type_id for the promotion-consumption discount relation
     */
    @Override
    public Integer getConsumptionTypeId() {
        return this.consumptionTypeId;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.consumption_type_id</code>. Consumption_type_id for the promotion-consumption discount relation
     */
    @Override
    public void setConsumptionTypeId(Integer consumptionTypeId) {
        this.consumptionTypeId = consumptionTypeId;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.entity_concept_id</code>. Entity_concept_id (entity_type_id) of the discount for the promotion-consumption discount relation
     */
    @Override
    public Integer getEntityConceptId() {
        return this.entityConceptId;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.entity_concept_id</code>. Entity_concept_id (entity_type_id) of the discount for the promotion-consumption discount relation
     */
    @Override
    public void setEntityConceptId(Integer entityConceptId) {
        this.entityConceptId = entityConceptId;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.status_id</code>. Status id for the promotion-consumption discount relation type
     */
    @Override
    public Integer getStatusId() {
        return this.statusId;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.status_id</code>. Status id for the promotion-consumption discount relation type
     */
    @Override
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.input_date</code>. Date on which the record was created
     */
    @Override
    public LocalDateTime getInputDate() {
        return this.inputDate;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.input_date</code>. Date on which the record was created
     */
    @Override
    public void setInputDate(LocalDateTime inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.input_user</code>. User who was modified the record
     */
    @Override
    public String getInputUser() {
        return this.inputUser;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.input_user</code>. User who was modified the record
     */
    @Override
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public LocalDateTime getModifDate() {
        return this.modifDate;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public void setModifDate(LocalDateTime modifDate) {
        this.modifDate = modifDate;
    }

    /**
     * Getter for <code>public.ct_promo_consum_discount_type.modif_user</code>.
     */
    @Override
    public String getModifUser() {
        return this.modifUser;
    }

    /**
     * Setter for <code>public.ct_promo_consum_discount_type.modif_user</code>.
     */
    @Override
    public void setModifUser(String modifUser) {
        this.modifUser = modifUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CtPromoConsumDiscountType (");

        sb.append(promoConsumDiscountTypeId);
        sb.append(", ").append(promotionTypeId);
        sb.append(", ").append(consumptionTypeId);
        sb.append(", ").append(entityConceptId);
        sb.append(", ").append(statusId);
        sb.append(", ").append(inputDate);
        sb.append(", ").append(inputUser);
        sb.append(", ").append(modifDate);
        sb.append(", ").append(modifUser);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ICtPromoConsumDiscountType from) {
        setPromoConsumDiscountTypeId(from.getPromoConsumDiscountTypeId());
        setPromotionTypeId(from.getPromotionTypeId());
        setConsumptionTypeId(from.getConsumptionTypeId());
        setEntityConceptId(from.getEntityConceptId());
        setStatusId(from.getStatusId());
        setInputDate(from.getInputDate());
        setInputUser(from.getInputUser());
        setModifDate(from.getModifDate());
        setModifUser(from.getModifUser());
    }

    @Override
    public <E extends ICtPromoConsumDiscountType> E into(E into) {
        into.from(this);
        return into;
    }
}
