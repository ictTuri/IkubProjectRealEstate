package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.Location;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.User;

@Repository
@Transactional
public class AdminRepository {

	EntityManager em;

	public AdminRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_PROPERTIES = "FROM Property prop";
	private static final String GET_ALL_USERS = "FROM User";
	private static final String GET_USER_BY_ID = "FROM User u WHERE u.userId = :id";
	private static final String GET_USER_BY_USERNAME = "SELECT u.username FROM User u WHERE u.username = :username";
	private static final String GET_USER_BY_EMAIL = "SELECT u.email FROM User u WHERE u.email = :email";
	private static final String GET_ALL_OWNERS = "SELECT user FROM User user WHERE user.roleId = 3";
	private static final String GET_ALL_CLIENTS = "SELECT user FROM User user WHERE user.roleId = 1";
	private static final String USER_BY_USERNAME = "SELECT u FROM User u where u.username =?1 and u.isActive = true";
	//RETRIEVE OPERATIONS DOWN HERE
	
	public List<Property> getAllProperties() {
		TypedQuery<Property> query = em.createQuery(GET_ALL_PROPERTIES, Property.class);
		return query.getResultList();
	}

	public List<User> getAllUsers() {
		TypedQuery<User> query = em.createQuery(GET_ALL_USERS, User.class);
		return query.getResultList();
	}
	
	public User getUserById(Integer id) {
		TypedQuery<User> query = em.createQuery(GET_USER_BY_ID, User.class).setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<User> getAllOwners() {
		TypedQuery<User> query = em.createQuery(GET_ALL_OWNERS, User.class);
		return query.getResultList();
	}
	
	public List<User> getAllClients() {
		TypedQuery<User> query = em.createQuery(GET_ALL_CLIENTS, User.class);
		return query.getResultList();
	}
	
	//INSERT OPERATIONS DOWN HERE
	
	public void insertUser(User user) {
		em.persist(user);
	}
	
	public void insertPropertyWithInfo(Property property) {
		em.persist(property);
	}
	
	public void insertLocation(Location location) {
		em.persist(location);
	}
	
	//UPDATE OPERATIONS DOWN HERE
	
	public User updateUser(User user) {
			return em.merge(user);
	}
	
	//DELETE OPERATIONS DOWN HERE
	public User deleteUser(User user) {
		User u = getUserByUsername(user.getUsername());
		if(u!=null) {
			u.setActive(false);
			return em.merge(u);
		}
		return null;
}
	
	//HELPING METHODS BELOW HERE
	public boolean existUsername(String username) {
		TypedQuery<String> query = em.createQuery(GET_USER_BY_USERNAME, String.class).setParameter("username", username);
		if(query.getSingleResult()!=null) {
			return true;
		}
		return false;
	}
	
	public User getUserByUsername(String username) {
		TypedQuery<User> query = em.createQuery(USER_BY_USERNAME, User.class).setParameter(1,username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean existEmail(String email) {
		TypedQuery<String> query = em.createQuery(GET_USER_BY_EMAIL, String.class).setParameter("email", email);
		if(query.getSingleResult()!=null) {
			return true;
		}
		return false;
	}
	
	public boolean isActiveUser(String username) {
		TypedQuery<User> query = em.createQuery("FROM User u WHERE u.username = :username", User.class).setParameter("username", username);
		User u = query.getSingleResult();
		if(u.getActive() == Boolean.TRUE) {
			return true;
		}
		return false;
	}
}
