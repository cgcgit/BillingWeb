/*
 * This file is generated by jOOQ.
 */
package com.billingweb.model.tables;


import com.billingweb.model.Keys;
import com.billingweb.model.Public;
import com.billingweb.model.tables.records.CtPromoConsumTypeDiscountRecord;

import java.time.LocalDateTime;
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
 * Table that stores the promotion fee discount relation types of the catalog 
 * for the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CtPromoConsumTypeDiscount extends TableImpl<CtPromoConsumTypeDiscountRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.ct_promo_consum_type_discount</code>
     */
    public static final CtPromoConsumTypeDiscount CT_PROMO_CONSUM_TYPE_DISCOUNT = new CtPromoConsumTypeDiscount();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CtPromoConsumTypeDiscountRecord> getRecordType() {
        return CtPromoConsumTypeDiscountRecord.class;
    }

    /**
     * The column <code>public.ct_promo_consum_type_discount.promo_consum_type_discount_id</code>. Internal identifier of the promotion-consumption discount relation type
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, Integer> PROMO_CONSUM_TYPE_DISCOUNT_ID = createField(DSL.name("promo_consum_type_discount_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("nextval('seq_promo_consum_type_disc_id'::regclass)", SQLDataType.INTEGER)), this, "Internal identifier of the promotion-consumption discount relation type");

    /**
     * The column <code>public.ct_promo_consum_type_discount.promotion_type_id</code>. Promotion_type_id for the promotion-consumption discount relation
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, Integer> PROMOTION_TYPE_ID = createField(DSL.name("promotion_type_id"), SQLDataType.INTEGER.nullable(false), this, "Promotion_type_id for the promotion-consumption discount relation");

    /**
     * The column <code>public.ct_promo_consum_type_discount.consumption_type_id</code>. Consumption_type_id for the promotion-consumption discount relation
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, Integer> CONSUMPTION_TYPE_ID = createField(DSL.name("consumption_type_id"), SQLDataType.INTEGER.nullable(false), this, "Consumption_type_id for the promotion-consumption discount relation");

    /**
     * The column <code>public.ct_promo_consum_type_discount.consum_type_discount_class_id</code>. Type id (entity_type_id) of the discount class for the promotion-consumption discount relation
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, Integer> CONSUM_TYPE_DISCOUNT_CLASS_ID = createField(DSL.name("consum_type_discount_class_id"), SQLDataType.INTEGER.nullable(false), this, "Type id (entity_type_id) of the discount class for the promotion-consumption discount relation");

    /**
     * The column <code>public.ct_promo_consum_type_discount.status_id</code>. Status id for the promotion-consumption discount relation type
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, Integer> STATUS_ID = createField(DSL.name("status_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("1000", SQLDataType.INTEGER)), this, "Status id for the promotion-consumption discount relation type");

    /**
     * The column <code>public.ct_promo_consum_type_discount.input_date</code>. Date on which the record was created
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, LocalDateTime> INPUT_DATE = createField(DSL.name("input_date"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("(('now'::text)::timestamp(0) with time zone)::timestamp without time zone", SQLDataType.LOCALDATETIME)), this, "Date on which the record was created");

    /**
     * The column <code>public.ct_promo_consum_type_discount.input_user</code>. User who was modified the record
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, String> INPUT_USER = createField(DSL.name("input_user"), SQLDataType.VARCHAR(10).nullable(false), this, "User who was modified the record");

    /**
     * The column <code>public.ct_promo_consum_type_discount.modif_date</code>. Date of the last modification of the record
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, LocalDateTime> MODIF_DATE = createField(DSL.name("modif_date"), SQLDataType.LOCALDATETIME(6), this, "Date of the last modification of the record");

    /**
     * The column <code>public.ct_promo_consum_type_discount.modif_user</code>.
     */
    public final TableField<CtPromoConsumTypeDiscountRecord, String> MODIF_USER = createField(DSL.name("modif_user"), SQLDataType.VARCHAR(10), this, "");

    private CtPromoConsumTypeDiscount(Name alias, Table<CtPromoConsumTypeDiscountRecord> aliased) {
        this(alias, aliased, null);
    }

    private CtPromoConsumTypeDiscount(Name alias, Table<CtPromoConsumTypeDiscountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Table that stores the promotion fee discount relation types of the catalog for the application"), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.ct_promo_consum_type_discount</code> table reference
     */
    public CtPromoConsumTypeDiscount(String alias) {
        this(DSL.name(alias), CT_PROMO_CONSUM_TYPE_DISCOUNT);
    }

    /**
     * Create an aliased <code>public.ct_promo_consum_type_discount</code> table reference
     */
    public CtPromoConsumTypeDiscount(Name alias) {
        this(alias, CT_PROMO_CONSUM_TYPE_DISCOUNT);
    }

    /**
     * Create a <code>public.ct_promo_consum_type_discount</code> table reference
     */
    public CtPromoConsumTypeDiscount() {
        this(DSL.name("ct_promo_consum_type_discount"), null);
    }

    public <O extends Record> CtPromoConsumTypeDiscount(Table<O> child, ForeignKey<O, CtPromoConsumTypeDiscountRecord> key) {
        super(child, key, CT_PROMO_CONSUM_TYPE_DISCOUNT);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<CtPromoConsumTypeDiscountRecord> getPrimaryKey() {
        return Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT_PK;
    }

    @Override
    public List<UniqueKey<CtPromoConsumTypeDiscountRecord>> getKeys() {
        return Arrays.<UniqueKey<CtPromoConsumTypeDiscountRecord>>asList(Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT_PK, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT_CODE_U);
    }

    @Override
    public List<ForeignKey<CtPromoConsumTypeDiscountRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CtPromoConsumTypeDiscountRecord, ?>>asList(Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISC_PROMO_FK, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISC_CONS_FK, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISC_CLASS_FK, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISCOUNT_STATUS_FK);
    }

    private transient CtPromotionType _ctPromotionType;
    private transient CtConsumptionType _ctConsumptionType;
    private transient PtEntityType _ptEntityType;
    private transient PtStatus _ptStatus;

    public CtPromotionType ctPromotionType() {
        if (_ctPromotionType == null)
            _ctPromotionType = new CtPromotionType(this, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISC_PROMO_FK);

        return _ctPromotionType;
    }

    public CtConsumptionType ctConsumptionType() {
        if (_ctConsumptionType == null)
            _ctConsumptionType = new CtConsumptionType(this, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISC_CONS_FK);

        return _ctConsumptionType;
    }

    public PtEntityType ptEntityType() {
        if (_ptEntityType == null)
            _ptEntityType = new PtEntityType(this, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISC_CLASS_FK);

        return _ptEntityType;
    }

    public PtStatus ptStatus() {
        if (_ptStatus == null)
            _ptStatus = new PtStatus(this, Keys.CT_PROMO_CONSUM_TYPE_DISCOUNT__CT_PROMO_CONSUM_TYPE_DISCOUNT_STATUS_FK);

        return _ptStatus;
    }

    @Override
    public CtPromoConsumTypeDiscount as(String alias) {
        return new CtPromoConsumTypeDiscount(DSL.name(alias), this);
    }

    @Override
    public CtPromoConsumTypeDiscount as(Name alias) {
        return new CtPromoConsumTypeDiscount(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CtPromoConsumTypeDiscount rename(String name) {
        return new CtPromoConsumTypeDiscount(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CtPromoConsumTypeDiscount rename(Name name) {
        return new CtPromoConsumTypeDiscount(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, Integer, Integer, Integer, Integer, LocalDateTime, String, LocalDateTime, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}