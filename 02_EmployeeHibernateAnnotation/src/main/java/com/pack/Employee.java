package com.pack;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity// This class represents an entity class
@Table(name = "Employee1") //represents th table name in db
public class Employee{
		 @Id //Primary key
		 // Automatic generation of primary key
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 @Column(name = "id") // It represents the corresponding column name in table
		 private int id;
 
			@Column(name = "first_name")// It represents the corresponding column name in table
		 private String firstName;
 
			@Column(name = "last_name")// It represents the corresponding column name in table
		 private String lastName;
 
			@Column(name = "salary")// It represents the corresponding column name in table
		 private int salary;
 
		 public Employee() {}
		 public Employee(String fname, String lname, int salary) {
		 this.firstName = fname;
		 this.lastName = lname;
		 this.salary = salary;
		 }
		
		 public Employee(int id, String firstName, String lastName, int salary) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.salary = salary;
		}
		public int getId() {
		 return id;
		 }
		
		 public void setId( int id ) {
		 this.id = id;
		 }
		
		 public String getFirstName() {
		 return firstName;
		 }
		
		 public void setFirstName( String first_name ) {
		 this.firstName = first_name;
		 }
		
		 public String getLastName() {
		 return lastName;
		 }
		
		 public void setLastName( String last_name ) {
		 this.lastName = last_name;
		 }
		
		 public int getSalary() {
		 return salary;
		 }
		
		 public void setSalary( int salary ) {
		 this.salary = salary;
		 }
		@Override
		public String toString() {
			return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
					+ "]";
		}
		
		}