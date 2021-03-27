package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDtoForCreate;
import com.realestate.app.dto.TradeDtoForCreate;
import com.realestate.app.dto.TradeDtoForUpdate;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.TradeEntity;

public interface IssuesAndTradeService {
	
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<IssuesEntity> allIssues();
	List<TradeEntity> allTrades();
	IssuesEntity issuesById(int id);
	TradeEntity tradesById(int id);
	
	//FUNCTIONS TO INSERT DATA TO DATABASE
	IssuesEntity addIssues(IssuesDtoForCreate issue);
	TradeEntity addTrade(TradeDtoForCreate trade);
	
	//FUNSTIONS TO UPDATE DATA ON DATABASE
	IssuesEntity updateIssues(IssueDtoForUpdate issue, int id);
	TradeEntity updateTrade(TradeDtoForUpdate trade, int id);
	
	//FUNTIONS TO DELETE DATA FROM DATABASE
	void deleteIssue(int id);
	void deleteTrade(int id);

}
