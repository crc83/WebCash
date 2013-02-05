package com.siriusif.managed.bean;

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

import com.siriusif.model.TablesHall;
import com.siriusif.service.model.TablesDao;

// http://www.primefaces.org/showcase/ui/dynamicImage.jsf
// see here
@ManagedBean(name = "tableUseBean")
public class TableUseBean {
	
	//TODO SB : Change logger initialization in spring way
	private Logger LOGGER = Logger.getLogger(TableUseBean.class);
  
    private StreamedContent graphicText;  
      
    private StreamedContent barcode;  
      
    private StreamedContent chart;
    
    private String responce;
    

	@ManagedProperty(value="#{tablesDao}")
    private TablesDao tablesDao;
    
    /**
	 * @return the tablesDao
	 */
	public TablesDao getTablesDao() {
		return tablesDao;
	}

	/**
	 * @param tablesDao the tablesDao to set
	 */
	public void setTablesDao(TablesDao tablesDao) {
		LOGGER.debug(" >> TablesDao is set");
		this.tablesDao = tablesDao;
	}

	public TableUseBean() {
		super();
		//NOTE : Dao is not accessible here
    }  
      
    public StreamedContent getBarcode() {  
        return barcode;  
    }  
  
    public StreamedContent getGraphicText() throws IOException {  
    	LOGGER.debug(" >> TablesDao is null? "+(tablesDao==null));
    	List<TablesHall> tables = tablesDao.list();
  
    	//Graphic Text 
    	  BufferedImage bufferedImg = readImage("background");  
    	  Graphics2D g2 = bufferedImg.createGraphics(); 
    	  for (TablesHall table : tables){
    		  g2.drawImage(readImage("tables"), null, table.getLeft(), table.getTop());
    		  g2.drawString(table.getName(), table.getLeft(), table.getTop());
    	  }	
    	  
    	  ByteArrayOutputStream os = new ByteArrayOutputStream();  
    	  ImageIO.write(bufferedImg, "png", os);  
    	  graphicText = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");   

    	  return graphicText;  
    }

	private BufferedImage readImage(String name) {
		BufferedImage bi = null;
		try {
			String tablesImageURL = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/"+name+".png");
	    	File imageSrc = new File(tablesImageURL);
	    	bi = ImageIO.read(imageSrc);
		} catch (IOException ioe) {
			LOGGER.error("Error during loading image : "+ name);
			LOGGER.debug("",ioe);
		}
		return bi;
	}  
	
	
          
    public StreamedContent getChart() {  
        return chart;  
    }  
      
    public String getResponce() {
    	responce = "./order.jsf";
    	return responce;
    }
   
}  