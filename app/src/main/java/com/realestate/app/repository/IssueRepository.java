package com.realestate.app.repository;

import java.util.List;

import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.UserEntity;

public interface IssueRepository {
	// RETURN ALL ISSUES FROM DB
	List<IssuesEntity> getAllIssues();
	IssuesEntity getIssueById(Integer issueId);
	
	// INSERT NEW ISSUES ON DB
	void insertIssue(IssuesEntity issue);
	
	// UPDATE ISSUES ON DB
	IssuesEntity updateIssue(IssuesEntity issue);
	
	// DELETE ISSUES FROM DB
	void deleteIssue(IssuesEntity issue);
	
	//HELPING METHODS
	IssuesEntity existIssueWithProperty(PropertyEntity property);
	List<IssuesEntity> issuesOfOwnersByOwner(UserEntity user);
	List<IssuesEntity> issuesOfClientByClient(UserEntity user);
}
