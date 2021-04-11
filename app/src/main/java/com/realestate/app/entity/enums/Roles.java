package com.realestate.app.entity.enums;

public enum Roles {
	OWNER("ROLE_OWNER"),
	CLIENT("ROLE_CLIENT");
	
	String value;

	private Roles(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
