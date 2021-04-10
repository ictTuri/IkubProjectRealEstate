package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.entity.PropertyTypeEntity;

public interface TypeService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
		List<PropertyTypeEntity> allPropertyTypes();
		
		PropertyTypeEntity propertyTypeById(int id);
		
		//FUNCTIONS TO STORE DATA TO DATABASE 
		PropertyTypeEntity addPropertyType(PropertyTypeDto propertyType);
		
		//FUNCTIONS TO UPDATE DATA ON DATABASE 
		PropertyTypeEntity updatePropertyType(PropertyTypeDto propertyType, int id);
		
		//FUNCTIONS TO DELETE DATA FROM DATABASE 
		void deletePropertyType(int id);
}
