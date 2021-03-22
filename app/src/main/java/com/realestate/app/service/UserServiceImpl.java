package com.realestate.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.app.entity.Bought;
import com.realestate.app.entity.Issues;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.Rented;


@Service
public class UserServiceImpl implements UserService{

	@Override
	public Property getProperty(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rented> showRented() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bought> showBought() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createIssue(Issues issue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIssue(int id) {
		// TODO Auto-generated method stub
		
	}

	

}
