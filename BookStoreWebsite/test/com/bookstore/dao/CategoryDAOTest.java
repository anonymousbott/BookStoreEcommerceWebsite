package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest{

	
	private static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDAO=new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}
	

	@Test
	public void testCreateCategory() {
		Category category=new Category("Framework");
		Category cat = categoryDAO.create(category);
		assertTrue(cat!=null && cat.getCategoryId()>0);
	}

	@Test
	public void testUpdateCategory() {
		Category category=new Category("Adv Java");
		category.setCategoryId(2);
		Category cat = categoryDAO.update(category);
		assertEquals(category.getName(), cat.getName());
	}

	@Test
	public void testGet() {
		int id=1;
		Category category = categoryDAO.get(id);
		assertNotNull(category);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId=6;
		categoryDAO.delete(catId);
		Category category = categoryDAO.get(catId);
		assertNull(category);
	}

	@Test
	public void testListAll() {
		List<Category> catListAll = categoryDAO.listAll();
		assertTrue(catListAll.size()>0);
	}

	@Test
	public void testCount() {
		long catCount = categoryDAO.count();
		assertEquals(5, catCount);
	}
	
	@Test
	public void testFindByName() {
		String catName="Cooking";
		Category catFindByName = categoryDAO.findByName(catName);
		//System.out.println(catFindByName.getName());
		assertNotNull(catFindByName);
	}
	@Test
	public void testFindByNameNotFound() {
		String catName="Adv Java";
		Category catFindByName = categoryDAO.findByName(catName);
		//System.out.println(catFindByName.getName());
		assertNull(catFindByName);
	}

}
