/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables;


import com.comasw.model.Keys;
import com.comasw.model.Public;
import com.comasw.model.tables.records.PtDiscountTypeRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Table that stores the discount types for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PtDiscountType extends TableImpl<PtDiscountTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.pt_discount_type</code>
     */
    public static final PtDiscountType PT_DISCOUNT_TYPE = new PtDiscountType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PtDiscountTypeRecord> getRecordType() {
        return PtDiscountTypeRecord.class;
    }

    /**
     * The column <code>public.pt_discount_type.discount_type_id</code>. Internal identifier of the discount type
     */
    public final TableField<PtDiscountTypeRecord, Integer> DISCOUNT_TYPE_ID = createField(DSL.name("discount_type_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("nextval('seq_discount_type_id'::regclass)", SQLDataType.INTEGER)), this, "Internal identifier of the discount type");

    /**
     * The column <code>public.pt_discount_type.code</code>. Code of the discount type
     */
    public final TableField<PtDiscountTypeRecord, String> CODE = createField(DSL.name("code"), SQLDataType.VARCHAR(10).nullable(false), this, "Code of the discount type");

    /**
     * The column <code>public.pt_discount_type.name</code>. Name of the discount type
     */
    public final TableField<PtDiscountTypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "Name of the discount type");

    /**
     * The column <code>public.pt_discount_type.description</code>. Description for the discount type
     */
    public final TableField<PtDiscountTypeRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(500).nullable(false), this, "Description for the discount type");

    private PtDiscountType(Name alias, Table<PtDiscountTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private PtDiscountType(Name alias, Table<PtDiscountTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Table that stores the discount types for the application"), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.pt_discount_type</code> table reference
     */
    public PtDiscountType(String alias) {
        this(DSL.name(alias), PT_DISCOUNT_TYPE);
    }

    /**
     * Create an aliased <code>public.pt_discount_type</code> table reference
     */
    public PtDiscountType(Name alias) {
        this(alias, PT_DISCOUNT_TYPE);
    }

    /**
     * Create a <code>public.pt_discount_type</code> table reference
     */
    public PtDiscountType() {
        this(DSL.name("pt_discount_type"), null);
    }

    public <O extends Record> PtDiscountType(Table<O> child, ForeignKey<O, PtDiscountTypeRecord> key) {
        super(child, key, PT_DISCOUNT_TYPE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<PtDiscountTypeRecord> getPrimaryKey() {
        return Keys.PT_DISCOUNT_TYPE_PK;
    }

    @Override
    public List<UniqueKey<PtDiscountTypeRecord>> getKeys() {
        return Arrays.<UniqueKey<PtDiscountTypeRecord>>asList(Keys.PT_DISCOUNT_TYPE_PK, Keys.PT_DISCOUNT_TYPE_CODE_U, Keys.PT_DISCOUNT_TYPE_NAME_U);
    }

    @Override
    public PtDiscountType as(String alias) {
        return new PtDiscountType(DSL.name(alias), this);
    }

    @Override
    public PtDiscountType as(Name alias) {
        return new PtDiscountType(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PtDiscountType rename(String name) {
        return new PtDiscountType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PtDiscountType rename(Name name) {
        return new PtDiscountType(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
