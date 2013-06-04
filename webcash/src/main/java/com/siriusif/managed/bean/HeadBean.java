package com.siriusif.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "headBean")
public class HeadBean {
	
	private Logger LOGGER = Logger.getLogger(HeadBean.class);
	
	public void openManageWorkshifts(ActionEvent evt) {
		LOGGER.info("Clicked");		
	}

}
