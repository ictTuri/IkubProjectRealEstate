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
import com.realestate.app.dto.LocationDto;
import com.realestate.app.service.LocationService;

@RestController
@RequestMapping("/api/v1")
public class LocationController {
	
	LocationService propertyService;

	@Autowired
	public LocationController(LocationService propertyService) {
		super();
		this.propertyService = propertyService;
	}

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/locations")
	public ResponseEntity<List<LocationDto>> showAllLocations() {
		// show all locations on database
		return new ResponseEntity<>(LocationConverter.toDto(propertyService.allLocations()), HttpStatus.OK);
	}

	@GetMapping("/locations/{id}")
	public ResponseEntity<LocationDto> showLocationById(@PathVariable("id") int id) {
		// show location by id
		return new ResponseEntity<>(LocationConverter.toDto(propertyService.locationById(id)), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/locations/new")
	public ResponseEntity<LocationDto> addLocation(@Valid @RequestBody LocationDto location) {
		// return the added location formated by converter
		return new ResponseEntity<>(LocationConverter.toDto(propertyService.addLocation(location)), HttpStatus.CREATED);
	}
	
	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/locations/edit/{id}")
	public ResponseEntity<LocationDto> updateLocation(@Valid @RequestBody LocationDto location,
			@PathVariable("id") int id) {
		return new ResponseEntity<>(LocationConverter.toDto(propertyService.updateLocation(location, id)),
				HttpStatus.CREATED);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/locations/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLocation(@PathVariable("id") int id) {
		propertyService.deleteLocation(id);
	}
}
