package com.realestate.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserForCreateDto;
import com.realestate.app.service.UserProfileService;

@RestController
@RequestMapping("api/v1/user/profile")
public class UserProfileController {

	UserProfileService userPorfileService;

	@Autowired
	public UserProfileController(UserProfileService userPorfileService) {
		super();
		this.userPorfileService = userPorfileService;
	}

	@GetMapping()
	public ResponseEntity<UserDto> getLoggedUser() {
		// return the profile (logged in user data)
		return new ResponseEntity<>(userPorfileService.getLoggedUser(), HttpStatus.FOUND);
	}
	
	@PutMapping()
	public ResponseEntity<UserDto> updateLoggedUser(@Valid @RequestBody UserForCreateDto user) {
		// update the profile (logged in user)
		return new ResponseEntity<>(userPorfileService.updateLoggedUser(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> deleteLoggedUser() {
		// Delete profile (logged in user)
		userPorfileService.deleteLoggedUser(); 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
