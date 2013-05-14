package com.siriusif.managed.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;

import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.model.Order;
import com.siriusif.model.Suborder;
import com.siriusif.process.OrderProcess;
import com.siriusif.service.model.GroupDao;

//import static com.siriusif.model.helpers.TestHelper.*;

/**
 * @author Администратор
 *
 */
/**
 * @author Администратор
 * 
 */
@ManagedBean(name = "orderBean")
@ViewScoped
public class OrderBean {

	private static Logger LOGGER = Logger.getLogger(OrderBean.class);

	private Order order;

	@ManagedProperty(value = "#{orderProcess}")
	private OrderProcess orderProcess;

	@ManagedProperty(value = "#{groupDao}")
	private GroupDao groupDao;

	private List<Group> groups;

	private long orderId;

	private long goodId;

	private long suborderId;

	/**
	 * get order id from http request
	 * view opened order
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("starting view");

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String orderIdStr = request.getParameter("order_id");
		orderId = Long.parseLong(orderIdStr);
		LOGGER.info("Recieved order id: " + orderId);
		order = orderProcess.getOrder(orderId);
		suborderId = order.getSuborders().get(0).getId();
	}

	/**
	 * @return
	 * view groups and goods
	 */
	public List<Group> getGroups() {
		groups = groupDao.list();
		for (Group group : groups) {
			LOGGER.debug(" | " + group.getName());
			LOGGER.debug(" | " + group.getGoods().size());
		}
		LOGGER.debug(" || " + groups.size());
		return groups;
	}

	/**
	 * @param evt
	 * add selected good to order
	 */
	public void onClick(ActionEvent evt) {
		LOGGER.info(evt.toString());
		Good good = (Good) evt.getComponent().getAttributes()
				.get("selectedGood");
		goodId = good.getId();
		LOGGER.info("Good id is: " + goodId);
		LOGGER.info("Suborder id is: " + suborderId);
		for (Suborder suborder : order.getSuborders()) {
			LOGGER.info("Suborders of order: " + suborder.getId());
		}
		order = orderProcess.addGoodsToOrder(goodId, orderId, suborderId);
	}

	public void activeSuborderId(ActionEvent event) {
		LOGGER.info("On click: " + event.toString());
		Suborder suborder = (Suborder) event.getComponent().getAttributes()
				.get("selectedSuborder");
		suborderId = suborder.getId();
		LOGGER.info("Suborder id: " + suborderId);
	}

	/**
	 * add new suborder to order
	 */
	public void addNewSuborder() {
		order = orderProcess.addSuborder(orderId);
		suborderId = order.getSuborders().get(orderProcess.countOfSuborders(orderId)-1).getId();
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

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(long goodId) {
		this.goodId = goodId;
	}

	public long getSuborderId() {
		return suborderId;
	}

	public void setSuborderId(long suborderId) {
		this.suborderId = suborderId;
	}
}
