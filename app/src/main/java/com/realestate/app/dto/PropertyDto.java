package com.realestate.app.dto;


public class PropertyDto {

	private int propertiesId;
	
	private UserDto owner;
	
	private String description;
	
	private int rentingPrice;
	
	private int sellingPrice;

	private String category;

	private PropertyTypeDto propertyType;
	
	private LocationDto propertyLocation;
	
	private PropertyInfoDto propertyInfo;	
	
	private int version;

	public Integer getPropertiesId() {
		return propertiesId;
	}

	public void setPropertiesId(Integer propertiesId) {
		this.propertiesId = propertiesId;
	}

	public UserDto getOwner() {
		return owner;
	}

	public void setOwner(UserDto owner) {
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

	public PropertyTypeDto getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyTypeDto propertyType) {
		this.propertyType = propertyType;
	}

	public LocationDto getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(LocationDto propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public PropertyInfoDto getPropertyInfo() {
		return propertyInfo;
	}

	public void setPropertyInfo(PropertyInfoDto propertyInfo) {
		this.propertyInfo = propertyInfo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
