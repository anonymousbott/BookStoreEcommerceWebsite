package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.service.CommonUtility;


@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddBookToCartServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		Object cartObject = request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart = null;
		if(cartObject != null) {
			shoppingCart = (ShoppingCart) cartObject;
		}
		else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		shoppingCart.addItem(book);
		response.sendRedirect(request.getContextPath()+"/view_cart");
	}

}
