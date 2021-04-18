package com.realestate.app.repository.impl;


import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.repository.InfoRepository;

@Repository
public class InfoRepositoryImpl implements InfoRepository {
	EntityManager em;

	public InfoRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}


	// Get info by id
	@Override
	public PropertyInfoEntity getPropertyInfoById(Integer propertyInfoId) {
		try {
			return em.find(PropertyInfoEntity.class, propertyInfoId);
		} catch (IllegalArgumentException  e) {
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

}
