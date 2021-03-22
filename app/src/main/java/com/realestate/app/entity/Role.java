package com.realestate.app.entity;

import javax.persistence.*;

import lombok.Data;

@Table(name = "roles")
@Entity
@Data
public class Role {

	public Role() {
		super();
	}

	public Role(Integer roleId) {
		super();
		this.roleId = roleId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;
	@Column(name = "role_name")
	private String roleName;

}
