package com.realestate.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.realestate.app.dto.TradeDto;
import com.realestate.app.entity.TradeEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.security.UserPrincipal;
import com.realestate.app.service.UserTradeService;

@Service
@Transactional
public class UserTradeServiceImpl implements UserTradeService {
	TradeRepository tradeRepo;
	UserRepository userRepo;
	
	@Autowired
	public UserTradeServiceImpl(TradeRepository tradeRepo,UserRepository userRepo) {
		super();
		this.tradeRepo = tradeRepo;
		this.userRepo = userRepo;
	}

	@Override
	public List<TradeEntity> allMyTrades() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		switch(user.getRole().getRoleName()) {
		case "ROLE_OWNER":
			return tradeRepo.tradesOfOwnersByOwner(user);
		case "ROLE_CLIENT":
			return tradeRepo.tradesOfClientByClient(user);
		default :
			return tradeRepo.getAllTrades();
		}
	}

	@Override
	public TradeEntity updateMyTrade(@Valid TradeDto trade, int id) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		TradeEntity tradeToUpdate = tradeRepo.getTradeById(id);
		List<TradeEntity> list = tradeRepo.tradesOfOwnersByOwner(user);
		for(TradeEntity te :list) {
			if(te.getTradeId() == id) {
				tradeToUpdate.setTradeType(trade.getTradeType());
				tradeToUpdate.setPaymentType(trade.getPaymentType());
				trade.setEndTradeDate(LocalDateTime.now());
				tradeRepo.updateTrade(tradeToUpdate);
				return tradeToUpdate;
			}else {
				throw new MyExcMessages("You do not own a property with given id !");
			}
		}
		return null;
	}

	@Override
	public void deleteTrade(int id) {
		TradeEntity tradeToDelete = tradeRepo.getTradeById(id);
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		List<TradeEntity> list = tradeRepo.tradesOfOwnersByOwner(user);
		for(TradeEntity te :list) {
			if(te.getTradeId() == id) {
				if (tradeToDelete != null) {
					tradeRepo.deleteTrade(tradeToDelete);
				} else {
					throw new MyExcMessages("Trade with given id does not exist !");
				}
			}else {
				throw new MyExcMessages("No Trade of yours with id passed !");
			}
		}
	}

}
