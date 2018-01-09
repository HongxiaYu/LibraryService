package com.lexicon.libraryservice.data;

import java.time.LocalDate;
import java.util.List;

import com.lexicon.libraryservice.model.Loan;

public interface LoanDAOInterface {
	
	public void persistLoan(Long bookId, Long memberID);


	public List<Loan> getAllLoans();
	//get all overdue in 2 days, grouped/sorted by member

	public void deleteLoan(Long bookId, Long memberId);

	public void updateLoanReturnDate(LocalDate returnDate, Long bookId, Long memberId);

	public List<Loan> getAllLoanById(Long id);

	public void deleteLoanById(Long id);

	public Loan getLoanByMemberBook(Long memberId, Long bookId);

	void updateLoanReturnDateById(LocalDate returnDate, Long id);
	

}
