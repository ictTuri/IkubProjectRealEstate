package com.realestate.app.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "properties")
@Data
public class Property {
	
	public Property() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "properties_id")
	private Integer propertiesId;
	@OneToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	private User ownerId;
	@Column(name = "description")
	private String description;
	@Column(name = "renting_price")
	private Integer rentingPrice;
	@Column(name = "selling_price")
	private Integer sellingPrice;
	@Column(name = "category")
	private String category;
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "property_location_id", referencedColumnName = "location_id")
	private Location propertyLocationId;
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "property_info_id", referencedColumnName = "property_info_id")
	private PropertyInfo propertyInfoId;	
	
}
