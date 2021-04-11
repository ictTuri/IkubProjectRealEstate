package com.realestate.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.enums.PropertyTypeName;
import com.realestate.app.repository.TypeRepository;

@Repository
public class TypeRepositoryImpl implements TypeRepository{

	EntityManager em;

	public TypeRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_PROPERTY_TYPES = "FROM PropertyTypeEntity";
	private static final String CHECK_PROPERTY_TYPE_EXIST = "FROM PropertyTypeEntity pte WHERE pte.propertyTypeName = :name and pte.propertyTypeDesc = :desc";
	private static final String CHECK_PROPERTY_TYPE_IN_PROPERTIES = "FROM PropertyEntity pe WHERE pe.propertyType = :id";

	// PROPERTY TYPES
	@Override
	public List<PropertyTypeEntity> getAllPropertyTypes() {
		return em.createQuery(GET_ALL_PROPERTY_TYPES, PropertyTypeEntity.class).getResultList();
	}

	@Override
	public PropertyTypeEntity getPropertyTypeById(Integer propertyTypeId) {
		try {
			return em.find(PropertyTypeEntity.class, propertyTypeId);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public void insertPropertyType(PropertyTypeEntity propertyType) {
		em.persist(propertyType);
	}

	@Override
	public PropertyTypeEntity updatePropertyType(PropertyTypeEntity propertyType) {
		return em.merge(propertyType);
	}

	@Override
	public void deletePropertyType(PropertyTypeEntity propertyType) {
		em.remove(propertyType);
	}

	// HELPING METHODS DOWN HERE
	@Override
	public boolean existPropertyType(int propertyTypeId) {
		try {
			return em.find(PropertyTypeEntity.class, propertyTypeId) != null;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

	@Override
	public boolean existPropertyType(PropertyTypeName propertyTypeNameEnum, String desc) {
		TypedQuery<PropertyTypeEntity> query = em.createQuery(CHECK_PROPERTY_TYPE_EXIST, PropertyTypeEntity.class)
				.setParameter("name", propertyTypeNameEnum).setParameter("desc", desc);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	@Override
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
