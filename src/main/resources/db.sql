
create table users
(
id number primary key,
full_name varchar2(1000) not null,
username varchar2(1000) UNIQUE not null,
password varchar2(2000) not null,
user_identifier varchar2(2000) unique not null,
first_name varchar2 (500) not null
last_name varchar2(500) not null;
);

create table authorities(
id number primary key,
name varchar2(1000) not null,
user_id number not null,
CONSTRAINT ua_fk
    FOREIGN KEY (user_id)
    REFERENCES users(id)
);

CREATE SEQUENCE USER_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;
CREATE SEQUENCE AUTHORITY_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;

alter table users
add  created_by varchar2(2000) add  updated_by varchar2(2000) add  created_date TIMESTAMP add  updated_date TIMESTAMP  add deleted number(1);

alter table authorities
add  created_by varchar2(2000) add  updated_by varchar2(2000) add  created_date TIMESTAMP add  updated_date TIMESTAMP  add deleted number(1);


create table link_token(
id number primary key,
generation_time timestamp not null,
expiration_time timestamp not null,
token_identifier varchar2(2000) not null,
user_identifier varchar2(2000) not null ,
token_type varchar2(2000),
is_active number(1),
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT fpt_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
);

CREATE SEQUENCE LINK_TOKEN_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;

alter table users add enabled number(1) not null;



create table product_configurations (
id number primary key,
key varchar2(2000) not null,
value varchar2(2000) not null,
uuid varchar2(2000),
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1));

CREATE SEQUENCE PRODUCT_CONFIG_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;

/
create or replace NONEDITIONABLE function getSysUUID
RETURN varchar2
IS 
   sysUUID varchar2(100);
BEGIN  
   select lower(regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{8})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{12})'
       , '\1-\2-\3-\4-\5') ) into sysUUID from dual;
	   return sysUUID;
END getSysUUID;

/
insert into product_configurations(id, key,  value, uuid,deleted) values (PRODUCT_Config_seq.nextval, 'BASE_URL','http://localhost:8080', getSysUUID(),0);

insert into product_configurations(id, key,  value, uuid,deleted) values (PRODUCT_Config_seq.nextval, 'BASE_CURRENCY','INR', getSysUUID(),0);


create table user_accounts(
id number primary key,
user_identifier varchar2(2000) not null ,
account_identifier varchar2(2000) not null unique,
is_active number(1) default 1 not null,
account_type varchar2(2000) not null,
account_name varchar2(2000) not null,
account_color varchar2(2000),
account_balance number default 0 not null,
icon varchar2(2000) ,
initial_balance number,
is_exclude_from_stats number(1) default 0 not null,
is_archived number(10) default 0 not null,
account_currency varchar2(2000) not null,
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT user_acc_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
);


create table master_currency(
id number primary key,
identifier varchar2(2000) not null unique,
currency_symbol varchar2(100) not null unique,
current_conversion_rate number not null,
last_synced_date date,
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
);


create table user_currency(
id number primary key,
user_identifier varchar2(2000) not null,
identifier varchar2(2000) not null UNIQUE,
currency_title varchar2(2000) not null,
currency_icon varchar2(2000) not null,
user_currency_rate number,
is_base_currency number(1),
master_currency_identifier varchar2(2000), 
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT user_curr_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
,CONSTRAINT master_cur_fk FOREIGN key (master_currency_identifier) REFERENCES master_currency(identifier)
);

create table user_category (
id number primary key,
identifier  varchar2(2000) not null unique,
user_identifier varchar2(2000) not null,
parent_identifier varchar2(2000),
icon varchar2(2000) not null,
color varchar2(2000) not null,
hidden varchar2(2000) not null,
is_default_category number(1) not null,
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT user_cat_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
,CONSTRAINT cat_parent_fk FOREIGN key (parent_identifier) REFERENCES user_category(identifier)
);

