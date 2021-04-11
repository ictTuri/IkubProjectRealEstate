package com.realestate.app.converter;

import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.entity.enums.PropertyCategory;

public class FullPropertyConverter {
	
	private FullPropertyConverter() {
	}

	public static FullPropertyDto toDto(PropertyEntity property, PropertyInfoEntity propertyInfo) {
		FullPropertyDto propertyToReturn = new FullPropertyDto();
		propertyToReturn.setDescription(property.getDescription());
		propertyToReturn.setRentingPrice(property.getRentingPrice());
		propertyToReturn.setSellingPrice(property.getSellingPrice());
		propertyToReturn.setCategory(property.getCategory().toString());
		propertyToReturn.setPropertyType(property.getPropertyType().getPropertyTypeId());
		propertyToReturn.setLocation(property.getPropertyLocation().getLocationId());
		propertyToReturn.setHasGarage(propertyInfo.isHasGarage());
		propertyToReturn.setHasElevator(propertyInfo.isHasElevator());
		propertyToReturn.setHasPool(propertyInfo.isHasPool());
		propertyToReturn.setFloorNumber(propertyInfo.getFloorNumber());
		propertyToReturn.setNrBathrooms(propertyInfo.getNrBathrooms());
		propertyToReturn.setNrBedrooms(propertyInfo.getNrBedrooms());
		propertyToReturn.setArea(propertyInfo.getArea());
		return propertyToReturn;
	}
	
	public static PropertyDto singleToDto(PropertyEntity property) {
		PropertyDto propertyToReturn = new PropertyDto();
		propertyToReturn.setDescription(property.getDescription());
		propertyToReturn.setRentingPrice(property.getRentingPrice());
		propertyToReturn.setSellingPrice(property.getSellingPrice());
		propertyToReturn.setCategory(property.getCategory());
		propertyToReturn.setPropertyType(property.getPropertyType().getPropertyTypeName());
		propertyToReturn.setLocation(property.getPropertyLocation().getCityName());
		propertyToReturn.setPropertyInfo(PropertyInfoConverter.toDto(property.getPropertyInfo()));
		return propertyToReturn;
	}
	
	public static PropertyEntity toEntityForCreate(FullPropertyDto dto, UserEntity owner,
			PropertyTypeEntity propertyType, LocationEntity location, PropertyInfoEntity propertyInfo) {
		PropertyEntity toReturn = new PropertyEntity();
		toReturn.setPropertiesId(null);
		toReturn.setOwner(owner);
		toReturn.setDescription(dto.getDescription());
		toReturn.setRentingPrice(dto.getRentingPrice());
		toReturn.setSellingPrice(dto.getSellingPrice());
		toReturn.setCategory(PropertyCategory.valueOf(dto.getCategory()));
		toReturn.setPropertyType(propertyType);
		toReturn.setPropertyLocation(location);
		toReturn.setPropertyInfo(propertyInfo);
		return toReturn;
	}
	
	public static PropertyInfoEntity toInfoEntityForCreate(FullPropertyDto propertyInfo) {
		PropertyInfoEntity propertyInfoToReturn = new PropertyInfoEntity();
		propertyInfoToReturn.setHasGarage(propertyInfo.isHasGarage());
		propertyInfoToReturn.setHasElevator(propertyInfo.isHasElevator());
		propertyInfoToReturn.setHasPool(propertyInfo.isHasPool());
		propertyInfoToReturn.setFloorNumber(propertyInfo.getFloorNumber());
		propertyInfoToReturn.setArea(propertyInfo.getArea());
		propertyInfoToReturn.setNrBathrooms(propertyInfo.getNrBathrooms());
		propertyInfoToReturn.setNrBedrooms(propertyInfo.getNrBedrooms());
		return propertyInfoToReturn;
	}
}
