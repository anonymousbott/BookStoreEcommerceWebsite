package com.bookstore.controller.frontend.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;
import com.bookstore.service.CommonUtility;


@WebFilter("/*")
public class CustomerLoginFilter implements Filter {

	private final CategoryDAO categoryDAO;
    public CustomerLoginFilter() {
		this.categoryDAO = new CategoryDAO();
        
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer")!=null ;
		String requestURL = httpRequest.getRequestURL().toString();
		List<Category> catListAll = categoryDAO.listAll();
		request.setAttribute("catListAll", catListAll);
		if(path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return ;
		}
		System.out.println("path--> "+path);
		System.out.println("loggedIn--> "+loggedIn);
		if(!loggedIn && path.startsWith("/login/")) {
			System.out.println("1 we are not logged in and theres a request to login");
			chain.doFilter(request, response);
		}
		
		  if(!loggedIn && (path.startsWith("/view_profile") || path.startsWith("/view_orders") || path.startsWith("/logout") || path.startsWith("/edit_profile")
				|| path.startsWith("/update_profile") || path.startsWith("/write_review") || path.startsWith("/checkout") || path.startsWith("/place_order")  ||
				path.startsWith("/view_orders") || path.startsWith("/show_order_detail"))) { 
			  System.out.println("2 we are logged out and trying to visit profile or orders page or trying to log out or write review"
			  		+ " or trying to checkout");
			  String redirectURL = requestURL;
			  String queryString = httpRequest.getQueryString();
			  if(queryString !=null) {
				  redirectURL = redirectURL.concat("?").concat(queryString);
			  }
			  session.setAttribute("redirectURL",redirectURL);
			  String pageName="frontend/login.jsp"; 
			  RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher(pageName);
			  requestDispatcher.forward(request, response);
		  
		  }
		 
		  else if(!loggedIn) {
			  System.out.println("4 normal features when you are logged out");
			chain.doFilter(request, response);
		}
		else if(loggedIn && (path.startsWith("/login") || path.startsWith("/register"))) {
			System.out.println("3 we are logged in but again clicking login or register ");
			RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("/");
			requestDispatcher.forward(request, response);
		}
		else if(loggedIn) {
			System.out.println("5 normal features when you are logged in");
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
