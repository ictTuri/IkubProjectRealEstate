package com.realestate.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.realestate.app.repository.impl.PropertyRepositoryImpl;
import com.realestate.app.repository.impl.TradeRepositoryImpl;
import com.realestate.app.repository.impl.UserRepositoryImpl;
import com.realestate.app.security.UserPrincipal;
import com.realestate.app.service.UserTradeService;

@Service
@Transactional
public class UserTradeServiceImpl implements UserTradeService {

	private static final Logger logger = LogManager.getLogger(UserTradeServiceImpl.class);
	private static final String OWNER = "ROLE_OWNER";
	private static final String CLIENT = "ROLE_CLIENT";

	PropertyRepositoryImpl propertyRepo;
	TradeRepositoryImpl tradeRepo;
	UserRepositoryImpl userRepo;

	@Autowired
	public UserTradeServiceImpl(TradeRepositoryImpl tradeRepo, UserRepositoryImpl userRepo,
			PropertyRepositoryImpl propertyRepo) {
		super();
		this.tradeRepo = tradeRepo;
		this.userRepo = userRepo;
		this.propertyRepo = propertyRepo;
	}

	// Get all trades if you are a client with trade or owner whose property is part
	// of a trade / if admin access here it returns all trades
	@Override
	public List<TradeDto> allMyTrades() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		switch (user.getRole().getRoleName()) {
		case OWNER:

			// LOGGING
			logger.info("Showing trades of Owner: {}", thisUser);

			return TradeConverter.toDto(tradeRepo.tradesOfOwnersByOwner(user));
		case CLIENT:

			// LOGGING
			logger.info("Showing trades of Client: {}", thisUser);

			return TradeConverter.toDto(tradeRepo.tradesOfClientByClient(user));
		default:
			return TradeConverter.toDto(tradeRepo.getAllTrades());
		}
	}

	// Insert new trade for owners property
	@Override
	public TradeDto insertMyTrade(@Valid TradeForCreateDto trade) {
		trade.setTradeType(trade.getTradeType().toUpperCase());
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		PropertyEntity prop = propertyRepo.getPropertiesById(trade.getProperties());
		Iterable<PropertyEntity> propertyList = propertyRepo.getPropertiesByOwner(user);
		for (PropertyEntity pe : propertyList) {
			if (pe.getPropertiesId().equals(prop.getPropertiesId())) {
				RoleEntity role = userRepo.getRoleById(3);
				if (userRepo.isClient(trade.getClient(), role)) {
					if (!tradeRepo.isInRentedStatus(prop)) {
						UserEntity client = userRepo.getUserByUsername(trade.getClient());
						TradeEntity tradeToAdd = TradeConverter.toEntityForCreate(trade, client, prop);

						// LOGGING
						logger.info("Inserting new trade: {} with client: {} and property: {}", tradeToAdd, client,
								prop);

						tradeRepo.insertTrade(tradeToAdd);
						return TradeConverter.toDto(tradeToAdd);
					} else {
						throw new MyExcMessages("Property is bought or Rented !");
					}
				} else {
					throw new MyExcMessages("User Passes is not a client!");
				}

			} else {
				throw new MyExcMessages("Property Passes is not one of yours!");
			}
		}
		throw new MyExcMessages("Process can not be completed at this time!");
	}

	// Update the trades by owner whose property is part of a trade. if rented it
	// closes the rent !
	@Override
	public TradeDto updateMyTrade(@Valid TradeForUpdateDto trade, int id) {
		trade.setTradeType(trade.getTradeType().toUpperCase());
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		TradeEntity tradeToUpdate = tradeRepo.getTradeById(id);
		List<TradeEntity> list = tradeRepo.tradesOfOwnersByOwner(user);
		for (TradeEntity te : list) {
			if (te.getTradeId() == id) {
				tradeToUpdate.setTradeType(TradeType.valueOf(trade.getTradeType()));
				tradeToUpdate.setPaymentType(trade.getPaymentType());
				trade.setEndTradeDate(LocalDateTime.now());
				tradeRepo.updateTrade(tradeToUpdate);

				// LOGGING
				logger.info("Trade Updated: {}", tradeToUpdate);

				return TradeConverter.toDto(tradeToUpdate);
			} else {
				throw new MyExcMessages("You do not own a property with given id !");
			}
		}
		return null;
	}

	// Delete trade by owners whose property is part of the trade
	@Override
	public void deleteTrade(int id) {
		TradeEntity tradeToDelete = tradeRepo.getTradeById(id);
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		List<TradeEntity> list = tradeRepo.tradesOfOwnersByOwner(user);
		for (TradeEntity te : list) {
			if (te.getTradeId() == id) {
				if (tradeToDelete != null) {

					// LOGGING
					logger.info("Trade Deleted: {}", tradeToDelete);

					tradeRepo.deleteTrade(tradeToDelete);
				} else {
					throw new MyExcMessages("Trade with given id does not exist !");
				}
			} else {
				throw new MyExcMessages("No Trade of yours with id passed !");
			}
		}
	}

}
