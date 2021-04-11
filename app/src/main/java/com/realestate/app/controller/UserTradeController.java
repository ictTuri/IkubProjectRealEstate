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

import com.realestate.app.dto.TradeDto;
import com.realestate.app.dto.TradeForCreateDto;
import com.realestate.app.dto.TradeForUpdateDto;
import com.realestate.app.service.UserTradeService;

@RestController
@RequestMapping("/api/v1/trade")
public class UserTradeController {

	UserTradeService userTradeService;

	@Autowired
	public UserTradeController(UserTradeService userTradeService) {
		super();
		this.userTradeService = userTradeService;
	}

	// ------------------------------
	// GET ROUTES TO RETRIEVE TRADES
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN','OWNER','CLIENT')")
	@GetMapping("/mytrades")
	public ResponseEntity<List<TradeDto>> showMyTrades() {
		// show all trades on database for logged in user
		return new ResponseEntity<>(userTradeService.allMyTrades(), HttpStatus.OK);
	}

	// ------------------------------
	// POST ROUTES TO CREATE TRADE
	// -----------------------------
	@PreAuthorize("hasAnyRole('OWNER')")
	@PostMapping("/mytrades")
	public ResponseEntity<TradeDto> insertMyTradeByOwner(@Valid @RequestBody TradeForCreateDto trade) {
		//Insert trade only by owner
		return new ResponseEntity<>(userTradeService.insertMyTrade(trade), HttpStatus.OK);
	}

	// ------------------------------
	// PUT ROUTES TO UPDATE TRADE
	// -----------------------------
	@PreAuthorize("hasAnyRole('OWNER')")
	@PutMapping("/mytrades/{id}")
	public ResponseEntity<TradeDto> updateMyTradeById(@Valid @RequestBody TradeForUpdateDto trade,
			@PathVariable int id) {
		//Update trades based on role
		return new ResponseEntity<>(userTradeService.updateMyTrade(trade, id), HttpStatus.OK);
	}

	// ------------------------------
	// DELETE ROUTES TO DELETE TRADE
	// -----------------------------
	@PreAuthorize("hasAnyRole('OWNER')")
	@DeleteMapping("/mytrades/{id}")
	public ResponseEntity<Void> deleteTrade(@PathVariable("id") int id) {
		// delete trade if needed to
		userTradeService.deleteTrade(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
