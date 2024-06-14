package com.pack;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pack.persistence.HibernateUtil;

public class UseEmployee {
	public static void main(String[] args) {
		
		EmployeeDAO empDao = new EmployeeDAO();
		int ch;
		do {
			System.out.println("Enter a choice \n"
				+ "1. Get All Data \n"
				+ "2. Save all information\n"
				+ "3. update data  \n "
				+ "4. Retrieve data of emp 2 \n"
				+ "5. Delete data"
				+ "6. Exit");
		
			Scanner sc = new Scanner(System.in);
			ch = sc.nextInt();
		
			switch (ch)
			{
				case 1:
						List<Employee> empList = empDao.getAllEmployees();
						System.out.println(empList);
						break;
				case 4: 
						Employee e = empDao.getEmployeeById(1);
						System.out.println(e); 
						break;
						
				case 2: 
						Employee emp1 = new Employee(2,"Bob","KJBK", 34135);
						empDao.saveEmployee(emp1);
						break;
				case 3:
						empDao.updateEmployee(new Employee(1,"mini","mac",44));
						break;
				case 5: 
						empDao.deleteEmployee(1);
						break;
				
			}
		}while(ch!=6);
		
		
	}
}