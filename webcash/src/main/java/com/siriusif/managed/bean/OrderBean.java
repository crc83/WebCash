package com.siriusif.managed.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import com.siriusif.process.OrderProcess;
import com.siriusif.service.model.GroupDao;

//import static com.siriusif.model.helpers.TestHelper.*;

@ManagedBean(name = "orderBean")
public class OrderBean {

	private static Logger LOGGER = Logger.getLogger(OrderBean.class);

	private Order order;

	@ManagedProperty(value = "#{orderProcess}")
	private OrderProcess orderProcess;

	@ManagedProperty(value = "#{groupDao}")
	private GroupDao groupDao;

	private List<Group> groups;

	private long orderId;

	@PostConstruct
	public void init() {
		LOGGER.info("starting view");

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String orderIdStr = request.getParameter("order_id");
		orderView(orderIdStr);
	}

	public List<Group> getGroups() {
		groups = groupDao.list();
		for(Group group : groups){
			LOGGER.debug(" | "+group.getName());
			LOGGER.debug(" | "+group.getGoods().size());
		}
		LOGGER.debug(" || " + groups.size());
		return groups;
	}

	// public OrderBean(){
	// //TODO SB : Remove this when we will have DB connection
	// // begin
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// HttpSession session = (HttpSession)
	// facesContext.getExternalContext().getSession(false);
	// Object maybeOrder = session.getAttribute("order");
	// // end
	// if (maybeOrder == null) {
	// orderView(orderIdStr);
	// } else {
	// order =(Order)maybeOrder;
	// }
	// }

	/**
	 * new Order view
	 * 
	 * @param orderIdStr
	 */
	private void orderView(String orderIdStr) {
		orderId = Long.parseLong(orderIdStr);
		LOGGER.info("Recieved order id: " + orderId);
		order = orderProcess.getOrder(orderId);
	}

	public void onClick(ActionEvent evt) {
		Good good = (Good) evt.getComponent().getAttributes()
				.get("selectedGood");
		Sale sale = new Sale();
		sale.setSalesGood(good);
		sale.setAmount(new BigDecimal(1).setScale(3, RoundingMode.HALF_UP));
		order.getSuborders().get(0).addSale(sale);
		// TODO SB : Remove this when we will have DB connection
		// begin
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		session.setAttribute("order", order);
		// end

		// FacesContext.getCurrentInstance().addMessage(null, new
		// FacesMessage("Welcome " + "!"));

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

	public OrderProcess getOrderProcess() {
		return orderProcess;
	}

	public void setOrderProcess(OrderProcess orderProcess) {
		this.orderProcess = orderProcess;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}
