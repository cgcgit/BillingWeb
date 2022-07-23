/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.pojos;


import com.comasw.model.tables.interfaces.IVwPromotionServiceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VwPromotionServiceType implements IVwPromotionServiceType {

    private static final long serialVersionUID = 1L;

    private Integer       promoServTypeId;
    private Integer       promoServTypeStatusId;
    private String        promoServTypeStatusCode;
    private Integer       serviceTypeId;
    private String        serviceTypeCode;
    private String        serviceTypeName;
    private String        serviceTypeDescription;
    private Integer       serviceTypeStatusId;
    private String        serviceTypeStatusCode;
    private Integer       promotionTypeId;
    private LocalDateTime promotionTypeStartDate;
    private LocalDateTime promotionTypeEndDate;
    private String        promotionTypeCode;
    private String        promotionTypeName;
    private String        promotionTypeDescription;
    private Integer       discountTypeId;
    private String        discountCode;
    private String        discountName;
    private String        discountDescription;
    private BigDecimal    discountValue;
    private Integer       promotionTypeStatusId;
    private String        promotionTypeStatusCode;

    public VwPromotionServiceType() {}

    public VwPromotionServiceType(IVwPromotionServiceType value) {
        this.promoServTypeId = value.getPromoServTypeId();
        this.promoServTypeStatusId = value.getPromoServTypeStatusId();
        this.promoServTypeStatusCode = value.getPromoServTypeStatusCode();
        this.serviceTypeId = value.getServiceTypeId();
        this.serviceTypeCode = value.getServiceTypeCode();
        this.serviceTypeName = value.getServiceTypeName();
        this.serviceTypeDescription = value.getServiceTypeDescription();
        this.serviceTypeStatusId = value.getServiceTypeStatusId();
        this.serviceTypeStatusCode = value.getServiceTypeStatusCode();
        this.promotionTypeId = value.getPromotionTypeId();
        this.promotionTypeStartDate = value.getPromotionTypeStartDate();
        this.promotionTypeEndDate = value.getPromotionTypeEndDate();
        this.promotionTypeCode = value.getPromotionTypeCode();
        this.promotionTypeName = value.getPromotionTypeName();
        this.promotionTypeDescription = value.getPromotionTypeDescription();
        this.discountTypeId = value.getDiscountTypeId();
        this.discountCode = value.getDiscountCode();
        this.discountName = value.getDiscountName();
        this.discountDescription = value.getDiscountDescription();
        this.discountValue = value.getDiscountValue();
        this.promotionTypeStatusId = value.getPromotionTypeStatusId();
        this.promotionTypeStatusCode = value.getPromotionTypeStatusCode();
    }

    public VwPromotionServiceType(
        Integer       promoServTypeId,
        Integer       promoServTypeStatusId,
        String        promoServTypeStatusCode,
        Integer       serviceTypeId,
        String        serviceTypeCode,
        String        serviceTypeName,
        String        serviceTypeDescription,
        Integer       serviceTypeStatusId,
        String        serviceTypeStatusCode,
        Integer       promotionTypeId,
        LocalDateTime promotionTypeStartDate,
        LocalDateTime promotionTypeEndDate,
        String        promotionTypeCode,
        String        promotionTypeName,
        String        promotionTypeDescription,
        Integer       discountTypeId,
        String        discountCode,
        String        discountName,
        String        discountDescription,
        BigDecimal    discountValue,
        Integer       promotionTypeStatusId,
        String        promotionTypeStatusCode
    ) {
        this.promoServTypeId = promoServTypeId;
        this.promoServTypeStatusId = promoServTypeStatusId;
        this.promoServTypeStatusCode = promoServTypeStatusCode;
        this.serviceTypeId = serviceTypeId;
        this.serviceTypeCode = serviceTypeCode;
        this.serviceTypeName = serviceTypeName;
        this.serviceTypeDescription = serviceTypeDescription;
        this.serviceTypeStatusId = serviceTypeStatusId;
        this.serviceTypeStatusCode = serviceTypeStatusCode;
        this.promotionTypeId = promotionTypeId;
        this.promotionTypeStartDate = promotionTypeStartDate;
        this.promotionTypeEndDate = promotionTypeEndDate;
        this.promotionTypeCode = promotionTypeCode;
        this.promotionTypeName = promotionTypeName;
        this.promotionTypeDescription = promotionTypeDescription;
        this.discountTypeId = discountTypeId;
        this.discountCode = discountCode;
        this.discountName = discountName;
        this.discountDescription = discountDescription;
        this.discountValue = discountValue;
        this.promotionTypeStatusId = promotionTypeStatusId;
        this.promotionTypeStatusCode = promotionTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promo_serv_type_id</code>.
     */
    @Override
    public Integer getPromoServTypeId() {
        return this.promoServTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promo_serv_type_id</code>.
     */
    @Override
    public void setPromoServTypeId(Integer promoServTypeId) {
        this.promoServTypeId = promoServTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promo_serv_type_status_id</code>.
     */
    @Override
    public Integer getPromoServTypeStatusId() {
        return this.promoServTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promo_serv_type_status_id</code>.
     */
    @Override
    public void setPromoServTypeStatusId(Integer promoServTypeStatusId) {
        this.promoServTypeStatusId = promoServTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promo_serv_type_status_code</code>.
     */
    @Override
    public String getPromoServTypeStatusCode() {
        return this.promoServTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promo_serv_type_status_code</code>.
     */
    @Override
    public void setPromoServTypeStatusCode(String promoServTypeStatusCode) {
        this.promoServTypeStatusCode = promoServTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.service_type_id</code>.
     */
    @Override
    public Integer getServiceTypeId() {
        return this.serviceTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.service_type_id</code>.
     */
    @Override
    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.service_type_code</code>.
     */
    @Override
    public String getServiceTypeCode() {
        return this.serviceTypeCode;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.service_type_code</code>.
     */
    @Override
    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.service_type_name</code>.
     */
    @Override
    public String getServiceTypeName() {
        return this.serviceTypeName;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.service_type_name</code>.
     */
    @Override
    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.service_type_description</code>.
     */
    @Override
    public String getServiceTypeDescription() {
        return this.serviceTypeDescription;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.service_type_description</code>.
     */
    @Override
    public void setServiceTypeDescription(String serviceTypeDescription) {
        this.serviceTypeDescription = serviceTypeDescription;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.service_type_status_id</code>.
     */
    @Override
    public Integer getServiceTypeStatusId() {
        return this.serviceTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.service_type_status_id</code>.
     */
    @Override
    public void setServiceTypeStatusId(Integer serviceTypeStatusId) {
        this.serviceTypeStatusId = serviceTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.service_type_status_code</code>.
     */
    @Override
    public String getServiceTypeStatusCode() {
        return this.serviceTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.service_type_status_code</code>.
     */
    @Override
    public void setServiceTypeStatusCode(String serviceTypeStatusCode) {
        this.serviceTypeStatusCode = serviceTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_id</code>.
     */
    @Override
    public Integer getPromotionTypeId() {
        return this.promotionTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_id</code>.
     */
    @Override
    public void setPromotionTypeId(Integer promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_start_date</code>.
     */
    @Override
    public LocalDateTime getPromotionTypeStartDate() {
        return this.promotionTypeStartDate;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_start_date</code>.
     */
    @Override
    public void setPromotionTypeStartDate(LocalDateTime promotionTypeStartDate) {
        this.promotionTypeStartDate = promotionTypeStartDate;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_end_date</code>.
     */
    @Override
    public LocalDateTime getPromotionTypeEndDate() {
        return this.promotionTypeEndDate;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_end_date</code>.
     */
    @Override
    public void setPromotionTypeEndDate(LocalDateTime promotionTypeEndDate) {
        this.promotionTypeEndDate = promotionTypeEndDate;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_code</code>.
     */
    @Override
    public String getPromotionTypeCode() {
        return this.promotionTypeCode;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_code</code>.
     */
    @Override
    public void setPromotionTypeCode(String promotionTypeCode) {
        this.promotionTypeCode = promotionTypeCode;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_name</code>.
     */
    @Override
    public String getPromotionTypeName() {
        return this.promotionTypeName;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_name</code>.
     */
    @Override
    public void setPromotionTypeName(String promotionTypeName) {
        this.promotionTypeName = promotionTypeName;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_description</code>.
     */
    @Override
    public String getPromotionTypeDescription() {
        return this.promotionTypeDescription;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_description</code>.
     */
    @Override
    public void setPromotionTypeDescription(String promotionTypeDescription) {
        this.promotionTypeDescription = promotionTypeDescription;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.discount_type_id</code>.
     */
    @Override
    public Integer getDiscountTypeId() {
        return this.discountTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.discount_type_id</code>.
     */
    @Override
    public void setDiscountTypeId(Integer discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.discount_code</code>.
     */
    @Override
    public String getDiscountCode() {
        return this.discountCode;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.discount_code</code>.
     */
    @Override
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.discount_name</code>.
     */
    @Override
    public String getDiscountName() {
        return this.discountName;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.discount_name</code>.
     */
    @Override
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.discount_description</code>.
     */
    @Override
    public String getDiscountDescription() {
        return this.discountDescription;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.discount_description</code>.
     */
    @Override
    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.discount_value</code>.
     */
    @Override
    public BigDecimal getDiscountValue() {
        return this.discountValue;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.discount_value</code>.
     */
    @Override
    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_status_id</code>.
     */
    @Override
    public Integer getPromotionTypeStatusId() {
        return this.promotionTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_status_id</code>.
     */
    @Override
    public void setPromotionTypeStatusId(Integer promotionTypeStatusId) {
        this.promotionTypeStatusId = promotionTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_promotion_service_type.promotion_type_status_code</code>.
     */
    @Override
    public String getPromotionTypeStatusCode() {
        return this.promotionTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_promotion_service_type.promotion_type_status_code</code>.
     */
    @Override
    public void setPromotionTypeStatusCode(String promotionTypeStatusCode) {
        this.promotionTypeStatusCode = promotionTypeStatusCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VwPromotionServiceType (");

        sb.append(promoServTypeId);
        sb.append(", ").append(promoServTypeStatusId);
        sb.append(", ").append(promoServTypeStatusCode);
        sb.append(", ").append(serviceTypeId);
        sb.append(", ").append(serviceTypeCode);
        sb.append(", ").append(serviceTypeName);
        sb.append(", ").append(serviceTypeDescription);
        sb.append(", ").append(serviceTypeStatusId);
        sb.append(", ").append(serviceTypeStatusCode);
        sb.append(", ").append(promotionTypeId);
        sb.append(", ").append(promotionTypeStartDate);
        sb.append(", ").append(promotionTypeEndDate);
        sb.append(", ").append(promotionTypeCode);
        sb.append(", ").append(promotionTypeName);
        sb.append(", ").append(promotionTypeDescription);
        sb.append(", ").append(discountTypeId);
        sb.append(", ").append(discountCode);
        sb.append(", ").append(discountName);
        sb.append(", ").append(discountDescription);
        sb.append(", ").append(discountValue);
        sb.append(", ").append(promotionTypeStatusId);
        sb.append(", ").append(promotionTypeStatusCode);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVwPromotionServiceType from) {
        setPromoServTypeId(from.getPromoServTypeId());
        setPromoServTypeStatusId(from.getPromoServTypeStatusId());
        setPromoServTypeStatusCode(from.getPromoServTypeStatusCode());
        setServiceTypeId(from.getServiceTypeId());
        setServiceTypeCode(from.getServiceTypeCode());
        setServiceTypeName(from.getServiceTypeName());
        setServiceTypeDescription(from.getServiceTypeDescription());
        setServiceTypeStatusId(from.getServiceTypeStatusId());
        setServiceTypeStatusCode(from.getServiceTypeStatusCode());
        setPromotionTypeId(from.getPromotionTypeId());
        setPromotionTypeStartDate(from.getPromotionTypeStartDate());
        setPromotionTypeEndDate(from.getPromotionTypeEndDate());
        setPromotionTypeCode(from.getPromotionTypeCode());
        setPromotionTypeName(from.getPromotionTypeName());
        setPromotionTypeDescription(from.getPromotionTypeDescription());
        setDiscountTypeId(from.getDiscountTypeId());
        setDiscountCode(from.getDiscountCode());
        setDiscountName(from.getDiscountName());
        setDiscountDescription(from.getDiscountDescription());
        setDiscountValue(from.getDiscountValue());
        setPromotionTypeStatusId(from.getPromotionTypeStatusId());
        setPromotionTypeStatusCode(from.getPromotionTypeStatusCode());
    }

    @Override
    public <E extends IVwPromotionServiceType> E into(E into) {
        into.from(this);
        return into;
    }
}
