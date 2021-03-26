package com.realestate.app.dto;

public class PropertyInfoDto {

	private int propertyInfoId;

	private boolean hasGarage;

	private boolean hasElevator;

	private boolean hasPool;

	private int floorNumber;

	private int nrBathrooms;

	private int nrBedrooms;

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
