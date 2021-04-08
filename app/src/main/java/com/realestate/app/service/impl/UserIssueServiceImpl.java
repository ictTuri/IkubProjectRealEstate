package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.IssueRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.security.UserPrincipal;
import com.realestate.app.service.UserIssueService;

@Service
@Transactional
public class UserIssueServiceImpl implements UserIssueService {

	IssueRepository issueRepo;
	UserRepository userRepo;

	@Autowired
	public UserIssueServiceImpl(IssueRepository issueRepo, UserRepository userRepo) {
		super();
		this.issueRepo = issueRepo;
		this.userRepo = userRepo;
	}

	@Override
	public List<IssuesEntity> allMyIssues() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		switch (user.getRole().getRoleName()) {
		case "ROLE_OWNER":
			return issueRepo.issuesOfOwnersByOwner(user);
		case "ROLE_CLIENT":
			return issueRepo.issuesOfClientByClient(user);
		default:
			return issueRepo.getAllIssues();
		}
	}

	@Override
	public IssuesEntity updateMyIssue(@Valid IssueDtoForUpdate issue, int id) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		IssuesEntity issueToUpdate = issueRepo.getIssueById(id);
		switch (user.getRole().getRoleName()) {
		case "ROLE_OWNER":
			List<IssuesEntity> list = issueRepo.issuesOfOwnersByOwner(user);
			for (IssuesEntity ie : list) {
				if (ie.getIssueId() == id) {
					issueToUpdate.setCategory(issue.getCategory());
					issueToUpdate.setResoulutionStatus(issue.getResoulutionStatus());
					issueToUpdate.setDescription(issue.getDescription());
					issueRepo.updateIssue(issueToUpdate);
				} else {
					throw new MyExcMessages("You do not own a Issue with given id !");
				}
			}
			return issueToUpdate;
		case "ROLE_CLIENT":
			List<IssuesEntity> list2 = issueRepo.issuesOfClientByClient(user);
			for (IssuesEntity ie : list2) {
				if (ie.getIssueId() == id) {
					issueToUpdate.setCategory(issue.getCategory());
					issueToUpdate.setResoulutionStatus(issue.getResoulutionStatus());
					issueToUpdate.setDescription(issue.getDescription());
					issueRepo.updateIssue(issueToUpdate);
				} else {
					throw new MyExcMessages("You do not own a Issue with given id !");
				}
			}
			return issueToUpdate;
		default:
			throw new MyExcMessages("Please authneticate to proceed here !");
		}

	}

	@Override
	public void deleteMyIssue(int id) {
		IssuesEntity issueToDelete = issueRepo.getIssueById(id);
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		switch (user.getRole().getRoleName()) {
		case "ROLE_OWNER":
			List<IssuesEntity> list = issueRepo.issuesOfOwnersByOwner(user);
			for (IssuesEntity ie : list) {
				if (ie.getIssueId() == id) {
					issueRepo.deleteIssue(issueToDelete);
				} else {
					throw new MyExcMessages("You do not own a Issue with given id !");
				}
			}break;
		case "ROLE_CLIENT":
			List<IssuesEntity> list2 = issueRepo.issuesOfClientByClient(user);
			for (IssuesEntity ie : list2) {
				if (ie.getIssueId() == id) {
					issueRepo.deleteIssue(issueToDelete);
				} else {
					throw new MyExcMessages("You do not own a Issue with given id !");
				}
			}break;
		default:
			throw new MyExcMessages("Please authneticate to proceed here !");
		}
	}

}
