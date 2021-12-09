package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
		super();
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object id) {
		return super.find(Book.class, id);
	}

	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);

	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");

	}

	@Override
	public long count() {

		return super.countWithNamedQuery("Book.countAll");
	}
	
	public Book findByTitle(String title) {
		 List<Book> bookByTitle = super.findWithNamedQuery("Book.findByTitle", "title", title);
		 if(!bookByTitle.isEmpty()) {
			 return bookByTitle.get(0);
		 }
		return null;
		
	}
	public List<Book> listByCategory(int catId){
		List<Book> bookByCategory = super.findWithNamedQuery("Book.findByCategory", "catId", catId);
		return bookByCategory;
	}
	
	public List<Book> listNewBook(){
		return super.findWithNamedQuery("Book.listNewBook", 0, 4);
		
	}
	
	public List<Book> search(String keyword) {

		return super.findWithNamedQuery("Book.search", "keyword", keyword);
		
	}

}
