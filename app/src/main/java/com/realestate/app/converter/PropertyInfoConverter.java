package com.realestate.app.converter;

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

}
