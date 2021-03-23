package com.realestate.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.entity.UserEntity;
import com.realestate.app.service.UserService;

@RestController
@RequestMapping
public class MainController {

	UserService adminService;

	public MainController(UserService adminServ) {
		super();
		this.adminService = adminServ;
	}

	// -----------------------------
	// ADMIN GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/users")
	public List<UserEntity> showAllUsers() {
		// show all users on database
		return adminService.allUsers();
	}
	// -----------------------------
	// ADMIN GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// ADMIN POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/addowner")
	public void addOwner(@RequestBody UserEntity user) {
		user.setActive(true);
		adminService.addOwner(user);
	}
	// -----------------------------
	// ADMIN POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// ADMIN PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/updateuser")
	public UserEntity updateUser(@RequestBody UserEntity user) {
		return adminService.updateUser(user);
	}
	// -----------------------------
	// ADMIN PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// ADMIN DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/deleteuser/{username}")
	public void deleteUser(@PathVariable("username") String username) {
		adminService.deleteUser(username);
	}
	// -----------------------------
	// ADMIN DELETE ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// EVERY OTHER ROUT REQUEST HANDLED BELOW
	// -----------------------------

	@RequestMapping("/*")
	public String getBack() {
		return "nothing here";
	}
}
