
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
insert into product_configurations(id, key,  value, uuid) values (PRODUCT_Config_seq.nextval, 'BASE_URL','http://localhost:8080', getSysUUID());
update product_configurations set deleted=0 ;