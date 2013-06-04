package com.siriusif.jsf.utils;

import java.io.IOException;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
 
/**
 * Provides some useful routines for working with JSF context and session.
 * Usage
 * 1) inport static JSFHelper.jsf()
 * 2) jsf().redirectTo(url);
 * @idea Anton Danshin
 * @author sbelei 
 */
public class JSFHelper {
 
	private Logger LOGGER = Logger.getLogger(JSFHelper.class);
	
    private FacesContext context;
 
    public static JSFHelper jsf(){
    	return new JSFHelper();
    }
    
    /**
     * Creates new instance with current Faces context.
     */
    public JSFHelper() {
        this(FacesContext.getCurrentInstance());
    }
 
    public JSFHelper(FacesContext context) {
        this.context = context;
    }
 
    public FacesContext getFacesContext() {
        return context;
    }
 
    /**
     * Redirects to desired url
     * @param url
     * @return true if success or false in case of error
     */
	public boolean redirectTo(String url) {
		try {
			if (StringUtils.isNotBlank(url)) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(url);
			}
			return true;
		} catch (IOException e) {
			LOGGER.error("Can't redirect to :"+url);
			LOGGER.debug("Error caused by", e);
		}
		return false;

	}
	
	/**
	 * Retrives HTTP request object from context
	 * @return request
	 */
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
	}

}