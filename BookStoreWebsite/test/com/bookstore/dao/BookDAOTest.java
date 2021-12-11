package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {
	
	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDAO=new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDAO.close();
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook=new Book();
		existBook.setBookId(1);
		Category category=new Category("J2SE");
		category.setCategoryId(1);
		existBook.setCategory(category);
		existBook.setTitle("Effective Java (3rd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop");
		existBook.setPrice(40f);
		existBook.setIsbn("0321356683");
		//date format to create a date from a string
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/28/2008");
		existBook.setPublishDate(publishDate);
		String imagePath="F:\\Ecommerce-Online_Book_Store\\dummy-data-books\\books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		Book updatedBook = bookDAO.update(existBook);
		assertEquals(updatedBook.getTitle(),"Effective Java (3rd Edition)");
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook=new Book();
		Category category=new Category("Adv Java");
		category.setCategoryId(2);
		newBook.setCategory(category);
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");
		//date format to create a date from a string
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);
		String imagePath="F:\\Ecommerce-Online_Book_Store\\dummy-data-books\\books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		Book createdBook = bookDAO.create(newBook);
		assertTrue(createdBook.getBookId() >0);
	}

	@Test
	public void testCreate2ndBook() throws ParseException, IOException {
		Book newBook=new Book();
		Category category=new Category("J2SE");
		category.setCategoryId(1);
		newBook.setCategory(category);
		newBook.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming");
		newBook.setAuthor("Mario Fusco");
		newBook.setDescription("The book covers lambdas, streams, and functional-style programming. With Java 8's functional features you can now write more concise code in less time, and also automatically benefit from multicore architectures. It's time to dig in!");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");
		//date format to create a date from a string
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);
		String imagePath="F:\\Ecommerce-Online_Book_Store\\dummy-data-books\\books\\Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		Book createdBook = bookDAO.create(newBook);
		assertTrue(createdBook.getBookId() >0);
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId=100;
		bookDAO.delete(bookId);
	}
	
	@Test
	public void testDeleteBookSuccess() {
		Integer bookId=1;
		bookDAO.delete(bookId);
		assertTrue(true);
	}
	
	@Test
	public void testGetBookFail() {
		Integer bookId=99;
		Book book = bookDAO.get(bookId);
		assertNull(book);
	}
	@Test
	public void testGetBookSuccess() {
		Integer bookId=2;
		Book book = bookDAO.get(bookId);
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDAO.listAll();
		for(Book aBook:listBooks) {
			System.out.println(aBook.getTitle());
		}
		assertTrue(!listBooks.isEmpty());
	}
	
	@Test
	public void testFindByTitleExist() {
		String title="Effective Java (2nd Edition)";
		Book bookFindByTitle = bookDAO.findByTitle(title);
		assertEquals(bookFindByTitle.getTitle(),title);
	}
	@Test
	public void testFindByTitleNotExist() {
		String title="Thinking in Java";
		Book bookFindByTitle = bookDAO.findByTitle(title);
		assertNull(bookFindByTitle);
	}
	
	@Test
	public void testListByCategory() {
		int catId=2;
		List<Book> bookListByCategory = bookDAO.listByCategory(catId);
		assertTrue(bookListByCategory.size()>0);
	}
	
	@Test
	public void testCount() {
		long totalBook=bookDAO.count();
		assertEquals(2, totalBook);
	}
	
	@Test
	public void listNewBook() {
		List<Book> listNewBook = bookDAO.listNewBook();
		assertEquals(4,listNewBook.size());
	}
	
	@Test
	public void testSearch() {
		String keyword="Maurice Naftalin";
		List<Book> search = bookDAO.search(keyword);
		for(Book abook:search) {
			System.out.println(abook.getTitle());
		}
		assertTrue(search.size()>0);
	}
	
	@Test
	public void testListBestSellingBooks() {
		List<Book> listBestSellingBooks = bookDAO.listBestSellingBooks();
		for(Book book :listBestSellingBooks) {
			System.out.println(book.getBookId()+" "+book.getTitle());
		}
		Book book = listBestSellingBooks.get(0);
		int bookId = book.getBookId();
		assertEquals(11,bookId);
	}
	
	@Test
	public void testListMostFavouredBooks() {
		List<Book> listMostFavouredBooks = bookDAO.listMostFavouredBooks();
		for(Book book : listMostFavouredBooks) {
			System.out.println(book.getBookId() +" " +book.getTitle());
		}
		int bookId = listMostFavouredBooks.get(0).getBookId();
		assertEquals(11,bookId);
	}
	
}
