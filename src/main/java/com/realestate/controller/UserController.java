package com.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.converter.UserConverter;
import com.realestate.dto.UserDto;
import com.realestate.dto.UserDtoForCreate;
import com.realestate.service.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	UserService userservice;
	
//	
//	@DeleteMapping("/deleteUser/{id}")
//	public void deleteUser(@PathVariable long id) {
//		UserService.deleteUser(id);
//	}
//	
//	@PostMapping("/addUser")
//	public UserDto addUser(@RequestBody UserDtoForCreate user) {
//		return UserConverter.toDto(userService.addUser(user));
//	}
//	

}
