
-- START CHANGE SCRIPT #10000003: 10000003_DDL_changeForiegnKey.sql





INSERT INTO CHANGELOG_Expenser (change_number, complete_dt, applied_by, description)
 VALUES (10000003, CURRENT_TIMESTAMP, USER, '10000003_DDL_changeForiegnKey.sql');

COMMIT;

-- END CHANGE SCRIPT #10000003: 10000003_DDL_changeForiegnKey.sql


-- START CHANGE SCRIPT #10000004: 10000004_DDL_fileUploadTable.sql


create table file_upload
(
id number primary key,
file_name varchar2(1000) not null,
client_identifier varchar2(2000) unique not null,
file_identifier varchar2 (500) unique not null,
file_data CLOB not null,
file_meta_data CLOB not null, 
record_start_date date,
record_end_date date,
file_imported number(1),
account_identifier varchar2(1000) not null,
created_by varchar2(2000) ,
updated_by varchar2(2000),
created_date TIMESTAMP,
updated_date TIMESTAMP,
deleted number(1),
FOREIGN KEY (CLIENT_IDENTIFIER) REFERENCES Client(CLIENT_IDENTIFIER),
FOREIGN KEY (account_identifier) REFERENCES user_accounts(account_identifier)
)
/

CREATE SEQUENCE FILE_UPLOAD_SEQ INCREMENT BY 1 START WITH 100 MINVALUE 100
/





INSERT INTO CHANGELOG_Expenser (change_number, complete_dt, applied_by, description)
 VALUES (10000004, CURRENT_TIMESTAMP, USER, '10000004_DDL_fileUploadTable.sql');

COMMIT;

-- END CHANGE SCRIPT #10000004: 10000004_DDL_fileUploadTable.sql

