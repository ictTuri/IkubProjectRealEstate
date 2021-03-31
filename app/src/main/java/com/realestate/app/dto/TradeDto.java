package com.realestate.app.dto;

import java.time.LocalDateTime;

public class TradeDto {
	
	private UserDto client;
	
	private PropertyDto properties;

	private LocalDateTime tradeDate;

	private LocalDateTime endTradeDate;

	private String tradeType;

	private String paymentType;
	
	private int version;

	public UserDto getClient() {
		return client;
	}

	public void setClient(UserDto client) {
		this.client = client;
	}

	public PropertyDto getProperties() {
		return properties;
	}

	public void setProperties(PropertyDto properties) {
		this.properties = properties;
	}

	public LocalDateTime getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDateTime tradeDate) {
		this.tradeDate = tradeDate;
	}

	public LocalDateTime getEndTradeDate() {
		return endTradeDate;
	}

	public void setEndTradeDate(LocalDateTime endTradeDate) {
		this.endTradeDate = endTradeDate;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
