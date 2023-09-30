package com.expenser.repository;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.UserRecord;
import com.expenser.mapper.RecordMapper;
import com.expenser.model.AccountStatsDTO;
import com.expenser.model.RecordDTO;
import com.expenser.model.SearchRequest;

@Service
public class RecordRepositoryCustomImpl implements RecordRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	RecordMapper recordMapper;
	
	public AccountStatsDTO findStatsWithinInterval(String accountIdentifier, LocalDate startDate, LocalDate endDate) {
		return callFunction(accountIdentifier, startDate,endDate , "{? = call ACCOUNT_STATE_BY_DATES(?,?,?)}");
	}
	
	private AccountStatsDTO callFunction(String ein, LocalDate startDate, LocalDate endDate, String functionToCall) {
		
		try {
			Connection cc = ((SessionImpl) entityManager.getDelegate()).connection();
			CallableStatement cs = cc.prepareCall(functionToCall);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setObject(2, ein, OracleTypes.VARCHAR);
			cs.setObject(3, startDate, OracleTypes.DATE);
			cs.setObject(4, endDate, OracleTypes.DATE);
			cs.execute();
			try (ResultSet rs = (ResultSet) cs.getObject(1)) {
				if (rs != null) {
					while (rs.next()) {
						return new AccountStatsDTO(startDate, endDate, rs.getLong(3),  rs.getLong(5),  rs.getLong(4));		
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		return null;
	}

	@Override
	public List<RecordDTO> findRecordsFromAccountsByDate(List<String> accountIdentifiers, String startDate,
			String endDate, SearchRequest searchRequest) {
		StringBuffer sql = new StringBuffer("");
		 sql.append("SELECT * FROM user_records WHERE account_Identifier IN (:accountIdentifiers) " +
	               "AND trunc(record_date) BETWEEN to_date(:startDate, 'DD-MM-YYYY') AND to_date(:endDate, 'DD-MM-YYYY')");
	   
		 sql.append(searchRequest.getOrdeByClause());
		 sql.append(searchRequest.getLimitQuery());
        List<UserRecord> records =entityManager
                .createNativeQuery(sql.toString(), UserRecord.class)
                .setParameter("accountIdentifiers", accountIdentifiers)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
        
        return recordMapper.toDtoList(records);
	}

	@Override
	public Map<String, Object> findCashFlowForInterval(List<String> accountIdentifiers, LocalDate sDate,
			LocalDate eDate) {
		StringBuffer sql = new StringBuffer("");
        sql.append("SELECT record_type, SUM(ur.amount * uc.USER_CURRENCY_RATE) FROM user_records ur "
        		+ "join user_currency uc on uc.IDENTIFIER = ur.USER_CURRENCY_IDENTIFIER WHERE ur.account_Identifier IN (:accountIdentifiers) " +
                   "AND trunc(ur.record_date) BETWEEN :startDate AND :endDate " +
                   "AND ur.record_type IN ('Expense', 'Income') " +
                   "GROUP BY ur.record_type");

        List<Object[]> resultList = entityManager
                .createNativeQuery(sql.toString())
                .setParameter("accountIdentifiers", accountIdentifiers)
                .setParameter("startDate", sDate)
                .setParameter("endDate", eDate)
                .getResultList();

        Map<String, Object> cashFlowMap = new HashMap<>();
        for (Object[] result : resultList) {
            String recordType = (String) result[0];
            BigDecimal sumAmount = new BigDecimal(result[1].toString());
            cashFlowMap.put(recordType, sumAmount);
        }

        return cashFlowMap;
	}
	
	public AccountStatsDTO findStatsWithinIntervalForAccountList(List<String> accountIdentifiers, LocalDate startDate, LocalDate endDate) {
		long balance = 0, credit =0 , debit =0;
		for(String accId : accountIdentifiers) {			
			AccountStatsDTO stat = callFunction(accId, startDate,endDate , "{? = call ACCOUNT_STATE_BY_DATES(?,?,?)}");
			balance += stat.getBalanceAsOfDate();
			credit += stat.getTotalCreditInInterval();
			debit += stat.getTotalDebitInInterval();
		}
		AccountStatsDTO result = new AccountStatsDTO(startDate, endDate, balance, credit, debit);
		return result;
	}
}
