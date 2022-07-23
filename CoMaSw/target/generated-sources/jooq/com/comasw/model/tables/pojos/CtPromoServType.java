/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.pojos;


import com.comasw.model.tables.interfaces.ICtPromoServType;

import java.time.LocalDateTime;


/**
 * Table that stores the promotion service relation types of the catalog for 
 * the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtPromoServType implements ICtPromoServType {

    private static final long serialVersionUID = 1L;

    private Integer       promoServTypeId;
    private Integer       promotionTypeId;
    private Integer       serviceTypeId;
    private Integer       statusId;
    private LocalDateTime inputDate;
    private String        inputUser;
    private LocalDateTime modifDate;
    private String        modifUser;

    public CtPromoServType() {}

    public CtPromoServType(ICtPromoServType value) {
        this.promoServTypeId = value.getPromoServTypeId();
        this.promotionTypeId = value.getPromotionTypeId();
        this.serviceTypeId = value.getServiceTypeId();
        this.statusId = value.getStatusId();
        this.inputDate = value.getInputDate();
        this.inputUser = value.getInputUser();
        this.modifDate = value.getModifDate();
        this.modifUser = value.getModifUser();
    }

    public CtPromoServType(
        Integer       promoServTypeId,
        Integer       promotionTypeId,
        Integer       serviceTypeId,
        Integer       statusId,
        LocalDateTime inputDate,
        String        inputUser,
        LocalDateTime modifDate,
        String        modifUser
    ) {
        this.promoServTypeId = promoServTypeId;
        this.promotionTypeId = promotionTypeId;
        this.serviceTypeId = serviceTypeId;
        this.statusId = statusId;
        this.inputDate = inputDate;
        this.inputUser = inputUser;
        this.modifDate = modifDate;
        this.modifUser = modifUser;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.promo_serv_type_id</code>. Internal identifier of the promotion-service relation type
     */
    @Override
    public Integer getPromoServTypeId() {
        return this.promoServTypeId;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.promo_serv_type_id</code>. Internal identifier of the promotion-service relation type
     */
    @Override
    public void setPromoServTypeId(Integer promoServTypeId) {
        this.promoServTypeId = promoServTypeId;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.promotion_type_id</code>. Promotion_type_id for the promotion-service relation
     */
    @Override
    public Integer getPromotionTypeId() {
        return this.promotionTypeId;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.promotion_type_id</code>. Promotion_type_id for the promotion-service relation
     */
    @Override
    public void setPromotionTypeId(Integer promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.service_type_id</code>. Service_type_id for the promotion-service relation
     */
    @Override
    public Integer getServiceTypeId() {
        return this.serviceTypeId;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.service_type_id</code>. Service_type_id for the promotion-service relation
     */
    @Override
    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.status_id</code>. Status id for the promotion-service relation type
     */
    @Override
    public Integer getStatusId() {
        return this.statusId;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.status_id</code>. Status id for the promotion-service relation type
     */
    @Override
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.input_date</code>. Date on which the record was created
     */
    @Override
    public LocalDateTime getInputDate() {
        return this.inputDate;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.input_date</code>. Date on which the record was created
     */
    @Override
    public void setInputDate(LocalDateTime inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.input_user</code>. User who was modified the record
     */
    @Override
    public String getInputUser() {
        return this.inputUser;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.input_user</code>. User who was modified the record
     */
    @Override
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public LocalDateTime getModifDate() {
        return this.modifDate;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public void setModifDate(LocalDateTime modifDate) {
        this.modifDate = modifDate;
    }

    /**
     * Getter for <code>public.ct_promo_serv_type.modif_user</code>.
     */
    @Override
    public String getModifUser() {
        return this.modifUser;
    }

    /**
     * Setter for <code>public.ct_promo_serv_type.modif_user</code>.
     */
    @Override
    public void setModifUser(String modifUser) {
        this.modifUser = modifUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CtPromoServType (");

        sb.append(promoServTypeId);
        sb.append(", ").append(promotionTypeId);
        sb.append(", ").append(serviceTypeId);
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
}
