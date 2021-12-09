package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Category create(Category category) {
		// TODO Auto-generated method stub
		return super.create(category);
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return super.update(category);
	}

	@Override
	public Category get(Object id) {

		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);

	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Category.countAll");
	}

	public Category findByName(String name) {
		List<Category> listCategory = super.findWithNamedQuery("Category.findByName", "name", name);
		if (listCategory != null && listCategory.size() >= 1) {
			return listCategory.get(0);
		}

		return null;

	}

}
