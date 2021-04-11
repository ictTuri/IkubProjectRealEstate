package com.realestate.app.service;

import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserForCreateDto;
import com.realestate.app.dto.UserRegisterDto;

public interface UserProfileService {
	//SHOW PROFILE (USER)
	UserDto getLoggedUser();
	
	// REGISTER USER
	UserRegisterDto addUser(UserRegisterDto user);
	
	//UPDATE PROFILE (USER)
	UserDto updateLoggedUser(UserForCreateDto user);

	//DELETE PROFILE (USER)
	void deleteLoggedUser();

}
