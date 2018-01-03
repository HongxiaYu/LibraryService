package com.lexicon.libraryservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Loan {

	@Id
	@GeneratedValue
	private Long id;	
	
	public Loan() {
	//	super();	
	}

	//@OneToOne(cascade = CascadeType.ALL , fetch=FetchType.EAGER)
	//@OneToOne
	//@JoinColumn(name = "BOOK_ID")	
	private Long bookId;   //!!!  Not Null
	
	//@JoinColumn(name = "MEMBER_ID")
	private Long memberId;   //!!! Not Null
	
//	private LocalDate dateToReturn;
	
	public Loan(long bookId, long memberId) {
		super();
		this.bookId = bookId;
		this.memberId = memberId;
		//this.dateToReturn = dateToReturn;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	
	/*
	public void setDateToReturn(LocalDate dateToReturn) {
		this.dateToReturn = dateToReturn;
	}
	*/
		
}
