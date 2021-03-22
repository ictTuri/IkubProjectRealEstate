package com.realestate.app.service;

import java.util.List;

import com.realestate.app.entity.Bought;
import com.realestate.app.entity.Issues;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.Rented;


public interface UserService {
	
	//FUNCTIONS TO GET DATA FROM DATABASE 
	Property getProperty(int id);
	
	List<Rented> showRented();
	
	List<Bought> showBought();
	
	//FUNCTIONS TO POST DATA TO DATABASE 
	void createIssue(Issues issue);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	void deleteIssue(int id);
	
}
