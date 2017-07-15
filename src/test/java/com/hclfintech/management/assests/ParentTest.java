package com.hclfintech.management.assests;

import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hclfintech.management.assets.dao.ShopDAO;
import com.hclfintech.management.assets.dao.ShopDAOImpl;
import com.hclfintech.management.assets.model.Shop;
import com.hclfintech.management.assets.model.ShopAddress;
import com.hclfintech.management.assets.model.ShopGeoLocation;

public class ParentTest {
	protected Shop buildShop(String shopName, String number, String post) {
		Shop shop = new Shop();
		shop.setShopName(shopName);
		ShopAddress shopAddress = new ShopAddress();
		shopAddress.setNumber(number);
		shopAddress.setPostCode(post);
		shop.setShopAddress(shopAddress);
		return shop;
	}

	protected String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Collection<Shop> addTwoDiffShopsToDb() {
		Shop shop1 = buildShop("Om Sweets",
				"SCF 43, Shopping Complex, Sector 23, Huda, Cartarpuri Alias Daulatpur Nasirabad, Gurugram, Haryana",
				"122001");
		shop1.getShopAddress().setShopGeoLocation(new ShopGeoLocation(28.5117935, 77.0101381));

		Shop shop2 = buildShop("Biryani Blues",
				"B 202 & 203, Upper Ground Floor, Supermart 1, DLF Phase IV, DLF Phase IV, Gurugram, Haryana", "122002");
		shop2.getShopAddress().setShopGeoLocation(new ShopGeoLocation(28.4622346, 77.084998));
		ShopDAO shopDAO = new ShopDAOImpl();
		;
		shopDAO.save(shop1);
		shopDAO.save(shop2);
		return shopDAO.getAllShop();
	}

}
