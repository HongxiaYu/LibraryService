package com.lexicon.rest;

import java.util.List;
import com.lexicon.model.Book;

public interface BookDAOInterface {	
	void persistBook(Book book);
	List<Book> getAllBooks();
	Book findBookById(long id);
	Book findBookByISBN(String isbn);
	List<Book> findBookByTitle(String title);
	List<Book> findBookByAuthor(String author);
	List<Book> findBookByGenre(String genre);
}
