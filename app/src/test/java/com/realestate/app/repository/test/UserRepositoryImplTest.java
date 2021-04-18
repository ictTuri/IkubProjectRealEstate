package com.realestate.app.repository.test;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.filter.UserFilter;
import com.realestate.app.repository.impl.UserRepositoryImpl;

@SpringBootTest
@Transactional
class UserRepositoryImplTest {

	@Autowired
	private UserRepositoryImpl userRepository;

	
	@Test
	void givenUsername_whenRetrieved_thenGetUserData() {
		RoleEntity role = userRepository.getRoleById(2);
		UserEntity user = UserUtil.createUser();
		user.setRole(role);
		userRepository.insertUser(user);
		String username = "test";
		
		UserEntity userRetrieved = userRepository.getUserByUsername(username);
		
		Assertions.assertEquals(username, userRetrieved.getUsername());
	}
	
	@Test
	void givenUser_whenUpdate_thenGetUpdatedUser() {
		RoleEntity role = userRepository.getRoleById(3);
		UserEntity user = UserUtil.createUser();
		user.setRole(role);
		userRepository.insertUser(user);
		user.setFirstName("testUpdate");
		
		userRepository.updateUser(user);
		
		Assertions.assertEquals("testUpdate", userRepository.getUserByUsername("test").getFirstName());
	}
	
	@Test
	void givenUser_whenSave_thenGetCreatedUser() {
		RoleEntity role = userRepository.getRoleById(1);
		Integer userSize = userRepository.getAllUsers(new UserFilter()).size();
		UserEntity user = UserUtil.createUserAdmin();
		user.setRole(role);

		userRepository.insertUser(user);
		
		Assertions.assertEquals(userSize+1, userRepository.getAllUsers(new UserFilter()).size());
		Assertions.assertNotNull(userRepository.getUserByUsername("admin"));
	}
	
	@Test
	void givenWrongUsername_whenRetrieved_thenGetNoResult() {
		String username = "test";
		
		UserEntity user = userRepository.getUserByUsername(username);
		
		Assertions.assertNull(user);
	}
	
	
	@Test
	void givenUser_whenSoftDelete_thenGetNoResult() {
		RoleEntity role = userRepository.getRoleById(1);
		UserEntity user = UserUtil.createUserAdmin();
		user.setRole(role);
		userRepository.insertUser(user);

		userRepository.deleteUser(user);
		
		Assertions.assertNull(userRepository.getUserByUsername("admin123"));
	}
}
