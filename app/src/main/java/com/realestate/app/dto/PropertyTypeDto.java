package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;

public class PropertyTypeDto {

	@NotBlank(message = "Property Type Name is mandatory")
	private String propertyTypeName;
	
	@NotBlank(message = "Property Type Description is mandatory")
	private String propertyTypeDesc;

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	public String getPropertyTypeDesc() {
		return propertyTypeDesc;
	}

	public void setPropertyTypeDesc(String propertyTypeDesc) {
		this.propertyTypeDesc = propertyTypeDesc;
	}

}
