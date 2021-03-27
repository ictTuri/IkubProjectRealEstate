package com.realestate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;

@Repository
public class UserRepository {

	EntityManager em;

	public UserRepository(EntityManager em) {
		super();
		this.em = em;
	}

	private static final String GET_ALL_USERS = "FROM UserEntity";
	
	private static final String GET_USER_BY_ID = "FROM UserEntity u WHERE u.userId = :id";
	private static final String GET_ROLE_BY_ID = "FROM RoleEntity r WHERE r.roleId = :id";
	private static final String GET_OWNER_BY_ID = "SELECT u FROM UserEntity u WHERE u.userId = :id and u.role = :role";
	
	private static final String CHECK_USERNAME_EXIST = "SELECT u.username FROM UserEntity u WHERE u.username = :username";
	private static final String CHECK_EMAIL_EXIST = "SELECT u.email FROM UserEntity u WHERE u.email = :email";
	private static final String USER_BY_USERNAME = "SELECT u FROM UserEntity u WHERE u.username =?1 and u.isActive = true";
	private static final String CHECK_BY_USERNAME = "SELECT u FROM UserEntity u WHERE u.username = :username";
	private static final String CHECK_IF_CLIENT = "FROM UserEntity u WHERE u.userId = :id and u.role = :role";

	// RETRIEVE OPERATIONS DOWN HERE
	public List<UserEntity> getAllUsers() {
		return em.createQuery(GET_ALL_USERS, UserEntity.class).getResultList();
	}

	public UserEntity getUserById(int id) {
		TypedQuery<UserEntity> query = em.createQuery(GET_USER_BY_ID, UserEntity.class).setParameter("id", id);
		try{
			return query.getResultList().get(0);
		}catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	//ROLE
	public RoleEntity getRoleById(int id) {
		TypedQuery<RoleEntity> query = em.createQuery(GET_ROLE_BY_ID, RoleEntity.class).setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	// INSERT OPERATIONS DOWN HERE
	public void insertUser(UserEntity user) {
		em.persist(user);
	}

	// UPDATE OPERATIONS DOWN HERE
	public UserEntity updateUser(UserEntity user) {
		return em.merge(user);
	}

	// DELETE OPERATIONS DOWN HERE
	public UserEntity deleteUser(UserEntity user) {
		UserEntity u = getUserByUsername(user.getUsername());
		if (u != null) {
			u.setActive(false);
			return em.merge(u);
		}
		return null;
	}


	// HELPING METHODS BELOW HERE
	public boolean existUsername(String username) {
		TypedQuery<String> query = em.createQuery(CHECK_USERNAME_EXIST, String.class).setParameter("username",username);
		try {
			return query.getResultList().get(0) != null;
		}catch(IndexOutOfBoundsException e) {
			return false;
		}
	}

	public boolean existEmail(String email) {
		TypedQuery<String> query = em.createQuery(CHECK_EMAIL_EXIST, String.class).setParameter("email", email);
		try {
			return query.getResultList().get(0) != null;
		}catch(IndexOutOfBoundsException e) {
			return false;
		}
		
	}
	
	public boolean existUserById(int id, RoleEntity role) {
		TypedQuery<UserEntity> query = em.createQuery(GET_OWNER_BY_ID, UserEntity.class).setParameter("id", id).setParameter("role", role);
		try {
			return query.getResultList().get(0) != null;
		}catch(IndexOutOfBoundsException e) {
			return false;
		}
		
	}

	public UserEntity getUserByUsername(String username) {
		TypedQuery<UserEntity> query = em.createQuery(USER_BY_USERNAME, UserEntity.class).setParameter(1, username);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean isActiveUser(String username) {
		TypedQuery<UserEntity> query = em.createQuery(CHECK_BY_USERNAME, UserEntity.class).setParameter("username", username);
		try {
			UserEntity u = query.getSingleResult();
			return u.getActive() == Boolean.TRUE;
		}catch(NoResultException e) {
			return false;
		}
	}

	public boolean isClient(int id,RoleEntity role) {
		TypedQuery<UserEntity> query = em.createQuery(CHECK_IF_CLIENT, UserEntity.class)
				.setParameter("id", id)
				.setParameter("role", role);
		try {
			return query.getSingleResult() != null;
		}catch(NoResultException e) {
			return false;
		}
	}
}
