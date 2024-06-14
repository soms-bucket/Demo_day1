package com.pack;
//interact with Db

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pack.persistence.HibernateUtil;

public class EmployeeDAO {
	public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	public Employee getEmployeeById(int i) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	public void saveEmployee(Employee emp) throws IllegalStateException{
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction =  session.beginTransaction();
            session.save(emp);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	public void updateEmployee(Employee emp) throws IllegalStateException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            session.update(emp);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	public void deleteEmployee(Integer l) throws IllegalStateException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = (Transaction) session.beginTransaction();
            Employee emp = session.get(Employee.class, l);
            if (emp != null) {
                session.delete(emp);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
	
}

