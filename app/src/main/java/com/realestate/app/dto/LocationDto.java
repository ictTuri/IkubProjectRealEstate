package com.realestate.app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

	@NotBlank(message = "City Name is mandatory")
	private String cityName;

	@NotBlank(message = "Street Name is mandatory")
	private String streetName;

	@NotNull(message = "Zip Code is mandatory")
	@Min(value = 1000, message = "Smallest Albanian Zip is 1000 !")
	@Max(value = 10000, message = "Largest Albanian Zip is 10000 !")
	private int zipCode;

	@NotBlank(message = "Description is mandatory")
	private String description;
}
