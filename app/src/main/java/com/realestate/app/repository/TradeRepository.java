package com.realestate.app.repository;

import java.util.List;

import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.TradeEntity;
import com.realestate.app.entity.UserEntity;

public interface TradeRepository {
	// GET INFOS FROM DATABASE
	List<TradeEntity> getAllTrades();
	TradeEntity getTradeById(Integer tradeId);
	
	// INSERT NEW INFO INTO DATABASE
	void insertTrade(TradeEntity trade);
	
	// UPDATE INFO ON DATABASE
	TradeEntity updateTrade(TradeEntity trade);
	
	// DELETE INFO FROM DATABASE
	void deleteTrade(TradeEntity trade);
	
	// HELPING METHODS 
	boolean existTrade(UserEntity client, PropertyEntity property);
	boolean isInRentedStatus(PropertyEntity property);
	boolean checkClientInTrade(UserEntity user);
	TradeEntity getTradeByProperty(PropertyEntity property);
	List<TradeEntity> tradesOfOwnersByOwner(UserEntity user);
	List<TradeEntity> tradesOfClientByClient(UserEntity user);
}
