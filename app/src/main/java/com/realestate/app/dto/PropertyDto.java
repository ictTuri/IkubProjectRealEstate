package com.realestate.app.dto;

import com.realestate.app.entity.enums.PropertyCategory;
import com.realestate.app.entity.enums.PropertyTypeName;

import lombok.Data;

@Data
public class PropertyDto {
	
	private Integer propertyId;

	private String description;

	private int rentingPrice;

	private int sellingPrice;

	private PropertyCategory category;

	private PropertyTypeName propertyType;
	
	private String location;
	
	private PropertyInfoDto propertyInfo;
}
