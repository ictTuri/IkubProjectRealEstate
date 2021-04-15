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
import com.realestate.app.service.TradeService;

@RestController
@RequestMapping("/api/v1/trades")
public class TradeController {

	TradeService tradeService;

	@Autowired
	public TradeController(TradeService tradeService) {
		super();
		this.tradeService = tradeService;
	}

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping()
	public ResponseEntity<List<TradeDto>> showAllTrades() {
		// show all trades on database
		return new ResponseEntity<>(tradeService.allTrades(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<TradeDto> showTradeById(@PathVariable("id") int id) {
		// show trades by id
		return new ResponseEntity<>(tradeService.tradesById(id), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping()
	public ResponseEntity<TradeDto> addTrade(@Valid @RequestBody TradeForCreateDto trade) {
		// return the added trade formated by converter
		return new ResponseEntity<>(tradeService.addTrade(trade), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<TradeDto> updateTrade(@Valid @RequestBody TradeForUpdateDto trade,
			@PathVariable("id") int id) {
		// return the updated trade if process complete
		return new ResponseEntity<>(tradeService.updateTrade(trade, id), HttpStatus.OK);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTrade(@PathVariable("id") int id) {
		// delete trade if needed to
		tradeService.deleteTrade(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
