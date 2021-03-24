package com.realestate.app.utils;

import java.time.LocalDateTime;

import com.realestate.app.entity.TradeEntity;

public class TradeUtil {
	public static TradeEntity createTradeOne() {
		TradeEntity trade1 = new TradeEntity();
		trade1.setClient(null);
		trade1.setProperties(null);
		trade1.setTradeDate(LocalDateTime.now());
		trade1.setEndTradeDate(null);
		trade1.setTradeType("Bought");
		trade1.setPaymentType("Kesh");
		return trade1;
	}
	
	public static TradeEntity createTradeTwo() {
		TradeEntity trade2 = new TradeEntity();
		trade2.setClient(null);
		trade2.setProperties(null);
		trade2.setTradeDate(LocalDateTime.now());
		trade2.setEndTradeDate(null);
		trade2.setTradeType("Rented");
		trade2.setPaymentType("Banka Kombetare Tregtare");
		return trade2;
	}
}
