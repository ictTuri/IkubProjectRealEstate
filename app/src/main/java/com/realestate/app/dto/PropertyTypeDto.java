package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;


import com.realestate.app.entity.enums.PropertyTypeNameEnum;

import lombok.Data;

@Data
public class PropertyTypeDto {

	private PropertyTypeNameEnum propertyTypeName;
	
	@NotBlank(message = "Property Type Description is mandatory")
	private String propertyTypeDesc;

}
