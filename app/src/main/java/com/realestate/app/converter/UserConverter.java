package com.realestate.app.converter;

import java.util.ArrayList;
import java.util.List;

import com.realestate.app.dto.UserDto;
import com.realestate.app.dto.UserForCreateDto;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;

public class UserConverter {

	UserConverter() {
	}

	public static UserDto toDto(UserEntity entity) {
		UserDto toReturn = new UserDto();
		toReturn.setFirstName(entity.getFirstName());
		toReturn.setLastName(entity.getLastName());
		toReturn.setEmail(entity.getEmail());
		toReturn.setRole(RoleConverter.toDto(entity.getRole()));
		toReturn.setActive(entity.isActive());
		return toReturn;
	}

	public static List<UserDto> toDto(List<UserEntity> entity) {
		List<UserDto> toReturn = new ArrayList<>();
		for (UserEntity ue : entity) {
			toReturn.add(toDto(ue));
		}
		return toReturn;
	}

	public static UserEntity toEntity(UserDto dto) {
		UserEntity toReturn = new UserEntity();
		toReturn.setFirstName(dto.getFirstName());
		toReturn.setLastName(dto.getLastName());
		toReturn.setEmail(dto.getEmail());
		toReturn.setRole(RoleConverter.toEntity(dto.getRole()));
		toReturn.setActive(dto.isActive());
		return toReturn;
	}

	public static UserEntity toEntityForCreate(UserForCreateDto dto, RoleEntity subEntity) {
		UserEntity toReturn = new UserEntity();
		toReturn.setUserId(null);
		toReturn.setFirstName(dto.getFirstName());
		toReturn.setLastName(dto.getLastName());
		toReturn.setEmail(dto.getEmail());
		toReturn.setUsername(dto.getUsername());
		toReturn.setPassword(dto.getPassword());
		toReturn.setRole(subEntity);
		toReturn.setActive(dto.isActive());
		return toReturn;
	}
}
