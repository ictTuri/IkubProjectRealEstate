package com.realestate.app.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.FullPropertyConverter;
import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.UserDto;
import com.realestate.app.filter.PropertyFilter;
import com.realestate.app.service.PropertyService;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

	PropertyService propertyService;
	
	@Autowired
	public PropertyController(PropertyService propertyService) {
		super();
		this.propertyService = propertyService;
	}

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/properties")
	public ResponseEntity<List<PropertyDto>> showAllProperties(@RequestParam(required = false) String category,
			@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String order) {
		
		// show all properties on database
		List<PropertyDto> toReturn = new ArrayList<>();
		PropertyFilter filter = new PropertyFilter(category, min, max, city, sortBy, order);
		propertyService.getAllProperties(filter).forEach(entity -> toReturn.add(FullPropertyConverter.singleToDto(entity)));
		logger.info("Getting all properties filtering by filter: {}", filter);
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

	@GetMapping("/properties/{id}")
	public ResponseEntity<PropertyDto> showPropertyById(@PathVariable("id") int id) {
		// show property by id
		return new ResponseEntity<>(FullPropertyConverter.singleToDto(propertyService.propertyById(id)), HttpStatus.FOUND);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/properties/{id}/owner")
	public ResponseEntity<UserDto> showPropertyOwner(@PathVariable("id") int id) {
		// show property by id
		return new ResponseEntity<>(UserConverter.toDto(propertyService.propertyOwner(id)), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
	@PostMapping("/properties")
	public ResponseEntity<PropertyDto> addProperty(@Valid @RequestBody FullPropertyDto property) {
		// return the added property formated by converter
		return new ResponseEntity<>(FullPropertyConverter.singleToDto(propertyService.addProperty(property)), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/properties/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@Valid @RequestBody FullPropertyDto property,
			@PathVariable("id") int id) {
		return new ResponseEntity<>(FullPropertyConverter.singleToDto(propertyService.updateProperty(property, id)),
				HttpStatus.CREATED);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/properties/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProperty(@PathVariable("id") int id) {
		propertyService.deleteProperty(id);
	}

	// -----------------------------
	// EVERY OTHER ROUT REQUEST HANDLED BELOW
	// -----------------------------
	@RequestMapping("properties/*")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String getBack() {
		return "nothing here";
	}

}
