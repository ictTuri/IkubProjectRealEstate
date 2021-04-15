package com.realestate.app.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.realestate.app.entity.UserEntity;
import com.realestate.app.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryImplTest {

	
	@Autowired
	private TestEntityManager entityManager;

	@Mock
	private UserRepository repository;

	@Test
	void findByUsernameShouldReturnUser() throws Exception {
		UserEntity user = getUser();
				
		this.entityManager.persist(user);
		
		when(this.repository.getUserByUsername("Test")).thenReturn(user);
		
		UserEntity userRetrieved = this.repository.getUserByUsername("Test");
		assertThat(userRetrieved.getUsername()).isEqualTo("Test");
		assertThat(userRetrieved.getPassword()).isEqualTo("pass");
	}
	
	UserEntity getUser() {
		UserEntity user = new UserEntity();
		user.setActive(true);
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setEmail("Test@gmail.com");
		user.setPassword("pass");
		user.setUsername("Test");
		user.setVersion(0);
		user.setUserId(null);
		user.setRole(this.repository.getRoleById(1));
		
		return user;
	}
	

}
