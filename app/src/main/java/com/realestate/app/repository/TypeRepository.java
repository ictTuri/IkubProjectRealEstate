package com.realestate.app.repository;

import java.util.List;

import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.enums.PropertyTypeName;

public interface TypeRepository {
	// GET INFOS FROM DATABASE
	List<PropertyTypeEntity> getAllPropertyTypes();
	PropertyTypeEntity getPropertyTypeById(Integer propertyTypeId);
	
	// INSERT NEW INFO INTO DATABASE
	void insertPropertyType(PropertyTypeEntity propertyType);
	
	// UPDATE INFO ON DATABASE
	PropertyTypeEntity updatePropertyType(PropertyTypeEntity propertyType);
	
	// DELETE INFO FROM DATABASE
	void deletePropertyType(PropertyTypeEntity propertyType);
	
	// HELPING METHODS 
	boolean existPropertyType(int propertyTypeId);
	boolean existPropertyType(PropertyTypeName propertyTypeNameEnum, String desc);
	boolean existPropertyTypeInProperties(PropertyTypeEntity id);
}
