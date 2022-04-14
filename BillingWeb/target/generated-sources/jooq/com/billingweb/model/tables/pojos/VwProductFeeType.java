/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.pojos;


import com.billingweb.model.tables.interfaces.IVwProductFeeType;

import java.math.BigDecimal;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VwProductFeeType implements IVwProductFeeType {

    private static final long serialVersionUID = 1L;

    private Integer    prodFeeTypeId;
    private Integer    prodFeeTypeStatusId;
    private String     prodFeeTypeStatusCode;
    private Integer    productTypeId;
    private String     productTypeCode;
    private String     productTypeName;
    private String     productTypeDescription;
    private Integer    productTypeStatusId;
    private String     productTypeStatusCode;
    private Integer    feeTypeId;
    private String     feeTypeCode;
    private String     feeTypeName;
    private String     feeTypeDescription;
    private Boolean    prorate;
    private BigDecimal price;
    private Integer    feeTypeStatusId;
    private String     feeTypeStatusCode;

    public VwProductFeeType() {}

    public VwProductFeeType(IVwProductFeeType value) {
        this.prodFeeTypeId = value.getProdFeeTypeId();
        this.prodFeeTypeStatusId = value.getProdFeeTypeStatusId();
        this.prodFeeTypeStatusCode = value.getProdFeeTypeStatusCode();
        this.productTypeId = value.getProductTypeId();
        this.productTypeCode = value.getProductTypeCode();
        this.productTypeName = value.getProductTypeName();
        this.productTypeDescription = value.getProductTypeDescription();
        this.productTypeStatusId = value.getProductTypeStatusId();
        this.productTypeStatusCode = value.getProductTypeStatusCode();
        this.feeTypeId = value.getFeeTypeId();
        this.feeTypeCode = value.getFeeTypeCode();
        this.feeTypeName = value.getFeeTypeName();
        this.feeTypeDescription = value.getFeeTypeDescription();
        this.prorate = value.getProrate();
        this.price = value.getPrice();
        this.feeTypeStatusId = value.getFeeTypeStatusId();
        this.feeTypeStatusCode = value.getFeeTypeStatusCode();
    }

    public VwProductFeeType(
        Integer    prodFeeTypeId,
        Integer    prodFeeTypeStatusId,
        String     prodFeeTypeStatusCode,
        Integer    productTypeId,
        String     productTypeCode,
        String     productTypeName,
        String     productTypeDescription,
        Integer    productTypeStatusId,
        String     productTypeStatusCode,
        Integer    feeTypeId,
        String     feeTypeCode,
        String     feeTypeName,
        String     feeTypeDescription,
        Boolean    prorate,
        BigDecimal price,
        Integer    feeTypeStatusId,
        String     feeTypeStatusCode
    ) {
        this.prodFeeTypeId = prodFeeTypeId;
        this.prodFeeTypeStatusId = prodFeeTypeStatusId;
        this.prodFeeTypeStatusCode = prodFeeTypeStatusCode;
        this.productTypeId = productTypeId;
        this.productTypeCode = productTypeCode;
        this.productTypeName = productTypeName;
        this.productTypeDescription = productTypeDescription;
        this.productTypeStatusId = productTypeStatusId;
        this.productTypeStatusCode = productTypeStatusCode;
        this.feeTypeId = feeTypeId;
        this.feeTypeCode = feeTypeCode;
        this.feeTypeName = feeTypeName;
        this.feeTypeDescription = feeTypeDescription;
        this.prorate = prorate;
        this.price = price;
        this.feeTypeStatusId = feeTypeStatusId;
        this.feeTypeStatusCode = feeTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.prod_fee_type_id</code>.
     */
    @Override
    public Integer getProdFeeTypeId() {
        return this.prodFeeTypeId;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.prod_fee_type_id</code>.
     */
    @Override
    public void setProdFeeTypeId(Integer prodFeeTypeId) {
        this.prodFeeTypeId = prodFeeTypeId;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.prod_fee_type_status_id</code>.
     */
    @Override
    public Integer getProdFeeTypeStatusId() {
        return this.prodFeeTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.prod_fee_type_status_id</code>.
     */
    @Override
    public void setProdFeeTypeStatusId(Integer prodFeeTypeStatusId) {
        this.prodFeeTypeStatusId = prodFeeTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.prod_fee_type_status_code</code>.
     */
    @Override
    public String getProdFeeTypeStatusCode() {
        return this.prodFeeTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.prod_fee_type_status_code</code>.
     */
    @Override
    public void setProdFeeTypeStatusCode(String prodFeeTypeStatusCode) {
        this.prodFeeTypeStatusCode = prodFeeTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.product_type_id</code>.
     */
    @Override
    public Integer getProductTypeId() {
        return this.productTypeId;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.product_type_id</code>.
     */
    @Override
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.product_type_code</code>.
     */
    @Override
    public String getProductTypeCode() {
        return this.productTypeCode;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.product_type_code</code>.
     */
    @Override
    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.product_type_name</code>.
     */
    @Override
    public String getProductTypeName() {
        return this.productTypeName;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.product_type_name</code>.
     */
    @Override
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.product_type_description</code>.
     */
    @Override
    public String getProductTypeDescription() {
        return this.productTypeDescription;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.product_type_description</code>.
     */
    @Override
    public void setProductTypeDescription(String productTypeDescription) {
        this.productTypeDescription = productTypeDescription;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.product_type_status_id</code>.
     */
    @Override
    public Integer getProductTypeStatusId() {
        return this.productTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.product_type_status_id</code>.
     */
    @Override
    public void setProductTypeStatusId(Integer productTypeStatusId) {
        this.productTypeStatusId = productTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.product_type_status_code</code>.
     */
    @Override
    public String getProductTypeStatusCode() {
        return this.productTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.product_type_status_code</code>.
     */
    @Override
    public void setProductTypeStatusCode(String productTypeStatusCode) {
        this.productTypeStatusCode = productTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.fee_type_id</code>.
     */
    @Override
    public Integer getFeeTypeId() {
        return this.feeTypeId;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.fee_type_id</code>.
     */
    @Override
    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.fee_type_code</code>.
     */
    @Override
    public String getFeeTypeCode() {
        return this.feeTypeCode;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.fee_type_code</code>.
     */
    @Override
    public void setFeeTypeCode(String feeTypeCode) {
        this.feeTypeCode = feeTypeCode;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.fee_type_name</code>.
     */
    @Override
    public String getFeeTypeName() {
        return this.feeTypeName;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.fee_type_name</code>.
     */
    @Override
    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.fee_type_description</code>.
     */
    @Override
    public String getFeeTypeDescription() {
        return this.feeTypeDescription;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.fee_type_description</code>.
     */
    @Override
    public void setFeeTypeDescription(String feeTypeDescription) {
        this.feeTypeDescription = feeTypeDescription;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.prorate</code>.
     */
    @Override
    public Boolean getProrate() {
        return this.prorate;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.prorate</code>.
     */
    @Override
    public void setProrate(Boolean prorate) {
        this.prorate = prorate;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.price</code>.
     */
    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.price</code>.
     */
    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.fee_type_status_id</code>.
     */
    @Override
    public Integer getFeeTypeStatusId() {
        return this.feeTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.fee_type_status_id</code>.
     */
    @Override
    public void setFeeTypeStatusId(Integer feeTypeStatusId) {
        this.feeTypeStatusId = feeTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_product_fee_type.fee_type_status_code</code>.
     */
    @Override
    public String getFeeTypeStatusCode() {
        return this.feeTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_product_fee_type.fee_type_status_code</code>.
     */
    @Override
    public void setFeeTypeStatusCode(String feeTypeStatusCode) {
        this.feeTypeStatusCode = feeTypeStatusCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VwProductFeeType (");

        sb.append(prodFeeTypeId);
        sb.append(", ").append(prodFeeTypeStatusId);
        sb.append(", ").append(prodFeeTypeStatusCode);
        sb.append(", ").append(productTypeId);
        sb.append(", ").append(productTypeCode);
        sb.append(", ").append(productTypeName);
        sb.append(", ").append(productTypeDescription);
        sb.append(", ").append(productTypeStatusId);
        sb.append(", ").append(productTypeStatusCode);
        sb.append(", ").append(feeTypeId);
        sb.append(", ").append(feeTypeCode);
        sb.append(", ").append(feeTypeName);
        sb.append(", ").append(feeTypeDescription);
        sb.append(", ").append(prorate);
        sb.append(", ").append(price);
        sb.append(", ").append(feeTypeStatusId);
        sb.append(", ").append(feeTypeStatusCode);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IVwProductFeeType from) {
        setProdFeeTypeId(from.getProdFeeTypeId());
        setProdFeeTypeStatusId(from.getProdFeeTypeStatusId());
        setProdFeeTypeStatusCode(from.getProdFeeTypeStatusCode());
        setProductTypeId(from.getProductTypeId());
        setProductTypeCode(from.getProductTypeCode());
        setProductTypeName(from.getProductTypeName());
        setProductTypeDescription(from.getProductTypeDescription());
        setProductTypeStatusId(from.getProductTypeStatusId());
        setProductTypeStatusCode(from.getProductTypeStatusCode());
        setFeeTypeId(from.getFeeTypeId());
        setFeeTypeCode(from.getFeeTypeCode());
        setFeeTypeName(from.getFeeTypeName());
        setFeeTypeDescription(from.getFeeTypeDescription());
        setProrate(from.getProrate());
        setPrice(from.getPrice());
        setFeeTypeStatusId(from.getFeeTypeStatusId());
        setFeeTypeStatusCode(from.getFeeTypeStatusCode());
    }

    @Override
    public <E extends IVwProductFeeType> E into(E into) {
        into.from(this);
        return into;
    }
}
