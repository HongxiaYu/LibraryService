package com.lexicon.libraryservice.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class Loan {

	@Id
	@GeneratedValue
	private Long id;	
	
	public Loan() {
		super();
		borrowDate = LocalDate.now();
		returnDate = borrowDate.plusDays(30);
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOK_ID")
	private Book loanBook;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	private LibraryMember member;
	
	private LocalDate borrowDate;
	private LocalDate returnDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getLoanBook() {
		return loanBook;
	}

	public void setLoanBook(Book loanBook) {
		this.loanBook = loanBook;
	}

	public LibraryMember getMember() {
		return member;
	}

	public void setMember(LibraryMember member) {
		this.member = member;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}		
}
