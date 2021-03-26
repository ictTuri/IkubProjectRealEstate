package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.LocationDto;
import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;

public interface PropertyService {
		//FUNCTIONS TO GET DATA FROM DATABASE 
		List<PropertyEntity> allProperties();
		List<LocationEntity> allLocations();
		
		//FUNCTIONS TO STORE DATA TO DATABASE 
		PropertyEntity addProperty(PropertyDtoForCreate property);
		LocationEntity addLocation(LocationDto location);

		//FUNCTIONS TO UPDATE DATA ON DATABASE 
		PropertyEntity updateProperty(PropertyDtoForCreate user, int id);

		//FUNCTIONS TO DELETE DATA FROM DATABASE 
		PropertyEntity deleteProperty(int id);
		
}
