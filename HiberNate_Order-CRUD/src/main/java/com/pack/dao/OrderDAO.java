package com.pack.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pack.model.Order;

public class OrderDAO {
	// Create reference of Session Factory
	private SessionFactory factory;

	public OrderDAO() {
		Configuration cfg = new Configuration()
				.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
	}

	public void saveOrUpdate(Order order) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(order);
			System.out.println(order);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Order getById(int id) {
		Session session = factory.openSession();
		Order order = null;
		// Write the business logic to get an id
		try {
			order = session.get(Order.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return order;
	}

	public void delete(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		// Write business Logic to delete the object

		try {
			tx = session.beginTransaction();
			Order order = session.get(Order.class, id);
			if (order != null) {
				// Call delete ()
				session.delete(order);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
