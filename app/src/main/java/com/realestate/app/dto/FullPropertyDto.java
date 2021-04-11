package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
public class FullPropertyDto {
	
	@NotNull(message = "Owner Id is mandatory !")
	private int owner;

	@NotBlank(message = "Description is mandatory !")
	private String description;

	private int rentingPrice;

	private int sellingPrice;

	@NotBlank(message = "Need a value of: REZIDENTIAL,COMMERTIAL,LAND")
	private String category;

	@NotNull(message = "Property type Id is mandatory !")
	private int propertyType;

	@NotNull(message = "Property Location Id is mandatory !")
	private int location;

	private PropertyInfoDto propertyInfo;

	public void setPropertyInfo(boolean hasGarage, boolean hasElevator, boolean hasPool, Integer area,
			Integer nrBathrooms, Integer nrBedrooms, Integer floorNumber) {
	}
}
