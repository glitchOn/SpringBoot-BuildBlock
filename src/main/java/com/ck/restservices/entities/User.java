package com.ck.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
    
	@Id
	@GeneratedValue
	private Long id ;
    
	@NotEmpty(message ="UserName is a mandatory field")
	@Column(name="USER_NAME",length=50,nullable=false,unique=true)
	private String userName;
	
	@Size(min=2, message ="First Name should have atleast 2 characters")
	@Column(name="FIRST_NAME",length=50,nullable=false,unique=false)
	private String firstName;
    
	@Column(name="LAST_NAME",length=50,nullable=false,unique=false)
	private String lastname;
    
	@Column(name="EMAIL",length=50,nullable=false,unique=false)
	private String email;
    
	@Column(name="ROLE",length=50,nullable=false,unique=false)
	private String role;
    
	@Column(name="SSN",length=50,nullable=false,unique=true)
	private String ssn;
	
    
    public User() {
	}
    
	public User(Long id, String userName, String firstName, String lastname, String email, String role, String ssn) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
    
    
	
}
