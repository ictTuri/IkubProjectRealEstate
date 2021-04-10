package com.realestate.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.realestate.app.entity.enums.PropertyTypeNameEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROPERTY_TYPE")
@Data
@NoArgsConstructor
public class PropertyTypeEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_type_id")
	private Integer propertyTypeId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "property_type_name")
	private PropertyTypeNameEnum propertyTypeName;
	
	@Column(name = "property_type_desc")
	private String propertyTypeDesc;
}
