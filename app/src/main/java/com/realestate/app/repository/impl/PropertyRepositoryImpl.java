package com.realestate.app.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.entity.enums.PropertyCategory;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.filter.PropertyFilter;
import com.realestate.app.repository.PropertyRepository;

@Repository
public class PropertyRepositoryImpl implements PropertyRepository{
	EntityManager em;
	LocationRepositoryImpl locRepo;

	@Autowired
	public PropertyRepositoryImpl(EntityManager em, LocationRepositoryImpl locRepo) {
		super();
		this.em = em;
		this.locRepo = locRepo;
	}

	private static final String GET_PROPERTY_OWNER = "SELECT pe.owner FROM PropertyEntity pe WHERE pe.propertiesId = :id";

	private static final String GET_PROPERTY_INFO_BY_ID = "FROM PropertyInfoEntity pie WHERE pie.propertyInfoId = :id ";
	private static final String CHECK_PROPERTY_INFO_TAKEN = "FROM PropertyEntity pe WHERE pe.propertiesId != :id and pe.propertyInfo = :info ";
	private static final String CHECK_LOCATION_INTO_PROPERTY = "FROM PropertyEntity pe WHERE pe.propertyLocation = :id ";
	private static final String GET_PROPERTIES_BY_OWNER = "FROM PropertyEntity pe WHERE pe.owner = :owner";
	String fetchquery = "From PropertyEntity pe Left join fetch pe.propertyLocation pl where pl.cityName = :city ";

	// RETRIEVE OPERATIONS DOWN HERE
	@Override
	public List<PropertyEntity> getAllProperties(PropertyFilter filter) {
		// Starting query
		String queryString = "SELECT prop from PropertyEntity prop where 1=1 ";

		// Creating query string for all filtrabl
		queryString = extractedFilterCheck(filter, queryString);

		// setting sort field
		if (filter.getSortBy() != null && !filter.getSortBy().isEmpty()) {
			if (filter.getSortBy().equals("category") || filter.getSortBy().equals("location")
					|| filter.getSortBy().equals("sellingPrice") || filter.getSortBy().equals("rentingPrice")) {
				queryString = queryString + " order by prop." + filter.getSortBy();
			} else {
				throw new MyExcMessages("Sort by must be category, location, sellingPrice or RentingPrice");
			}
		}
		// setting order
		if (filter.getOrder() != null && !filter.getOrder().isEmpty() && filter.getSortBy() != null
				&& !filter.getSortBy().isEmpty()) {
			if (filter.getOrder().equalsIgnoreCase("ASC") || filter.getOrder().equalsIgnoreCase("DESC")) {
				queryString = queryString + " " + filter.getOrder();
			} else {
				throw new MyExcMessages("Order  must be ASC or DESC");
			}
		}

		return extractedFinalQuery(filter, queryString);
	}

	// Extracted
	private String extractedFilterCheck(PropertyFilter filter, String queryString) {
		if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
			queryString = queryString + " and prop.category = :category ";
		}
		if (filter.getMinPrice() != null) {
			queryString = queryString + " and ( prop.rentingPrice > :minone OR prop.sellingPrice > :mintwo ) ";
		}
		if (filter.getMaxPrice() != null && filter.getMinPrice() != null
				&& filter.getMaxPrice() > filter.getMinPrice()) {
			queryString = queryString + " and ( prop.rentingPrice < :maxone OR prop.sellingPrice < :maxtwo ) ";
		} else {
			if (filter.getMaxPrice() != null && filter.getMinPrice() == null) {
				queryString = queryString + " and ( prop.rentingPrice < :maxone OR prop.sellingPrice < :maxtwo ) ";
			}
		}
		return queryString;
	}

	// Extracted
	private List<PropertyEntity> extractedFinalQuery(PropertyFilter filter, String queryString) {
		TypedQuery<PropertyEntity> query = em.createQuery(queryString, PropertyEntity.class);

		// Setting parameters
		if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
			query.setParameter("category", PropertyCategory.valueOf(filter.getCategory()));
		}
		if (filter.getMinPrice() != null) {
			query.setParameter("minone", filter.getMinPrice()).setParameter("mintwo", filter.getMinPrice());
		}
		if (filter.getMaxPrice() != null && filter.getMinPrice() != null
				&& filter.getMaxPrice() > filter.getMinPrice()) {
			query.setParameter("maxone", filter.getMaxPrice()).setParameter("maxtwo", filter.getMaxPrice());
		} else {
			if (filter.getMaxPrice() != null && filter.getMinPrice() == null) {
				query.setParameter("maxone", filter.getMaxPrice()).setParameter("maxtwo", filter.getMaxPrice());
			}
		}
		return query.getResultList();
	}

	// Get Property by id
	@Override
	public PropertyEntity getPropertiesById(Integer propertiesId) {
		try {
			return em.find(PropertyEntity.class, propertiesId);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	// INSERT OPERATIONS DOWN HERE
	@Override
	public void insertProperties(PropertyEntity property) {
		em.persist(property);
	}

	// UPDATE OPERATIONS DOWN HERE
	@Override
	public PropertyEntity updateProperty(PropertyEntity property) {
		return em.merge(property);
	}

	// DELETE OPERATIONS DOWN HERE
	@Override
	public void deleteProperty(PropertyEntity property) {
		em.remove(property);
	}

	// HELPING METHODS DOWN HERE
	@Override
	public boolean existLocationInProperty(LocationEntity id) {
		TypedQuery<PropertyEntity> query = em.createQuery(CHECK_LOCATION_INTO_PROPERTY, PropertyEntity.class)
				.setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	@Override
	public boolean existPropertyInfoAnotherProperty(int id, int info) {
		TypedQuery<PropertyTypeEntity> query = em.createQuery(CHECK_PROPERTY_INFO_TAKEN, PropertyTypeEntity.class)
				.setParameter("id", id).setParameter("info", info);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	@Override
	public boolean existPropertyInfo(int id) {
		TypedQuery<PropertyInfoEntity> query = em.createQuery(GET_PROPERTY_INFO_BY_ID, PropertyInfoEntity.class)
				.setParameter("id", id);
		try {
			return query.getResultList().get(0) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	@Override
	public List<PropertyEntity> getPropertiesByOwner(UserEntity user) {
		return em.createQuery(GET_PROPERTIES_BY_OWNER, PropertyEntity.class).setParameter("owner", user)
				.getResultList();
	}

	@Override
	public UserEntity getPropertyOwner(int id) {
		try {
			return em.createQuery(GET_PROPERTY_OWNER, UserEntity.class).setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
