package com.realestate.app.repository;

import java.util.List;

import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.filter.PropertyFilter;

public interface PropertyRepository {
	// RETRIEVE ALL PROPERTIES FROM DATABASE
	List<PropertyEntity> getAllProperties(PropertyFilter filter);
	PropertyEntity getPropertiesById(Integer propertiesId);

	// PERSIST A NEW PROPERTY ON DATABASE
	void insertProperties(PropertyEntity property);

	// UPDATE PROPERTY BY ID ON DATABASE
	PropertyEntity updateProperty(PropertyEntity property);

	// DELETE PROPERTY FROM DATABASE
	void deleteProperty(PropertyEntity property);
	
	// HELPING METHODS DOWN HERE
	boolean existLocationInProperty(LocationEntity id);
	boolean existPropertyInfoAnotherProperty(int id, int info);
	boolean existPropertyInfo(int id);
	List<PropertyEntity> getPropertiesByOwner(UserEntity user);
	UserEntity getPropertyOwner(int id);
}
