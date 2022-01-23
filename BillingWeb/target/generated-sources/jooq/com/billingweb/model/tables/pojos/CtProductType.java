/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables.pojos;


import com.billingweb.model.tables.interfaces.ICtProductType;

import java.time.LocalDateTime;


/**
 * Table that stores the product types of the catalog for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtProductType implements ICtProductType {

    private static final long serialVersionUID = 1L;

    private Integer       productTypeId;
    private Integer       entityTypeId;
    private String        code;
    private String        name;
    private String        description;
    private Integer       statusId;
    private LocalDateTime inputDate;
    private String        inputUser;
    private LocalDateTime modifDate;
    private String        modifUser;

    public CtProductType() {}

    public CtProductType(ICtProductType value) {
        this.productTypeId = value.getProductTypeId();
        this.entityTypeId = value.getEntityTypeId();
        this.code = value.getCode();
        this.name = value.getName();
        this.description = value.getDescription();
        this.statusId = value.getStatusId();
        this.inputDate = value.getInputDate();
        this.inputUser = value.getInputUser();
        this.modifDate = value.getModifDate();
        this.modifUser = value.getModifUser();
    }

    public CtProductType(
        Integer       productTypeId,
        Integer       entityTypeId,
        String        code,
        String        name,
        String        description,
        Integer       statusId,
        LocalDateTime inputDate,
        String        inputUser,
        LocalDateTime modifDate,
        String        modifUser
    ) {
        this.productTypeId = productTypeId;
        this.entityTypeId = entityTypeId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.statusId = statusId;
        this.inputDate = inputDate;
        this.inputUser = inputUser;
        this.modifDate = modifDate;
        this.modifUser = modifUser;
    }

    /**
     * Getter for <code>public.ct_product_type.product_type_id</code>. Internal identifier of the product type
     */
    @Override
    public Integer getProductTypeId() {
        return this.productTypeId;
    }

    /**
     * Setter for <code>public.ct_product_type.product_type_id</code>. Internal identifier of the product type
     */
    @Override
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * Getter for <code>public.ct_product_type.entity_type_id</code>. Entity_id for the product type
     */
    @Override
    public Integer getEntityTypeId() {
        return this.entityTypeId;
    }

    /**
     * Setter for <code>public.ct_product_type.entity_type_id</code>. Entity_id for the product type
     */
    @Override
    public void setEntityTypeId(Integer entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    /**
     * Getter for <code>public.ct_product_type.code</code>. Code of the product type
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>public.ct_product_type.code</code>. Code of the product type
     */
    @Override
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for <code>public.ct_product_type.name</code>. Name of the product type
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.ct_product_type.name</code>. Name of the product type
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.ct_product_type.description</code>. Description for the product type
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.ct_product_type.description</code>. Description for the product type
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>public.ct_product_type.status_id</code>. Status id for the product type
     */
    @Override
    public Integer getStatusId() {
        return this.statusId;
    }

    /**
     * Setter for <code>public.ct_product_type.status_id</code>. Status id for the product type
     */
    @Override
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * Getter for <code>public.ct_product_type.input_date</code>. Date on which the record was created
     */
    @Override
    public LocalDateTime getInputDate() {
        return this.inputDate;
    }

    /**
     * Setter for <code>public.ct_product_type.input_date</code>. Date on which the record was created
     */
    @Override
    public void setInputDate(LocalDateTime inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * Getter for <code>public.ct_product_type.input_user</code>. User who was modified the record
     */
    @Override
    public String getInputUser() {
        return this.inputUser;
    }

    /**
     * Setter for <code>public.ct_product_type.input_user</code>. User who was modified the record
     */
    @Override
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    /**
     * Getter for <code>public.ct_product_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public LocalDateTime getModifDate() {
        return this.modifDate;
    }

    /**
     * Setter for <code>public.ct_product_type.modif_date</code>. Date of the last modification of the record
     */
    @Override
    public void setModifDate(LocalDateTime modifDate) {
        this.modifDate = modifDate;
    }

    /**
     * Getter for <code>public.ct_product_type.modif_user</code>.
     */
    @Override
    public String getModifUser() {
        return this.modifUser;
    }

    /**
     * Setter for <code>public.ct_product_type.modif_user</code>.
     */
    @Override
    public void setModifUser(String modifUser) {
        this.modifUser = modifUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CtProductType (");

        sb.append(productTypeId);
        sb.append(", ").append(entityTypeId);
        sb.append(", ").append(code);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
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
    public void from(ICtProductType from) {
        setProductTypeId(from.getProductTypeId());
        setEntityTypeId(from.getEntityTypeId());
        setCode(from.getCode());
        setName(from.getName());
        setDescription(from.getDescription());
        setStatusId(from.getStatusId());
        setInputDate(from.getInputDate());
        setInputUser(from.getInputUser());
        setModifDate(from.getModifDate());
        setModifUser(from.getModifUser());
    }

    @Override
    public <E extends ICtProductType> E into(E into) {
        into.from(this);
        return into;
    }
}
