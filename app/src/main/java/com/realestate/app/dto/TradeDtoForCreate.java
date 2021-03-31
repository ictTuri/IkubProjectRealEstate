package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TradeDtoForCreate {
	
	@NotNull(message = "Client Id is mandatory !")
	private int client;
	
	@NotNull(message = "Property Id is mandatory !")
	private int properties;

	@NotBlank(message = "Trade type is mandatory !")
	private String tradeType;

	@NotBlank(message = "Payment type is mandatory !")
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
