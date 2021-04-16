package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClientIssueForCreateDto {
	
	@NotBlank(message = "Category type is mandatory")
	private String category;
	
	@NotBlank(message = "Description is mandatory")
	private String description;

	@NotNull(message = "Property id is mandatory")
	private int property;
}
