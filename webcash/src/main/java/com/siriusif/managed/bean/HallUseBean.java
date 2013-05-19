package com.siriusif.managed.bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;  
  
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;  
import org.primefaces.model.StreamedContent;  

import com.siriusif.model.DinnerTable;
import com.siriusif.service.model.DinnerTableDao;

// http://www.primefaces.org/showcase/ui/dynamicImage.jsf
// see here
@ManagedBean(name = "hallUseBean")
public class HallUseBean {
	
	//TODO SB : Change logger initialization in spring way
	private Logger LOGGER = Logger.getLogger(HallUseBean.class);
  
	@ManagedProperty(value="#{tablesDao}")
    private DinnerTableDao tablesDao;
    
	private List<DinnerTable> tables;

	public List<DinnerTable> getTables() {
		tables = tablesDao.list();
		LOGGER.debug("getting tables list");
		LOGGER.debug("tables size :"+tables.size());
		for (DinnerTable table : tables){
			LOGGER.debug("	| "+table);
		}
		return tables;
	}

	/**
	 * @return the tablesDao
	 */
	public DinnerTableDao getTablesDao() {
		return tablesDao;
	}

	/**
	 * @param tablesDao the tablesDao to set
	 */
	public void setTablesDao(DinnerTableDao tablesDao) {
		this.tablesDao = tablesDao;
	}          
}  