package com.hclfintech.management.assets.dao;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hclfintech.management.assets.model.Shop;

@Repository
public class ShopDAOImpl implements ShopDAO{
	private static final Logger logger = LoggerFactory.getLogger(ShopDAOImpl.class);

	ConcurrentHashMap<String, Shop> shopMap = new ConcurrentHashMap<String, Shop>();
	public  Shop save(Shop shop) { 
		Shop replacedVersion=null; 
		if(shopMap.containsKey(shop.getShopName())){
			replacedVersion = shopMap.replace(shop.getShopName(), shop);
			logger.debug("Replaced Shop "+shop.getShopName()+ " oldValue="+replacedVersion +
					" new Value ="+shop);
			
		}else {
			shopMap.put(shop.getShopName(), shop);
			logger.debug("Created  Shop "+shop.getShopName()+ " with values ="+shop);
		}
		return replacedVersion;
	}
	@Override
	public Collection<Shop> getAllShop() {
		return shopMap.values();
	}

}
