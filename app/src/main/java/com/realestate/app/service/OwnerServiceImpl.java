package com.realestate.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.app.model.BoughtModel;
import com.realestate.app.model.IssuesModel;
import com.realestate.app.model.LocationModel;
import com.realestate.app.model.PropertyInfoModel;
import com.realestate.app.model.PropertyModel;
import com.realestate.app.model.RentedModel;
import com.realestate.app.model.UserModel;

@Service
public class OwnerServiceImpl implements OwnerService{

	@Override
	public List<PropertyModel> allOwnerProperties(String username) {
		return null;
	}

	@Override
	public List<PropertyModel> allProperties() {
		return null;
	}

	@Override
	public List<UserModel> allOwnerRelatedUsers() {
		return null;
	}

	@Override
	public List<RentedModel> allRented() {
		return null;
	}

	@Override
	public List<BoughtModel> allSold() {
		return null;
	}

	@Override
	public List<IssuesModel> allIssues() {
		return null;
	}

	@Override
	public IssuesModel issueById(int id) {
		return null;
	}

	@Override
	public UserModel showProfile() {
		return null;
	}

	@Override
	public void addProperty(PropertyModel property) {
		
	}

	@Override
	public void addRented(RentedModel rented) {
	}

	@Override
	public void addBought(BoughtModel bought) {
		
	}

	@Override
	public void addLocation(LocationModel location) {
		
	}

	@Override
	public UserModel updateProfile() {
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
	public PropertyInfoModel updatePropertyInfo(int id) {
		return null;
	}

	@Override
	public IssuesModel updateIssues(int id) {
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
