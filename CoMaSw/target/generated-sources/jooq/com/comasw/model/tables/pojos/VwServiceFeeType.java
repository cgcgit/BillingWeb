/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.pojos;


import com.comasw.model.tables.interfaces.IVwServiceFeeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VwServiceFeeType implements IVwServiceFeeType {

    private static final long serialVersionUID = 1L;

    private Integer       servFeeTypeId;
    private Integer       servFeeTypeStatusId;
    private String        servFeeTypeStatusCode;
    private Integer       serviceTypeId;
    private String        serviceTypeCode;
    private String        serviceTypeName;
    private String        serviceTypeDescription;
    private Integer       serviceTypeStatusId;
    private String        serviceTypeStatusCode;
    private Integer       feeTypeId;
    private LocalDateTime feeTypeStartDate;
    private LocalDateTime feeTypeEndDate;
    private String        feeTypeCode;
    private String        feeTypeName;
    private String        feeTypeDescription;
    private Boolean       prorate;
    private BigDecimal    price;
    private Integer       feeTypeStatusId;
    private String        feeTypeStatusCode;

    public VwServiceFeeType() {}

    public VwServiceFeeType(IVwServiceFeeType value) {
        this.servFeeTypeId = value.getServFeeTypeId();
        this.servFeeTypeStatusId = value.getServFeeTypeStatusId();
        this.servFeeTypeStatusCode = value.getServFeeTypeStatusCode();
        this.serviceTypeId = value.getServiceTypeId();
        this.serviceTypeCode = value.getServiceTypeCode();
        this.serviceTypeName = value.getServiceTypeName();
        this.serviceTypeDescription = value.getServiceTypeDescription();
        this.serviceTypeStatusId = value.getServiceTypeStatusId();
        this.serviceTypeStatusCode = value.getServiceTypeStatusCode();
        this.feeTypeId = value.getFeeTypeId();
        this.feeTypeStartDate = value.getFeeTypeStartDate();
        this.feeTypeEndDate = value.getFeeTypeEndDate();
        this.feeTypeCode = value.getFeeTypeCode();
        this.feeTypeName = value.getFeeTypeName();
        this.feeTypeDescription = value.getFeeTypeDescription();
        this.prorate = value.getProrate();
        this.price = value.getPrice();
        this.feeTypeStatusId = value.getFeeTypeStatusId();
        this.feeTypeStatusCode = value.getFeeTypeStatusCode();
    }

    public VwServiceFeeType(
        Integer       servFeeTypeId,
        Integer       servFeeTypeStatusId,
        String        servFeeTypeStatusCode,
        Integer       serviceTypeId,
        String        serviceTypeCode,
        String        serviceTypeName,
        String        serviceTypeDescription,
        Integer       serviceTypeStatusId,
        String        serviceTypeStatusCode,
        Integer       feeTypeId,
        LocalDateTime feeTypeStartDate,
        LocalDateTime feeTypeEndDate,
        String        feeTypeCode,
        String        feeTypeName,
        String        feeTypeDescription,
        Boolean       prorate,
        BigDecimal    price,
        Integer       feeTypeStatusId,
        String        feeTypeStatusCode
    ) {
        this.servFeeTypeId = servFeeTypeId;
        this.servFeeTypeStatusId = servFeeTypeStatusId;
        this.servFeeTypeStatusCode = servFeeTypeStatusCode;
        this.serviceTypeId = serviceTypeId;
        this.serviceTypeCode = serviceTypeCode;
        this.serviceTypeName = serviceTypeName;
        this.serviceTypeDescription = serviceTypeDescription;
        this.serviceTypeStatusId = serviceTypeStatusId;
        this.serviceTypeStatusCode = serviceTypeStatusCode;
        this.feeTypeId = feeTypeId;
        this.feeTypeStartDate = feeTypeStartDate;
        this.feeTypeEndDate = feeTypeEndDate;
        this.feeTypeCode = feeTypeCode;
        this.feeTypeName = feeTypeName;
        this.feeTypeDescription = feeTypeDescription;
        this.prorate = prorate;
        this.price = price;
        this.feeTypeStatusId = feeTypeStatusId;
        this.feeTypeStatusCode = feeTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.serv_fee_type_id</code>.
     */
    @Override
    public Integer getServFeeTypeId() {
        return this.servFeeTypeId;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.serv_fee_type_id</code>.
     */
    @Override
    public void setServFeeTypeId(Integer servFeeTypeId) {
        this.servFeeTypeId = servFeeTypeId;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.serv_fee_type_status_id</code>.
     */
    @Override
    public Integer getServFeeTypeStatusId() {
        return this.servFeeTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.serv_fee_type_status_id</code>.
     */
    @Override
    public void setServFeeTypeStatusId(Integer servFeeTypeStatusId) {
        this.servFeeTypeStatusId = servFeeTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.serv_fee_type_status_code</code>.
     */
    @Override
    public String getServFeeTypeStatusCode() {
        return this.servFeeTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.serv_fee_type_status_code</code>.
     */
    @Override
    public void setServFeeTypeStatusCode(String servFeeTypeStatusCode) {
        this.servFeeTypeStatusCode = servFeeTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_id</code>.
     */
    @Override
    public Integer getServiceTypeId() {
        return this.serviceTypeId;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_id</code>.
     */
    @Override
    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_code</code>.
     */
    @Override
    public String getServiceTypeCode() {
        return this.serviceTypeCode;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_code</code>.
     */
    @Override
    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_name</code>.
     */
    @Override
    public String getServiceTypeName() {
        return this.serviceTypeName;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_name</code>.
     */
    @Override
    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_description</code>.
     */
    @Override
    public String getServiceTypeDescription() {
        return this.serviceTypeDescription;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_description</code>.
     */
    @Override
    public void setServiceTypeDescription(String serviceTypeDescription) {
        this.serviceTypeDescription = serviceTypeDescription;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_status_id</code>.
     */
    @Override
    public Integer getServiceTypeStatusId() {
        return this.serviceTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_status_id</code>.
     */
    @Override
    public void setServiceTypeStatusId(Integer serviceTypeStatusId) {
        this.serviceTypeStatusId = serviceTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_status_code</code>.
     */
    @Override
    public String getServiceTypeStatusCode() {
        return this.serviceTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_status_code</code>.
     */
    @Override
    public void setServiceTypeStatusCode(String serviceTypeStatusCode) {
        this.serviceTypeStatusCode = serviceTypeStatusCode;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_id</code>.
     */
    @Override
    public Integer getFeeTypeId() {
        return this.feeTypeId;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_id</code>.
     */
    @Override
    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_start_date</code>.
     */
    @Override
    public LocalDateTime getFeeTypeStartDate() {
        return this.feeTypeStartDate;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_start_date</code>.
     */
    @Override
    public void setFeeTypeStartDate(LocalDateTime feeTypeStartDate) {
        this.feeTypeStartDate = feeTypeStartDate;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_end_date</code>.
     */
    @Override
    public LocalDateTime getFeeTypeEndDate() {
        return this.feeTypeEndDate;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_end_date</code>.
     */
    @Override
    public void setFeeTypeEndDate(LocalDateTime feeTypeEndDate) {
        this.feeTypeEndDate = feeTypeEndDate;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_code</code>.
     */
    @Override
    public String getFeeTypeCode() {
        return this.feeTypeCode;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_code</code>.
     */
    @Override
    public void setFeeTypeCode(String feeTypeCode) {
        this.feeTypeCode = feeTypeCode;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_name</code>.
     */
    @Override
    public String getFeeTypeName() {
        return this.feeTypeName;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_name</code>.
     */
    @Override
    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_description</code>.
     */
    @Override
    public String getFeeTypeDescription() {
        return this.feeTypeDescription;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_description</code>.
     */
    @Override
    public void setFeeTypeDescription(String feeTypeDescription) {
        this.feeTypeDescription = feeTypeDescription;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.prorate</code>.
     */
    @Override
    public Boolean getProrate() {
        return this.prorate;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.prorate</code>.
     */
    @Override
    public void setProrate(Boolean prorate) {
        this.prorate = prorate;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.price</code>.
     */
    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.price</code>.
     */
    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_status_id</code>.
     */
    @Override
    public Integer getFeeTypeStatusId() {
        return this.feeTypeStatusId;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_status_id</code>.
     */
    @Override
    public void setFeeTypeStatusId(Integer feeTypeStatusId) {
        this.feeTypeStatusId = feeTypeStatusId;
    }

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_status_code</code>.
     */
    @Override
    public String getFeeTypeStatusCode() {
        return this.feeTypeStatusCode;
    }

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_status_code</code>.
     */
    @Override
    public void setFeeTypeStatusCode(String feeTypeStatusCode) {
        this.feeTypeStatusCode = feeTypeStatusCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VwServiceFeeType (");

        sb.append(servFeeTypeId);
        sb.append(", ").append(servFeeTypeStatusId);
        sb.append(", ").append(servFeeTypeStatusCode);
        sb.append(", ").append(serviceTypeId);
        sb.append(", ").append(serviceTypeCode);
        sb.append(", ").append(serviceTypeName);
        sb.append(", ").append(serviceTypeDescription);
        sb.append(", ").append(serviceTypeStatusId);
        sb.append(", ").append(serviceTypeStatusCode);
        sb.append(", ").append(feeTypeId);
        sb.append(", ").append(feeTypeStartDate);
        sb.append(", ").append(feeTypeEndDate);
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
    public void from(IVwServiceFeeType from) {
        setServFeeTypeId(from.getServFeeTypeId());
        setServFeeTypeStatusId(from.getServFeeTypeStatusId());
        setServFeeTypeStatusCode(from.getServFeeTypeStatusCode());
        setServiceTypeId(from.getServiceTypeId());
        setServiceTypeCode(from.getServiceTypeCode());
        setServiceTypeName(from.getServiceTypeName());
        setServiceTypeDescription(from.getServiceTypeDescription());
        setServiceTypeStatusId(from.getServiceTypeStatusId());
        setServiceTypeStatusCode(from.getServiceTypeStatusCode());
        setFeeTypeId(from.getFeeTypeId());
        setFeeTypeStartDate(from.getFeeTypeStartDate());
        setFeeTypeEndDate(from.getFeeTypeEndDate());
        setFeeTypeCode(from.getFeeTypeCode());
        setFeeTypeName(from.getFeeTypeName());
        setFeeTypeDescription(from.getFeeTypeDescription());
        setProrate(from.getProrate());
        setPrice(from.getPrice());
        setFeeTypeStatusId(from.getFeeTypeStatusId());
        setFeeTypeStatusCode(from.getFeeTypeStatusCode());
    }

    @Override
    public <E extends IVwServiceFeeType> E into(E into) {
        into.from(this);
        return into;
    }
}
