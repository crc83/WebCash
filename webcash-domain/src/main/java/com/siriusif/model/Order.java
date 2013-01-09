package com.siriusif.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Order {
	private int num;
	private DateFormat date;
	private Currency sum;
	private String autor;
	private int discount;
	private DateFormat time;
	private int status;
	private int tableNum;
	private String originalAutor;
	private boolean ro;
	private boolean tape;
	private DateFormat aDate;
	private DateFormat cTime;
	private int workShift;
	private int nomerok;
	private Currency payed;
	private boolean isCard;
	private int chIdX;
	private List<Suborder> suborders;
	
	public Order(){
		suborders = new ArrayList<Suborder>();
	}

	// use("",0,0)
	/*
	 * public void use(autor, tableNum, workShift){ this.autor = autor;
	 * this.tableNum = tableNum; this.workShift = workShift; //tableNum>0 //vvv
	 * }
	 */
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public DateFormat getDate() {
		return date;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}

	public Currency getSum() {
		return sum;
	}

	public void setSum(Currency sum) {
		this.sum = sum;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public DateFormat getTime() {
		return time;
	}

	public void setTime(DateFormat time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public String getOriginalAutor() {
		return originalAutor;
	}

	public void setOriginalAutor(String originalAutor) {
		this.originalAutor = originalAutor;
	}

	public boolean isRo() {
		return ro;
	}

	public void setRo(boolean ro) {
		this.ro = ro;
	}

	public boolean isTape() {
		return tape;
	}

	public void setTape(boolean tape) {
		this.tape = tape;
	}

	public DateFormat getaDate() {
		return aDate;
	}

	public void setaDate(DateFormat aDate) {
		this.aDate = aDate;
	}

	public DateFormat getcTime() {
		return cTime;
	}

	public void setcTime(DateFormat cTime) {
		this.cTime = cTime;
	}

	public int getWorkShift() {
		return workShift;
	}

	public void setWorkShift(int workShift) {
		this.workShift = workShift;
	}

	public int getNomerok() {
		return nomerok;
	}

	public void setNomerok(int nomerok) {
		this.nomerok = nomerok;
	}

	public Currency getPayed() {
		return payed;
	}

	public void setPayed(Currency payed) {
		this.payed = payed;
	}

	public boolean isCard() {
		return isCard;
	}

	public void setCard(boolean isCard) {
		this.isCard = isCard;
	}

	public int getChIdX() {
		return chIdX;
	}

	public void setChIdX(int chIdX) {
		this.chIdX = chIdX;
	}

	public boolean isValid() {
		if (tableNum > 0 && autor != null && workShift > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Suborder> getSuborders() {
		return suborders;
	}

	public void setSuborders(List<Suborder> suborders) {
		this.suborders = suborders;
	}

	public void addSuborder(Suborder suborder) {
		suborder.setOrder(this);
		suborders.add(suborder);
	}
	
	public double getTotal(){
		//TODO change from double to Currency
		double sum = 0;
		for(Suborder s : suborders){
		sum += s.getTotal();
		}
		return sum;
	}

}
