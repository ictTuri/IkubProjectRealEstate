package com.realestate.app.utils;

import com.realestate.app.entity.LocationEntity;

public class LocationUtil {
	public static LocationEntity createLocationOne() {
		LocationEntity locOne = new LocationEntity();
		locOne.setCityName("Durres");
		locOne.setStreetName("Roses Road");
		locOne.setZipCode(2001);
		locOne.setDescription("East of Blue Star Center");
		return locOne;
	}
	
	public static LocationEntity createLocationTwo() {
		LocationEntity locTwo = new LocationEntity();
		locTwo.setCityName("Tirane");
		locTwo.setStreetName("Muhamed Gjollesha");
		locTwo.setZipCode(1001);
		locTwo.setDescription("East of police station number 2");
		return locTwo;
	}
}
