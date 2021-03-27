package com.realestate.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.PropertyInfoConverter;
import com.realestate.app.converter.PropertyTypeConverter;
import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.service.PropertyService;

@RestController
@RequestMapping("/additional")
public class AdditionalController {

	@Autowired
	PropertyService propertyService;

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/propertytypes")
	public List<PropertyTypeDto> showAllPropertyTypes() {
		// show all property types on database
		return PropertyTypeConverter.toDto(propertyService.allPropertyTypes());
	}

	@GetMapping("/propertytype/{id}")
	public PropertyTypeDto showPropertTypeById(@PathVariable("id") int id) {
		// show property type by id
		return PropertyTypeConverter.toDto(propertyService.propertyTypeById(id));
	}
	@GetMapping("/propertyinfos")
	public List<PropertyInfoDto> showAllPropertyInfos() {
		// show all property Info on database
		return PropertyInfoConverter.toDto(propertyService.allPropertyInfos());
	}

	@GetMapping("/propertyinfo/{id}")
	public PropertyInfoDto showPropertInfoById(@PathVariable("id") int id) {
		// show property type by id
		return PropertyInfoConverter.toDto(propertyService.propertyInfoById(id));
	}

	// -----------------------------
	// GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/addpropertytype")
	public PropertyTypeDto addPropertyType(@RequestBody PropertyTypeDto propertyType) {
		// return the added property type formated by converter
		return PropertyTypeConverter.toDto(propertyService.addPropertyType(propertyType));
	}
	@PostMapping("/addpropertyinfo")
	public PropertyInfoDto addPropertyInfo(@RequestBody PropertyInfoDto propertyInfo) {
		// return the added property info formated by converter
		return PropertyInfoConverter.toDto(propertyService.addPropertyInfo(propertyInfo));
	}
	// -----------------------------
	// POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/updatepropertytype/{id}")
	public PropertyTypeDto updatePropertyType(@RequestBody PropertyTypeDto propertyType, @PathVariable("id") int id) {
		// return the updated property type if process complete
		return PropertyTypeConverter.toDto(propertyService.updatePropertyType(propertyType, id));
	}

	@PutMapping("/updatepropertyinfo/{id}")
	public PropertyInfoDto updatePropertyInfo(@RequestBody PropertyInfoDto propertyInfo, @PathVariable("id") int id) {
		// return the updated property info if process complete
		return PropertyInfoConverter.toDto(propertyService.updatePropertyInfo(propertyInfo, id));
	}
	// -----------------------------
	// PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/deletepropertytype/{id}")
	public void deletePropertyType(@PathVariable("id") int id) {
		//delete property type if not used
		propertyService.deletePropertyType(id);
	}

	@DeleteMapping("/deletepropertyinfo/{id}")
	public void deletePropertyInfo(@PathVariable("id") int id) {
		//delete property info if no property assigned it
		propertyService.deletePropertyInfo(id);
	}
	// -----------------------------
	// DELETE ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// EVERY OTHER ROUT REQUEST HANDLED BELOW
	// -----------------------------

	@RequestMapping("/*")
	public String getBack() {
		return "nothing here";
	}
}
