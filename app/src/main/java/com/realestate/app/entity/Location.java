package com.realestate.app.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "locations")
@Data
public class Location {
	
	public Location() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Integer locationId;
	@Column(name = "city_name")
	private String cityName;
	@Column(name = "street_name")
	private String streetName;
	@Column(name = "zip_code")
	private Integer zipCode;
	@Column(name = "description")
	private String description;
}
