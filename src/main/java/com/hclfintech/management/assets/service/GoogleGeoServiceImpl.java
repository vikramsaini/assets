package com.hclfintech.management.assets.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.hclfintech.management.assets.config.Constants;
import com.hclfintech.management.assets.exception.AssetsManagementException;


@Service
public class GoogleGeoServiceImpl implements AssetsGeoService{
	private static final Logger logger = LoggerFactory.getLogger(GoogleGeoServiceImpl.class);
	 
	private GeoApiContext context=new GeoApiContext();
	@Value("${com.google.maps.api.key}")
	private String googleMapApiKey;
	
	@Override
	public double[] getGeoLocation(String address) throws AssetsManagementException {
		logger.debug("Geo Coding Api Key =" + googleMapApiKey);
		context.setApiKey(googleMapApiKey);
		try {
			logger.debug("Start call ! Google Map API  for Address =" + address);
			GeocodingResult result = GeocodingApi.geocode(context, address).await()[0];
			LatLng latLong = result.geometry.location;
			logger.debug("End Call ! Google Map  API response =" + latLong.toUrlValue());
			double[] latLongArray = {latLong.lat,latLong.lat};
			return latLongArray;
		} catch (Exception e) {
			logger.error("Error while using Google Maps Geocoding API", e);
			throw new AssetsManagementException(e, Constants.GOOGLE_SERVICE_DOWN, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
