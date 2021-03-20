package com.realestate.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.app.model.BoughtModel;
import com.realestate.app.model.IssuesModel;
import com.realestate.app.model.PropertyModel;
import com.realestate.app.model.RentedModel;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public PropertyModel getProperty(int id) {
		return null;
	}

	@Override
	public List<RentedModel> showRented() {
		return null;
	}

	@Override
	public List<BoughtModel> showBought() {
		return null;
	}

	@Override
	public void createIssue(IssuesModel issue) {
		
	}

	@Override
	public void deleteIssue(int id) {
		
	}

}
