/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables;


import com.billingweb.model.Keys;
import com.billingweb.model.Public;
import com.billingweb.model.tables.records.CtConsumptionTypeRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Table that stores the consumption types of the catalog for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtConsumptionType extends TableImpl<CtConsumptionTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.ct_consumption_type</code>
     */
    public static final CtConsumptionType CT_CONSUMPTION_TYPE = new CtConsumptionType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CtConsumptionTypeRecord> getRecordType() {
        return CtConsumptionTypeRecord.class;
    }

    /**
     * The column <code>public.ct_consumption_type.consumption_type_id</code>. Internal identifier of the consumption type
     */
    public final TableField<CtConsumptionTypeRecord, Integer> CONSUMPTION_TYPE_ID = createField(DSL.name("consumption_type_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("nextval('seq_consumption_type'::regclass)", SQLDataType.INTEGER)), this, "Internal identifier of the consumption type");

    /**
     * The column <code>public.ct_consumption_type.entity_type_id</code>. Entity_id for the consumption type
     */
    public final TableField<CtConsumptionTypeRecord, Integer> ENTITY_TYPE_ID = createField(DSL.name("entity_type_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("1003", SQLDataType.INTEGER)), this, "Entity_id for the consumption type");

    /**
     * The column <code>public.ct_consumption_type.code</code>. Code of the consumption type
     */
    public final TableField<CtConsumptionTypeRecord, String> CODE = createField(DSL.name("code"), SQLDataType.VARCHAR(10).nullable(false), this, "Code of the consumption type");

    /**
     * The column <code>public.ct_consumption_type.name</code>. Name of the consumption type
     */
    public final TableField<CtConsumptionTypeRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "Name of the consumption type");

    /**
     * The column <code>public.ct_consumption_type.description</code>. Description for the consumption type
     */
    public final TableField<CtConsumptionTypeRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(500).nullable(false), this, "Description for the consumption type");

    /**
     * The column <code>public.ct_consumption_type.status_id</code>. Status id for the consumption type
     */
    public final TableField<CtConsumptionTypeRecord, Integer> STATUS_ID = createField(DSL.name("status_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("1000", SQLDataType.INTEGER)), this, "Status id for the consumption type");

    /**
     * The column <code>public.ct_consumption_type.input_date</code>. Date on which the record was created
     */
    public final TableField<CtConsumptionTypeRecord, LocalDateTime> INPUT_DATE = createField(DSL.name("input_date"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("(('now'::text)::timestamp(0) with time zone)::timestamp without time zone", SQLDataType.LOCALDATETIME)), this, "Date on which the record was created");

    /**
     * The column <code>public.ct_consumption_type.input_user</code>. User who was modified the record
     */
    public final TableField<CtConsumptionTypeRecord, String> INPUT_USER = createField(DSL.name("input_user"), SQLDataType.VARCHAR(10).nullable(false), this, "User who was modified the record");

    /**
     * The column <code>public.ct_consumption_type.modif_date</code>. Date of the last modification of the record
     */
    public final TableField<CtConsumptionTypeRecord, LocalDateTime> MODIF_DATE = createField(DSL.name("modif_date"), SQLDataType.LOCALDATETIME(6), this, "Date of the last modification of the record");

    /**
     * The column <code>public.ct_consumption_type.modif_user</code>.
     */
    public final TableField<CtConsumptionTypeRecord, String> MODIF_USER = createField(DSL.name("modif_user"), SQLDataType.VARCHAR(10), this, "");

    private CtConsumptionType(Name alias, Table<CtConsumptionTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private CtConsumptionType(Name alias, Table<CtConsumptionTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Table that stores the consumption types of the catalog for the application"), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.ct_consumption_type</code> table reference
     */
    public CtConsumptionType(String alias) {
        this(DSL.name(alias), CT_CONSUMPTION_TYPE);
    }

    /**
     * Create an aliased <code>public.ct_consumption_type</code> table reference
     */
    public CtConsumptionType(Name alias) {
        this(alias, CT_CONSUMPTION_TYPE);
    }

    /**
     * Create a <code>public.ct_consumption_type</code> table reference
     */
    public CtConsumptionType() {
        this(DSL.name("ct_consumption_type"), null);
    }

    public <O extends Record> CtConsumptionType(Table<O> child, ForeignKey<O, CtConsumptionTypeRecord> key) {
        super(child, key, CT_CONSUMPTION_TYPE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<CtConsumptionTypeRecord> getPrimaryKey() {
        return Keys.CT_CONSUMPTION_TYPE_PK;
    }

    @Override
    public List<UniqueKey<CtConsumptionTypeRecord>> getKeys() {
        return Arrays.<UniqueKey<CtConsumptionTypeRecord>>asList(Keys.CT_CONSUMPTION_TYPE_PK, Keys.CT_CONSUMPTION_TYPE_CODE_U, Keys.CT_CONSUMPTION_TYPE_NAME_U);
    }

    @Override
    public List<ForeignKey<CtConsumptionTypeRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CtConsumptionTypeRecord, ?>>asList(Keys.CT_CONSUMPTION_TYPE__CT_CONSUMPTION_TYPE_ENTITY_FK, Keys.CT_CONSUMPTION_TYPE__CT_CONSUMPTION_TYPE_STATUS_FK);
    }

    private transient PtEntityType _ptEntityType;
    private transient PtStatus _ptStatus;

    public PtEntityType ptEntityType() {
        if (_ptEntityType == null)
            _ptEntityType = new PtEntityType(this, Keys.CT_CONSUMPTION_TYPE__CT_CONSUMPTION_TYPE_ENTITY_FK);

        return _ptEntityType;
    }

    public PtStatus ptStatus() {
        if (_ptStatus == null)
            _ptStatus = new PtStatus(this, Keys.CT_CONSUMPTION_TYPE__CT_CONSUMPTION_TYPE_STATUS_FK);

        return _ptStatus;
    }

    @Override
    public CtConsumptionType as(String alias) {
        return new CtConsumptionType(DSL.name(alias), this);
    }

    @Override
    public CtConsumptionType as(Name alias) {
        return new CtConsumptionType(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CtConsumptionType rename(String name) {
        return new CtConsumptionType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CtConsumptionType rename(Name name) {
        return new CtConsumptionType(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, Integer, String, String, String, Integer, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
