package com.realestate.app.dto;

import lombok.Data;

@Data
public class UserDto{
	
	private String firstName;

	private String lastName;

	private String email;

	private RoleDto role;
	
	private boolean active;

}
