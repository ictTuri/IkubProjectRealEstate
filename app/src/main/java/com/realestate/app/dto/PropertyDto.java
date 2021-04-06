package com.realestate.app.dto;

import lombok.Data;

@Data
public class PropertyDto {

	private String description;

	private int rentingPrice;

	private int sellingPrice;

	private String category;

	private String propertyType;
	
	private String location;
	
	private PropertyInfoDto propertyInfo;
}
