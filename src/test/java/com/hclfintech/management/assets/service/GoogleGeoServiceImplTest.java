package com.hclfintech.management.assets.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.maps.GeoApiContext;
import com.hclfintech.management.assets.exception.AssetsManagementException;
@RunWith(MockitoJUnitRunner.class)
public class GoogleGeoServiceImplTest {
	@InjectMocks
	private GoogleGeoServiceImpl shopService;
	@Mock
	private GeoApiContext geoApiContext;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(shopService);
	}

	@Test(expected=AssetsManagementException.class)
	public void testGetGeoLocation() throws Exception {
		shopService.getGeoLocation("abc");
	}

}
