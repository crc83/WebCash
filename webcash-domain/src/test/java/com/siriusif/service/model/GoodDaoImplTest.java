package com.siriusif.service.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.model.Good;
import static com.siriusif.model.helpers.TestHelper.money;

public class GoodDaoImplTest extends AbstractSpringTest {
	
	@Autowired
	private GoodDao goodDao;

	@Test
	public void testAdd() {
		int size = goodDao.list().size();
		
		Good good = new Good();
		good.setName("Помідори");
		good.setPrice(money(10.00));
		good.setShortName("Помідорчики");
		goodDao.add(good);
		
		assertTrue(size < goodDao.list().size());
	}

}
