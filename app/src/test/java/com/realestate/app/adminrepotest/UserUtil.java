package com.realestate.app.adminrepotest;

import com.realestate.app.entity.User;

public class UserUtil {
	public static User createUser() {
		User user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setEmail("test@gmail.com");
		user.setPassword("test");
		user.setUsername("test123");
		user.setActive(Boolean.TRUE);
		return user;
	}
	
	public static User createUserAdmin() {
		User user = new User();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@gmail.com");
		user.setPassword("admin");
		user.setUsername("admin123");
		user.setActive(Boolean.TRUE);
		return user;
	}
}
