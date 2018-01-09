package com.lexicon.libraryservice.model;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long id;
	
	private String strISBN;
	private String genre;
	private String title;
	private String author;
	private String shelf;
	private String shelfRow;
	private String shelfColumn;
	private Integer copies;
	private Integer avilableCopies;
	
	@OneToMany(mappedBy="loanBook", fetch=FetchType.EAGER)
	private List<Loan> loans;
	
//	@ManyToMany(mappedBy="loanBook", fetch=FetchType.LAZY)
//	private Loan loan;
	


	public Book() {

	}
	
	public Book(String strISBN, String ISBN, String genre, String title, String author, String shelf, String shelfRow,
			String shelfColumn, int copies) {		

		this.title = title;
		this.strISBN = strISBN;
		this.genre = genre;
		this.author = author;
		this.shelf = shelf;
		this.shelfRow = shelfRow;
		this.shelfColumn = shelfColumn;
		
		this.copies = copies;
		this.avilableCopies = copies;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	
	public String getStrISBN() {
		return strISBN;
	}

	public void setStrISBN(String strISBN) {
		this.strISBN = strISBN;
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

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(int copies) {		
		this.copies = copies;
	}

	public Integer getAvilableCopies() {
		return avilableCopies;
	}

	public void setAvilableCopies(int avilableCopies) {
		this.avilableCopies = avilableCopies;
	}
	
	public void addLoan(Loan loan) {
		if(avilableCopies>0) {
			avilableCopies --;
			loans.add(loan);
		}else {
			System.out.println("No book to loan out!");
		}
	}
	
	public void deleteLoan(Loan loan) {
			avilableCopies ++;
			loans.remove(loan);
	}
	
	public List<Loan> loadLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public String toString() {
		return title + " " + author ;
	}


}
