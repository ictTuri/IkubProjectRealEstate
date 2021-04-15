package com.realestate.app.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.dto.LocationDto;
import com.realestate.app.service.LocationService;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

	LocationService locationService;

	@Autowired
	public LocationController(LocationService locationService) {
		super();
		this.locationService = locationService;
	}

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
	@GetMapping()
	public ResponseEntity<List<LocationDto>> showAllLocations() {
		// show all locations on database
		return new ResponseEntity<>(locationService.allLocations(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
	@GetMapping("/{id}")
	public ResponseEntity<LocationDto> showLocationById(@PathVariable("id") int id) {
		// show location by id
		return new ResponseEntity<>(locationService.locationById(id), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN','OWNER')")
	@PostMapping()
	public ResponseEntity<LocationDto> addLocation(@Valid @RequestBody LocationDto location) {
		// return the added location formated by converter
		return new ResponseEntity<>(locationService.addLocation(location), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<LocationDto> updateLocation(@Valid @RequestBody LocationDto location,
			@PathVariable("id") int id) {
		return new ResponseEntity<>(locationService.updateLocation(location, id), HttpStatus.CREATED);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLocation(@PathVariable("id") int id) {
		locationService.deleteLocation(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
