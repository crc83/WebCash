package com.siriusif.model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class GroupTest {

	/**
	 * Given : new empty group
	 * When : I ask list of goods or subgroups
	 * Than : I get lists of size 0
	 */
	@Test
	public void testGetEmptyGroupsList() {
		//Given
		Group group = new Group();
		
		//When
		List<Group> subGroups =group.getSubGroups();  
		List<Good> goods =group.getGoods();  
		
		//Than
		assertEquals(0,subGroups.size());
		assertEquals(0,goods.size());
	}
	
	/**
	 * Given : New empty group A and subgroup
	 * When  : I add subgroup to group A
	 * Than  : Parent group for subgroup will be group A
	 **/
	 @Test
	 public void testAddSubgroup() {
		 Group groupA = new Group();
		 Group subGroup = new Group();
		 
		 groupA.addSubGroup(subGroup);
		 
		 List<Group> subGroups = groupA.getSubGroups();
		 assertEquals(1, subGroups.size());
		 Group subGroupOfGroupA = subGroups.get(0);
		 assertEquals(groupA, subGroupOfGroupA.getParentGroup());
	 }
	 
	 /**
	  * Given : New empty group and good
	  * When  : I add good to group
	  * Than  : Good will be add to group
	  **/
	 @Test
	 public void testGoodsAddToGroups(){
		 Group group = new Group();
		 Good good = new Good();
		 
		 group.addGood(good);
		 assertEquals(group, good.getParentGroup());
	 }

}
