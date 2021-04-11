package com.realestate.app.repository;

import java.util.List;

import com.realestate.app.entity.LocationEntity;

public interface LocationRepository {
	// RETURN ALL LOCATION FROM DB
	List<LocationEntity> getAllLocations();
	LocationEntity getLocationById(Integer locationId);

	// INSERT NEW LOCATION ON DB
	void insertLocation(LocationEntity location);

	// UPDATE ISSUES ON DB
	LocationEntity updateLocation(LocationEntity location);

	// DELETE LOCATION FROM DB
	void deleteLocation(LocationEntity location);

	// HELPING METHODS
	boolean existLocation(int locationId);
}
