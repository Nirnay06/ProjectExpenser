package com.expenser.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserRecord;

@Repository
public interface RecordRepository extends JpaRepository<UserRecord, Long>{
	
	@Query("select ur from UserRecord ur where ur.recordIdentifier=:identifier")
	public UserRecord findByIdentifier(String identifier);
	
	@Query("select ur from UserRecord ur where ur.client.clientIdentifier =:clientIdentifier")
	public List<UserRecord> findByClient(@Param("clientIdentifier") String clientIdentifier);
	
	@Query( "select ur from UserRecord ur where ur.account.identifier=:accountIdentifier order by ur.date desc")
	public List<UserRecord> findByAccountIdentifier(@Param("accountIdentifier") String accountIdentifier);

	@Query("select ur from UserRecord ur where ur.account.identifier=:accountIdentifier "
			+ " and ur.client.clientIdentifier=:clientIdentifier")
	public List<UserRecord> findByAccountIdentifierAndClientIdentifier(@Param("accountIdentifier") String accountIdentifier,
			@Param("clientIdentifier")  String clientIdentifier);

	@Query("SELECT CONCAT(cur.title, ' ', COALESCE(SUM(ur.amount*uc.currencyRate/cur.currencyRate)+ac.initialBalance, 0)) " +
			"FROM UserAccount ac " +
            "left JOIN UserRecord ur on ur.account.identifier=ac.identifier " +
            "left join ur.currency uc " +
            "JOIN ac.accountCurrency cur " +
            "WHERE ac.identifier = :accountIdentifier " +
            "GROUP BY cur.title, ac.initialBalance")
	public String findAccountBalanceStringByAccountIdentifier(String accountIdentifier);
	
	@Query("SELECT COALESCE(SUM(ur.amount*uc.currencyRate/cur.currencyRate), 0) +ac.initialBalance " +
            "FROM UserAccount ac " +
            "left JOIN UserRecord ur on ur.account.identifier=ac.identifier "
            + "left join ur.currency uc " +
            "JOIN ac.accountCurrency cur " +
            "WHERE ac.identifier = :accountIdentifier " +
            "GROUP BY cur.title, ac.initialBalance")
	public Long findAccountBalanceByAccountIdentifier(String accountIdentifier);

	@Query("SELECT uc.identifier, SUM(ur.amount) as totalAmount, SUM(ur.amount * uc.currencyRate) " +
		       "FROM UserRecord ur " +
		       "JOIN ur.currency uc " +
		       "WHERE ur.account.identifier IN (:accountIdentifiers) " +
		       "AND trunc(ur.date) BETWEEN :sDate AND :eDate " +
		       "GROUP BY uc.identifier")
	public List<String[]> findCurrencyBalanceForInterval(List<String> accountIdentifiers, Date sDate,
			Date eDate);

	@Query("SELECT uc.title, SUM(abs(ur.amount) * curr.currencyRate) " +
		       "FROM UserRecord ur " +
		       "JOIN ur.category uc " +
		       "JOIN ur.currency curr " +
		       "WHERE ur.account.identifier IN (:accountIdentifiers) " +
		       "AND trunc(ur.date) BETWEEN :sDate AND :eDate "
		       + "and ur.recordType='Expense' " +
		       "GROUP BY uc.title")
	public List<String[]> findSpendByCategoryForInterval(List<String>  accountIdentifiers, Date sDate,
			Date eDate);
}
