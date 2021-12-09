package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {

	private static CustomerDAO customerDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("billy.jane@gmail.com");
		customer.setFullname("Jane Billy");
		customer.setCity("New York");
		customer.setCountry("United States");
		customer.setAddress("100 North Avenue");
		customer.setPassword("jane2021");
		customer.setPhone("1804585696");
		customer.setZipcode("45989");
		
		Customer saveCustomer = customerDAO.create(customer);
		assertTrue(saveCustomer.getCustomerId()>0);
	}

	@Test
	public void testGet() {
		Integer customerId=1;
		Customer customer = customerDAO.get(customerId);
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		Integer customerId=1;
		Customer customer = customerDAO.get(customerId);
		String fullName="Tom M Eagar";
		customer.setFullname(fullName);
		Customer updatedCustomer = customerDAO.update(customer);
		assertTrue(updatedCustomer.getFullname().equals(fullName));
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId=1;
		customerDAO.delete(customerId);
		Customer customer = customerDAO.get(customerId);
		assertNull(customer);
	}

	@Test
	public void testListAll() {
		List<Customer> listAll = customerDAO.listAll();
		assertNotNull(listAll);
	}

	@Test
	public void testCount() {
		long countCustomer = customerDAO.count();
		assertTrue(countCustomer==2);
	}
	
	@Test
	public void testFindByEmail() {
		String email="billy.jane@gmail.com";
		Customer findByEmail = customerDAO.findByEmail(email);
		assertNotNull(findByEmail);
	}
	
	@Test
	public void testCheckLogin() {
		String email="AlizehOfficial@gmail.com";
		String password="alizeh";
		boolean checkLogin = customerDAO.checkLogin(email, password);
		assertTrue(checkLogin);
	}

}
