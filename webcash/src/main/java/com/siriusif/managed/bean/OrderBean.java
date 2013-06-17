package com.siriusif.managed.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import com.siriusif.process.OrderProcess;
import com.siriusif.service.model.GroupDao;
import com.siriusif.service.model.SaleDao;

import static com.siriusif.jsf.utils.JSFHelper.jsf;

//import static com.siriusif.model.helpers.TestHelper.*;

/**
 * @author csurudin
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

	@ManagedProperty(value = "#{saleDao}")
	private SaleDao saleDao;

	private List<Group> groups;

	private long orderId;

	private long goodId;

	private long suborderId;

	private long saleId;

	private BigDecimal change;

	private BigDecimal moneyFromClient;

	private boolean card;

	/**
	 * get order id from http request
	 * view opened order
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("starting view");

		HttpServletRequest request = jsf().getRequest();
		String orderIdStr = request.getParameter("order_id");
		orderId = Long.parseLong(orderIdStr);
		LOGGER.info("Recieved order id: " + orderId);
		order = orderProcess.getOrder(orderId);
		suborderId = order.getSuborders().get(0).getId();
	}

	/**
	 * @return
	 *         view groups and goods
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
	 * Perform payment and close order
	 * 
	 * @param evt
	 */
	public void payOrder(ActionEvent evt) {
		// TODO : Ask if customer has a discount
		// TODO : Ask about payment amount
		orderProcess.closeOrder(orderId, order.getTotal(), isCard());
		jsf().redirectTo("/webcash/pages/hall_use.jsf");
	}

	/**
	 * Payment choose of Order: cash or credit card
	 * 
	 * @param event
	 */
	public void choosePaymentOrder(ValueChangeEvent event) {
		String choice = (String) event.getNewValue();
		LOGGER.info("Payment choose of Order: " + choice);
		if ("payCard".equals(choice)) {
			card = true;
			LOGGER.info("Is card: " + card);
		}else{
			LOGGER.info("Is card: " + card);
			LOGGER.info("Payment choose of Order: " + choice);
		}
	}

	/**
	 * Add selected good to order.
	 * 
	 * @param evt
	 */
	public void addGood(ActionEvent evt) {
		LOGGER.debug(evt.toString());
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
		LOGGER.debug("On click: " + event.toString());
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
		suborderId = order.getSuborders()
				.get(orderProcess.countOfSuborders(orderId) - 1).getId();
	}

	public void editAmount(ValueChangeEvent event) {
		BigDecimal newAmount = (BigDecimal) event.getNewValue();
		Sale sale = (Sale) event.getComponent().getAttributes()
				.get("selectedSale");
		saleId = sale.getId();
		orderProcess.uptadeSale(saleId, newAmount);
	}

	public void calculateChange(ValueChangeEvent event) {
		moneyFromClient = (BigDecimal) event.getNewValue();
		LOGGER.info("money From Client " + moneyFromClient);
		change = moneyFromClient.subtract(order.getTotal());
		LOGGER.info("change" + change);
	}

	public void deleteSale(ActionEvent event) {
		Sale sale = (Sale) event.getComponent().getAttributes()
				.get("selectedSale");
		saleId = sale.getId();
		orderProcess.deleteSale(saleId);
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public SaleDao getSaleDao() {
		return saleDao;
	}

	public void setSaleDao(SaleDao saleDao) {
		this.saleDao = saleDao;
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

	public BigDecimal getMoneyFromClient() {
		moneyFromClient = order.getTotal();
		return moneyFromClient;
	}

	public void setMoneyFromClient(BigDecimal moneyFromClient) {
		this.moneyFromClient = moneyFromClient;
	}

	public boolean isCard() {
		return card;
	}

	public void setCard(boolean card) {
		this.card = card;
	}
}
