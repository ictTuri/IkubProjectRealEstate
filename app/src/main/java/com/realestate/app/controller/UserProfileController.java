package com.realestate.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserDtoForCreate;
import com.realestate.app.service.UserProfileService;

@RestController
@RequestMapping("api/v1/user")
public class UserProfileController {

	UserProfileService userPorfileService;

	@Autowired
	public UserProfileController(UserProfileService userPorfileService) {
		super();
		this.userPorfileService = userPorfileService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','OWNER','CLIENT')")
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getLoggedUser() {
		
		return new ResponseEntity<>(UserConverter.toDto(userPorfileService.getLoggedUser()), HttpStatus.FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','OWNER','CLIENT')")
	@PutMapping("/profile")
	public ResponseEntity<UserDto> updateLoggedUser(@Valid @RequestBody UserDtoForCreate user) {

		return new ResponseEntity<>(UserConverter.toDto(userPorfileService.updateLoggedUser(user)), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','OWNER','CLIENT')")
	@DeleteMapping("/profile")
	public String deleteLoggedUser() {

		return userPorfileService.deleteLoggedUser(); 
	}

}
