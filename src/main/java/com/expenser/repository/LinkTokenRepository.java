package com.expenser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expenser.Entity.LinkToken;
import com.expenser.enums.LinkTokenType;

public interface LinkTokenRepository extends JpaRepository<LinkToken, Long> {

	List<LinkToken> findByTokenIdentifierAndTokenType(String tokenIdentifier, LinkTokenType tokenType);

	List<LinkToken> findByUserIdentifier(String userIdentifier);

	@Query("select lt.userIdentifier from LinkToken lt where lt.tokenIdentifier=:tokenIdentifier")
	String findUserIdByToken(String tokenIdentifier);

	LinkToken findByTokenIdentifier(String tokenIdentifier);
	
	List<LinkToken> findByUserIdentifierAndTokenType(String userIdentifier, LinkTokenType tokenType);

}
