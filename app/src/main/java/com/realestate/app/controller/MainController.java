package com.realestate.app.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
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
@RequestMapping("/realestate/api/v1")
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
	public ResponseEntity<List<UserDto>> showAllUsers() {
		// show all users on database
		return new ResponseEntity<>(UserConverter.toDto(userService.allUsers()), HttpStatus.OK); 
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> showUserById(@PathVariable("id") int id) {
		// show user by id
		return new ResponseEntity<>(UserConverter.toDto(userService.userById(id)),HttpStatus.FOUND);
	}
	@GetMapping("/properties")
	public ResponseEntity<List<PropertyDto>> showAllProperties(){
		// show all properties on database
		return new ResponseEntity<> (PropertyConverter.toDto(propertyService.allProperties()),HttpStatus.OK);
	}
	@GetMapping("/properties/{id}")
	public ResponseEntity<PropertyDto> showPropertyById(@PathVariable("id") int id){
		// show property by id
		return new ResponseEntity<> (PropertyConverter.toDto(propertyService.propertyById(id)),HttpStatus.FOUND);
	}
	@GetMapping("/locations")
	public ResponseEntity<List<LocationDto>> showAllLocations(){
		// show all locations on database
		return new ResponseEntity<> (LocationConverter.toDto(propertyService.allLocations()),HttpStatus.OK);
	}
	@GetMapping("/locations/{id}")
	public ResponseEntity<LocationDto> showLocationById(@PathVariable("id") int id){
		// show location by id
		return new ResponseEntity<> (LocationConverter.toDto(propertyService.locationById(id)), HttpStatus.FOUND);
	}
	// -----------------------------
	// GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/users")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDtoForCreate user) {
		//return the added user formated by converter
		return new ResponseEntity<>(UserConverter.toDto(userService.addUser(user)),HttpStatus.CREATED);
	}
	@PostMapping("/properties")
	public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDtoForCreate property) {
		//return the added property formated by converter
		return new ResponseEntity<> (PropertyConverter.toDto(propertyService.addProperty(property)),HttpStatus.CREATED);
	}
	@PostMapping("/locations")
	public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto location) {
		//return the added location formated by converter
		return new ResponseEntity<> (LocationConverter.toDto(propertyService.addLocation(location)),HttpStatus.CREATED);
	}
	// -----------------------------
	// POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDtoForCreate user,@PathVariable("id") int id) {
		return new ResponseEntity<> (UserConverter.toDto(userService.updateUser(user,id)),HttpStatus.CREATED);
	}
	@PutMapping("/properties/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@Valid @RequestBody PropertyDtoForCreate property,@PathVariable("id") int id) {
		return new ResponseEntity<> (PropertyConverter.toDto(propertyService.updateProperty(property, id)), HttpStatus.CREATED);
	}
	@PutMapping("/locations/{id}")
	public ResponseEntity<LocationDto> updateLocation(@Valid @RequestBody LocationDto location,@PathVariable("id") int id) {
		return new ResponseEntity<> (LocationConverter.toDto(propertyService.updateLocation(location, id)), HttpStatus.CREATED);
	}
	// -----------------------------
	// PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}
	@DeleteMapping("/properties/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProperty(@PathVariable("id") int id) {
		propertyService.deleteProperty(id);
	}
	@DeleteMapping("/locations/{id}")
	@ResponseStatus(HttpStatus.OK)
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
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String getBack() {
		return "nothing here";
	}
}
