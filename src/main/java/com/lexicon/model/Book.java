package com.lexicon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private long id;
	
	private String ISBN;
	private String genre;
	private String title;
	private String author;
	private String shelf;
	private String shelfRow;
	private String shelfColumn;
	private int copies;

	public Book() {

	}

	public Book(String title, String ISBN, String genre, String author, String shelf, String shelfRow,
			String shelfColumn, int copies) {
		
		this.title = title;
		this.ISBN = ISBN;
		this.genre = genre;
		this.author = author;
		this.shelf = shelf;
		this.shelfRow = shelfRow;
		this.shelfColumn = shelfColumn;
		this.copies = copies;

	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getShelfRow() {
		return shelfRow;
	}

	public void setShelfRow(String shelfRow) {
		this.shelfRow = shelfRow;
	}

	public String getShelfColumn() {
		return shelfColumn;
	}

	public void setShelfColumn(String shelfColumn) {
		this.shelfColumn = shelfColumn;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

}
