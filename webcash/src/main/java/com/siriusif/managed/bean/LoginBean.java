package com.siriusif.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "loginBean")
public class LoginBean{
	private String username;  
    private String password;
    
    public String getUsername() {  
        return username;  
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
  
    //private String url;
    
    public String login(ActionEvent actionEvent) {
    	String responce = "error";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
       
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
                loggedIn = true;
//                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
                responce = "success";
        } else {
                loggedIn = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
        }
        
        FacesContext.getCurrentInstance().addMessage("success", msg);
        context.addCallbackParam("loggedIn", loggedIn);
		return responce;
}

}  


