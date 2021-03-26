package com.realestate.model;


import java.io.Serializable;

import java.util.GregorianCalendar;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQuery(name="User.findAll", query="SELECT user FROM UserEntity user")
public class UserEntity implements Serializable{
	
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable=false, name="first_name", length = 50)
	private String firstName;
	
	@Column(nullable = false, name="last_name", length =50)
	private String lastName;
	
	@Column(nullable=false,name="email")
	private String email;
	
	@Column (nullable = false, name="username", length = 25)
	private String username;
	
	@Column (nullable = false, name ="password", length=50)
	private String password;
	
	@Column (name= "valid")
	private boolean valid;
	
	@Column (name="created_on")
	private GregorianCalendar createdOn;
	
	@Column(name="updated_on")
	private GregorianCalendar updatedOn;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public GregorianCalendar getCreatedOn() {
		return createdOn;
	}

	public void setCreated_on(GregorianCalendar createdOn) {
		this.createdOn = createdOn;
	}

	public GregorianCalendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdated_on(GregorianCalendar updatedOn) {
		this.updatedOn = updatedOn;
		
	
	}
	
	@OneToMany(fetch =FetchType.LAZY)
	@JoinColumn(name="role", referencedColumnName = "id")
	private RoleEntity role;
	

		
//@ManyToMany(fetch=FetchType.LAZY)
//@JoinTable(name="user_role",
//		joinColumns =@JoinColumn(name="user_id"),
//		inverseJoinColumns =@JoinColumn(name="role_id"))
//
//private List<RoleEntity> roles =new ArrayList();
//

	
	

}
