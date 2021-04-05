package com.realestate.app.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.IssuesConverter;
import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDtoForCreate;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.IssueRepository;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.service.IssueService;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {

	UserRepository userRepo;
	IssueRepository issueRepo;
	TradeRepository tradeRepo;
	PropertyRepository propertyRepo;

	// CONSTRUCTOR
	@Autowired
	public IssueServiceImpl(TradeRepository tradeRepo, UserRepository userRepo,
			PropertyRepository propertyRepo,IssueRepository issueRepo) {
		super();
		this.tradeRepo = tradeRepo;
		this.issueRepo = issueRepo;
		this.userRepo = userRepo;
		this.propertyRepo = propertyRepo;
	}

	// DISPLAY ALL ISSUES
	@Override
	public List<IssuesEntity> allIssues() {
		return issueRepo.getAllIssues();
	}

	// DISPLAY ISSUE BY ID 
	@Override
	public IssuesEntity issuesById(int id) {
		IssuesEntity issue = issueRepo.getIssueById(id);
		if (issue != null) {
			return issue;
		} else {
			throw new MyExcMessages("No such issue with given Id !");
		}
	}

	// CREATE NEW ISSUE
	@Override
	public IssuesEntity addIssues(IssuesDtoForCreate issue) {
		RoleEntity role = userRepo.getRoleById(3);
		if (userRepo.isClient(issue.getClient(), role)) {
			UserEntity user = userRepo.getUserById(issue.getClient());
			PropertyEntity property = propertyRepo.getPropertiesById(issue.getProperty());
			if (tradeRepo.existTrade(user, property)) {
				IssuesEntity issueToAdd = IssuesConverter.toEntityForCreate(issue, user, property);
				issueToAdd.setResoulutionStatus("Unchecked");
				issueRepo.insertIssue(issueToAdd);
				return issueToAdd;
			} else {
				throw new MyExcMessages("Client and Property have no relation !");
			}
		} else {
			throw new MyExcMessages("Issue owner must be a client");
		}
	}

	// UPDATE EXISTING ISSUE
	@Override
	public IssuesEntity updateIssues(IssueDtoForUpdate issue, int id) {
		IssuesEntity issueToUpdate = issueRepo.getIssueById(id);
		if (issueToUpdate != null) {
			issueToUpdate.setCategory(issue.getCategory());
			issueToUpdate.setDescription(issue.getDescription());
			issueToUpdate.setResoulutionStatus(issue.getResoulutionStatus());
			issueRepo.updateIssue(issueToUpdate);
			return issueToUpdate;
		} else {
			throw new MyExcMessages("No issue with given id !");
		}
	}

	// DELETE ISSUE BY ID
	@Override
	public void deleteIssue(int id) {
		IssuesEntity issueToDelete = issueRepo.getIssueById(id);
		if (issueToDelete != null) {
			issueRepo.deleteIssue(issueToDelete);
		} else {
			throw new MyExcMessages("Issue with given id does not exist !");
		}
	}

}
