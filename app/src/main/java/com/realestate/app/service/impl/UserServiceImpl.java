package com.realestate.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserForCreateDto;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.filter.UserFilter;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	PasswordEncoder passwordEncoder;
	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// GET ALL USER OR FILTERED BY NAME
	@Override
	public List<UserDto> getUsers(UserFilter filter) {
		
		//LOGGING
		logger.info("Filtring users with filter {}", filter);
		
		List<UserDto> toReturn = new ArrayList<>();
		userRepository.getAllUsers(filter).forEach(entity -> toReturn.add(UserConverter.toDto(entity)));
		return toReturn;
	}

	// USER DISPLAY BY ID
	@Override
	public UserDto userById(int id) {
		UserEntity user = userRepository.getUserById(id);
		if (user != null) {
			
			//LOGGING
			logger.info("Getting users with ID {}", id);
			
			return UserConverter.toDto(user);
		} else {
			throw new MyExcMessages("No such User with given Id !");
		}
	}

	// USER INSERT
	@Override
	public UserDto addUser(UserForCreateDto user) {
		if (!userRepository.existUsername(user.getUsername())) {
			if (!userRepository.existEmail(user.getEmail())) {
				RoleEntity role = userRepository.getRoleById(user.getRole());
				if (role != null) {
					String encodedPass = passwordEncoder.encode(user.getPassword());
					user.setPassword(encodedPass);
					UserEntity userToAdd = UserConverter.toEntityForCreate(user, role);
					userRepository.insertUser(userToAdd);
					
					//LOGGING
					logger.info("User inserted: {}", userToAdd);
					
					return UserConverter.toDto(userToAdd);
				} else {
					throw new MyExcMessages("Role does not exist");
				}
			} else {
				throw new MyExcMessages("Email already registered");
			}
		} else {
			throw new MyExcMessages("Username already exist");
		}
	}

	// USER UPDATE
	@Override
	public UserDto updateUser(UserForCreateDto user, int id) {
		UserEntity userToUpdate = userRepository.getUserById(id);
		if (userToUpdate != null) {
			return userUpdateValidation(user, userToUpdate);
		} else {
			throw new MyExcMessages("No User with such Id / Please check again");
		}
	}

	// Validations for the User Update Extracted
	private UserDto userUpdateValidation(UserForCreateDto user, UserEntity userToUpdate) {
		if (!userToUpdate.getUsername().equals(user.getUsername())
				&& userRepository.existUsername(user.getUsername())) {
			throw new MyExcMessages("Can not use this username !");
		} else {
			if (!user.getEmail().equals(userToUpdate.getEmail()) && userRepository.existEmail(user.getEmail())) {
				throw new MyExcMessages("Updated Email is used by another User !");
			} else {
				RoleEntity role = userRepository.getRoleById(user.getRole());
				if (role != null) {
					userToUpdate.setFirstName(user.getFirstName());
					userToUpdate.setLastName(user.getLastName());
					userToUpdate.setEmail(user.getEmail());
					userToUpdate.setUsername(user.getUsername());
					userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
					userToUpdate.setRole(role);
					
					//LOGGING
					logger.info("User Updated: {}", userToUpdate);
					
					userRepository.updateUser(userToUpdate);
					return UserConverter.toDto(userToUpdate);
				} else {
					throw new MyExcMessages("Role given does not exist");
				}
			}
		}
	}

	// USER DELETION
	@Override
	public void deleteUser(int id) {
		UserEntity userToDelete = userRepository.getUserById(id);
		if (userToDelete != null) {
			if (userToDelete.isActive()) {
				userToDelete.setActive(false);
				
				//LOGGING
				logger.info("User Soft Deleted: {}", userToDelete);
				
				userRepository.updateUser(userToDelete);
			} else {
				throw new MyExcMessages("User Already deleted.");
			}

		} else
			throw new MyExcMessages("Can not delete User / Or user does not exist with given Id");
	}

}
