package com.lexicon.libraryservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "loans")
public class Loan {

	@Id
	@GeneratedValue
	private Long id;	
	
	public Loan() {
	//	super();	
	}

	/*
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinTable(name = "user_passport",
	        joinColumns = @JoinColumn(name="user_id"),
	        inverseJoinColumns = @JoinColumn(name="passport_id")
	    )
	  //  private Passport passport;
      private Book book;
      */
/*	@JoinTable(name = "books",
	   joinColumns = @JoinColumn(name = "BOOK_ID")
	// inverseJoinColumns = @JoinColumn(name="passport_id")
	)
	*/
	
   // @OneToOne (cascade = CascadeType.ALL)
   // @PrimaryKeyJoinColumn
   private Long bookId;   //!!!  Not Null
	//private Book book;
		
	//@JoinColumn(name = "MEMBER_ID")
	private Long memberId;   //!!! Not Null
	
//	private LocalDate dateToReturn;
	
	//public Loan(Long memberId, Book book) {
	public Loan(Long memberId, Long bookId) {		
		super();
		//this.book = book;
		this.bookId = bookId;
		this.memberId = memberId;
		//this.dateToReturn = dateToReturn;
	}
	
	public Long getBookId() {
		return bookId;
	}
	
	/*
	//@OneToOne(cascade = CascadeType.ALL)
	public Book getBook() {
		return book;
	}

	public void setBookId(Book book) {
		this.book = book;
	}
	*/

	public long getMemberId() {
		return memberId;
	}
/*
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
*/	
	/*
	public void setDateToReturn(LocalDate dateToReturn) {
		this.dateToReturn = dateToReturn;
	}
	*/	
}
