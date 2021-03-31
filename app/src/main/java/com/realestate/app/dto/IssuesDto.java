package com.realestate.app.dto;

import java.time.LocalDateTime;

public class IssuesDto {

	private String category;

	private String resoulutionStatus;

	private String description;

	private LocalDateTime createdDate;
	
	private int version;

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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
