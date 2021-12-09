package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;
import com.bookstore.hash.HashGeneratorUtils;

public class CustomerServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;
	private ReviewDAO reviewDAO;
	
	public CustomerServices() {
		
	}
	
	public CustomerServices(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		customerDAO = new CustomerDAO();
		reviewDAO = new ReviewDAO();
	}
	public void listAll() throws ServletException, IOException {
		listAll(null);
	}
	public void listAll(String message) throws ServletException, IOException {
		List<Customer> customerList = customerDAO.listAll();
		request.setAttribute("customerList", customerList);
		request.setAttribute("message", message);
		String pageName="customer_list.jsp";
		CommonUtility.forwardToPage(pageName, request, response);
		
	}
	
	public void readCustomerFields(Customer customer) {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		if(email!=null && !email.equals("")) {
			customer.setEmail(email);
		}
		customer.setFullname(fullName);
		if(password!=null && !password.equals("")) {
			customer.setPassword(password);
		}
		customer.setPhone(phone);
		customer.setCity(city);
		customer.setZipcode(zipCode);
		customer.setCountry(country);
		customer.setAddress(address);
	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer customerFindByEmail = customerDAO.findByEmail(email);
		if(customerFindByEmail!=null) {
			String message="Couldnt create Customer as customer with email "+email+" already exists";
			CommonUtility.showMessageBackend(message, request, response);
		}
		else {
			String message="Customer successfully created";
			//fullName password phone address city zipCode country
			Customer customer = new Customer();
			readCustomerFields(customer);
			String password = request.getParameter("password");
			if(password!=null) {
				String hashedPassword = HashGeneratorUtils.generateMD5(password);
				customer.setPassword(hashedPassword);	
			}
			customerDAO.create(customer);
			listAll(message);
		}
		
	}

	public void editCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		if(customer == null) {
			String message="Could not find customer with ID "+customerId;
			CommonUtility.showMessageBackend(message, request, response);
		}
		else {
			customer.setPassword(null);
			request.setAttribute("customer", customer);
			String pageName = "customer_form.jsp";
			CommonUtility.forwardToPage(pageName, request, response);
		}
		
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId  = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		Customer customerById = customerDAO.get(customerId);
		Customer customerByEmail = customerDAO.findByEmail(email);
		if(customerByEmail != null && customerById.getCustomerId() != customerByEmail.getCustomerId()) {
			String message="Couldnt Update as customer with email "+email+" already exists";
			CommonUtility.showMessageBackend(message, request, response);
		}
		else {
			String message="Customer updated Successfully";
			String password = request.getParameter("password");
			readCustomerFields(customerById);
			if(password != null && !password.isEmpty()) {
				String hashedPassword = HashGeneratorUtils.generateMD5(password);
				customerById.setPassword(hashedPassword);
			}
			customerDAO.update(customerById);
			listAll(message);
		}
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		String message="Customer has been successfully deleted";
		if(customer == null) {
			message="Couldnt find customer with id "+customerId+" or it might have been deleted by another admin";
			CommonUtility.showMessageBackend(message, request, response);
		}
		else {
			List<Review> listReviewByCustomerId = reviewDAO.listReviewByCustomerId(customerId);
			if(listReviewByCustomerId !=null && listReviewByCustomerId.size() >0) {
				message="Could not delete customer with ID "+customerId+ " because he/she posted reviews for books";
				CommonUtility.showMessageBackend(message, request, response);
			}
			else {
				OrderDAO orderDAO = new OrderDAO();
				long findOrderForCustomer = orderDAO.findOrderForCustomer(customerId);
				if(findOrderForCustomer > 0) {
					message="Could not delete customer with ID "+customerId+ " because he/she placed orders";
					CommonUtility.showMessageBackend(message, request, response);
				}
				else {
					customerDAO.delete(customerId);
					listAll(message);
				}
			}
		}
	}

	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer customerFindByEmail = customerDAO.findByEmail(email);
		if(customerFindByEmail!=null) {
			String message="Couldnt register Customer as customer with email "+email+" already exists";
			CommonUtility.showMessageFrontend(message, request, response);
		}
		else {
			String message="You have been registered successfully!";
			//fullName password phone address city zipCode country
			Customer customer = new Customer();
			readCustomerFields(customer);
			String password = request.getParameter("password");
			if(password!=null) {
				String hashedPassword = HashGeneratorUtils.generateMD5(password);
				customer.setPassword(hashedPassword);	
			}
			customerDAO.create(customer);
			CommonUtility.showMessageFrontend(message, request, response);
		}
		
	}

	public void showLogin() throws ServletException, IOException {
		String pageName="frontend/login.jsp";
		CommonUtility.forwardToPage(pageName, request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean checkLogin = customerDAO.checkLogin(email,password);
		if(checkLogin) {
			Customer customer = customerDAO.findByEmail(email);
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);
			Object objectRedirectURL = session.getAttribute("redirectURL");
			if(objectRedirectURL != null) {
				String redirectURL = (String) objectRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			}
			else {
				showCustomerProfile();
			}
		}
		else {
			String message="Login failed! Please check your email and password";
			request.setAttribute("message", message);
			showLogin();
		}
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String pageName="frontend/customer_profile.jsp";
		CommonUtility.forwardToPage(pageName, request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String pageName="frontend/edit_profile.jsp";
		CommonUtility.forwardToPage(pageName, request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer =(Customer) request.getSession().getAttribute("loggedCustomer");
		readCustomerFields(customer);
		customerDAO.update(customer);
		showCustomerProfile();
		
	}
}
