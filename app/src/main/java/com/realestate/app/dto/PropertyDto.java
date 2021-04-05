package com.realestate.app.dto;

import lombok.Data;

@Data
public class PropertyDto {

	private UserDto owner;

	private String description;

	private int rentingPrice;

	private int sellingPrice;

	private String category;

	private PropertyTypeDto propertyType;

	private int version;

}
