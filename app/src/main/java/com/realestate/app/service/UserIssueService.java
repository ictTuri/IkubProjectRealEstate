package com.realestate.app.service;

import java.util.List;

import javax.validation.Valid;

import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.entity.IssuesEntity;

public interface UserIssueService {

	List<IssuesEntity> allMyIssues();

	IssuesEntity updateMyIssue(@Valid IssueDtoForUpdate issue, int id);

	void deleteMyIssue(int id);

}
