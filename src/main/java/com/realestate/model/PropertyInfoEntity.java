package com.realestate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="property_info")
@Entity
public class PropertyInfoEntity implements Serializable {
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="area")
	private int area;
	
	@Column(name="has_garage")
	private boolean has_garage;
	
	@Column(name="has_elevator")
	private boolean has_elevator;
	
	@Column(name="has_pool")
	private boolean has_pool;
	
	@Column(name="floor_number")
	private int floor_number;
	
	@Column(name="bathroom_number")
	private int bathroom_number;
	
	@Column(name="bedroom_number")
	private int bedroom_number;
	
	
	

}
