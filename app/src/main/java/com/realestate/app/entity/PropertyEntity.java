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
	
	@Version
	@Column(name = "version")
	private int version;

	public Integer getPropertiesId() {
		return propertiesId;
	}

	public void setPropertiesId(Integer propertiesId) {
		this.propertiesId = propertiesId;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRentingPrice() {
		return rentingPrice;
	}

	public void setRentingPrice(Integer rentingPrice) {
		this.rentingPrice = rentingPrice;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public PropertyTypeEntity getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyTypeEntity propertyType) {
		this.propertyType = propertyType;
	}

	public LocationEntity getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(LocationEntity propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public PropertyInfoEntity getPropertyInfo() {
		return propertyInfo;
	}

	public void setPropertyInfo(PropertyInfoEntity propertyInfo) {
		this.propertyInfo = propertyInfo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
