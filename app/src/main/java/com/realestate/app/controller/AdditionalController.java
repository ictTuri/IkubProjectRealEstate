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

import com.realestate.app.converter.IssuesConverter;
import com.realestate.app.converter.PropertyInfoConverter;
import com.realestate.app.converter.PropertyTypeConverter;
import com.realestate.app.converter.TradeConverter;
import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.dto.IssuesDtoForCreate;
import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.dto.TradeDto;
import com.realestate.app.dto.TradeDtoForCreate;
import com.realestate.app.dto.TradeDtoForUpdate;
import com.realestate.app.service.IssuesAndTradeService;
import com.realestate.app.service.PropertyService;

@RestController
@RequestMapping("/realestate/api/v1")
public class AdditionalController {
 
	PropertyService propertyService;
	IssuesAndTradeService iATService;

	@Autowired
	public AdditionalController(PropertyService propertyService, IssuesAndTradeService iATService) {
		super();
		this.propertyService = propertyService;
		this.iATService = iATService;
	}
	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/propertytypes")
	public ResponseEntity<List<PropertyTypeDto>> propertyTypes() {
		// show all property types on database
		return new ResponseEntity<>(PropertyTypeConverter.toDto(propertyService.allPropertyTypes()), HttpStatus.OK);
	}
	@GetMapping("/propertytypes/{id}")
	public ResponseEntity<PropertyTypeDto> showPropertTypeById(@PathVariable("id") int id) {
		// show property type by id
		return new ResponseEntity<> (PropertyTypeConverter.toDto(propertyService.propertyTypeById(id)), HttpStatus.FOUND);
	}
	@GetMapping("/propertyinfos")
	public ResponseEntity<List<PropertyInfoDto>> showAllPropertyInfos() {
		// show all property Info on database
		return new ResponseEntity<> (PropertyInfoConverter.toDto(propertyService.allPropertyInfos()),HttpStatus.OK);
	}
	@GetMapping("/propertyinfos/{id}")
	public ResponseEntity<PropertyInfoDto> showPropertInfoById(@PathVariable("id") int id) {
		// show property info by id
		return new ResponseEntity<> (PropertyInfoConverter.toDto(propertyService.propertyInfoById(id)),HttpStatus.FOUND);
	}
	@GetMapping("/issues")
	public ResponseEntity<List<IssuesDto>> showAllIssues() {
		// show all issues on database
		return new ResponseEntity<> (IssuesConverter.toDto(iATService.allIssues()),HttpStatus.OK);
	}
	@GetMapping("/issues/{id}")
	public ResponseEntity<IssuesDto> showIssueById(@PathVariable("id") int id) {
		// show issue by id
		return new ResponseEntity<> (IssuesConverter.toDto(iATService.issuesById(id)),HttpStatus.FOUND);
	}
	@GetMapping("/trades")
	public ResponseEntity<List<TradeDto>> showAllTrades() {
		// show all trades on database
		return new ResponseEntity<> (TradeConverter.toDto(iATService.allTrades()),HttpStatus.OK);
	}
	@GetMapping("/trades/{id}")
	public ResponseEntity<TradeDto> showTradeById(@PathVariable("id") int id) {
		// show trades by id
		return new ResponseEntity<> (TradeConverter.toDto(iATService.tradesById(id)),HttpStatus.FOUND);
	}
	// -----------------------------
	// GET ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/propertytypes")
	public ResponseEntity<PropertyTypeDto> addPropertyType(@Valid @RequestBody PropertyTypeDto propertyType) {
		// return the added property type formated by converter
		return new ResponseEntity<> (PropertyTypeConverter.toDto(propertyService.addPropertyType(propertyType)),HttpStatus.CREATED);
	}
	@PostMapping("/propertyinfos")
	public ResponseEntity<PropertyInfoDto> addPropertyInfo(@Valid @RequestBody PropertyInfoDto propertyInfo) {
		// return the added property info formated by converter
		return new ResponseEntity<> (PropertyInfoConverter.toDto(propertyService.addPropertyInfo(propertyInfo)),HttpStatus.CREATED);
	}
	@PostMapping("/issues")
	public ResponseEntity<IssuesDto> addPropertyInfo(@Valid @RequestBody IssuesDtoForCreate issue) {
		// return the added issue formated by converter
		return new ResponseEntity<> (IssuesConverter.toDto(iATService.addIssues(issue)),HttpStatus.CREATED);
	}
	@PostMapping("/trades")
	public ResponseEntity<TradeDto> addTrade(@Valid @RequestBody TradeDtoForCreate trade) {
		// return the added trade formated by converter
		return new ResponseEntity<> (TradeConverter.toDto(iATService.addTrade(trade)),HttpStatus.CREATED);
	}
	// -----------------------------
	// POST ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/propertytypes/{id}")
	public ResponseEntity<PropertyTypeDto> updatePropertyType(@Valid @RequestBody PropertyTypeDto propertyType, @PathVariable("id") int id) {
		// return the updated property type if process complete
		return new ResponseEntity<> (PropertyTypeConverter.toDto(propertyService.updatePropertyType(propertyType, id)),HttpStatus.OK);
	}
	@PutMapping("/propertyinfos/{id}")
	public ResponseEntity<PropertyInfoDto> updatePropertyInfo(@Valid @RequestBody PropertyInfoDto propertyInfo, @PathVariable("id") int id) {
		// return the updated property info if process complete
		return new ResponseEntity<> (PropertyInfoConverter.toDto(propertyService.updatePropertyInfo(propertyInfo, id)),HttpStatus.OK);
	}
	@PutMapping("/issues/{id}")
	public ResponseEntity<IssuesDto> updateIssue(@Valid @RequestBody IssueDtoForUpdate issue, @PathVariable("id") int id) {
		// return the updated issue if process complete
		return new ResponseEntity<> (IssuesConverter.toDto(iATService.updateIssues(issue, id)),HttpStatus.OK);
	}
	@PutMapping("/trades/{id}")
	public ResponseEntity<TradeDto> updateTrade(@Valid @RequestBody TradeDtoForUpdate trade, @PathVariable("id") int id) {
		// return the updated trade if process complete
		return new ResponseEntity<> (TradeConverter.toDto(iATService.updateTrade(trade, id)),HttpStatus.OK);
	}
	// -----------------------------
	// PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/propertytypes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePropertyType(@PathVariable("id") int id) {
		// delete property type if not used
		propertyService.deletePropertyType(id);
	}
	@DeleteMapping("/propertyinfos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePropertyInfo(@PathVariable("id") int id) {
		// delete property info if no property assigned it
		propertyService.deletePropertyInfo(id);
	}
	@DeleteMapping("/issues/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIssue(@PathVariable("id") int id) {
		// delete issue if needed to
		iATService.deleteIssue(id);
	}
	@DeleteMapping("/trades/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTrade(@PathVariable("id") int id) {
		// delete trade if needed to
		iATService.deleteTrade(id);
	}
	// -----------------------------
	// DELETE ROUTES ENDS HERE
	// -----------------------------
}
