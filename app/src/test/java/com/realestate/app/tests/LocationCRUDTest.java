package com.realestate.app.tests;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.realestate.app.entity.LocationEntity;
import com.realestate.app.repository.impl.LocationRepositoryImpl;
import com.realestate.app.utils.LocationUtil;

@SpringBootTest
@Transactional
class LocationCRUDTest {

	@Autowired
	LocationRepositoryImpl locationRepo;
	
	@Test
	 void givenWrongId_whenRetrieved_thenGetNoResult() {
		int id = 2;
		
		LocationEntity loc = locationRepo.getLocationById(id);
		
		Assertions.assertNull(loc);
	}
	
	@Test
	 void givenLocation_whenSave_thenGetCreatedLocation() {
		Integer userSize = locationRepo.getAllLocations().size();
		LocationEntity location = LocationUtil.createLocationTwo();

		locationRepo.insertLocation(location);
		
		Assertions.assertEquals(userSize+1, locationRepo.getAllLocations().size());
		Assertions.assertNotNull(locationRepo.getAllLocations());
	}
	
	@Test
	 void givenLocation_whenDelete_thenGetNoResult() {
		LocationEntity location = LocationUtil.createLocationTwo();
		locationRepo.insertLocation(location);

		locationRepo.deleteLocation(location);
		
		Assertions.assertNull(locationRepo.getLocationById(2));
	}
	
	@Test
	 void givenLocation_whenUpdate_thenGetUpdatedLocation() {
		LocationEntity location = LocationUtil.createLocationOne();
		locationRepo.insertLocation(location);
		location.setCityName("Shkoder");
		
		locationRepo.updateLocation(location);
		
		Assertions.assertEquals("Shkoder", locationRepo.getLocationById(3).getCityName());
	}
}
