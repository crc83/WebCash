package com.siriusif.service.model;

import static com.siriusif.model.helpers.SaleBuiledr.money;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Good;
import com.siriusif.model.Group;

public class GroupDaoImplTest extends AbstractSpringTest {
	
	@Autowired
	private GroupDao groupDao;
	
	@Before
	public void setUp(){
		groupDao.clearAll();
	}

	@Test
	public void testAdd() {
		int size = groupDao.list().size();
		
		Group group = new Group();
		group.setName("Супи");
		groupDao.add(group);
		
		assertTrue(size < groupDao.list().size());
	}
	
	@Test
	public void testOneToManyGroupGoods(){
		int size = groupDao.list().size();
		Group group = new Group("Овочі");
		
		Good good = new Good();
		good.setName("огірки");
		good.setPrice(money(10.00));
		good.setShortName("Огірочки");
		group.addGood(good);
		
		Good good1 = new Good();
		good1.setName("Цибуля");
		good1.setPrice(money(11.00));
		good1.setShortName("Цибулька");
		group.addGood(good1);
		
		Good good2 = new Good();
		good2.setName("Картопля");
		good2.setPrice(money(12.00));
		good2.setShortName("Бульба");
		group.addGood(good2);
		
		groupDao.add(group);
		
		assertTrue(size < groupDao.list().size());
		Group groupFromBd = groupDao.find(group.getId());
		assertEquals("Овочі", groupFromBd.getName());
		assertEquals(3, groupFromBd.getGoods().size());
	}
	
	@Test
	public void testOneToManyGroupSubgroup(){
		int size = groupDao.list().size();
		Group group = new Group("Напої");
		
		Group subGroup = new Group("Гарячі напої");
		group.addSubGroup(subGroup);
		
		Group subGroup1 = new Group("Холдні напої");
		group.addSubGroup(subGroup1);
		
		Group subGroup2 = new Group("Соки");
		group.addSubGroup(subGroup2);
		
		Group subGroup3 = new Group("Вода");
		group.addSubGroup(subGroup3);
		
		groupDao.add(group);
		
		assertTrue(size < groupDao.list().size());
		Group groupFromBd = groupDao.find(group.getId());
		assertEquals("Напої", groupFromBd.getName());
		assertEquals(4, groupFromBd.getSubGroups().size());
		Group subgroupFromBd = groupDao.find(subGroup.getId());
		assertEquals("Гарячі напої", subgroupFromBd.getName());
	}
	
	/**
	 * Given : Group with subgroups and goods in each subgroup saved in database
	 * When  : I read subgroup (which is second)
	 * Than  : I read from database list of goods as well
	 */
	@Test
	public void testReadGroupWithGoodsAndSubgroups()throws IOException{
		// set up data for tests
		Group[] groups = Helper.fromJsonGroups("/model_group.json");
		Long index = null;
		for (Group group : groups){
			index = groupDao.add(group);
		}
		
		Group groupFromBd = groupDao.find(index);
		assertEquals("Напої", groupFromBd.getName());
		assertEquals(0, groupFromBd.getGoods().size());
		assertEquals(2, groupFromBd.getSubGroups().size());		
	}
	
	/**
	 * Given : Empty database
	 * When  : I try to fetch any Group
	 * Than  : I receive null and no exceptions
	 */
	@Test
	public void testIfFindNonexistingGroup(){
		Group group = groupDao.find(1l);
		assertNull(group);
	}
}
