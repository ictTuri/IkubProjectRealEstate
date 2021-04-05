package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;

@Repository
public class PropertyRepository {
	EntityManager em;

	public PropertyRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_PROPERTIES = "FROM PropertyEntity";

	private static final String GET_PROPERTY_BY_ID = "FROM PropertyEntity pe WHERE pe.propertiesId = :id";
	private static final String GET_PROPERTY_INFO_BY_ID = "FROM PropertyInfoEntity pie WHERE pie.propertyInfoId = :id";
	private static final String CHECK_PROPERTY_INFO_TAKEN = "FROM PropertyEntity pe WHERE pe.propertiesId != :id and pe.propertyInfo = :info";
	private static final String CHECK_LOCATION_INTO_PROPERTY = "FROM PropertyEntity pe WHERE pe.propertyLocation = :id";
	
	// RETRIEVE OPERATIONS DOWN HERE
	// PROPERTIES
	public List<PropertyEntity> getAllProperties() {
		return em.createQuery(GET_ALL_PROPERTIES, PropertyEntity.class).getResultList();
	}

	public PropertyEntity getPropertiesById(Integer id) {
		TypedQuery<PropertyEntity> query = em.createQuery(GET_PROPERTY_BY_ID, PropertyEntity.class).setParameter("id",
				id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// INSERT OPERATIONS DOWN HERE
	public void insertProperties(PropertyEntity property) {
		em.persist(property);
	}

	// UPDATE OPERATIONS DOWN HERE
	public PropertyEntity updateProperty(PropertyEntity property) {
		return em.merge(property);
	}

	// DELETE OPERATIONS DOWN HERE
	public void deleteProperty(PropertyEntity property) {
		em.remove(property);
	}

	public boolean existLocationInProperty(LocationEntity id) {
		TypedQuery<PropertyEntity> query = em.createQuery(CHECK_LOCATION_INTO_PROPERTY, PropertyEntity.class)
				.setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	public boolean existPropertyInfoAnotherProperty(int id, int info) {
		TypedQuery<PropertyTypeEntity> query = em.createQuery(CHECK_PROPERTY_INFO_TAKEN, PropertyTypeEntity.class)
				.setParameter("id", id).setParameter("info", info);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	public boolean existPropertyInfo(int id) {
		TypedQuery<PropertyInfoEntity> query = em.createQuery(GET_PROPERTY_INFO_BY_ID, PropertyInfoEntity.class)
				.setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}


}
