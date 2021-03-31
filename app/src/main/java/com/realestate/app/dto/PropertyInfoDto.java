package com.realestate.app.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PropertyInfoDto {

	private int propertyInfoId;

	private boolean hasGarage;

	private boolean hasElevator;

	private boolean hasPool;
	
	@NotBlank(message = "Floor number is mandatory")
	@Min(value = -10, message = "Minimum value for floor numbers is minus 10 !")
	private int floorNumber;
	
	@NotBlank(message = "Number of Bathrooms is mandatory")
	@Min(value = 0, message = "Minimum value for bathroom numbers is zero !")
	private int nrBathrooms;

	@NotBlank(message = "Number of Bedrooms is mandatory")
	@Min(value = 0, message = "Minimum value for bedroom numbers is zero !")
	private int nrBedrooms;

	@NotBlank(message = "Area is mandatory")
	@Min(value = 25, message = "Minimum value for area is twenty-five !")
	private int area;

	private int version;

	public int getPropertyInfoId() {
		return propertyInfoId;
	}

	public void setPropertyInfoId(int propertyInfoId) {
		this.propertyInfoId = propertyInfoId;
	}

	public boolean isHasGarage() {
		return hasGarage;
	}

	public void setHasGarage(boolean hasGarage) {
		this.hasGarage = hasGarage;
	}

	public boolean isHasElevator() {
		return hasElevator;
	}

	public void setHasElevator(boolean hasElevator) {
		this.hasElevator = hasElevator;
	}

	public boolean isHasPool() {
		return hasPool;
	}

	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getNrBathrooms() {
		return nrBathrooms;
	}

	public void setNrBathrooms(int nrBathrooms) {
		this.nrBathrooms = nrBathrooms;
	}

	public int getNrBedrooms() {
		return nrBedrooms;
	}

	public void setNrBedrooms(int nrBedrooms) {
		this.nrBedrooms = nrBedrooms;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
