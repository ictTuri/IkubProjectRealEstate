package com.realestate.app.dto;

import lombok.Data;

@Data
public class UserDto{
	
	private String firstName;

	private String lastName;

	private String email;

	private RoleDto role;
	
	private boolean active;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean isActive) {
		this.active = isActive;
	}
}
