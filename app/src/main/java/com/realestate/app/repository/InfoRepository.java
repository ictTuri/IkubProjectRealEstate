package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;

@Repository
public class InfoRepository {
	EntityManager em;

	public InfoRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_PROPERTY_INFOS = "FROM PropertyInfoEntity";
	private static final String GET_PROPERTY_INFO_BY_ID = "FROM PropertyInfoEntity pie WHERE pie.propertyInfoId = :id";
	private static final String CHECK_INFO_EXIST_IN_PROPERTY = "FROM PropertyEntity pe WHERE pe.propertyInfo = :id";

	// PROPERTY INFO
	public List<PropertyInfoEntity> getAllPropertyInfos() {
		return em.createQuery(GET_ALL_PROPERTY_INFOS, PropertyInfoEntity.class).getResultList();
	}

	public PropertyInfoEntity getPropertyInfoById(Integer id) {
		TypedQuery<PropertyInfoEntity> query = em.createQuery(GET_PROPERTY_INFO_BY_ID, PropertyInfoEntity.class)
				.setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void insertPropertyInfo(PropertyInfoEntity propertyInfo) {
		em.persist(propertyInfo);
	}

	public PropertyInfoEntity updatePropertyInfo(PropertyInfoEntity propertyInfo) {
		return em.merge(propertyInfo);
	}
	
	public void deletePropertyInfo(PropertyInfoEntity propertyInfo) {
		em.remove(propertyInfo);
	}
	
	// ADDITIONAL METHODS
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
