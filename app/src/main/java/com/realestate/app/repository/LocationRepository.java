package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.LocationEntity;

@Repository
public class LocationRepository {
	EntityManager em;

	public LocationRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_LOCATIONS = "FROM LocationEntity";

	// LOCATIONS
	public List<LocationEntity> getAllLocations() {
		return em.createQuery(GET_ALL_LOCATIONS, LocationEntity.class).getResultList();
	}

	public LocationEntity getLocationById(Integer locationId) {
		try {
			return em.find(LocationEntity.class, locationId);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	// INSERT OPERATIONS DOWN HERE
	public void insertLocation(LocationEntity location) {
		em.persist(location);
	}

	// UPDATE OPERATIONS DOWN HERE
	public LocationEntity updateLocation(LocationEntity location) {
		return em.merge(location);
	}

	// DELETE OPERATIONS DOWN HERE
	public void deleteLocation(LocationEntity location) {
		 em.remove(location);
	}

	//HELPING METHODS DOWN HERE
	public boolean existLocation(int locationId) {
		try {
			return em.find(LocationEntity.class, locationId)!= null;
		}catch(IllegalArgumentException e) {
			return false;
		}
		
	}

}
