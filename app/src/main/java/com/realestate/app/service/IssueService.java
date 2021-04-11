package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.IssueForUpdateDto;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.dto.IssuesForCreateDto;

public interface IssueService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<IssuesDto> allIssues();
	IssuesDto issuesById(int id);
	
	//FUNCTIONS TO INSERT DATA TO DATABASE
	IssuesDto addIssues(IssuesForCreateDto issue);
	
	//FUNSTIONS TO UPDATE DATA ON DATABASE
	IssuesDto updateIssues(IssueForUpdateDto issue, int id);
	
	//FUNTIONS TO DELETE DATA FROM DATABASE
	void deleteIssue(int id);
	
}
