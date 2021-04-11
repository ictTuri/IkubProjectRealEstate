package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.TradeDto;
import com.realestate.app.dto.TradeForCreateDto;
import com.realestate.app.dto.TradeForUpdateDto;

public interface TradeService {
	
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<TradeDto> allTrades();
	TradeDto tradesById(int id);
	
	//FUNCTIONS TO INSERT DATA TO DATABASE
	TradeDto addTrade(TradeForCreateDto trade);
	
	//FUNSTIONS TO UPDATE DATA ON DATABASE
	TradeDto updateTrade(TradeForUpdateDto trade, int id);
	
	//FUNTIONS TO DELETE DATA FROM DATABASE
	void deleteTrade(int id);
}
