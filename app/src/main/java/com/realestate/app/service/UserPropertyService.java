package com.realestate.app.service;

import javax.validation.Valid;

import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.entity.PropertyEntity;

public interface UserPropertyService {
	//SHOW LOGGED IN OWNER PROPERTIES
	Iterable<PropertyEntity> showAllMyProperties();

	//INSERT NEW PROPERTY
	PropertyEntity insertMyProperty(@Valid FullPropertyDto property);
	
	//UPDATE LOGGED IN OWNER PROPERTIES
	PropertyEntity updateMyProperty(@Valid FullPropertyDto property, int id);

	//DELETE LOGGEN IN OWNER PROPERTY
	void deleteMyProperty(int id);



}
