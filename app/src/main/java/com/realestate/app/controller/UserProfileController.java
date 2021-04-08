package com.realestate.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.UserDto;
import com.realestate.app.security.UserPrincipal;
import com.realestate.app.service.UserProfileService;

@RestController
@RequestMapping("api/v1/users")
public class UserProfileController {

	UserProfileService userPorfileService;

	@Autowired
	public UserProfileController(UserProfileService userPorfileService) {
		super();
		this.userPorfileService = userPorfileService;
	}

	@GetMapping("/profile")
	public ResponseEntity<UserDto> getLoggedUser(Authentication authentication) {
		UserPrincipal thisUser = (UserPrincipal) authentication.getPrincipal();
		return new ResponseEntity<>(UserConverter.toDto(userPorfileService.getLoggedUser(thisUser.getUsername())), HttpStatus.FOUND);
	}

}
