package com.siriusif.managed.bean;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import static com.siriusif.model.helpers.SaleBuiledr.*;

@ManagedBean(name="orderBean")
public class OrderBean {
	private Order order;
	private List<Group> groups;
	


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
		menuView();
	}

	private void orderView() {
		order = new Order();
		
		Suborder first = new Suborder(1);
		
		first.addSale(buildSale("Юшка грибна", 0.280, 10.50));
		first.addSale(buildSale("Салат домашній", 0.280, 11.00));
		first.addSale(buildSale("М'ясо по французьки", 0.200, 20.00));
		first.addSale(buildSale("Картопля молода з зеленню", 0.200, 8.00));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSale("Картопля молода з зеленню", 0.100, 8.00));
		second.addSale(buildSale("Сметана", 1, 4.00));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSale("Фреш", 0.200, 16.00));
		third.addSale(buildSale("Кава Еспрессо", 0.040, 9.00));
		third.addSale(buildSale("Штрудель", 0.150, 14.00));
		order.addSuborder(third);
	}
	
	private void menuView() {
		groups = new LinkedList<Group>();
		
		Group first = new Group("Салати");
		first.getGoods().add(new Good("Сирний 200г.", 7.50));
		first.getGoods().add(new Good("Домашній 280г.", 7.50));
		first.getGoods().add(new Good("З капусти 200г.", 7.50));
		first.getGoods().add(new Good("Кореєць 200г.", 7.50));
		first.getGoods().add(new Good("Цезар 240г.", 7.50));
		first.getGoods().add(new Good("Олів'є 250г.", 7.50));
		first.getGoods().add(new Good("М'ясний Теріякі 150г.", 7.50));
		first.getGoods().add(new Good("Грецький 220г.", 7.50));
		first.getGoods().add(new Good("Рибний 200г.", 7.50));
		groups.add(first);
		
		Group two = new Group("Перші страви");
		two.getGoods().add(new Good("Сирний 200г.", 7.50));
		two.getGoods().add(new Good("Домашній 280г.", 7.50));
		two.getGoods().add(new Good("З капусти 200г.", 7.50));
		two.getGoods().add(new Good("Кореєць 200г.", 7.50));
		two.getGoods().add(new Good("Цезар 240г.", 7.50));
		two.getGoods().add(new Good("Олів'є 250г.", 7.50));
		two.getGoods().add(new Good("М'ясний Теріякі 150г.", 7.50));
		two.getGoods().add(new Good("Грецький 220г.", 7.50));
		two.getGoods().add(new Good("Рибний 200г.", 7.50));
		groups.add(two);
		groups.add(new Group("Другі страви"));
		groups.add(new Group("Десерти"));
	}
	
	public void onClick(ActionEvent evt){
		 Good good = (Good)evt.getComponent().getAttributes().get("selectedGood");
		 Sale sale = new Sale();
		 sale.setSalesGood(good);
		 sale.setAmount(5);
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
	public List<Group> getGroups() {
		return groups;
	}

}
