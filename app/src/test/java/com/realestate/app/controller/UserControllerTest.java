package com.realestate.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.realestate.app.dto.UserDto;
import com.realestate.app.service.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class UserControllerTest {

	private MockMvc mock;
	
	@Mock
	private UserService userService;
	
	@org.junit.Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void whenGetOnController_thenReturnUser_andStatusFound() throws Exception {
		UserDto user = UserUtil.createUserOne();
		when(userService.userById(1)).thenReturn(user);
		
		mock.perform(get("/api/v1/users/1"))
			.andExpect(status().isFound());
	}
	

}
