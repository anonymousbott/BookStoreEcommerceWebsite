package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {

	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}
	
	public void listAll() throws ServletException, IOException {
		listAll(null);
	}
	
	public void listAll(String message) throws ServletException, IOException {
		List<BookOrder> listOrder = orderDAO.listAll();
		if(message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listOrder", listOrder);
		String pageName="order_list.jsp";
		CommonUtility.forwardToPage(pageName, request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder bookOrder = orderDAO.get(orderId);
		if(bookOrder == null) {
			String message="Couldnt find Order with id "+ orderId;
			CommonUtility.showMessageBackend(message, request, response);
		}else {
			request.setAttribute("order", bookOrder);
			String pageName="order_detail.jsp";
			CommonUtility.forwardToPage(pageName, request, response);
		}
	}

	public void showCheckOutForm() throws ServletException, IOException {
		String pageName = "frontend/checkout.jsp";
		CommonUtility.forwardToPage(pageName , request, response);
		
	}

	public void placeOrder() throws ServletException, IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");
		BookOrder order = new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		String shippingAddress = address +", " + city +", "+zipcode+", "+country;
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);
		if(shoppingCart != null) {
			Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
			order.setTotal((float) shoppingCart.getTotalAmount());
			order.setCustomer(customer);
			Map<Book, Integer> cartItems = shoppingCart.getItems();
			Iterator<Book> iterator = cartItems.keySet().iterator();
			while(iterator.hasNext()) {
				OrderDetail orderDetail = new OrderDetail();
				Book book = iterator.next();
				orderDetail.setBook(book);
				orderDetail.setBookOrder(order);
				orderDetail.setQuantity(cartItems.get(book));
				orderDetail.setSubTotal(book.getPrice() * cartItems.get(book));
				orderDetails.add(orderDetail);
			}
			order.setOrderDetails(orderDetails);
			orderDAO.create(order);
			shoppingCart.clear();
			String message="Thank You. Your Order has been received." + " We will deliver your books within few days";
			CommonUtility.showMessageFrontend(message, request, response);
		}
		else {
			
		}
		
	}

	public void listOrderByCustomer() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		Integer customerId = customer.getCustomerId();
		
		List<BookOrder> orderByCustomer = orderDAO.listByCustomer(customerId);
		request.setAttribute("bookOrder", orderByCustomer);
		String pageName = "frontend/order_list.jsp";
		CommonUtility.forwardToPage(pageName , request, response);
		
	}

	public void showOrderDetailForCustomer() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		BookOrder bookOrder = orderDAO.get(orderId,customer.getCustomerId());
		request.setAttribute("order", bookOrder);
		String pageName = "frontend/order_detail.jsp";
		CommonUtility.forwardToPage(pageName , request, response);
		
	}

	public void showEditOrderForm() throws ServletException, IOException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		BookOrder bookOrder = orderDAO.get(orderId);
		if(bookOrder != null) {
			Object attribute = request.getSession().getAttribute("bookAddedToOrder");
			if(attribute == null) {
				request.getSession().setAttribute("order", bookOrder);
			}
			else {
				request.getSession().removeAttribute("bookAddedToOrder");
			}
			String pageName ="order_form.jsp";
			CommonUtility.forwardToPage(pageName , request, response);
		}
		else {
			String message="Couldn't find order with Id " + orderId;
			CommonUtility.showMessageBackend(message, request, response);
		}
	}

	public void updateOrder() throws ServletException, IOException {
		HttpSession session = request.getSession();
		BookOrder bookOrder = (BookOrder) session.getAttribute("order");
		
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String shippingAddress = request.getParameter("shippingAddress");
		String paymentMethod = request.getParameter("paymentMethod");
		String orderStatus = request.getParameter("orderStatus");
		
		bookOrder.setPaymentMethod(paymentMethod);
		bookOrder.setRecipientName(recipientName);
		bookOrder.setRecipientPhone(recipientPhone);
		bookOrder.setShippingAddress(shippingAddress);
		bookOrder.setStatus(orderStatus);
		
		String arrayBookId[] = request.getParameterValues("bookId");
		String arrayQuantity[] = new String[arrayBookId.length];
		String arrayBookPrice[] = request.getParameterValues("bookPrice");
		
		Set<OrderDetail> orderDetails = bookOrder.getOrderDetails();
		orderDetails.clear();
		
		float total = 0.0f;
		
		for(int i=0;i<arrayBookId.length;i++) {
			
			arrayQuantity[i] = request.getParameter("quantity"+(i+1));
			float subTotal = Integer.parseInt(arrayQuantity[i]) * Float.parseFloat(arrayBookPrice[i]);
			Integer bookId = Integer.parseInt(arrayBookId[i]);
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(new Book(bookId));
			orderDetail.setQuantity(Integer.parseInt(arrayQuantity[i]));
			orderDetail.setSubTotal(subTotal);
			orderDetail.setBookOrder(bookOrder);
			
			orderDetails.add(orderDetail);
			total = total+subTotal;
		}
		bookOrder.setTotal(total);
		//test area
		BookOrder updateBook = orderDAO.update(bookOrder);
		Set<OrderDetail> orderDetails2 = updateBook.getOrderDetails();
		Iterator<OrderDetail> iterator = orderDetails2.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().getBook().getTitle()+"----------------->");
		}
		
		
		String message = "The order "+ bookOrder.getOrderId() + " has been updated successfully";
		listAll(message);
	}
	
	
}
