package com.realestate.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserDtoForCreate;
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
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> showAllUsers(@RequestParam(required = false) String name) {
		// show all users on database
		logger.warn("attempt to get users");
		List<UserDto> toReturn = new ArrayList<>();
		userService.getUsers(name).forEach(entity -> toReturn.add(UserConverter.toDto(entity)));
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> showUserById(@PathVariable("id") int id) {
		// show user by id
		return new ResponseEntity<>(UserConverter.toDto(userService.userById(id)), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/users/new")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDtoForCreate user) {
		// return the added user formated by converter
		return new ResponseEntity<>(UserConverter.toDto(userService.addUser(user)), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/users/edit/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDtoForCreate user, @PathVariable("id") int id) {
		return new ResponseEntity<>(UserConverter.toDto(userService.updateUser(user, id)), HttpStatus.CREATED);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/users/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}

	// -----------------------------
	// EVERY OTHER ROUT REQUEST HANDLED BELOW
	// -----------------------------
	@RequestMapping("/*")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String getBack() {
		return "nothing here";
	}
}
