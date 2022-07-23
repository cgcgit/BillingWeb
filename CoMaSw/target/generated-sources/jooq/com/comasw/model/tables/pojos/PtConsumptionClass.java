/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.pojos;


import com.comasw.model.tables.interfaces.IPtConsumptionClass;


/**
 * Table that stores the consumtion class of the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtConsumptionClass implements IPtConsumptionClass {

    private static final long serialVersionUID = 1L;

    private Integer consumptionClassId;
    private String  code;
    private String  name;
    private String  description;

    public PtConsumptionClass() {}

    public PtConsumptionClass(IPtConsumptionClass value) {
        this.consumptionClassId = value.getConsumptionClassId();
        this.code = value.getCode();
        this.name = value.getName();
        this.description = value.getDescription();
    }

    public PtConsumptionClass(
        Integer consumptionClassId,
        String  code,
        String  name,
        String  description
    ) {
        this.consumptionClassId = consumptionClassId;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    /**
     * Getter for <code>public.pt_consumption_class.consumption_class_id</code>. Internal identifier of the consumption class
     */
    @Override
    public Integer getConsumptionClassId() {
        return this.consumptionClassId;
    }

    /**
     * Setter for <code>public.pt_consumption_class.consumption_class_id</code>. Internal identifier of the consumption class
     */
    @Override
    public void setConsumptionClassId(Integer consumptionClassId) {
        this.consumptionClassId = consumptionClassId;
    }

    /**
     * Getter for <code>public.pt_consumption_class.code</code>. Code of the consumption class
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>public.pt_consumption_class.code</code>. Code of the consumption class
     */
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for <code>public.pt_consumption_class.name</code>. Name of the consumption class
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.pt_consumption_class.name</code>. Name of the consumption class
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.pt_consumption_class.description</code>. Description for the consumption class
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.pt_consumption_class.description</code>. Description for the consumption class
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PtConsumptionClass (");

        sb.append(consumptionClassId);
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
    public void from(IPtConsumptionClass from) {
        setConsumptionClassId(from.getConsumptionClassId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
    }

    @Override
    public <E extends IPtConsumptionClass> E into(E into) {
        into.from(this);
        return into;
    }
}
