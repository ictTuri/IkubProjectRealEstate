package com.realestate.app.repository;

import java.util.List;

import com.realestate.app.entity.PropertyInfoEntity;

public interface InfoRepository {
	// GET INFOS FROM DATABASE
	List<PropertyInfoEntity> getAllPropertyInfos();
	PropertyInfoEntity getPropertyInfoById(Integer propertyInfoId);
	
	// INSERT NEW INFO INTO DATABASE
	void insertPropertyInfo(PropertyInfoEntity propertyInfo);
	
	// UPDATE INFO ON DATABASE
	PropertyInfoEntity updatePropertyInfo(PropertyInfoEntity propertyInfo);
	
	// DELETE INFO FROM DATABASE
	void deletePropertyInfo(PropertyInfoEntity propertyInfo);
	
	// HELPING METHODS 
	boolean existProropertyWithInfo(PropertyInfoEntity id);
}
