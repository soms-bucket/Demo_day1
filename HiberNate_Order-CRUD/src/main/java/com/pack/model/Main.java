package com.pack.model;

import com.pack.dao.OrderDAO;
import com.pack.dao.OrderItemDAO;
import com.pack.model.Order;
import com.pack.model.OrderItem;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		OrderDAO orderDAO = new OrderDAO();
		OrderItemDAO orderItemDAO = new OrderItemDAO();
		
		// Create Order
		Order order = new Order();
		// Call setter for date
		Date orderDate = new Date(); 
		order.setOrderDate(orderDate);
		List<OrderItem> items = Arrays.asList(new OrderItem(order,"soft",56));
 		order.setOrderItems(items);

		//save order
		orderDAO.saveOrUpdate(order);
		//orderDAO.getById(order.getId());
		//System.out.println("Order created: " + order);
/*	
		// Create OrderItem
		OrderItem orderItem1 = new OrderItem();
		// Call setter for orderIem1
		orderItem1.setOrder(order);
		orderItem1.setProduct("Pizza");
		orderItem1.setQuantity(5);
		// Save OrderItem
		orderItemDAO.saveOrUpdate(orderItem1);
		System.out.println("Order item created: " + orderItem1);
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setOrder(order);
		orderItem2.setProduct("Mojito");
		orderItem2.setQuantity(6);
		// Save OrderItem
		// save another orderItem
		orderItemDAO.saveOrUpdate(orderItem2);
		// Read Order
		Order retrievedOrder = orderDAO.getById(order.getId());
		System.out.println("Retrieved order: " + retrievedOrder);
		// Read OrderItems
		List<OrderItem> orderItems = orderItemDAO.getById(order.getId());
		System.out.println("Order items for order " + order.getId() + ": " + orderItems);
		// Update OrderItem
		orderItem1.setQuantity(3);*/
//		orderItemDAO.saveOrUpdate(orderItem1);
//		System.out.println("Updated order item: " + orderItem1);
//		// Delete OrderItem
//		// Delete Order

	}

}
