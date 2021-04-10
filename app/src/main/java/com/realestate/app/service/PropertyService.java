package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.filter.PropertyFilter;

public interface PropertyService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<PropertyEntity> getAllProperties(PropertyFilter filter);
	UserEntity propertyOwner(int id);
	PropertyEntity propertyById(int id);
	
	//FUNCTIONS TO STORE DATA TO DATABASE 
	PropertyEntity addProperty(FullPropertyDto property);
	
	//FUNCTIONS TO UPDATE DATA ON DATABASE 
	PropertyEntity updateProperty(FullPropertyDto user, int id);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	PropertyEntity deleteProperty(int id);
}
