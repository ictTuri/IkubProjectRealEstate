package com.realestate.app.repository;

import com.realestate.app.entity.PropertyInfoEntity;

public interface InfoRepository {
	//GET INFO BY ID
	PropertyInfoEntity getPropertyInfoById(Integer propertyInfoId);
	
	// INSERT NEW INFO INTO DATABASE
	void insertPropertyInfo(PropertyInfoEntity propertyInfo);
	
	// UPDATE INFO ON DATABASE
	PropertyInfoEntity updatePropertyInfo(PropertyInfoEntity propertyInfo);
	
	// DELETE INFO FROM DATABASE
	void deletePropertyInfo(PropertyInfoEntity propertyInfo);
}
