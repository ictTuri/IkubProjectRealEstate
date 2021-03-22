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

import com.realestate.app.entity.Location;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.PropertyInfo;
import com.realestate.app.entity.Rented;
import com.realestate.app.entity.Role;
import com.realestate.app.entity.User;
import com.realestate.app.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	AdminService adminService;

	public AdminController(AdminService adminServ) {
		super();
		this.adminService = adminServ;
	}

	// -----------------------------
	// ADMIN GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/properties")
	public List<Property> showProperties() {
		// show all properties on database
		return adminService.allProperties();
	}

	@GetMapping("/users")
	public List<User> showAllUsers() {
		// show all users on database
		return adminService.allUsers();
	}
	
	@GetMapping("/users/{id}")
	public User showUserById(@PathVariable("id") Integer id) {
		// show all users on database
		return adminService.showUserById(id);
	}

	@GetMapping("/owners")
	public List<User> showAllOwners() {
		// show all users that are owners
		return adminService.allOwners();
	}

	@GetMapping("/clients")
	public List<User> showAllClients() {
		// show all users that are clients
		return adminService.allClients();
	}

	// -----------------------------
	// ADMIN GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// ADMIN POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/addowner")
	public void addOwner(@RequestBody User user) {
		user.setRoleId(new Role(3));
		user.setActive(true);
		adminService.addOwner(user);
	}

	@PostMapping("/addproperty")
	public void addProperty(@RequestBody Property property) {
		adminService.addProperty(property);
	}

	@PostMapping("/addclient")
	public void addClient(@RequestBody User user) {
		adminService.addClient(user);
	}

	@PostMapping("/addlocations")
	public void addLocation(@RequestBody Location location) {
		adminService.addLocation(location);
	}

	// -----------------------------
	// ADMIN POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// ADMIN PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/updateuser")
	public User updateUser(@RequestBody User user) {
		return adminService.updateUser(user);
	}

	@PutMapping("/updateproperty/{id}")
	public Property updateProperty(@PathVariable("id") int id) {
		return adminService.updateProperty(id);
	}

	@PutMapping("/updaterented/{id}")
	public Rented updateRented(@PathVariable("id") int id) {
		return adminService.updateRented(id);
	}

	@PutMapping("/updatelocations/{id}")
	public Location updateLocation(@PathVariable("id") int id) {
		return adminService.updateLocation(id);
	}

	@PutMapping("/updatepropertyinfo/{id}")
	public PropertyInfo updatePropertyInfo(@PathVariable("id") int id) {
		return adminService.updatePropertyInfo(id);
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

	@DeleteMapping("/deleteproperty/{id}")
	public void deleteProperty(@PathVariable("id") int id) {
		adminService.deleteProperty(id);
	}

	@DeleteMapping("/deleterented/{id}")
	public void deleteRented(@PathVariable("id") int id) {
		adminService.deleteRented(id);
	}

	@DeleteMapping("/deletelocations/{id}")
	public void deleteLocation(@PathVariable("id") int id) {
		adminService.deleteLocation(id);
	}

	@DeleteMapping("/deletebought/{id}")
	public void deleteBought(@PathVariable("id") int id) {
		adminService.deleteBought(id);
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
