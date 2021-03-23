package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.UserEntity;

@Repository
@Transactional
public class UserRepository {

	EntityManager em;

	public UserRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_USERS = "FROM UserEntity";
	private static final String GET_USER_BY_ID = "FROM UserEntity u WHERE u.userId = :id";
	private static final String GET_USER_BY_USERNAME = "SELECT u.username FROM UserEntity u WHERE u.username = :username";
	private static final String GET_USER_BY_EMAIL = "SELECT u.email FROM UserEntity u WHERE u.email = :email";
	private static final String USER_BY_USERNAME = "SELECT u FROM UserEntity u where u.username =?1 and u.isActive = true";
	
	//RETRIEVE OPERATIONS DOWN HERE
	public List<UserEntity> getAllUsers() {
		return em.createQuery(GET_ALL_USERS, UserEntity.class).getResultList();
	}
	
	public UserEntity getUserById(Integer id) {
		TypedQuery<UserEntity> query = em.createQuery(GET_USER_BY_ID, UserEntity.class).setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//INSERT OPERATIONS DOWN HERE
	public void insertUser(UserEntity user) {
		em.persist(user);
	}
	//UPDATE OPERATIONS DOWN HERE
	
	public UserEntity updateUser(UserEntity user) {
			return em.merge(user);
	}
	
	//DELETE OPERATIONS DOWN HERE
	public UserEntity deleteUser(UserEntity user) {
		UserEntity u = getUserByUsername(user.getUsername());
		if(u!=null) {
			u.setActive(false);
			return em.merge(u);
		}
		return null;
}
	
	//HELPING METHODS BELOW HERE
	public boolean existUsername(String username) {
		TypedQuery<String> query = em.createQuery(GET_USER_BY_USERNAME, String.class).setParameter("username", username);
		return query.getSingleResult()!=null;
		
	}
	
	public UserEntity getUserByUsername(String username) {
		TypedQuery<UserEntity> query = em.createQuery(USER_BY_USERNAME, UserEntity.class).setParameter(1,username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean existEmail(String email) {
		TypedQuery<String> query = em.createQuery(GET_USER_BY_EMAIL, String.class).setParameter("email", email);
		return query.getSingleResult()!=null;
	}
	
	public boolean isActiveUser(String username) {
		TypedQuery<UserEntity> query = em.createQuery("FROM User u WHERE u.username = :username", UserEntity.class).setParameter("username", username);
		UserEntity u = query.getSingleResult();
		return u.isActive() == Boolean.TRUE;
	
	}
}
