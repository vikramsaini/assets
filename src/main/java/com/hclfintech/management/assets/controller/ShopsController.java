package com.hclfintech.management.assets.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hclfintech.management.assets.config.Constants;
import com.hclfintech.management.assets.exception.AssetsManagementException;
import com.hclfintech.management.assets.model.Shop;
import com.hclfintech.management.assets.model.ShopGeoLocation;
import com.hclfintech.management.assets.service.ShopService;

@RestController
@RequestMapping("/shops")
public class ShopsController {
	@Autowired
	private ShopService shopService;
	private static final Logger logger = LoggerFactory.getLogger(ShopsController.class);

	@RequestMapping( method = RequestMethod.GET)
	public String isUp() {
		return Constants.SERVICE_UP_SUCCESS;
	}

	@RequestMapping(path = "/postShop", method = RequestMethod.POST)
	public ResponseEntity<Shop> postShop(@RequestBody(required = true) @Validated Shop shop)
			throws AssetsManagementException {

		Shop saveShop = shopService.saveShop(shop);
		logger.debug("Shop created !");
		HttpStatus status = saveShop == null ? HttpStatus.NO_CONTENT : HttpStatus.CREATED;
		ResponseEntity<Shop> responseEntity = new ResponseEntity<Shop>(shop, status);
		return responseEntity;

	}

	@RequestMapping(value = "/getClosest", method = RequestMethod.GET)
	public ResponseEntity<Shop> getNearestShop(@RequestParam("latitude") String latitude ,
			@RequestParam("longitude") String longitude) throws AssetsManagementException {
		try {
			ShopGeoLocation location = new ShopGeoLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
			Shop nearestShop = shopService.getClosestShop(location);
			logger.debug("Closest Shop found is =" + nearestShop);
			ResponseEntity<Shop> responseEntity = new ResponseEntity<Shop>(nearestShop, HttpStatus.OK);
			return responseEntity;
		} catch (NumberFormatException e) {
			logger.error("Invalid value for longitude - " + longitude + " or latitude - " + latitude + "\n", e);
			throw new AssetsManagementException(e, Constants.INCORRECT_SHOP_LOCATION, HttpStatus.BAD_REQUEST);
		}
	}

}
