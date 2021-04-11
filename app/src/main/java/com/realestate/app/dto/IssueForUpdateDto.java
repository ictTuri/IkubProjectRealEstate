package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class IssueForUpdateDto {
	
	@NotBlank(message = "Category type is mandatory")
	private String category;
	
	@NotBlank(message = "Need a value of: UNCHECKED, REVIEWED, ON_PROGRESS, FIXED, ESCALATED")
	private String resoulutionStatus;
	
	@NotBlank(message = "Description is mandatory")
	private String description;
	
}
