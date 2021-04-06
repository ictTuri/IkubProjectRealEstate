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

import com.realestate.app.converter.PropertyTypeConverter;
import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.service.TypeService;

@RestController
@RequestMapping("/api/v1")
public class TypeController {
	
	TypeService typeService;

	@Autowired
	public TypeController(TypeService typeService) {
		super();
		this.typeService = typeService;
	}

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/propertytypes")
	public ResponseEntity<List<PropertyTypeDto>> propertyTypes() {
		// show all property types on database
		return new ResponseEntity<>(PropertyTypeConverter.toDto(typeService.allPropertyTypes()), HttpStatus.OK);
	}

	@GetMapping("/propertytypes/{id}")
	public ResponseEntity<PropertyTypeDto> showPropertTypeById(@PathVariable("id") int id) {
		// show property type by id
		return new ResponseEntity<>(PropertyTypeConverter.toDto(typeService.propertyTypeById(id)),
				HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/propertytypes")
	public ResponseEntity<PropertyTypeDto> addPropertyType(@Valid @RequestBody PropertyTypeDto propertyType) {
		// return the added property type formated by converter
		return new ResponseEntity<>(PropertyTypeConverter.toDto(typeService.addPropertyType(propertyType)),
				HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/propertytypes/{id}")
	public ResponseEntity<PropertyTypeDto> updatePropertyType(@Valid @RequestBody PropertyTypeDto propertyType,
			@PathVariable("id") int id) {
		// return the updated property type if process complete
		return new ResponseEntity<>(PropertyTypeConverter.toDto(typeService.updatePropertyType(propertyType, id)),
				HttpStatus.OK);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/propertytypes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePropertyType(@PathVariable("id") int id) {
		// delete property type if not used
		typeService.deletePropertyType(id);
	}
	
	// -----------------------------
	// EVERY OTHER ROUT REQUEST HANDLED BELOW
	// -----------------------------
	@RequestMapping("propertytypes/*")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String getBack() {
		return "nothing here";
	}
}
