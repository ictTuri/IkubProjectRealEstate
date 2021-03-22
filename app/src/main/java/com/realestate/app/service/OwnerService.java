package com.realestate.app.service;

import java.util.List;

import com.realestate.app.entity.Bought;
import com.realestate.app.entity.Issues;
import com.realestate.app.entity.Location;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.PropertyInfo;
import com.realestate.app.entity.Rented;
import com.realestate.app.entity.User;


public interface OwnerService {
	//FUNCTIONS TO GET DATA FROM DATABASE
	List<Property> allOwnerProperties(String username);
	
	List<Property> allProperties();

	List<User> allOwnerRelatedUsers();
	
	List<Rented> allRented();
	
	List<Bought> allSold();
	
	List<Issues> allIssues();
	
	Issues issueById(int id);
	
	 User showProfile();
	
	//FUNCTIONS TO INSERT DATA TO DATABASE
	void addProperty(Property property);
	
	void addRented(Rented rented);
	
	void addBought(Bought bought);
	
	 void addLocation(Location location);
	 
	//FUNCTIONS TO UPDATE DATA ON DATABASE
	 User updateProfile();
	 
	 Property updateProperty(int id);
	 
	 Rented updateRented(int id);
	 
	 PropertyInfo updatePropertyInfo(int id);
	 
	 Issues updateIssues(int id);
	 
	//FUNCTIONS TO DELETE DATA FROM DATABASE
	 void deleteUser(String username);
	 
	 void deleteProperty(int id);
	 
	 void deleteRented(int id);
	 
	 void deleteLocation(int id);
	 
	 void deleteBought(int id);
	 
}
