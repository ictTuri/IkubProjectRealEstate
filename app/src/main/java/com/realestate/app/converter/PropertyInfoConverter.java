package com.realestate.app.converter;

import java.util.ArrayList;
import java.util.List;

import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.entity.PropertyInfoEntity;

public class PropertyInfoConverter {
	
	private PropertyInfoConverter() {
	}
	
	public static PropertyInfoDto toDto(PropertyInfoEntity propertyInfo) {
		PropertyInfoDto propertyInfoToReturn = new PropertyInfoDto();
		propertyInfoToReturn.setPropertyInfoId(propertyInfo.getPropertyInfoId());
		propertyInfoToReturn.setHasGarage(propertyInfo.isHasGarage());
		propertyInfoToReturn.setHasElevator(propertyInfo.isHasElevator());
		propertyInfoToReturn.setHasPool(propertyInfo.isHasPool());
		propertyInfoToReturn.setFloorNumber(propertyInfo.getFloorNumber());
		propertyInfoToReturn.setArea(propertyInfo.getArea());
		propertyInfoToReturn.setNrBathrooms(propertyInfo.getNrBathrooms());
		propertyInfoToReturn.setNrBedrooms(propertyInfo.getNrBedrooms());
		propertyInfoToReturn.setVersion(propertyInfo.getVersion());
		return propertyInfoToReturn;
	}
	
	public static List<PropertyInfoDto> toDto(List<PropertyInfoEntity> propertyInfo) {
		List<PropertyInfoDto> toReturn = new ArrayList<>();
		for(PropertyInfoEntity ue : propertyInfo) {
			toReturn.add(toDto(ue));
		}
		return toReturn;
	}


	public static PropertyInfoEntity toEntity(PropertyInfoDto propertyInfo) {
		PropertyInfoEntity propertyInfoToReturn = new PropertyInfoEntity();
		propertyInfoToReturn.setHasGarage(propertyInfo.isHasGarage());
		propertyInfoToReturn.setHasElevator(propertyInfo.isHasElevator());
		propertyInfoToReturn.setHasPool(propertyInfo.isHasPool());
		propertyInfoToReturn.setFloorNumber(propertyInfo.getFloorNumber());
		propertyInfoToReturn.setArea(propertyInfo.getArea());
		propertyInfoToReturn.setNrBathrooms(propertyInfo.getNrBathrooms());
		propertyInfoToReturn.setNrBedrooms(propertyInfo.getNrBedrooms());
		propertyInfoToReturn.setVersion(propertyInfo.getVersion());
		return propertyInfoToReturn;
	}

}
