package com.realestate.app.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "property_info")
@Data
public class PropertyInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_info_id")
	private int propertyInfoId;
	@Column(name = "has_garage")
	private boolean hasGarage;
	@Column(name = "has_elevator")
	private boolean hasElevator;
	@Column(name = "has_pool")
	private boolean hasPool;
	@Column(name = "floor_number")
	private int floorNumber;
	@Column(name = "nr_bathrooms")
	private int nrBathrooms;
	@Column(name = "nr_bedrooms")
	private int nrBedrooms;
	@Column(name = "area")
	private int area;
}
