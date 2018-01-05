package com.lexicon.libraryservice.data;

import java.util.List;

import com.lexicon.libraryservice.model.Loan;

public interface LoanDAOInterface {
	
	void persistLoan(Loan loan);
	
	//void persistLoan(Long memberid, Book book);
	
	List<Loan> getAllLoans();
	//get all overdue in 2 days, grouped/sorted by member	
}
