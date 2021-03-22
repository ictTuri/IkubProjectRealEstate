package com.realestate.app.entity;

import javax.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "rented")
@Data
public class Rented {

	public Rented() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rent_id")
	private Integer rentId;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User userId;
	@ManyToOne
	@JoinColumn(name = "properties_id", referencedColumnName = "properties_id")
	private Property propertiesId;
	@Column(name = "rented_date")
	private LocalDateTime rentedDate;
	@Column(name = "left_date")
	private LocalDateTime leftDate;
}
