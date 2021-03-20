package com.realestate.app.entity;

import javax.persistence.*;

import lombok.Data;

@Table(name = "roles")
@Entity
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleId;
	@Column(name = "role_name")
	private int roleName;
}
