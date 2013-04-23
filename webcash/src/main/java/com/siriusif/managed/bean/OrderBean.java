package com.siriusif.managed.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.siriusif.service.model.GroupDao;

import static com.siriusif.model.helpers.TestHelper.*;

@ManagedBean(name="orderBean")
public class OrderBean {
	
	private static Logger LOGGER = Logger.getLogger(OrderBean.class);
	
	private Order order;
	

	@ManagedProperty(value="#{groupDao}")
	private GroupDao groupDao;
	
	private List<Group> groups;
	

	public List<Group> getGroups() {
		groups = groupDao.list();
		for(Group group : groups){
			LOGGER.debug(" | "+group.getgName());
			LOGGER.debug(" | "+group.getGoods().size());
		}
		LOGGER.debug(" || "+groups.size());
		return groups;
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

		first.addSale(buildSale("Юшка грибна", amount(0.280), money(12.50)));
		first.addSale(buildSale("Салат домашній", amount(0.280), money(12.00)));
		first.addSale(buildSale("М'ясо по французьки", amount(0.200), money(20.00)));
		first.addSale(buildSale("Картопля молода з зеленню", amount(0.200), money(8.00)));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSale("Смалець", amount(0.100), money(8.00)));
		second.addSale(buildSale("Сметана", amount(1), money(4.00)));
		second.addSale(buildSale("Фреш", amount(0.200), money(16.00)));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSale("Хліб", amount(10), money(0.50)));
		third.addSale(buildSale("Кава Еспрессо", amount(0.040), money(9.00)));
		third.addSale(buildSale("Штрудель", amount(0.150), money(14.00)));
		order.addSuborder(third);
	}
	

	public void onClick(ActionEvent evt){
		 Good good = (Good)evt.getComponent().getAttributes().get("selectedGood");
		 Sale sale = new Sale();
		 sale.setSalesGood(good);
		 sale.setAmount(new BigDecimal(1).setScale(3, RoundingMode.HALF_UP));
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

}