create table user_records(
id number primary key,
identifier varchar2(2000) not null unique,
user_identifier varchar2(2000) not null,
account_identifier varchar2(2000) not null,
record_type varchar2(2000) not null,
user_currency_identifier varchar2(2000) not null,
amount number default 0 not null,
user_category_identifier varchar2(2000) not null,
record_date date not null,
payment_type varchar2(2000),
payment_status varchar2(2000),
refund_record_identifier varchar2(2000),
transfer_transaction_identifier varchar2(2000),
comments varchar2(2000),
payee varchar2(2000),
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT user_rec_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
,CONSTRAINT rec_acc_fk FOREIGN key (account_identifier) REFERENCES user_accounts(account_identifier)
,CONSTRAINT rec_curr_fk FOREIGN key (user_currency_identifier) REFERENCES user_currency(identifier)
,CONSTRAINT rec_cat_fk FOREIGN key (user_category_identifier) REFERENCES user_category(identifier)
,CONSTRAINT refund_fk FOREIGN key (refund_record_identifier) REFERENCES user_records(identifier)
,CONSTRAINT transfer_fk FOREIGN key (transfer_transaction_identifier) REFERENCES user_records(identifier)
);


create table user_labels (
id number primary key,
identifier varchar2(2000) not null unique,
user_identifier varchar2(2000) not null,
is_archive number(1) not null,
title varchar2(2000) not null,
color varchar2(2000) not null,
default_assign number(1) default 0,
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT user_lab_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
);


create table record_labels(
id number primary key,
identifier varchar2(2000) not null unique,
user_identifier varchar2(2000) not null,
record_identifier varchar2(2000) not null,
user_label_identifier varchar2(2000) not null,
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT rl_user_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
,CONSTRAINT rl_rec_fk FOREIGN key (record_identifier) REFERENCES user_records(identifier)
,CONSTRAINT rl_ul_fk FOREIGN key (user_label_identifier) REFERENCES user_labels(identifier)
);


create table record_location(
id number primary key,
identifier varchar2(2000) not null unique,
user_identifier varchar2(2000) not null,
record_identifier varchar2(2000) not null,
latitude number not null,
longitude number not null,
title varchar2(2000) not null,
address_line varchar2(2000),
city varchar2(2000),
state varchar2(2000),
country varchar2(2000),
modified number(1),
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1)
,CONSTRAINT rloc_user_fk FOREIGN key (user_identifier) REFERENCES users(user_identifier)
,CONSTRAINT rloc_rec_fk FOREIGN key (record_identifier) REFERENCES user_records(identifier)
);

alter table user_accounts add CONSTRAINT acc_curr_fk FOREIGN key (account_currency) REFERENCES user_currency(identifier);

alter table user_currency add is_rate_overriden number(1);

CREATE SEQUENCE MASTER_CURRENCY_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;
CREATE SEQUENCE RECORD_LABEL_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;
CREATE SEQUENCE RECORD_LOCATION_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;
CREATE SEQUENCE USER_RECORD_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;


CREATE SEQUENCE ACCOUNT_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;
CREATE SEQUENCE USER_CURR_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;
CREATE SEQUENCE USER_LABEL_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;
CREATE SEQUENCE USER_RECORD_CAT_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100;

insert into master_currency(id,identifier, currency_symbol, current_conversion_rate) values (MASTER_CURRENCY_SEQ.nextval, (select getsysuuid from dual), 'INR', '1');

alter table user_Category add  category_title varchar2(2000) not null;




--select * from users;--0bf37723-f19d-470d-97db-3678f056cadd
--select * from master_currency;--f0697940-b1d8-4a65-ae97-5e566329ae6d
--select * from user_currency;--d8e7c565-802c-41a8-808b-5c60b7ce7da9
--select * from USER_CATEGORY;--c7446fcb-2a84-49e4-957f-b0677d3204e5
--select * from USER_ACCOUNTS;--f34cdf77-3bc7-49ef-b023-a2ce7c6527df
--insert into USER_ACCOUNTS(id, user_identifier, account_identifier, account_type, account_name, account_color, account_balance, icon, initial_balance, account_currency)
--values (USER_RECORD_CAT_SEQ.nextval,'0bf37723-f19d-470d-97db-3678f056cadd', (select getSysUUID() from dual), 'default','Federal Bank','#f44336',0,'redeem',0,'d8e7c565-802c-41a8-808b-5c60b7ce7da9' );