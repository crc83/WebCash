package com.siriusif.service.model;

import static com.siriusif.model.helpers.SaleBuiledr.money;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.model.Good;
import com.siriusif.model.Group;

public class GroupDaoImplTest extends AbstractDaoImplTest {
	
	@Autowired
	private GroupDao groupDao;

	@Test
	public void testAdd() {
		int size = groupDao.list().size();
		
		Group group = new Group();
		group.setgName("Супи");
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
		Group groupFromBd = groupDao.find(group.getgId());
		assertEquals("Овочі", groupFromBd.getgName());
		assertEquals(3, groupFromBd.getGoods().size());
	}

}
