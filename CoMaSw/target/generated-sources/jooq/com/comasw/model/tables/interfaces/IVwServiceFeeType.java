/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.interfaces;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IVwServiceFeeType extends Serializable {

    /**
     * Setter for <code>public.vw_service_fee_type.serv_fee_type_id</code>.
     */
    public void setServFeeTypeId(Integer value);

    /**
     * Getter for <code>public.vw_service_fee_type.serv_fee_type_id</code>.
     */
    public Integer getServFeeTypeId();

    /**
     * Setter for <code>public.vw_service_fee_type.serv_fee_type_status_id</code>.
     */
    public void setServFeeTypeStatusId(Integer value);

    /**
     * Getter for <code>public.vw_service_fee_type.serv_fee_type_status_id</code>.
     */
    public Integer getServFeeTypeStatusId();

    /**
     * Setter for <code>public.vw_service_fee_type.serv_fee_type_status_code</code>.
     */
    public void setServFeeTypeStatusCode(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.serv_fee_type_status_code</code>.
     */
    public String getServFeeTypeStatusCode();

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_id</code>.
     */
    public void setServiceTypeId(Integer value);

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_id</code>.
     */
    public Integer getServiceTypeId();

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_code</code>.
     */
    public void setServiceTypeCode(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_code</code>.
     */
    public String getServiceTypeCode();

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_name</code>.
     */
    public void setServiceTypeName(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_name</code>.
     */
    public String getServiceTypeName();

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_description</code>.
     */
    public void setServiceTypeDescription(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_description</code>.
     */
    public String getServiceTypeDescription();

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_status_id</code>.
     */
    public void setServiceTypeStatusId(Integer value);

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_status_id</code>.
     */
    public Integer getServiceTypeStatusId();

    /**
     * Setter for <code>public.vw_service_fee_type.service_type_status_code</code>.
     */
    public void setServiceTypeStatusCode(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.service_type_status_code</code>.
     */
    public String getServiceTypeStatusCode();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_id</code>.
     */
    public void setFeeTypeId(Integer value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_id</code>.
     */
    public Integer getFeeTypeId();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_start_date</code>.
     */
    public void setFeeTypeStartDate(LocalDateTime value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_start_date</code>.
     */
    public LocalDateTime getFeeTypeStartDate();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_end_date</code>.
     */
    public void setFeeTypeEndDate(LocalDateTime value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_end_date</code>.
     */
    public LocalDateTime getFeeTypeEndDate();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_code</code>.
     */
    public void setFeeTypeCode(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_code</code>.
     */
    public String getFeeTypeCode();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_name</code>.
     */
    public void setFeeTypeName(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_name</code>.
     */
    public String getFeeTypeName();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_description</code>.
     */
    public void setFeeTypeDescription(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_description</code>.
     */
    public String getFeeTypeDescription();

    /**
     * Setter for <code>public.vw_service_fee_type.prorate</code>.
     */
    public void setProrate(Boolean value);

    /**
     * Getter for <code>public.vw_service_fee_type.prorate</code>.
     */
    public Boolean getProrate();

    /**
     * Setter for <code>public.vw_service_fee_type.price</code>.
     */
    public void setPrice(BigDecimal value);

    /**
     * Getter for <code>public.vw_service_fee_type.price</code>.
     */
    public BigDecimal getPrice();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_status_id</code>.
     */
    public void setFeeTypeStatusId(Integer value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_status_id</code>.
     */
    public Integer getFeeTypeStatusId();

    /**
     * Setter for <code>public.vw_service_fee_type.fee_type_status_code</code>.
     */
    public void setFeeTypeStatusCode(String value);

    /**
     * Getter for <code>public.vw_service_fee_type.fee_type_status_code</code>.
     */
    public String getFeeTypeStatusCode();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IVwServiceFeeType
     */
    public void from(IVwServiceFeeType from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IVwServiceFeeType
     */
    public <E extends IVwServiceFeeType> E into(E into);
}
