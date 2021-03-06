package com.bookstore.controller.frontend.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.service.CommonUtility;


@WebServlet("/admin/add_book_form")
public class ShowAddBookFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowAddBookFormServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO = new BookDAO();
		List<Book> listAllBooks = bookDAO.listAll();
		request.setAttribute("listBook", listAllBooks);
		String pageName = "add_book_form.jsp";
		CommonUtility.forwardToPage(pageName , request, response);
	}

}
