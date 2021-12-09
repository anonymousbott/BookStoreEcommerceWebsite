package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.lookup.TypeSystem.HashedParameterizedTypes;

import com.bookstore.entity.Customer;
import com.bookstore.hash.HashGeneratorUtils;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {
	
	

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		return super.create(customer);
	}

	@Override
	public Customer get(Object id) {
		return super.find(Customer.class, id);
	}
	

	@Override
	public Customer update(Customer customer) {
		return super.update(customer);
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
		
	}

	@Override
	public List<Customer> listAll() {
		List<Customer> listCustomer = super.findWithNamedQuery("Customer.findAll");
		return listCustomer;
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}
	
	public Customer findByEmail(String email) {
		List<Customer> customerByEmail = super.findWithNamedQuery("Customer.findByEmail", "email", email);
		if(customerByEmail!= null && customerByEmail.size() >0) {
			return customerByEmail.get(0);
		}
		return  null;
	}

	public boolean checkLogin(String email,String password) {
		Map<String, Object> parameters = new HashMap<>();
		password=HashGeneratorUtils.generateMD5(password);
		parameters.put("email", email);
		parameters.put("password", password);
		List<Customer> listCustomer = super.findWithNamedQuery("Customer.checkLogin", parameters);
		if(listCustomer!= null && listCustomer.size()>0) {
			return true;
		}
		return false;
	}

}
