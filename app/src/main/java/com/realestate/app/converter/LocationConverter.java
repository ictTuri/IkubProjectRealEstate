package com.realestate.app.converter;

import java.util.ArrayList;
import java.util.List;

import com.realestate.app.dto.LocationDto;
import com.realestate.app.entity.LocationEntity;

public class LocationConverter {
	
	private LocationConverter() {
	}

	public static LocationDto toDto(LocationEntity location) {
		LocationDto locationToReturn = new LocationDto();
		locationToReturn.setCityName(location.getCityName());
		locationToReturn.setStreetName(location.getStreetName());
		locationToReturn.setZipCode(location.getZipCode());
		locationToReturn.setDescription(location.getDescription());
		return locationToReturn;
	}

	public static List<LocationDto> toDto(List<LocationEntity> location) {
		List<LocationDto> toReturn = new ArrayList<>();
		for(LocationEntity ue : location) {
			toReturn.add(toDto(ue));
		}
		return toReturn;
	}
	
	public static LocationEntity toEntity(LocationDto location) {
		LocationEntity locationToReturn = new LocationEntity();
		locationToReturn.setCityName(location.getCityName());
		locationToReturn.setStreetName(location.getStreetName());
		locationToReturn.setZipCode(location.getZipCode());
		locationToReturn.setDescription(location.getDescription());
		return locationToReturn;
	}
	
}
