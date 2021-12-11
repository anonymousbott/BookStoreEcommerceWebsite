package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder bookOrder) {
		bookOrder.setOrderDate(new Date());
		bookOrder.setStatus("Processing");
		return super.create(bookOrder);
	}

	@Override
	public BookOrder update(BookOrder bookOrder) {

		return super.update(bookOrder);
	}

	@Override
	public void delete(Class<BookOrder> type, Object id) {

		super.delete(type, id);
	}

	@Override
	public BookOrder get(Object orderId) {

		return super.find(BookOrder.class, orderId);

	}
	
	public BookOrder get(Object orderId,Object customerId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		List<BookOrder> bookOrderResult = super.findWithNamedQuery("BookOrder.findByOrderIdAndCustomerId", parameters);
		if(!bookOrderResult.isEmpty()) {
			return bookOrderResult.get(0);
		}
		return null;
	}

	@Override
	public void delete(Object orderId) {
		super.delete(BookOrder.class, orderId);
	}

	@Override
	public List<BookOrder> listAll() {

		return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count() {

		return super.countWithNamedQuery("BookOrder.countAll");
	}
	
	public long findBookWithOrderId(Integer bookId) {
		return  super.countWithNamedQuery("OrderDetail.findBookWithOrderId", "bookId", bookId);
		
	}
	
	public long findOrderForCustomer(Integer customerId) {
		return super.countWithNamedQuery("BookOrder.findOrderForCustomer", "customerId", customerId);
	}
	
	public List<BookOrder> listByCustomer(Integer customerId) {
		return super.findWithNamedQuery("BookOrder.listOrderByCustomer", "customerId", customerId);
	}
	
}
