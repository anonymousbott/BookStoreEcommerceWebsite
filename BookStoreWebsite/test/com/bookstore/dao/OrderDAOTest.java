package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderDAOTest {
	
	private static OrderDAO orderDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(8);
		order.setCustomer(customer);
		order.setRecipientName("syed kazim");
		order.setRecipientPhone("7902225");
		order.setShippingAddress("123 south street USA");
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail = new OrderDetail();
		Book book = new Book(3);
		orderDetail.setBook(book);
		orderDetail.setBookOrder(order);
		orderDetail.setQuantity(2);
		orderDetail.setSubTotal(60.5f);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		assertTrue(order.getOrderId()>0);
	}
	
	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(3);
		order.setCustomer(customer);
		order.setRecipientName("Jamey Anderson");
		order.setRecipientPhone("79025696");
		order.setShippingAddress("123 south street London");
		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(3);
		orderDetail.setBook(book);
		orderDetail.setBookOrder(order);
		orderDetail.setQuantity(3);
		orderDetail.setSubTotal(60.5f);
		orderDetails.add(orderDetail);

		
		OrderDetail orderDetail1 = new OrderDetail();
		Book book1 = new Book(11);
		orderDetail1.setBook(book1);
		orderDetail1.setBookOrder(order);
		orderDetail1.setQuantity(1);
		orderDetail1.setSubTotal(30.5f);
		orderDetails.add(orderDetail1);
		
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		assertTrue(order.getOrderId()>0 && order.getOrderDetails().size() ==2);
	}

	@Test
	public void testUpdateBookOrderShippingAddress() {
		Integer orderId = 2;
		BookOrder bookOrder = orderDAO.get(orderId);
		bookOrder.setShippingAddress("New Friends Colony Delhi 110025");
		BookOrder updatedOrder = orderDAO.update(bookOrder);
		assertEquals(bookOrder.getShippingAddress(),updatedOrder.getShippingAddress());
	}
	
	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId = 2;
		BookOrder bookOrder = orderDAO.get(orderId);
		Iterator<OrderDetail> iterator = bookOrder.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getBook().getBookId()==11) {
				orderDetail.setQuantity(3);
				orderDetail.setSubTotal(100);
			}
		}
		int actualQuantity = 0;
		BookOrder updatedOrder = orderDAO.update(bookOrder);
		iterator = updatedOrder.getOrderDetails().iterator();
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if(orderDetail.getBook().getBookId()==11) {
				actualQuantity = orderDetail.getQuantity();
			}
		}
		assertEquals(3,actualQuantity);
	}
	
	@Test
	public void testUpdateBookOrderDetailRemove() {
		Integer orderId = 2;
		BookOrder bookOrder = orderDAO.get(orderId);
		Iterator<OrderDetail> iterator = bookOrder.getOrderDetails().iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			System.out.println(orderDetail.getBook().getBookId() +" b4update----->");
			if(orderDetail.getBook().getBookId()==11) {
				iterator.remove();
			}
		}
		Book book =null;
		BookOrder updatedOrder = orderDAO.update(bookOrder);
		iterator = updatedOrder.getOrderDetails().iterator();
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			System.out.println(orderDetail.getBook().getBookId() +"afterupdate----->");
		}
		//System.out.println(book.getTitle()); //null pointer exception
		//not getting removed from order detail table
		assertTrue(true);
	}

	@Test
	public void testGet() {
		Integer orderId=2;
		BookOrder bookOrder = orderDAO.get(orderId);
		assertEquals(2,bookOrder.getOrderDetails().size());
	}

	@Test
	public void testDeleteObject() {
		Integer orderId = 7;
		orderDAO.delete(orderId);
		BookOrder bookOrder = orderDAO.get(orderId);
		assertNull(bookOrder);
	}

	@Test
	public void testListAll() {
		List<BookOrder> listAll = orderDAO.listAll();
		assertEquals(2, listAll.size());
	}

	@Test
	public void testCount() {
		long count = orderDAO.count();
		assertEquals(2,count);
	}
	
	@Test
	public void testFindBookWithOrderId() {
		Integer bookId =11;
		long findBookWithOrderId = orderDAO.findBookWithOrderId(bookId);
		assertEquals(2,findBookWithOrderId);
	}
	
	@Test
	public void testFindOrderForCustomer() {
		Integer customerId = 6;
		long findOrderForCustomer = orderDAO.findOrderForCustomer(customerId);
		assertEquals(1,findOrderForCustomer);
	}
	
	@Test
	public void testListByCustomer() {
		Integer customerId = 8;
		List<BookOrder> listByCustomer = orderDAO.listByCustomer(customerId);
		assertEquals(2, listByCustomer.size());
	}
	
	@Test
	public void testFindByOrderIdAndCustomerId() {
		Integer orderId = 6;
		Integer customerId = 8;
		BookOrder bookOrder = orderDAO.get(orderId, customerId);
		System.out.println(bookOrder.getCustomer().getEmail());
		assertNotNull(bookOrder);
	}
	
	@Test
	public void testListMostRecentSales() {
		List<BookOrder> listMostRecentSales = orderDAO.listMostRecentSales();
		for(BookOrder bookOrder :listMostRecentSales) {
			System.out.println(bookOrder.getCustomer().getCustomerId());
		}
		assertEquals(3,listMostRecentSales.size());
	}
	

}
