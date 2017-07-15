package com.hclfintech.management.assets.model;

import javax.validation.constraints.NotNull;

public class Shop {
	@NotNull
	private String shopName;
	@NotNull
	private ShopAddress shopAddress;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public ShopAddress getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", shopAddress=" + shopAddress + "]";
	}
	public String buildAddressString() {
		StringBuilder shopAddressBuilder = new StringBuilder(shopName).append(",").append(shopAddress.getNumber())
				.append(",").append(shopAddress.getPostCode());
		return shopAddressBuilder.toString();
	}
}
