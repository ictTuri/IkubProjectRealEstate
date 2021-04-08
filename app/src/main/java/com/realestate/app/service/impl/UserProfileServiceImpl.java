package com.realestate.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.entity.UserEntity;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	UserRepository userRepo;

	private static final Logger logger = LogManager.getLogger(UserProfileServiceImpl.class);
	
	@Autowired
	public UserProfileServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserEntity getLoggedUser(String username) {
		
		logger.info("Getting user with username{} ", username);
		
		return userRepo.getUserByUsername(username);
	}


	
}