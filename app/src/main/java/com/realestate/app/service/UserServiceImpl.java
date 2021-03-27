package com.realestate.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.UserDtoForCreate;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	//ALL USERS DISPLAYED
	@Override
	public List<UserEntity> allUsers() {
		return userRepository.getAllUsers();
	}
	
	//USER DISPLAY BY ID
	@Override
	public UserEntity userById(int id) {
		UserEntity user = userRepository.getUserById(id);
		if(user!=null) {
			return user;
		}else {
			throw new MyExcMessages("No such User with given Id !");
		}
	}
	
	//USER INSERT
	@Override
	public UserEntity addUser(UserDtoForCreate user) {
		if (user != null) {
			return userValidations(user);
		} else {
			throw new MyExcMessages("please fill the user data to proceed !");
		}	
	}
	
	//Validations for the new user extracted
	private UserEntity userValidations(UserDtoForCreate user) {
		if (user.getFirstName() != null || user.getLastName() != null) {
			if(!userRepository.existUsername(user.getUsername())) {
				if(!userRepository.existEmail(user.getEmail())) {
					RoleEntity role = userRepository.getRoleById(user.getRole());
					if (role != null) {
						UserEntity userToAdd = UserConverter.toEntityForCreate(user, role);
						userRepository.insertUser(userToAdd);
						return userToAdd;
					} else {
						throw new MyExcMessages("Role does not exist");
					}
				}else {
					throw new MyExcMessages("Email already registered");
				}
			}else {
				throw new MyExcMessages("Username already exist");
			}
		}else {
			throw new MyExcMessages("Please fill your first name and last name !");
		}
	}
	
	//USER UPDATE
	@Override
	public UserEntity updateUser(UserDtoForCreate user, int id) {
		UserEntity userToUpdate = userRepository.getUserById(id);
		if (userToUpdate != null) {
			return userUpdateValidation(user, userToUpdate);
		} else {
			throw new MyExcMessages("No User with such Id / Please check again");
		}
	}
	
	//Validations for the User Update Extracted
	private UserEntity userUpdateValidation(UserDtoForCreate user, UserEntity userToUpdate) {
		if (!userToUpdate.getUsername().equals(user.getUsername()) && userRepository.existUsername(null)) {
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
					userToUpdate.setPassword(user.getPassword());
					userToUpdate.setRole(role);
					userRepository.updateUser(userToUpdate);
					return userToUpdate;
				} else {
					throw new MyExcMessages("Role given does not exist");
				}
			}
		}
	}

	//USER DELETION
	@Override
	public UserEntity deleteUser(int id) {
		UserEntity userToDelete = userRepository.getUserById(id);
		if (userToDelete != null) {
			if (userToDelete.getActive()) {
				userToDelete.setActive(false);
				return userRepository.updateUser(userToDelete);
			} else {
				throw new MyExcMessages("User Already deleted.");
			}

		} else
			throw new MyExcMessages("Can not delete User / Or user does not exist with given Id");
	}

}
