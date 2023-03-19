package com.expenser.security;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.expenser.Entity.Client;
import com.expenser.Entity.User;
import com.expenser.api.ClientService;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.UserDTO;
import com.expenser.util.SecurityUtils;

@Component
@Order(2)
public class ClientFilter extends OncePerRequestFilter{

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			UserDTO user = SecurityUtils.getLoggedUserFromSession();
			ClientDTO presentClient = SecurityUtils.getClientFromSession();
			if(user!=null && presentClient==null) {
				Client client = clientService.findByUserIdentifier(user.getUserIdentifier());
				if(client!=null) {
					ClientDTO clientDTO = mapper.map(client, ClientDTO.class);
					SecurityUtils.setClientDetails(clientDTO);
				}
			}
		} catch (AccessDeniedException | BusinessException e) {
			e.printStackTrace();
		}
		filterChain.doFilter(request, response);
	}


	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().contains("/auth");
	}
}
