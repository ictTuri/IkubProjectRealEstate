package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;

public class IssueDtoForUpdate {
	
	@NotBlank(message = "Category type is mandatory")
	private String category;
	
	@NotBlank(message = "Updated Resolution is mandatory is mandatory")
	private String resoulutionStatus;
	
	@NotBlank(message = "Description is mandatory")
	private String description;

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
	
	
}
