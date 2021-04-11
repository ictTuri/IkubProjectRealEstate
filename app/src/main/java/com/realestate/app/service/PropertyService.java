package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.UserDto;
import com.realestate.app.filter.PropertyFilter;

public interface PropertyService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<PropertyDto> getAllProperties(PropertyFilter filter);
	UserDto propertyOwner(int id);
	PropertyDto propertyById(int id);
	
	//FUNCTIONS TO STORE DATA TO DATABASE 
	PropertyDto addProperty(FullPropertyDto property);
	
	//FUNCTIONS TO UPDATE DATA ON DATABASE 
	PropertyDto updateProperty(FullPropertyDto user, int id);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	void deleteProperty(int id);
}
