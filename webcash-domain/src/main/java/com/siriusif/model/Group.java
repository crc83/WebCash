package com.siriusif.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "`groupofgood`")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	/**
	 * name of group 
	 */
	@Column(name="`name`", nullable=false, length=100)
	private String name;
	
	/**
	 * subgroup of group
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "parentGroup")
	private List<Group> subGroups;
	
	/**
	 * list of goods
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "parentGroup")
	private List<Good> goods;
	
	/** Group belongs to this parent group **/
	@ManyToOne
	@JoinColumn(name = "`parentgroup_id`")
	private Group parentGroup;
	
	public Group(){
		subGroups = new ArrayList<Group>();	
		goods = new ArrayList<Good>();
	}
	

	public Group(String name) {
		this();
		this.name = name;
	}

	public void addSubGroup(Group subGroup) {
		subGroup.setParentGroup(this);
		subGroups.add(subGroup);
	}
	
	public void addGood(Good good) {
		good.setParentGroup(this);
		goods.add(good);
	}
	
	public void fixReferencesToParentGroup(){
		for(Group subgroup : subGroups){
			subgroup.setParentGroup(this);
			subgroup.fixReferencesToParentGroup();
		}
		for(Good good : goods){
			good.setParentGroup(this);
		}
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

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
		for(Good good : goods){
			good.setParentGroup(this);
		}
	}
	
	public List<Group> getSubGroups() {
		return subGroups;
	}

	public void setSubGroups(List<Group> subGroups) {
		this.subGroups = subGroups;
		for(Group group : subGroups){
			group.setParentGroup(this);
		}
	}

	private void setParentGroup(Group group) {
		parentGroup = group;
		for(Good good : goods){
			good.setParentGroup(this);
		}
	}

	public Group getParentGroup() {
		return parentGroup;
	}


	@Override
	public String toString() {
		return "Group [\n\tid=" + id + ", \n\tname=" + name
				+ ", \n\tsubGroups=" + subGroups + ", \n\tgoods=" + goods
				+ "\n]";
	}
}
