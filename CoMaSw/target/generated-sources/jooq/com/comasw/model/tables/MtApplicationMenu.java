/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables;


import com.comasw.model.Keys;
import com.comasw.model.Public;
import com.comasw.model.tables.records.MtApplicationMenuRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Table that stores the application's menu
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MtApplicationMenu extends TableImpl<MtApplicationMenuRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.mt_application_menu</code>
     */
    public static final MtApplicationMenu MT_APPLICATION_MENU = new MtApplicationMenu();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MtApplicationMenuRecord> getRecordType() {
        return MtApplicationMenuRecord.class;
    }

    /**
     * The column <code>public.mt_application_menu.application_menu_id</code>. Internal identifier of the menu item
     */
    public final TableField<MtApplicationMenuRecord, Integer> APPLICATION_MENU_ID = createField(DSL.name("application_menu_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("nextval('seq_application_menu_id'::regclass)", SQLDataType.INTEGER)), this, "Internal identifier of the menu item");

    /**
     * The column <code>public.mt_application_menu.application_parent_menu_id</code>. Internal identifier of the parent menu item, if exists
     */
    public final TableField<MtApplicationMenuRecord, Integer> APPLICATION_PARENT_MENU_ID = createField(DSL.name("application_parent_menu_id"), SQLDataType.INTEGER, this, "Internal identifier of the parent menu item, if exists");

    /**
     * The column <code>public.mt_application_menu.has_children</code>. Flag that indicates if the item menu has children (true or false)
     */
    public final TableField<MtApplicationMenuRecord, Boolean> HAS_CHILDREN = createField(DSL.name("has_children"), SQLDataType.BOOLEAN.nullable(false), this, "Flag that indicates if the item menu has children (true or false)");

    /**
     * The column <code>public.mt_application_menu.menu_level</code>. Level of the menu item in the menu hierarchy
     */
    public final TableField<MtApplicationMenuRecord, Integer> MENU_LEVEL = createField(DSL.name("menu_level"), SQLDataType.INTEGER.nullable(false), this, "Level of the menu item in the menu hierarchy");

    /**
     * The column <code>public.mt_application_menu.position</code>. Position of the menu item in the menu level
     */
    public final TableField<MtApplicationMenuRecord, Integer> POSITION = createField(DSL.name("position"), SQLDataType.INTEGER.nullable(false), this, "Position of the menu item in the menu level");

    /**
     * The column <code>public.mt_application_menu.name</code>. Name to shown in the menu application
     */
    public final TableField<MtApplicationMenuRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "Name to shown in the menu application");

    /**
     * The column <code>public.mt_application_menu.description</code>. Description of the menu item
     */
    public final TableField<MtApplicationMenuRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(500).nullable(false), this, "Description of the menu item");

    /**
     * The column <code>public.mt_application_menu.profile_code</code>. Profiles authoriced to see the menu item
     */
    public final TableField<MtApplicationMenuRecord, String> PROFILE_CODE = createField(DSL.name("profile_code"), SQLDataType.VARCHAR(50), this, "Profiles authoriced to see the menu item");

    /**
     * The column <code>public.mt_application_menu.page</code>. JSF to redirect when the menu item is selected
     */
    public final TableField<MtApplicationMenuRecord, String> PAGE = createField(DSL.name("page"), SQLDataType.VARCHAR(500).nullable(false), this, "JSF to redirect when the menu item is selected");

    private MtApplicationMenu(Name alias, Table<MtApplicationMenuRecord> aliased) {
        this(alias, aliased, null);
    }

    private MtApplicationMenu(Name alias, Table<MtApplicationMenuRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Table that stores the application's menu"), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.mt_application_menu</code> table reference
     */
    public MtApplicationMenu(String alias) {
        this(DSL.name(alias), MT_APPLICATION_MENU);
    }

    /**
     * Create an aliased <code>public.mt_application_menu</code> table reference
     */
    public MtApplicationMenu(Name alias) {
        this(alias, MT_APPLICATION_MENU);
    }

    /**
     * Create a <code>public.mt_application_menu</code> table reference
     */
    public MtApplicationMenu() {
        this(DSL.name("mt_application_menu"), null);
    }

    public <O extends Record> MtApplicationMenu(Table<O> child, ForeignKey<O, MtApplicationMenuRecord> key) {
        super(child, key, MT_APPLICATION_MENU);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<MtApplicationMenuRecord> getPrimaryKey() {
        return Keys.MT_APPLICATION_MENU_PK;
    }

    @Override
    public List<UniqueKey<MtApplicationMenuRecord>> getKeys() {
        return Arrays.<UniqueKey<MtApplicationMenuRecord>>asList(Keys.MT_APPLICATION_MENU_PK);
    }

    @Override
    public List<ForeignKey<MtApplicationMenuRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MtApplicationMenuRecord, ?>>asList(Keys.MT_APPLICATION_MENU__MT_APPLICATION_MENU_FK);
    }

    private transient MtApplicationMenu _mtApplicationMenu;

    public MtApplicationMenu mtApplicationMenu() {
        if (_mtApplicationMenu == null)
            _mtApplicationMenu = new MtApplicationMenu(this, Keys.MT_APPLICATION_MENU__MT_APPLICATION_MENU_FK);

        return _mtApplicationMenu;
    }

    @Override
    public MtApplicationMenu as(String alias) {
        return new MtApplicationMenu(DSL.name(alias), this);
    }

    @Override
    public MtApplicationMenu as(Name alias) {
        return new MtApplicationMenu(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MtApplicationMenu rename(String name) {
        return new MtApplicationMenu(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MtApplicationMenu rename(Name name) {
        return new MtApplicationMenu(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, Integer, Boolean, Integer, Integer, String, String, String, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
