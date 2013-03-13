package com.siriusif.model;

import java.util.ArrayList;
import java.util.List;

public class GroupsList {
	
	private List<Group> groups;
	
	public GroupsList(){
		groups = new ArrayList<Group>();
	}
	
	public void addGroups(Group group){
		group.setGroupsList(this);
		groups.add(group);
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
		for(Group group : groups){
			group.setGroupsList(this);
		}
	}

}
