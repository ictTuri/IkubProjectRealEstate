package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.LocationConverter;
import com.realestate.app.dto.LocationDto;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.LocationRepository;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	private static final Logger logger = LogManager.getLogger(LocationServiceImpl.class);
	
	PropertyRepository propertyRepo;
	LocationRepository locationRepo;

	@Autowired
	public LocationServiceImpl(PropertyRepository propertyRepo, LocationRepository locationRepo) {
		super();
		this.propertyRepo = propertyRepo;
		this.locationRepo = locationRepo;
	}
	
	// DISPLAY ALL LOCATIONS
	@Override
	public List<LocationEntity> allLocations() {
		return locationRepo.getAllLocations();
	}

	// DISPLAY LOCATION BY ID
	@Override
	public LocationEntity locationById(int id) {
		LocationEntity location = locationRepo.getLocationById(id);
		if (location != null) {
			
			//LOGGING
			logger.info("Showing location by id, location {}",location);
			
			return location;
		} else {
			throw new MyExcMessages("No such location with given Id !");
		}
	}

	// LOCATION INSERT
	@Override
	public LocationEntity addLocation(LocationDto location) {
		LocationEntity locationToAdd = LocationConverter.toEntity(location);
		locationRepo.insertLocation(locationToAdd);
		
		//LOGGING
		logger.info("Inserting location: {}",locationToAdd);
		
		return locationToAdd;
	}

	// LOCATION UPDATE
	@Override
	public LocationEntity updateLocation(LocationDto location, int id) {
		LocationEntity locationToUpdate = locationRepo.getLocationById(id);
		if (locationToUpdate != null) {
			locationToUpdate.setCityName(location.getCityName());
			locationToUpdate.setStreetName(location.getStreetName());
			locationToUpdate.setDescription(location.getDescription());
			locationToUpdate.setZipCode(location.getZipCode());
			locationRepo.updateLocation(locationToUpdate);
			
			//LOGGING
			logger.info("Updated location: {}",locationToUpdate);
			
			return locationToUpdate;
		} else {
			throw new MyExcMessages("No Location with such Id / Please check again");
		}
	}

	// LOCATION DELETION
	@Override
	public void deleteLocation(int id) {
		LocationEntity locationToDelete = locationRepo.getLocationById(id);
		if (locationToDelete != null) {
			if (propertyRepo.existLocationInProperty(locationToDelete)) {
				throw new MyExcMessages("Location attached to a property, can not delete");
			} else {
				locationRepo.deleteLocation(locationToDelete);
				
				//LOGGING
				logger.info("Deleted location: {}",locationToDelete);
			}
		} else {
			throw new MyExcMessages("No locatin on given id or already deleted");
		}
	}
	
}
