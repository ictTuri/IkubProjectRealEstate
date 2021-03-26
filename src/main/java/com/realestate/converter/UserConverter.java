package com.realestate.converter;

import com.realestate.dto.UserDto;
import com.realestate.model.UserEntity;

public class UserConverter {
	
	public static UserDto toDto(UserEntity entity) {
		
		UserDto toReturn = new UserDto();
		//toReturn.setId(entity.getId());
		
		return toReturn;
		
	}

}
