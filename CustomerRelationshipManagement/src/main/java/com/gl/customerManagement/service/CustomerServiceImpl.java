package com.gl.customerManagement.service;

import com.gl.customerManagement.entity.Customer;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Autowired
	public CustomerServiceImpl( SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}
	
	
	@Transactional
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		
		Transaction tx= session.beginTransaction();
		List<Customer> customers= session.createQuery("from Customer").list();		
		
		tx.commit();

		
		return customers;
	}


	@Transactional

	public Customer findById(int theid) {
		// TODO Auto-generated method stub
		
		Customer customer= new Customer();
		
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		customer = session.get(Customer.class, theid);

		tx.commit();

		return customer;

	}

	@Transactional
		public void save(Customer theCustomer) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theCustomer);

		tx.commit();

	}

	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record
		session.delete(customer);

		tx.commit();

	}

}
