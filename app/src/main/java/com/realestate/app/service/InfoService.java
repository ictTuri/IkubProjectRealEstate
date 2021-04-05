package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.entity.PropertyInfoEntity;

public interface InfoService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<PropertyInfoEntity> allPropertyInfos();
	
	PropertyInfoEntity propertyInfoById(int id);
	
	//FUNCTIONS TO STORE DATA TO DATABASE 
	PropertyInfoEntity addPropertyInfo(PropertyInfoDto propertyInfo);
	
	//FUNCTIONS TO UPDATE DATA ON DATABASE 
	PropertyInfoEntity updatePropertyInfo(PropertyInfoDto propertyInfo, int id);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	void deletePropertyInfo(int id);

}
