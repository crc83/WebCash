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
@Table(name = "groupofgood")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long gId;
	
	/**
	 * name of group 
	 */
	@Column(name="name", nullable=false, length=100)
	private String gName;
	
	/**
	 * subgroup of group
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "parentGroup")
	private List<Group> subGroups;
	
	/**
	 * list of goods
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "parentGroup")
	private List<Good> goods;
	
	/** Group belongs to this parent group **/
	@ManyToOne
	@JoinColumn(name = "parentgroup_id")
	private Group parentGroup;
	
	public Group(){
		subGroups = new ArrayList<Group>();	
		goods = new ArrayList<Good>();
	}
	

	public Group(String gName) {
		this();
		this.gName = gName;
	}

	public void addSubGroup(Group subGroup) {
		subGroup.setParentGroup(this);
		subGroups.add(subGroup);
	}
	
	public void addGood(Good good) {
		good.setParentGroup(this);
		goods.add(good);
	}

	/* autogenerated stuff */
	
	public Long getgId() {
		return gId;
	}
	public void setgId(Long gId) {
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


	
}
