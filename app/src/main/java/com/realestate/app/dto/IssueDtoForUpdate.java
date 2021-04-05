package com.realestate.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class IssueDtoForUpdate {
	
	@NotBlank(message = "Category type is mandatory")
	private String category;
	
	@NotBlank(message = "Updated Resolution is mandatory is mandatory")
	private String resoulutionStatus;
	
	@NotBlank(message = "Description is mandatory")
	private String description;
	
}
