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
}
