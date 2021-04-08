package com.realestate.app.service;

import java.util.List;

import javax.validation.Valid;

import com.realestate.app.dto.TradeDto;
import com.realestate.app.entity.TradeEntity;

public interface UserTradeService {
	//SHOW TRADES OF PROPERTY OWNERS OR CLIENTS 
	List<TradeEntity> allMyTrades();

	//UPDATE MY TRADES (OWNER)
	TradeEntity updateMyTrade(@Valid TradeDto trade, int id);

	//DELETE MY TRADES (OWNER)
	void deleteTrade(int id);

}
