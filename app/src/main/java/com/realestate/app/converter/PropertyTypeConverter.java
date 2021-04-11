package com.realestate.app.converter;

import java.util.ArrayList;
import java.util.List;

import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.enums.PropertyTypeName;

public class PropertyTypeConverter {

	private PropertyTypeConverter() {
	}

	public static PropertyTypeDto toDto(PropertyTypeEntity propertyType) {
		PropertyTypeDto propertyTypeToReturn = new PropertyTypeDto();
		propertyTypeToReturn.setPropertyTypeName(propertyType.getPropertyTypeName().toString());
		propertyTypeToReturn.setPropertyTypeDesc(propertyType.getPropertyTypeDesc());
		return propertyTypeToReturn;
	}
	
	public static List<PropertyTypeDto> toDto(List<PropertyTypeEntity> propertyType) {
		List<PropertyTypeDto> toReturn = new ArrayList<>();
		for(PropertyTypeEntity ue : propertyType) {
			toReturn.add(toDto(ue));
		}
		return toReturn;
	}

	public static PropertyTypeEntity toEntity(PropertyTypeDto propertyType) {
		PropertyTypeEntity propertyTypeToReturn = new PropertyTypeEntity();
		propertyTypeToReturn.setPropertyTypeName(PropertyTypeName.valueOf(propertyType.getPropertyTypeName()));
		propertyTypeToReturn.setPropertyTypeDesc(propertyType.getPropertyTypeDesc());
		return propertyTypeToReturn;
	}
}
