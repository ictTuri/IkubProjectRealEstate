package com.realestate.app.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDtoForCreate {
	
	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	private String email;

	@NotBlank(message = "Username is mandatory")
	private String username;

	@NotBlank(message = "Password is mandatory")
	private String password;

	@NotNull(message = "Role is mandatory")
	private int role;

	private boolean active;
	
}
