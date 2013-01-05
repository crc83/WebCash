package com.siriusif.managed.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.siriusif.model.Good;
import com.siriusif.model.Group;

@ManagedBean(name="menuBean")
public class MenuBean {
	private String name;
	private String price;
	private List<Good> goods;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
	public MenuBean(){
		menuView();
	}

	private void menuView() {
		goods = new ArrayList<Good>();
		
		
		goods.add(new Good("Сирний 200г.", 7.50));
		goods.add(new Good("Домашній 280г.", 7.50));
		goods.add(new Good("З капусти 200г.", 7.50));
		goods.add(new Good("Кореєць 200г.", 7.50));
		goods.add(new Good("Цезар 240г.", 7.50));
		goods.add(new Good("Олів'є 250г.", 7.50));
		goods.add(new Good("М'ясний Теріякі 150г.", 7.50));
		goods.add(new Good("Грецький 220г.", 7.50));
		goods.add(new Good("Рибний 200г.", 7.50));
		
		
		
		goods.add(new Good("Сирний 200г.", 7.50));
		goods.add(new Good("Домашній 280г.", 7.50));
		goods.add(new Good("З капусти 200г.", 7.50));
		goods.add(new Good("Кореєць 200г.", 7.50));
		goods.add(new Good("Цезар 240г.", 7.50));
		goods.add(new Good("Олів'є 250г.", 7.50));
		goods.add(new Good("М'ясний Теріякі 150г.", 7.50));
		goods.add(new Good("Грецький 220г.", 7.50));
		goods.add(new Good("Рибний 200г.", 7.50));
		
	}
	
	 public void onClick(ActionEvent evt){
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + name + " " + price + "!"));  
		
	  }

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
}
