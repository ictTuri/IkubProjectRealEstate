package com.realestate.app.repository.test;

import com.realestate.app.entity.UserEntity;

public class UserUtil {
	
	public static UserEntity createUser() {
		UserEntity user = new UserEntity();
		user.setFirstName("test");
		user.setLastName("test");
		user.setEmail("test@gmail.com");
		user.setPassword("test");
		user.setUsername("test");
		user.setActive(true);
		return user;
	}
	
	public static UserEntity createUserAdmin() {
		UserEntity user = new UserEntity();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@gmail.com");
		user.setPassword("admin");
		user.setUsername("admin");
		user.setActive(true);
		return user;
	}
}
