package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.enums.PropertyTypeNameEnum;

@Repository
public class TypeRepository {

	EntityManager em;

	public TypeRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_PROPERTY_TYPES = "FROM PropertyTypeEntity";
	private static final String GET_PROPERTY_TYPE_BY_ID = "FROM PropertyTypeEntity pte WHERE pte.propertyTypeId = :id";
	private static final String CHECK_PROPERTY_TYPE_EXIST = "FROM PropertyTypeEntity pte WHERE pte.propertyTypeName = :name and pte.propertyTypeDesc = :desc";
	private static final String CHECK_PROPERTY_TYPE_IN_PROPERTIES = "FROM PropertyEntity pe WHERE pe.propertyType = :id";

	// PROPERTY TYPES
	public List<PropertyTypeEntity> getAllPropertyTypes() {
		return em.createQuery(GET_ALL_PROPERTY_TYPES, PropertyTypeEntity.class).getResultList();
	}

	public PropertyTypeEntity getPropertyTypeById(Integer id) {
		TypedQuery<PropertyTypeEntity> query = em.createQuery(GET_PROPERTY_TYPE_BY_ID, PropertyTypeEntity.class)
				.setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void insertPropertyType(PropertyTypeEntity propertyType) {
		em.persist(propertyType);
	}

	public PropertyTypeEntity updatePropertyType(PropertyTypeEntity propertyType) {
		return em.merge(propertyType);
	}

	public void deletePropertyType(PropertyTypeEntity propertyType) {
		em.remove(propertyType);
	}

	// HELPING METHODS DOWN HERE
	public boolean existPropertyType(int id) {
		TypedQuery<PropertyTypeEntity> query = em.createQuery(GET_PROPERTY_TYPE_BY_ID, PropertyTypeEntity.class)
				.setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	public boolean existPropertyType(PropertyTypeNameEnum propertyTypeNameEnum, String desc) {
		TypedQuery<PropertyTypeEntity> query = em.createQuery(CHECK_PROPERTY_TYPE_EXIST, PropertyTypeEntity.class)
				.setParameter("name", propertyTypeNameEnum).setParameter("desc", desc);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	public boolean existPropertyTypeInProperties(PropertyTypeEntity id) {
		TypedQuery<PropertyEntity> query = em.createQuery(CHECK_PROPERTY_TYPE_IN_PROPERTIES, PropertyEntity.class)
				.setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

}
