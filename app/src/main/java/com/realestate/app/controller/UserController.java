package com.realestate.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserForCreateDto;
import com.realestate.app.filter.UserFilter;
import com.realestate.app.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	private static final Logger logger = LogManager.getLogger(UserController.class);

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> showAllUsers(@RequestParam(required = false) String name,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String username,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String order) {

		// show all users on database
		UserFilter filter = new UserFilter(name, lastName, username, sortBy, order);

		// LOGGING
		logger.info("Getting all users filtering by filter: {}", filter);

		return new ResponseEntity<>(userService.getUsers(filter), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> showUserById(@PathVariable("id") int id) {
		// show user by id
		return new ResponseEntity<>(userService.userById(id), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/users")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserForCreateDto user) {
		// return the added user formated by converter
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserForCreateDto user, @PathVariable("id") int id) {
		// return the updated user
		return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.CREATED);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
