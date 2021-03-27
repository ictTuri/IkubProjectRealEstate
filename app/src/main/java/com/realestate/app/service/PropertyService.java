package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.LocationDto;
import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;

public interface PropertyService {
		//FUNCTIONS TO GET DATA FROM DATABASE 
		List<PropertyEntity> allProperties();
		List<LocationEntity> allLocations();
		List<PropertyTypeEntity> allPropertyTypes();
		List<PropertyInfoEntity> allPropertyInfos();
		
		PropertyEntity propertyById(int id);
		LocationEntity locationById(int id);
		PropertyTypeEntity propertyTypeById(int id);
		PropertyInfoEntity propertyInfoById(int id);
		
		//FUNCTIONS TO STORE DATA TO DATABASE 
		PropertyEntity addProperty(PropertyDtoForCreate property);
		LocationEntity addLocation(LocationDto location);
		PropertyTypeEntity addPropertyType(PropertyTypeDto propertyType);
		PropertyInfoEntity addPropertyInfo(PropertyInfoDto propertyInfo);
		
		//FUNCTIONS TO UPDATE DATA ON DATABASE 
		PropertyEntity updateProperty(PropertyDtoForCreate user, int id);
		LocationEntity updateLocation(LocationDto location, int id);
		PropertyTypeEntity updatePropertyType(PropertyTypeDto propertyType, int id);
		PropertyInfoEntity updatePropertyInfo(PropertyInfoDto propertyInfo, int id);
		
		//FUNCTIONS TO DELETE DATA FROM DATABASE 
		PropertyEntity deleteProperty(int id);
		void deleteLocation(int id);
		void deletePropertyType(int id);
		void deletePropertyInfo(int id);


		
		
}
