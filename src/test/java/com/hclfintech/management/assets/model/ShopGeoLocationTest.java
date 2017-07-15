package com.hclfintech.management.assets.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShopGeoLocationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDistanceTo() {
		ShopGeoLocation loc1 = new ShopGeoLocation( 28.5117935,77.0101381);
		ShopGeoLocation loc2 = new ShopGeoLocation(   28.5117216,77.0094509);  
        double distance = loc1.distanceTo(loc2);
        Assert.assertEquals(0.04198876273188795, distance, 2);
	}

}
