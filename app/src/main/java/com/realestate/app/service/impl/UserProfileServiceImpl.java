package com.realestate.app.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.UserConverter;
import com.realestate.app.converter.UserRegisterConverter;
import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserForCreateDto;
import com.realestate.app.dto.UserRegisterDto;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.entity.enums.Roles;
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

	public UserProfileServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder, TradeRepository tradeRepo) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.tradeRepo = tradeRepo;
	}

	@Override
	public UserDto getLoggedUser() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//LOGGING
		logger.info("Showing user Profile");
		
		return UserConverter.toDto(userRepo.getUserByUsername(thisUser.getUsername()));
	}
	

	@Override
	public UserRegisterDto addUser(UserRegisterDto user) {
		user.setRole(user.getRole().toUpperCase());
		if(user.getRole().equals(Roles.OWNER.getValue())) {
			if (!userRepo.existUsername(user.getUsername())) {
				return extractedOwnerRegisterValidation(user);
			} else {
				throw new MyExcMessages("Username already exist");
			}
		}else {
			if(user.getRole().equals(Roles.CLIENT.getValue())) {
				if (!userRepo.existUsername(user.getUsername())) {
					return extractedClientRegisterValidation(user);
				} else {
					throw new MyExcMessages("Username already exist");
				}
			}else {
				throw new MyExcMessages("Please fill one role: ROLE_CLIENT or ROLE_OWNER!");
			}
		}
	}

	private UserRegisterDto extractedOwnerRegisterValidation(UserRegisterDto user) {
		if (!userRepo.existEmail(user.getEmail())) {
			RoleEntity role = userRepo.getRoleById(2);
			if (role != null) {
				String encodedPass = passwordEncoder.encode(user.getPassword());
				user.setPassword(encodedPass);
				UserEntity userToAdd = UserRegisterConverter.toEntityForCreate(user, role);
				userToAdd.setActive(true);
				userRepo.insertUser(userToAdd);
				
				//LOGGING
				logger.info("User inserted: {}", userToAdd);
				
				return UserRegisterConverter.toDto(userToAdd);
			} else {
				throw new MyExcMessages("Role does not exist");
			}
		} else {
			throw new MyExcMessages("Email already registered");
		}
	}

	private UserRegisterDto extractedClientRegisterValidation(UserRegisterDto user) {
		if (!userRepo.existEmail(user.getEmail())) {
			RoleEntity role = userRepo.getRoleById(3);
			if (role != null) {
				String encodedPass = passwordEncoder.encode(user.getPassword());
				user.setPassword(encodedPass);
				UserEntity userToAdd = UserRegisterConverter.toEntityForCreate(user, role);
				userRepo.insertUser(userToAdd);
				
				//LOGGING
				logger.info("User inserted: {}", userToAdd);
				
				return UserRegisterConverter.toDto(userToAdd);
			} else {
				throw new MyExcMessages("Role does not exist");
			}
		} else {
			throw new MyExcMessages("Email already registered");
		}
	}

	@Override
	public UserDto updateLoggedUser(UserForCreateDto user) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity userToUpdate = userRepo.getUserByUsername(thisUser.getUsername());	
		return userUpdateValidation(user, userToUpdate);
	}

	private UserDto userUpdateValidation(UserForCreateDto user, UserEntity userToUpdate) {
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
				return UserConverter.toDto(userToUpdate);
			}
		}
	}

	@Override
	public void deleteLoggedUser() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity userToDelete = userRepo.getUserByUsername(thisUser.getUsername());
		if (!(tradeRepo.checkClientInTrade(userToDelete))) {
			if (userToDelete.isActive()) {
				userToDelete.setActive(false);
				userRepo.updateUser(userToDelete);
				
				//LOGGING
				logger.info("Soft Deletion of Profile, User: {}",userToDelete);
				
			} else {
				throw new MyExcMessages("User Already deleted.");
			}
		} else {
			throw new MyExcMessages("You are part of a rented trade type. Please close the rent first.");
		}

	}

}