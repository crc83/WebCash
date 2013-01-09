package com.siriusif.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name="onclickBean")
public class OnclickBean {

    private String firstname;
   
    private String surname;
    
    public void savePerson(ActionEvent actionEvent) {
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You've registered"));

    }
    public void resetFail() {
        this.firstname = null;
        this.surname = null;
       
        FacesMessage msg = new FacesMessage("Model reset, but it won't work.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


}
