package com.realestate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class LocationEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="city_name")
	private String city_name;
	
	@Column(name="street_name")
	private String street_name;
	
	@Column(name="zip_code")
	private int zip_code;
	
	@Column(name="description")
	private String decription;
	
}
