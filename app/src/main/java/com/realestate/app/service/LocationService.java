package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.LocationDto;

public interface LocationService {
		//FUNCTIONS TO GET DATA FROM DATABASE 
		List<LocationDto> allLocations();
		LocationDto locationById(int id);
		
		//FUNCTIONS TO STORE DATA TO DATABASE 
		LocationDto addLocation(LocationDto location);
		
		//FUNCTIONS TO UPDATE DATA ON DATABASE 
		LocationDto updateLocation(LocationDto location, int id);
		
		//FUNCTIONS TO DELETE DATA FROM DATABASE 
		void deleteLocation(int id);		
}
