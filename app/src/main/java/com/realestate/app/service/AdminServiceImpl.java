package com.realestate.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.app.model.LocationModel;
import com.realestate.app.model.PropertyInfoModel;
import com.realestate.app.model.PropertyModel;
import com.realestate.app.model.RentedModel;
import com.realestate.app.model.UserModel;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public List<PropertyModel> allProperties() {
		return null;
	}

	@Override
	public List<UserModel> allUsers() {
		return null;
	}

	@Override
	public List<UserModel> allOwners() {
		return null;
	}

	@Override
	public List<UserModel> allClients() {
		return null;
	}

	@Override
	public void addOwner(UserModel user) {
		
	}

	@Override
	public void addProperty(PropertyModel property) {
		
	}

	@Override
	public void addClient(UserModel user) {
		
	}

	@Override
	public void addLocation(LocationModel location) {
		
	}

	@Override
	public UserModel updateUser(String username) {
		return null;
	}

	@Override
	public PropertyModel updateProperty(int id) {
		return null;
	}

	@Override
	public RentedModel updateRented(int id) {
		return null;
	}

	@Override
	public LocationModel updateLocation(int id) {
		return null;
	}

	@Override
	public PropertyInfoModel updatePropertyInfo(int id) {
		return null;
	}

	@Override
	public void deleteUser(String username) {
		
	}

	@Override
	public void deleteProperty(int id) {
		
	}

	@Override
	public void deleteRented(int id) {
		
	}

	@Override
	public void deleteLocation(int id) {
		
	}

	@Override
	public void deleteBought(int id) {
		
	}

}
