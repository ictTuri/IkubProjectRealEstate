package com.realestate.app.dto;

import javax.validation.constraints.Min;
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

	private boolean hasGarage;

	private boolean hasElevator;

	private boolean hasPool;
	
	@NotNull(message = "Floor number is mandatory")
	@Min(value = -10, message = "Minimum value for floor numbers is minus 10 !")
	private int floorNumber;
	
	@NotNull(message = "Number of Bathrooms is mandatory")
	@Min(value = 0, message = "Minimum value for bathroom numbers is zero !")
	private int nrBathrooms;

	@NotNull(message = "Number of Bedrooms is mandatory")
	@Min(value = 0, message = "Minimum value for bedroom numbers is zero !")
	private int nrBedrooms;

	@NotNull(message = "Area is mandatory")
	@Min(value = 25, message = "Minimum value for area is twenty-five !")
	private int area;
}
