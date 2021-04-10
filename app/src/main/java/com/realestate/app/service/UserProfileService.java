package com.realestate.app.service;

import com.realestate.app.dto.UserDtoForCreate;
import com.realestate.app.entity.UserEntity;

public interface UserProfileService {
	//SHOW PROFILE (USER)
	UserEntity getLoggedUser();
	
	//UPDATE PROFILE (USER)
	UserEntity updateLoggedUser(UserDtoForCreate user);

	//DELETE PROFILE (USER)
	String deleteLoggedUser();

}
