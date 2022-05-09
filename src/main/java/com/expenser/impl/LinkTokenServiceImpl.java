package com.expenser.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.LinkToken;
import com.expenser.api.LinkTokenService;
import com.expenser.enums.LinkTokenType;
import com.expenser.repository.LinkTokenRepository;
import com.expenser.util.DateUtil;

@Service
public class LinkTokenServiceImpl implements LinkTokenService{
	
	@Autowired
	LinkTokenRepository linkTokenRepository;
	
	
	@Override
	public void createUserLinkToken(String tokenIdentifier, String userIdentifier, LinkTokenType tokenType) {
		LinkToken token = new LinkToken();
		token.setTokenIdentifier(tokenIdentifier);
		token.setUserIdentifier(userIdentifier);
		token.setGenerationTime(new Date());
		token.setExpirationTime(new Date(new Date().getTime() + 24*60*60*1000));
		token.setTokenType(tokenType);
		linkTokenRepository.save(token);
	}


	@Override
	public LinkToken findByTokenIdentifierAndTokenType(String tokenIdentifier, LinkTokenType tokenType) {
		List<LinkToken> tokens = linkTokenRepository.findByTokenIdentifierAndTokenType(tokenIdentifier,tokenType);
		if(tokens!=null && tokens.size()>0) {
			return tokens.get(0);
		}
		return null;
	}


	@Override
	public boolean isTokenActive(LinkToken token) {
		if(token.isActive()) {
			if(token.getExpirationTime()!=null && DateUtil.isFutureDate(token.getExpirationTime())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void disablePreviousTokensForUser(String userIdentifier) {
		if(userIdentifier!=null) {
			List<LinkToken> tokens = findByUserIdentifier(userIdentifier);
			if(tokens!=null && tokens.size()>0) {
				tokens.forEach(t-> t.setActive(false));
			}
		}
	}

	@Override
	public void disablePreviousTokensForUserAndType(String userIdentifier, LinkTokenType tokenType) {
		if(userIdentifier!=null && tokenType!=null ){
			List<LinkToken> tokens = linkTokenRepository.findByUserIdentifierAndTokenType(userIdentifier, tokenType);
			if(tokens!=null && tokens.size()>0) {
				tokens.forEach(t-> t.setActive(false));
			}
		}
		
	}
	@Override
	public List<LinkToken> findByUserIdentifier(String userIdentifier) {
		return linkTokenRepository.findByUserIdentifier(userIdentifier);
	}


	@Override
	public String getUserIdentifierByTokenIdentifier(String tokenIdentifier) {
		return linkTokenRepository.findUserIdByToken(tokenIdentifier);
	}


	@Override
	public LinkToken findByTokenIdentifier(String tokenIdentifier) {
		return linkTokenRepository.findByTokenIdentifier(tokenIdentifier);
	}
}

