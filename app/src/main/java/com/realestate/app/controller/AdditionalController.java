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
@RequestMapping("/additional")
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
		// show property info by id
		return PropertyInfoConverter.toDto(propertyService.propertyInfoById(id));
	}

	@GetMapping("/issues")
	public List<IssuesDto> showAllIssues() {
		// show all issues on database
		return IssuesConverter.toDto(iATService.allIssues());
	}

	@GetMapping("/issue/{id}")
	public IssuesDto showIssueById(@PathVariable("id") int id) {
		// show issue by id
		return IssuesConverter.toDto(iATService.issuesById(id));
	}
	@GetMapping("/trades")
	public List<TradeDto> showAllTrades() {
		// show all trades on database
		return TradeConverter.toDto(iATService.allTrades());
	}

	@GetMapping("/trade/{id}")
	public TradeDto showTradeById(@PathVariable("id") int id) {
		// show trades by id
		return TradeConverter.toDto(iATService.tradesById(id));
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
	@PostMapping("/addissue")
	public IssuesDto addPropertyInfo(@RequestBody IssuesDtoForCreate issue) {
		// return the added issue formated by converter
		return IssuesConverter.toDto(iATService.addIssues(issue));
	}
	@PostMapping("/addtrade")
	public TradeDto addTrade(@RequestBody TradeDtoForCreate trade) {
		// return the added trade formated by converter
		return TradeConverter.toDto(iATService.addTrade(trade));
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
	
	@PutMapping("/updateissues/{id}")
	public IssuesDto updateIssue(@RequestBody IssueDtoForUpdate issue, @PathVariable("id") int id) {
		// return the updated issue if process complete
		return IssuesConverter.toDto(iATService.updateIssues(issue, id));
	}
	
	@PutMapping("/updatetrade/{id}")
	public TradeDto updateTrade(@RequestBody TradeDtoForUpdate trade, @PathVariable("id") int id) {
		// return the updated trade if process complete
		return TradeConverter.toDto(iATService.updateTrade(trade, id));
	}
	// -----------------------------
	// PUT ROUTES ENDS HERE
	// -----------------------------
	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/deletepropertytype/{id}")
	public void deletePropertyType(@PathVariable("id") int id) {
		// delete property type if not used
		propertyService.deletePropertyType(id);
	}

	@DeleteMapping("/deletepropertyinfo/{id}")
	public void deletePropertyInfo(@PathVariable("id") int id) {
		// delete property info if no property assigned it
		propertyService.deletePropertyInfo(id);
	}
	
	@DeleteMapping("/deleteissue/{id}")
	public void deleteIssue(@PathVariable("id") int id) {
		// delete issue if needed to
		iATService.deleteIssue(id);
	}
	
	@DeleteMapping("/deletetrade/{id}")
	public void deleteTrade(@PathVariable("id") int id) {
		// delete trade if needed to
		iATService.deleteTrade(id);
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
