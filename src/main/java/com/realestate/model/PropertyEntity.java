package com.realestate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="property")
@Data
@NoArgsConstructor
public class PropertyEntity implements Serializable {
	private static final long serialVersionUID=1L;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="renting_price")
	private int renting_price;
	
	@Column(name="selling_price")
	private int selling_price;
	
	@Column(name="category")
	private String category;
	
	@ManyToOne
	@JoinColumn(name="property_type", referencedColumnName ="id")
	private PropertyTypeEntity propertyType;
	
	@OneToOne
	@JoinColumn(name="location", referencedColumnName = "id")
	private LocationEntity propertyLocation;
	
	

	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "property_info", referencedColumnName = "property_info_id")
	private PropertyInfoEntity propertyInfo;
	
	

}
