
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

--//@UNDO