package com.hclfintech.management.assets.model;


import javax.validation.constraints.NotNull;

public class ShopGeoLocation {
    
	@NotNull
    private final Double latitude;
    
    @NotNull 
    private final Double longitude;

    public ShopGeoLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		ShopGeoLocation other = (ShopGeoLocation) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equalss(other.longitude))
			return false;
		return true;
	}
	//Copied Method from  Open Source ApI  
		 public double distanceTo(ShopGeoLocation that) {
		        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
		        double lat1 = Math.toRadians(this.latitude);
		        double lon1 = Math.toRadians(this.longitude);
		        double lat2 = Math.toRadians(that.latitude);
		        double lon2 = Math.toRadians(that.longitude);

		        // great circle distance in radians, using law of cosines formula
		        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
		                               + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		        // each degree on a great circle of Earth is 60 nautical miles
		        double nauticalMiles = 60 * Math.toDegrees(angle);
		        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
		        return statuteMiles;
		    }

}
