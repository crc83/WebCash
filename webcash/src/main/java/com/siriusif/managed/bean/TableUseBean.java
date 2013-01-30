package com.siriusif.managed.bean;

import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.File;  

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;  
import javax.inject.Inject;
  
import org.primefaces.model.DefaultStreamedContent;  
import org.primefaces.model.StreamedContent;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.siriusif.model.TablesHall;
import com.siriusif.service.model.TablesDao;

// http://www.primefaces.org/showcase/ui/dynamicImage.jsf
// see here
@ManagedBean(name = "tableUseBean")
public class TableUseBean {
  
    private StreamedContent graphicText;  
      
    private StreamedContent barcode;  
      
    private StreamedContent chart;
    
    private TablesDao tablesDao;
    
    public TableUseBean() {  
        try {  
        	TablesHall tablesHall = tablesDao.list().get(0);
      
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
//            File imageSrc = null;
//            imageSrc = new File("/images/tables.png");
//        	BufferedImage img = ImageIO.read(imageSrc);
//        	int w = img.getWidth(null);
//        	int h = img.getHeight(null);
//        	BufferedImage bi = new
//        	    BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
//        	Graphics g = bi.getGraphics();
//        	g.drawImage(img, 50, 50, null);
//        	ByteArrayOutputStream os = new ByteArrayOutputStream();  
//            ImageIO.write(bi, "png", os);  
//        	
//            graphicText = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");   
//        	String path = "/images/table.png";
//        	BufferedImage bufferedImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);  
//            Graphics2D g2 = bufferedImg.createGraphics();
//            float[] scales = { 1f, 1f, 1f, 0.5f };
//            float[] offsets = new float[4];
//            RescaleOp rop = new RescaleOp(scales, offsets, null);
//
//            g2.drawImage(bufferedImg, rop, 10, 0);  
//            ByteArrayOutputStream os = new ByteArrayOutputStream();  
//            ImageIO.write(bufferedImg, path, os);  
//            graphicText = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");   
        	
//            BufferedImage bufferedImg = new BufferedImag(10, 10, BufferedImage.TYPE_INT_RGB);  
//            Graphics2D g2 = bufferedImg.createGraphics();  
//            g2.drawImage(, 10, 10);  
//            ByteArrayOutputStream os = new ByteArrayOutputStream();  
//            ImageIO.write(bufferedImg, "png", os);  
//            graphicText = new DefaultStreamedContent(getClass().getClassLoader().getResourceAsStream("/images/tables.png"), "image/png");   
//            getClass().getClassLoader().getResourceAsStream(
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    public StreamedContent getBarcode() {  
        return barcode;  
    }  
  
    public StreamedContent getGraphicText() {  
        return graphicText;  
    }  
          
    public StreamedContent getChart() {  
        return chart;  
    }  
      
   
}  