package com.hclfintech.management.assets.model;

import javax.validation.constraints.NotNull;

public class ShopAddress {
	@NotNull
	private String number ;
	@NotNull
	private String postCode ;
	private ShopGeoLocation shopGeoLocation;
	
	public ShopGeoLocation getShopGeoLocation() {
		return shopGeoLocation;
	}
	public void setShopGeoLocation(ShopGeoLocation shopGeoLocation) {
		this.shopGeoLocation = shopGeoLocation;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	@Override
	public String toString() {
		return "ShopAddress [number=" + number + ", postCode=" + postCode + ", shopGeoLocation=" + shopGeoLocation
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + ((shopGeoLocation == null) ? 0 : shopGeoLocation.hashCode());
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
		ShopAddress other = (ShopAddress) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (shopGeoLocation == null) {
			if (other.shopGeoLocation != null)
				return false;
		} else if (!shopGeoLocation.equals(other.shopGeoLocation))
			return false;
		return true;
	}
	 
}
