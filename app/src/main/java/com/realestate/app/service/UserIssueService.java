package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.ClientIssueForCreateDto;
import com.realestate.app.dto.IssueForUpdateDto;
import com.realestate.app.dto.IssuesDto;

public interface UserIssueService {
	// GET DATA FROM DATABASE
	List<IssuesDto> allMyIssues();

	// INSERT NEW ISSUE ON THE APP
	IssuesDto insertMyIssue(ClientIssueForCreateDto issue);
	
	// UPDATE ISSUE ON APP
	IssuesDto updateMyIssue(IssueForUpdateDto issue, int id);

	// DELETE ISSUE
	void deleteMyIssue(int id);

}
