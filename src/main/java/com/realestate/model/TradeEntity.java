package com.realestate.model;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Trade")

public class TradeEntity implements Serializable {
	
	public static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="trade_date")
	private Timestamp trade_date;
	
	@Column(name="end_trade")
	private Timestamp end_trade;
	
	@Column(name="trade_type")
	private String trade_type;
	
	@Column(name="payment_type")
	private String payment_type;
	
	
	


}
