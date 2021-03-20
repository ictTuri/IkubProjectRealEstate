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

import com.realestate.app.model.BoughtModel;
import com.realestate.app.model.IssuesModel;
import com.realestate.app.model.PropertyModel;
import com.realestate.app.model.RentedModel;
import com.realestate.app.model.UserModel;
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
	public PropertyModel showProperty(@PathVariable("id") int id) {
		return userService.getProperty(id);
	}
	@GetMapping("/properties")
	public List<PropertyModel> showAllProperty() {
		return ownerService.allProperties();
	}
	@GetMapping("/rented")
	public List<RentedModel> showRented() {
		return userService.showRented();
	}
	@GetMapping("/bought")
	public List<BoughtModel> showBought() {
		return userService.showBought();
	}
	@GetMapping("/profile")
	public UserModel showUser() {
		return ownerService.showProfile();
	}
	// -----------------------------
	// CLIENT GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// CLIENT POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/register")
	public void registerClient(@RequestBody UserModel user) {
		adminService.addClient(user);
	}
	@PostMapping("/issue")
	public void createIssue(@RequestBody IssuesModel issue) {
		userService.createIssue(issue);
	}
	// -----------------------------
	// CLIENT POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// CLIENT PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/updateprofile")
	public UserModel updateProfile() {
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
