package com.realestate.app.converter;

import com.realestate.app.dto.UserRegisterDto;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;

public class UserRegisterConverter {
	UserRegisterConverter() {
	}

	public static UserRegisterDto toDto(UserEntity entity) {
		UserRegisterDto toReturn = new UserRegisterDto();
		toReturn.setFirstName(entity.getFirstName());
		toReturn.setLastName(entity.getLastName());
		toReturn.setEmail(entity.getEmail());
		toReturn.setRole(entity.getRole().getRoleName());
		return toReturn;
	}

	public static UserEntity toEntityForCreate(UserRegisterDto dto, RoleEntity subEntity) {
		UserEntity toReturn = new UserEntity();
		toReturn.setUserId(null);
		toReturn.setFirstName(dto.getFirstName());
		toReturn.setLastName(dto.getLastName());
		toReturn.setEmail(dto.getEmail());
		toReturn.setUsername(dto.getUsername());
		toReturn.setPassword(dto.getPassword());
		toReturn.setRole(subEntity);
		return toReturn;
	}
}
