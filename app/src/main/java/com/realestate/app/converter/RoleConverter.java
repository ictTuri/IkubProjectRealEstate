package com.realestate.app.converter;

import com.realestate.app.dto.RoleDto;
import com.realestate.app.entity.RoleEntity;

public class RoleConverter {
	
	private RoleConverter() {}
	
	public static RoleDto toDto(RoleEntity role) {
		RoleDto toReturn = new RoleDto();
		toReturn.setRoleId(role.getRoleId());
		toReturn.setRoleName(role.getRoleName());
		return toReturn;
	}

	public static RoleEntity toEntity(RoleDto dto) {
		RoleEntity toReturn=new RoleEntity();
		toReturn.setRoleId(dto.getRoleId());
		toReturn.setRoleName(dto.getRoleName());
		return toReturn;
	}
	
}
