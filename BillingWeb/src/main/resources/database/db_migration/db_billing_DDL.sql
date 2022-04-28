--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6 (Ubuntu 13.6-1.pgdg20.04+1+b1)
-- Dumped by pg_dump version 13.6 (Ubuntu 13.6-1.pgdg20.04+1+b1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: seq_account_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_account_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_account_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_account_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_account_type_id IS 'sequence for field account_type_id in table ct_account_type';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: ct_account_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_account_type (
    account_type_id integer DEFAULT nextval('public.seq_account_type_id'::regclass) NOT NULL,
    entity_type_id integer DEFAULT 1009 NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    ordinary_bill_cycle_type_id integer NOT NULL,
    corrective_bill_cycle_type_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_account_type OWNER TO billing_admin;

--
-- Name: TABLE ct_account_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_account_type IS 'Table that stores the account types of the catalog for the application';


--
-- Name: COLUMN ct_account_type.account_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.account_type_id IS 'Internal identifier of the account type';


--
-- Name: COLUMN ct_account_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.entity_type_id IS 'Entity_id for the account type';


--
-- Name: COLUMN ct_account_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.code IS 'Code of the account type';


--
-- Name: COLUMN ct_account_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.name IS 'Name of the account type';


--
-- Name: COLUMN ct_account_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.description IS 'Description for the account type';


--
-- Name: COLUMN ct_account_type.ordinary_bill_cycle_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.ordinary_bill_cycle_type_id IS 'Ordinary cycle type id for the ordinary billing of the account';


--
-- Name: COLUMN ct_account_type.corrective_bill_cycle_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.corrective_bill_cycle_type_id IS 'Bill cycle type id for the ordinary billing of the account';


--
-- Name: COLUMN ct_account_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.status_id IS 'Status id for the account type';


--
-- Name: COLUMN ct_account_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_account_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_account_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_account_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_bill_cycle_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_bill_cycle_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_bill_cycle_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_bill_cycle_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_bill_cycle_type_id IS 'sequence for field bill_cycle_type_id in table ct_bill_cycle_type';


--
-- Name: ct_bill_cycle_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_bill_cycle_type (
    bill_cycle_type_id integer DEFAULT nextval('public.seq_bill_cycle_type_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    billing_period_id integer NOT NULL,
    bill_cycle_day numeric(2,0) NOT NULL,
    bill_cycle_codenum character varying(10) NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10),
    corrective boolean NOT NULL
);


ALTER TABLE public.ct_bill_cycle_type OWNER TO billing_admin;

--
-- Name: TABLE ct_bill_cycle_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_bill_cycle_type IS 'Table that stores the bill cycle type of the application';


--
-- Name: COLUMN ct_bill_cycle_type.bill_cycle_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_bill_cycle_type.bill_cycle_type_id IS 'Internal identifier of the bill cycle type';


--
-- Name: COLUMN ct_bill_cycle_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_bill_cycle_type.code IS 'Code of the bill cycle type';


--
-- Name: COLUMN ct_bill_cycle_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_bill_cycle_type.name IS 'Name of the bill cycle type';


--
-- Name: COLUMN ct_bill_cycle_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_bill_cycle_type.description IS 'Description for the bill cycle type';


--
-- Name: COLUMN ct_bill_cycle_type.billing_period_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_bill_cycle_type.billing_period_id IS 'Billing period for the bill cycle type';


--
-- Name: COLUMN ct_bill_cycle_type.bill_cycle_codenum; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_bill_cycle_type.bill_cycle_codenum IS 'Billing numbering code with which the invoice number is composed';


--
-- Name: COLUMN ct_bill_cycle_type.corrective; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_bill_cycle_type.corrective IS 'Flag for corrective cycle (true: corrective cycle, false: ordinary cycle)';


--
-- Name: seq_consumption_type; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_consumption_type
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_consumption_type OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_consumption_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_consumption_type IS 'sequence for field consumption_type_id in table ct_consumption_type';


--
-- Name: ct_consumption_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_consumption_type (
    consumption_type_id integer DEFAULT nextval('public.seq_consumption_type'::regclass) NOT NULL,
    entity_type_id integer DEFAULT 1004 NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10),
    consumption_class_id integer NOT NULL
);


ALTER TABLE public.ct_consumption_type OWNER TO billing_admin;

--
-- Name: TABLE ct_consumption_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_consumption_type IS 'Table that stores the consumption types of the catalog for the application';


--
-- Name: COLUMN ct_consumption_type.consumption_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.consumption_type_id IS 'Internal identifier of the consumption type';


--
-- Name: COLUMN ct_consumption_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.entity_type_id IS 'Entity_id for the consumption type';


--
-- Name: COLUMN ct_consumption_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.code IS 'Code of the consumption type';


--
-- Name: COLUMN ct_consumption_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.name IS 'Name of the consumption type';


--
-- Name: COLUMN ct_consumption_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.description IS 'Description for the consumption type';


--
-- Name: COLUMN ct_consumption_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.status_id IS 'Status id for the consumption type';


--
-- Name: COLUMN ct_consumption_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_consumption_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_consumption_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: COLUMN ct_consumption_type.consumption_class_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_consumption_type.consumption_class_id IS 'Consumption class for the consumption type';


--
-- Name: seq_customer_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_customer_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_customer_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_customer_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_customer_type_id IS 'sequence for field customer_type_id in table ct_customer_type';


--
-- Name: ct_customer_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_customer_type (
    customer_type_id integer DEFAULT nextval('public.seq_customer_type_id'::regclass) NOT NULL,
    entity_type_id integer DEFAULT 1008 NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_customer_type OWNER TO billing_admin;

--
-- Name: TABLE ct_customer_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_customer_type IS 'Table that stores the customer types of the catalog for the application';


--
-- Name: COLUMN ct_customer_type.customer_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.customer_type_id IS 'Internal identifier of the customer type';


--
-- Name: COLUMN ct_customer_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.entity_type_id IS 'Entity_id for the customer type';


--
-- Name: COLUMN ct_customer_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.code IS 'Code of the customer type';


--
-- Name: COLUMN ct_customer_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.name IS 'Name of the customer type';


--
-- Name: COLUMN ct_customer_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.description IS 'Description for the customer type';


--
-- Name: COLUMN ct_customer_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.status_id IS 'Status id for the customer type';


--
-- Name: COLUMN ct_customer_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_customer_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_customer_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_customer_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_fee_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_fee_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_fee_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_fee_type_id IS 'sequence for field fee_type_id in table ct_fee_type';


--
-- Name: ct_fee_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_fee_type (
    fee_type_id integer DEFAULT nextval('public.seq_fee_type_id'::regclass) NOT NULL,
    entity_type_id integer DEFAULT 1003 NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    application_level_id integer NOT NULL,
    prorate boolean DEFAULT false NOT NULL,
    price numeric(10,4) NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_fee_type OWNER TO billing_admin;

--
-- Name: TABLE ct_fee_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_fee_type IS 'Table that stores the fee types of the catalog for the application';


--
-- Name: COLUMN ct_fee_type.fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.fee_type_id IS 'Internal identifier of the fee type';


--
-- Name: COLUMN ct_fee_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.entity_type_id IS 'Entity_id for the fee type';


--
-- Name: COLUMN ct_fee_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.code IS 'Code of the fee type';


--
-- Name: COLUMN ct_fee_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.name IS 'Name of the fee type';


--
-- Name: COLUMN ct_fee_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.description IS 'Description for the fee type';


--
-- Name: COLUMN ct_fee_type.application_level_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.application_level_id IS 'Application level id for the fee type';


--
-- Name: COLUMN ct_fee_type.prorate; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.prorate IS 'Flag for prorate (true: prorated fee)';


--
-- Name: COLUMN ct_fee_type.price; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.price IS 'Price for the fee type';


--
-- Name: COLUMN ct_fee_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.status_id IS 'Status id for the fee type';


--
-- Name: COLUMN ct_fee_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_fee_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_fee_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_fee_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_prod_fee_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_prod_fee_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_prod_fee_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_prod_fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_prod_fee_type_id IS 'sequence for field prod_fee_type_id in table ct_prod_fee_type';


--
-- Name: ct_prod_fee_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_prod_fee_type (
    prod_fee_type_id integer DEFAULT nextval('public.seq_prod_fee_type_id'::regclass) NOT NULL,
    product_type_id integer NOT NULL,
    fee_type_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_prod_fee_type OWNER TO billing_admin;

--
-- Name: TABLE ct_prod_fee_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_prod_fee_type IS 'Table that stores the fee product relation types of the catalog for the application';


--
-- Name: COLUMN ct_prod_fee_type.prod_fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_fee_type.prod_fee_type_id IS 'Internal identifier of the product-fee relation type';


--
-- Name: COLUMN ct_prod_fee_type.product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_fee_type.product_type_id IS 'Product_type_id for the product-fee relation';


--
-- Name: COLUMN ct_prod_fee_type.fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_fee_type.fee_type_id IS 'Service_type_id for the product-fee relation';


--
-- Name: COLUMN ct_prod_fee_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_fee_type.status_id IS 'Status id for the product-fee relation type';


--
-- Name: COLUMN ct_prod_fee_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_fee_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_prod_fee_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_fee_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_prod_fee_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_fee_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_prod_serv_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_prod_serv_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_prod_serv_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_prod_serv_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_prod_serv_type_id IS 'sequence for field prod_serv_type_id in table ct_prod_serv_type';


--
-- Name: ct_prod_serv_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_prod_serv_type (
    prod_serv_type_id integer DEFAULT nextval('public.seq_prod_serv_type_id'::regclass) NOT NULL,
    product_type_id integer NOT NULL,
    service_type_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_prod_serv_type OWNER TO billing_admin;

--
-- Name: TABLE ct_prod_serv_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_prod_serv_type IS 'Table that stores the promotion product relation types of the catalog for the application';


--
-- Name: COLUMN ct_prod_serv_type.prod_serv_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_serv_type.prod_serv_type_id IS 'Internal identifier of the product-service relation type';


--
-- Name: COLUMN ct_prod_serv_type.product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_serv_type.product_type_id IS 'Product_type_id for the product-service relation';


--
-- Name: COLUMN ct_prod_serv_type.service_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_serv_type.service_type_id IS 'Service_type_id for the product-service relation';


--
-- Name: COLUMN ct_prod_serv_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_serv_type.status_id IS 'Status id for the product-service relation type';


--
-- Name: COLUMN ct_prod_serv_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_serv_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_prod_serv_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_serv_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_prod_serv_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_prod_serv_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_product_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_product_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_product_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_product_type_id IS 'sequence for field product_type_id in table ct_product_type';


--
-- Name: ct_product_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_product_type (
    product_type_id integer DEFAULT nextval('public.seq_product_type_id'::regclass) NOT NULL,
    entity_type_id integer DEFAULT 1000 NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_product_type OWNER TO billing_admin;

--
-- Name: TABLE ct_product_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_product_type IS 'Table that stores the product types of the catalog for the application';


--
-- Name: COLUMN ct_product_type.product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.product_type_id IS 'Internal identifier of the product type';


--
-- Name: COLUMN ct_product_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.entity_type_id IS 'Entity_id for the product type';


--
-- Name: COLUMN ct_product_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.code IS 'Code of the product type';


--
-- Name: COLUMN ct_product_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.name IS 'Name of the product type';


--
-- Name: COLUMN ct_product_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.description IS 'Description for the product type';


--
-- Name: COLUMN ct_product_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.status_id IS 'Status id for the product type';


--
-- Name: COLUMN ct_product_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_product_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_product_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_product_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_promo_consum_type_disc_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_promo_consum_type_disc_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_promo_consum_type_disc_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_promo_consum_type_disc_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_promo_consum_type_disc_id IS 'sequence for field promo_consum_type_discount_id in table ct_promo_consum_discount_type';


--
-- Name: ct_promo_consum_type_discount; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_promo_consum_type_discount (
    promo_consum_type_discount_id integer DEFAULT nextval('public.seq_promo_consum_type_disc_id'::regclass) NOT NULL,
    promotion_type_id integer NOT NULL,
    consumption_type_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_promo_consum_type_discount OWNER TO billing_admin;

--
-- Name: TABLE ct_promo_consum_type_discount; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_promo_consum_type_discount IS 'Table that stores the promotion fee discount relation types of the catalog for the application';


--
-- Name: COLUMN ct_promo_consum_type_discount.promo_consum_type_discount_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_consum_type_discount.promo_consum_type_discount_id IS 'Internal identifier of the promotion-consumption discount relation type';


--
-- Name: COLUMN ct_promo_consum_type_discount.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_consum_type_discount.promotion_type_id IS 'Promotion_type_id for the promotion-consumption discount relation';


--
-- Name: COLUMN ct_promo_consum_type_discount.consumption_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_consum_type_discount.consumption_type_id IS 'Consumption_type_id for the promotion-consumption discount relation';


--
-- Name: COLUMN ct_promo_consum_type_discount.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_consum_type_discount.status_id IS 'Status id for the promotion-consumption discount relation type';


--
-- Name: COLUMN ct_promo_consum_type_discount.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_consum_type_discount.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_promo_consum_type_discount.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_consum_type_discount.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_promo_consum_type_discount.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_consum_type_discount.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_promo_fee_type_disc_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_promo_fee_type_disc_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_promo_fee_type_disc_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_promo_fee_type_disc_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_promo_fee_type_disc_id IS 'sequence for field promo_fee_type_discount_id in table ct_promo_fee_type_discount';


--
-- Name: ct_promo_fee_type_discount; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_promo_fee_type_discount (
    promo_fee_type_discount_id integer DEFAULT nextval('public.seq_promo_fee_type_disc_id'::regclass) NOT NULL,
    promotion_type_id integer NOT NULL,
    fee_type_id integer NOT NULL,
    application_level_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_promo_fee_type_discount OWNER TO billing_admin;

--
-- Name: TABLE ct_promo_fee_type_discount; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_promo_fee_type_discount IS 'Table that stores the promotion fee discount relation types of the catalog for the application';


--
-- Name: COLUMN ct_promo_fee_type_discount.promo_fee_type_discount_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.promo_fee_type_discount_id IS 'Internal identifier of the promotion-fee discount relation type';


--
-- Name: COLUMN ct_promo_fee_type_discount.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.promotion_type_id IS 'Promotion_type_id for the promotion-fee discount relation';


--
-- Name: COLUMN ct_promo_fee_type_discount.fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.fee_type_id IS 'Fee_type_id for the promotion-fee discount relation';


--
-- Name: COLUMN ct_promo_fee_type_discount.application_level_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.application_level_id IS 'Application_level_id for the promotion-fee discount relation';


--
-- Name: COLUMN ct_promo_fee_type_discount.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.status_id IS 'Status id for the promotion-fee discount relation type';


--
-- Name: COLUMN ct_promo_fee_type_discount.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_promo_fee_type_discount.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_promo_fee_type_discount.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_fee_type_discount.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_promo_prod_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_promo_prod_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_promo_prod_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_promo_prod_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_promo_prod_type_id IS 'sequence for field promo_prod_type_id in table ct_promo_prod_type';


--
-- Name: ct_promo_prod_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_promo_prod_type (
    promo_prod_type_id integer DEFAULT nextval('public.seq_promo_prod_type_id'::regclass) NOT NULL,
    promotion_type_id integer NOT NULL,
    product_type_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_promo_prod_type OWNER TO billing_admin;

--
-- Name: TABLE ct_promo_prod_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_promo_prod_type IS 'Table that stores the promotion product relation types of the catalog for the application';


--
-- Name: COLUMN ct_promo_prod_type.promo_prod_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_prod_type.promo_prod_type_id IS 'Internal identifier of the promotion-product relation type';


--
-- Name: COLUMN ct_promo_prod_type.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_prod_type.promotion_type_id IS 'Promotion_type_id for the promotion-product relation';


--
-- Name: COLUMN ct_promo_prod_type.product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_prod_type.product_type_id IS 'Product_type_id for the promotion-product relation';


--
-- Name: COLUMN ct_promo_prod_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_prod_type.status_id IS 'Status id for the promotion-product relation type';


--
-- Name: COLUMN ct_promo_prod_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_prod_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_promo_prod_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_prod_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_promo_prod_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_prod_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_promo_serv_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_promo_serv_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_promo_serv_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_promo_serv_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_promo_serv_type_id IS 'sequence for field promo_serv_type_id in table ct_promo_serv_type';


--
-- Name: ct_promo_serv_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_promo_serv_type (
    promo_serv_type_id integer DEFAULT nextval('public.seq_promo_serv_type_id'::regclass) NOT NULL,
    promotion_type_id integer NOT NULL,
    service_type_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_promo_serv_type OWNER TO billing_admin;

--
-- Name: TABLE ct_promo_serv_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_promo_serv_type IS 'Table that stores the promotion service relation types of the catalog for the application';


--
-- Name: COLUMN ct_promo_serv_type.promo_serv_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_serv_type.promo_serv_type_id IS 'Internal identifier of the promotion-service relation type';


--
-- Name: COLUMN ct_promo_serv_type.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_serv_type.promotion_type_id IS 'Promotion_type_id for the promotion-service relation';


--
-- Name: COLUMN ct_promo_serv_type.service_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_serv_type.service_type_id IS 'Service_type_id for the promotion-service relation';


--
-- Name: COLUMN ct_promo_serv_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_serv_type.status_id IS 'Status id for the promotion-service relation type';


--
-- Name: COLUMN ct_promo_serv_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_serv_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_promo_serv_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_serv_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_promo_serv_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promo_serv_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_promotion_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_promotion_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_promotion_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_promotion_type_id IS 'sequence for field promotion_type_id in table ct_promotion_type';


--
-- Name: ct_promotion_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_promotion_type (
    promotion_type_id integer DEFAULT nextval('public.seq_promotion_type_id'::regclass) NOT NULL,
    entity_type_id integer DEFAULT 1002 NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    application_level_id integer NOT NULL,
    discount_type_id integer NOT NULL,
    discount_value numeric(10,4) NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_promotion_type OWNER TO billing_admin;

--
-- Name: TABLE ct_promotion_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_promotion_type IS 'Table that stores the promotion types of the catalog for the application';


--
-- Name: COLUMN ct_promotion_type.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.promotion_type_id IS 'Internal identifier of the promotion type';


--
-- Name: COLUMN ct_promotion_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.entity_type_id IS 'Entity_id for the promotion type';


--
-- Name: COLUMN ct_promotion_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.code IS 'Code of the promotion type';


--
-- Name: COLUMN ct_promotion_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.name IS 'Name of the promotion type';


--
-- Name: COLUMN ct_promotion_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.description IS 'Description for the promotion type';


--
-- Name: COLUMN ct_promotion_type.application_level_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.application_level_id IS 'Application level id for the promotion type';


--
-- Name: COLUMN ct_promotion_type.discount_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.discount_type_id IS 'Type of the discount for the promotion type (percent, minutes, units, etc)';


--
-- Name: COLUMN ct_promotion_type.discount_value; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.discount_value IS 'Value of the discount for the promotion type';


--
-- Name: COLUMN ct_promotion_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.status_id IS 'Status id for the promotion type';


--
-- Name: COLUMN ct_promotion_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_promotion_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_promotion_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_promotion_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_serv_fee_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_serv_fee_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_serv_fee_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_serv_fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_serv_fee_type_id IS 'sequence for field serv_fee_type_id in table ct_serv_fee_type';


--
-- Name: ct_serv_fee_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_serv_fee_type (
    serv_fee_type_id integer DEFAULT nextval('public.seq_serv_fee_type_id'::regclass) NOT NULL,
    service_type_id integer NOT NULL,
    fee_type_id integer NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_serv_fee_type OWNER TO billing_admin;

--
-- Name: TABLE ct_serv_fee_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_serv_fee_type IS 'Table that stores the fee service relation types of the catalog for the application';


--
-- Name: COLUMN ct_serv_fee_type.serv_fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_serv_fee_type.serv_fee_type_id IS 'Internal identifier of the service-fee relation type';


--
-- Name: COLUMN ct_serv_fee_type.service_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_serv_fee_type.service_type_id IS 'Product_type_id for the service-fee relation';


--
-- Name: COLUMN ct_serv_fee_type.fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_serv_fee_type.fee_type_id IS 'Service_type_id for the service-fee relation';


--
-- Name: COLUMN ct_serv_fee_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_serv_fee_type.status_id IS 'Status id for the service-fee relation type';


--
-- Name: COLUMN ct_serv_fee_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_serv_fee_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_serv_fee_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_serv_fee_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_serv_fee_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_serv_fee_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_service_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_service_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_service_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_service_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_service_type_id IS 'sequence for field service_type_id in table ct_service_type';


--
-- Name: ct_service_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.ct_service_type (
    service_type_id integer DEFAULT nextval('public.seq_service_type_id'::regclass) NOT NULL,
    entity_type_id integer DEFAULT 1001 NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    status_id integer DEFAULT 1000 NOT NULL,
    input_date timestamp without time zone DEFAULT (('now'::text)::timestamp(0) with time zone)::timestamp without time zone NOT NULL,
    input_user character varying(10) NOT NULL,
    modif_date timestamp without time zone,
    modif_user character varying(10)
);


ALTER TABLE public.ct_service_type OWNER TO billing_admin;

--
-- Name: TABLE ct_service_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.ct_service_type IS 'Table that stores the service types of the catalog for the application';


--
-- Name: COLUMN ct_service_type.service_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.service_type_id IS 'Internal identifier of the service type';


--
-- Name: COLUMN ct_service_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.entity_type_id IS 'Entity_id for the service type';


--
-- Name: COLUMN ct_service_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.code IS 'Code of the service type';


--
-- Name: COLUMN ct_service_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.name IS 'Name of the service type';


--
-- Name: COLUMN ct_service_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.description IS 'Description for the service type';


--
-- Name: COLUMN ct_service_type.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.status_id IS 'Status id for the service type';


--
-- Name: COLUMN ct_service_type.input_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.input_date IS 'Date on which the record was created';


--
-- Name: COLUMN ct_service_type.input_user; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.input_user IS 'User who was modified the record';


--
-- Name: COLUMN ct_service_type.modif_date; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.ct_service_type.modif_date IS 'Date of the last modification of the record';


--
-- Name: seq_profile_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_profile_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_profile_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_profile_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_profile_id IS 'sequence for field profile_id in table it_profiles';


--
-- Name: it_profiles; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.it_profiles (
    profile_id integer DEFAULT nextval('public.seq_profile_id'::regclass) NOT NULL,
    profile_code character varying(10) NOT NULL,
    description character varying(500) NOT NULL
);


ALTER TABLE public.it_profiles OWNER TO billing_admin;

--
-- Name: TABLE it_profiles; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.it_profiles IS 'Table that stores application profiles';


--
-- Name: COLUMN it_profiles.profile_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_profiles.profile_id IS 'InternaL identifier of the profile';


--
-- Name: COLUMN it_profiles.profile_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_profiles.profile_code IS 'Code of the profile';


--
-- Name: COLUMN it_profiles.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_profiles.description IS 'Description for the profile';


--
-- Name: seq_user_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_user_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_user_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_user_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_user_id IS 'sequence for field user_id in table it_users';


--
-- Name: it_users; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.it_users (
    user_id integer DEFAULT nextval('public.seq_user_id'::regclass) NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(100) NOT NULL,
    email character varying(50) NOT NULL,
    phone_contact character varying(50),
    user_code character varying(10) NOT NULL,
    password character(32) NOT NULL,
    profile_id integer NOT NULL,
    active boolean NOT NULL
);


ALTER TABLE public.it_users OWNER TO billing_admin;

--
-- Name: TABLE it_users; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.it_users IS 'Table that stores application users';


--
-- Name: COLUMN it_users.user_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.user_id IS 'InternaL identifier of the user';


--
-- Name: COLUMN it_users.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.name IS 'Name of the user';


--
-- Name: COLUMN it_users.surname; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.surname IS 'Surname of the user';


--
-- Name: COLUMN it_users.email; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.email IS 'Email of the user';


--
-- Name: COLUMN it_users.phone_contact; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.phone_contact IS 'Phone contact of the user';


--
-- Name: COLUMN it_users.user_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.user_code IS 'Code of the user (to login)';


--
-- Name: COLUMN it_users.password; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.password IS 'Password for the user - md5 encrypted';


--
-- Name: COLUMN it_users.profile_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.it_users.profile_id IS 'Internal identifier for the profile of the user';


--
-- Name: seq_application_menu_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_application_menu_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_application_menu_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_application_menu_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_application_menu_id IS 'sequence for field product_type_id in table mt_application_menu';


--
-- Name: mt_application_menu; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.mt_application_menu (
    application_menu_id integer DEFAULT nextval('public.seq_application_menu_id'::regclass) NOT NULL,
    application_parent_menu_id integer,
    has_children boolean NOT NULL,
    menu_level integer NOT NULL,
    "position" integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(500) NOT NULL,
    profile_code character varying(50),
    page character varying(500) NOT NULL
);


ALTER TABLE public.mt_application_menu OWNER TO billing_admin;

--
-- Name: TABLE mt_application_menu; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.mt_application_menu IS 'Table that stores the application''s menu';


--
-- Name: COLUMN mt_application_menu.application_menu_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.application_menu_id IS 'Internal identifier of the menu item';


--
-- Name: COLUMN mt_application_menu.application_parent_menu_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.application_parent_menu_id IS 'Internal identifier of the parent menu item, if exists';


--
-- Name: COLUMN mt_application_menu.has_children; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.has_children IS 'Flag that indicates if the item menu has children (true or false)';


--
-- Name: COLUMN mt_application_menu.menu_level; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.menu_level IS 'Level of the menu item in the menu hierarchy';


--
-- Name: COLUMN mt_application_menu."position"; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu."position" IS 'Position of the menu item in the menu level';


--
-- Name: COLUMN mt_application_menu.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.name IS 'Name to shown in the menu application';


--
-- Name: COLUMN mt_application_menu.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.description IS 'Description of the menu item';


--
-- Name: COLUMN mt_application_menu.profile_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.profile_code IS 'Profiles authoriced to see the menu item';


--
-- Name: COLUMN mt_application_menu.page; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.mt_application_menu.page IS 'JSF to redirect when the menu item is selected';


--
-- Name: seq_application_level_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_application_level_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_application_level_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_application_level_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_application_level_id IS 'sequence for field application_level_id in table pt_application_level';


--
-- Name: pt_application_level; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.pt_application_level (
    application_level_id integer DEFAULT nextval('public.seq_application_level_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL
);


ALTER TABLE public.pt_application_level OWNER TO billing_admin;

--
-- Name: TABLE pt_application_level; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.pt_application_level IS 'Table that stores the application level for the application';


--
-- Name: COLUMN pt_application_level.application_level_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_application_level.application_level_id IS 'Internal identifier of the application level';


--
-- Name: COLUMN pt_application_level.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_application_level.code IS 'Code of the application level';


--
-- Name: COLUMN pt_application_level.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_application_level.name IS 'Name of the application level';


--
-- Name: COLUMN pt_application_level.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_application_level.description IS 'Description for the application level';


--
-- Name: seq_billing_period_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_billing_period_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_billing_period_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_billing_period_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_billing_period_id IS 'sequence for field billing_period_id in table pt_billing_period';


--
-- Name: pt_billing_period; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.pt_billing_period (
    billing_period_id integer DEFAULT nextval('public.seq_billing_period_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    billing_period_days numeric(3,0) NOT NULL
);


ALTER TABLE public.pt_billing_period OWNER TO billing_admin;

--
-- Name: TABLE pt_billing_period; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.pt_billing_period IS 'Table that stores the billing period types for the application';


--
-- Name: COLUMN pt_billing_period.billing_period_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_billing_period.billing_period_id IS 'Internal identifier of the billing period type';


--
-- Name: COLUMN pt_billing_period.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_billing_period.code IS 'Code of the billing period type';


--
-- Name: COLUMN pt_billing_period.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_billing_period.name IS 'Name of the billing period type';


--
-- Name: COLUMN pt_billing_period.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_billing_period.description IS 'Description for the billing period type';


--
-- Name: COLUMN pt_billing_period.billing_period_days; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_billing_period.billing_period_days IS 'Number of days that defines the billing period';


--
-- Name: seq_consumption_class_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_consumption_class_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_consumption_class_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_consumption_class_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_consumption_class_id IS 'sequence for field consumption_class_id in table pt_consumption_class';


--
-- Name: pt_consumption_class; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.pt_consumption_class (
    consumption_class_id integer DEFAULT nextval('public.seq_consumption_class_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL
);


ALTER TABLE public.pt_consumption_class OWNER TO billing_admin;

--
-- Name: TABLE pt_consumption_class; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.pt_consumption_class IS 'Table that stores the consumtion class of the application';


--
-- Name: COLUMN pt_consumption_class.consumption_class_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_consumption_class.consumption_class_id IS 'Internal identifier of the consumption class';


--
-- Name: COLUMN pt_consumption_class.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_consumption_class.code IS 'Code of the consumption class';


--
-- Name: COLUMN pt_consumption_class.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_consumption_class.name IS 'Name of the consumption class';


--
-- Name: COLUMN pt_consumption_class.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_consumption_class.description IS 'Description for the consumption class';


--
-- Name: seq_discount_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_discount_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_discount_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_discount_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_discount_type_id IS 'sequence for field discount_type_id in table pt_discount_type';


--
-- Name: pt_discount_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.pt_discount_type (
    discount_type_id integer DEFAULT nextval('public.seq_discount_type_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL
);


ALTER TABLE public.pt_discount_type OWNER TO billing_admin;

--
-- Name: TABLE pt_discount_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.pt_discount_type IS 'Table that stores the discount types for the application';


--
-- Name: COLUMN pt_discount_type.discount_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_discount_type.discount_type_id IS 'Internal identifier of the discount type';


--
-- Name: COLUMN pt_discount_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_discount_type.code IS 'Code of the discount type';


--
-- Name: COLUMN pt_discount_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_discount_type.name IS 'Name of the discount type';


--
-- Name: COLUMN pt_discount_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_discount_type.description IS 'Description for the discount type';


--
-- Name: seq_entity_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_entity_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_entity_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_entity_type_id IS 'sequence for field entity_type_id in table pt_entity_type';


--
-- Name: pt_entity_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.pt_entity_type (
    entity_type_id integer DEFAULT nextval('public.seq_entity_type_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL
);


ALTER TABLE public.pt_entity_type OWNER TO billing_admin;

--
-- Name: TABLE pt_entity_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.pt_entity_type IS 'Table that stores the entity type of the application';


--
-- Name: COLUMN pt_entity_type.entity_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_entity_type.entity_type_id IS 'Internal identifier of the entity type';


--
-- Name: COLUMN pt_entity_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_entity_type.code IS 'Code of the entity type';


--
-- Name: COLUMN pt_entity_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_entity_type.name IS 'Name of the entity type';


--
-- Name: COLUMN pt_entity_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_entity_type.description IS 'Description for the entity type';


--
-- Name: seq_status_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_status_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_status_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_status_id IS 'sequence for field status_id in table pt_status';


--
-- Name: pt_status; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.pt_status (
    status_id integer DEFAULT nextval('public.seq_status_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL
);


ALTER TABLE public.pt_status OWNER TO billing_admin;

--
-- Name: TABLE pt_status; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.pt_status IS 'Table that stores the status for the entities of the application';


--
-- Name: COLUMN pt_status.status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_status.status_id IS 'Internal identifier of the status';


--
-- Name: COLUMN pt_status.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_status.code IS 'Code of the status';


--
-- Name: COLUMN pt_status.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_status.name IS 'Name of the status';


--
-- Name: COLUMN pt_status.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_status.description IS 'Description for the status';


--
-- Name: seq_tax_type_id; Type: SEQUENCE; Schema: public; Owner: billing_admin
--

CREATE SEQUENCE public.seq_tax_type_id
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_tax_type_id OWNER TO billing_admin;

--
-- Name: SEQUENCE seq_tax_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON SEQUENCE public.seq_tax_type_id IS 'sequence for field tax_type_id in table pt_tax_type';


--
-- Name: pt_tax_type; Type: TABLE; Schema: public; Owner: billing_admin
--

CREATE TABLE public.pt_tax_type (
    tax_type_id integer DEFAULT nextval('public.seq_tax_type_id'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    percent_value numeric(10,4) NOT NULL
);


ALTER TABLE public.pt_tax_type OWNER TO billing_admin;

--
-- Name: TABLE pt_tax_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON TABLE public.pt_tax_type IS 'Table that stores the tax type of the application';


--
-- Name: COLUMN pt_tax_type.tax_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_tax_type.tax_type_id IS 'Internal identifier of the tax type';


--
-- Name: COLUMN pt_tax_type.code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_tax_type.code IS 'Code of the tax type';


--
-- Name: COLUMN pt_tax_type.name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_tax_type.name IS 'Name of the tax type';


--
-- Name: COLUMN pt_tax_type.description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_tax_type.description IS 'Description for the tax type';


--
-- Name: COLUMN pt_tax_type.percent_value; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.pt_tax_type.percent_value IS 'Percent value for the tax type';


--
-- Name: vw_product_fee_type; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_product_fee_type AS
 SELECT cpft.prod_fee_type_id,
    cpft.status_id AS prod_fee_type_status_id,
    psr.code AS prod_fee_type_status_code,
    cpt.product_type_id,
    cpt.code AS product_type_code,
    cpt.name AS product_type_name,
    cpt.description AS product_type_description,
    cpt.status_id AS product_type_status_id,
    psp.code AS product_type_status_code,
    cft.fee_type_id,
    cft.code AS fee_type_code,
    cft.name AS fee_type_name,
    cft.description AS fee_type_description,
    cft.prorate,
    cft.price,
    cft.status_id AS fee_type_status_id,
    psf.code AS fee_type_status_code
   FROM (((((public.ct_product_type cpt
     JOIN public.pt_status psp ON ((cpt.status_id = psp.status_id)))
     JOIN public.ct_prod_fee_type cpft ON ((cpt.product_type_id = cpft.product_type_id)))
     JOIN public.pt_status psr ON ((cpft.status_id = psr.status_id)))
     JOIN public.ct_fee_type cft ON ((cft.fee_type_id = cpft.fee_type_id)))
     JOIN public.pt_status psf ON ((cpft.status_id = psf.status_id)));


ALTER TABLE public.vw_product_fee_type OWNER TO billing_admin;

--
-- Name: VIEW vw_product_fee_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON VIEW public.vw_product_fee_type IS 'View showing the relationship between product types and the fee types associated with them - related to ct_prod_fee_type';


--
-- Name: COLUMN vw_product_fee_type.prod_fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.prod_fee_type_id IS 'prod_fee_type_id identifier of the product-fee type relation (field prod_fee_type_id from ct_prod_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.prod_fee_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.prod_fee_type_status_id IS 'Status id of the product-fee type relation (field status_id from ct_prod_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.prod_fee_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.prod_fee_type_status_code IS 'Status code of the product-fee type relation';


--
-- Name: COLUMN vw_product_fee_type.product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.product_type_id IS 'Product_type_id for the product-fee type relation (field product_type_id from ct_product_type table)';


--
-- Name: COLUMN vw_product_fee_type.product_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.product_type_code IS 'Code for the product type (field code from ct_product_type table)';


--
-- Name: COLUMN vw_product_fee_type.product_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.product_type_name IS 'Name for the product type (field name from ct_product_type table)';


--
-- Name: COLUMN vw_product_fee_type.product_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.product_type_description IS 'Description for the product type (field description from ct_product_type table)';


--
-- Name: COLUMN vw_product_fee_type.product_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.product_type_status_id IS 'Status_id for the product type (field status_id from ct_product_type table)';


--
-- Name: COLUMN vw_product_fee_type.product_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.product_type_status_code IS 'Status code for the product_type';


--
-- Name: COLUMN vw_product_fee_type.fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.fee_type_id IS 'Fee_type_id for the product-fee type relation (field fee_type_id from ct_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.fee_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.fee_type_code IS 'Code for the fee type (field code from ct_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.fee_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.fee_type_name IS 'Name for the fee type (field name from ct_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.fee_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.fee_type_description IS 'Description for the fee type (field description from ct_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.prorate; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.prorate IS 'Flag that indicates if the fee type is prorated (true) or not (false)(field prorate from ct_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.price; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.price IS 'Price for the fee type (field price from ct_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.fee_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.fee_type_status_id IS 'Status_id for the fee type (field status_id from ct_fee_type table)';


--
-- Name: COLUMN vw_product_fee_type.fee_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_fee_type.fee_type_status_code IS 'Status code for the fee type';


--
-- Name: vw_product_service_type; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_product_service_type AS
 SELECT cpst.prod_serv_type_id,
    cpst.status_id AS prod_serv_type_status_id,
    psr.code AS prod_serv_type_status_code,
    cpt.product_type_id,
    cpt.code AS product_type_code,
    cpt.name AS product_type_name,
    cpt.description AS product_type_description,
    cpt.status_id AS product_type_status_id,
    psp.code AS product_type_status_code,
    cst.service_type_id,
    cst.code AS service_type_code,
    cst.name AS service_type_name,
    cst.description AS service_type_description,
    cst.status_id AS service_type_status_id,
    pss.code AS service_type_status_code
   FROM (((((public.ct_product_type cpt
     JOIN public.pt_status psp ON ((cpt.status_id = psp.status_id)))
     JOIN public.ct_prod_serv_type cpst ON ((cpt.product_type_id = cpst.product_type_id)))
     JOIN public.pt_status psr ON ((cpst.status_id = psr.status_id)))
     JOIN public.ct_service_type cst ON ((cst.service_type_id = cpst.service_type_id)))
     JOIN public.pt_status pss ON ((cst.status_id = pss.status_id)));


ALTER TABLE public.vw_product_service_type OWNER TO billing_admin;

--
-- Name: VIEW vw_product_service_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON VIEW public.vw_product_service_type IS 'View showing the relationship between product types and the service types associated with them - related to ct_prod_serv_type';


--
-- Name: COLUMN vw_product_service_type.prod_serv_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.prod_serv_type_id IS 'prod_serv_type_id identifier of the product-service type relation (field prod_serv_type_id from ct_prod_serv_type table)';


--
-- Name: COLUMN vw_product_service_type.prod_serv_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.prod_serv_type_status_id IS 'Status id of the product-service type relation (field status_id from ct_prod_serv_type table)';


--
-- Name: COLUMN vw_product_service_type.prod_serv_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.prod_serv_type_status_code IS 'Status code of the product-service type relation';


--
-- Name: COLUMN vw_product_service_type.product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.product_type_id IS 'Product_type_id for the product-service type relation (field product_type_id from ct_product_type table)';


--
-- Name: COLUMN vw_product_service_type.product_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.product_type_code IS 'Code for the product type (field code from ct_product_type table)';


--
-- Name: COLUMN vw_product_service_type.product_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.product_type_name IS 'Name for the product type (field name from ct_product_type table)';


--
-- Name: COLUMN vw_product_service_type.product_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.product_type_description IS 'Description for the product type (field description from ct_product_type table)';


--
-- Name: COLUMN vw_product_service_type.product_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.product_type_status_id IS 'Status_id for the product type (field status_id from ct_product_type table)';


--
-- Name: COLUMN vw_product_service_type.product_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.product_type_status_code IS 'Status code for the product_type';


--
-- Name: COLUMN vw_product_service_type.service_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.service_type_id IS 'Service_type_id for the product-service type relation (field service_type_id from ct_service_type table)';


--
-- Name: COLUMN vw_product_service_type.service_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.service_type_code IS 'Code for the service type (field code from ct_service_type table)';


--
-- Name: COLUMN vw_product_service_type.service_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.service_type_name IS 'Name for the service type (field name from ct_service_type table)';


--
-- Name: COLUMN vw_product_service_type.service_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.service_type_description IS 'Description for the service type (field description from ct_service_type table)';


--
-- Name: COLUMN vw_product_service_type.service_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.service_type_status_id IS 'Status_id for the service type (field status_id from ct_service_type table)';


--
-- Name: COLUMN vw_product_service_type.service_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_product_service_type.service_type_status_code IS 'Status code for the service type';


--
-- Name: vw_promo_consum_type_discount; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_promo_consum_type_discount AS
 SELECT cpctd.promo_consum_type_discount_id,
    cpctd.status_id AS promo_consum_type_disc_status_id,
    psr.code AS promo_consum_type_disc_status_code,
    cpctd.promotion_type_id,
    cpt.code AS promotion_type_code,
    cpt.name AS promotion_type_name,
    cpt.description AS promotion_type_description,
    cpt.discount_type_id,
    pdt.code AS discount_type_code,
    pdt.name AS discount_type_name,
    pdt.description AS discount_type_description,
    cpt.discount_value AS promotion_type_discount_value,
    cpt.status_id AS promotion_type_status_id,
    psp.code AS promotion_type_status_code,
    cpctd.consumption_type_id,
    cct.code AS consumption_type_code,
    cct.name AS consumption_type_name,
    cct.description AS consumption_type_description,
    cct.consumption_class_id,
    pcc.code AS consumption_class_code,
    pcc.name AS consumption_class_name,
    pcc.description AS consumption_class_description,
    cct.status_id AS consumption_type_status_id,
    psc.code AS consumption_type_status_code
   FROM (((((((public.ct_promotion_type cpt
     JOIN public.pt_status psp ON ((psp.status_id = cpt.status_id)))
     JOIN public.ct_promo_consum_type_discount cpctd ON ((cpt.promotion_type_id = cpctd.promotion_type_id)))
     JOIN public.pt_status psr ON ((cpctd.status_id = psr.status_id)))
     JOIN public.pt_discount_type pdt ON ((pdt.discount_type_id = cpt.discount_type_id)))
     JOIN public.ct_consumption_type cct ON ((cct.consumption_type_id = cpctd.consumption_type_id)))
     JOIN public.pt_consumption_class pcc ON ((pcc.consumption_class_id = cct.consumption_class_id)))
     JOIN public.pt_status psc ON ((psc.status_id = cct.status_id)))
  WHERE (cpt.application_level_id IN ( SELECT pal2.application_level_id
           FROM public.pt_application_level pal2
          WHERE ((pal2.code)::text = 'SERV'::text)));


ALTER TABLE public.vw_promo_consum_type_discount OWNER TO billing_admin;

--
-- Name: VIEW vw_promo_consum_type_discount; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON VIEW public.vw_promo_consum_type_discount IS 'View showing the relationship between promotion types and the fee types that can be discount by the promotion type - related to ct_promo_consum_type_discount';


--
-- Name: COLUMN vw_promo_consum_type_discount.promo_consum_type_discount_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promo_consum_type_discount_id IS 'promo_consum_type_discount_id identifier of the promotion-consumption discount type relation (field promo_consum_type_discount_id from ct_promo_consum_type_discount table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promo_consum_type_disc_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promo_consum_type_disc_status_id IS 'Status id of the promotion-consumption discount type relation (field status_id from ct_prod_prom_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promo_consum_type_disc_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promo_consum_type_disc_status_code IS 'Status code of the promotion-consumption discount type relation';


--
-- Name: COLUMN vw_promo_consum_type_discount.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promotion_type_id IS 'Promotion_type_id for the promotion-consumption discount type relation (field promotion_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promotion_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promotion_type_code IS 'Code for the promotion type (field code from ct_promotion_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promotion_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promotion_type_name IS 'Name for the promotion type (field name from ct_promotion_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promotion_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promotion_type_description IS 'Description for the promotion type (field description from ct_promotion_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.discount_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.discount_type_id IS 'Discount_type_id for the promotion type (field discount_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.discount_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.discount_type_code IS 'Code for the discount type for the promotion type (field code from pt_discount_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.discount_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.discount_type_name IS 'Name for the discount type for the promotion type (field name from pt_discount_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.discount_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.discount_type_description IS 'Description for the discount type for the promotion type (field description from pt_discount_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promotion_type_discount_value; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promotion_type_discount_value IS 'discount_value for the discount type for the promotion type (field discount_value from ct_promotion_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promotion_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promotion_type_status_id IS 'Status_id for the promotion type (field status_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.promotion_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.promotion_type_status_code IS 'Status code for the promotion_type';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_type_id IS 'Consumption_type_id for the promotion-consumption discount type relation (field fee_type_id from ct_consumption_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_type_code IS 'Code for the consumption type (field code from ct_consumption_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_type_name IS 'Name for the consumption type (field name from ct_consumption_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_type_description IS 'Description for the consumption type (field description from ct_consumption_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_class_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_class_id IS 'Consumption_class_id for the consumption type (field consumption_class_id from pt_consumption_class table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_class_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_class_code IS 'Code for the consumption type (field code from pt_consumption_class table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_class_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_class_name IS 'Name for the consumption type (field name from pt_consumption_class table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_class_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_class_description IS 'Description for the consumption type (field description from pt_consumption_class table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_type_status_id IS 'Status_id for the consumption type (field status_id from ct_consumption_type table)';


--
-- Name: COLUMN vw_promo_consum_type_discount.consumption_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promo_consum_type_discount.consumption_type_status_code IS 'Status code for the consumption type';


--
-- Name: vw_promotion_fee_type_discount; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_promotion_fee_type_discount AS
 SELECT cpfdt.promo_fee_type_discount_id,
    cpfdt.status_id AS promo_fee_type_disc_status_id,
    psr.code AS promo_fee_type_disc_status_code,
    cpt.promotion_type_id,
    cpt.code AS promotion_type_code,
    cpt.name AS promotion_type_name,
    cpt.description AS promotion_type_description,
    cpt.application_level_id,
    pal.code AS application_level_code,
    cpt.discount_type_id,
    pdt.code AS discount_type_code,
    pdt.name AS discount_type_name,
    pdt.description AS discount_type_description,
    pal.description AS application_level_description,
    cpt.discount_value AS promotion_type_discount_value,
    cpt.status_id AS promotion_type_status_id,
    psp.code AS promotion_type_status_code,
    cft.fee_type_id,
    cft.code AS fee_type_code,
    cft.name AS fee_type_name,
    cft.description AS fee_type_description,
    cft.prorate,
    cft.price,
    cft.status_id AS fee_type_status_id,
    psf.code AS fee_type_status_code
   FROM (((((((public.ct_promotion_type cpt
     JOIN public.pt_status psp ON ((cpt.status_id = psp.status_id)))
     JOIN public.pt_application_level pal ON ((cpt.application_level_id = pal.application_level_id)))
     JOIN public.pt_discount_type pdt ON ((pdt.discount_type_id = cpt.discount_type_id)))
     JOIN public.ct_promo_fee_type_discount cpfdt ON ((cpt.promotion_type_id = cpfdt.promotion_type_id)))
     JOIN public.pt_status psr ON (((cpfdt.status_id = psr.status_id) AND (cpt.application_level_id = cpfdt.application_level_id))))
     JOIN public.ct_fee_type cft ON (((cft.fee_type_id = cpfdt.fee_type_id) AND (cft.application_level_id = cpfdt.application_level_id))))
     JOIN public.pt_status psf ON ((cft.status_id = psf.status_id)));


ALTER TABLE public.vw_promotion_fee_type_discount OWNER TO billing_admin;

--
-- Name: VIEW vw_promotion_fee_type_discount; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON VIEW public.vw_promotion_fee_type_discount IS 'View showing the relationship between promotion types and the fee types that can be discount by the promotion type - related to ct_promo_fee_type_discount';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promo_fee_type_discount_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promo_fee_type_discount_id IS 'promo_fee_type_discount_id identifier of the promotion-fee discount type relation (field promo_fee_type_discount_id from ct_promo_fee_type_discount table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promo_fee_type_disc_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promo_fee_type_disc_status_id IS 'Status id of the promotion-fee discount type relation (field status_id from ct_prod_prom_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promo_fee_type_disc_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promo_fee_type_disc_status_code IS 'Status code of the promotion-fee discount type relation';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promotion_type_id IS 'Promotion_type_id for the promotion-fee discount type relation (field promotion_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promotion_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promotion_type_code IS 'Code for the promotion type (field code from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promotion_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promotion_type_name IS 'Name for the promotion type (field name from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promotion_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promotion_type_description IS 'Description for the promotion type (field description from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.application_level_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.application_level_id IS 'Application_level_id for the promotion type (field application_level_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.application_level_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.application_level_code IS 'Code for the application level of the promotion type (field code from pt_application_level table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.discount_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.discount_type_id IS 'Discount_type_id for the promotion type (field discount_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.discount_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.discount_type_code IS 'Code for the discount type for the promotion type (field code from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.discount_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.discount_type_name IS 'Name for the discount type for the promotion type (field name from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.discount_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.discount_type_description IS 'Description for the discount type for the promotion type (field description from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.application_level_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.application_level_description IS 'Description for the application level of the promotion type (field description from pt_application_level table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promotion_type_discount_value; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promotion_type_discount_value IS 'discount_value for the discount type for the promotion type (field discount_value from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promotion_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promotion_type_status_id IS 'Status_id for the promotion type (field status_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.promotion_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.promotion_type_status_code IS 'Status code for the promotion_type';


--
-- Name: COLUMN vw_promotion_fee_type_discount.fee_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.fee_type_id IS 'Fee_type_id for the promotion-fee discount type relation (field fee_type_id from ct_fee_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.fee_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.fee_type_code IS 'Code for the fee type (field code from ct_fee_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.fee_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.fee_type_name IS 'Name for the fee type (field name from ct_fee_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.fee_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.fee_type_description IS 'Description for the fee type (field description from ct_fee_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.fee_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.fee_type_status_id IS 'Status_id for the fee type (field status_id from ct_fee_type table)';


--
-- Name: COLUMN vw_promotion_fee_type_discount.fee_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_fee_type_discount.fee_type_status_code IS 'Status code for the fee type';


--
-- Name: vw_promotion_product_type; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_promotion_product_type AS
 SELECT cppt.promo_prod_type_id,
    cppt.status_id AS promo_prod_type_status_id,
    psr.code AS promo_prod_type_status_code,
    cpt.product_type_id,
    cpt.code AS product_type_code,
    cpt.name AS product_type_name,
    cpt.description AS product_type_description,
    cpt.status_id AS product_type_status_id,
    psp.code AS product_type_status_code,
    cpt2.promotion_type_id,
    cpt2.code AS promotion_type_code,
    cpt2.name AS promotion_type_name,
    cpt2.description AS promotion_type_description,
    cpt2.discount_type_id,
    pdt.code AS discount_code,
    pdt.name AS discount_name,
    pdt.description AS discount_description,
    cpt2.discount_value,
    cpt2.status_id AS promotion_type_status_id,
    psp2.code AS promotion_type_status_code
   FROM ((((((public.ct_product_type cpt
     JOIN public.pt_status psp ON ((cpt.status_id = psp.status_id)))
     JOIN public.ct_promo_prod_type cppt ON ((cpt.product_type_id = cppt.product_type_id)))
     JOIN public.pt_status psr ON ((cppt.status_id = psr.status_id)))
     JOIN public.ct_promotion_type cpt2 ON ((cppt.promotion_type_id = cpt2.promotion_type_id)))
     JOIN public.pt_status psp2 ON ((cpt2.status_id = psp2.status_id)))
     JOIN public.pt_discount_type pdt ON ((pdt.discount_type_id = cpt2.discount_type_id)));


ALTER TABLE public.vw_promotion_product_type OWNER TO billing_admin;

--
-- Name: VIEW vw_promotion_product_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON VIEW public.vw_promotion_product_type IS 'View showing the relationship between product types and the promotion types associated with them - related to ct_promo_prod_type';


--
-- Name: COLUMN vw_promotion_product_type.promo_prod_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promo_prod_type_id IS 'promo_prod_type_id identifier of the product-promotion type relation (field promo_prod_type_id from ct_promo_prod_type table)';


--
-- Name: COLUMN vw_promotion_product_type.promo_prod_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promo_prod_type_status_id IS 'Status id of the product-promotion type relation (field status_id from ct_prod_prom_type table)';


--
-- Name: COLUMN vw_promotion_product_type.promo_prod_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promo_prod_type_status_code IS 'Status code of the product-promotion type relation';


--
-- Name: COLUMN vw_promotion_product_type.product_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.product_type_id IS 'Product_type_id for the product-promotion type relation (field product_type_id from ct_product_type table)';


--
-- Name: COLUMN vw_promotion_product_type.product_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.product_type_code IS 'Code for the product type (field code from ct_product_type table)';


--
-- Name: COLUMN vw_promotion_product_type.product_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.product_type_name IS 'Name for the product type (field name from ct_product_type table)';


--
-- Name: COLUMN vw_promotion_product_type.product_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.product_type_description IS 'Description for the product type (field description from ct_product_type table)';


--
-- Name: COLUMN vw_promotion_product_type.product_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.product_type_status_id IS 'Status_id for the product type (field status_id from ct_product_type table)';


--
-- Name: COLUMN vw_promotion_product_type.product_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.product_type_status_code IS 'Status code for the product_type';


--
-- Name: COLUMN vw_promotion_product_type.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promotion_type_id IS 'Service_type_id for the product-promotion type relation (field promotion_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_product_type.promotion_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promotion_type_code IS 'Code for the promotion type (field code from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_product_type.promotion_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promotion_type_name IS 'Name for the promotion type (field name from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_product_type.promotion_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promotion_type_description IS 'Description for the promotion type (field description from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_product_type.discount_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.discount_type_id IS 'Discount_type_id for the promotion type (field discount_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_product_type.discount_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.discount_code IS 'Code for the discount type for the promotion type (field code from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_product_type.discount_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.discount_name IS 'Name for the discount type for the promotion type (field name from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_product_type.discount_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.discount_description IS 'Description for the discount type for the promotion type (field description from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_product_type.discount_value; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.discount_value IS 'discount_value for the discount type for the promotion type (field discount_value from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_product_type.promotion_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promotion_type_status_id IS 'Status_id for the promotion type (field status_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_product_type.promotion_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_product_type.promotion_type_status_code IS 'Status code for the promotion type';


--
-- Name: vw_promotion_service_type; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_promotion_service_type AS
 SELECT cpst.promo_serv_type_id,
    cpst.status_id AS prom_serv_type_status_id,
    psr.code AS prom_serv_type_status_code,
    cst.service_type_id,
    cst.code AS service_type_code,
    cst.name AS service_type_name,
    cst.description AS service_type_description,
    cst.status_id AS service_type_status_id,
    psp.code AS service_type_status_code,
    cpt.promotion_type_id,
    cpt.code AS promotion_type_code,
    cpt.name AS promotion_type_name,
    cpt.description AS promotion_type_description,
    cpt.discount_type_id,
    pdt.code AS discount_code,
    pdt.name AS discount_name,
    pdt.description AS discount_description,
    cpt.discount_value,
    cpt.status_id AS promotion_type_status_id,
    psp.code AS promotion_type_status_code
   FROM ((((((public.ct_service_type cst
     JOIN public.pt_status pss ON ((cst.status_id = pss.status_id)))
     JOIN public.ct_promo_serv_type cpst ON ((cst.service_type_id = cpst.service_type_id)))
     JOIN public.pt_status psr ON ((cpst.status_id = psr.status_id)))
     JOIN public.ct_promotion_type cpt ON ((cpst.promotion_type_id = cpt.promotion_type_id)))
     JOIN public.pt_status psp ON ((cpt.status_id = psp.status_id)))
     JOIN public.pt_discount_type pdt ON ((pdt.discount_type_id = cpt.discount_type_id)));


ALTER TABLE public.vw_promotion_service_type OWNER TO billing_admin;

--
-- Name: VIEW vw_promotion_service_type; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON VIEW public.vw_promotion_service_type IS 'View showing the relationship between service types and the promotion types associated with them - related to ct_promo_serv_type';


--
-- Name: COLUMN vw_promotion_service_type.promo_serv_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.promo_serv_type_id IS 'promo_serv_type_id identifier of the service-promotion type relation (field promo_serv_type_id from ct_promo_serv_type table)';


--
-- Name: COLUMN vw_promotion_service_type.prom_serv_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.prom_serv_type_status_id IS 'Status id of the service-promotion type relation (field status_id from ct_prod_prom_type table)';


--
-- Name: COLUMN vw_promotion_service_type.prom_serv_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.prom_serv_type_status_code IS 'Status code of the service-promotion type relation';


--
-- Name: COLUMN vw_promotion_service_type.service_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.service_type_id IS 'Service_type_id for the service-promotion type relation (field service_type_id from ct_service_type table)';


--
-- Name: COLUMN vw_promotion_service_type.service_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.service_type_code IS 'Code for the service type (field code from ct_service_type table)';


--
-- Name: COLUMN vw_promotion_service_type.service_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.service_type_name IS 'Name for the service type (field name from ct_service_type table)';


--
-- Name: COLUMN vw_promotion_service_type.service_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.service_type_description IS 'Description for the service type (field description from ct_service_type table)';


--
-- Name: COLUMN vw_promotion_service_type.service_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.service_type_status_id IS 'Status_id for the service type (field status_id from ct_service_type table)';


--
-- Name: COLUMN vw_promotion_service_type.service_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.service_type_status_code IS 'Status code for the service_type';


--
-- Name: COLUMN vw_promotion_service_type.promotion_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.promotion_type_id IS 'Promotion_type_id for the service-promotion type relation (field promotion_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_service_type.promotion_type_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.promotion_type_code IS 'Code for the promotion type (field code from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_service_type.promotion_type_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.promotion_type_name IS 'Name for the promotion type (field name from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_service_type.promotion_type_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.promotion_type_description IS 'Description for the promotion type (field description from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_service_type.discount_type_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.discount_type_id IS 'Discount_type_id for the promotion type (field discount_type_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_service_type.discount_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.discount_code IS 'Code for the discount type for the promotion type (field code from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_service_type.discount_name; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.discount_name IS 'Name for the discount type for the promotion type (field name from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_service_type.discount_description; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.discount_description IS 'Description for the discount type for the promotion type (field description from pt_discount_type table)';


--
-- Name: COLUMN vw_promotion_service_type.discount_value; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.discount_value IS 'discount_value for the discount type for the promotion type (field discount_value from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_service_type.promotion_type_status_id; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.promotion_type_status_id IS 'Status_id for the promotion type (field status_id from ct_promotion_type table)';


--
-- Name: COLUMN vw_promotion_service_type.promotion_type_status_code; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_promotion_service_type.promotion_type_status_code IS 'Status code for the promotion type';


--
-- Name: vw_service_fee_type; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_service_fee_type AS
 SELECT csft.serv_fee_type_id,
    csft.status_id AS serv_fee_type_status_id,
    psr.code AS serv_fee_type_status_code,
    cst.service_type_id,
    cst.code AS service_type_code,
    cst.name AS service_type_name,
    cst.description AS service_type_description,
    cst.status_id AS service_type_status_id,
    pss.code AS service_type_status_code,
    cft.fee_type_id,
    cft.code AS fee_type_code,
    cft.name AS fee_type_name,
    cft.description AS fee_type_description,
    cft.prorate,
    cft.price,
    cft.status_id AS fee_type_status_id,
    psf.code AS fee_type_status_code
   FROM (((((public.ct_service_type cst
     JOIN public.pt_status pss ON ((cst.status_id = pss.status_id)))
     JOIN public.ct_serv_fee_type csft ON ((cst.service_type_id = csft.service_type_id)))
     JOIN public.pt_status psr ON ((csft.status_id = psr.status_id)))
     JOIN public.ct_fee_type cft ON ((cft.fee_type_id = csft.fee_type_id)))
     JOIN public.pt_status psf ON ((cst.status_id = psf.status_id)));


ALTER TABLE public.vw_service_fee_type OWNER TO billing_admin;

--
-- Name: COLUMN vw_service_fee_type.prorate; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_service_fee_type.prorate IS 'Flag that indicates if the fee type is prorated (true) or not (false)(field prorate from ct_fee_type table)';


--
-- Name: COLUMN vw_service_fee_type.price; Type: COMMENT; Schema: public; Owner: billing_admin
--

COMMENT ON COLUMN public.vw_service_fee_type.price IS 'Price for the fee type (field price from ct_fee_type table)';


--
-- Name: vw_users; Type: VIEW; Schema: public; Owner: billing_admin
--

CREATE VIEW public.vw_users AS
 SELECT u.user_id,
    u.user_code,
    u.password,
    u.profile_id,
    p.profile_code
   FROM (public.it_users u
     JOIN public.it_profiles p ON ((u.profile_id = p.profile_id)))
  WHERE (u.active = true);


ALTER TABLE public.vw_users OWNER TO billing_admin;

--
-- Name: ct_account_type ct_account_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_account_type
    ADD CONSTRAINT ct_account_type_code_u UNIQUE (code);


--
-- Name: ct_account_type ct_account_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_account_type
    ADD CONSTRAINT ct_account_type_name_u UNIQUE (name);


--
-- Name: ct_account_type ct_account_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_account_type
    ADD CONSTRAINT ct_account_type_pk PRIMARY KEY (account_type_id);


--
-- Name: ct_bill_cycle_type ct_bill_cycle_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_bill_cycle_type
    ADD CONSTRAINT ct_bill_cycle_type_code_u UNIQUE (code);


--
-- Name: ct_bill_cycle_type ct_bill_cycle_type_codnum_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_bill_cycle_type
    ADD CONSTRAINT ct_bill_cycle_type_codnum_u UNIQUE (bill_cycle_codenum);


--
-- Name: ct_bill_cycle_type ct_bill_cycle_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_bill_cycle_type
    ADD CONSTRAINT ct_bill_cycle_type_name_u UNIQUE (name);


--
-- Name: ct_bill_cycle_type ct_bill_cycle_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_bill_cycle_type
    ADD CONSTRAINT ct_bill_cycle_type_pk PRIMARY KEY (bill_cycle_type_id);


--
-- Name: ct_consumption_type ct_consumption_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_consumption_type
    ADD CONSTRAINT ct_consumption_type_code_u UNIQUE (code);


--
-- Name: ct_consumption_type ct_consumption_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_consumption_type
    ADD CONSTRAINT ct_consumption_type_name_u UNIQUE (name);


--
-- Name: ct_consumption_type ct_consumption_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_consumption_type
    ADD CONSTRAINT ct_consumption_type_pk PRIMARY KEY (consumption_type_id);


--
-- Name: ct_customer_type ct_customer_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_customer_type
    ADD CONSTRAINT ct_customer_type_code_u UNIQUE (code);


--
-- Name: ct_customer_type ct_customer_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_customer_type
    ADD CONSTRAINT ct_customer_type_name_u UNIQUE (name);


--
-- Name: ct_customer_type ct_customer_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_customer_type
    ADD CONSTRAINT ct_customer_type_pk PRIMARY KEY (customer_type_id);


--
-- Name: ct_fee_type ct_fee_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_fee_type
    ADD CONSTRAINT ct_fee_type_code_u UNIQUE (code);


--
-- Name: ct_fee_type ct_fee_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_fee_type
    ADD CONSTRAINT ct_fee_type_name_u UNIQUE (name);


--
-- Name: ct_fee_type ct_fee_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_fee_type
    ADD CONSTRAINT ct_fee_type_pk PRIMARY KEY (fee_type_id);


--
-- Name: ct_prod_fee_type ct_prod_fee_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_fee_type
    ADD CONSTRAINT ct_prod_fee_type_code_u UNIQUE (product_type_id, fee_type_id);


--
-- Name: ct_prod_fee_type ct_prod_fee_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_fee_type
    ADD CONSTRAINT ct_prod_fee_type_pk PRIMARY KEY (prod_fee_type_id);


--
-- Name: ct_prod_serv_type ct_prod_serv_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_serv_type
    ADD CONSTRAINT ct_prod_serv_type_code_u UNIQUE (product_type_id, service_type_id);


--
-- Name: ct_prod_serv_type ct_prod_serv_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_serv_type
    ADD CONSTRAINT ct_prod_serv_type_pk PRIMARY KEY (prod_serv_type_id);


--
-- Name: ct_product_type ct_product_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_product_type
    ADD CONSTRAINT ct_product_type_code_u UNIQUE (code);


--
-- Name: ct_product_type ct_product_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_product_type
    ADD CONSTRAINT ct_product_type_name_u UNIQUE (name);


--
-- Name: ct_product_type ct_product_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_product_type
    ADD CONSTRAINT ct_product_type_pk PRIMARY KEY (product_type_id);


--
-- Name: ct_promo_consum_type_discount ct_promo_consum_type_discount_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_consum_type_discount
    ADD CONSTRAINT ct_promo_consum_type_discount_code_u UNIQUE (promotion_type_id, consumption_type_id);


--
-- Name: ct_promo_consum_type_discount ct_promo_consum_type_discount_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_consum_type_discount
    ADD CONSTRAINT ct_promo_consum_type_discount_pk PRIMARY KEY (promo_consum_type_discount_id);


--
-- Name: ct_promo_fee_type_discount ct_promo_fee_type_discount_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_fee_type_discount
    ADD CONSTRAINT ct_promo_fee_type_discount_code_u UNIQUE (promotion_type_id, fee_type_id);


--
-- Name: ct_promo_fee_type_discount ct_promo_fee_type_discount_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_fee_type_discount
    ADD CONSTRAINT ct_promo_fee_type_discount_pk PRIMARY KEY (promo_fee_type_discount_id);


--
-- Name: ct_promo_prod_type ct_promo_prod_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_prod_type
    ADD CONSTRAINT ct_promo_prod_type_code_u UNIQUE (promotion_type_id, product_type_id);


--
-- Name: ct_promo_prod_type ct_promo_prod_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_prod_type
    ADD CONSTRAINT ct_promo_prod_type_pk PRIMARY KEY (promo_prod_type_id);


--
-- Name: ct_promo_serv_type ct_promo_serv_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_serv_type
    ADD CONSTRAINT ct_promo_serv_type_code_u UNIQUE (promotion_type_id, service_type_id);


--
-- Name: ct_promo_serv_type ct_promo_serv_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_serv_type
    ADD CONSTRAINT ct_promo_serv_type_pk PRIMARY KEY (promo_serv_type_id);


--
-- Name: ct_promotion_type ct_promotion_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promotion_type
    ADD CONSTRAINT ct_promotion_type_code_u UNIQUE (code);


--
-- Name: ct_promotion_type ct_promotion_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promotion_type
    ADD CONSTRAINT ct_promotion_type_name_u UNIQUE (name);


--
-- Name: ct_promotion_type ct_promotion_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promotion_type
    ADD CONSTRAINT ct_promotion_type_pk PRIMARY KEY (promotion_type_id);


--
-- Name: ct_serv_fee_type ct_serv_fee_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_serv_fee_type
    ADD CONSTRAINT ct_serv_fee_type_code_u UNIQUE (service_type_id, fee_type_id);


--
-- Name: ct_serv_fee_type ct_serv_fee_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_serv_fee_type
    ADD CONSTRAINT ct_serv_fee_type_pk PRIMARY KEY (serv_fee_type_id);


--
-- Name: ct_service_type ct_service_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_service_type
    ADD CONSTRAINT ct_service_type_code_u UNIQUE (code);


--
-- Name: ct_service_type ct_service_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_service_type
    ADD CONSTRAINT ct_service_type_name_u UNIQUE (name);


--
-- Name: ct_service_type ct_service_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_service_type
    ADD CONSTRAINT ct_service_type_pk PRIMARY KEY (service_type_id);


--
-- Name: it_profiles it_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.it_profiles
    ADD CONSTRAINT it_profiles_pkey PRIMARY KEY (profile_id);


--
-- Name: it_profiles it_profiles_profile_code_key; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.it_profiles
    ADD CONSTRAINT it_profiles_profile_code_key UNIQUE (profile_code);


--
-- Name: it_users it_users_pkey; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.it_users
    ADD CONSTRAINT it_users_pkey PRIMARY KEY (user_id);


--
-- Name: it_users it_users_user_code_key; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.it_users
    ADD CONSTRAINT it_users_user_code_key UNIQUE (user_code);


--
-- Name: mt_application_menu mt_application_menu_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.mt_application_menu
    ADD CONSTRAINT mt_application_menu_pk PRIMARY KEY (application_menu_id);


--
-- Name: pt_application_level pt_application_level_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_application_level
    ADD CONSTRAINT pt_application_level_code_u UNIQUE (code);


--
-- Name: pt_application_level pt_application_level_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_application_level
    ADD CONSTRAINT pt_application_level_name_u UNIQUE (name);


--
-- Name: pt_application_level pt_application_level_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_application_level
    ADD CONSTRAINT pt_application_level_pk PRIMARY KEY (application_level_id);


--
-- Name: pt_billing_period pt_billing_period_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_billing_period
    ADD CONSTRAINT pt_billing_period_code_u UNIQUE (code);


--
-- Name: pt_billing_period pt_billing_period_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_billing_period
    ADD CONSTRAINT pt_billing_period_name_u UNIQUE (name);


--
-- Name: pt_billing_period pt_billing_period_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_billing_period
    ADD CONSTRAINT pt_billing_period_pk PRIMARY KEY (billing_period_id);


--
-- Name: pt_consumption_class pt_consumption_class_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_consumption_class
    ADD CONSTRAINT pt_consumption_class_code_u UNIQUE (code);


--
-- Name: pt_consumption_class pt_consumption_class_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_consumption_class
    ADD CONSTRAINT pt_consumption_class_name_u UNIQUE (name);


--
-- Name: pt_consumption_class pt_consumption_class_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_consumption_class
    ADD CONSTRAINT pt_consumption_class_pk PRIMARY KEY (consumption_class_id);


--
-- Name: pt_discount_type pt_discount_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_discount_type
    ADD CONSTRAINT pt_discount_type_code_u UNIQUE (code);


--
-- Name: pt_discount_type pt_discount_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_discount_type
    ADD CONSTRAINT pt_discount_type_name_u UNIQUE (name);


--
-- Name: pt_discount_type pt_discount_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_discount_type
    ADD CONSTRAINT pt_discount_type_pk PRIMARY KEY (discount_type_id);


--
-- Name: pt_entity_type pt_entity_type_PK; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_entity_type
    ADD CONSTRAINT "pt_entity_type_PK" PRIMARY KEY (entity_type_id);


--
-- Name: pt_entity_type pt_entity_type_code_U; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_entity_type
    ADD CONSTRAINT "pt_entity_type_code_U" UNIQUE (code);


--
-- Name: pt_entity_type pt_entity_type_name_U; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_entity_type
    ADD CONSTRAINT "pt_entity_type_name_U" UNIQUE (name);


--
-- Name: pt_status pt_status_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_status
    ADD CONSTRAINT pt_status_code_u UNIQUE (code);


--
-- Name: pt_status pt_status_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_status
    ADD CONSTRAINT pt_status_name_u UNIQUE (name);


--
-- Name: pt_status pt_status_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_status
    ADD CONSTRAINT pt_status_pk PRIMARY KEY (status_id);


--
-- Name: pt_tax_type pt_tax_type_code_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_tax_type
    ADD CONSTRAINT pt_tax_type_code_u UNIQUE (code);


--
-- Name: pt_tax_type pt_tax_type_name_u; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_tax_type
    ADD CONSTRAINT pt_tax_type_name_u UNIQUE (name);


--
-- Name: pt_tax_type pt_tax_type_pk; Type: CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.pt_tax_type
    ADD CONSTRAINT pt_tax_type_pk PRIMARY KEY (tax_type_id);


--
-- Name: ct_account_type ct_account_type_cbc_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_account_type
    ADD CONSTRAINT ct_account_type_cbc_fk FOREIGN KEY (corrective_bill_cycle_type_id) REFERENCES public.ct_bill_cycle_type(bill_cycle_type_id);


--
-- Name: ct_account_type ct_account_type_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_account_type
    ADD CONSTRAINT ct_account_type_entity_fk FOREIGN KEY (entity_type_id) REFERENCES public.pt_entity_type(entity_type_id);


--
-- Name: ct_account_type ct_account_type_obc_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_account_type
    ADD CONSTRAINT ct_account_type_obc_fk FOREIGN KEY (ordinary_bill_cycle_type_id) REFERENCES public.ct_bill_cycle_type(bill_cycle_type_id);


--
-- Name: ct_account_type ct_account_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_account_type
    ADD CONSTRAINT ct_account_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_bill_cycle_type ct_bill_cycle_type_period_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_bill_cycle_type
    ADD CONSTRAINT ct_bill_cycle_type_period_fk FOREIGN KEY (billing_period_id) REFERENCES public.pt_billing_period(billing_period_id);


--
-- Name: ct_bill_cycle_type ct_bill_cycle_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_bill_cycle_type
    ADD CONSTRAINT ct_bill_cycle_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_consumption_type ct_consumption_type_class_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_consumption_type
    ADD CONSTRAINT ct_consumption_type_class_fk FOREIGN KEY (consumption_class_id) REFERENCES public.pt_consumption_class(consumption_class_id);


--
-- Name: ct_consumption_type ct_consumption_type_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_consumption_type
    ADD CONSTRAINT ct_consumption_type_entity_fk FOREIGN KEY (entity_type_id) REFERENCES public.pt_entity_type(entity_type_id);


--
-- Name: ct_consumption_type ct_consumption_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_consumption_type
    ADD CONSTRAINT ct_consumption_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_customer_type ct_customer_type_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_customer_type
    ADD CONSTRAINT ct_customer_type_entity_fk FOREIGN KEY (entity_type_id) REFERENCES public.pt_entity_type(entity_type_id);


--
-- Name: ct_customer_type ct_customer_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_customer_type
    ADD CONSTRAINT ct_customer_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_fee_type ct_fee_type_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_fee_type
    ADD CONSTRAINT ct_fee_type_entity_fk FOREIGN KEY (entity_type_id) REFERENCES public.pt_entity_type(entity_type_id);


--
-- Name: ct_fee_type ct_fee_type_level_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_fee_type
    ADD CONSTRAINT ct_fee_type_level_fk FOREIGN KEY (application_level_id) REFERENCES public.pt_application_level(application_level_id);


--
-- Name: ct_fee_type ct_fee_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_fee_type
    ADD CONSTRAINT ct_fee_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_prod_fee_type ct_prod_fee_type_fee_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_fee_type
    ADD CONSTRAINT ct_prod_fee_type_fee_fk FOREIGN KEY (fee_type_id) REFERENCES public.ct_fee_type(fee_type_id);


--
-- Name: ct_prod_fee_type ct_prod_fee_type_product_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_fee_type
    ADD CONSTRAINT ct_prod_fee_type_product_fk FOREIGN KEY (product_type_id) REFERENCES public.ct_product_type(product_type_id);


--
-- Name: ct_prod_fee_type ct_prod_fee_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_fee_type
    ADD CONSTRAINT ct_prod_fee_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_prod_serv_type ct_prod_serv_type_product_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_serv_type
    ADD CONSTRAINT ct_prod_serv_type_product_fk FOREIGN KEY (product_type_id) REFERENCES public.ct_product_type(product_type_id);


--
-- Name: ct_prod_serv_type ct_prod_serv_type_service_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_serv_type
    ADD CONSTRAINT ct_prod_serv_type_service_fk FOREIGN KEY (service_type_id) REFERENCES public.ct_service_type(service_type_id);


--
-- Name: ct_prod_serv_type ct_prod_serv_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_prod_serv_type
    ADD CONSTRAINT ct_prod_serv_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_product_type ct_product_type_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_product_type
    ADD CONSTRAINT ct_product_type_entity_fk FOREIGN KEY (entity_type_id) REFERENCES public.pt_entity_type(entity_type_id);


--
-- Name: ct_product_type ct_product_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_product_type
    ADD CONSTRAINT ct_product_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_promo_consum_type_discount ct_promo_consum_type_disc_cons_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_consum_type_discount
    ADD CONSTRAINT ct_promo_consum_type_disc_cons_fk FOREIGN KEY (consumption_type_id) REFERENCES public.ct_consumption_type(consumption_type_id);


--
-- Name: ct_promo_consum_type_discount ct_promo_consum_type_disc_promo_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_consum_type_discount
    ADD CONSTRAINT ct_promo_consum_type_disc_promo_fk FOREIGN KEY (promotion_type_id) REFERENCES public.ct_promotion_type(promotion_type_id);


--
-- Name: ct_promo_consum_type_discount ct_promo_consum_type_discount_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_consum_type_discount
    ADD CONSTRAINT ct_promo_consum_type_discount_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_promo_fee_type_discount ct_promo_fee_type_discount_fee_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_fee_type_discount
    ADD CONSTRAINT ct_promo_fee_type_discount_fee_fk FOREIGN KEY (fee_type_id) REFERENCES public.ct_fee_type(fee_type_id);


--
-- Name: ct_promo_fee_type_discount ct_promo_fee_type_discount_lev_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_fee_type_discount
    ADD CONSTRAINT ct_promo_fee_type_discount_lev_fk FOREIGN KEY (application_level_id) REFERENCES public.pt_application_level(application_level_id);


--
-- Name: ct_promo_fee_type_discount ct_promo_fee_type_discount_promo_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_fee_type_discount
    ADD CONSTRAINT ct_promo_fee_type_discount_promo_fk FOREIGN KEY (promotion_type_id) REFERENCES public.ct_promotion_type(promotion_type_id);


--
-- Name: ct_promo_fee_type_discount ct_promo_fee_type_discount_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_fee_type_discount
    ADD CONSTRAINT ct_promo_fee_type_discount_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_promo_prod_type ct_promo_prod_type_prod_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_prod_type
    ADD CONSTRAINT ct_promo_prod_type_prod_fk FOREIGN KEY (product_type_id) REFERENCES public.ct_product_type(product_type_id);


--
-- Name: ct_promo_prod_type ct_promo_prod_type_promo_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_prod_type
    ADD CONSTRAINT ct_promo_prod_type_promo_fk FOREIGN KEY (promotion_type_id) REFERENCES public.ct_promotion_type(promotion_type_id);


--
-- Name: ct_promo_prod_type ct_promo_prod_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_prod_type
    ADD CONSTRAINT ct_promo_prod_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_promo_serv_type ct_promo_serv_type_promo_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_serv_type
    ADD CONSTRAINT ct_promo_serv_type_promo_fk FOREIGN KEY (promotion_type_id) REFERENCES public.ct_promotion_type(promotion_type_id);


--
-- Name: ct_promo_serv_type ct_promo_serv_type_serv_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_serv_type
    ADD CONSTRAINT ct_promo_serv_type_serv_fk FOREIGN KEY (service_type_id) REFERENCES public.ct_service_type(service_type_id);


--
-- Name: ct_promo_serv_type ct_promo_serv_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promo_serv_type
    ADD CONSTRAINT ct_promo_serv_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_promotion_type ct_promotion_type_discount_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promotion_type
    ADD CONSTRAINT ct_promotion_type_discount_fk FOREIGN KEY (application_level_id) REFERENCES public.pt_application_level(application_level_id);


--
-- Name: ct_promotion_type ct_promotion_type_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promotion_type
    ADD CONSTRAINT ct_promotion_type_entity_fk FOREIGN KEY (entity_type_id) REFERENCES public.pt_entity_type(entity_type_id);


--
-- Name: ct_promotion_type ct_promotion_type_level_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promotion_type
    ADD CONSTRAINT ct_promotion_type_level_fk FOREIGN KEY (discount_type_id) REFERENCES public.pt_discount_type(discount_type_id);


--
-- Name: ct_promotion_type ct_promotion_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_promotion_type
    ADD CONSTRAINT ct_promotion_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_serv_fee_type ct_serv_fee_type_fee_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_serv_fee_type
    ADD CONSTRAINT ct_serv_fee_type_fee_fk FOREIGN KEY (fee_type_id) REFERENCES public.ct_fee_type(fee_type_id);


--
-- Name: ct_serv_fee_type ct_serv_fee_type_service_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_serv_fee_type
    ADD CONSTRAINT ct_serv_fee_type_service_fk FOREIGN KEY (service_type_id) REFERENCES public.ct_service_type(service_type_id);


--
-- Name: ct_serv_fee_type ct_serv_fee_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_serv_fee_type
    ADD CONSTRAINT ct_serv_fee_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: ct_service_type ct_service_type_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_service_type
    ADD CONSTRAINT ct_service_type_entity_fk FOREIGN KEY (entity_type_id) REFERENCES public.pt_entity_type(entity_type_id);


--
-- Name: ct_service_type ct_service_type_status_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.ct_service_type
    ADD CONSTRAINT ct_service_type_status_fk FOREIGN KEY (status_id) REFERENCES public.pt_status(status_id);


--
-- Name: it_users it_users_profile_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.it_users
    ADD CONSTRAINT it_users_profile_id_fkey FOREIGN KEY (profile_id) REFERENCES public.it_profiles(profile_id);


--
-- Name: mt_application_menu mt_application_menu_fk; Type: FK CONSTRAINT; Schema: public; Owner: billing_admin
--

ALTER TABLE ONLY public.mt_application_menu
    ADD CONSTRAINT mt_application_menu_fk FOREIGN KEY (application_parent_menu_id) REFERENCES public.mt_application_menu(application_menu_id);


--
-- Name: SEQUENCE seq_account_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_account_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_account_type_id TO postgres;


--
-- Name: TABLE ct_account_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_account_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_account_type TO billing_app;


--
-- Name: SEQUENCE seq_bill_cycle_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_bill_cycle_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_bill_cycle_type_id TO postgres;


--
-- Name: TABLE ct_bill_cycle_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_bill_cycle_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_bill_cycle_type TO billing_app;


--
-- Name: SEQUENCE seq_consumption_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_consumption_type TO billing_app;
GRANT ALL ON SEQUENCE public.seq_consumption_type TO postgres;


--
-- Name: TABLE ct_consumption_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_consumption_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_consumption_type TO billing_app;


--
-- Name: SEQUENCE seq_customer_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_customer_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_customer_type_id TO postgres;


--
-- Name: TABLE ct_customer_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_customer_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_customer_type TO billing_app;


--
-- Name: SEQUENCE seq_fee_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_fee_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_fee_type_id TO postgres;


--
-- Name: TABLE ct_fee_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_fee_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_fee_type TO billing_app;


--
-- Name: SEQUENCE seq_prod_fee_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_prod_fee_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_prod_fee_type_id TO postgres;


--
-- Name: TABLE ct_prod_fee_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_prod_fee_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_prod_fee_type TO billing_app;


--
-- Name: SEQUENCE seq_prod_serv_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_prod_serv_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_prod_serv_type_id TO postgres;


--
-- Name: TABLE ct_prod_serv_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_prod_serv_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_prod_serv_type TO billing_app;


--
-- Name: SEQUENCE seq_product_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_product_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_product_type_id TO postgres;


--
-- Name: TABLE ct_product_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_product_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_product_type TO billing_app;


--
-- Name: SEQUENCE seq_promo_consum_type_disc_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_promo_consum_type_disc_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_promo_consum_type_disc_id TO postgres;


--
-- Name: TABLE ct_promo_consum_type_discount; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_promo_consum_type_discount TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_promo_consum_type_discount TO billing_app;


--
-- Name: SEQUENCE seq_promo_fee_type_disc_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_promo_fee_type_disc_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_promo_fee_type_disc_id TO postgres;


--
-- Name: TABLE ct_promo_fee_type_discount; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_promo_fee_type_discount TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_promo_fee_type_discount TO billing_app;


--
-- Name: SEQUENCE seq_promo_prod_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_promo_prod_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_promo_prod_type_id TO postgres;


--
-- Name: TABLE ct_promo_prod_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_promo_prod_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_promo_prod_type TO billing_app;


--
-- Name: SEQUENCE seq_promo_serv_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_promo_serv_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_promo_serv_type_id TO postgres;


--
-- Name: TABLE ct_promo_serv_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_promo_serv_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_promo_serv_type TO billing_app;


--
-- Name: SEQUENCE seq_promotion_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_promotion_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_promotion_type_id TO postgres;


--
-- Name: TABLE ct_promotion_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_promotion_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_promotion_type TO billing_app;


--
-- Name: SEQUENCE seq_serv_fee_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_serv_fee_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_serv_fee_type_id TO postgres;


--
-- Name: TABLE ct_serv_fee_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_serv_fee_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_serv_fee_type TO billing_app;


--
-- Name: SEQUENCE seq_service_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_service_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_service_type_id TO postgres;


--
-- Name: TABLE ct_service_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.ct_service_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ct_service_type TO billing_app;


--
-- Name: SEQUENCE seq_profile_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_profile_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_profile_id TO postgres;


--
-- Name: TABLE it_profiles; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.it_profiles TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.it_profiles TO billing_app;


--
-- Name: SEQUENCE seq_user_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_user_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_user_id TO postgres;


--
-- Name: TABLE it_users; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.it_users TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.it_users TO billing_app;


--
-- Name: SEQUENCE seq_application_menu_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_application_menu_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_application_menu_id TO postgres;


--
-- Name: TABLE mt_application_menu; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.mt_application_menu TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.mt_application_menu TO billing_app;


--
-- Name: SEQUENCE seq_application_level_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_application_level_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_application_level_id TO postgres;


--
-- Name: TABLE pt_application_level; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.pt_application_level TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.pt_application_level TO billing_app;


--
-- Name: SEQUENCE seq_billing_period_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_billing_period_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_billing_period_id TO postgres;


--
-- Name: TABLE pt_billing_period; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.pt_billing_period TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.pt_billing_period TO billing_app;


--
-- Name: SEQUENCE seq_consumption_class_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_consumption_class_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_consumption_class_id TO postgres;


--
-- Name: TABLE pt_consumption_class; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.pt_consumption_class TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.pt_consumption_class TO billing_app;


--
-- Name: SEQUENCE seq_discount_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_discount_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_discount_type_id TO postgres;


--
-- Name: TABLE pt_discount_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.pt_discount_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.pt_discount_type TO billing_app;


--
-- Name: SEQUENCE seq_entity_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_entity_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_entity_type_id TO postgres;


--
-- Name: TABLE pt_entity_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.pt_entity_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.pt_entity_type TO billing_app;


--
-- Name: SEQUENCE seq_status_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_status_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_status_id TO postgres;


--
-- Name: TABLE pt_status; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.pt_status TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.pt_status TO billing_app;


--
-- Name: SEQUENCE seq_tax_type_id; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON SEQUENCE public.seq_tax_type_id TO billing_app;
GRANT ALL ON SEQUENCE public.seq_tax_type_id TO postgres;


--
-- Name: TABLE pt_tax_type; Type: ACL; Schema: public; Owner: billing_admin
--

GRANT ALL ON TABLE public.pt_tax_type TO postgres;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.pt_tax_type TO billing_app;


--
-- PostgreSQL database dump complete
--

