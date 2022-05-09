package com.expenser.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expenser.Entity.LinkToken;
import com.expenser.enums.LinkTokenType;

@Service
public interface LinkTokenService {
	void createUserLinkToken(String tokenIdentifier, String userIdentifier, LinkTokenType tokenType);

	LinkToken findByTokenIdentifierAndTokenType(String tokenIdentifier, LinkTokenType confirmRegistration);

	boolean isTokenActive(LinkToken token);

	void disablePreviousTokensForUser(String userIdentifier);

	List<LinkToken> findByUserIdentifier(String userIdentifier);

	String getUserIdentifierByTokenIdentifier(String tokenIdentifier);

	LinkToken findByTokenIdentifier(String tokenIdentifier);

	void disablePreviousTokensForUserAndType(String userIdentifier, LinkTokenType forgetPassword);

}
