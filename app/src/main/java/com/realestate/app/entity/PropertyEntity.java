package com.realestate.app.entity;

import java.io.Serializable;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROPERTIES")
@Data
@NoArgsConstructor
public class PropertyEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "properties_id")
	private Integer propertiesId;
	
	@OneToOne
	@JoinColumn(name = "owner", referencedColumnName = "user_id")
	private UserEntity owner;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "renting_price")
	private Integer rentingPrice;
	
	@Column(name = "selling_price")
	private Integer sellingPrice;
	
	@Column(name = "category")
	private String category;
	
	@ManyToOne
	@JoinColumn(name = "property_type", referencedColumnName = "property_type_id")
	private PropertyTypeEntity propertyType;
	
	@ManyToOne
	@JoinColumn(name = "property_location", referencedColumnName = "location_id")
	private LocationEntity propertyLocation;
	
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "property_info", referencedColumnName = "property_info_id")
	private PropertyInfoEntity propertyInfo;	
	
}
