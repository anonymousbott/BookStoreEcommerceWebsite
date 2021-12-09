package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Review;

public class BookServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private ReviewDAO reviewDAO;

	public BookServices() {

	}

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO();
		categoryDAO = new CategoryDAO();
		reviewDAO = new ReviewDAO();
	}

	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listAllBook = bookDAO.listAll();
		String bookListPage = "book_list.jsp";
		request.setAttribute("listAllBook", listAllBook);
		request.setAttribute("message", message);
		CommonUtility.forwardToPage(bookListPage, request, response);

	}

	public void showBookNewForm() throws ServletException, IOException {
		String newPage = "book_form.jsp";
		List<Category> catListAll = categoryDAO.listAll();
		request.setAttribute("catListAll", catListAll);
		System.out.println("checkpoint edit");
		CommonUtility.forwardToPage(newPage, request, response);
	}

	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title");
		Book bookExist = bookDAO.findByTitle(title);
		if (bookExist != null) {
			String message = "A book with title '" + title + "' already exists";
			listBooks(message);
			return;
		}
		Book newBook = new Book();
		readBookFields(newBook);
		Book createdBook = bookDAO.create(newBook);
		if (createdBook != null && createdBook.getBookId() > 0) {
			String message = "New Book has been successfully created";
			listBooks(message);
		}

	}

	public void readBookFields(Book book) throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("Error parsing publish date format in MM/dd/yyyy");
		}
		Category category = categoryDAO.get(categoryId);
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setCategory(category);
		book.setPublishDate(publishDate);
		Part part = request.getPart("bookImage");
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];

			// opening input stream from the part
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			book.setImage(imageBytes);
		}
	}

	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		if(book==null) {
			String message="Could not find book with id "+bookId;
			CommonUtility.showMessageBackend(message, request, response);
		}
		else {
		Category category = book.getCategory();
		Integer categoryId = category.getCategoryId();
		List<Category> catListAll = categoryDAO.listAll();
		System.out.println("categoryId-->" + categoryId);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("book", book);
		request.setAttribute("catListAll", catListAll);
		showBookNewForm();
		}

	}

	public void updateBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		Book bookById = bookDAO.get(bookId);
		Book bookByTitle = bookDAO.findByTitle(title);

		if (bookByTitle != null && bookByTitle.getBookId() != bookId) {
			String message = "Could not update as book with title " + title + " already exists";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			readBookFields(bookById);
			bookDAO.update(bookById);
			String message = "Book updated successfully";
			listBooks(message);

		}
		/*
		 * bookDAO.update(bookById); String message = "Book updated successfully";
		 * listBooks(message);
		 */
	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book bookById = bookDAO.get(bookId);
		String message="";
		if(bookById!=null) {
			List<Review> listReviewByBookId = reviewDAO.listReviewByBookId(bookId);
			if(listReviewByBookId != null && listReviewByBookId.size() >0) {
				message="Could not delete the book with ID " +bookId+ " because it has reviews";
				CommonUtility.showMessageBackend(message, request, response);
			}
			else {
				OrderDAO orderDAO = new  OrderDAO();
				long countBookWithOrderId = orderDAO.findBookWithOrderId(bookId);
				if(countBookWithOrderId >0) {
					message="Could not delete the book with ID " +bookId+ " because it has orders associated";
					CommonUtility.showMessageBackend(message, request, response);
				}
				else {
					bookDAO.delete(bookId);
					message="Book deleted successfully";	
					listBooks(message);
				}
			}
			}
		else {
			message="Couldnt find book with id "+bookId+" or it might have been deleted by other admin";
			CommonUtility.showMessageBackend(message, request, response);
		}
		
	}

	public void listBooksByCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		List<Book> listByCategory = bookDAO.listByCategory(categoryId);
		Category category = categoryDAO.get(categoryId);
		if (listByCategory.size()==0 && category==null) {
			System.out.println("hello");
			String message = "Sorry, the category ID "+ categoryId+ " is not available";
			CommonUtility.showMessageFrontend(message, request, response);
		} else {
			request.setAttribute("category", category);
			request.setAttribute("listByCategory", listByCategory);
			String pageName="frontend/books_list_by_category.jsp";
			CommonUtility.forwardToPage(pageName, request, response);
		}
	}

	public void viewBookDetails() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(id);
		if (book == null) {
			String message="Sorry book with Id "+id+" has been deleted";
			CommonUtility.showMessageFrontend(message, request, response);

		} else {
			request.setAttribute("book", book);
			String detailPage = "frontend/book_detail.jsp";
			CommonUtility.forwardToPage(detailPage, request, response);
		}

	}

	public void search() throws ServletException, IOException {
		String keyword=request.getParameter("keyword");
		List<Book> searchBookByKeyword=null;
		if(keyword.equals("")) {
			searchBookByKeyword = bookDAO.listAll();
		}
		else {
			searchBookByKeyword = bookDAO.search(keyword);
		}
		String display="frontend/search_result.jsp";
		request.setAttribute("searchBookByKeyword", searchBookByKeyword);
		request.setAttribute("keyword",keyword);
		CommonUtility.forwardToPage(display,request,response);
		
	}

}
