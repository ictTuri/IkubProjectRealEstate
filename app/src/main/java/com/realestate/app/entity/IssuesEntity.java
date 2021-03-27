package com.realestate.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issues")
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
	
	@Version
	@Column(name = "version")
	private int version;

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getResoulutionStatus() {
		return resoulutionStatus;
	}

	public void setResoulutionStatus(String resoulutionStatus) {
		this.resoulutionStatus = resoulutionStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public UserEntity getClient() {
		return client;
	}

	public void setClient(UserEntity client) {
		this.client = client;
	}

	public PropertyEntity getProperty() {
		return property;
	}

	public void setProperty(PropertyEntity property) {
		this.property = property;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
}
