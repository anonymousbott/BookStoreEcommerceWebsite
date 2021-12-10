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

import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;
import com.bookstore.service.CommonUtility;

@WebServlet("/admin/remove_book_from_order")
public class RemoveBookFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveBookFromOrderServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		// session.getAttribute(name)
		BookOrder bookOrder = (BookOrder) session.getAttribute("order");
		Set<OrderDetail> orderDetails = bookOrder.getOrderDetails();
		Iterator<OrderDetail> iterator = orderDetails.iterator();
		OrderDetail orderDetail = null;
		while (iterator.hasNext()) {
			OrderDetail nextOrderDetail = iterator.next();
			if (nextOrderDetail.getBook().getBookId() == bookId) {
				orderDetail = nextOrderDetail;
				float subTotal = nextOrderDetail.getSubTotal();
				float total = bookOrder.getTotal();
				total = total - subTotal;
				if (total < 0) {
					total = 0;
				}
				bookOrder.setTotal(total);
				//orderDetails.remove(nextOrderDetail);
				iterator.remove();
				orderDetail.setBookOrder(null);
				//break;
			}
		}
		//bookOrder.setOrderDetails(orderDetails);
		/*
		 * request.getSession().setAttribute("bookAddedToOrder", true); String
		 * redirectUrl = "edit_order?id="; Integer queryParameter =
		 * bookOrder.getOrderId(); redirectUrl = redirectUrl + queryParameter;
		 * response.sendRedirect(redirectUrl);
		 */
		String pageName ="order_form.jsp";
		CommonUtility.forwardToPage(pageName , request, response);

	}

}
