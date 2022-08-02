/*
 * This file is generated by jOOQ.
 */
package com.comasw.model;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.seq_account_id</code>
     */
    public static final Sequence<Long> SEQ_ACCOUNT_ID = Internal.createSequence("seq_account_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_account_type_id</code>
     */
    public static final Sequence<Long> SEQ_ACCOUNT_TYPE_ID = Internal.createSequence("seq_account_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_application_level_id</code>
     */
    public static final Sequence<Long> SEQ_APPLICATION_LEVEL_ID = Internal.createSequence("seq_application_level_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_application_menu_id</code>
     */
    public static final Sequence<Long> SEQ_APPLICATION_MENU_ID = Internal.createSequence("seq_application_menu_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_bill_cycle_type_id</code>
     */
    public static final Sequence<Long> SEQ_BILL_CYCLE_TYPE_ID = Internal.createSequence("seq_bill_cycle_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_billing_period_id</code>
     */
    public static final Sequence<Long> SEQ_BILLING_PERIOD_ID = Internal.createSequence("seq_billing_period_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_consumption_class_id</code>
     */
    public static final Sequence<Long> SEQ_CONSUMPTION_CLASS_ID = Internal.createSequence("seq_consumption_class_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_consumption_type</code>
     */
    public static final Sequence<Long> SEQ_CONSUMPTION_TYPE = Internal.createSequence("seq_consumption_type", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_contract_id</code>
     */
    public static final Sequence<Long> SEQ_CONTRACT_ID = Internal.createSequence("seq_contract_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_customer_id</code>
     */
    public static final Sequence<Long> SEQ_CUSTOMER_ID = Internal.createSequence("seq_customer_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_customer_type_id</code>
     */
    public static final Sequence<Long> SEQ_CUSTOMER_TYPE_ID = Internal.createSequence("seq_customer_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_discount_type_id</code>
     */
    public static final Sequence<Long> SEQ_DISCOUNT_TYPE_ID = Internal.createSequence("seq_discount_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_entity_type_id</code>
     */
    public static final Sequence<Long> SEQ_ENTITY_TYPE_ID = Internal.createSequence("seq_entity_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_fee_type_id</code>
     */
    public static final Sequence<Long> SEQ_FEE_TYPE_ID = Internal.createSequence("seq_fee_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_identity_card_type_id</code>
     */
    public static final Sequence<Long> SEQ_IDENTITY_CARD_TYPE_ID = Internal.createSequence("seq_identity_card_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_payment_method_id</code>
     */
    public static final Sequence<Long> SEQ_PAYMENT_METHOD_ID = Internal.createSequence("seq_payment_method_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_prod_fee_type_id</code>
     */
    public static final Sequence<Long> SEQ_PROD_FEE_TYPE_ID = Internal.createSequence("seq_prod_fee_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_prod_serv_type_id</code>
     */
    public static final Sequence<Long> SEQ_PROD_SERV_TYPE_ID = Internal.createSequence("seq_prod_serv_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_product_fee_id</code>
     */
    public static final Sequence<Long> SEQ_PRODUCT_FEE_ID = Internal.createSequence("seq_product_fee_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_product_id</code>
     */
    public static final Sequence<Long> SEQ_PRODUCT_ID = Internal.createSequence("seq_product_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_product_promotion_id</code>
     */
    public static final Sequence<Long> SEQ_PRODUCT_PROMOTION_ID = Internal.createSequence("seq_product_promotion_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_product_service_id</code>
     */
    public static final Sequence<Long> SEQ_PRODUCT_SERVICE_ID = Internal.createSequence("seq_product_service_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_product_type_id</code>
     */
    public static final Sequence<Long> SEQ_PRODUCT_TYPE_ID = Internal.createSequence("seq_product_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_profile_id</code>
     */
    public static final Sequence<Long> SEQ_PROFILE_ID = Internal.createSequence("seq_profile_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_promo_consum_type_disc_id</code>
     */
    public static final Sequence<Long> SEQ_PROMO_CONSUM_TYPE_DISC_ID = Internal.createSequence("seq_promo_consum_type_disc_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_promo_fee_type_disc_id</code>
     */
    public static final Sequence<Long> SEQ_PROMO_FEE_TYPE_DISC_ID = Internal.createSequence("seq_promo_fee_type_disc_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_promo_prod_type_id</code>
     */
    public static final Sequence<Long> SEQ_PROMO_PROD_TYPE_ID = Internal.createSequence("seq_promo_prod_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_promo_serv_type_id</code>
     */
    public static final Sequence<Long> SEQ_PROMO_SERV_TYPE_ID = Internal.createSequence("seq_promo_serv_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_promotion_id</code>
     */
    public static final Sequence<Long> SEQ_PROMOTION_ID = Internal.createSequence("seq_promotion_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_promotion_type_id</code>
     */
    public static final Sequence<Long> SEQ_PROMOTION_TYPE_ID = Internal.createSequence("seq_promotion_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_serv_fee_type_id</code>
     */
    public static final Sequence<Long> SEQ_SERV_FEE_TYPE_ID = Internal.createSequence("seq_serv_fee_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_service_fee_id</code>
     */
    public static final Sequence<Long> SEQ_SERVICE_FEE_ID = Internal.createSequence("seq_service_fee_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_service_id</code>
     */
    public static final Sequence<Long> SEQ_SERVICE_ID = Internal.createSequence("seq_service_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_service_promotion_id</code>
     */
    public static final Sequence<Long> SEQ_SERVICE_PROMOTION_ID = Internal.createSequence("seq_service_promotion_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_service_type_id</code>
     */
    public static final Sequence<Long> SEQ_SERVICE_TYPE_ID = Internal.createSequence("seq_service_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_status_id</code>
     */
    public static final Sequence<Long> SEQ_STATUS_ID = Internal.createSequence("seq_status_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_tax_type_id</code>
     */
    public static final Sequence<Long> SEQ_TAX_TYPE_ID = Internal.createSequence("seq_tax_type_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.seq_user_id</code>
     */
    public static final Sequence<Long> SEQ_USER_ID = Internal.createSequence("seq_user_id", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);
}
