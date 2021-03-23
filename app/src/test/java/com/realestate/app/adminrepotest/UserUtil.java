package com.realestate.app.adminrepotest;

import com.realestate.app.entity.UserEntity;

public class UserUtil {
	public static UserEntity createUser() {
		UserEntity user = new UserEntity();
		user.setFirstName("test");
		user.setLastName("test");
		user.setEmail("test@gmail.com");
		user.setPassword("test");
		user.setUsername("test123");
		user.setActive(Boolean.TRUE);
		return user;
	}
	
	public static UserEntity createUserAdmin() {
		UserEntity user = new UserEntity();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@gmail.com");
		user.setPassword("admin");
		user.setUsername("admin123");
		user.setActive(Boolean.TRUE);
		return user;
	}
}
