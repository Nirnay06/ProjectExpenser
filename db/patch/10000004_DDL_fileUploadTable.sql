
create table file_upload
(
id number primary key,
file_name varchar2(1000) not null,
client_identifier varchar2(2000) not null,
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

alter table user_records 
add file_upload_id number;

--//@UNDO

drop table FILE_UPLOAD
/
drop sequence FILE_UPLOAD_SEQ
/
