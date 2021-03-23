package com.realestate.app.service;

import java.util.List;

import com.realestate.app.entity.UserEntity;


public interface UserService {

	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<UserEntity> allUsers();
	
	//FUNCTIONS TO STORE DATA TO DATABASE 
	void addOwner(UserEntity user);

	//FUNCTIONS TO UPDATE DATA ON DATABASE 
	UserEntity updateUser(UserEntity user);

	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	UserEntity deleteUser(String username);
	
}

















