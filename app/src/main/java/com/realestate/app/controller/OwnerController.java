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
import com.realestate.app.entity.Location;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.PropertyInfo;
import com.realestate.app.entity.Rented;
import com.realestate.app.entity.User;
import com.realestate.app.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	OwnerService ownerService;

	public OwnerController(OwnerService ownerServ) {
		super();
		this.ownerService = ownerServ;
	}

	// -----------------------------
	// OWNER GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/properties/{username}")
	public List<Property> showProperties(@PathVariable("username") String username) {
		return ownerService.allOwnerProperties(username);
	}

	@GetMapping("/allproperties")
	public List<Property> showProperties() {
		//Get all properties from DB
		return ownerService.allProperties();
	}

	@GetMapping("/users")
	public List<User> showAllUsers() {
		//get users that have rent properties of the owner
		return ownerService.allOwnerRelatedUsers();
	}

	@GetMapping("/rented")
	public List<Rented> showAllRented() {
		// show rented of owner properties
		return ownerService.allRented();
	}

	@GetMapping("/sold")
	public List<Bought>  showAllBought() {
		// show sold of owner properties
		return ownerService.allSold();
	}

	@GetMapping("/issues")
	public List<Issues> showAllIssues() {
		// show issues of owner properties
		return ownerService.allIssues();
	}

	@GetMapping("/issues/{id}")
	public Issues showIssueFromId(@PathVariable("id") int id) {
		// show issue with passed id
		return ownerService.issueById(id);
	}
	//new route profiles added after flowchart
	@GetMapping("/profile")
	public User showProfile() {
		// show owner profile
		return ownerService.showProfile();
	}

	// -----------------------------
	// OWNER GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// OWNER POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/addproperty")
	public void addProperty(@RequestBody Property property) {
		ownerService.addProperty(property);
	}

	@PostMapping("/addrented")
	public void addRented(@RequestBody Rented rented) {
		ownerService.addRented(rented);
	}

	@PostMapping("/addbought")
	public void addBought(@RequestBody Bought bought) {
		ownerService.addBought(bought);
	}

	@PostMapping("/addlocations")
	public void addLocation(@RequestBody Location location) {
		ownerService.addLocation(location);
	}

	// -----------------------------
	// OWNER POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// OWNER PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/updateprofile")
	public User updateProfile() {
		return ownerService.updateProfile();
	}

	@PutMapping("/updateproperty/{id}")
	public Property updateProperty(@PathVariable("id") int id) {
		return ownerService.updateProperty(id);
	}

	@PutMapping("/updaterented/{id}")
	public Rented updateRented(@PathVariable("id") int id) {
		return ownerService.updateRented(id);
	}

	@PutMapping("/updatepropertyinfo/{id}")
	public PropertyInfo updatePropertyInfo(@PathVariable("id") int id) {
		return ownerService.updatePropertyInfo(id);
	}

	@PutMapping("/updateissues/{id}")
	public Issues updateIssues(@PathVariable("id") int id) {
		return ownerService.updateIssues(id);
	}

	// -----------------------------
	// OWNER PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// OWNER DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/deleteuser/{username}")
	public void deleteUser(@PathVariable("username") String username) {
		ownerService.deleteUser(username);
	}

	@DeleteMapping("/deleteproperty/{id}")
	public void deleteProperty(@PathVariable("id") int id) {
		ownerService.deleteProperty(id);
	}

	@DeleteMapping("/deleterented/{id}")
	public void deleteRented(@PathVariable("id") int id) {
		ownerService.deleteRented(id);
	}

	@DeleteMapping("/deletelocations/{id}")
	public void deleteLocation(@PathVariable("id") int id) {
		ownerService.deleteLocation(id);
	}

	@DeleteMapping("/deletebought/{id}")
	public void deleteBought(@PathVariable("id") int id) {
		ownerService.deleteBought(id);
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
