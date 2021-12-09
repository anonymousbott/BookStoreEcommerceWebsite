package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {
	
	private static ReviewDAO reviewDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDAO = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDAO.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book();
		book.setBookId(4);
		Customer customer = new Customer();
		customer.setCustomerId(4);
		review.setBook(book);
		review.setCustomer(customer);
		review.setRating(3);
		review.setHeadline("Become Spring Guru!");
		review.setComment("Please do give it a read!");
		Review savedReview = reviewDAO.create(review);
		assertTrue(savedReview.getReviewId() >0);
		
	}

	@Test
	public void testUpdateReview() {
		Integer reviewId=1;
		Review review = reviewDAO.get(reviewId);
		review.setHeadline("Excellent Book");
		Review updateReview = reviewDAO.update(review);
		System.out.println(updateReview.getHeadline()+"-->");
		assertEquals("Excellent Book",updateReview.getHeadline());
	}

	@Test
	public void testGet() {
		Integer reviewId=1;
		Review review = reviewDAO.get(reviewId);
		assertNotNull(review);
	}

	@Test
	public void testDeleteObject() {
		Integer reviewId=1;
		reviewDAO.delete(reviewId);
		Review review = reviewDAO.get(reviewId);
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listAllReview = reviewDAO.listAll();
		for(Review review :listAllReview) {
			System.out.println(review.getReviewId() + " - "+review.getReviewTime()+" - "+ review.getHeadline() +" - "+ review.getCustomer().getEmail()
			+" - "+ review.getBook().getTitle());
		}
		assertTrue(listAllReview.size() >0);
	}

	@Test
	public void testCount() {
		long countReview = reviewDAO.count();
		assertTrue(countReview >0);
	}
	@Test
	public void testListReviewByBookId() {
		Integer bookId=9;
		List<Review> listReviewByBookId = reviewDAO.listReviewByBookId(bookId);
		for(Review review :listReviewByBookId) {
			System.out.println(review.getHeadline());
		}
		assertTrue(listReviewByBookId.size() ==1);
	}
	
	@Test
	public void testListReviewByCustomerId() {
		Integer customerId=3;
		List<Review> listReviewByCustomerId = reviewDAO.listReviewByCustomerId(customerId);
		for(Review review :listReviewByCustomerId) {
			System.out.println(review.getHeadline());
		}
		assertTrue(listReviewByCustomerId.size() ==1);
	}
	@Test
	public void testFindByCustomerAndBook() {
		Integer bookId=4;
		Integer customerId=8;
		Review findReviewByCustomerAndBook = reviewDAO.findByCustomerAndBook(customerId, bookId);
		System.out.println(findReviewByCustomerAndBook.getComment());
		assertNotNull(findReviewByCustomerAndBook);
	}
	@Test
	public void testFindByCustomerAndBookNotFond() {
		Integer bookId=40;
		Integer customerId=8;
		Review findReviewByCustomerAndBook = reviewDAO.findByCustomerAndBook(customerId, bookId);
		//System.out.println(findReviewByCustomerAndBook.getComment());
		assertNull(findReviewByCustomerAndBook);
	}

}
