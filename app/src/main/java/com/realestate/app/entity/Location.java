package com.realestate.app.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "locations")
@Data
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private int locationId;
	@Column(name = "city_name")
	private String cityName;
	@Column(name = "street_name")
	private String streetName;
	@Column(name = "zip_code")
	private int zipCode;
	@Column(name = "description")
	private String description;
}
