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

import com.realestate.app.converter.PropertyInfoConverter;
import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.service.InfoService;

@RestController
@RequestMapping("/api/v1")
public class InfoController {
	
	InfoService propertyService;

	@Autowired
	public InfoController(InfoService propertyService) {
		super();
		this.propertyService = propertyService;
	}

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/propertyinfos")
	public ResponseEntity<List<PropertyInfoDto>> showAllPropertyInfos() {
		// show all property Info on database
		return new ResponseEntity<>(PropertyInfoConverter.toDto(propertyService.allPropertyInfos()), HttpStatus.OK);
	}

	@GetMapping("/propertyinfos/{id}")
	public ResponseEntity<PropertyInfoDto> showPropertInfoById(@PathVariable("id") int id) {
		// show property info by id
		return new ResponseEntity<>(PropertyInfoConverter.toDto(propertyService.propertyInfoById(id)),
				HttpStatus.FOUND);
	}
	
	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/propertyinfos/new")
	public ResponseEntity<PropertyInfoDto> addPropertyInfo(@Valid @RequestBody PropertyInfoDto propertyInfo) {
		// return the added property info formated by converter
		return new ResponseEntity<>(PropertyInfoConverter.toDto(propertyService.addPropertyInfo(propertyInfo)),
				HttpStatus.CREATED);
	}
	
	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/propertyinfos/edit/{id}")
	public ResponseEntity<PropertyInfoDto> updatePropertyInfo(@Valid @RequestBody PropertyInfoDto propertyInfo,
			@PathVariable("id") int id) {
		// return the updated property info if process complete
		return new ResponseEntity<>(PropertyInfoConverter.toDto(propertyService.updatePropertyInfo(propertyInfo, id)),
				HttpStatus.OK);
	}
	
	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/propertyinfos/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePropertyInfo(@PathVariable("id") int id) {
		// delete property info if no property assigned it
		propertyService.deletePropertyInfo(id);
	}
}
