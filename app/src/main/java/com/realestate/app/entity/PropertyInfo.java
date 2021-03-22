package com.realestate.app.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "property_info")
@Data
public class PropertyInfo {
	
	public PropertyInfo() {
		super();
	}
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
	@Column(name = "area")
	private Integer area;
}
