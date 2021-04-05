package com.realestate.app.entity;

import javax.persistence.*;


import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRADE")
@Data
@NoArgsConstructor
public class TradeEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trade_id")
	private Integer tradeId;
	
	@ManyToOne
	@JoinColumn(name = "client", referencedColumnName = "user_id")
	private UserEntity client;
	
	@ManyToOne
	@JoinColumn(name = "property", referencedColumnName = "properties_id")
	private PropertyEntity property;
	
	@Column(name = "trade_date")
	private LocalDateTime tradeDate;
	
	@Column(name = "end_trade_date")
	private LocalDateTime endTradeDate;
	
	@Column(name = "trade_type")
	private String tradeType;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Version
	@Column(name = "version")
	private int version;
}
