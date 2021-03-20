package com.realestate.app.service;

import java.util.List;


import com.realestate.app.model.LocationModel;
import com.realestate.app.model.PropertyInfoModel;
import com.realestate.app.model.PropertyModel;
import com.realestate.app.model.RentedModel;
import com.realestate.app.model.UserModel;


public interface AdminService {

	//FUNCTIONS TO GET DATA FROM DATABASE 
	List<PropertyModel> allProperties();

	List<UserModel> allUsers();

	List<UserModel> allOwners();

	List<UserModel> allClients();
	
	//FUNCTIONS TO STORE DATA TO DATABASE 
	void addOwner(UserModel user);
	
	void addProperty(PropertyModel property);
	
	void addClient(UserModel user);
	
	void addLocation(LocationModel location);
	
	//FUNCTIONS TO UPDATE DATA ON DATABASE 
	UserModel updateUser(String username);
	
	PropertyModel updateProperty(int id);
	
	RentedModel updateRented(int id);
	
	LocationModel updateLocation(int id);
	
	PropertyInfoModel updatePropertyInfo(int id);
	
	//FUNCTIONS TO DELETE DATA FROM DATABASE 
	void deleteUser(String username);
	
	void deleteProperty(int id);
	
	 void deleteRented(int id);
	 
	 void deleteLocation(int id);
	 
	 void deleteBought(int id);
}

















