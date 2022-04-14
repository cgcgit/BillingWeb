/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.pojos;


import com.billingweb.model.tables.interfaces.IVwPromotionProductType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VwPromotionProductType implements IVwPromotionProductType {

    private static final long serialVersionUID = 1L;

    private Integer promoProdTypeId;
    private Integer promProdTypeStatusId;
    private String  promProdTypeStatusCode;
    private Integer productTypeId;
    private String  productTypeCode;
    private String  productTypeName;
    private String  productTypeDescription;
    private Integer productTypeStatusId;
    private String  productTypeStatusCode;
    private Integer promotionTypeId;
    private String  promotionTypeCode;
    private String  promotionTypeName;
    private String  promotionTypeDescription;
    private Integer discountTypeId;
    private String  discountCode;
    private String  discountName;
    private String  discountDescription;
    private Integer discountValue;
    private Integer promotionTypeStatusId;
    private String  promotionTypeStatusCode;

    public VwPromotionProductType() {}

    public VwPromotionProductType(IVwPromotionProductType value) {
        this.promoProdTypeId = value.getPromoProdTypeId();
        this.promProdTypeStatusId = value.getPromProdTypeStatusId();
        this.promProdTypeStatusCode = value.getPromProdTypeStatusCode();
        this.productTypeId = value.getProductTypeId();
        this.productTypeCode = value.getProductTypeCode();
        this.productTypeName = value.getProductTypeName();
        this.productTypeDescription = value.getProductTypeDescription();
        this.productTypeStatusId = value.getProductTypeStatusId();
        this.productTypeStatusCode = value.getProductTypeStatusCode();
        this.promotionTypeId = value.getPromotionTypeId();
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

    public VwPromotionProductType(
        Integer promoProdTypeId,
        Integer promProdTypeStatusId,
        String  promProdTypeStatusCode,
        Integer productTypeId,
        String  productTypeCode,
        String  productTypeName,
        String  productTypeDescription,
        Integer productTypeStatusId,
        String  productTypeStatusCode,
        Integer promotionTypeId,
        String  promotionTypeCode,
        String  promotionTypeName,
        String  promotionTypeDescription,
        Integer discountTypeId,
        String  discountCode,
        String  discountName,
        String  discountDescription,
        Integer discountValue,
        Integer promotionTypeStatusId,
        String  promotionTypeStatusCode
    ) {
        this.promoProdTypeId = promoProdTypeId;
        this.promProdTypeStatusId = promProdTypeStatusId;
        this.promProdTypeStatusCode = promProdTypeStatusCode;
        this.productTypeId = productTypeId;
        this.productTypeCode = productTypeCode;
        this.productTypeName = productTypeName;
        this.productTypeDescription = productTypeDescription;
        this.productTypeStatusId = productTypeStatusId;
        this.productTypeStatusCode = productTypeStatusCode;
        this.promotionTypeId = promotionTypeId;
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
     * Getter for <code>public.vw_promotion_product_type.promo_prod_type_id</code>.
     */
    @Override
    public Integer getPromoProdTypeId() {
        return this.promoProdTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.promo_prod_type_id</code>.
     */
    @Override
    public void setPromoProdTypeId(Integer promoProdTypeId) {
        this.promoProdTypeId = promoProdTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.prom_prod_type_status_id</code>.
     */
    @Override
    public Integer getPromProdTypeStatusId() {
        return this.promProdTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.prom_prod_type_status_id</code>.
     */
    @Override
    public void setPromProdTypeStatusId(Integer promProdTypeStatusId) {
        this.promProdTypeStatusId = promProdTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.prom_prod_type_status_code</code>.
     */
    @Override
    public String getPromProdTypeStatusCode() {
        return this.promProdTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.prom_prod_type_status_code</code>.
     */
    @Override
    public void setPromProdTypeStatusCode(String promProdTypeStatusCode) {
        this.promProdTypeStatusCode = promProdTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.product_type_id</code>.
     */
    @Override
    public Integer getProductTypeId() {
        return this.productTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.product_type_id</code>.
     */
    @Override
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.product_type_code</code>.
     */
    @Override
    public String getProductTypeCode() {
        return this.productTypeCode;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.product_type_code</code>.
     */
    @Override
    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.product_type_name</code>.
     */
    @Override
    public String getProductTypeName() {
        return this.productTypeName;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.product_type_name</code>.
     */
    @Override
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.product_type_description</code>.
     */
    @Override
    public String getProductTypeDescription() {
        return this.productTypeDescription;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.product_type_description</code>.
     */
    @Override
    public void setProductTypeDescription(String productTypeDescription) {
        this.productTypeDescription = productTypeDescription;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.product_type_status_id</code>.
     */
    @Override
    public Integer getProductTypeStatusId() {
        return this.productTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.product_type_status_id</code>.
     */
    @Override
    public void setProductTypeStatusId(Integer productTypeStatusId) {
        this.productTypeStatusId = productTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.product_type_status_code</code>.
     */
    @Override
    public String getProductTypeStatusCode() {
        return this.productTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.product_type_status_code</code>.
     */
    @Override
    public void setProductTypeStatusCode(String productTypeStatusCode) {
        this.productTypeStatusCode = productTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.promotion_type_id</code>.
     */
    @Override
    public Integer getPromotionTypeId() {
        return this.promotionTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.promotion_type_id</code>.
     */
    @Override
    public void setPromotionTypeId(Integer promotionTypeId) {
        this.promotionTypeId = promotionTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.promotion_type_code</code>.
     */
    @Override
    public String getPromotionTypeCode() {
        return this.promotionTypeCode;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.promotion_type_code</code>.
     */
    @Override
    public void setPromotionTypeCode(String promotionTypeCode) {
        this.promotionTypeCode = promotionTypeCode;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.promotion_type_name</code>.
     */
    @Override
    public String getPromotionTypeName() {
        return this.promotionTypeName;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.promotion_type_name</code>.
     */
    @Override
    public void setPromotionTypeName(String promotionTypeName) {
        this.promotionTypeName = promotionTypeName;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.promotion_type_description</code>.
     */
    @Override
    public String getPromotionTypeDescription() {
        return this.promotionTypeDescription;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.promotion_type_description</code>.
     */
    @Override
    public void setPromotionTypeDescription(String promotionTypeDescription) {
        this.promotionTypeDescription = promotionTypeDescription;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.discount_type_id</code>.
     */
    @Override
    public Integer getDiscountTypeId() {
        return this.discountTypeId;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.discount_type_id</code>.
     */
    @Override
    public void setDiscountTypeId(Integer discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.discount_code</code>.
     */
    @Override
    public String getDiscountCode() {
        return this.discountCode;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.discount_code</code>.
     */
    @Override
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.discount_name</code>.
     */
    @Override
    public String getDiscountName() {
        return this.discountName;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.discount_name</code>.
     */
    @Override
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.discount_description</code>.
     */
    @Override
    public String getDiscountDescription() {
        return this.discountDescription;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.discount_description</code>.
     */
    @Override
    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.discount_value</code>.
     */
    @Override
    public Integer getDiscountValue() {
        return this.discountValue;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.discount_value</code>.
     */
    @Override
    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.promotion_type_status_id</code>.
     */
    @Override
    public Integer getPromotionTypeStatusId() {
        return this.promotionTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.promotion_type_status_id</code>.
     */
    @Override
    public void setPromotionTypeStatusId(Integer promotionTypeStatusId) {
        this.promotionTypeStatusId = promotionTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_promotion_product_type.promotion_type_status_code</code>.
     */
    @Override
    public String getPromotionTypeStatusCode() {
        return this.promotionTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_promotion_product_type.promotion_type_status_code</code>.
     */
    @Override
    public void setPromotionTypeStatusCode(String promotionTypeStatusCode) {
        this.promotionTypeStatusCode = promotionTypeStatusCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VwPromotionProductType (");

        sb.append(promoProdTypeId);
        sb.append(", ").append(promProdTypeStatusId);
        sb.append(", ").append(promProdTypeStatusCode);
        sb.append(", ").append(productTypeId);
        sb.append(", ").append(productTypeCode);
        sb.append(", ").append(productTypeName);
        sb.append(", ").append(productTypeDescription);
        sb.append(", ").append(productTypeStatusId);
        sb.append(", ").append(productTypeStatusCode);
        sb.append(", ").append(promotionTypeId);
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
    public void from(IVwPromotionProductType from) {
        setPromoProdTypeId(from.getPromoProdTypeId());
        setPromProdTypeStatusId(from.getPromProdTypeStatusId());
        setPromProdTypeStatusCode(from.getPromProdTypeStatusCode());
        setProductTypeId(from.getProductTypeId());
        setProductTypeCode(from.getProductTypeCode());
        setProductTypeName(from.getProductTypeName());
        setProductTypeDescription(from.getProductTypeDescription());
        setProductTypeStatusId(from.getProductTypeStatusId());
        setProductTypeStatusCode(from.getProductTypeStatusCode());
        setPromotionTypeId(from.getPromotionTypeId());
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
    public <E extends IVwPromotionProductType> E into(E into) {
        into.from(this);
        return into;
    }
}
