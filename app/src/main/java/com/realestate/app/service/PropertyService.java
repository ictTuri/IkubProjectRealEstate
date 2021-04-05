package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.UserEntity;

public interface PropertyService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<PropertyEntity> allProperties();
	UserEntity propertyOwner(int id);
	PropertyEntity propertyById(int id);
	
	//FUNCTIONS TO STORE DATA TO DATABASE 
	PropertyEntity addProperty(PropertyDtoForCreate property);
	
	//FUNCTIONS TO UPDATE DATA ON DATABASE 
	PropertyEntity updateProperty(PropertyDtoForCreate user, int id);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	PropertyEntity deleteProperty(int id);
}
