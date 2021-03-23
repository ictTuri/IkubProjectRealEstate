package com.realestate.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.app.entity.UserEntity;
import com.realestate.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	UserRepository adminRepository;

	@Override
	public List<UserEntity> allUsers() {
		return null;
	}

	@Override
	public void addOwner(UserEntity user) {

	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		return null;
	}

	@Override
	public UserEntity deleteUser(String username) {
		return null;
	}

}
