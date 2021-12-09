package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ReviewDAO reviewDAO;
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		reviewDAO = new ReviewDAO();
	}
	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}
	public void listAllReview(String message) throws ServletException, IOException {
		 List<Review> listReviews = reviewDAO.listAll();
		 request.setAttribute("listReviews", listReviews);
		 request.setAttribute("message", message);
		 String pageName="review_list.jsp";
		 CommonUtility.forwardToPage(pageName, request, response);
		
	}
	public void editReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		if (review != null) {
			request.setAttribute("review", review);
			String pageName = "review_form.jsp";
			CommonUtility.forwardToPage(pageName, request, response);
		}
		else {
			String message="Could not find review with ID "+reviewId;
			CommonUtility.showMessageBackend(message, request, response);
		}
		
		
	}
	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		Review review = reviewDAO.get(reviewId);
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		review.setHeadline(headline);
		review.setComment(comment);
		reviewDAO.update(review);
		String message="Review has been updated successfully";
		listAllReview(message);
		
		
	}
	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		if(review != null) {
			reviewDAO.delete(reviewId);
			String message="Review has been successfully deleted";
			listAllReview(message);
		}
		else {
			String message="Couldn't find review with ID "+ reviewId+", or it might have been deleted by another admin";
			CommonUtility.showMessageBackend(message, request, response);
		}
		
	}
	public void showReviewForm() throws ServletException, IOException {
		String pageName="frontend/review_form.jsp";
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedCustomer");
		session.setAttribute("book", book);
		Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		if(existReview != null) {
			request.setAttribute("review", existReview);
			pageName="frontend/review_info.jsp";
		}
		
		CommonUtility.forwardToPage(pageName, request, response);
		
	}
	public void submitReview() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review newReview = new Review();
		newReview.setComment(comment);
		newReview.setRating(rating);
		newReview.setHeadline(headline);
		
		Book book = new Book();
		book.setBookId(bookId);
		newReview.setBook(book);
		
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);
		reviewDAO.create(newReview);
		
		String pageName="frontend/review_done.jsp";
		CommonUtility.forwardToPage(pageName, request, response);
		
	}
	
	
	

}
