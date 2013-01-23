package com.siriusif.model;

import java.util.ArrayList;
import java.util.List;


public class Hall {
	private Long id;
	private String name;
	private List<Tables> tables;
	
	public Hall(){
		tables = new ArrayList<Tables>();
	}
	
	public void addTables(Tables table) {
		table.setHall(this);
		tables.add(table);
	}

	/* autogenerated stuff */
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Tables> getTables() {
		return tables;
	}
	public void setTables(List<Tables> tables) {
		this.tables = tables;
	}

}
