package com.siriusif.managed.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import com.siriusif.service.model.GoodDao;
import com.siriusif.service.model.GroupDao;

import static com.siriusif.model.helpers.SaleBuiledr.*;

@ManagedBean(name="orderBean")
public class OrderBean {
	
	private static Logger LOGGER = Logger.getLogger(OrderBean.class);
	
	private Order order;
	

	@ManagedProperty(value="#{groupDao}")
	private GroupDao groupDao;
	@ManagedProperty(value="#{goodDao}")
	private GoodDao goodDao;
	
	private List<Group> groups;
	private List<Good> goods;
	

	public List<Group> getGroups() {
		groups = groupDao.list();
		for(Group group : groups){
			LOGGER.debug(" | "+group.getgName());
		}
		LOGGER.debug(" || "+groups.size());
		return groups;
	}
	
	public List<Good> getGoods() {
		goods = goodDao.list();
		return goods;
	}
	
	public OrderBean(){
		//TODO SB : Remove this when we will have DB connection
		// begin
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Object maybeOrder = session.getAttribute("order");
		// end
		if (maybeOrder == null) {
			orderView();
		} else {
			order =(Order)maybeOrder;
		}
	}

	private void orderView() {
		order = new Order();
		
		Suborder first = new Suborder(1);
		
		first.addSale(buildSaleOld("Юшка грибна", 0.280, 10.50));
		first.addSale(buildSaleOld("Салат домашній", 0.280, 11.00));
		first.addSale(buildSaleOld("М'ясо по французьки", 0.200, 20.00));
		first.addSale(buildSaleOld("Картопля молода з зеленню", 0.200, 8.00));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSaleOld("Картопля молода з зеленню", 0.100, 8.00));
		second.addSale(buildSaleOld("Сметана", 1, 4.00));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSaleOld("Фреш", 0.200, 16.00));
		third.addSale(buildSaleOld("Кава Еспрессо", 0.040, 9.00));
		third.addSale(buildSaleOld("Штрудель", 0.150, 14.00));
		order.addSuborder(third);
	}
	

	public void onClick(ActionEvent evt){
		 Good good = (Good)evt.getComponent().getAttributes().get("selectedGood");
		 Sale sale = new Sale();
		 sale.setSalesGood(good);
		 sale.setAmount(new BigDecimal(0.505).setScale(3, RoundingMode.HALF_UP));
		 order.getSuborders().get(0).addSale(sale);
		//TODO SB : Remove this when we will have DB connection
		// begin
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("order",order);
		//end
		
//		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome "  + "!"));  
		
	  }

	public Order getOrder() {
		return order;
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public GoodDao getGoodDao() {
		return goodDao;
	}

	public void setGoodDao(GoodDao goodDao) {
		this.goodDao = goodDao;
	}
}
