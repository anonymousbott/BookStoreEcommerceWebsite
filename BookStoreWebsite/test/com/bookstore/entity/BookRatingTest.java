package com.bookstore.entity;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testAverageRaing1() {
		Book book =  new Book();
		Set<Review>reviews =  new HashSet<>();
		Review review =  new Review();
		review.setRating(5);
		reviews.add(review);
		book.setReviews(reviews);
		float averageRating = book.getAverageRating();
		assertEquals(5.0,averageRating,0.0);
	}
	
	@Test
	public void testAverageRaing2() {
		Book book =  new Book();
		Set<Review>reviews =  new HashSet<>();
		Review review =  new Review();
		review.setRating(5);
		reviews.add(review);
		Review review1 =  new Review();
		review1.setRating(4);
		reviews.add(review1);
		Review review2 =  new Review();
		review2.setRating(3);
		reviews.add(review2);
		book.setReviews(reviews);
		float averageRating = book.getAverageRating();
		assertEquals(4.0,averageRating,0.0);
	}
	
	@Test
	public void testRatingString1() {
		float averageRating=0.0f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		String expected="off,off,off,off,off";
		assertEquals(ratingString,expected);
		
	}
	
	@Test
	public void testRatingString2() {
		float averageRating= 5.0f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		String expected="on,on,on,on,on";
		assertEquals(ratingString,expected);
		
	}
	@Test
	public void testRatingString3() {
		float averageRating= 3.0f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		String expected="on,on,on,off,off";
		assertEquals(ratingString,expected);
		
	}
	
	@Test
	public void testRatingString4() {
		float averageRating= 3.0f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		String expected="on,on,on,off,off";
		assertEquals(ratingString,expected);
		
	}
	@Test
	public void testRatingString5() {
		float averageRating= 3.6f;
		Book book = new Book();
		String ratingString = book.getRatingString(averageRating);
		String expected="on,on,on,half,off";
		assertEquals(ratingString,expected);
		
	}

}
