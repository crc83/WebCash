package com.siriusif.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siriusif.security.AuthenticationService;

@ManagedBean(name = "loginBean")
public class LoginBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);

	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService; // injected Spring defined service for bikes
	
	/**
	 * @return the authenticationService
	 */
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService the authenticationService to set
	 */
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Inject
	private FacesMessage message;

	/** login from page */
	private String username;

	/** password from page */
	private String password;

	public String getUsername() {
		return username;
	}

	public FacesMessage getMessage() {
		return message;
	}

	public void setMessage(FacesMessage message) {
		this.message = message;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Action to check credentials and register user as active user
	 * 
	 * @return url to redirect after ok/wrong login
	 */
	public String login() {
		String responce = "/pages/login";
		LOGGER.debug("checking login and password");
		LOGGER.debug("Username:"+username);
		LOGGER.debug("Password:"+password);
		boolean success = authenticationService.login(username, password);
		if (success) {
			//TODO : Check how to test if we have messages
			// message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome",
			// username);
			LOGGER.debug("correct user");
			responce = "/pages/hall?faces-redirect=true";
		} else {
			LOGGER.debug("wrong user");
			// message = new FacesMessage(FacesMessage.SEVERITY_WARN,
			// "Login Error", "Invalid credentials");
		}

		return responce;
	}

}
