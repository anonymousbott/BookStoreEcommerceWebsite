package com.bookstore.controller.admin;

import java.io.IOException;
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

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

	public AdminLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		// false --> dont create a session if it doesnt exist
		boolean loggedIn = session != null && session.getAttribute("userEmail") != null;
		String requestURI = httpRequest.getContextPath() + "/admin/login";
		boolean loginRequest = httpRequest.getRequestURI().equals(requestURI);
		boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp");

		if (loggedIn && (loginRequest || loginPage)) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/");
			requestDispatcher.forward(request, response);
		} else {
			if (loggedIn || loginRequest) {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			} else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
