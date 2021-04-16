package com.realestate.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.realestate.app.controller.RegisterController;
import com.realestate.app.dto.UserRegisterDto;
import com.realestate.app.service.UserProfileService;

@ExtendWith(MockitoExtension.class)
class RegisterControllerTest {
	@InjectMocks
	RegisterController registerController;
     
    @Mock
    UserProfileService userProfileService;
    
    @Test
    void testAddUser() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        UserRegisterDto user = getUser();
        
        when(userProfileService.addUser(user)).thenReturn(user);
         
        ResponseEntity<UserRegisterDto> responseEntity = registerController.addUser(user);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

	private UserRegisterDto getUser() {
		UserRegisterDto userToReturn = new UserRegisterDto();
		userToReturn.setEmail("Test@gmail.com");
		userToReturn.setFirstName("TestFirst");
		userToReturn.setLastName("TestLast");
		userToReturn.setUsername("TestUser");
		userToReturn.setPassword("TestPass");
		return userToReturn;
	}
}
