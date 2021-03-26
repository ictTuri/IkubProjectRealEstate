package com.realestate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realestate.model.UserEntity;



@Repository
@Transactional

public class UserRepository {
	
	private EntityManager entityManager;
	
	public UserRepository (EntityManager entityManager) {
		super();
		this.entityManager=entityManager;
	}
	
	
	private static final String USER_BY_USERNAME ="SELECT user FROM UserEntity where user.username=?1 and user.valid=true";
    private static final String ALL_USERS_FETCHED ="SELECT user FROM UserEntity LEFT JOIN FETCH user.roles";
    
    
    public List<UserEntity> getAllUsers(){
    	TypedQuery<UserEntity> query =entityManager.createNamedQuery("User.findAlll", UserEntity.class);
    	return query.getResultList();
    }
    
    public List<UserEntity> getAllUsersFetched(){
    	TypedQuery<UserEntity> query =entityManager.createQuery(ALL_USERS_FETCHED, UserEntity.class);
    	return query.getResultList();
    }
    
    public UserEntity getUserByUsername(String username) {
    	
    	TypedQuery<UserEntity> query = entityManager.createQuery(USER_BY_USERNAME,UserEntity.class).setParameter(1,username);
    	try {
    		return query.getSingleResult();
    	}
    	catch (NoResultException e) {
    		return null;
    	}
    }
    
    public void addUser(UserEntity user) {
    	entityManager.persist(user);
    }
    
    public void updateUser(UserEntity user) {
    	entityManager.merge(user);
    }
    
    
    public void softDeleteUser(UserEntity user) {
    	user.setValid(Boolean.FALSE);
    	updateUser(user);
    	
    	
    }
    
    public void hardDeleteUser(UserEntity user) {
    	entityManager.remove(user);
    	
    }
    
    
    
    
    }
