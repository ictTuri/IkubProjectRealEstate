package com.realestate.app.service;

import com.realestate.app.entity.UserEntity;

public interface UserProfileService {

	UserEntity getLoggedUser(String username);

}
