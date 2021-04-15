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

import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.service.UserPropertyService;

@RestController
@RequestMapping("/api/v1/property/myproperties")
public class UserPropertyController {

	UserPropertyService userPropertyService;

	@Autowired
	public UserPropertyController(UserPropertyService userPropertyService) {
		super();
		this.userPropertyService = userPropertyService;
	}
	
	@PreAuthorize("hasAnyRole('OWNER')")
	@GetMapping()
	public ResponseEntity<List<PropertyDto>> showAllMyProperties(){
		// Return all owner properties
		return new ResponseEntity<>(userPropertyService.showAllMyProperties(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('OWNER')")
	@PostMapping()
	public ResponseEntity<PropertyDto> insertMyProperties(@Valid @RequestBody FullPropertyDto property){
		//Insert new property
		return new ResponseEntity<>(userPropertyService.insertMyProperty(property),HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('OWNER')")
	@PutMapping("/{id}")
	public ResponseEntity<PropertyDto> updateMyProperties(@Valid @RequestBody FullPropertyDto property, @PathVariable int id){
		
		property.setCategory(property.getCategory().toUpperCase());
		return new ResponseEntity<>(userPropertyService.updateMyProperty(property, id),HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('OWNER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMyProperties(@PathVariable int id){
		userPropertyService.deleteMyProperty(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
