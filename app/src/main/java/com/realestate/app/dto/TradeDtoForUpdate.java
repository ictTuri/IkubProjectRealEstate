package com.realestate.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.realestate.app.entity.enums.TradeTypeEnum;

import lombok.Data;

@Data
public class TradeDtoForUpdate {

	private LocalDateTime endTradeDate;

	private TradeTypeEnum tradeType;

	@NotBlank(message = "Paymment type is mandatory !")
	private String paymentType;

}
