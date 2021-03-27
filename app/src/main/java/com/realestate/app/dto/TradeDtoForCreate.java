package com.realestate.app.dto;

import java.time.LocalDateTime;

public class TradeDtoForCreate {
	
	private int client;
	
	private int properties;

	private LocalDateTime tradeDate;

	private LocalDateTime endTradeDate;

	private String tradeType;

	private String paymentType;

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public int getProperties() {
		return properties;
	}

	public void setProperties(int properties) {
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
	


}
