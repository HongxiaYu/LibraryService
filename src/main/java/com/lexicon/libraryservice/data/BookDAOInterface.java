package com.lexicon.libraryservice.data;

import java.util.List;
import com.lexicon.libraryservice.model.Book;
import com.lexicon.libraryservice.model.Loan;

public interface BookDAOInterface {	
	public void persistBook(Book book);
	public  List<Book> getAllBooks();
	public  Book findBookById(long id);
	public  Book findBookByISBN(String isbn);
	List<Book> findBookByTitle(String title);
	List<Book> findBookByAuthor(String author);
	List<Book> findBookByGenre(String genre);
	void deleteBookById(long id);
	void updateBookGenre(long id, String genre);
	void updateBookBorrowedCopies(long id, int iCopies);
	List<Loan> findBookLoansById(long id);
	boolean contains(long id);
	Book getBookById(long id);
}
