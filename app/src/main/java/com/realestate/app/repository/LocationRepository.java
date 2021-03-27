package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
	private static final String GET_LOCATION_BY_ID = "FROM LocationEntity l WHERE l.locationId = :id";

	// LOCATIONS
	public List<LocationEntity> getAllLocations() {
		return em.createQuery(GET_ALL_LOCATIONS, LocationEntity.class).getResultList();
	}

	public LocationEntity getLocationById(Integer id) {
		TypedQuery<LocationEntity> query = em.createQuery(GET_LOCATION_BY_ID, LocationEntity.class).setParameter("id",
				id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
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
	public boolean existLocation(int id) {
		TypedQuery<LocationEntity> query = em.createQuery(GET_LOCATION_BY_ID, LocationEntity.class).setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		}catch(IndexOutOfBoundsException e) {
			return false;
		}
		
	}
}
