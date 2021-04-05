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

import com.realestate.app.converter.PropertyConverter;
import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.dto.UserDto;
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

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/properties")
	public ResponseEntity<List<PropertyDto>> showAllProperties() {
		// show all properties on database
		return new ResponseEntity<>(PropertyConverter.toDto(propertyService.allProperties()), HttpStatus.OK);
	}

	@GetMapping("/properties/{id}")
	public ResponseEntity<PropertyDto> showPropertyById(@PathVariable("id") int id) {
		// show property by id
		return new ResponseEntity<>(PropertyConverter.toDto(propertyService.propertyById(id)), HttpStatus.FOUND);
	}

	@GetMapping("/properties/{id}/owner")
	public ResponseEntity<UserDto> showPropertyOwner(@PathVariable("id") int id) {
		// show property by id
		return new ResponseEntity<>(UserConverter.toDto(propertyService.propertyOwner(id)), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/properties/new")
	public ResponseEntity<PropertyDto> addProperty(@Valid @RequestBody PropertyDtoForCreate property) {
		// return the added property formated by converter
		return new ResponseEntity<>(PropertyConverter.toDto(propertyService.addProperty(property)), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/properties/edit/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@Valid @RequestBody PropertyDtoForCreate property,
			@PathVariable("id") int id) {
		return new ResponseEntity<>(PropertyConverter.toDto(propertyService.updateProperty(property, id)),
				HttpStatus.CREATED);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/properties/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProperty(@PathVariable("id") int id) {
		propertyService.deleteProperty(id);
	}

}
