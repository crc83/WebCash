package com.siriusif.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private int gId;
	private String gName;
	
	private List<Group> subGroups;
	private List<Good> goods;
	
	/** Group belongs to this parent group **/
	private Group parentGroup;
	
	public Group(){
		subGroups = new ArrayList<Group>();	
		goods = new ArrayList<Good>();
	}
	

	public int getgId() {
		return gId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
	
	public List<Group> getSubGroups() {
		return subGroups;
	}


	public void setSubGroups(List<Group> subGroups) {
		this.subGroups = subGroups;
	}


	public void addSubGroup(Group subGroup) {
		subGroup.setParentGroup(this);
		subGroups.add(subGroup);
	}

	public void addGood(Good good) {
		good.setParentGroup(this);
		goods.add(good);
	}

	private void setParentGroup(Group group) {
		parentGroup = group;
	}


	public Group getParentGroup() {
		return parentGroup;
	}


	
}
