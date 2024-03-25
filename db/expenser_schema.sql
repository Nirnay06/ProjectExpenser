--
-- PostgreSQL database dump
--

-- Dumped from database version 14.10 (Homebrew)
-- Dumped by pg_dump version 14.10 (Homebrew)

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
-- Name: expenser_db; Type: SCHEMA; Schema: -; Owner: expenser_db
--

CREATE SCHEMA expenser_db;


ALTER SCHEMA expenser_db OWNER TO expenser_db;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA expenser_db;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- Name: createdefaultcategoryforclient(character varying, character varying); Type: PROCEDURE; Schema: expenser_db; Owner: expenser_db
--

CREATE OR REPLACE PROCEDURE expenser_db.createdefaultcategoryforclient(IN clientidentifier character varying, IN createdby character varying)
    LANGUAGE plpgsql
    AS $$
      Declare
      parentIdentifier varchar(2000);
	  PARENT_COUNT int =1;
	  CHILD_COUNT int =1;
      BEGIN 
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'restaurant','#f44336','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Food & Drinks',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'add_shopping_cart','#f44336','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Groceries',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'fastfood','#f44336','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Restaurant, fast-food',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'glass2','#f44336','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Bar, cafe',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'restaurant','#f44336','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Food & Drinks',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Shopping',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'t-shirt','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Clothes & shoes',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'diamond','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Jewels, accessories',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'add_shopping_cart','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Health and beauty',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'child_friendly','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Kids',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'park','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Home, garden',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'pets','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Pets, animals',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'monitor','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Electronics, accessories',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'redeem','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Gifts, joy',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'create','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Stationery, tools',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'hourglass_full','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Free time',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Shopping',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1; 
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'medical_services','#4fC3F7','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Drug-store, chemist',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Housing',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Rent',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Mortgage',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Energy, utilities',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Services',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Maintenance, repairs',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Housing',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FFA726','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Property insurance',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#78909C','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Transportation',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#78909C','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Public transport',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#78909C','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Taxi',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#78909C','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Long distance',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#78909C','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Business trips',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#78909C','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Transportation',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Vehicle',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Fuel',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Parking',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Vehicle maintenance',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Rentals',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Vehicle',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Vehicle insurance',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#AA00FF','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Leasing',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Life & Entertainment',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Health care, doctor',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Wellness, beauty',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Active sport, fitness',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Culture, sport events',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Life events',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Hobbies',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Education, development',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Books, audio, subscriptions',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'TV, Streaming',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Holiday, trips, hotels',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Charity, gifts',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Alcohol, tobacco',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Lottery, gambling',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#64DD17','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Life & Entertainment',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#536DFE','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Communication, PC',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#536DFE','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Phone, mobile phone',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#536DFE','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Internet',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#536DFE','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Software, apps, games',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#536DFE','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Postal services',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#536DFE','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Communication, PC',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Financial expenses',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Taxes',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Insurances',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Loan, interests',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Fines',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Advisory',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Charges, Fees',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Child Support',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#00BFA5','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Financial expenses',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#FF4081','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Investments',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FF4081','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Realty',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FF4081','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Vehicles, chattels',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FF4081','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Financial investments',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FF4081','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Savings',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FF4081','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Collections',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FF4081','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Investments',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Income',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Wage, invoices',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Interests, dividends',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Sale',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Rental income',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Dues & grants',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Lending, renting',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Checks, coupons',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Lottery, gambling',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Refunds (tax, purchase)',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Child Support',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Gifts',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#FBC02D','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Income',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            select getSysUUID() into parentIdentifier;
 child_count :=1; parent_count := parent_count +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),parentIdentifier,clientIdentifier,null,'shopping_bag','#9E9E9E','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Others',false, PARENT_COUNT);
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#9E9E9E','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Others',false, CHILD_COUNT);
 CHILD_COUNT :=CHILD_COUNT +1;
            -- SQLINES LICENSE FOR EVALUATION USE ONLY
            Insert into user_category (ID,IDENTIFIER,CLIENT_IDENTIFIER,PARENT_IDENTIFIER,ICON,COLOR,HIDDEN,IS_DEFAULT_CATEGORY,CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,DELETED,CATEGORY_TITLE,selectable, category_order) values (nextval('USER_RECORD_CAT_SEQ'),(select getSysUUID()),clientIdentifier,parentIdentifier,'shopping_bag','#9E9E9E','0',true,createdBy,createdBy,current_timestamp,current_timestamp,false,'Missing',false, CHILD_COUNT);


END;
$$;


ALTER PROCEDURE expenser_db.createdefaultcategoryforclient(IN clientidentifier character varying, IN createdby character varying) OWNER TO expenser_db;

--
-- Name: getsysuuid(); Type: FUNCTION; Schema: expenser_db; Owner: neeraj
--

CREATE FUNCTION expenser_db.getsysuuid() RETURNS character varying
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN lower(regexp_replace(uuid_generate_v4()::text, '([A-F0-9]{8})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{12})', '\1-\2-\3-\4-\5'));
END;
$$;


ALTER FUNCTION expenser_db.getsysuuid() OWNER TO neeraj;

--
-- Name: account_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.account_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.account_seq OWNER TO expenser_db;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: authorities; Type: TABLE; Schema: expenser_db; Owner: neeraj
--

CREATE TABLE expenser_db.authorities (
    id integer NOT NULL,
    name character varying(1000) NOT NULL,
    user_id integer NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    deleted boolean
);


ALTER TABLE expenser_db.authorities OWNER TO neeraj;

--
-- Name: authorities_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.authorities_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.authorities_id_seq OWNER TO neeraj;

--
-- Name: authorities_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: neeraj
--

ALTER SEQUENCE expenser_db.authorities_id_seq OWNED BY expenser_db.authorities.id;


--
-- Name: authority_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.authority_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.authority_seq OWNER TO neeraj;

--
-- Name: client; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.client (
    id integer NOT NULL,
    full_name character varying(1000) NOT NULL,
    client_identifier character varying(2000) NOT NULL,
    user_identifier character varying(2000) NOT NULL,
    first_name character varying(500) NOT NULL,
    last_name character varying(500) NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    deleted boolean
);


ALTER TABLE expenser_db.client OWNER TO expenser_db;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.client_id_seq OWNER TO expenser_db;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.client_id_seq OWNED BY expenser_db.client.id;


--
-- Name: client_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.client_seq OWNER TO expenser_db;

