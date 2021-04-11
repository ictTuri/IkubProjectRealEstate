package com.realestate.app.service;

import java.util.List;

import javax.validation.Valid;

import com.realestate.app.dto.TradeDto;
import com.realestate.app.dto.TradeForCreateDto;
import com.realestate.app.dto.TradeForUpdateDto;

public interface UserTradeService {
	//SHOW TRADES OF PROPERTY OWNERS OR CLIENTS 
	List<TradeDto> allMyTrades();

	//INSERT NEW TRADE BY OWNER
	TradeDto insertMyTrade(@Valid TradeForCreateDto trade);
	
	//UPDATE MY TRADES (OWNER)
	TradeDto updateMyTrade(@Valid TradeForUpdateDto trade, int id);

	//DELETE MY TRADES (OWNER)
	void deleteTrade(int id);



}
