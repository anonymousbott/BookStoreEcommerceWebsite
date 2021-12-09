package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;

public class ShoppingCartTest {
	
	private static ShoppingCart cart;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		cart = new ShoppingCart();
		Book book = new Book(1);
		book.setPrice(10);
		cart.addItem(book);
		cart.addItem(book);
		System.out.println(cart.getItems().get(book));
	}

	@Test
	public void testAddItem() {
		Map<Book, Integer> items = cart.getItems();
		System.out.println(items.size());
		int quantity = items.get(new Book(1));
		System.out.println(quantity);
		assertEquals(2,quantity);
		
	}
	
	@Test
	public void testRemoveItem() {
		cart.removeItem(new Book(1));
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
		cart.addItem(new Book(2));
		cart.removeItem(new Book(1));
		assertTrue(!cart.getItems().isEmpty());
	}
	
	@Test
	public void testGetTotalQuantity() {
		Book book = new Book(3);
		cart.addItem(book);
		cart.addItem(book);
		cart.addItem(book);
		assertEquals(5,cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount1() {
		assertEquals(20.0f,cart.getTotalAmount(),0.0f);
	}
	
	@Test
	public void testClear() {
		cart.clear();
		assertEquals(0, cart.getTotalQuantity());
	}
	
	@Test
	public void testUpdateCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		Book book1 = new Book(1);
		Book book2 = new Book(2);
		shoppingCart.addItem(book1);
		shoppingCart.addItem(book2);
		int bookIds[] = {1,2};
		int quantities[] = {3,4};
		shoppingCart.updateCart(bookIds, quantities);
		assertEquals(7,shoppingCart.getTotalQuantity());
	}

}
