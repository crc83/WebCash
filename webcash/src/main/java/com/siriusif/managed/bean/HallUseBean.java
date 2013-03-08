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

import com.siriusif.model.TablesHall;
import com.siriusif.service.model.TablesDao;

// http://www.primefaces.org/showcase/ui/dynamicImage.jsf
// see here
@ManagedBean(name = "hallUseBean")
public class HallUseBean {
	
	//TODO SB : Change logger initialization in spring way
	private Logger LOGGER = Logger.getLogger(HallUseBean.class);
  
    private StreamedContent graphicText;  
    
    /**
     * response of click
     */
    private String responce;
    
      

	@ManagedProperty(value="#{tablesDao}")
    private TablesDao tablesDao;
    
	private List<TablesHall> tables;

	public List<TablesHall> getTables() {
		tables = tablesDao.list();
		return tables;
	}

  
    public StreamedContent getGraphicText() throws IOException {  
    	LOGGER.debug(" >> TablesDao is null? "+(tablesDao==null));
    	List<TablesHall> tables = tablesDao.list();
  
    	//Graphic Text 
    	  BufferedImage bufferedImg = readImage("background");  
    	  Graphics2D g2 = bufferedImg.createGraphics(); 
    	  for (TablesHall table : tables){
    		  // TODO 121 change to height of table + 3
    		  int textBottom = table.getTop() + 121;
    		  int textLeft = table.getLeft() + 20;
    		  g2.drawImage(readImage("tables"), null, table.getLeft(), table.getTop());
    		  Font font = new Font("Arial", Font.ITALIC, 18);
    		  g2.setFont(font);
    		  // color of name table
    		  g2.setColor(Color.green);
    		  // location of tables name. We can change to top(textBottom to getTop())
    		  g2.drawString(table.getName(), textLeft, textBottom);
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
    
    public String getResponce() {
    	responce = "./order.jsf";
    	return responce;
    }


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
		this.tablesDao = tablesDao;
	}
    
      
}  