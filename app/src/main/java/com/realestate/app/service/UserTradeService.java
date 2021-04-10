package com.realestate.app.service;

import java.util.List;

import javax.validation.Valid;

import com.realestate.app.dto.TradeDtoForCreate;
import com.realestate.app.dto.TradeDtoForUpdate;
import com.realestate.app.entity.TradeEntity;

public interface UserTradeService {
	//SHOW TRADES OF PROPERTY OWNERS OR CLIENTS 
	List<TradeEntity> allMyTrades();

	//INSERT NEW TRADE BY OWNER
	TradeEntity insertMyTrade(@Valid TradeDtoForCreate trade);
	
	//UPDATE MY TRADES (OWNER)
	TradeEntity updateMyTrade(@Valid TradeDtoForUpdate trade, int id);

	//DELETE MY TRADES (OWNER)
	void deleteTrade(int id);



}
