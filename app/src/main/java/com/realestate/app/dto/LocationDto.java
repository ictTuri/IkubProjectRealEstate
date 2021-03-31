package com.realestate.app.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocationDto {

	@NotBlank(message = "City Name is mandatory")
	private String cityName;
	
	@NotBlank(message = "Street Name is mandatory")
	private String streetName;
	
	@NotNull(message = "Zip Code is mandatory")
	@Min(value = 1000, message="Smallest Albanian Zip is 1000 !")
	@Max(value = 10000, message="Largest Albanian Zip is 10000 !")
	private int zipCode;
	
	@NotBlank(message = "Description is mandatory")
	private String description;

	private int version;


	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
