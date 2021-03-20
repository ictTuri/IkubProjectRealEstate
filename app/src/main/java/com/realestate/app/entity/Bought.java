package com.realestate.app.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.Data;

@Entity
@Table(name = "bought")
@Data
public class Bought {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bought_id")
	private int boughtId;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User userId;
	@ManyToOne
	@JoinColumn(name = "properties_id", referencedColumnName = "properties_id")
	private Property propertiesId;
	@Column(name = "bought_date")
	private LocalDateTime boughtDate;
	@Column(name = "payment_type")
	private String paymentType;
}
