package com.siriusif.managed.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siriusif.model.Order;
import com.siriusif.process.OrderProcess;
import com.siriusif.service.model.OrderDao;

@ManagedBean(name = "ordersList")
@ViewScoped
public class OrdersListBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrdersListBean.class);
	
	private long tableId;
	
	private List<Order> ordersForTable;
	
	//TODO SB : Remove access to dao clases here
	@ManagedProperty(value="#{orderDao}")
	private OrderDao orderDao;
	
	@ManagedProperty(value="#{orderProcess}")
	private OrderProcess orderProcess;

	@PostConstruct
	public void init() {
		LOGGER.error("starting view");
//		orderProcess.setCurrentUser(curentUser)
		
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String value=request.getParameter("table");
		LOGGER.info("Recieved table id :"+value);
		tableId = Long.parseLong(value);
		if(skipAndOpenNewOrder()) {
			try {
				Order order = orderProcess.newOrder();
				//since order list and order are at the same level in /pages
				FacesContext.getCurrentInstance().getExternalContext().redirect("order.jsf?order_id="+order.getId());
			} catch (IOException e) {
				LOGGER.error("I can't open new order");
				LOGGER.debug("Error while redirecting to new check", e);
			}
		}
	}
	
	/**
	 * Here is rule to open new order immediately
	 * @return true if we should skip this page
	 */
	public boolean skipAndOpenNewOrder(){
		return (orderDao.countOpenedForTableId(tableId)<1);
	}
	
	/**
	 * This method retrieves table_id from request parameter <code>table</code>.</br>
	 * NOTE : This method stores retrieved table_id for future use.
	 * @return table id for which order list should be displayed. 
	 * At the moment this page invoked only from tables selection view.
	 */
	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	} 
	
	public List<Order> getOrdersForTable() {
		return orderDao.listForTableId(getTableId());
	}
	
	public void setOrdersForTable() {
		//this is read only property
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public OrderProcess getOrderProcess() {
		return orderProcess;
	}

	public void setOrderProcess(OrderProcess orderProcess) {
		this.orderProcess = orderProcess;
	}

}
