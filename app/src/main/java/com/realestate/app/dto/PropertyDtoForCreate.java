package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRentingPrice() {
		return rentingPrice;
	}

	public void setRentingPrice(int rentingPrice) {
		this.rentingPrice = rentingPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}

	public int getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(int propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public int getPropertyInfo() {
		return propertyInfo;
	}

	public void setPropertyInfo(int propertyInfo) {
		this.propertyInfo = propertyInfo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
