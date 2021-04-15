package com.realestate.app.repository;

import java.util.List;

import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.filter.UserFilter;

public interface UserRepository {
	// GET INFOS FROM DATABASE
	List<UserEntity> getAllUsers(UserFilter filter);
	UserEntity getUserById(int userId);
	RoleEntity getRoleById(int roleId);
	
	// INSERT NEW INFO INTO DATABASE
	void insertUser(UserEntity user);
	
	// UPDATE INFO ON DATABASE
	UserEntity updateUser(UserEntity user);
	
	// DELETE INFO FROM DATABASE
	UserEntity deleteUser(UserEntity user);
	
	// HELPING METHODS 
	List<UserEntity> getFilterByName(String name);
	boolean existUsername(String username);
	boolean existEmail(String email);
	boolean existUserById(int id, RoleEntity role);
	UserEntity getUserByUsername(String username);
	boolean isActiveUser(String username);
	 boolean isClient(String username, RoleEntity role);
	 
}
