package com.realestate.app.service;

import java.util.List;

import com.realestate.app.entity.Location;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.PropertyInfo;
import com.realestate.app.entity.Rented;
import com.realestate.app.entity.User;


public interface AdminService {

	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<Property> allProperties();

	List<User> allUsers();

	User showUserById(Integer id);
	
	List<User> allOwners();

	List<User> allClients();
	
	//FUNCTIONS TO STORE DATA TO DATABASE 
	void addOwner(User user);
	
	void addProperty(Property property);
	
	void addClient(User user);
	
	void addLocation(Location location);
	
	//FUNCTIONS TO UPDATE DATA ON DATABASE 
	User updateUser(User user);
	
	Property updateProperty(int id);
	
	Rented updateRented(int id);
	
	Location updateLocation(int id);
	
	PropertyInfo updatePropertyInfo(int id);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	User deleteUser(String username);
	
	void deleteProperty(int id);
	
	 void deleteRented(int id);
	 
	 void deleteLocation(int id);
	 
	 void deleteBought(int id);
}

















