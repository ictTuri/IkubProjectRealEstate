package com.realestate.app.service;

import java.util.List;


import com.realestate.app.model.BoughtModel;
import com.realestate.app.model.IssuesModel;
import com.realestate.app.model.PropertyModel;
import com.realestate.app.model.RentedModel;


public interface UserService {
	
	//FUNCTIONS TO GET DATA FROM DATABASE 
	PropertyModel getProperty(int id);
	
	List<RentedModel> showRented();
	
	List<BoughtModel> showBought();
	
	//FUNCTIONS TO POST DATA TO DATABASE 
	void createIssue(IssuesModel issue);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	void deleteIssue(int id);
	
}
