/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.pojos;


import com.billingweb.model.tables.interfaces.IPtApplicationLevel;


/**
 * Table that stores the application level for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtApplicationLevel implements IPtApplicationLevel {

    private static final long serialVersionUID = 1L;

    private Integer applicationLevelId;
    private String  code;
    private String  name;
    private String  description;

    public PtApplicationLevel() {}

    public PtApplicationLevel(IPtApplicationLevel value) {
        this.applicationLevelId = value.getApplicationLevelId();
        this.code = value.getCode();
        this.name = value.getName();
        this.description = value.getDescription();
    }

    public PtApplicationLevel(
        Integer applicationLevelId,
        String  code,
        String  name,
        String  description
    ) {
        this.applicationLevelId = applicationLevelId;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    /**
     * Getter for <code>public.pt_application_level.application_level_id</code>. Internal identifier of the application level
     */
    @Override
    public Integer getApplicationLevelId() {
        return this.applicationLevelId;
    }

    /**
     * Setter for <code>public.pt_application_level.application_level_id</code>. Internal identifier of the application level
     */
    @Override
    public void setApplicationLevelId(Integer applicationLevelId) {
        this.applicationLevelId = applicationLevelId;
    }

    /**
     * Getter for <code>public.pt_application_level.code</code>. Code of the application level
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>public.pt_application_level.code</code>. Code of the application level
     */
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for <code>public.pt_application_level.name</code>. Name of the application level
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.pt_application_level.name</code>. Name of the application level
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.pt_application_level.description</code>. Description for the application level
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.pt_application_level.description</code>. Description for the application level
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PtApplicationLevel (");

        sb.append(applicationLevelId);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(description);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IPtApplicationLevel from) {
        setApplicationLevelId(from.getApplicationLevelId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
    }

    @Override
    public <E extends IPtApplicationLevel> E into(E into) {
        into.from(this);
        return into;
    }
}
