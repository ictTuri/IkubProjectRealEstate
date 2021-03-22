package com.realestate.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.app.entity.Bought;
import com.realestate.app.entity.Issues;
import com.realestate.app.entity.Location;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.PropertyInfo;
import com.realestate.app.entity.Rented;
import com.realestate.app.entity.User;

@Service
public class OwnerServiceImpl implements OwnerService{

	@Override
	public List<Property> allOwnerProperties(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Property> allProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> allOwnerRelatedUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rented> allRented() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bought> allSold() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issues> allIssues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issues issueById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User showProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProperty(Property property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRented(Rented rented) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBought(Bought bought) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLocation(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property updateProperty(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rented updateRented(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyInfo updatePropertyInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issues updateIssues(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProperty(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRented(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLocation(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBought(int id) {
		// TODO Auto-generated method stub
		
	}

	

}
