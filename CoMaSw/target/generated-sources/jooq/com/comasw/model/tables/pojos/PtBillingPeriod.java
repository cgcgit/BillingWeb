/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.pojos;


import com.comasw.model.tables.interfaces.IPtBillingPeriod;


/**
 * Table that stores the billing period types for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtBillingPeriod implements IPtBillingPeriod {

    private static final long serialVersionUID = 1L;

    private Integer billingPeriodId;
    private String  code;
    private String  name;
    private String  description;
    private Short   billingPeriodDays;

    public PtBillingPeriod() {}

    public PtBillingPeriod(IPtBillingPeriod value) {
        this.billingPeriodId = value.getBillingPeriodId();
        this.code = value.getCode();
        this.name = value.getName();
        this.description = value.getDescription();
        this.billingPeriodDays = value.getBillingPeriodDays();
    }

    public PtBillingPeriod(
        Integer billingPeriodId,
        String  code,
        String  name,
        String  description,
        Short   billingPeriodDays
    ) {
        this.billingPeriodId = billingPeriodId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.billingPeriodDays = billingPeriodDays;
    }

    /**
     * Getter for <code>public.pt_billing_period.billing_period_id</code>. Internal identifier of the billing period type
     */
    @Override
    public Integer getBillingPeriodId() {
        return this.billingPeriodId;
    }

    /**
     * Setter for <code>public.pt_billing_period.billing_period_id</code>. Internal identifier of the billing period type
     */
    @Override
    public void setBillingPeriodId(Integer billingPeriodId) {
        this.billingPeriodId = billingPeriodId;
    }

    /**
     * Getter for <code>public.pt_billing_period.code</code>. Code of the billing period type
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>public.pt_billing_period.code</code>. Code of the billing period type
     */
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for <code>public.pt_billing_period.name</code>. Name of the billing period type
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.pt_billing_period.name</code>. Name of the billing period type
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.pt_billing_period.description</code>. Description for the billing period type
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.pt_billing_period.description</code>. Description for the billing period type
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>public.pt_billing_period.billing_period_days</code>. Number of days that defines the billing period
     */
    @Override
    public Short getBillingPeriodDays() {
        return this.billingPeriodDays;
    }

    /**
     * Setter for <code>public.pt_billing_period.billing_period_days</code>. Number of days that defines the billing period
     */
    @Override
    public void setBillingPeriodDays(Short billingPeriodDays) {
        this.billingPeriodDays = billingPeriodDays;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PtBillingPeriod (");

        sb.append(billingPeriodId);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(billingPeriodDays);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IPtBillingPeriod from) {
        setBillingPeriodId(from.getBillingPeriodId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
        setBillingPeriodDays(from.getBillingPeriodDays());
    }

    @Override
    public <E extends IPtBillingPeriod> E into(E into) {
        into.from(this);
        return into;
    }
}
