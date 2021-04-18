package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.TradeConverter;
import com.realestate.app.dto.TradeDto;
import com.realestate.app.dto.TradeForCreateDto;
import com.realestate.app.dto.TradeForUpdateDto;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.TradeEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.entity.enums.TradeType;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.service.TradeService;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {

	private static final Logger logger = LogManager.getLogger(TradeServiceImpl.class);
	
	UserRepository userRepo;
	TradeRepository tradeRepo;
	PropertyRepository propertyRepo;

	public TradeServiceImpl(TradeRepository tradeRepo, UserRepository userRepo,
			PropertyRepository propertyRepo) {
		super();
		this.tradeRepo = tradeRepo;
		this.userRepo = userRepo;
		this.propertyRepo = propertyRepo;
	}


	// DISPLAY ALL TRADES
	@Override
	public List<TradeDto> allTrades() {
		
		//LOGGING
		logger.info("Showing all trades !");
		
		return TradeConverter.toDto(tradeRepo.getAllTrades());
	}

	// DISPLAY TRADES BY ID
	@Override
	public TradeDto tradesById(int id) {
		TradeEntity trade = tradeRepo.getTradeById(id);
		if (trade != null) {
			
			//LOGGING
			logger.info("Returning trade by id :{}", trade);
			
			return TradeConverter.toDto(trade);
		} else {
			throw new MyExcMessages("No such trade with given Id !");
		}
	}

	// TRADE INSERT
	@Override
	public TradeDto addTrade(TradeForCreateDto trade) {
		trade.setTradeType(trade.getTradeType().toUpperCase());
		RoleEntity role = userRepo.getRoleById(3);
		if (userRepo.isClient(trade.getClient(), role)) {
			PropertyEntity property = propertyRepo.getPropertiesById(trade.getProperties());
				if(property != null) {
					if (!tradeRepo.isInRentedStatus(property)) {
						UserEntity client = userRepo.getUserByUsername(trade.getClient());
						TradeEntity tradeToAdd = TradeConverter.toEntityForCreate(trade, client, property);
						
						//LOGGING
						logger.info("Inserting new trade: {} with client: {} and property: {}", tradeToAdd,client,property);
						
						tradeRepo.insertTrade(tradeToAdd);
						return TradeConverter.toDto(tradeToAdd);
					} else {
						throw new MyExcMessages("Property is bought or Rented !");
					}
				}else {
					throw new MyExcMessages("Property id is not listed !");
				}
		} else {
			throw new MyExcMessages("Only client can be part of a trade !");
		}
	}

	// TRADE UPDATE
	@Override
	public TradeDto updateTrade(TradeForUpdateDto trade, int id) {
		trade.setTradeType(trade.getTradeType().toUpperCase());
		TradeEntity tradeToUpdate = tradeRepo.getTradeById(id);
		if (tradeToUpdate != null) {
			tradeToUpdate.setTradeType(TradeType.valueOf(trade.getTradeType()));
			tradeToUpdate.setPaymentType(trade.getPaymentType());
			tradeRepo.updateTrade(tradeToUpdate);
			
			//LOGGING
			logger.info("Updating trade: {}",tradeToUpdate);
			
			return TradeConverter.toDto(tradeToUpdate);
		} else {
			throw new MyExcMessages("No trade exist with given id !");
		}
	}

	// TRADE DELETION
	@Override
	public void deleteTrade(int id) {
		TradeEntity tradeToDelete = tradeRepo.getTradeById(id);
		if (tradeToDelete != null) {
			
			//LOGGING
			logger.info("Deleting trade: {}",tradeToDelete);
			
			tradeRepo.deleteTrade(tradeToDelete);
		} else {
			throw new MyExcMessages("Trade with given id does not exist !");
		}
	}
}
