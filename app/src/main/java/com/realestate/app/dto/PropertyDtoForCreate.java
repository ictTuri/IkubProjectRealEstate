package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class PropertyDtoForCreate {

	@NotNull(message = "Owner Id is mandatory !")
	private int owner;

	@NotBlank(message = "Description is mandatory !")
	private String description;

	private int rentingPrice;

	private int sellingPrice;

	private String category;

	@NotNull(message = "Property type Id is mandatory !")
	private int propertyType;

	@NotNull(message = "Property Location Id is mandatory !")
	private int propertyLocation;

	@NotNull(message = "Property info Id is mandatory !")
	private int propertyInfo;

	private int version;
}
