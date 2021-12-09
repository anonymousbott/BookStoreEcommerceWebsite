package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;
import com.bookstore.service.CommonUtility;


@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddBookToOrderServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		OrderDetail orderDetail = null;
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		float subTotal = book.getPrice() * quantity;
		Iterator<OrderDetail> iterator = orderDetails.iterator();
		while(iterator.hasNext()) {
			OrderDetail next = iterator.next();
			if(next.getBook().getBookId() == bookId) {
				orderDetail = next;
				break;
			}
		}
		if(orderDetail == null) {
			orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubTotal(subTotal);
		}
		else {
			orderDetail.setQuantity(orderDetail.getQuantity()+quantity);
			orderDetail.setSubTotal(orderDetail.getSubTotal()+subTotal);
		}
		order.setTotal(order.getTotal()+subTotal);
		orderDetails.add(orderDetail);
		request.getSession().setAttribute("bookAddedToOrder", true);
		request.setAttribute("book", book);
		String pageName = "add_book_result.jsp";
		CommonUtility.forwardToPage(pageName , request, response);
	}

}
