/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.interfaces;


import java.io.Serializable;


/**
 * Table that stores the payment types for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IPtPaymentMethod extends Serializable {

    /**
     * Setter for <code>public.pt_payment_method.payment_method_id</code>. Internal identifier of the payment method
     */
    public void setPaymentMethodId(Integer value);

    /**
     * Getter for <code>public.pt_payment_method.payment_method_id</code>. Internal identifier of the payment method
     */
    public Integer getPaymentMethodId();

    /**
     * Setter for <code>public.pt_payment_method.code</code>. Code of the payment method
     */
    public void setCode(String value);

    /**
     * Getter for <code>public.pt_payment_method.code</code>. Code of the payment method
     */
    public String getCode();

    /**
     * Setter for <code>public.pt_payment_method.name</code>. Name of the payment method
     */
    public void setName(String value);

    /**
     * Getter for <code>public.pt_payment_method.name</code>. Name of the payment method
     */
    public String getName();

    /**
     * Setter for <code>public.pt_payment_method.description</code>. Description for the payment method
     */
    public void setDescription(String value);

    /**
     * Getter for <code>public.pt_payment_method.description</code>. Description for the payment method
     */
    public String getDescription();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IPtPaymentMethod
     */
    public void from(IPtPaymentMethod from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IPtPaymentMethod
     */
    public <E extends IPtPaymentMethod> E into(E into);
}