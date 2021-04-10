package com.realestate.app.dto;

import com.realestate.app.entity.enums.PropertyCategoryEnum;
import com.realestate.app.entity.enums.PropertyTypeNameEnum;

import lombok.Data;

@Data
public class PropertyDto {

	private String description;

	private int rentingPrice;

	private int sellingPrice;

	private PropertyCategoryEnum category;

	private PropertyTypeNameEnum propertyType;
	
	private String location;
	
	private PropertyInfoDto propertyInfo;
}
