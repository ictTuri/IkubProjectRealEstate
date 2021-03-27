package com.realestate.app.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.IssuesConverter;
import com.realestate.app.converter.TradeConverter;
import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDtoForCreate;
import com.realestate.app.dto.TradeDtoForCreate;
import com.realestate.app.dto.TradeDtoForUpdate;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.TradeEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.IssuesRepository;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.UserRepository;

@Service
@Transactional
public class IssuesAndTradeServiceImpl implements IssuesAndTradeService {
	
	UserRepository userRepo;
	IssuesRepository issuesRepo;
	TradeRepository tradeRepo;
	PropertyRepository propertyRepo;
	
	//CONSTRUCTOR
	@Autowired
	public IssuesAndTradeServiceImpl(IssuesRepository issuesRepo, TradeRepository tradeRepo
			,UserRepository userRepo, PropertyRepository propertyRepo) {
		super();
		this.issuesRepo = issuesRepo;
		this.tradeRepo = tradeRepo;
		this.userRepo = userRepo;
		this.propertyRepo = propertyRepo;
	}
	
	//DISPLAY ALL ISSUES
	@Override
	public List<IssuesEntity> allIssues() {
		return issuesRepo.getAllIssues();
	}

	//DISPLAY ISSUE BY ID
	@Override
	public IssuesEntity issuesById(int id) {
		IssuesEntity issue = issuesRepo.getIssueById(id);
		if(issue != null) {
			return issue;
		}else {
			throw new MyExcMessages("No such issue with given Id !");
		}
	}

	//DISPLAY ALL TRADES
	@Override
	public List<TradeEntity> allTrades() {
		return tradeRepo.getAllTrades();
	}
	
	//DISPLAY TRADES BY ID
	@Override
	public TradeEntity tradesById(int id) {
		TradeEntity trade = tradeRepo.getTradeById(id);
		if(trade!=null) {
			return trade;
		}else {
			throw new MyExcMessages("No such trade with given Id !");
		}
	}
	
	//ISSUE INSERT
	@Override
	public IssuesEntity addIssues(IssuesDtoForCreate issue) {
		if(issue!=null) {
			return issuesValidations(issue);
		}else {
			throw new MyExcMessages("please fill the issues data to proceed !");
		}
	}
	
	//Validations for the new issue extracted
	private IssuesEntity issuesValidations(IssuesDtoForCreate issue) {
		if(issue.getCategory()!=null&&issue.getDescription()!=null) {
			RoleEntity role = userRepo.getRoleById(3);
			if(userRepo.isClient(issue.getClient(),role)) {
				UserEntity user = userRepo.getUserById(issue.getClient());
				PropertyEntity property = propertyRepo.getPropertiesById(issue.getProperty());
				if(tradeRepo.existTrade(user, property)) {
					IssuesEntity issueToAdd = IssuesConverter.toEntityForCreate(issue, user, property);
					issueToAdd.setResoulutionStatus("Unchecked");
					issuesRepo.insertIssue(issueToAdd);
					return issueToAdd;
				}else {
					throw new MyExcMessages("Client and Property have no relation !");
				}
			}else {
				throw new MyExcMessages("Issue owner must be a client");
			}
		}else {
			throw new MyExcMessages("please add a category and description");
		}
	}
	
	//TRADE INSERT
	@Override
	public TradeEntity addTrade(TradeDtoForCreate trade) {
		if(trade!=null) {
			RoleEntity role = userRepo.getRoleById(3);
			if(userRepo.isClient(trade.getClient(), role)) {
				if(trade.getPaymentType()!=null&&trade.getTradeType()!=null) {
					PropertyEntity property = propertyRepo.getPropertiesById(trade.getProperties());
					if(tradeRepo.isInRentedStatus(property)) {
						UserEntity client = userRepo.getUserById(trade.getClient());
						TradeEntity tradeToAdd = TradeConverter.toEntityForCreate(trade, client, property);
						tradeRepo.insertTrade(tradeToAdd);
						return tradeToAdd;
					}else {
						throw new MyExcMessages("Property is bought or Rented !");
					}
				}else {
					throw new MyExcMessages("Please fill trade type and payment type");
				}
			}else {
				throw new MyExcMessages("Only client can be part of a trade !");
			}
		}else {
			throw new MyExcMessages("Please fill the trade data to proceed !");
		}
	}

	//ISSUE UPDATE
	@Override
	public IssuesEntity updateIssues(IssueDtoForUpdate issue, int id) {
		if(issue!=null) {
			IssuesEntity issueToUpdate = issuesRepo.getIssueById(id);
			if(issueToUpdate != null) {
				issueToUpdate.setCategory(issue.getCategory());
				issueToUpdate.setDescription(issue.getDescription());
				issueToUpdate.setResoulutionStatus(issue.getResoulutionStatus());
				issuesRepo.updateIssue(issueToUpdate);
				return issueToUpdate;
			}else {
				throw new MyExcMessages("No issue with given id !");
			}
		}else {
			throw new MyExcMessages("Please fill teh issue data required");
		}
	}

	//TRADE UPDATE
	@Override
	public TradeEntity updateTrade(TradeDtoForUpdate trade, int id) {
		if(trade!=null) {
			TradeEntity tradeToUpdate = tradeRepo.getTradeById(id);
			if(tradeToUpdate != null) {
				tradeToUpdate.setTradeType(trade.getTradeType());
				tradeToUpdate.setPaymentType(trade.getPaymentType());
				trade.setEndTradeDate(LocalDateTime.now());
				tradeRepo.updateTrade(tradeToUpdate);
				return tradeToUpdate;
			}else {
				throw new MyExcMessages("No trade exist with given id !");
			}
		}else {
			throw new MyExcMessages("Please fill the data for the update !");
		}
	}
	
	//ISSUE DELETION
	@Override
	public void deleteIssue(int id) {
		IssuesEntity issueToDelete = issuesRepo.getIssueById(id);
		if(issueToDelete!=null) {
			issuesRepo.deleteIssue(issueToDelete);
		}else {
			throw new MyExcMessages("Issue with given id does not exist !");
		}
	}


	//TRADE DELETION
	@Override
	public void deleteTrade(int id) {
		TradeEntity tradeToDelete = tradeRepo.getTradeById(id);
		if(tradeToDelete!=null) {
			tradeRepo.deleteTrade(tradeToDelete);
		}else {
			throw new MyExcMessages("Trade with given id does not exist !");
		}
	}
}
