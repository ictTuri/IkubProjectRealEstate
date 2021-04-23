package com.realestate.app.converter;

import com.realestate.app.dto.AdminFullPropertyDto;
import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.PropertyInfoDto;
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
		propertyToReturn.setPropertyInfo(propertyInfo.isHasGarage(), propertyInfo.isHasElevator(),
				propertyInfo.isHasPool(), propertyInfo.getArea(), propertyInfo.getNrBathrooms(),
				propertyInfo.getNrBedrooms(), propertyInfo.getFloorNumber());
		return propertyToReturn;
	}

	public static PropertyDto singleToDto(PropertyEntity property) {
		PropertyDto propertyToReturn = new PropertyDto();
		propertyToReturn.setPropertyId(property.getPropertiesId());
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
		propertyInfoToReturn.setHasGarage(propertyInfo.getPropertyInfo().isHasGarage());
		propertyInfoToReturn.setHasElevator(propertyInfo.getPropertyInfo().isHasElevator());
		propertyInfoToReturn.setHasPool(propertyInfo.getPropertyInfo().isHasPool());
		propertyInfoToReturn.setFloorNumber(propertyInfo.getPropertyInfo().getFloorNumber());
		propertyInfoToReturn.setArea(propertyInfo.getPropertyInfo().getArea());
		propertyInfoToReturn.setNrBathrooms(propertyInfo.getPropertyInfo().getNrBathrooms());
		propertyInfoToReturn.setNrBedrooms(propertyInfo.getPropertyInfo().getNrBedrooms());
		return propertyInfoToReturn;
	}


	public static PropertyInfoEntity adminPropertyToInfoEntity(PropertyInfoDto propertyInfo) {
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

	public static PropertyEntity adminPropertyEntityForCreate(AdminFullPropertyDto property, UserEntity owner,
			PropertyTypeEntity propertyType, LocationEntity location, PropertyInfoEntity propertyInfo) {
		PropertyEntity toReturn = new PropertyEntity();
		toReturn.setPropertiesId(null);
		toReturn.setOwner(owner);
		toReturn.setDescription(property.getDescription());
		toReturn.setRentingPrice(property.getRentingPrice());
		toReturn.setSellingPrice(property.getSellingPrice());
		toReturn.setCategory(PropertyCategory.valueOf(property.getCategory()));
		toReturn.setPropertyType(propertyType);
		toReturn.setPropertyLocation(location);
		toReturn.setPropertyInfo(propertyInfo);
		return toReturn;
	}
}
