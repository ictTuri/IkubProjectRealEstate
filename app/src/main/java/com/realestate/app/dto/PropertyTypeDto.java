package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PropertyTypeDto {

	@NotBlank(message = "Property Type Name is mandatory")
	private String propertyTypeName;
	
	@NotBlank(message = "Property Type Description is mandatory")
	private String propertyTypeDesc;

}
