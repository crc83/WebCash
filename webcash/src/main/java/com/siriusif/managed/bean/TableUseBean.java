package com.siriusif.managed.bean;

import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  

import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;  
  
import org.primefaces.model.DefaultStreamedContent;  
import org.primefaces.model.StreamedContent;  

// http://www.primefaces.org/showcase/ui/dynamicImage.jsf
// see here

@ManagedBean(name = "tableUseBean")
public class TableUseBean {  
  
    private StreamedContent graphicText;  
      
    private StreamedContent barcode;  
      
    private StreamedContent chart;  
  
    public TableUseBean() {  
        try {  
            //Graphic Text  
            BufferedImage bufferedImg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = bufferedImg.createGraphics();  
            g2.drawString("This is a text", 100, 100);  
            ByteArrayOutputStream os = new ByteArrayOutputStream();  
            ImageIO.write(bufferedImg, "png", os);  
            graphicText = new DefaultStreamedContent(getClass().getClassLoader().getResourceAsStream("/images/tables.png"), "image/png");   
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