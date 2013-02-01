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
    	TablesHall tablesHall = tables.get(0);
  
    	//Graphic Text 
    	  File imageSrc = null;
    	  String tablesImageURL = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/table.png");
        imageSrc = new File(tablesImageURL);
        BufferedImage bi = ImageIO.read(imageSrc);
    	BufferedImage bufferedImg = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);  
         Graphics2D g2 = bufferedImg.createGraphics();  
         g2.drawImage(bi, null, tablesHall.getLeft(), tablesHall.getTop());  
         ByteArrayOutputStream os = new ByteArrayOutputStream();  
         ImageIO.write(bufferedImg, "png", os);  
         graphicText = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");   

        return graphicText;  
    }  
          
    public StreamedContent getChart() {  
        return chart;  
    }  
      
   
}  