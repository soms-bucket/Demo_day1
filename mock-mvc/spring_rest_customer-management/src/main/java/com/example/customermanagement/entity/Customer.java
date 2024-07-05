package com.example.customermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "newcustomer")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;  
	
	@Column(name = "Firstname", nullable = false)
	private String firstName;
	@Column(name = "Lastname", nullable = false)
	private String lastName;
	@Column(name = "Email", nullable = false)
	private String email;
	@Column(name = "Phone", nullable = false)
	private String phone;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    public Customer(@NotNull String firstName, String lastName, String email, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + "]";
	}

	
	
	
}
