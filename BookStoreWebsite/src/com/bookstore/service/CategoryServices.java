package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class CategoryServices {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private CategoryDAO categoryDAO;
	private BookDAO bookDAO;

	public CategoryServices() {

	}

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
		bookDAO = new BookDAO();
	}

	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException {
		List<Category> catListAll = categoryDAO.listAll();
		request.setAttribute("catListAll", catListAll);
		request.setAttribute("message", message);
		String listPage = "category_list.jsp";
		CommonUtility.forwardToPage(listPage, request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String catName = request.getParameter("name");
		Category categoryFindByName = categoryDAO.findByName(catName);
		if (categoryFindByName != null) {
			String message = "Category with name " + catName + " already exists";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			Category category = new Category(catName);
			categoryDAO.create(category);
			String message = "Category created successfully";
			listCategory(message);
		}
	}

	public void editCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(id);
		if (category == null) {
			String message = "Couldnt find Category with Id " + id;
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			request.setAttribute("category", category);
			String editPage = "category_form.jsp";
			CommonUtility.forwardToPage(editPage, request, response);
		}

	}

	public void updateCategory() throws ServletException, IOException {
		Integer categoryById = Integer.parseInt(request.getParameter("catId"));
		String categoryByName = request.getParameter("name");
		System.out.println(categoryByName+" "+categoryById);

		Category catById = categoryDAO.get(categoryById);
		Category catByName = categoryDAO.findByName(categoryByName);
		if (catByName != null && catByName.getCategoryId() != catById.getCategoryId()) {
			String message = "Category with name " + categoryByName + " already exists";
			CommonUtility.showMessageBackend(message, request, response);
		} else {
			catById.setName(categoryByName);
			categoryDAO.update(catById);
			String message = "Category updated successfully";
			listCategory(message);
		}

	}

	public void deleteCategory() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(id);
		List<Book> booksByCategory = bookDAO.findWithNamedQuery("Book.findByCategory", "catId", id);
		if (category == null) {
			String message = "Could not find catgory with ID " + id
					+ " or it might have been deleted by another admin.";
			CommonUtility.showMessageBackend(message, request, response);
		}
		else if(booksByCategory != null && !booksByCategory.isEmpty()) {
			System.out.println("hello");
			String message = "Could not delete catgory with ID " + id
					+ " as there are books available for this category.";
			CommonUtility.showMessageBackend(message, request, response);
		}
		else {
			categoryDAO.delete(id);
			String message = "Category has been successfully deleted";
			listCategory(message);
		}
	}


}
