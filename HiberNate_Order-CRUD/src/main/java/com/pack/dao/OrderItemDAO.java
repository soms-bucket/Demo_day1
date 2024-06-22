package com.pack.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pack.model.Order;
import com.pack.model.OrderItem;

public class OrderItemDAO {

	private SessionFactory factory;

	public OrderItemDAO() {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
	}

	public void saveOrUpdate(OrderItem orderItem) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// Save OrderITem
			session.saveOrUpdate(orderItem);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				// Call rollback if transaction is null
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<OrderItem> getById(int id) {
		Session session = factory.openSession();
		List<OrderItem> orderItems = null;
		try {
			// Get specific order item
			Order order = session.get(Order.class,id);
			orderItems = order.getOrderItems();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return orderItems;
	}

	public void delete(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			OrderItem orderItem = session.get(OrderItem.class, id);
			if (orderItem != null) {
				// Delete orderItem
				session.delete(orderItem);
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
