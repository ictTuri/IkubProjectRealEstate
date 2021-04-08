package com.realestate.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.FullPropertyConverter;
import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.service.UserPropertyService;

@RestController
@RequestMapping("/api/v1/property")
public class UserPropertyController {

	UserPropertyService userPropertyService;

	@Autowired
	public UserPropertyController(UserPropertyService userPropertyService) {
		super();
		this.userPropertyService = userPropertyService;
	}
	
	@PreAuthorize("hasAnyRole('OWNER')")
	@GetMapping("/myproperties")
	public ResponseEntity<List<PropertyDto>> showAllMyProperties(){
		List<PropertyDto> toReturn = new ArrayList<>();
		userPropertyService.showAllMyProperties().forEach(entity -> toReturn.add(FullPropertyConverter.singleToDto(entity)));
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('OWNER')")
	@PutMapping("/myproperties/{id}")
	public ResponseEntity<PropertyDto> updateMyProperties(@Valid @RequestBody FullPropertyDto property, @PathVariable int id){
		return new ResponseEntity<>(FullPropertyConverter.singleToDto(userPropertyService.updateMyProperty(property, id)),HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('OWNER')")
	@DeleteMapping("/myproperties/{id}")
	public void deleteMyProperties(@PathVariable int id){
		userPropertyService.deleteMyProperty(id);
	}
	
	
}
