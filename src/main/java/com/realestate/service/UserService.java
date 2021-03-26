package com.realestate.service;

import com.realestate.dto.UserDtoForCreate;
import com.realestate.model.UserEntity;

public interface UserService {
	
	public UserEntity addUser(UserDtoForCreate user);
	
	public void deleteUser(long id);
	
	public void testTransaction();

	

}
