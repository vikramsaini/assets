package com.hclfintech.management.assets.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.hclfintech.management.assests.ParentTest;
import com.hclfintech.management.assets.model.Shop;
@RunWith(MockitoJUnitRunner.class)
public class ShopDAOImplTest extends ParentTest{
	ShopDAO shopDAO;

	@Before
	public void setUp() throws Exception {
		 shopDAO = new ShopDAOImpl();	
	}

	@After
	public void tearDown() throws Exception {
		 shopDAO = null;
	}

	@Test
	public void testSave() {
		
		Shop oldShop = buildShop("abc", "1", "122011"); 
	    Shop shop=
	    	buildShop("abc", "2", "123456");
		assertNull(shopDAO.save(oldShop));
		Shop replShop = shopDAO.save(shop);
		assertEquals(oldShop, replShop);
	}

	@Test
	public void testGetAllShop() {
		addTwoDiffShopsToDb();
		Collection<Shop> allShop = shopDAO.getAllShop();
		assertNotNull(allShop);
		assertEquals(allShop.size(), 2);
		
	}


}
