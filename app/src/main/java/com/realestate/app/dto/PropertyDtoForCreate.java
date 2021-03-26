package com.realestate.app.dto;

public class PropertyDtoForCreate {
	
	private int owner;
	
	private String description;
	
	private int rentingPrice;
	
	private int sellingPrice;

	private String category;

	private int propertyType;
	
	private int propertyLocation;
	
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
