package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;
import com.bookstore.hash.HashGenerationException;
import com.bookstore.hash.HashGeneratorUtils;

public class UserServices {

	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		userDAO = new UserDAO();
		this.request = request;
		this.response = response;

	}

	public void listUser() throws ServletException, IOException {
		listUser(null);
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		request.setAttribute("listUsers", listUsers);
		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "/admin/user_list.jsp";
		CommonUtility.forwardToPage(listPage, request, response);
	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		String hashedPassword = HashGeneratorUtils.generateMD5(password);
		Users existUser = userDAO.findByEmail(email);
		if (existUser != null) {
			String message = "Could not create user.A user with email " + email + " already exists";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			Users user = new Users(email, fullName, hashedPassword);
			String message = "New User has been created successfully";
			userDAO.create(user);
			listUser(message);
		}

	}

	public void editUser() throws ServletException, IOException {
		Integer userId = (Integer.parseInt(request.getParameter("id")));
		Users user = userDAO.get(userId);

		if (user != null) {
			// set password as null to make the password is left blank by default
			// if left blank, the user's password won't be updated
			// this is to work with the encrypted password feature
			user.setPassword(null);
			request.setAttribute("user", user);
			String editPage = "user_form.jsp";
			CommonUtility.forwardToPage(editPage, request, response);
			System.out.println(user.getFullName());
		} else {
			String message = "Could not find user with ID " + userId;
			CommonUtility.showMessageBackend(message, request, response);
		}
	}

	public void updateUser() throws ServletException, IOException {
		Integer userId = (Integer.parseInt(request.getParameter("userId")));
		String email = (String) request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		Users userByEmail = userDAO.findByEmail(email);
		System.out.println("-->" + userId);

		Users userById = userDAO.get(userId);
		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update as user with email " + email + " already exists";
			CommonUtility.showMessageBackend(message, request, response);

		} else {
			userById.setEmail(email);
			userById.setFullName(fullName);
			if (password != null & !password.isEmpty()) {
				String hashedPassword = HashGeneratorUtils.generateMD5(password);
				userById.setPassword(hashedPassword);
			}

			userDAO.update(userById);

			String message = "User Updated successfully";
			listUser(message);
		}

	}

	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userId);
		if (user == null) {
			String message = "Could not find user with ID " + userId
					+ ", or it might have been deleted by another admin.";
			CommonUtility.showMessageBackend(message, request, response);

		} else if (userId == 1) {
			String message = "The default admin user account cannot be deleted.";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			userDAO.delete(userId);
			String message = "User has been succesfully deleted";
			listUser(message);
		}

	}

	public void login() throws ServletException, IOException, HashGenerationException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String hashedPassword = HashGeneratorUtils.generateMD5(password);
		boolean loginResult = userDAO.checkLogin(email, hashedPassword);
		if (loginResult) {
			request.getSession().setAttribute("userEmail", email);
			request.getRequestDispatcher("/admin/").forward(request, response);
		} else {
			String message = "Login failed!";
			request.setAttribute("message", message);
			String pageName="login.jsp";
			CommonUtility.forwardToPage(pageName, request, response);
		}

	}

}
