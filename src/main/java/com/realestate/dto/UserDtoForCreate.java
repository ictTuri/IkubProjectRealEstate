package com.realestate.dto;

import java.util.GregorianCalendar;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class UserDtoForCreate {
	
	private Long id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String username;
	private boolean valid;
	private GregorianCalendar created_on;
	private GregorianCalendar updated_on;
	private String role_id;
	

}
