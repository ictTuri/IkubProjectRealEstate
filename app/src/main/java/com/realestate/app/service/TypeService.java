package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.PropertyTypeDto;

public interface TypeService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
		List<PropertyTypeDto> allPropertyTypes();
		
		PropertyTypeDto propertyTypeById(int id);
		
		//FUNCTIONS TO STORE DATA TO DATABASE 
		PropertyTypeDto addPropertyType(PropertyTypeDto propertyType);
		
		//FUNCTIONS TO UPDATE DATA ON DATABASE 
		PropertyTypeDto updatePropertyType(PropertyTypeDto propertyType, int id);
		
		//FUNCTIONS TO DELETE DATA FROM DATABASE 
		void deletePropertyType(int id);
}
