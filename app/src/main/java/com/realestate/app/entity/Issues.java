package com.realestate.app.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "issues")
@Data
public class Issues {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private int issueId;
	@Column(name = "category")
	private String category;
	@Column(name = "resolution_status")
	private String resoulutionStatus;
	@Column(name = "description")
	private String description;
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User userId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "property_id", referencedColumnName = "properties_id")
	private Property propertyId;
	
}
