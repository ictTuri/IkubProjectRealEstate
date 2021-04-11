package com.realestate.app.service;

import java.util.List;

import javax.validation.Valid;

import com.realestate.app.dto.IssueForUpdateDto;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.dto.IssuesForCreateDto;

public interface UserIssueService {
	// GET DATA FROM DATABASE
	List<IssuesDto> allMyIssues();

	// INSERT NEW ISSUE ON THE APP
	IssuesDto insertMyIssue(@Valid IssuesForCreateDto issue);
	
	// UPDATE ISSUE ON APP
	IssuesDto updateMyIssue(@Valid IssueForUpdateDto issue, int id);

	// DELETE ISSUE
	void deleteMyIssue(int id);

}
