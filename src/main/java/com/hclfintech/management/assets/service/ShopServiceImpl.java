package com.hclfintech.management.assets.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hclfintech.management.assets.config.Constants;
import com.hclfintech.management.assets.dao.ShopDAO;
import com.hclfintech.management.assets.exception.AssetsManagementException;
import com.hclfintech.management.assets.model.Shop;
import com.hclfintech.management.assets.model.ShopAddress;
import com.hclfintech.management.assets.model.ShopGeoLocation;

@Service
public class ShopServiceImpl implements ShopService {
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private ShopDAO shopDAO;
	@Autowired
	private AssetsGeoService assetsGeoService;

	public Shop saveShop(Shop shop) throws AssetsManagementException {
		Shop replacedVersion = shopDAO.save(shop);
		addGeoLocationToShop(shop);
		return replacedVersion;

	}

	private void addGeoLocationToShop(Shop shop) throws AssetsManagementException {
		double[] latLongArr = assetsGeoService.getGeoLocation(shop.buildAddressString());
		ShopGeoLocation shopGeoLocation = new ShopGeoLocation(latLongArr[0], latLongArr[1]);
		shop.getShopAddress().setShopGeoLocation(shopGeoLocation);
		logger.debug("Added GeoLocation=" + shopGeoLocation + " to shop=" + shop.getShopName());

	}

	@Override
	public Shop getClosestShop(ShopGeoLocation location) throws AssetsManagementException {
		Collection<Shop> shops = shopDAO.getAllShop();
		if(shops.isEmpty()) {
			logger.info("Constants.NO_SHOPS_IN_DB");
			throw new AssetsManagementException(Constants.NO_SHOPS_IN_DB, HttpStatus.OK);
		}
		Shop closest = getClosestShopInTheList(location, shops);
		return closest;
	}

	private Shop getClosestShopInTheList(ShopGeoLocation location, Collection<Shop> shops) {
		Shop closest = null;
		double smallestDistance = Double.MAX_VALUE;
		for (Shop shop : shops) {
			ShopAddress shopAddress = shop.getShopAddress();
			if (shopAddress != null && shopAddress.getShopGeoLocation() != null) {
				double shopDistance = shopAddress.getShopGeoLocation().distanceTo(location);
				if (shopDistance < smallestDistance) {
					closest = shop;
					smallestDistance = shopDistance;
				}
			}

		}
		return closest;
	}
}
