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


@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewCartServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Object cartObject = request.getSession().getAttribute("cart"); if(cartObject
		 * == null) { ShoppingCart shoppingCart = new ShoppingCart();
		 * request.getSession().setAttribute("cart", shoppingCart); BookDAO bookDAO =
		 * new BookDAO(); Book book1 = bookDAO.get(8); Book book2 = bookDAO.get(9); Book
		 * book3 = bookDAO.get(3); shoppingCart.addItem(book1);
		 * shoppingCart.addItem(book1); shoppingCart.addItem(book2);
		 * shoppingCart.addItem(book3); }
		 */
		String pageName="frontend/shopping_cart.jsp";
		CommonUtility.forwardToPage(pageName, request, response);
	}

}
