package com.expenser.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserAccount;
import com.expenser.enums.AccountType;
import com.expenser.model.AccountDTO;

@Repository
public interface AccountRepository extends JpaRepository<UserAccount,Long>{

	public UserAccount findByIdentifier(String accountIdentifier);

	@Query("select new com.expenser.model.AccountDTO(ac.client.clientIdentifier, ac.identifier, ac.active, ac.accountType, "
			+ "			ac.accountName, ac.accountColor, ac.accountBalance, ac.icon, ac.initialBalance, "
			+ "			ac.excludeFromStats, ac.archived, ac.accountCurrency.identifier) from UserAccount ac "
			+ " 		where ac.client.clientIdentifier=:clientIdentifier and ac.active=:active and ac.archived=false ")
	public List<AccountDTO> findAllAccountByClientIdentifierAndStatus(String clientIdentifier, boolean active);

	@Query("select new com.expenser.model.AccountDTO(ac.client.clientIdentifier, ac.identifier, ac.active, ac.accountType, "
			+ "			ac.accountName, ac.accountColor, ac.accountBalance, ac.icon, ac.initialBalance, "
			+ "			ac.excludeFromStats, ac.archived, ac.accountCurrency.identifier) from UserAccount ac "
			+ " 		where ac.client.clientIdentifier=:clientIdentifier and ac.archived=false ")
	public List<AccountDTO> findAllAccountByClientIdentifier(String clientIdentifier);

	@Query("select new com.expenser.model.AccountDTO(ac.client.clientIdentifier, ac.identifier, ac.active, ac.accountType, "
			+ "			ac.accountName, ac.accountColor, ac.accountBalance, ac.icon, ac.initialBalance, "
			+ "			ac.excludeFromStats, ac.archived, ac.accountCurrency.identifier) from UserAccount ac "
			+ " 		where ac.client.clientIdentifier=:clientIdentifier and ac.identifier=:accountIdentifier ")
	public AccountDTO findAccountByClientAndAccountIdentifier(String clientIdentifier, String accountIdentifier);

	@Query("select ac from UserAccount ac where ac.client.clientIdentifier=:clientIdentifier and ac.accountName=:accountName ")
	public List<UserAccount> findAllAccountByClientIdentifierAndName(String clientIdentifier, String accountName);
	
}
