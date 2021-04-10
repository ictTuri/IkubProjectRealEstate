package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;


import lombok.Data;

@Data
public class PropertyTypeDto {

	@NotBlank(message = "Need a value of: HOME, BARACS, APARTAMENT, DUPLEX, SIMPLEX, FARM, VILLE, LAND,F OREIGN_OBJECT")
	private String propertyTypeName;
	
	@NotBlank(message = "Property Type Description is mandatory")
	private String propertyTypeDesc;

}
