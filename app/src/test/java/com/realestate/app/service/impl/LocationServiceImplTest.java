package com.realestate.app.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.realestate.app.dto.LocationDto;
import com.realestate.app.service.LocationService;

class LocationServiceImplTest {

	private LocationService locationService;

	@BeforeEach
	public void initializeBeforeAllTestMethods() {
		locationService = Mockito.mock(LocationService.class);
	}
	

	@Test
	void whenGetById_thenReturnCorrectObject() {
		LocationDto location = new LocationDto("Tirane", "Street", 1000, "Description", 0);

		when(locationService.locationById(1)).thenReturn(location);

		LocationDto locationToGet = locationService.locationById(1);
		assertNotNull(locationToGet);
		assertEquals("Tirane", locationToGet.getCityName());
	}

	@Test
	void whenGetList_thenReturnCorrectSIze() {
		List<LocationDto> locations = new ArrayList<LocationDto>();
		locations.add(new LocationDto("Tirane", "Street One", 1000, "Description One", 0));
		locations.add(new LocationDto("Tirane", "Street Two", 1000, "Description Two", 3));
		locations.add(new LocationDto("Durres", "Street One", 2000, "Description One", 0));

		when(locationService.allLocations()).thenReturn(locations);

		List<LocationDto> locationToGet = locationService.allLocations();
		assertNotNull(locationToGet);
		assertEquals("Tirane", locationToGet.get(0).getCityName());
		assertEquals(3, locationToGet.size());
		assertEquals(locationToGet.size(),locations.size());
		assertEquals("Durres", locationToGet.get(2).getCityName());
	}
	
	@Test
	void whenInsertLocation_thenReturnCorrectLocation() {
		LocationDto location = new LocationDto("Tirane", "Street One", 1000, "Description One", 0);

		when(locationService.addLocation(location)).thenReturn(location);

		LocationDto locationToGet = locationService.addLocation(location);
		assertNotNull(locationToGet);
		assertEquals(0, locationToGet.getVersion());
		assertEquals(locationToGet.getDescription(), location.getDescription());
		assertEquals("Street One", locationToGet.getStreetName());
	}
	
	@Test
	void whenUpdateLocation_thenReturnUpdatedLocation() {
		LocationDto location = new LocationDto("Tirane", "Street One", 1000, "Description One", 0);
		location.setCityName("Durres");

		when(locationService.updateLocation(location, 1)).thenReturn(location);
		
		LocationDto locationToGet = locationService.updateLocation(location,1);
		assertNotNull(locationToGet);
		assertEquals(0, locationToGet.getVersion());
		assertEquals(locationToGet.getDescription(), location.getDescription());
		assertEquals("Durres",locationToGet.getCityName());
	}

}
