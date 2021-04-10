package com.realestate.app.dto;

import java.time.LocalDateTime;

import com.realestate.app.entity.enums.TradeTypeEnum;

import lombok.Data;

@Data
public class TradeDto {
	
	private UserDto client;
	
	private PropertyDto properties;

	private LocalDateTime tradeDate;

	private LocalDateTime endTradeDate;

	private TradeTypeEnum tradeType;

	private String paymentType;
	
	private int version;

}
