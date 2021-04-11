package com.realestate.app.service;

import java.util.List;

import javax.validation.Valid;

import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;

public interface UserPropertyService {
	//SHOW LOGGED IN OWNER PROPERTIES
	List<PropertyDto> showAllMyProperties();

	//INSERT NEW PROPERTY
	PropertyDto insertMyProperty(@Valid FullPropertyDto property);
	
	//UPDATE LOGGED IN OWNER PROPERTIES
	PropertyDto updateMyProperty(@Valid FullPropertyDto property, int id);

	//DELETE LOGGEN IN OWNER PROPERTY
	void deleteMyProperty(int id);



}
