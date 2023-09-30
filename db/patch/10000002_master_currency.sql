alter table master_currency 
add currency_title varchar2(2000) not null;

insert into master_currency(id,identifier, currency_symbol, current_conversion_rate, currency_title, deleted) values (MASTER_CURRENCY_SEQ.nextval, (select getsysuuid from dual), 'INR', '1','Indian Rupee', 0 )
/

--//@UNDO

