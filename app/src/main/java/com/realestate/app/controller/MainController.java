package com.realestate.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.LocationConverter;
import com.realestate.app.converter.PropertyConverter;
import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.LocationDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserDtoForCreate;
import com.realestate.app.service.PropertyService;
import com.realestate.app.service.UserService;

@RestController
@RequestMapping
public class MainController {

	UserService userService;
	PropertyService propertyService;

	@Autowired
	public MainController(UserService userService ,PropertyService propertyService) {
		super();
		this.userService = userService;
		this.propertyService = propertyService;
	}

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/users")
	public List<UserDto> showAllUsers() {
		// show all users on database
		return UserConverter.toDto(userService.allUsers());
	}
	@GetMapping("/user/{id}")
	public UserDto showUserById(@PathVariable("id") int id) {
		// show user by id
		return UserConverter.toDto(userService.userById(id));
	}
	@GetMapping("/properties")
	public List<PropertyDto> showAllProperties(){
		// show all properties on database
		return PropertyConverter.toDto(propertyService.allProperties());
	}
	@GetMapping("/property/{id}")
	public PropertyDto showPropertyById(@PathVariable("id") int id){
		// show property by id
		return PropertyConverter.toDto(propertyService.propertyById(id));
	}
	@GetMapping("/locations")
	public List<LocationDto> showAllLocations(){
		// show all locations on database
		return LocationConverter.toDto(propertyService.allLocations());
	}
	@GetMapping("/location/{id}")
	public LocationDto showLocationById(@PathVariable("id") int id){
		// show property by id
		return LocationConverter.toDto(propertyService.locationById(id));
	}
	// -----------------------------
	// GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/adduser")
	public UserDto addUser(@RequestBody UserDtoForCreate user) {
		//return the added user formated by converter
		return UserConverter.toDto(userService.addUser(user));
	}
	@PostMapping("/addproperty")
	public PropertyDto addProperty(@RequestBody PropertyDtoForCreate property) {
		//return the added property formated by converter
		return PropertyConverter.toDto(propertyService.addProperty(property));
	}
	@PostMapping("/addlocation")
	public LocationDto addLocation(@RequestBody LocationDto location) {
		//return the added location formated by converter
		return LocationConverter.toDto(propertyService.addLocation(location));
	}
	// -----------------------------
	// POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/updateuser/{id}")
	public UserDto updateUser(@RequestBody UserDtoForCreate user,@PathVariable("id") int id) {
		return UserConverter.toDto(userService.updateUser(user,id));
	}
	@PutMapping("/updateproperty/{id}")
	public PropertyDto updateProperty(@RequestBody PropertyDtoForCreate property,@PathVariable("id") int id) {
		return PropertyConverter.toDto(propertyService.updateProperty(property, id));
	}
	@PutMapping("/updatelocation/{id}")
	public LocationDto updateLocation(@RequestBody LocationDto location,@PathVariable("id") int id) {
		return LocationConverter.toDto(propertyService.updateLocation(location, id));
	}
	// -----------------------------
	// PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}
	@DeleteMapping("/deleteproperty/{id}")
	public void deleteProperty(@PathVariable("id") int id) {
		propertyService.deleteProperty(id);
	}
	@DeleteMapping("/deletelocation/{id}")
	public void deleteLocation(@PathVariable("id") int id) {
		propertyService.deleteLocation(id);
	}
	// -----------------------------
	// DELETE ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// EVERY OTHER ROUT REQUEST HANDLED BELOW
	// -----------------------------

	@RequestMapping("/*")
	public String getBack() {
		return "nothing here";
	}
}
