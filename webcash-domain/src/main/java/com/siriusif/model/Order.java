package com.siriusif.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Order Entity CREATE TABLE "Orders" ( "id" INT NOT NULL, "date" DATETIME NOT
 * NULL, "Name" NVARCHAR(10) NOT NULL, );
 */
@Entity
@Table(name = "orders")
public class Order {
	public static final int STATUS_OPEN_DATA = 0;

	public static final int STATUS_CLOSE_DATA = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	/**
	 * date and time of order creation
	 */
	@Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date openDate;

	/**
	 * date and time of order closed
	 */
	@Column(name = "closedate", nullable = true, columnDefinition = "TIMESTAMP", insertable = false)
	@Temporal(TemporalType.DATE)
	private Date closeDate;

	@Column(name = "author", nullable = false, length = 100)
	private String author;

	@Column(name = "discount", nullable = true)
	private int discount;

	// private int status; ENUM

	/**
	 * number of table
	 */
	@Column(name = "dinnertable", nullable = true)
	private DinnerTable table;

	// private String originalAutor;??????

	/**
	 * order was printed on a fiscal printer
	 */
	@Column(name = "readOnly", columnDefinition = "boolean default false")
	private boolean readOnly;

	/**
	 * order for return
	 */
	@Column(name = "type", columnDefinition = "boolean default false")
	private boolean type;

	/**
	 * working date (filled at creation)
	 */
	@Column(name = "workingDate", nullable = true, columnDefinition = "TIMESTAMP", insertable = false)
	@Temporal(TemporalType.DATE)
	private Date workingDate;

	@Column(name = "workshift", nullable = true)
	private Long workShift;

	@Column(name = "nomeroc", nullable = true)
	private int nomerok;

	/**
	 * money from client
	 */
	@Column(name = "payed", nullable = true, precision = 16, scale = 2)
	private BigDecimal payed;

	/**
	 * paid with credit card
	 */
	@Column(name = "isCard", columnDefinition = "boolean default false")
	private boolean isCard;

	/**
	 * number of order for workshift
	 */
	@Column(name = "daylyId", nullable = false)
	private int dailyId;

	@Column(name = "status", nullable = false)
	private int status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	private List<Suborder> suborders;

	public Order() {
		suborders = new ArrayList<Suborder>();
	}

	public boolean isValid() {
		if (table != null && author != null && workShift > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidForClose() {
		if (payed.compareTo(getTotal()) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public void addSuborder(Suborder suborder) {
		suborder.setOrder(this);
		suborders.add(suborder);
	}

	/**
	 * Return total sum of all suborders in order
	 * 
	 * @return total sum
	 */
	public BigDecimal getTotal() {
		BigDecimal sum = BigDecimal.ZERO;
		for (Suborder s : suborders) {
			sum = sum.add(s.getTotal());
		}
		return sum;
	}

	/* autogenerated stuff */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public DinnerTable getTable() {
		return table;
	}

	public void setTable(DinnerTable table) {
		this.table = table;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public Date getWorkingDate() {
		return workingDate;
	}

	public void setWorkingDate(Date workingDate) {
		this.workingDate = workingDate;
	}

	public Long getWorkShift() {
		return workShift;
	}

	public void setWorkShift(Long workShift) {
		this.workShift = workShift;
	}

	public int getNomerok() {
		return nomerok;
	}

	public void setNomerok(int nomerok) {
		this.nomerok = nomerok;
	}

	public BigDecimal getPayed() {
		return payed;
	}

	public void setPayed(BigDecimal payed) {
		this.payed = payed;
	}

	public boolean isCard() {
		return isCard;
	}

	public void setCard(boolean isCard) {
		this.isCard = isCard;
	}

	public int getDailyId() {
		return dailyId;
	}

	public void setDailyId(int dailyId) {
		this.dailyId = dailyId;
	}

	public List<Suborder> getSuborders() {
		return suborders;
	}

	public void setSuborders(List<Suborder> suborders) {
		this.suborders = suborders;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
