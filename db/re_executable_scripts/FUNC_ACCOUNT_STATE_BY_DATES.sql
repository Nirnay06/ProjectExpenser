create or replace FUNCTION ACCOUNT_STATE_BY_DATES(IN_ACCOUNT_IDENTIFIER IN varchar2, IN_START_DATE IN DATE,IN_END_DATE IN DATE )
    RETURN SYS_REFCURSOR
  AS
    my_cursor SYS_REFCURSOR;
  BEGIN
    OPEN my_cursor FOR 
	select IN_START_DATE as start_date, IN_END_DATE as end_date, 
    
        nvl((select sum(amount) from user_records ur2 where trunc(ur2.record_date) <=IN_END_DATE and ur2.account_identifier = IN_ACCOUNT_IDENTIFIER and ur2.deleted=0),0) + nvl(INITIAL_BALANCE,0) as balance,
		
		nvl((select sum(abs(amount)) from user_records ur2 where trunc(ur2.record_date) >=IN_START_DATE and trunc(ur2.record_date) <=IN_END_DATE and ur2.account_identifier = IN_ACCOUNT_IDENTIFIER and ur2.deleted=0
		and ur2.amount < 0 and ur2.amount is not null),0) as debit, 
		
		nvl((select sum(amount) from user_records ur2 where trunc(ur2.record_date) >=IN_START_DATE and trunc(ur2.record_date) <=IN_END_DATE and ur2.account_identifier = IN_ACCOUNT_IDENTIFIER and ur2.deleted=0
		and ur2.amount >= 0 and ur2.amount is not null),0) as credit 
		
		from user_accounts where ACCOUNT_IDENTIFIER = IN_ACCOUNT_IDENTIFIER;
    RETURN my_cursor;    
  END;