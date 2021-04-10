package com.realestate.app.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realestate.app.dto.UserDtoForCreate;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.security.UserPrincipal;
import com.realestate.app.service.UserProfileService;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	private static final Logger logger = LogManager.getLogger(UserProfileServiceImpl.class);
	
	TradeRepository tradeRepo;
	PasswordEncoder passwordEncoder;
	UserRepository userRepo;

	@Autowired
	public UserProfileServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder, TradeRepository tradeRepo) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.tradeRepo = tradeRepo;
	}

	@Override
	public UserEntity getLoggedUser() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//LOGGING
		logger.info("Showing user Profile");
		
		return userRepo.getUserByUsername(thisUser.getUsername());
	}

	@Override
	public UserEntity updateLoggedUser(UserDtoForCreate user) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity userToUpdate = userRepo.getUserByUsername(thisUser.getUsername());	
		return userUpdateValidation(user, userToUpdate);
	}

	private UserEntity userUpdateValidation(UserDtoForCreate user, UserEntity userToUpdate) {
		if (!userToUpdate.getUsername().equals(user.getUsername()) && userRepo.existUsername(user.getUsername())) {
			throw new MyExcMessages("Can not use this username !");
		} else {
			if (!user.getEmail().equals(userToUpdate.getEmail()) && userRepo.existEmail(user.getEmail())) {
				throw new MyExcMessages("Updated Email is used by another User !");
			} else {
				userToUpdate.setFirstName(user.getFirstName());
				userToUpdate.setLastName(user.getLastName());
				userToUpdate.setEmail(user.getEmail());
				userToUpdate.setUsername(user.getUsername());
				userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
				
				//LOGGING
				logger.info("Updating Profile, User: {}",userToUpdate);
				
				userRepo.updateUser(userToUpdate);
				return userToUpdate;
			}
		}
	}

	@Override
	public String deleteLoggedUser() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity userToDelete = userRepo.getUserByUsername(thisUser.getUsername());
		if (!(tradeRepo.checkClientInTrade(userToDelete))) {
			if (userToDelete.isActive()) {
				userToDelete.setActive(false);
				userRepo.updateUser(userToDelete);
				
				//LOGGING
				logger.info("Soft Deletion of Profile, User: {}",userToDelete);
				
				return "Once the token expires you won be able to lo back in";
			} else {
				throw new MyExcMessages("User Already deleted.");
			}
		} else {
			throw new MyExcMessages("You are part of a rented trade type. Please close the rent first.");
		}

	}
}