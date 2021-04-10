package com.realestate.app.service;

import java.util.List;

import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDtoForCreate;
import com.realestate.app.entity.IssuesEntity;

public interface IssueService {
	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<IssuesEntity> allIssues();
	IssuesEntity issuesById(int id);
	
	//FUNCTIONS TO INSERT DATA TO DATABASE
	IssuesEntity addIssues(IssuesDtoForCreate issue);
	
	//FUNSTIONS TO UPDATE DATA ON DATABASE
	IssuesEntity updateIssues(IssueDtoForUpdate issue, int id);
	
	//FUNTIONS TO DELETE DATA FROM DATABASE
	void deleteIssue(int id);
	
}
