package com.realestate.app.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "username")
	private String username;
	@Column(name = "pass")
	private String password;
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Role roleId;
	@Column(name = "is_active")
	private boolean isActive;
	
	public User(String firstName, String lastName, String email, String username, String password, Role roleId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.isActive = true;
	}
	
	

}
