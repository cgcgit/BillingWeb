/*
 * This file is generated by jOOQ.
 */
package com.comasw.model;


import com.comasw.model.tables.CtAccountType;
import com.comasw.model.tables.CtBillCycleType;
import com.comasw.model.tables.CtConsumptionType;
import com.comasw.model.tables.CtCustomerType;
import com.comasw.model.tables.CtFeeType;
import com.comasw.model.tables.CtProdFeeType;
import com.comasw.model.tables.CtProdServType;
import com.comasw.model.tables.CtProductType;
import com.comasw.model.tables.CtPromoConsumTypeDiscount;
import com.comasw.model.tables.CtPromoFeeTypeDiscount;
import com.comasw.model.tables.CtPromoProdType;
import com.comasw.model.tables.CtPromoServType;
import com.comasw.model.tables.CtPromotionType;
import com.comasw.model.tables.CtServFeeType;
import com.comasw.model.tables.CtServiceType;
import com.comasw.model.tables.IdtAccount;
import com.comasw.model.tables.IdtCustomer;
import com.comasw.model.tables.IdtFeeType;
import com.comasw.model.tables.IdtProduct;
import com.comasw.model.tables.IdtProductFee;
import com.comasw.model.tables.IdtProductPromotion;
import com.comasw.model.tables.IdtProductService;
import com.comasw.model.tables.IdtPromotion;
import com.comasw.model.tables.IdtPromotionType;
import com.comasw.model.tables.IdtService;
import com.comasw.model.tables.IdtServiceFee;
import com.comasw.model.tables.IdtServicePromotion;
import com.comasw.model.tables.ItAccount;
import com.comasw.model.tables.ItContract;
import com.comasw.model.tables.ItCustomer;
import com.comasw.model.tables.ItProfiles;
import com.comasw.model.tables.ItUsers;
import com.comasw.model.tables.MtApplicationMenu;
import com.comasw.model.tables.PtApplicationLevel;
import com.comasw.model.tables.PtBillingPeriod;
import com.comasw.model.tables.PtConsumptionClass;
import com.comasw.model.tables.PtDiscountType;
import com.comasw.model.tables.PtEntityType;
import com.comasw.model.tables.PtIdentityCardType;
import com.comasw.model.tables.PtPaymentMethod;
import com.comasw.model.tables.PtStatus;
import com.comasw.model.tables.PtTaxType;
import com.comasw.model.tables.VwProductFeeType;
import com.comasw.model.tables.VwProductServiceType;
import com.comasw.model.tables.VwPromoConsumTypeDiscount;
import com.comasw.model.tables.VwPromotionFeeTypeDiscount;
import com.comasw.model.tables.VwPromotionProductType;
import com.comasw.model.tables.VwPromotionServiceType;
import com.comasw.model.tables.VwServiceFeeType;
import com.comasw.model.tables.VwUsers;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * Table that stores the account types of the catalog for the application
     */
    public final CtAccountType CT_ACCOUNT_TYPE = CtAccountType.CT_ACCOUNT_TYPE;

    /**
     * Table that stores the bill cycle type of the application
     */
    public final CtBillCycleType CT_BILL_CYCLE_TYPE = CtBillCycleType.CT_BILL_CYCLE_TYPE;

    /**
     * Table that stores the consumption types of the catalog for the application
     */
    public final CtConsumptionType CT_CONSUMPTION_TYPE = CtConsumptionType.CT_CONSUMPTION_TYPE;

    /**
     * Table that stores the customer types of the catalog for the application
     */
    public final CtCustomerType CT_CUSTOMER_TYPE = CtCustomerType.CT_CUSTOMER_TYPE;

    /**
     * Table that stores the fee types of the catalog for the application
     */
    public final CtFeeType CT_FEE_TYPE = CtFeeType.CT_FEE_TYPE;

    /**
     * Table that stores the fee product relation types of the catalog for the application
     */
    public final CtProdFeeType CT_PROD_FEE_TYPE = CtProdFeeType.CT_PROD_FEE_TYPE;

    /**
     * Table that stores the promotion product relation types of the catalog for the application
     */
    public final CtProdServType CT_PROD_SERV_TYPE = CtProdServType.CT_PROD_SERV_TYPE;

    /**
     * Table that stores the product types of the catalog for the application
     */
    public final CtProductType CT_PRODUCT_TYPE = CtProductType.CT_PRODUCT_TYPE;

    /**
     * Table that stores the promotion fee discount relation types of the catalog for the application
     */
    public final CtPromoConsumTypeDiscount CT_PROMO_CONSUM_TYPE_DISCOUNT = CtPromoConsumTypeDiscount.CT_PROMO_CONSUM_TYPE_DISCOUNT;

    /**
     * Table that stores the promotion fee discount relation types of the catalog for the application
     */
    public final CtPromoFeeTypeDiscount CT_PROMO_FEE_TYPE_DISCOUNT = CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT;

    /**
     * Table that stores the promotion product relation types of the catalog for the application
     */
    public final CtPromoProdType CT_PROMO_PROD_TYPE = CtPromoProdType.CT_PROMO_PROD_TYPE;

    /**
     * Table that stores the promotion service relation types of the catalog for the application
     */
    public final CtPromoServType CT_PROMO_SERV_TYPE = CtPromoServType.CT_PROMO_SERV_TYPE;

    /**
     * The table <code>public.ct_promotion_type</code>.
     */
    public final CtPromotionType CT_PROMOTION_TYPE = CtPromotionType.CT_PROMOTION_TYPE;

    /**
     * Table that stores the fee service relation types of the catalog for the application
     */
    public final CtServFeeType CT_SERV_FEE_TYPE = CtServFeeType.CT_SERV_FEE_TYPE;

    /**
     * Table that stores the service types of the catalog for the application
     */
    public final CtServiceType CT_SERVICE_TYPE = CtServiceType.CT_SERVICE_TYPE;

    /**
     * Table that stores the account instance ids referenced by account table
     */
    public final IdtAccount IDT_ACCOUNT = IdtAccount.IDT_ACCOUNT;

    /**
     * Table that stores the customer instance ids referenced by customer table
     */
    public final IdtCustomer IDT_CUSTOMER = IdtCustomer.IDT_CUSTOMER;

    /**
     * Table that stores the fee_type_id for the fee types of the catalog for the application
     */
    public final IdtFeeType IDT_FEE_TYPE = IdtFeeType.IDT_FEE_TYPE;

    /**
     * Table that stores the product instance ids referenced by product table
     */
    public final IdtProduct IDT_PRODUCT = IdtProduct.IDT_PRODUCT;

    /**
     * Table that stores the product-fee instance relation ids referenced by product_fee table
     */
    public final IdtProductFee IDT_PRODUCT_FEE = IdtProductFee.IDT_PRODUCT_FEE;

    /**
     * Table that stores the product-promotion instance relation ids referenced by product_promotion table
     */
    public final IdtProductPromotion IDT_PRODUCT_PROMOTION = IdtProductPromotion.IDT_PRODUCT_PROMOTION;

    /**
     * Table that stores the product-service instance relation ids referenced by product_service table
     */
    public final IdtProductService IDT_PRODUCT_SERVICE = IdtProductService.IDT_PRODUCT_SERVICE;

    /**
     * Table that stores the promotion instance ids referenced by promotion table
     */
    public final IdtPromotion IDT_PROMOTION = IdtPromotion.IDT_PROMOTION;

    /**
     * The table <code>public.idt_promotion_type</code>.
     */
    public final IdtPromotionType IDT_PROMOTION_TYPE = IdtPromotionType.IDT_PROMOTION_TYPE;

    /**
     * Table that stores the service instance ids referenced by service table
     */
    public final IdtService IDT_SERVICE = IdtService.IDT_SERVICE;

    /**
     * Table that stores the service-fee instance relation ids referenced by service_fee table
     */
    public final IdtServiceFee IDT_SERVICE_FEE = IdtServiceFee.IDT_SERVICE_FEE;

    /**
     * Table that stores the service-promotion instance relation ids referenced by service_promotion table
     */
    public final IdtServicePromotion IDT_SERVICE_PROMOTION = IdtServicePromotion.IDT_SERVICE_PROMOTION;

    /**
     * Table that stores the account instance data
     */
    public final ItAccount IT_ACCOUNT = ItAccount.IT_ACCOUNT;

    /**
     * Table that stores the contract instance data
     */
    public final ItContract IT_CONTRACT = ItContract.IT_CONTRACT;

    /**
     * Table that stores the customer instance data
     */
    public final ItCustomer IT_CUSTOMER = ItCustomer.IT_CUSTOMER;

    /**
     * Table that stores application profiles
     */
    public final ItProfiles IT_PROFILES = ItProfiles.IT_PROFILES;

    /**
     * Table that stores application users
     */
    public final ItUsers IT_USERS = ItUsers.IT_USERS;

    /**
     * Table that stores the application's menu
     */
    public final MtApplicationMenu MT_APPLICATION_MENU = MtApplicationMenu.MT_APPLICATION_MENU;

    /**
     * Table that stores the application level for the application
     */
    public final PtApplicationLevel PT_APPLICATION_LEVEL = PtApplicationLevel.PT_APPLICATION_LEVEL;

    /**
     * Table that stores the billing period types for the application
     */
    public final PtBillingPeriod PT_BILLING_PERIOD = PtBillingPeriod.PT_BILLING_PERIOD;

    /**
     * Table that stores the consumtion class of the application
     */
    public final PtConsumptionClass PT_CONSUMPTION_CLASS = PtConsumptionClass.PT_CONSUMPTION_CLASS;

    /**
     * Table that stores the discount types for the application
     */
    public final PtDiscountType PT_DISCOUNT_TYPE = PtDiscountType.PT_DISCOUNT_TYPE;

    /**
     * Table that stores the entity type of the application
     */
    public final PtEntityType PT_ENTITY_TYPE = PtEntityType.PT_ENTITY_TYPE;

    /**
     * Table that stores the posible types of the the identity card
     */
    public final PtIdentityCardType PT_IDENTITY_CARD_TYPE = PtIdentityCardType.PT_IDENTITY_CARD_TYPE;

    /**
     * Table that stores the payment types for the application
     */
    public final PtPaymentMethod PT_PAYMENT_METHOD = PtPaymentMethod.PT_PAYMENT_METHOD;

    /**
     * Table that stores the status for the entities of the application
     */
    public final PtStatus PT_STATUS = PtStatus.PT_STATUS;

    /**
     * Table that stores the tax type of the application
     */
    public final PtTaxType PT_TAX_TYPE = PtTaxType.PT_TAX_TYPE;

    /**
     * The table <code>public.vw_product_fee_type</code>.
     */
    public final VwProductFeeType VW_PRODUCT_FEE_TYPE = VwProductFeeType.VW_PRODUCT_FEE_TYPE;

    /**
     * View showing the relationship between product types and the service types associated with them - related to ct_prod_serv_type
     */
    public final VwProductServiceType VW_PRODUCT_SERVICE_TYPE = VwProductServiceType.VW_PRODUCT_SERVICE_TYPE;

    /**
     * The table <code>public.vw_promo_consum_type_discount</code>.
     */
    public final VwPromoConsumTypeDiscount VW_PROMO_CONSUM_TYPE_DISCOUNT = VwPromoConsumTypeDiscount.VW_PROMO_CONSUM_TYPE_DISCOUNT;

    /**
     * The table <code>public.vw_promotion_fee_type_discount</code>.
     */
    public final VwPromotionFeeTypeDiscount VW_PROMOTION_FEE_TYPE_DISCOUNT = VwPromotionFeeTypeDiscount.VW_PROMOTION_FEE_TYPE_DISCOUNT;

    /**
     * The table <code>public.vw_promotion_product_type</code>.
     */
    public final VwPromotionProductType VW_PROMOTION_PRODUCT_TYPE = VwPromotionProductType.VW_PROMOTION_PRODUCT_TYPE;

    /**
     * The table <code>public.vw_promotion_service_type</code>.
     */
    public final VwPromotionServiceType VW_PROMOTION_SERVICE_TYPE = VwPromotionServiceType.VW_PROMOTION_SERVICE_TYPE;

    /**
     * The table <code>public.vw_service_fee_type</code>.
     */
    public final VwServiceFeeType VW_SERVICE_FEE_TYPE = VwServiceFeeType.VW_SERVICE_FEE_TYPE;

    /**
     * The table <code>public.vw_users</code>.
     */
    public final VwUsers VW_USERS = VwUsers.VW_USERS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.<Sequence<?>>asList(
            Sequences.SEQ_ACCOUNT_ID,
            Sequences.SEQ_ACCOUNT_TYPE_ID,
            Sequences.SEQ_APPLICATION_LEVEL_ID,
            Sequences.SEQ_APPLICATION_MENU_ID,
            Sequences.SEQ_BILL_CYCLE_TYPE_ID,
            Sequences.SEQ_BILLING_PERIOD_ID,
            Sequences.SEQ_CONSUMPTION_CLASS_ID,
            Sequences.SEQ_CONSUMPTION_TYPE,
            Sequences.SEQ_CONTRACT_ID,
            Sequences.SEQ_CUSTOMER_ID,
            Sequences.SEQ_CUSTOMER_TYPE_ID,
            Sequences.SEQ_DISCOUNT_TYPE_ID,
            Sequences.SEQ_ENTITY_TYPE_ID,
            Sequences.SEQ_FEE_TYPE_ID,
            Sequences.SEQ_IDENTITY_CARD_TYPE_ID,
            Sequences.SEQ_PAYMENT_METHOD_ID,
            Sequences.SEQ_PROD_FEE_TYPE_ID,
            Sequences.SEQ_PROD_SERV_TYPE_ID,
            Sequences.SEQ_PRODUCT_FEE_ID,
            Sequences.SEQ_PRODUCT_ID,
            Sequences.SEQ_PRODUCT_PROMOTION_ID,
            Sequences.SEQ_PRODUCT_SERVICE_ID,
            Sequences.SEQ_PRODUCT_TYPE_ID,
            Sequences.SEQ_PROFILE_ID,
            Sequences.SEQ_PROMO_CONSUM_TYPE_DISC_ID,
            Sequences.SEQ_PROMO_FEE_TYPE_DISC_ID,
            Sequences.SEQ_PROMO_PROD_TYPE_ID,
            Sequences.SEQ_PROMO_SERV_TYPE_ID,
            Sequences.SEQ_PROMOTION_ID,
            Sequences.SEQ_PROMOTION_TYPE_ID,
            Sequences.SEQ_SERV_FEE_TYPE_ID,
            Sequences.SEQ_SERVICE_FEE_ID,
            Sequences.SEQ_SERVICE_ID,
            Sequences.SEQ_SERVICE_PROMOTION_ID,
            Sequences.SEQ_SERVICE_TYPE_ID,
            Sequences.SEQ_STATUS_ID,
            Sequences.SEQ_TAX_TYPE_ID,
            Sequences.SEQ_USER_ID);
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            CtAccountType.CT_ACCOUNT_TYPE,
            CtBillCycleType.CT_BILL_CYCLE_TYPE,
            CtConsumptionType.CT_CONSUMPTION_TYPE,
            CtCustomerType.CT_CUSTOMER_TYPE,
            CtFeeType.CT_FEE_TYPE,
            CtProdFeeType.CT_PROD_FEE_TYPE,
            CtProdServType.CT_PROD_SERV_TYPE,
            CtProductType.CT_PRODUCT_TYPE,
            CtPromoConsumTypeDiscount.CT_PROMO_CONSUM_TYPE_DISCOUNT,
            CtPromoFeeTypeDiscount.CT_PROMO_FEE_TYPE_DISCOUNT,
            CtPromoProdType.CT_PROMO_PROD_TYPE,
            CtPromoServType.CT_PROMO_SERV_TYPE,
            CtPromotionType.CT_PROMOTION_TYPE,
            CtServFeeType.CT_SERV_FEE_TYPE,
            CtServiceType.CT_SERVICE_TYPE,
            IdtAccount.IDT_ACCOUNT,
            IdtCustomer.IDT_CUSTOMER,
            IdtFeeType.IDT_FEE_TYPE,
            IdtProduct.IDT_PRODUCT,
            IdtProductFee.IDT_PRODUCT_FEE,
            IdtProductPromotion.IDT_PRODUCT_PROMOTION,
            IdtProductService.IDT_PRODUCT_SERVICE,
            IdtPromotion.IDT_PROMOTION,
            IdtPromotionType.IDT_PROMOTION_TYPE,
            IdtService.IDT_SERVICE,
            IdtServiceFee.IDT_SERVICE_FEE,
            IdtServicePromotion.IDT_SERVICE_PROMOTION,
            ItAccount.IT_ACCOUNT,
            ItContract.IT_CONTRACT,
            ItCustomer.IT_CUSTOMER,
            ItProfiles.IT_PROFILES,
            ItUsers.IT_USERS,
            MtApplicationMenu.MT_APPLICATION_MENU,
            PtApplicationLevel.PT_APPLICATION_LEVEL,
            PtBillingPeriod.PT_BILLING_PERIOD,
            PtConsumptionClass.PT_CONSUMPTION_CLASS,
            PtDiscountType.PT_DISCOUNT_TYPE,
            PtEntityType.PT_ENTITY_TYPE,
            PtIdentityCardType.PT_IDENTITY_CARD_TYPE,
            PtPaymentMethod.PT_PAYMENT_METHOD,
            PtStatus.PT_STATUS,
            PtTaxType.PT_TAX_TYPE,
            VwProductFeeType.VW_PRODUCT_FEE_TYPE,
            VwProductServiceType.VW_PRODUCT_SERVICE_TYPE,
            VwPromoConsumTypeDiscount.VW_PROMO_CONSUM_TYPE_DISCOUNT,
            VwPromotionFeeTypeDiscount.VW_PROMOTION_FEE_TYPE_DISCOUNT,
            VwPromotionProductType.VW_PROMOTION_PRODUCT_TYPE,
            VwPromotionServiceType.VW_PROMOTION_SERVICE_TYPE,
            VwServiceFeeType.VW_SERVICE_FEE_TYPE,
            VwUsers.VW_USERS);
    }
}
