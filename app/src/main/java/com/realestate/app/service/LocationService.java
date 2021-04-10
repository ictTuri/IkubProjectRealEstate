package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.LocationDto;
import com.realestate.app.entity.LocationEntity;

public interface LocationService {
		//FUNCTIONS TO GET DATA FROM DATABASE 
		List<LocationEntity> allLocations();
		LocationEntity locationById(int id);
		
		//FUNCTIONS TO STORE DATA TO DATABASE 
		LocationEntity addLocation(LocationDto location);
		
		
		//FUNCTIONS TO UPDATE DATA ON DATABASE 
		LocationEntity updateLocation(LocationDto location, int id);
		
		
		//FUNCTIONS TO DELETE DATA FROM DATABASE 
		void deleteLocation(int id);	
		
}
