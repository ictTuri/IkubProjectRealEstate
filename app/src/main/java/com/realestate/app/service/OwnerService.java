package com.realestate.app.service;

import java.util.List;


import com.realestate.app.model.BoughtModel;
import com.realestate.app.model.IssuesModel;
import com.realestate.app.model.LocationModel;
import com.realestate.app.model.PropertyInfoModel;
import com.realestate.app.model.PropertyModel;
import com.realestate.app.model.RentedModel;
import com.realestate.app.model.UserModel;


public interface OwnerService {
	//FUNCTIONS TO GET DATA FROM DATABASE
	List<PropertyModel> allOwnerProperties(String username);
	
	List<PropertyModel> allProperties();

	List<UserModel> allOwnerRelatedUsers();
	
	List<RentedModel> allRented();
	
	List<BoughtModel> allSold();
	
	List<IssuesModel> allIssues();
	
	IssuesModel issueById(int id);
	
	 UserModel showProfile();
	
	//FUNCTIONS TO INSERT DATA TO DATABASE
	void addProperty(PropertyModel property);
	
	void addRented(RentedModel rented);
	
	void addBought(BoughtModel bought);
	
	 void addLocation(LocationModel location);
	 
	//FUNCTIONS TO UPDATE DATA ON DATABASE
	 UserModel updateProfile();
	 
	 PropertyModel updateProperty(int id);
	 
	 RentedModel updateRented(int id);
	 
	 PropertyInfoModel updatePropertyInfo(int id);
	 
	 IssuesModel updateIssues(int id);
	 
	//FUNCTIONS TO DELETE DATA FROM DATABASE
	 void deleteUser(String username);
	 
	 void deleteProperty(int id);
	 
	 void deleteRented(int id);
	 
	 void deleteLocation(int id);
	 
	 void deleteBought(int id);
	 
}
