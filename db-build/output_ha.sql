
-- START CHANGE SCRIPT #10000001: 10000001_initialDbSetup.sql


ALTER TABLE user_accounts
DROP CONSTRAINT USER_ACC_FK
/

ALTER TABLE USER_ACCOUNTS RENAME COLUMN USER_IDENTIFIER TO CLIENT_IDENTIFIER
/

alter table USER_ACCOUNTS
add FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER)
/

ALTER TABLE record_location
DROP CONSTRAINT RLOC_USER_FK
/

ALTER TABLE record_location RENAME COLUMN USER_IDENTIFIER TO CLIENT_IDENTIFIER
/

alter table record_location
add FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER)
/

ALTER TABLE record_labels
DROP CONSTRAINT RL_USER_FK
/

ALTER TABLE record_labels RENAME COLUMN USER_IDENTIFIER TO CLIENT_IDENTIFIER
/

alter table record_labels
add FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER)
/

ALTER TABLE user_labels
DROP CONSTRAINT USER_LAB_FK
/

ALTER TABLE user_labels RENAME COLUMN USER_IDENTIFIER TO CLIENT_IDENTIFIER
/

alter table user_labels
add FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER)
/

ALTER TABLE user_records
DROP CONSTRAINT USER_REC_FK
/

ALTER TABLE user_records RENAME COLUMN USER_IDENTIFIER TO CLIENT_IDENTIFIER
/

alter table user_records
add FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER)
/
---------
ALTER TABLE user_category
DROP CONSTRAINT user_cat_fk
/

ALTER TABLE user_category RENAME COLUMN USER_IDENTIFIER TO CLIENT_IDENTIFIER
/

alter table user_category
add FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER)
/

--------
ALTER TABLE user_currency
DROP CONSTRAINT user_curr_fk
/

ALTER TABLE user_currency RENAME COLUMN USER_IDENTIFIER TO CLIENT_IDENTIFIER
/

alter table user_currency
add FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER)
/



ALTER TABLE USER_ACCOUNTS  
drop COLUMN is_archived
/

ALTER TABLE USER_ACCOUNTS  
add is_archived number(1) default 0 not null 
/

alter table record_labels 
drop column client_identifier
/

alter table record_location
drop column client_identifier
/

alter table user_records
modify record_date timestamp
/

alter table user_category 
modify hidden varchar2(2000) null
/

update user_category set hidden = 0
/

alter table user_category 
modify hidden number(1) default 0
/
update user_category set hidden = 0
/
alter table user_category 
modify hidden number(1) default 0 not null
/

alter table user_category 
add selectable number(1) default 0 not null
/

alter table user_category 
add category_order number
/ 

create table on_demand_event
(
id number primary key,
created_by varchar2(2000) ,  updated_by varchar2(2000) ,  created_date TIMESTAMP ,  updated_date TIMESTAMP  , deleted number(1),
key varchar2(2000), value varchar2(2000), active number(1), error_msg  blob
)
/


INSERT INTO CHANGELOG_Expenser (change_number, complete_dt, applied_by, description)
 VALUES (10000001, CURRENT_TIMESTAMP, USER, '10000001_initialDbSetup.sql');

COMMIT;

-- END CHANGE SCRIPT #10000001: 10000001_initialDbSetup.sql

