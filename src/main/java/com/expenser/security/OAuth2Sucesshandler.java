package com.expenser.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


public class OAuth2Sucesshandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        // Access user information from the OAuth2AuthenticationToken
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        Map<String, Object> attributes = token.getPrincipal().getAttributes();
//        String email = (String) attributes.get("email");
//        String name = (String) attributes.get("name");
//
//        // Redirect to the desired page
//        response.sendRedirect("/home");
//    }
}

