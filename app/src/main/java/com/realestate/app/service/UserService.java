package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserForCreateDto;
import com.realestate.app.filter.UserFilter;

public interface UserService {

	// FUNCTIONS TO GET DATA FROM DATABASE
	UserDto userById(int id);
	List<UserDto> getUsers(UserFilter filter);

	// FUNCTIONS TO STORE DATA TO DATABASE
	UserDto addUser(UserForCreateDto user);

	// FUNCTIONS TO UPDATE DATA ON DATABASE
	UserDto updateUser(UserForCreateDto user, int id);

	// FUNCTIONS TO DELETE DATA FROM DATABASE
	void deleteUser(int id);

}
