package com.hclfintech.management.assets.service;

import org.springframework.stereotype.Service;

import com.hclfintech.management.assets.exception.AssetsManagementException;

@Service
public interface AssetsGeoService {
	public double[] getGeoLocation(String address) throws AssetsManagementException;

}
