package com.expenser.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.expenser.repository.UserRepository;
import com.expenser.security.SecurityConstants;

@Controller
public class InitController {

	
	@Autowired
	UserRepository userRepository;
	

	@GetMapping("")
	public ModelAndView getHome(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=false) Map<String,String> qparams) {
		if(qparams!=null && qparams.containsKey("Authorization")) {
			response.addHeader(SecurityConstants.JWT_HEADER,  qparams.get("Authorization"));
			response.addCookie(new Cookie(SecurityConstants.JWT_HEADER,  qparams.get("Authorization")));
		}
		return new ModelAndView("index");
	}
}
