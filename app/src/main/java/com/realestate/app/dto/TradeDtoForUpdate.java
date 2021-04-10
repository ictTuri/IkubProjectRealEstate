package com.realestate.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;


import lombok.Data;

@Data
public class TradeDtoForUpdate {

	private LocalDateTime endTradeDate;

	@NotBlank(message = "Need a value of: RENTED or BOUGHT")
	private String tradeType;

	@NotBlank(message = "Paymment type is mandatory !")
	private String paymentType;

}
