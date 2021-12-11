package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;


/*
 *this servlet is responsible to handle request
 *to website context root
 * */
@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO categoryDAO=new CategoryDAO();
		BookDAO bookDAO = new BookDAO();
		List<Book> listNewBook = bookDAO.listNewBook();
		List<Book> listBestSellingBooks = bookDAO.listBestSellingBooks();
		List<Book> listMostFavouredBooks = bookDAO.listMostFavouredBooks();
		request.setAttribute("listNewBook", listNewBook);
		request.setAttribute("listBestSellingBooks", listBestSellingBooks);
		request.setAttribute("listMostFavouredBooks", listMostFavouredBooks);
		String page="frontend/index.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	

}
