package com.realestate.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.repository.InfoRepository;

@Repository
public class InfoRepositoryImpl implements InfoRepository{
	EntityManager em;

	public InfoRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_PROPERTY_INFOS = "FROM PropertyInfoEntity";
	private static final String CHECK_INFO_EXIST_IN_PROPERTY = "FROM PropertyEntity pe WHERE pe.propertyInfo = :id";

	// PROPERTY INFO
	@Override
	public List<PropertyInfoEntity> getAllPropertyInfos() {
		return em.createQuery(GET_ALL_PROPERTY_INFOS, PropertyInfoEntity.class).getResultList();
	}

	// Get info by id
	@Override
	public PropertyInfoEntity getPropertyInfoById(Integer propertyInfoId) {
		try {
			return em.find(PropertyInfoEntity.class, propertyInfoId);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	// Insert new info on database
	@Override
	public void insertPropertyInfo(PropertyInfoEntity propertyInfo) {
		em.persist(propertyInfo);
	}

	// Update info on database by id
	@Override
	public PropertyInfoEntity updatePropertyInfo(PropertyInfoEntity propertyInfo) {
		return em.merge(propertyInfo);
	}
	
	// Delete info from database
	@Override
	public void deletePropertyInfo(PropertyInfoEntity propertyInfo) {
		em.remove(propertyInfo);
	}
	
	// ADDITIONAL METHODS
	@Override
	public boolean existProropertyWithInfo(PropertyInfoEntity id) {
		TypedQuery<PropertyEntity> query = em.createQuery(CHECK_INFO_EXIST_IN_PROPERTY, PropertyEntity.class)
				.setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}
}
