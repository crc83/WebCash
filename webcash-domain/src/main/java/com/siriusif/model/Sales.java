package com.siriusif.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.math.*;
import java.text.*;

public class Sales {
	private int id;
	private double amount;
	private Currency sum;
	private Currency allowSum;
	private boolean fp;
	private boolean printed;
	private int idX;
	private int ChIdX;
	private Goods salesGood;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Currency getSum() {
		return sum;
	}
	public void setSum(Currency sum) {
		this.sum = sum;
	}
	public Currency getAllowSum() {
		return allowSum;
	}
	public void setAllowSum(Currency allowSum) {
		this.allowSum = allowSum;
	}
	public boolean isFp() {
		return fp;
	}
	public void setFp(boolean fp) {
		this.fp = fp;
	}
	public boolean isPrinted() {
		return printed;
	}
	public void setPrinted(boolean printed) {
		this.printed = printed;
	}
	public int getIdX() {
		return idX;
	}
	public void setIdX(int idX) {
		this.idX = idX;
	}
	public int getChIdX() {
		return ChIdX;
	}
	public void setChIdX(int chIdX) {
		ChIdX = chIdX;
	}
	
	public Goods getSalesGood() {
		return salesGood;
	}
	public void setSalesGood(Goods salesGood) {
		this.salesGood = salesGood;
	}
	public void addGoodSales(Sales goodSales) {
		goodSales.setSalesGood(salesGood);	
	}
	 
	public double endPrice() {
		//TODO change from double to Currency
		double sum = 0;
		double p = salesGood.getPrice();
		double am = getAmount();
		sum = p * am;
		return sum;
	}
	
	
}
