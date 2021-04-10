package com.realestate.app.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFilter {
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String sortBy;
	
	private String order;
}
