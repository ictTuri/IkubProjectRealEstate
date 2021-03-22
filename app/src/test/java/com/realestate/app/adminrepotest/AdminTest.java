package com.realestate.app.adminrepotest;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.realestate.app.entity.User;
import com.realestate.app.repository.AdminRepository;

@SpringBootTest
@Transactional
class AdminTest {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Test
	 void givenUsername_whenRetrieved_thenGetUserData() {
		User user = UserUtil.createUser();
		adminRepository.insertUser(user);
		String username = "test123";
		
		User userRetrieved = adminRepository.getUserByUsername(username);
		
		Assertions.assertEquals(username, userRetrieved.getUsername());
	}
	
	@Test
	 void givenUser_whenUpdate_thenGetUpdatedUser() {
		User user = UserUtil.createUser();
		adminRepository.insertUser(user);
		user.setFirstName("testUpdate");
		
		adminRepository.updateUser(user);
		
		Assertions.assertEquals("testUpdate", adminRepository.getUserByUsername("test123").getFirstName());
	}
	
	@Test
	 void givenUser_whenSave_thenGetCreatedUser() {
		Integer userSize = adminRepository.getAllUsers().size();
		User user = UserUtil.createUserAdmin();

		adminRepository.insertUser(user);
		
		Assertions.assertEquals(userSize+1, adminRepository.getAllUsers().size());
		Assertions.assertNotNull(adminRepository.getUserByUsername("admin123"));
	}
	
	@Test
	 void givenWrongUsername_whenRetrieved_thenGetNoResult() {
		String username = "test";
		
		User user = adminRepository.getUserByUsername(username);
		
		Assertions.assertNull(user);
	}
	
	
	@Test
	 void givenUser_whenSoftDelete_thenGetNoResult() {
		User user = UserUtil.createUserAdmin();
		adminRepository.insertUser(user);

		adminRepository.deleteUser(user);
		
		Assertions.assertNull(adminRepository.getUserByUsername("admin123"));
	}
	
}
