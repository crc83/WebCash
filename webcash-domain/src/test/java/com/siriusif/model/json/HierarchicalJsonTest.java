package com.siriusif.model.json;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siriusif.helper.Helper;
import com.siriusif.model.Group;

public class HierarchicalJsonTest {
	
	public static Group[] groups;
	
	@BeforeClass
	public static void globalSetUp() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		groups = Helper.fromJsonGroups("/demo_groupslist.json");
	}

	@Test
	public void testIfAllGroupsLoaded(){
		assertEquals(2,groups.length);
	}

	@Test
	public void testIfAllSubgroupsLoaded(){
		assertEquals(0, groups[0].getSubGroups().size());
		assertEquals(2, groups[1].getSubGroups().size());		
	}
	
	@Test
	public void testIfAllReferencesInSubgroupsCorrect(){
		for(int i=0;i<2;i++){
			assertEquals(groups[1], groups[1].getSubGroups().get(i).getParentGroup());
		}
	}
	
	@Test
	public void testIfReferencesInGoodsAreCoorrect(){
		for(int i=0; i<6; i++){
			assertEquals("Error with good:"+i,groups[0], groups[0].getGoods().get(i).getParentGroup());
		}
	}

	@Test
	public void testIfReferencesInGoodsToSubGroupAreCoorrect() {
		for (int s = 0; s < groups[1].getSubGroups().size(); s++) {
			for (int j = 0; j < 2; j++) {
				Group subGroup = groups[1].getSubGroups().get(j);
				for (int i = 0; i < 3; i++) {
					assertEquals("Error in subgroup:" + j + " with good:" + i,
							subGroup, subGroup.getGoods().get(i)
									.getParentGroup());
				}
			}
		}
	}

	@Test
	public void testIfAllGoodsLoaded() throws JsonSyntaxException, JsonIOException, UnsupportedEncodingException{
		assertEquals(6, groups[0].getGoods().size());
		assertEquals(0, groups[1].getGoods().size());
		
		assertEquals(3, groups[1].getSubGroups().get(0).getGoods().size());
		assertEquals(3, groups[1].getSubGroups().get(1).getGoods().size());
	}
}
