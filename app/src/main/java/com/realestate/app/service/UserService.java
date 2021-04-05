package com.realestate.app.service;

import com.realestate.app.dto.UserDtoForCreate;
import com.realestate.app.entity.UserEntity;

public interface UserService {

	// FUNCTIONS TO GET DATA FROM DATABASE
	UserEntity userById(int id);
	Iterable<UserEntity> getUsers(String name);

	// FUNCTIONS TO STORE DATA TO DATABASE
	UserEntity addUser(UserDtoForCreate user);

	// FUNCTIONS TO UPDATE DATA ON DATABASE
	UserEntity updateUser(UserDtoForCreate user, int id);

	// FUNCTIONS TO DELETE DATA FROM DATABASE
	UserEntity deleteUser(int id);

}
