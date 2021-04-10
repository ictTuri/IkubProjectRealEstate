package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TradeDtoForCreate {
	
	@NotNull(message = "Client Id is mandatory !")
	private int client;
	
	@NotNull(message = "Property Id is mandatory !")
	private int properties;
	
	@NotBlank(message = "Need a value of: RENTED or BOUGHT")
	private String tradeType;

	@NotBlank(message = "Payment type is mandatory !")
	private String paymentType;

}
