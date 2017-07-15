package com.hclfintech.management.assets.dao;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.hclfintech.management.assets.model.Shop;
@Repository
public interface ShopDAO {
	public  Shop save(Shop shop);
	public  Collection<Shop>  getAllShop();

}
