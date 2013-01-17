package com.siriusif.security;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.siriusif.managed.bean.LoginBean;

@Service("authenticationService")
public class AuthentificationServiceImpl implements AuthenticationService{

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);
	
	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager; // specific for Spring Security

	@Override
	public boolean login(String username, String password) {
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(
						authenticate);   
				return true;
			}
		} catch (AuthenticationException e) {
			LOGGER.info("Error during authentification [see details at debug level]");
			LOGGER.debug(e.getMessage(), e);
		}
		return false;
	}
}
