package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IssuesDtoForCreate {

	@NotBlank(message = "Category type is mandatory")
	private String category;
	
	@NotBlank(message = "Description is mandatory")
	private String description;
	
	@NotNull(message = "Client id is mandatory")
	private int client;

	@NotNull(message = "Property id is mandatory")
	private int property;
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public int getProperty() {
		return property;
	}

	public void setProperty(int property) {
		this.property = property;
	}
	
}
