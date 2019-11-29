package com.demo.model.persistance;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.model.persistance.Customer;

@Repository
public class CustomerDaoImplentation implements CustomerDao {

	@Autowired

	private SessionFactory factory;

	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		return getSession().createQuery("from Customer").list();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return (Customer) getSession().get(Customer.class, customerId);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		getSession().save(customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		getSession().update(customer);
		return customer;
	}

	@Override
	public Customer removeCustomer(int customerId) {
		Customer customerToBeDeleted =getCustomerById(customerId);
		getSession().delete(customerToBeDeleted);
		return customerToBeDeleted;
	}

	

}
