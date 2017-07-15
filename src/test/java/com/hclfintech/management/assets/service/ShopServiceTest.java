package com.hclfintech.management.assets.service;




import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.hclfintech.management.assests.ParentTest;
import com.hclfintech.management.assets.dao.ShopDAO;
import com.hclfintech.management.assets.exception.AssetsManagementException;
import com.hclfintech.management.assets.model.Shop;
import com.hclfintech.management.assets.model.ShopGeoLocation;
@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest extends ParentTest {
	@InjectMocks
	private ShopServiceImpl shopService;
	@Mock
	private ShopDAO shopDAO;
	@Mock
	private AssetsGeoService assetsGeoService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(shopService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveShop() throws AssetsManagementException {
		Shop shop = buildShop("abc", "1", "122011");
		when(shopDAO.save(shop)).thenReturn(null);
		double[] value = {1,2};
		when(assetsGeoService.getGeoLocation(anyString())).thenReturn(value);
		Shop savedShop = shopService.saveShop(shop);
		org.junit.Assert.assertNull(savedShop);
		
		//Test shop Update
		Shop shopUpdate = buildShop("abc", "2", "122011");
				double[] updatedLatLng = {2,3};
		when(assetsGeoService.getGeoLocation(anyString())).thenReturn(updatedLatLng);
		when(shopDAO.save(shopUpdate)).thenReturn(shop);
		Shop replacedShop = shopService.saveShop(shopUpdate);
		Assert.assertEquals(shop.getShopName(),replacedShop.getShopName());
		Assert.assertEquals(shop.getShopAddress(),replacedShop.getShopAddress());
		
		
	}

	@Test
	public void testGetClosestShop() throws AssetsManagementException {
		Collection<Shop> shops = addTwoDiffShopsToDb();
		when(shopDAO.getAllShop()).thenReturn(shops);
		Shop closestShop = shopService.getClosestShop(new ShopGeoLocation(28.5117935,77.0101381));
		Assert.assertEquals(closestShop.getShopName(),"Om Sweets");
		Assert.assertEquals("SCF 43, Shopping Complex, Sector 23, Huda, Cartarpuri Alias Daulatpur Nasirabad, Gurugram, Haryana",closestShop.getShopAddress().getNumber());
		
		
	}
	
	 
}
