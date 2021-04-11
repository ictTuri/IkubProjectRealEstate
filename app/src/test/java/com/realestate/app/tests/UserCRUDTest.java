package com.realestate.app.tests;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.realestate.app.entity.UserEntity;
import com.realestate.app.repository.impl.UserRepositoryImpl;
import com.realestate.app.utils.UserUtil;

@SpringBootTest
@Transactional
class UserCRUDTest {
	
	@Autowired
	UserRepositoryImpl userRepository;
	
	@Test
	 void givenUsername_whenRetrieved_thenGetUserData() {
		UserEntity user = UserUtil.createUser();
		userRepository.insertUser(user);
		String username = "test123";
		
		UserEntity userRetrieved = userRepository.getUserByUsername(username);
		
		Assertions.assertEquals(username, userRetrieved.getUsername());
	}
	
	@Test
	 void givenUser_whenUpdate_thenGetUpdatedUser() {
		UserEntity user = UserUtil.createUser();
		userRepository.insertUser(user);
		user.setFirstName("testUpdate");
		
		userRepository.updateUser(user);
		
		Assertions.assertEquals("testUpdate", userRepository.getUserByUsername("test123").getFirstName());
	}
	
	@Test
	 void givenUser_whenSave_thenGetCreatedUser() {
		Integer userSize = userRepository.getAllUsers(null).size();
		UserEntity user = UserUtil.createUserAdmin();

		userRepository.insertUser(user);
		
		Assertions.assertEquals(userSize+1, userRepository.getAllUsers(null).size());
		Assertions.assertNotNull(userRepository.getUserByUsername("admin123"));
	}
	
	@Test
	 void givenWrongUsername_whenRetrieved_thenGetNoResult() {
		String username = "test";
		
		UserEntity user = userRepository.getUserByUsername(username);
		
		Assertions.assertNull(user);
	}
	
	
	@Test
	 void givenUser_whenSoftDelete_thenGetNoResult() {
		UserEntity user = UserUtil.createUserAdmin();
		userRepository.insertUser(user);

		userRepository.deleteUser(user);
		
		Assertions.assertNull(userRepository.getUserByUsername("admin123"));
	}
	
}
