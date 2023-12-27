
-- START CHANGE SCRIPT #10000002: 10000002_master_currency.sql

alter table master_currency 
add currency_title varchar2(2000) not null
/

insert into master_currency(id,identifier, currency_symbol, current_conversion_rate, currency_title, deleted) values (MASTER_CURRENCY_SEQ.nextval, (select getsysuuid from dual), 'INR', '1','Indian Rupee', 0 )
/



INSERT INTO CHANGELOG_Expenser (change_number, complete_dt, applied_by, description)
 VALUES (10000002, CURRENT_TIMESTAMP, USER, '10000002_master_currency.sql');

COMMIT;

-- END CHANGE SCRIPT #10000002: 10000002_master_currency.sql


-- START CHANGE SCRIPT #10000003: 10000003_DDL_changeForiegnKey.sql


alter table user_records 
add record_location_id number
/

delete from record_location
/

alter table record_location 
drop column record_identifier
/

delete from record_labels
/

alter table record_labels 
drop column record_identifier
/

alter table record_labels 
drop column user_label_identifier 
/

alter table record_labels 
add record_id number not null add user_label_id number not null
/



INSERT INTO CHANGELOG_Expenser (change_number, complete_dt, applied_by, description)
 VALUES (10000003, CURRENT_TIMESTAMP, USER, '10000003_DDL_changeForiegnKey.sql');

COMMIT;

-- END CHANGE SCRIPT #10000003: 10000003_DDL_changeForiegnKey.sql

