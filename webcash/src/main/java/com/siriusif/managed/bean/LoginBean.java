package com.siriusif.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "loginBean")
public class LoginBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);

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
		if (username != null && username.equals("admin") && password != null
				&& password.equals("admin")) {
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
