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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	

	public PropertyEntity getProperty() {
		return property;
	}

	public void setProperty(PropertyEntity property) {
		this.property = property;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public UserEntity getClient() {
		return client;
	}

	public void setClient(UserEntity client) {
		this.client = client;
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
