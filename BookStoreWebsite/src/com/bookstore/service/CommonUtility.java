package com.bookstore.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtility {

	public static void forwardToPage(String pageName, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(pageName);
		requestDispatcher.forward(request, response);
	}

	public static void showMessageFrontend(String message, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("message", message);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontend/message.jsp");
		requestDispatcher.forward(request, response);
	}

	public static void showMessageBackend(String message, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.setAttribute("message", message);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
		requestDispatcher.forward(request, response);
	}

}