--
-- Name: example; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.example (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE expenser_db.example OWNER TO expenser_db;

--
-- Name: example_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.example_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.example_id_seq OWNER TO expenser_db;

--
-- Name: example_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.example_id_seq OWNED BY expenser_db.example.id;


--
-- Name: file_upload; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.file_upload (
    id integer NOT NULL,
    file_name character varying(1000) NOT NULL,
    client_identifier character varying(2000) NOT NULL,
    file_identifier character varying(500) NOT NULL,
    file_data text NOT NULL,
    file_meta_data text NOT NULL,
    record_start_date date,
    record_end_date date,
    file_imported boolean,
    account_identifier character varying(1000) NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    deleted boolean
);


ALTER TABLE expenser_db.file_upload OWNER TO expenser_db;

--
-- Name: file_upload_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.file_upload_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.file_upload_id_seq OWNER TO expenser_db;

--
-- Name: file_upload_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.file_upload_id_seq OWNED BY expenser_db.file_upload.id;


--
-- Name: file_upload_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.file_upload_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.file_upload_seq OWNER TO expenser_db;

--
-- Name: link_token; Type: TABLE; Schema: expenser_db; Owner: neeraj
--

CREATE TABLE expenser_db.link_token (
    id integer NOT NULL,
    generation_time timestamp without time zone NOT NULL,
    expiration_time timestamp without time zone NOT NULL,
    token_identifier character varying(2000) NOT NULL,
    user_identifier character varying(2000) NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    deleted boolean,
    is_active boolean,
    token_type integer
);


ALTER TABLE expenser_db.link_token OWNER TO neeraj;

--
-- Name: link_token_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.link_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.link_token_id_seq OWNER TO neeraj;

--
-- Name: link_token_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: neeraj
--

ALTER SEQUENCE expenser_db.link_token_id_seq OWNED BY expenser_db.link_token.id;


--
-- Name: link_token_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.link_token_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.link_token_seq OWNER TO neeraj;

--
-- Name: master_currency; Type: TABLE; Schema: expenser_db; Owner: neeraj
--

CREATE TABLE expenser_db.master_currency (
    id integer NOT NULL,
    identifier character varying(2000) NOT NULL,
    currency_symbol character varying(100) NOT NULL,
    current_conversion_rate numeric NOT NULL,
    last_synced_date date,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    currency_title character varying(255),
    deleted boolean,
    currency_abbr character varying(2000)
);


ALTER TABLE expenser_db.master_currency OWNER TO neeraj;

--
-- Name: master_currency_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.master_currency_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.master_currency_id_seq OWNER TO neeraj;

--
-- Name: master_currency_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: neeraj
--

ALTER SEQUENCE expenser_db.master_currency_id_seq OWNED BY expenser_db.master_currency.id;


--
-- Name: master_currency_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.master_currency_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.master_currency_seq OWNER TO expenser_db;

--
-- Name: on_demand_event; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.on_demand_event (
    id integer NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    key character varying(2000),
    value character varying(2000),
    error_msg bytea,
    deleted boolean,
    active boolean
);


ALTER TABLE expenser_db.on_demand_event OWNER TO expenser_db;

--
-- Name: on_demand_event_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.on_demand_event_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.on_demand_event_id_seq OWNER TO expenser_db;

--
-- Name: on_demand_event_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.on_demand_event_id_seq OWNED BY expenser_db.on_demand_event.id;


--
-- Name: on_demand_event_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.on_demand_event_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.on_demand_event_seq OWNER TO expenser_db;

--
-- Name: product_config_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.product_config_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.product_config_seq OWNER TO neeraj;

--
-- Name: product_configurations; Type: TABLE; Schema: expenser_db; Owner: neeraj
--

CREATE TABLE expenser_db.product_configurations (
    id integer NOT NULL,
    key character varying(2000) NOT NULL,
    value character varying(2000) NOT NULL,
    uuid character varying(2000),
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    deleted boolean
);


ALTER TABLE expenser_db.product_configurations OWNER TO neeraj;

--
-- Name: product_configurations_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.product_configurations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.product_configurations_id_seq OWNER TO neeraj;

--
-- Name: product_configurations_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: neeraj
--

ALTER SEQUENCE expenser_db.product_configurations_id_seq OWNED BY expenser_db.product_configurations.id;


--
-- Name: record_label_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.record_label_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.record_label_seq OWNER TO expenser_db;

--
-- Name: record_labels; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.record_labels (
    id integer NOT NULL,
    identifier character varying(2000) NOT NULL,
    record_identifier character varying(2000) NOT NULL,
    user_label_identifier character varying(2000) NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    record_id bigint,
    user_label_id bigint,
    deleted boolean
);


ALTER TABLE expenser_db.record_labels OWNER TO expenser_db;

--
-- Name: record_labels_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.record_labels_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.record_labels_id_seq OWNER TO expenser_db;

--
-- Name: record_labels_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.record_labels_id_seq OWNED BY expenser_db.record_labels.id;


--
-- Name: record_location; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.record_location (
    id integer NOT NULL,
    identifier character varying(2000) NOT NULL,
    record_identifier character varying(2000) NOT NULL,
    latitude numeric NOT NULL,
    longitude numeric NOT NULL,
    title character varying(2000) NOT NULL,
    address_line character varying(2000),
    city character varying(2000),
    state character varying(2000),
    country character varying(2000),
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    deleted boolean,
    modified boolean
);


ALTER TABLE expenser_db.record_location OWNER TO expenser_db;

--
-- Name: record_location_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.record_location_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.record_location_id_seq OWNER TO expenser_db;

--
-- Name: record_location_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.record_location_id_seq OWNED BY expenser_db.record_location.id;


--
-- Name: record_location_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.record_location_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.record_location_seq OWNER TO expenser_db;

--
-- Name: user_accounts; Type: TABLE; Schema: expenser_db; Owner: neeraj
--

CREATE TABLE expenser_db.user_accounts (
    id integer NOT NULL,
    client_identifier character varying(2000) NOT NULL,
    account_identifier character varying(2000) NOT NULL,
    account_type character varying(2000) NOT NULL,
    account_name character varying(2000) NOT NULL,
    account_color character varying(2000),
    account_balance numeric DEFAULT 0 NOT NULL,
    icon character varying(2000),
    initial_balance numeric,
    account_currency character varying(2000) NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    deleted boolean,
    is_active boolean,
    is_exclude_from_stats boolean,
    is_archived boolean
);


ALTER TABLE expenser_db.user_accounts OWNER TO neeraj;

--
-- Name: user_accounts_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.user_accounts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_accounts_id_seq OWNER TO neeraj;

--
-- Name: user_accounts_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: neeraj
--

ALTER SEQUENCE expenser_db.user_accounts_id_seq OWNED BY expenser_db.user_accounts.id;


--
-- Name: user_category; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.user_category (
    id integer NOT NULL,
    identifier character varying(2000) NOT NULL,
    client_identifier character varying(2000) NOT NULL,
    parent_identifier character varying(2000),
    icon character varying(2000) NOT NULL,
    color character varying(2000) NOT NULL,
    hidden character varying(2000) DEFAULT 0 NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    category_title character varying(2000) NOT NULL,
    category_order integer,
    deleted boolean,
    is_default_category boolean,
    selectable boolean
);


ALTER TABLE expenser_db.user_category OWNER TO expenser_db;

--
-- Name: user_category_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.user_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_category_id_seq OWNER TO expenser_db;

--
-- Name: user_category_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.user_category_id_seq OWNED BY expenser_db.user_category.id;


--
-- Name: user_curr_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.user_curr_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_curr_seq OWNER TO expenser_db;

--
-- Name: user_currency; Type: TABLE; Schema: expenser_db; Owner: neeraj
--

CREATE TABLE expenser_db.user_currency (
    id integer NOT NULL,
    client_identifier character varying(2000) NOT NULL,
    identifier character varying(2000) NOT NULL,
    user_currency_rate numeric,
    master_currency_identifier character varying(2000),
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    is_rate_overriden boolean,
    deleted boolean,
    is_base_currency boolean,
    is_rate_overridden boolean,
    display_order integer
);


ALTER TABLE expenser_db.user_currency OWNER TO neeraj;

--
-- Name: user_currency_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.user_currency_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_currency_id_seq OWNER TO neeraj;

--
-- Name: user_currency_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: neeraj
--

ALTER SEQUENCE expenser_db.user_currency_id_seq OWNED BY expenser_db.user_currency.id;


--
-- Name: user_label_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.user_label_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_label_seq OWNER TO expenser_db;

--
-- Name: user_labels; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.user_labels (
    id integer NOT NULL,
    identifier character varying(2000) NOT NULL,
    client_identifier character varying(2000) NOT NULL,
    title character varying(2000) NOT NULL,
    color character varying(2000) NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    default_assign boolean,
    deleted boolean,
    is_archive boolean
);


ALTER TABLE expenser_db.user_labels OWNER TO expenser_db;

--
-- Name: user_labels_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.user_labels_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_labels_id_seq OWNER TO expenser_db;

--
-- Name: user_labels_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.user_labels_id_seq OWNED BY expenser_db.user_labels.id;


--
-- Name: user_record_cat_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.user_record_cat_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_record_cat_seq OWNER TO expenser_db;

--
-- Name: user_record_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.user_record_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_record_seq OWNER TO expenser_db;

--
-- Name: user_records; Type: TABLE; Schema: expenser_db; Owner: expenser_db
--

CREATE TABLE expenser_db.user_records (
    id integer NOT NULL,
    identifier character varying(2000) NOT NULL,
    client_identifier character varying(2000) NOT NULL,
    account_identifier character varying(2000) NOT NULL,
    record_type character varying(2000) NOT NULL,
    user_currency_identifier character varying(2000) NOT NULL,
    amount numeric DEFAULT 0 NOT NULL,
    user_category_identifier character varying(2000) NOT NULL,
    record_date timestamp without time zone NOT NULL,
    payment_type character varying(2000),
    payment_status character varying(2000),
    refund_record_identifier character varying(2000),
    transfer_transaction_identifier character varying(2000),
    comments character varying(2000),
    payee character varying(2000),
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    record_location_id bigint,
    deleted boolean,
    file_upload_id integer
);


ALTER TABLE expenser_db.user_records OWNER TO expenser_db;

--
-- Name: user_records_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: expenser_db
--

CREATE SEQUENCE expenser_db.user_records_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_records_id_seq OWNER TO expenser_db;

--
-- Name: user_records_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: expenser_db
--

ALTER SEQUENCE expenser_db.user_records_id_seq OWNED BY expenser_db.user_records.id;


--
-- Name: user_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.user_seq
    START WITH 100
    INCREMENT BY 1
    MINVALUE 100
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.user_seq OWNER TO neeraj;

--
-- Name: users; Type: TABLE; Schema: expenser_db; Owner: neeraj
--

CREATE TABLE expenser_db.users (
    id integer NOT NULL,
    full_name character varying(1000) NOT NULL,
    username character varying(1000) NOT NULL,
    password character varying(2000) NOT NULL,
    user_identifier character varying(2000) NOT NULL,
    first_name character varying(500) NOT NULL,
    last_name character varying(500) NOT NULL,
    created_by character varying(2000),
    updated_by character varying(2000),
    created_date timestamp without time zone,
    updated_date timestamp without time zone,
    deleted boolean,
    enabled boolean
);


ALTER TABLE expenser_db.users OWNER TO neeraj;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: expenser_db; Owner: neeraj
--

CREATE SEQUENCE expenser_db.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE expenser_db.users_id_seq OWNER TO neeraj;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: expenser_db; Owner: neeraj
--

ALTER SEQUENCE expenser_db.users_id_seq OWNED BY expenser_db.users.id;


--
-- Name: authorities id; Type: DEFAULT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.authorities ALTER COLUMN id SET DEFAULT nextval('expenser_db.authorities_id_seq'::regclass);


--
-- Name: client id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.client ALTER COLUMN id SET DEFAULT nextval('expenser_db.client_id_seq'::regclass);


--
-- Name: example id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.example ALTER COLUMN id SET DEFAULT nextval('expenser_db.example_id_seq'::regclass);


--
-- Name: file_upload id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.file_upload ALTER COLUMN id SET DEFAULT nextval('expenser_db.file_upload_id_seq'::regclass);


--
-- Name: link_token id; Type: DEFAULT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.link_token ALTER COLUMN id SET DEFAULT nextval('expenser_db.link_token_id_seq'::regclass);


--
-- Name: master_currency id; Type: DEFAULT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.master_currency ALTER COLUMN id SET DEFAULT nextval('expenser_db.master_currency_id_seq'::regclass);


--
-- Name: on_demand_event id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.on_demand_event ALTER COLUMN id SET DEFAULT nextval('expenser_db.on_demand_event_id_seq'::regclass);


--
-- Name: product_configurations id; Type: DEFAULT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.product_configurations ALTER COLUMN id SET DEFAULT nextval('expenser_db.product_configurations_id_seq'::regclass);


--
-- Name: record_labels id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_labels ALTER COLUMN id SET DEFAULT nextval('expenser_db.record_labels_id_seq'::regclass);


--
-- Name: record_location id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_location ALTER COLUMN id SET DEFAULT nextval('expenser_db.record_location_id_seq'::regclass);


--
-- Name: user_accounts id; Type: DEFAULT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_accounts ALTER COLUMN id SET DEFAULT nextval('expenser_db.user_accounts_id_seq'::regclass);


--
-- Name: user_category id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_category ALTER COLUMN id SET DEFAULT nextval('expenser_db.user_category_id_seq'::regclass);


--
-- Name: user_currency id; Type: DEFAULT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_currency ALTER COLUMN id SET DEFAULT nextval('expenser_db.user_currency_id_seq'::regclass);


--
-- Name: user_labels id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_labels ALTER COLUMN id SET DEFAULT nextval('expenser_db.user_labels_id_seq'::regclass);


--
-- Name: user_records id; Type: DEFAULT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records ALTER COLUMN id SET DEFAULT nextval('expenser_db.user_records_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.users ALTER COLUMN id SET DEFAULT nextval('expenser_db.users_id_seq'::regclass);


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: expenser_db; Owner: neeraj
--

INSERT INTO expenser_db.authorities VALUES (105, 'USER', 105, 'anonymousUser', 'anonymousUser', '2023-12-23 11:57:52', '2023-12-23 11:57:52', false);
INSERT INTO expenser_db.authorities VALUES (106, 'USER', 106, 'anonymousUser', 'anonymousUser', '2023-12-23 12:08:03.228', '2023-12-23 12:08:03.228', false);


--
-- Data for Name: client; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--

INSERT INTO expenser_db.client VALUES (6, 'Niray Mittal', '631b0c51-77a7-47e1-ba54-22d0d5576c6a', '87eee027-8271-43cf-8d04-6fcbd24cde6e', 'Niray', 'Mittal', 'anonymousUser', 'anonymousUser', '2023-12-23 11:57:52.004+05:30', '2023-12-23 11:57:52.004+05:30', false);
INSERT INTO expenser_db.client VALUES (7, 'Nirnay Mittal', 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', '4a757834-91c5-43c5-be62-05e36f3cc378', 'Nirnay', 'Mittal', 'anonymousUser', 'anonymousUser', '2023-12-23 12:08:03.229+05:30', '2023-12-23 12:08:03.229+05:30', false);


--
-- Data for Name: example; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: file_upload; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: link_token; Type: TABLE DATA; Schema: expenser_db; Owner: neeraj
--

INSERT INTO expenser_db.link_token VALUES (105, '2023-12-23 11:57:52.022', '2023-12-24 11:57:52.022', 'c864e0ee-7796-4353-a745-fe3e5abd4f5b', '87eee027-8271-43cf-8d04-6fcbd24cde6e', 'anonymousUser', 'anonymousUser', '2023-12-23 11:57:52.023', '2023-12-23 11:57:52.023', false, true, NULL);
INSERT INTO expenser_db.link_token VALUES (106, '2023-12-23 12:08:03.231', '2023-12-24 12:08:03.231', 'bf8b6e8f-7ef0-4eaa-bd57-8ca7b5ca265f', '4a757834-91c5-43c5-be62-05e36f3cc378', 'anonymousUser', 'anonymousUser', '2023-12-23 12:08:03.231', '2023-12-23 12:08:19.936', false, false, 1);


--
-- Data for Name: master_currency; Type: TABLE DATA; Schema: expenser_db; Owner: neeraj
--

INSERT INTO expenser_db.master_currency VALUES (112, 'a416f160-4a33-4369-a3fa-cbea3e4d9cbf', 'AED', 0.044132975, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'UAE dirham', false, 'AED');
INSERT INTO expenser_db.master_currency VALUES (113, '5444aa1e-f06f-4074-88f9-7b8d51665b1f', 'Afs', 0.84082693, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Afghan afghani', false, 'AFN');
INSERT INTO expenser_db.master_currency VALUES (114, '39bc02d2-0c0d-4bf7-bb61-6684a416faa9', 'L', 1.12959024, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Albanian lek', false, 'ALL');
INSERT INTO expenser_db.master_currency VALUES (115, '296bc040-0929-4aab-b0b2-0ed960cd0ff6', 'AMD', 4.83800845, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Armenian dram', false, 'AMD');
INSERT INTO expenser_db.master_currency VALUES (116, 'a4094987-75d6-4baf-a54d-12bc2334d9a5', 'NAƒ', 0.021478322, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Netherlands Antillean gulden', false, 'ANG');
INSERT INTO expenser_db.master_currency VALUES (117, 'ff1aa8aa-3fe3-4eaa-a8b8-2681a028cc92', 'Kz', 9.95970201, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Angolan kwanza', false, 'AOA');
INSERT INTO expenser_db.master_currency VALUES (118, 'c73d4e33-5158-42ce-8b6f-56486ea70487', '$', 9.67082056, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Argentine peso', false, 'ARS');
INSERT INTO expenser_db.master_currency VALUES (119, 'd4f7c75d-61d2-4ad2-8212-496fa56484e0', '$', 0.017674273, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Australian dollar', false, 'AUD');
INSERT INTO expenser_db.master_currency VALUES (120, '51ac3702-41ba-41a1-86c6-15c795e57459', 'ƒ', 0.021510695, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Aruban florin', false, 'AWG');
INSERT INTO expenser_db.master_currency VALUES (121, 'b965d294-dc9a-42b1-942d-31745ed5fd0e', 'AZN', 0.020429411, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Azerbaijani manat', false, 'AZN');
INSERT INTO expenser_db.master_currency VALUES (122, '1f8ddc4d-8960-464f-8724-000a70b281bb', 'KM', 0.021324172, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bosnia and Herzegovina konvertibilna marka', false, 'BAM');
INSERT INTO expenser_db.master_currency VALUES (123, 'cfaf85fc-341a-4bd0-a356-b845c0c30bda', 'Bds$', 0.024034296, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Barbadian dollar', false, 'BBD');
INSERT INTO expenser_db.master_currency VALUES (124, '04df45d9-1123-4993-9fb7-9fc3b28d2b75', '৳', 1.31738043, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bangladeshi taka', false, 'BDT');
INSERT INTO expenser_db.master_currency VALUES (125, '987befbf-375e-4e58-b551-6b17ec789f43', 'BGN', 0.021324172, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bulgarian lev', false, 'BGN');
INSERT INTO expenser_db.master_currency VALUES (126, '7f09ef0c-aef8-4278-afa6-79603e4ea496', '.د.ب', 0.0045184476, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bahraini dinar', false, 'BHD');
INSERT INTO expenser_db.master_currency VALUES (127, 'f577e3c0-508e-44bb-a975-6985fd44e0a9', 'FBu', 34.22855167, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Burundi franc', false, 'BIF');
INSERT INTO expenser_db.master_currency VALUES (128, '0315801a-694f-4fcb-83ca-ca3ada45a7ac', 'BD$', 0.012017148, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bermudian dollar', false, 'BMD');
INSERT INTO expenser_db.master_currency VALUES (129, 'ebfd247b-236a-4b59-8824-56f43e32c408', 'B$', 0.015913641, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Brunei dollar', false, 'BND');
INSERT INTO expenser_db.master_currency VALUES (130, 'f6d4e691-8c44-4265-8317-b8bb1edea643', 'Bs.', 0.08333757, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bolivian boliviano', false, 'BOB');
INSERT INTO expenser_db.master_currency VALUES (131, 'd26deb8c-0867-469d-95a3-f188b999d60b', 'R$', 0.058314877, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Brazilian real', false, 'BRL');
INSERT INTO expenser_db.master_currency VALUES (132, 'c3fc67a5-f3fc-4608-a05f-7b82a7b708d8', 'B$', 0.012017148, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bahamian dollar', false, 'BSD');
INSERT INTO expenser_db.master_currency VALUES (133, 'c743d48e-db86-4e76-89b5-0d3a7b40e06d', 'Nu.', 1.0, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Bhutanese ngultrum', false, 'BTN');
INSERT INTO expenser_db.master_currency VALUES (134, '72ad1f41-4d97-4365-9707-259b00560d6c', 'P', 0.16105176, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Botswana pula', false, 'BWP');
INSERT INTO expenser_db.master_currency VALUES (135, 'ef4192be-def0-4ee4-a204-9d9a39b9b203', 'Br', 395.96523302, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Belarusian ruble', false, 'BYR');
INSERT INTO expenser_db.master_currency VALUES (136, '637e33ef-4ef0-4e5e-8f28-2047388c12b9', 'BZ$', 0.024218276, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Belize dollar', false, 'BZD');
INSERT INTO expenser_db.master_currency VALUES (137, 'd5c5bed8-9e79-414a-9198-b9496997819e', '$', 0.015952695, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Canadian dollar', false, 'CAD');
INSERT INTO expenser_db.master_currency VALUES (138, '02a9d53a-e768-4709-a1e5-7b1fc53d9a4f', 'F', 31.97763022, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Congolese franc', false, 'CDF');
INSERT INTO expenser_db.master_currency VALUES (139, '3645580c-0bc4-4b5d-94ef-89b46f8e85f9', 'Fr.', 0.010284263, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Swiss franc', false, 'CHF');
INSERT INTO expenser_db.master_currency VALUES (140, '35dc7d5e-c6e5-4caa-8ba1-ed5acc7c3055', '$', 10.68244953, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Chilean peso', false, 'CLP');
INSERT INTO expenser_db.master_currency VALUES (141, '64915fc6-0aa1-4dca-9fb9-699313a30335', '¥', 0.085677463, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Chinese/Yuan renminbi', false, 'CNY');
INSERT INTO expenser_db.master_currency VALUES (142, '619aa2da-ec4d-4191-9f1b-802ee5e4e892', 'Col$', 46.96384416, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Colombian peso', false, 'COP');
INSERT INTO expenser_db.master_currency VALUES (143, '97ff39c3-b553-41e1-8bac-5aa25b50fe21', '₡', 6.30790014, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Costa Rican colon', false, 'CRC');
INSERT INTO expenser_db.master_currency VALUES (144, '331457d6-53d8-4ed2-adb0-a63fae625996', '$', 0.012017148, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Cuban peso', false, 'CUC');
INSERT INTO expenser_db.master_currency VALUES (145, 'f6639a3e-f471-4285-8be5-cfea00bafe88', 'Esc', 1.20226012, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Cape Verdean escudo', false, 'CVE');
INSERT INTO expenser_db.master_currency VALUES (146, '43f1b771-a7f3-4f32-884b-27a9b8c665d9', 'Kč', 0.26824001, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Czech koruna', false, 'CZK');
INSERT INTO expenser_db.master_currency VALUES (147, '3a26c860-2e56-4c93-98bb-b27fb77cf0f7', 'Fdj', 2.1397446, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Djiboutian franc', false, 'DJF');
INSERT INTO expenser_db.master_currency VALUES (148, 'cced0fc7-f9b0-4500-b5c2-7f26377e1d19', 'Kr', 0.081378839, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Danish krone', false, 'DKK');
INSERT INTO expenser_db.master_currency VALUES (149, '050184c2-ac4f-451b-94ef-49ff87b39941', 'RD$', 0.6936302, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Dominican peso', false, 'DOP');
INSERT INTO expenser_db.master_currency VALUES (150, '31e4532b-92f4-4852-a874-5a63753a53a0', 'د.ج', 1.61736028, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Algerian dinar', false, 'DZD');
INSERT INTO expenser_db.master_currency VALUES (151, 'b5e22512-3385-4aa2-992f-5407dc73105c', 'KR', 0.17059337, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Estonian kroon', false, 'EEK');
INSERT INTO expenser_db.master_currency VALUES (152, '683d17f6-0b98-4254-9362-74ec611c9707', '£', 0.37134679, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Egyptian pound', false, 'EGP');
INSERT INTO expenser_db.master_currency VALUES (153, '335db19a-26ae-4949-833b-99bbda3d0671', 'Nfa', 0.18025722, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Eritrean nakfa', false, 'ERN');
INSERT INTO expenser_db.master_currency VALUES (154, '4e4d4556-84ea-49b3-8c9e-5f22c9af5b86', 'Br', 0.6767357, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Ethiopian birr', false, 'ETB');
INSERT INTO expenser_db.master_currency VALUES (155, '5573d655-2cd6-4766-aa73-2a2d39f21726', '€', 0.010902876, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'European Euro', false, 'EUR');
INSERT INTO expenser_db.master_currency VALUES (156, 'ccd04e3c-b02b-4c4f-8991-ad988313ee6b', 'FJ$', 0.026422913, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Fijian dollar', false, 'FJD');
INSERT INTO expenser_db.master_currency VALUES (157, '13ab371f-d67b-4de3-bc83-8237d3f2ef41', '£', 0.0094605594, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Falkland Islands pound', false, 'FKP');
INSERT INTO expenser_db.master_currency VALUES (158, '5c977eaf-5b51-471a-bab3-75a4d1a27e83', '£', 0.0094605594, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'British pound', false, 'GBP');
INSERT INTO expenser_db.master_currency VALUES (159, '2f069be5-57fe-4fea-923d-eee989f92394', 'GEL', 0.032299689, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Georgian lari', false, 'GEL');
INSERT INTO expenser_db.master_currency VALUES (160, '5cc13548-3429-453e-a56c-2141d72a15d6', 'GH₵', 0.14399751, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Ghanaian cedi', false, 'GHS');
INSERT INTO expenser_db.master_currency VALUES (161, '02922c7c-d1dd-4485-bea7-d50216ceda97', '£', 0.0094605594, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Gibraltar pound', false, 'GIP');
INSERT INTO expenser_db.master_currency VALUES (162, '14bd63de-8123-420c-b004-8e2988d92b0f', 'D', 0.80966351, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Gambian dalasi', false, 'GMD');
INSERT INTO expenser_db.master_currency VALUES (163, '5179c25d-1175-4fa7-a446-84f9279c6cf1', 'FG', 102.71543195, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Guinean franc', false, 'GNF');
INSERT INTO expenser_db.master_currency VALUES (164, '9acacfd4-6c56-48b8-aa0f-1905f60146be', 'Q', 0.0939865, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Guatemalan quetzal', false, 'GTQ');
INSERT INTO expenser_db.master_currency VALUES (165, 'c4955bea-edf5-4157-b8fc-d3b2646025f7', 'GY$', 2.51508744, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Guyanese dollar', false, 'GYD');
INSERT INTO expenser_db.master_currency VALUES (166, '214d0a5b-8ba7-4f8c-b3e7-a28d1bea1b4d', 'HK$', 0.093901313, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Hong Kong dollar', false, 'HKD');
INSERT INTO expenser_db.master_currency VALUES (167, '4e36625c-a813-48b6-a007-36dbbda89cde', 'L', 0.29644294, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Honduran lempira', false, 'HNL');
INSERT INTO expenser_db.master_currency VALUES (168, '4dc0a926-6d78-41b7-b606-006f08789b2c', 'kn', 0.082147718, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Croatian kuna', false, 'HRK');
INSERT INTO expenser_db.master_currency VALUES (169, '36312b0e-afda-46d7-b638-1843807f245c', 'G', 1.59207083, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Haitian gourde', false, 'HTG');
INSERT INTO expenser_db.master_currency VALUES (170, 'abc47abd-138d-409f-bae5-941ce0f0cdbb', 'Ft', 4.15652583, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Hungarian forint', false, 'HUF');
INSERT INTO expenser_db.master_currency VALUES (171, '226e48fa-6baa-4d81-b3d5-fd92d67fc5b8', 'Rp', 186.03932288, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Indonesian rupiah', false, 'IDR');
INSERT INTO expenser_db.master_currency VALUES (172, '1cc3305c-d0ff-4f78-94ca-b32129ffdd8c', '₪', 0.043305914, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Israeli new sheqel', false, 'ILS');
INSERT INTO expenser_db.master_currency VALUES (173, '397a0dd0-bbb8-4ef4-901a-8258ec65790e', '₹', 1.0, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Indian rupee', false, 'INR');
INSERT INTO expenser_db.master_currency VALUES (174, 'cecd3c35-9cd1-4e84-9079-d41334985642', 'د.ع', 15.74690226, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Iraqi dinar', false, 'IQD');
INSERT INTO expenser_db.master_currency VALUES (175, '7510290c-5602-4218-8c34-0b440151e314', 'IRR', 504.75165459, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Iranian rial', false, 'IRR');
INSERT INTO expenser_db.master_currency VALUES (176, 'e7c1b44b-db5a-43d1-94fb-2efb425345df', 'kr', 1.64076845, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Icelandic króna', false, 'ISK');
INSERT INTO expenser_db.master_currency VALUES (177, '798d43f7-aca2-4b2c-8870-58f280ec7586', 'J$', 1.87654294, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Jamaican dollar', false, 'JMD');
INSERT INTO expenser_db.master_currency VALUES (178, 'eeca3c4d-3ba5-449e-b8f2-0d88d34341d6', 'JOD', 0.0085201578, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Jordanian dinar', false, 'JOD');
INSERT INTO expenser_db.master_currency VALUES (179, '0672446f-29a8-4c4b-a4d7-ed19ba1ba91e', '¥', 1.71070239, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Japanese yen', false, 'JPY');
INSERT INTO expenser_db.master_currency VALUES (180, '2578b9d9-d215-45b3-b173-4039e71a91f3', 'KSh', 1.87031543, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Kenyan shilling', false, 'KES');
INSERT INTO expenser_db.master_currency VALUES (181, '3cdb70a7-5944-49fc-ae0a-988a7ae97148', 'сом', 1.07222999, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Kyrgyzstani som', false, 'KGS');
INSERT INTO expenser_db.master_currency VALUES (182, '4f75ce85-6d24-414f-bbea-d13658ad341a', '៛', 49.23443707, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Cambodian riel', false, 'KHR');
INSERT INTO expenser_db.master_currency VALUES (183, '0fa5bbc6-2a33-40cd-9086-b4c3ee33147f', 'KMF', 5.36386329, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Comorian franc', false, 'KMF');
INSERT INTO expenser_db.master_currency VALUES (184, '79bfec5a-80e5-4f7c-8c3d-b657d78b1625', 'W', 10.81543299, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'North Korean won', false, 'KPW');
INSERT INTO expenser_db.master_currency VALUES (185, '1c7da33a-289d-4c42-8632-5304dc9473e6', 'W', 15.60313989, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'South Korean won', false, 'KRW');
INSERT INTO expenser_db.master_currency VALUES (186, '3c99adc6-99a9-4246-b041-4a85ed993966', 'KWD', 0.0037085198, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Kuwaiti dinar', false, 'KWD');
INSERT INTO expenser_db.master_currency VALUES (187, '88df1b16-68a0-445e-89ab-ee0dbf265ecc', 'KY$', 0.0098541096, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Cayman Islands dollar', false, 'KYD');
INSERT INTO expenser_db.master_currency VALUES (188, 'd2c51775-24cd-462b-9a86-36bee9f886c9', 'T', 5.49441291, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Kazakhstani tenge', false, 'KZT');
INSERT INTO expenser_db.master_currency VALUES (189, '8aac0b86-eb77-4751-8ee8-dafe817dc4dc', 'KN', 246.1179904, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Lao kip', false, 'LAK');
INSERT INTO expenser_db.master_currency VALUES (190, '7bdbe230-87a3-4022-88ba-cf7834cc61f6', '£', 179.76758671, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Lebanese lira', false, 'LBP');
INSERT INTO expenser_db.master_currency VALUES (191, 'd1e1c3b0-92df-4843-9740-20293a7a3b4b', 'Rs', 3.91591208, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Sri Lankan rupee', false, 'LKR');
INSERT INTO expenser_db.master_currency VALUES (192, '26759c11-9b7b-4af7-a733-b3d29e669a68', 'L$', 2.26443181, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Liberian dollar', false, 'LRD');
INSERT INTO expenser_db.master_currency VALUES (193, 'db49345c-f0c5-4236-9712-4ace943611a1', 'M', 0.22200216, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Lesotho loti', false, 'LSL');
INSERT INTO expenser_db.master_currency VALUES (194, 'c84166c7-ec27-4675-b96c-13363f3971fb', 'Lt', 0.03764545, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Lithuanian litas', false, 'LTL');
INSERT INTO expenser_db.master_currency VALUES (195, '3cb1d595-6976-485d-ab80-b67cf78bfadd', 'Ls', 0.0076625411, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Latvian lats', false, 'LVL');
INSERT INTO expenser_db.master_currency VALUES (196, '2a1f20d2-3c64-4aad-bcc5-67b749c18325', 'LD', 0.057647496, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Libyan dinar', false, 'LYD');
INSERT INTO expenser_db.master_currency VALUES (197, 'b22a1345-2647-4c8a-a594-62922273db96', 'MAD', 0.11918282, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Moroccan dirham', false, 'MAD');
INSERT INTO expenser_db.master_currency VALUES (198, '39940f8f-c61c-4e60-9da8-c0da66c04c59', 'MDL', 0.21060111, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Moldovan leu', false, 'MDL');
INSERT INTO expenser_db.master_currency VALUES (199, '42406dab-2d90-4694-bf7e-0fc95b10a959', 'FMG', 54.58701017, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Malagasy ariary', false, 'MGA');
INSERT INTO expenser_db.master_currency VALUES (200, '58070e01-1602-47b4-b6a0-7f96c9cf1c98', 'MKD', 0.67044027, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Macedonian denar', false, 'MKD');
INSERT INTO expenser_db.master_currency VALUES (201, 'e8cd7367-f9b4-4e48-ab1f-9bfb00679f30', 'K', 25.1932494, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Myanma kyat', false, 'MMK');
INSERT INTO expenser_db.master_currency VALUES (202, 'e4fb3746-86c2-4a19-96c3-c26de19ee6bf', '₮', 41.10758885, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Mongolian tugrik', false, 'MNT');
INSERT INTO expenser_db.master_currency VALUES (203, '914c03f5-b54e-4855-a067-5ae53ae7f98c', 'P', 0.096718352, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Macanese pataca', false, 'MOP');
INSERT INTO expenser_db.master_currency VALUES (204, 'a7ad95a3-0b71-477c-b24e-d2be62149bca', 'UM', 4.76244191, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Mauritanian ouguiya', false, 'MRO');
INSERT INTO expenser_db.master_currency VALUES (205, '0d043a67-aaa5-4397-a277-3006e2dbab39', 'Rs', 0.52875828, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Mauritian rupee', false, 'MUR');
INSERT INTO expenser_db.master_currency VALUES (206, 'bafad9cd-6414-4c25-a667-c0ea60999b9f', 'Rf', 0.18578328, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Maldivian rufiyaa', false, 'MVR');
INSERT INTO expenser_db.master_currency VALUES (207, '1430ff96-3fed-4063-bad2-761c6a41118c', 'MK', 20.23086354, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Malawian kwacha', false, 'MWK');
INSERT INTO expenser_db.master_currency VALUES (208, '54cdd9df-ee5f-4b20-9d73-d468073aa7be', '$', 0.20400373, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Mexican peso', false, 'MXN');
INSERT INTO expenser_db.master_currency VALUES (209, '99891732-8413-4447-bbbf-168c5828bf4f', 'RM', 0.055639134, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Malaysian ringgit', false, 'MYR');
INSERT INTO expenser_db.master_currency VALUES (210, '07e337fb-e9d1-4b43-b510-1852c12183fc', 'MTn', 766.39629752, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Mozambican metical', false, 'MZM');
INSERT INTO expenser_db.master_currency VALUES (211, '893ed796-4008-482c-9acf-2f846b868329', 'N$', 0.22200216, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Namibian dollar', false, 'NAD');
INSERT INTO expenser_db.master_currency VALUES (212, 'cdc750cc-0b38-46aa-92ec-bc27a9002eb0', '₦', 10.88556346, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Nigerian naira', false, 'NGN');
INSERT INTO expenser_db.master_currency VALUES (213, '24c8e944-dcc1-4885-9fe2-a8ee2eea3a19', 'C$', 0.43979869, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Nicaraguan córdoba', false, 'NIO');
INSERT INTO expenser_db.master_currency VALUES (214, 'ba5abded-4b12-48b3-85fb-a8ec63f7d882', 'kr', 0.12259017, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Norwegian krone', false, 'NOK');
INSERT INTO expenser_db.master_currency VALUES (215, 'ccd5140e-dddf-4b88-8f13-d177e9dc271a', 'NRs', 1.60075, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Nepalese rupee', false, 'NPR');
INSERT INTO expenser_db.master_currency VALUES (216, '83ee2def-f571-4b2b-b6f9-c52068baddd6', 'NZ$', 0.019096098, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'New Zealand dollar', false, 'NZD');
INSERT INTO expenser_db.master_currency VALUES (217, 'fa7a3b69-f3e2-4b66-8746-e854f1ac1157', 'OMR', 0.0046278335, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Omani rial', false, 'OMR');
INSERT INTO expenser_db.master_currency VALUES (218, '3d42d8d0-72c2-45a2-a4c8-c03bf7a66323', 'B./', 0.012017148, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Panamanian balboa', false, 'PAB');
INSERT INTO expenser_db.master_currency VALUES (219, 'ef97d667-afd8-435d-b1c9-e834859f18a1', 'S/.', 0.044625452, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Peruvian nuevo sol', false, 'PEN');
INSERT INTO expenser_db.master_currency VALUES (220, '6b5032b8-4da4-49e2-94a4-e6748c10c7a5', 'K', 0.045135083, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Papua New Guinean kina', false, 'PGK');
INSERT INTO expenser_db.master_currency VALUES (221, 'd7f60c55-0985-4e7b-8370-25f19b363173', '₱', 0.66576196, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Philippine peso', false, 'PHP');
INSERT INTO expenser_db.master_currency VALUES (222, 'ad300994-9ab7-4a95-932d-c45bfe061332', 'Rs.', 3.39964219, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Pakistani rupee', false, 'PKR');
INSERT INTO expenser_db.master_currency VALUES (223, '56a55497-e267-4d03-97a5-db70aa6e25cd', 'zł', 0.047283951, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Polish zloty', false, 'PLN');
INSERT INTO expenser_db.master_currency VALUES (224, '5007c769-d2c3-40ca-b71d-3cdd3e1a57b7', '₲', 88.95023817, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Paraguayan guarani', false, 'PYG');
INSERT INTO expenser_db.master_currency VALUES (225, 'a6ea1cf6-4cdc-4178-aa06-4ff2c169d050', 'QR', 0.043742418, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Qatari riyal', false, 'QAR');
INSERT INTO expenser_db.master_currency VALUES (226, 'd4aeca27-693e-4c70-be1f-876751bff829', 'L', 0.054232165, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Romanian leu', false, 'RON');
INSERT INTO expenser_db.master_currency VALUES (227, 'b582d334-f272-4878-bcec-c94556f5390d', 'din.', 1.27868207, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Serbian dinar', false, 'RSD');
INSERT INTO expenser_db.master_currency VALUES (228, '1c91ccad-3fb4-4b12-9d48-d34f99c90e5e', 'R', 1.10950418, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Russian ruble', false, 'RUB');
INSERT INTO expenser_db.master_currency VALUES (229, '8d57ed8c-049a-4c12-95a3-542e62f55a65', 'SR', 0.045064304, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Saudi riyal', false, 'SAR');
INSERT INTO expenser_db.master_currency VALUES (230, '649ea7a9-4f92-4c65-910a-e0dacc69e8af', 'SI$', 0.10180547, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Solomon Islands dollar', false, 'SBD');
INSERT INTO expenser_db.master_currency VALUES (231, '100b5c95-54ed-479b-ac2d-809f31111bf1', 'SR', 0.17178249, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Seychellois rupee', false, 'SCR');
INSERT INTO expenser_db.master_currency VALUES (232, '74af7b1a-100d-4118-a390-7d5c8575a9b7', 'SDG', 7.19727834, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Sudanese pound', false, 'SDG');
INSERT INTO expenser_db.master_currency VALUES (233, 'b06c658c-af6f-43cd-8e53-e816822300ee', 'kr', 0.12029209, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Swedish krona', false, 'SEK');
INSERT INTO expenser_db.master_currency VALUES (234, '17c0cd99-2ad6-435c-83ed-48e482358127', 'S$', 0.015913641, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Singapore dollar', false, 'SGD');
INSERT INTO expenser_db.master_currency VALUES (235, 'b3ecade4-5f62-4a9a-aa93-861a6d5b94ec', '£', 0.0094605594, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Saint Helena pound', false, 'SHP');
INSERT INTO expenser_db.master_currency VALUES (236, '351808b1-1136-409b-9676-650a7e582e7a', 'Le', 272.60896362, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Sierra Leonean leone', false, 'SLL');
INSERT INTO expenser_db.master_currency VALUES (237, '1298df7a-b2cc-4646-b1c5-73c38769235c', 'Sh.', 6.86649014, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Somali shilling', false, 'SOS');
INSERT INTO expenser_db.master_currency VALUES (238, '7f4d7897-5193-4a64-9c0f-bd81aca51c27', '$', 0.44668152, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Surinamese dollar', false, 'SRD');
INSERT INTO expenser_db.master_currency VALUES (239, 'ef2d9936-c667-4125-b781-e1dc5eaf7fe4', 'LS', 156.24606472, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Syrian pound', false, 'SYP');
INSERT INTO expenser_db.master_currency VALUES (240, 'd76b24ca-0cd9-4e24-8e7b-5983bbdbc42f', 'E', 0.22200216, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Swazi lilangeni', false, 'SZL');
INSERT INTO expenser_db.master_currency VALUES (241, '1f27292a-2bc3-45ee-9324-943548bf278f', '฿', 0.41581597, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Thai baht', false, 'THB');
INSERT INTO expenser_db.master_currency VALUES (242, 'b7c96d71-bdec-4be2-9548-d5643c89e769', 'TJS', 0.13136843, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Tajikistani somoni', false, 'TJS');
INSERT INTO expenser_db.master_currency VALUES (243, 'caa0b490-6239-4917-ab4e-830374266a17', 'm', 0.042020214, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Turkmen manat', false, 'TMT');
INSERT INTO expenser_db.master_currency VALUES (244, '634b9a66-1e5e-475b-9332-1e4b84514fca', 'DT', 0.037034151, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Tunisian dinar', false, 'TND');
INSERT INTO expenser_db.master_currency VALUES (245, '2e5a2d6d-fcd6-4bb9-b3cf-66cdc62279bc', 'TRY', 0.35123929, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Turkish new lira', false, 'TRY');
INSERT INTO expenser_db.master_currency VALUES (246, 'b4fc837f-1e40-47e5-a34d-d2d78c5276de', 'TT$', 0.081515732, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Trinidad and Tobago dollar', false, 'TTD');
INSERT INTO expenser_db.master_currency VALUES (247, 'f1d61d21-37bb-41cb-aa7d-9a53850e4c27', 'NT$', 0.37379313, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'New Taiwan dollar', false, 'TWD');
INSERT INTO expenser_db.master_currency VALUES (248, '46993ee0-ae21-436c-8f2f-fcd9b363ce36', 'TZS', 30.19353637, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Tanzanian shilling', false, 'TZS');
INSERT INTO expenser_db.master_currency VALUES (249, 'e01220fe-601f-4700-86ac-7634ac22a6b7', 'UAH', 0.45055379, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Ukrainian hryvnia', false, 'UAH');
INSERT INTO expenser_db.master_currency VALUES (250, 'bc329201-eb89-4aaf-a997-9e88b8da8216', 'USh', 45.71687789, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Ugandan shilling', false, 'UGX');
INSERT INTO expenser_db.master_currency VALUES (251, 'c991f46a-1a49-4ea9-9ace-48319e144b22', 'US$', 0.012017148, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'United States dollar', false, 'USD');
INSERT INTO expenser_db.master_currency VALUES (252, '04d2e56c-db11-4974-9491-14f3281c2da1', '$U', 0.47188945, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Uruguayan peso', false, 'UYU');
INSERT INTO expenser_db.master_currency VALUES (253, '7951a95c-81de-4656-a765-7146e457a2f4', 'UZS', 148.49722115, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Uzbekistani som', false, 'UZS');
INSERT INTO expenser_db.master_currency VALUES (254, 'ef90cc9e-7cf0-45b4-8f2d-132b8ab1de13', 'Bs', 42819314.00764684, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Venezuelan bolivar', false, 'VEB');
INSERT INTO expenser_db.master_currency VALUES (255, 'a0e25113-fc00-4da4-a296-64a4634597fb', '₫', 292.49581507, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Vietnamese dong', false, 'VND');
INSERT INTO expenser_db.master_currency VALUES (256, 'bf231823-bf65-4ed0-8130-e8a4ed475667', 'VT', 1.40879398, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Vanuatu vatu', false, 'VUV');
INSERT INTO expenser_db.master_currency VALUES (257, '898c9871-ac2f-405d-96b9-4c2b0f0be669', 'WS$', 0.032243321, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Samoan tala', false, 'WST');
INSERT INTO expenser_db.master_currency VALUES (258, '55110452-b410-43f0-b523-e2702008905a', 'CFA', 7.15181771, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Central African CFA franc', false, 'XAF');
INSERT INTO expenser_db.master_currency VALUES (259, '7293944e-774a-403f-b6f4-5c955459d974', 'EC$', 0.032446336, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'East Caribbean dollar', false, 'XCD');
INSERT INTO expenser_db.master_currency VALUES (260, '469fcd53-41ad-48cb-a760-921edded3032', 'SDR', 0.0089541218, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Special Drawing Rights', false, 'XDR');
INSERT INTO expenser_db.master_currency VALUES (261, 'cfbe2e85-80d8-4eae-a427-f9b591a086de', 'CFA', 7.15181771, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'West African CFA franc', false, 'XOF');
INSERT INTO expenser_db.master_currency VALUES (262, '8e202047-6e18-4fb7-98ab-489fc162918e', 'F', 1.30105917, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'CFP franc', false, 'XPF');
INSERT INTO expenser_db.master_currency VALUES (263, '5169a677-e327-4b28-8552-1f4a22e7327b', 'YER', 3.00675837, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Yemeni rial', false, 'YER');
INSERT INTO expenser_db.master_currency VALUES (264, '1c22f74c-ec26-46d5-a7c0-dc23c5e49cc4', 'R', 0.22200216, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'South African rand', false, 'ZAR');
INSERT INTO expenser_db.master_currency VALUES (265, '3a1a3729-c4ed-4c87-a2a3-676f7a4cd269', 'ZK', 306.08565035, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Zambian kwacha', false, 'ZMK');
INSERT INTO expenser_db.master_currency VALUES (266, '55d7eed1-365f-4e58-b4c2-c1d03c3e0f6d', 'Z$', 3.863957, '2023-12-24', 'SYSTEM', 'SYSTEM', '2023-12-24 20:52:02.109021', '2023-12-24 20:52:02.109021', 'Zimbabwean dollar', false, 'ZWR');


--
-- Data for Name: on_demand_event; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: product_configurations; Type: TABLE DATA; Schema: expenser_db; Owner: neeraj
--

INSERT INTO expenser_db.product_configurations VALUES (104, 'BASE_URL', 'http://localhost:8080', 'ca59bd49-489e-4721-ae9b-35a9992d90cd', NULL, NULL, NULL, NULL, NULL);
INSERT INTO expenser_db.product_configurations VALUES (105, 'BASE_CURRENCY', 'INR', 'ca99fc0f-1efc-4595-b4fc-a0e76b8efd3d', NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: record_labels; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: record_location; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: user_accounts; Type: TABLE DATA; Schema: expenser_db; Owner: neeraj
--

INSERT INTO expenser_db.user_accounts VALUES (104, 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', '294ac1ac-d4d8-4e11-9c70-7028dd51801c', 'General', 'SBI', '#1677FF', 0, NULL, 86, 'e24b9cc8-6603-4484-b47d-2a79905d4c11', 'nirnay.mittal@example.com', 'nirnay.mittal@example.com', '2023-12-25 23:26:00.248', '2023-12-25 23:26:00.248', false, false, false, false);
INSERT INTO expenser_db.user_accounts VALUES (105, 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', '1c2b03c9-574b-4453-ac6c-fafb15d52cad', 'Account_With_Overdraft', '123', '#1677FF', 0, NULL, 0, 'e24b9cc8-6603-4484-b47d-2a79905d4c11', 'nirnay.mittal@example.com', 'nirnay.mittal@example.com', '2023-12-26 00:36:43.998', '2023-12-26 00:36:43.998', false, false, false, false);
INSERT INTO expenser_db.user_accounts VALUES (106, 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', '277937d5-3919-44a4-90ab-435b076d987c', 'Current_Account', 'qqq', '#1677FF', 0, NULL, 0, '8fa28d6d-96e1-4536-ba5e-1c9fad6e95e2', 'nirnay.mittal@example.com', 'nirnay.mittal@example.com', '2023-12-26 00:37:34.493', '2023-12-26 00:37:34.493', false, false, false, false);


--
-- Data for Name: user_category; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: user_currency; Type: TABLE DATA; Schema: expenser_db; Owner: neeraj
--

INSERT INTO expenser_db.user_currency VALUES (100, 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', '8fa28d6d-96e1-4536-ba5e-1c9fad6e95e2', 1, '397a0dd0-bbb8-4ef4-901a-8258ec65790e', 'SYSTEM', 'SYSTEM', '2023-12-24 23:34:55.113333', '2023-12-24 23:34:55.113333', false, false, true, NULL, 1);
INSERT INTO expenser_db.user_currency VALUES (105, 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', 'a078224a-a000-4cad-aa09-11a3c4334bb3', 0.8408, '5444aa1e-f06f-4074-88f9-7b8d51665b1f', 'nirnay.mittal@example.com', 'nirnay.mittal@example.com', '2023-12-25 01:54:23.308', '2023-12-25 01:54:23.308', false, false, false, NULL, 3);
INSERT INTO expenser_db.user_currency VALUES (101, 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', 'e24b9cc8-6603-4484-b47d-2a79905d4c11', 1, 'a416f160-4a33-4369-a3fa-cbea3e4d9cbf', 'nirnay.mittal@example.com', 'nirnay.mittal@example.com', '2023-12-25 01:44:16.799', '2023-12-25 16:07:30.731', false, false, false, NULL, 2);
INSERT INTO expenser_db.user_currency VALUES (106, 'bb25a23d-1a2b-44a4-b60f-06027196b7cb', 'cf847e23-6b9a-4555-8b1f-f2697e2939e9', 1.1296, '39bc02d2-0c0d-4bf7-bb61-6684a416faa9', 'nirnay.mittal@example.com', 'nirnay.mittal@example.com', '2023-12-25 02:28:59.972', '2023-12-25 02:28:59.972', false, true, false, NULL, 10);


--
-- Data for Name: user_labels; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: user_records; Type: TABLE DATA; Schema: expenser_db; Owner: expenser_db
--



--
-- Data for Name: users; Type: TABLE DATA; Schema: expenser_db; Owner: neeraj
--

INSERT INTO expenser_db.users VALUES (105, 'Niray Mittal', 'nirnay@mittal.com', '$2a$10$OLMHjLpX/Fl2jAu5zBP4quVbYujC6IvAdwLJp5vjaYOjsaP0s47N6', '87eee027-8271-43cf-8d04-6fcbd24cde6e', 'Niray', 'Mittal', 'anonymousUser', 'anonymousUser', '2023-12-23 11:57:51.988', '2023-12-23 11:57:51.988', false, false);
INSERT INTO expenser_db.users VALUES (106, 'Nirnay Mittal', 'nirnay.mittal@example.com', '$2a$10$wIxzX8fGP4Pmu2ZZlTVRc.xgD0sVFQRq8.CLchDFaO6GZcaNJhP3C', '4a757834-91c5-43c5-be62-05e36f3cc378', 'Nirnay', 'Mittal', 'anonymousUser', 'anonymousUser', '2023-12-23 12:08:03.227', '2023-12-23 12:08:19.937', false, true);


--
-- Name: account_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.account_seq', 106, true);


--
-- Name: authorities_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.authorities_id_seq', 1, false);


--
-- Name: authority_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.authority_seq', 106, true);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.client_id_seq', 1, false);


--
-- Name: client_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.client_seq', 7, true);


--
-- Name: example_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.example_id_seq', 1, false);


--
-- Name: file_upload_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.file_upload_id_seq', 1, false);


--
-- Name: file_upload_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.file_upload_seq', 100, false);


--
-- Name: link_token_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.link_token_id_seq', 1, false);


--
-- Name: link_token_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.link_token_seq', 106, true);


--
-- Name: master_currency_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.master_currency_id_seq', 1, false);


--
-- Name: master_currency_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.master_currency_seq', 266, true);


--
-- Name: on_demand_event_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.on_demand_event_id_seq', 1, false);


--
-- Name: on_demand_event_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.on_demand_event_seq', 1, false);


--
-- Name: product_config_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.product_config_seq', 105, true);


--
-- Name: product_configurations_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.product_configurations_id_seq', 1, false);


--
-- Name: record_label_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.record_label_seq', 100, false);


--
-- Name: record_labels_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.record_labels_id_seq', 1, false);


--
-- Name: record_location_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.record_location_id_seq', 1, false);


--
-- Name: record_location_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.record_location_seq', 100, false);


--
-- Name: user_accounts_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.user_accounts_id_seq', 1, false);


--
-- Name: user_category_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.user_category_id_seq', 1, false);


--
-- Name: user_curr_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.user_curr_seq', 106, true);


--
-- Name: user_currency_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.user_currency_id_seq', 1, false);


--
-- Name: user_label_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.user_label_seq', 100, false);


--
-- Name: user_labels_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.user_labels_id_seq', 1, false);


--
-- Name: user_record_cat_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.user_record_cat_seq', 100, false);


--
-- Name: user_record_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.user_record_seq', 100, false);


--
-- Name: user_records_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: expenser_db
--

SELECT pg_catalog.setval('expenser_db.user_records_id_seq', 1, false);


--
-- Name: user_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.user_seq', 106, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: expenser_db; Owner: neeraj
--

SELECT pg_catalog.setval('expenser_db.users_id_seq', 1, false);


--
-- Name: authorities authorities_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (id);


--
-- Name: client client_client_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.client
    ADD CONSTRAINT client_client_identifier_key UNIQUE (client_identifier);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: client client_user_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.client
    ADD CONSTRAINT client_user_identifier_key UNIQUE (user_identifier);


--
-- Name: example example_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.example
    ADD CONSTRAINT example_pkey PRIMARY KEY (id);


--
-- Name: file_upload file_upload_file_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.file_upload
    ADD CONSTRAINT file_upload_file_identifier_key UNIQUE (file_identifier);


--
-- Name: file_upload file_upload_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.file_upload
    ADD CONSTRAINT file_upload_pkey PRIMARY KEY (id);


--
-- Name: link_token link_token_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.link_token
    ADD CONSTRAINT link_token_pkey PRIMARY KEY (id);


--
-- Name: master_currency master_currency_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.master_currency
    ADD CONSTRAINT master_currency_identifier_key UNIQUE (identifier);


--
-- Name: master_currency master_currency_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.master_currency
    ADD CONSTRAINT master_currency_pkey PRIMARY KEY (id);


--
-- Name: on_demand_event on_demand_event_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.on_demand_event
    ADD CONSTRAINT on_demand_event_pkey PRIMARY KEY (id);


--
-- Name: product_configurations product_configurations_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.product_configurations
    ADD CONSTRAINT product_configurations_pkey PRIMARY KEY (id);


--
-- Name: record_labels record_labels_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_labels
    ADD CONSTRAINT record_labels_identifier_key UNIQUE (identifier);


--
-- Name: record_labels record_labels_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_labels
    ADD CONSTRAINT record_labels_pkey PRIMARY KEY (id);


--
-- Name: record_location record_location_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_location
    ADD CONSTRAINT record_location_identifier_key UNIQUE (identifier);


--
-- Name: record_location record_location_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_location
    ADD CONSTRAINT record_location_pkey PRIMARY KEY (id);


--
-- Name: user_category uk_6hga2iabgx14v9lvrfvcy4mem; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_category
    ADD CONSTRAINT uk_6hga2iabgx14v9lvrfvcy4mem UNIQUE (identifier);


--
-- Name: user_accounts uk_6vxoeargg0m47v16nalut1qhp; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_accounts
    ADD CONSTRAINT uk_6vxoeargg0m47v16nalut1qhp UNIQUE (account_identifier);


--
-- Name: client uk_9u22y1kwru4ks27u48mlc5m1o; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.client
    ADD CONSTRAINT uk_9u22y1kwru4ks27u48mlc5m1o UNIQUE (client_identifier);


--
-- Name: users uk_hl8cmkyvgqsgelu76p0x79wjb; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.users
    ADD CONSTRAINT uk_hl8cmkyvgqsgelu76p0x79wjb UNIQUE (user_identifier);


--
-- Name: user_currency uk_lqcnt1cfpi2kifj17ntvd0cyv; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_currency
    ADD CONSTRAINT uk_lqcnt1cfpi2kifj17ntvd0cyv UNIQUE (identifier);


--
-- Name: master_currency uk_oakm1ly9g0efvfa13uv1l3c7y; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.master_currency
    ADD CONSTRAINT uk_oakm1ly9g0efvfa13uv1l3c7y UNIQUE (identifier);


--
-- Name: user_records uk_qmqvpljwnxs3qiq2mmrcixerp; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT uk_qmqvpljwnxs3qiq2mmrcixerp UNIQUE (identifier);


--
-- Name: user_accounts user_accounts_account_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_accounts
    ADD CONSTRAINT user_accounts_account_identifier_key UNIQUE (account_identifier);


--
-- Name: user_accounts user_accounts_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_accounts
    ADD CONSTRAINT user_accounts_pkey PRIMARY KEY (id);


--
-- Name: user_category user_category_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_category
    ADD CONSTRAINT user_category_identifier_key UNIQUE (identifier);


--
-- Name: user_category user_category_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_category
    ADD CONSTRAINT user_category_pkey PRIMARY KEY (id);


--
-- Name: user_currency user_currency_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_currency
    ADD CONSTRAINT user_currency_identifier_key UNIQUE (identifier);


--
-- Name: user_currency user_currency_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_currency
    ADD CONSTRAINT user_currency_pkey PRIMARY KEY (id);


--
-- Name: user_labels user_labels_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_labels
    ADD CONSTRAINT user_labels_identifier_key UNIQUE (identifier);


--
-- Name: user_labels user_labels_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_labels
    ADD CONSTRAINT user_labels_pkey PRIMARY KEY (id);


--
-- Name: user_records user_records_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT user_records_identifier_key UNIQUE (identifier);


--
-- Name: user_records user_records_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT user_records_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_user_identifier_key; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.users
    ADD CONSTRAINT users_user_identifier_key UNIQUE (user_identifier);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: user_accounts acc_client_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_accounts
    ADD CONSTRAINT acc_client_fk FOREIGN KEY (client_identifier) REFERENCES expenser_db.client(client_identifier);


--
-- Name: user_accounts acc_curr_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_accounts
    ADD CONSTRAINT acc_curr_fk FOREIGN KEY (account_currency) REFERENCES expenser_db.user_currency(identifier);


--
-- Name: user_category cat_parent_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_category
    ADD CONSTRAINT cat_parent_fk FOREIGN KEY (parent_identifier) REFERENCES expenser_db.user_category(identifier);


--
-- Name: record_labels fk6vbb7199di6dhdgjbuahehf0e; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_labels
    ADD CONSTRAINT fk6vbb7199di6dhdgjbuahehf0e FOREIGN KEY (record_id) REFERENCES expenser_db.user_records(id);


--
-- Name: file_upload fk_account; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.file_upload
    ADD CONSTRAINT fk_account FOREIGN KEY (account_identifier) REFERENCES expenser_db.user_accounts(account_identifier);


--
-- Name: file_upload fk_client; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.file_upload
    ADD CONSTRAINT fk_client FOREIGN KEY (client_identifier) REFERENCES expenser_db.client(client_identifier);


--
-- Name: client fkf7r3lgdiow7jebx017bxjgxat; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.client
    ADD CONSTRAINT fkf7r3lgdiow7jebx017bxjgxat FOREIGN KEY (user_identifier) REFERENCES expenser_db.users(user_identifier);


--
-- Name: user_records fkqygunaacf92f1idho0u6wppum; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT fkqygunaacf92f1idho0u6wppum FOREIGN KEY (record_location_id) REFERENCES expenser_db.record_location(id);


--
-- Name: record_labels fktq51xkiampgicaaqhq0acdyld; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_labels
    ADD CONSTRAINT fktq51xkiampgicaaqhq0acdyld FOREIGN KEY (user_label_id) REFERENCES expenser_db.user_labels(id);


--
-- Name: link_token fpt_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.link_token
    ADD CONSTRAINT fpt_fk FOREIGN KEY (user_identifier) REFERENCES expenser_db.users(user_identifier);


--
-- Name: user_currency master_cur_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_currency
    ADD CONSTRAINT master_cur_fk FOREIGN KEY (master_currency_identifier) REFERENCES expenser_db.master_currency(identifier);


--
-- Name: user_records rec_acc_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT rec_acc_fk FOREIGN KEY (account_identifier) REFERENCES expenser_db.user_accounts(account_identifier);


--
-- Name: user_records rec_cat_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT rec_cat_fk FOREIGN KEY (user_category_identifier) REFERENCES expenser_db.user_category(identifier);


--
-- Name: user_records rec_curr_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT rec_curr_fk FOREIGN KEY (user_currency_identifier) REFERENCES expenser_db.user_currency(identifier);


--
-- Name: user_records refund_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT refund_fk FOREIGN KEY (refund_record_identifier) REFERENCES expenser_db.user_records(identifier);


--
-- Name: record_labels rl_rec_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_labels
    ADD CONSTRAINT rl_rec_fk FOREIGN KEY (record_identifier) REFERENCES expenser_db.user_records(identifier);


--
-- Name: record_labels rl_ul_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_labels
    ADD CONSTRAINT rl_ul_fk FOREIGN KEY (user_label_identifier) REFERENCES expenser_db.user_labels(identifier);


--
-- Name: record_location rloc_rec_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.record_location
    ADD CONSTRAINT rloc_rec_fk FOREIGN KEY (record_identifier) REFERENCES expenser_db.user_records(identifier);


--
-- Name: user_records transfer_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT transfer_fk FOREIGN KEY (transfer_transaction_identifier) REFERENCES expenser_db.user_records(identifier);


--
-- Name: authorities ua_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.authorities
    ADD CONSTRAINT ua_fk FOREIGN KEY (user_id) REFERENCES expenser_db.users(id);


--
-- Name: user_category uc_client_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_category
    ADD CONSTRAINT uc_client_fk FOREIGN KEY (client_identifier) REFERENCES expenser_db.client(client_identifier);


--
-- Name: user_currency uc_client_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: neeraj
--

ALTER TABLE ONLY expenser_db.user_currency
    ADD CONSTRAINT uc_client_fk FOREIGN KEY (client_identifier) REFERENCES expenser_db.client(client_identifier);


--
-- Name: user_labels ul_client_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_labels
    ADD CONSTRAINT ul_client_fk FOREIGN KEY (client_identifier) REFERENCES expenser_db.client(client_identifier);


--
-- Name: user_records ur_client_fk; Type: FK CONSTRAINT; Schema: expenser_db; Owner: expenser_db
--

ALTER TABLE ONLY expenser_db.user_records
    ADD CONSTRAINT ur_client_fk FOREIGN KEY (client_identifier) REFERENCES expenser_db.client(client_identifier);


--
-- PostgreSQL database dump complete
--

