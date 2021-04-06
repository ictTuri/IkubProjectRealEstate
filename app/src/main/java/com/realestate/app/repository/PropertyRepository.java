package com.realestate.app.repository;

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
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.filter.PropertyFilter;

@Repository
public class PropertyRepository {
	EntityManager em;
	LocationRepository locRepo;
	
	@Autowired
	public PropertyRepository(EntityManager em, LocationRepository locRepo) {
		super();
		this.em = em;
		this.locRepo = locRepo;
	}

	private static final String GET_PROPERTY_BY_ID = "FROM PropertyEntity pe WHERE pe.propertiesId = :id";
	private static final String GET_PROPERTY_INFO_BY_ID = "FROM PropertyInfoEntity pie WHERE pie.propertyInfoId = :id";
	private static final String CHECK_PROPERTY_INFO_TAKEN = "FROM PropertyEntity pe WHERE pe.propertiesId != :id and pe.propertyInfo = :info";
	private static final String CHECK_LOCATION_INTO_PROPERTY = "FROM PropertyEntity pe WHERE pe.propertyLocation = :id";
	
	// RETRIEVE OPERATIONS DOWN HERE
	// PROPERTIES
	public List<PropertyEntity> getAllProperties(PropertyFilter filter) {
		// Starting query
				String queryString = "SELECT prop from PropertyEntity prop where 1=1 ";

				// Creating query string for all filtrabl
				queryString = extractedFilterCheck(filter, queryString);

				// setting sort field
				if (filter.getSortBy() != null && !filter.getSortBy().isEmpty()) {
					if (filter.getSortBy().equals("category") || filter.getSortBy().equals("location")
							|| filter.getSortBy().equals("sellingPrice")|| filter.getSortBy().equals("rentingPrice")) {
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
		if (filter.getMinPrice()!=null) {
			queryString = queryString + " and prop.rentingPrice > :min OR prop.sellingPrice > :minPrice";
		}
		if (filter.getMaxPrice() != null) {
				queryString = queryString + " and prop.rentingPrice < :maxPrice OR prop.sellingPrice < :maxPrice";
		}
		if (filter.getCityLocated() != null && !filter.getCityLocated().isEmpty()) {
			queryString = queryString + "and prop.location = :location ";
		}
		return queryString;
	}

	// Extracted
	private List<PropertyEntity> extractedFinalQuery(PropertyFilter filter, String queryString) {
		TypedQuery<PropertyEntity> query = em.createQuery(queryString, PropertyEntity.class);

     //Setting parameters
		if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
			query.setParameter("category", filter.getCategory());
		}
		if (filter.getMinPrice()!=null) {
			query.setParameter("minPrice", filter.getMinPrice());
		}
		if (filter.getMaxPrice()!=null) {
			if(filter.getMinPrice()!=null) {
				if(filter.getMaxPrice() > filter.getMinPrice() || filter.getMinPrice()==null ) {
					query.setParameter("maxPrice", filter.getMaxPrice());	
				}
			}		
		}
		if (filter.getCityLocated() != null && !filter.getCityLocated().isEmpty()) {
			LocationEntity location = locRepo.getLocationByCityName(filter.getCityLocated());
			query.setParameter("category", location);
		}

		return query.getResultList();
	}

	// Get Property by id
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
