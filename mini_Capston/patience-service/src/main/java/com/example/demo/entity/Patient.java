package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Patience")
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int patienceId;  
	
	@Column(name = "PatienceName", nullable = false)
	private String PatienceName;
	@Column(name = "gender", nullable = false)
	private String gender;
	@Column(name = "age", nullable = false)
	private int age;
	@Column(name = "Phone", nullable = false)
	private String phone;
	@Column(name = "address", nullable = false)
	private String address;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String patienceName, String gender, int age, String phone, String address) {
		super();
		PatienceName = patienceName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.address = address;
	}

	public int getPatienceId() {
		return patienceId;
	}

	public void setPatienceId(int patienceId) {
		this.patienceId = patienceId;
	}

	public String getPatienceName() {
		return PatienceName;
	}

	public void setPatienceName(String patienceName) {
		PatienceName = patienceName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
