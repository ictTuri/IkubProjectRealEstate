package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.IssuesConverter;
import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDtoForCreate;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.entity.enums.IssueStatusEnum;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.IssueRepository;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.security.UserPrincipal;
import com.realestate.app.service.UserIssueService;

@Service
@Transactional
public class UserIssueServiceImpl implements UserIssueService {

	private static final Logger logger = LogManager.getLogger(UserIssueServiceImpl.class);
	
	IssueRepository issueRepo;
	UserRepository userRepo;
	PropertyRepository propertyRepo;
	TradeRepository tradeRepo;

	@Autowired
	public UserIssueServiceImpl(IssueRepository issueRepo, UserRepository userRepo,
			PropertyRepository propertyRepo, TradeRepository tradeRepo) {
		super();
		this.issueRepo = issueRepo;
		this.userRepo = userRepo;
		this.propertyRepo = propertyRepo;
		this.tradeRepo = tradeRepo;
	}

	@Override
	public List<IssuesEntity> allMyIssues() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		switch (user.getRole().getRoleName()) {
		case "ROLE_OWNER":
			
			//LOGGING
			logger.info("Showing issues related to owner: {}",user);
			
			return issueRepo.issuesOfOwnersByOwner(user);
		case "ROLE_CLIENT":
			
			//LOGGING
			logger.info("Showing issues related to client: {}",user);
			
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
					issueToUpdate.setResoulutionStatus(IssueStatusEnum.valueOf(issue.getResoulutionStatus()));
					issueToUpdate.setDescription(issue.getDescription());
					
					//LOGGING
					logger.info("Updating issue related to owner, issue: {}",issueToUpdate);
					
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
					issueToUpdate.setResoulutionStatus(IssueStatusEnum.valueOf(issue.getResoulutionStatus()));
					issueToUpdate.setDescription(issue.getDescription());
					
					//LOGGING
					logger.info("Updating issue related to client, issue: {}",issueToUpdate);
					
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
					
					//LOGGING
					logger.info("Owner: {} , deleting issue: {}",user,issueToDelete);
					
					issueRepo.deleteIssue(issueToDelete);
				} else {
					throw new MyExcMessages("You do not own a Issue with given id !");
				}
			}break;
		case "ROLE_CLIENT":
			List<IssuesEntity> list2 = issueRepo.issuesOfClientByClient(user);
			for (IssuesEntity ie : list2) {
				if (ie.getIssueId() == id) {
					
					//LOGGING
					logger.info("Client: {} , deleting issue: {}",user,issueToDelete);
					
					issueRepo.deleteIssue(issueToDelete);
				} else {
					throw new MyExcMessages("You do not own a Issue with given id !");
				}
			}break;
		default:
			throw new MyExcMessages("Please authneticate to proceed here !");
		}
	}

	@Override
	public IssuesEntity insertMyIssue(@Valid IssuesDtoForCreate issue) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		PropertyEntity property = propertyRepo.getPropertiesById(issue.getProperty());
		if (tradeRepo.existTrade(user, property)) {
			IssuesEntity issueToAdd = IssuesConverter.toEntityForCreate(issue, user, property);
			issueToAdd.setClient(user);
			issueToAdd.setResoulutionStatus(IssueStatusEnum.UNCHECKED);
			issueRepo.insertIssue(issueToAdd);
			
			//LOGGING
			logger.info("Inserted new issue: {}",issueToAdd);
			
			return issueToAdd;
		} else {
			throw new MyExcMessages("There is no relation from you to this property!");
		}
	}

}
