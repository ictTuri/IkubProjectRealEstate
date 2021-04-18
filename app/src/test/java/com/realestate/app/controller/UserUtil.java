package com.realestate.app.controller;

import com.realestate.app.dto.UserDto;

public class UserUtil {

	public static UserDto createUserOne() {
		UserDto user = new UserDto();
		user.setFirstName("test");
		user.setLastName("test");
		user.setEmail("test@gmail.com");
		user.setActive(true);
		return user;
	}
	
	public static UserDto createUserTwo() {
		UserDto user = new UserDto();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@gmail.com");
		user.setActive(true);
		return user;
	}
	
}
