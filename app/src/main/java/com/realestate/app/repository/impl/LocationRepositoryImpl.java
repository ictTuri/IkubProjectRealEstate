package com.realestate.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.LocationEntity;
import com.realestate.app.repository.LocationRepository;


@Repository
public class LocationRepositoryImpl implements LocationRepository{
	EntityManager em;

	public LocationRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}
	
	private static final String GET_ALL_LOCATIONS = "FROM LocationEntity";

	// LOCATIONS
	@Override
	public List<LocationEntity> getAllLocations() {
		return em.createQuery(GET_ALL_LOCATIONS, LocationEntity.class).getResultList();
	}

	@Override
	public LocationEntity getLocationById(Integer locationId) {
		try {
			return em.find(LocationEntity.class, locationId);
		} catch (IllegalArgumentException  e) {
			return null;
		}
	}

	// INSERT OPERATIONS DOWN HERE
	@Override
	public void insertLocation(LocationEntity location) {
		em.persist(location);
	}

	// UPDATE OPERATIONS DOWN HERE
	@Override
	public LocationEntity updateLocation(LocationEntity location) {
		return em.merge(location);
	}

	// DELETE OPERATIONS DOWN HERE
	@Override
	public void deleteLocation(LocationEntity location) {
		 em.remove(location);
	}

	//HELPING METHODS DOWN HERE
	@Override
	public boolean existLocation(int locationId) {
		try {
			return em.find(LocationEntity.class, locationId)!= null;
		}catch(IllegalArgumentException e) {
			return false;
		}
		
	}

}
