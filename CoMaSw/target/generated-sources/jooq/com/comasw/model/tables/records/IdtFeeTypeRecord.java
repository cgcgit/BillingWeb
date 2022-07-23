/*
 * This file is generated by jOOQ.
 */
package com.comasw.model.tables.records;


import com.comasw.model.tables.IdtFeeType;
import com.comasw.model.tables.interfaces.IIdtFeeType;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Table that stores the fee_type_id for the fee types of the catalog for 
 * the application
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IdtFeeTypeRecord extends UpdatableRecordImpl<IdtFeeTypeRecord> implements Record1<Integer>, IIdtFeeType {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.idt_fee_type.fee_type_id</code>. Internal identifier of the fee type
     */
    @Override
    public void setFeeTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.idt_fee_type.fee_type_id</code>. Internal identifier of the fee type
     */
    @Override
    public Integer getFeeTypeId() {
        return (Integer) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row1<Integer> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    @Override
    public Row1<Integer> valuesRow() {
        return (Row1) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return IdtFeeType.IDT_FEE_TYPE.FEE_TYPE_ID;
    }

    @Override
    public Integer component1() {
        return getFeeTypeId();
    }

    @Override
    public Integer value1() {
        return getFeeTypeId();
    }

    @Override
    public IdtFeeTypeRecord value1(Integer value) {
        setFeeTypeId(value);
        return this;
    }

    @Override
    public IdtFeeTypeRecord values(Integer value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IIdtFeeType from) {
        setFeeTypeId(from.getFeeTypeId());
    }

    @Override
    public <E extends IIdtFeeType> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached IdtFeeTypeRecord
     */
    public IdtFeeTypeRecord() {
        super(IdtFeeType.IDT_FEE_TYPE);
    }

    /**
     * Create a detached, initialised IdtFeeTypeRecord
     */
    public IdtFeeTypeRecord(Integer feeTypeId) {
        super(IdtFeeType.IDT_FEE_TYPE);

        setFeeTypeId(feeTypeId);
    }
}
