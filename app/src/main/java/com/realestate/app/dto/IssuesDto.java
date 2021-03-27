package com.realestate.app.dto;

import java.time.LocalDateTime;

public class IssuesDto {

	private Integer issueId;

	private String category;

	private String resoulutionStatus;

	private String description;

	private LocalDateTime createdDate;
	
	private UserDto client;

	private PropertyDto property;
	
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

	public UserDto getClient() {
		return client;
	}

	public void setClient(UserDto client) {
		this.client = client;
	}

	public PropertyDto getProperty() {
		return property;
	}

	public void setProperty(PropertyDto property) {
		this.property = property;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
