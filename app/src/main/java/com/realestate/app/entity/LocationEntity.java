package com.realestate.app.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LOCATIONS")
@Data
@NoArgsConstructor
public class LocationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

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
	
	@Version
	@Column(name = "version")
	private int version;
}
