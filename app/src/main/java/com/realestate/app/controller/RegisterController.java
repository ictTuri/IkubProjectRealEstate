package com.realestate.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserDtoForCreate;
import com.realestate.app.service.UserService;

@RestController
@RequestMapping("/api")
public class RegisterController {

	UserService userService;
	
	@Autowired
	public RegisterController(UserService userService) {
		super();
		this.userService = userService;
	}


	@PostMapping("/register/client")
	public ResponseEntity<UserDto> addClient(@Valid @RequestBody UserDtoForCreate user) {
		// return the added user formated by converter
		user.setRole(3);
		return new ResponseEntity<>(UserConverter.toDto(userService.addUser(user)), HttpStatus.CREATED);
	}
	@PostMapping("/register/owner")
	public ResponseEntity<UserDto> addOwner(@Valid @RequestBody UserDtoForCreate user) {
		// return the added user formated by converter
		user.setRole(2);
		return new ResponseEntity<>(UserConverter.toDto(userService.addUser(user)), HttpStatus.CREATED);
	}
	
}
