package com.pack;



import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pack.persistence.HibernateUtil;

public class UseEmployee {
	public static void main(String[] args) {
		Employee empObj1= new Employee(201,"Ashok","Sharma",20000); // Transient State
 
		
		  Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            session.save(empObj1); // Persistent State
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	}
}