package com.realestate.app.converter;

import java.util.ArrayList;
import java.util.List;

import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.UserEntity;

public class PropertyConverter {

	private PropertyConverter() {
	}

	public static PropertyDto toDto(PropertyEntity property) {
		PropertyDto propertyToReturn = new PropertyDto();
		propertyToReturn.setOwner(UserConverter.toDto(property.getOwner()));
		propertyToReturn.setDescription(property.getDescription());
		propertyToReturn.setRentingPrice(property.getRentingPrice());
		propertyToReturn.setSellingPrice(property.getSellingPrice());
		propertyToReturn.setCategory(property.getCategory());
		propertyToReturn.setPropertyType(PropertyTypeConverter.toDto(property.getPropertyType()));
		return propertyToReturn;
	}

	public static List<PropertyDto> toDto(List<PropertyEntity> property) {
		List<PropertyDto> propertyToReturn = new ArrayList<>();
		for (PropertyEntity ue : property) {
			propertyToReturn.add(toDto(ue));
		}
		return propertyToReturn;
	}

	public static PropertyEntity toEntity(PropertyDto property) {
		PropertyEntity propertyToReturn = new PropertyEntity();
		propertyToReturn.setOwner(UserConverter.toEntity(property.getOwner()));
		propertyToReturn.setDescription(property.getDescription());
		propertyToReturn.setRentingPrice(property.getRentingPrice());
		propertyToReturn.setSellingPrice(property.getSellingPrice());
		propertyToReturn.setCategory(property.getCategory());
		propertyToReturn.setPropertyType(PropertyTypeConverter.toEntity(property.getPropertyType()));
		return propertyToReturn;
	}

	public static PropertyEntity toEntityForCreate(PropertyDtoForCreate dto, UserEntity owner,
			PropertyTypeEntity propertyType, LocationEntity location, PropertyInfoEntity propertyInfo) {
		PropertyEntity toReturn = new PropertyEntity();
		toReturn.setPropertiesId(null);
		toReturn.setOwner(owner);
		toReturn.setDescription(dto.getDescription());
		toReturn.setRentingPrice(dto.getRentingPrice());
		toReturn.setSellingPrice(dto.getSellingPrice());
		toReturn.setCategory(dto.getCategory());
		toReturn.setPropertyType(propertyType);
		toReturn.setPropertyLocation(location);
		toReturn.setPropertyInfo(propertyInfo);
		return toReturn;
	}
}
