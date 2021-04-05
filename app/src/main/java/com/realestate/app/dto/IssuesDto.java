package com.realestate.app.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class IssuesDto {

	private String category;

	private String resoulutionStatus;

	private String description;

	private LocalDateTime createdDate;
	
	private int version;

}
