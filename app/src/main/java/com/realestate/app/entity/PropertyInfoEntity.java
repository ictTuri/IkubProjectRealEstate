package com.realestate.app.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "property_info")
@Data
@NoArgsConstructor
public class PropertyInfoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_info_id")
	private Integer propertyInfoId;
	
	@Column(name = "has_garage")
	private boolean hasGarage;
	
	@Column(name = "has_elevator")
	private boolean hasElevator;
	
	@Column(name = "has_pool")
	private boolean hasPool;
	
	@Column(name = "floor_number")
	private Integer floorNumber;
	
	@Column(name = "nr_bathrooms")
	private Integer nrBathrooms;
	
	@Column(name = "nr_bedrooms")
	private Integer nrBedrooms;
	
	@Column(name = "property_area")
	private Integer area;

	@Version
	@Column(name = "version")
	private int version;

	public Integer getPropertyInfoId() {
		return propertyInfoId;
	}

	public void setPropertyInfoId(Integer propertyInfoId) {
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

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Integer getNrBathrooms() {
		return nrBathrooms;
	}

	public void setNrBathrooms(Integer nrBathrooms) {
		this.nrBathrooms = nrBathrooms;
	}

	public Integer getNrBedrooms() {
		return nrBedrooms;
	}

	public void setNrBedrooms(Integer nrBedrooms) {
		this.nrBedrooms = nrBedrooms;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
