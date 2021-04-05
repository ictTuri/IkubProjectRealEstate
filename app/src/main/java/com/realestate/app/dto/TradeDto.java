package com.realestate.app.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TradeDto {
	
	private UserDto client;
	
	private PropertyDto properties;

	private LocalDateTime tradeDate;

	private LocalDateTime endTradeDate;

	private String tradeType;

	private String paymentType;
	
	private int version;

}
