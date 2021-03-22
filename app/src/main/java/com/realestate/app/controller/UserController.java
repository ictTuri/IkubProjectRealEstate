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

import com.realestate.app.entity.Bought;
import com.realestate.app.entity.Issues;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.Rented;
import com.realestate.app.entity.User;
import com.realestate.app.service.AdminService;
import com.realestate.app.service.OwnerService;
import com.realestate.app.service.UserService;

@RestController
@RequestMapping("/client")
public class UserController {

	UserService userService;
	OwnerService ownerService;
	AdminService adminService;
	
	public UserController(UserService userServ, OwnerService ownerServ, AdminService adminServ) {
		super();
		this.userService = userServ;
		this.ownerService = ownerServ;
		this.adminService = adminServ;
	}
	// -----------------------------
	// CLIENT GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/property/{id}")
	public Property showProperty(@PathVariable("id") int id) {
		return userService.getProperty(id);
	}
	@GetMapping("/properties")
	public List<Property> showAllProperty() {
		return ownerService.allProperties();
	}
	@GetMapping("/rented")
	public List<Rented> showRented() {
		return userService.showRented();
	}
	@GetMapping("/bought")
	public List<Bought> showBought() {
		return userService.showBought();
	}
	@GetMapping("/profile")
	public User showUser() {
		return ownerService.showProfile();
	}
	// -----------------------------
	// CLIENT GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// CLIENT POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/register")
	public void registerClient(@RequestBody User user) {
		adminService.addClient(user);
	}
	@PostMapping("/issue")
	public void createIssue(@RequestBody Issues issue) {
		userService.createIssue(issue);
	}
	// -----------------------------
	// CLIENT POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// CLIENT PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/updateprofile")
	public User updateProfile() {
		return ownerService.updateProfile();
	}
	// -----------------------------
	// CLIENT PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// CLIENT DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/deleteissues/{id}")
	public void deleteIssue(@PathVariable("id") int id) {
		userService.deleteIssue(id);
	}
	// -----------------------------
	// CLIENT DELETE ROUTES ENDS HERE
	// -----------------------------
	
	@RequestMapping("*")
	public String getBack() {
		return "nothing here";
	}
}
