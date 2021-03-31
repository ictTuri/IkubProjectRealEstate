package com.realestate.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;


public class TradeDtoForUpdate {

	private LocalDateTime endTradeDate;

	@NotBlank(message = "Trade type is mandatory !")
	private String tradeType;

	@NotBlank(message = "Paymment type is mandatory !")
	private String paymentType;

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
