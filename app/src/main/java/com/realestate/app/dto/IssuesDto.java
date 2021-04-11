package com.realestate.app.dto;

import java.time.LocalDateTime;

import com.realestate.app.entity.enums.IssueStatus;

import lombok.Data;

@Data
public class IssuesDto {

	private String category;

	private IssueStatus resoulutionStatus;

	private String description;

	private LocalDateTime createdDate;
	
	private int version;

}
