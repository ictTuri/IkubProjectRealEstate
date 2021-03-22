package com.realestate.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.app.MyExcMessages;
import com.realestate.app.NoDataFromQueryExc;
import com.realestate.app.entity.Location;
import com.realestate.app.entity.Property;
import com.realestate.app.entity.PropertyInfo;
import com.realestate.app.entity.Rented;
import com.realestate.app.entity.Role;
import com.realestate.app.entity.User;
import com.realestate.app.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	// RETRIEVE OPERATIONS DOWN HERE
	
	@Override
	public List<Property> allProperties() {
		return adminRepository.getAllProperties();
	}

	@Override
	public List<User> allUsers() {
		return adminRepository.getAllUsers();
	}
	
	@Override
	public User showUserById(Integer id) {
		User u = adminRepository.getUserById(id);
		if(u!=null) {
			return u ;
		}else 
			throw new NoDataFromQueryExc("No User with this Id exist!");
	}

	@Override
	public List<User> allOwners() {
		return adminRepository.getAllOwners();
	}

	@Override
	public List<User> allClients() {
		return adminRepository.getAllClients();
	}
	
	// INSERT OPERATIONS DOWN HERE

	@Override
	public void addOwner(User user) {
		if(adminRepository.existUsername(user.getUsername()) || adminRepository.existEmail(user.getEmail())) {
			throw new MyExcMessages("Username or email already exist");
		}
		adminRepository.insertUser(user);
	}

	@Override
	public void addProperty(Property property) {
		adminRepository.insertPropertyWithInfo(property);
	}

	@Override
	public void addClient(User user) {
		user.setRoleId(new Role(1));
		adminRepository.insertUser(user);
	}

	@Override
	public void addLocation(Location location) {
		adminRepository.insertLocation(location);
	}
	
	// UPDATE OPERATIONS DOWN HERE

	@Override
	public User updateUser(User user) {
		if(adminRepository.existUsername(user.getUsername())) {
			return adminRepository.updateUser(user);
		}else {
			throw new MyExcMessages("No user with give username");
		}
	}

	@Override
	public Property updateProperty(int id) {
		return null;
	}

	@Override
	public Rented updateRented(int id) {
		return null;
	}

	@Override
	public Location updateLocation(int id) {
		return null;
	}

	@Override
	public PropertyInfo updatePropertyInfo(int id) {
		return null;
	}

	
	// DELETE OPERATIONS DOWN HERE
	
	@Override
	public User deleteUser(String username) {
		User u = adminRepository.getUserByUsername(username);
		if(u!=null) {
			u.setActive(false);
			return adminRepository.updateUser(u);
		}else {
			throw new MyExcMessages("can not find user with given username . please check if user already Deactivated");
		}
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
