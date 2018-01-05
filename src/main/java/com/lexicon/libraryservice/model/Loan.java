package com.lexicon.libraryservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "loans")
public class Loan {

	@Id
	@GeneratedValue
	private Long id;	
	
    @NotNull
    @NotEmpty	
    private Long bookId;   
		
    @NotNull
    @NotEmpty
	private Long memberId;  
	
    @NotNull
    @NotEmpty
	private LocalDate dateToReturn;

	public Loan() {
    	//	super();	
    	}
    
   public Loan(Long memberId, Long bookId) {				
		//super();		
		this.bookId = bookId;
		this.memberId = memberId;
		
		setNewDateToReturn();
		//setDateToReturn(termToReturn()); 
		//this.dateToReturn = LocalDate.now().plusDays(30);  
	}
	
	public Long getId() {
		return id;
	}
   
	public Long getBookId() {
		return bookId;
	}
	
	public long getMemberId() {
		return memberId;
	}

	public void setDateToReturn(LocalDate dateToReturn) {
		this.dateToReturn = dateToReturn;
    }	
	
//DATE TO RETURN:
	
	 public LocalDate getDateToReturn() {
			return dateToReturn;
		}
	
	public void setNewDateToReturn() {
		setDateToReturn(termToReturn()); 		
    }	
	
	private LocalDate termToReturn() {
		return LocalDate.now().plusDays(30); //May 'depends' on a book 
}	
}
