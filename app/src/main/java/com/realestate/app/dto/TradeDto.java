package com.realestate.app.dto;

import java.time.LocalDateTime;

import com.realestate.app.entity.enums.TradeType;

import lombok.Data;

@Data
public class TradeDto {
	
	private Integer tradeId;
	
	private UserDto client;
	
	private PropertyDto properties;

	private LocalDateTime tradeDate;

	private LocalDateTime endTradeDate;

	private TradeType tradeType;

	private String paymentType;
	
	private int version;

}
