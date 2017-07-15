package com.hclfintech.management.assets.service;

import org.springframework.stereotype.Service;

import com.hclfintech.management.assets.exception.AssetsManagementException;
import com.hclfintech.management.assets.model.Shop;
import com.hclfintech.management.assets.model.ShopGeoLocation;

@Service
public interface ShopService {
	public Shop saveShop(Shop shop) throws AssetsManagementException;

	public Shop getClosestShop(ShopGeoLocation location) throws AssetsManagementException;
   	

}
