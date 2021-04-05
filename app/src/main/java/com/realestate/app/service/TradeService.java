package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.TradeDtoForCreate;
import com.realestate.app.dto.TradeDtoForUpdate;
import com.realestate.app.entity.TradeEntity;

public interface TradeService {
	
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<TradeEntity> allTrades();
	TradeEntity tradesById(int id);
	
	//FUNCTIONS TO INSERT DATA TO DATABASE
	TradeEntity addTrade(TradeDtoForCreate trade);
	
	//FUNSTIONS TO UPDATE DATA ON DATABASE
	TradeEntity updateTrade(TradeDtoForUpdate trade, int id);
	
	//FUNTIONS TO DELETE DATA FROM DATABASE
	void deleteTrade(int id);
}
