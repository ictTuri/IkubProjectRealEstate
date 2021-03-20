package com.realestate.app.repository;

import javax.persistence.EntityManager;

import com.realestate.app.entity.User;

public class AdminRepository {
	
	EntityManager em;
	
	public void createOwner(User user) {
		em.persist(user);
		
	}
}
