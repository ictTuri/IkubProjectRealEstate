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

import com.realestate.app.dto.AdminFullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.UserDto;
import com.realestate.app.filter.PropertyFilter;
import com.realestate.app.service.PropertyService;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

	PropertyService propertyService;
	
	@Autowired
	public PropertyController(PropertyService propertyService) {
		super();
		this.propertyService = propertyService;
	}

	private static final Logger logger = LogManager.getLogger(PropertyController.class);
	
	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping()
	public ResponseEntity<List<PropertyDto>> showAllProperties(@RequestParam(required = false) String category,
			@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max,
			 @RequestParam(required = false) String city,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String order) {
		
		// show all properties on database
		PropertyFilter filter = new PropertyFilter(category, min, max, city, sortBy, order);
		
		//LOGGING
		logger.info("Getting all properties filtering by filter: {}", filter);
		
		return new ResponseEntity<>(propertyService.getAllProperties(filter), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropertyDto> showPropertyById(@PathVariable("id") int id) {
		// show property by id
		return new ResponseEntity<>(propertyService.propertyById(id), HttpStatus.FOUND);
	}

	@GetMapping("/{id}/owner")
	public ResponseEntity<UserDto> showPropertyOwner(@PathVariable("id") int id) {
		// show property by id
		return new ResponseEntity<>(propertyService.propertyOwner(id), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping()
	public ResponseEntity<PropertyDto> addProperty(@Valid @RequestBody AdminFullPropertyDto property) {
		// return the added property formated by converter
		return new ResponseEntity<>(propertyService.addProperty(property), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@Valid @RequestBody AdminFullPropertyDto property,
			@PathVariable("id") int id) {
		// return the updated property 
		return new ResponseEntity<>(propertyService.updateProperty(property, id),HttpStatus.CREATED);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProperty(@PathVariable("id") int id) {
		// delete property by id
		propertyService.deleteProperty(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
