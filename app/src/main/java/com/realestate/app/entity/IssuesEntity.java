package com.realestate.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
public class IssuesEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private Integer issueId;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "resolution_status")
	private String resoulutionStatus;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client", referencedColumnName = "user_id")
	private UserEntity client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property", referencedColumnName = "properties_id")
	private PropertyEntity property;
	
}
