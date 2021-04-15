package com.realestate.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.dto.UserRegisterDto;
import com.realestate.app.service.UserProfileService;

@RestController
@RequestMapping("/api/register/user")
public class RegisterController {

	UserProfileService userProfileService;
	
	@Autowired
	public RegisterController(UserProfileService userProfileService) {
		super();
		this.userProfileService = userProfileService;
	}

	@PostMapping()
	public ResponseEntity<UserRegisterDto> addClient(@Valid @RequestBody UserRegisterDto user) {
		// return the added user formated by converter
		return new ResponseEntity<>(userProfileService.addUser(user), HttpStatus.CREATED);
	}
	
}
