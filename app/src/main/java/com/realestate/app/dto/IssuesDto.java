package com.realestate.app.dto;

import java.time.LocalDateTime;

import com.realestate.app.entity.enums.IssueStatusEnum;

import lombok.Data;

@Data
public class IssuesDto {

	private String category;

	private IssueStatusEnum resoulutionStatus;

	private String description;

	private LocalDateTime createdDate;
	
	private int version;

}
