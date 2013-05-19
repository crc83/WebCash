package com.siriusif.managed.bean;

import static com.siriusif.jsf.utils.JSFHelper.jsf;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siriusif.model.Order;
import com.siriusif.process.OrderProcess;

@ManagedBean(name = "ordersList")
@ViewScoped
public class OrdersListBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrdersListBean.class);

	private long tableId;

	@ManagedProperty(value = "#{orderProcess}")
	private OrderProcess orderProcess;

	@PostConstruct
	public void init() {
		HttpServletRequest request = jsf().getRequest();
		String tableIdStr = request.getParameter("table");
		jsf().redirectTo(urlToNewOrderIfNoOrdersForTable(tableIdStr));
	}

	// TODO SB: Javadoc add here
	// TODO SB: return "I can't create new order"
	public String urlToNewOrderIfNoOrdersForTable(String tableIdStr) {
		String redirectTo = "";
		LOGGER.info("Recieved table id :" + tableIdStr);
		tableId = Long.parseLong(tableIdStr);
		// Here is rule to open new order immediately
		if (orderProcess.countOpenedForTableId(tableId) < 1) {
			Order order = orderProcess.newOrder(tableId);
			if (order != null) {
				// since order list and order are at the same level in /pages
				redirectTo = "order.jsf?order_id=" + order.getId();
			} else {
				// order hasn't been created for some reason
				notifyUser("I can't create new order");
				LOGGER.error("I can't create new order");
			}
		}
		return redirectTo;
	}

	/**
	 * for button (new Order create and redirect to order.jsf)
	 * 
	 * @return url redirect to order view
	 */
	public String urlToNewOrder() {
		String redirectTo = "";
		LOGGER.info("urlToNewOrder:||Recieved table id :" + tableId);
		Order order = orderProcess.newOrder(tableId);
		if (order != null) {
			redirectTo = "order.jsf?order_id=" + order.getId()
					+ "faces-redirect=true";
		} else {
			notifyUser("I can't create new order");
			LOGGER.error("I can't create new order");
		}
		return redirectTo;
	}

	private void notifyUser(String message) {
		// TODO SB : Implement
	}

	/**
	 * This method retrieves table_id from request parameter <code>table</code>
	 * .</br> NOTE : This method stores retrieved table_id for future use.
	 * 
	 * @return table id for which order list should be displayed. At the moment
	 *         this page invoked only from tables selection view.
	 */
	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public List<Order> getOrdersForTable() {
		return orderProcess.listForTableId(getTableId());
	}

	public void setOrdersForTable(List<Order> orders) {
		// this is read only property
	}

	public OrderProcess getOrderProcess() {
		return orderProcess;
	}

	public void setOrderProcess(OrderProcess orderProcess) {
		this.orderProcess = orderProcess;
	}

}
