package com.lexicon.libraryservice.data;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lexicon.libraryservice.model.Book;
import com.lexicon.libraryservice.model.LibraryMember;
import com.lexicon.libraryservice.model.Loan;

@Stateless
public class LoanDAO implements LoanDAOInterface{

	@Inject
	EntityManager em;
	
	@Override
	public void persistLoan(Long bookId, Long memberId) {
		Book book = em.find(Book.class, bookId);
		LibraryMember member = em.find(LibraryMember.class, memberId);		
		Loan loan = new Loan();
		loan.setLoanBook(book);
		loan.setMember(member);
		book.addLoan(loan);
		member.loadLoans().add(loan);
		em.persist(loan);
		em.merge(book);
		em.merge(member);
	}
	
	@Override
	public List<Loan> getAllLoans(){
		TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l", Loan.class);
		return query.getResultList();
	}
	
	@Override
	public List<Loan> getAllLoanById(Long id){
		TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.id =:id", Loan.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	@Override
	public Loan getLoanByMemberBook(Long memberId, Long bookId){
		TypedQuery<Loan> query = em.createQuery("SELECT b FROM Loan b WHERE b.member.id =:memberId and b.loanBook.id =:bookId", Loan.class);
		query.setParameter("memberId", memberId);
		query.setParameter("bookId", bookId);
		return query.getSingleResult();
	}
	
	@Override
	public void deleteLoan( Long memberId, Long bookId) {
//		Book book = em.find(Book.class, bookId);
//		Loan loan = getLoanByMemberBook(memberId, bookId);
//		book.deleteLoan(loan);		
		Query query = em.createQuery(
			      "DELETE Loan b  WHERE b.member.id =:memberId and b.loanBook.id =:bookId");
		query.setParameter("memberId", memberId);
		query.setParameter("bookId", bookId);
		query.executeUpdate();
//		em.merge(book);	
	}
	
	@Override
	public void updateLoanReturnDate(LocalDate returnDate, Long bookId, Long memberId) {			
		Query query = em.createQuery(
			      "update Loan b SET b.returnDate =:newDate WHERE b.member.id =:memberId and b.loanBook.id =:bookId");
		query.setParameter("newDate", returnDate);
		query.setParameter("memberId", memberId);
		query.setParameter("bookId", bookId);
		query.executeUpdate();
	}
	
	@Override
	public void updateLoanReturnDateById(LocalDate returnDate, Long id) {			
		Query query = em.createQuery(
			      "update Loan b SET b.returnDate =:newDate WHERE b.id =:ID");
		query.setParameter("newDate", returnDate);
		query.setParameter("ID", id);
		query.executeUpdate();
	}
	

	@Override
	public void deleteLoanById(Long id) {		
		Query query = em.createQuery(
			      "DELETE FROM Loan b WHERE b.id =:id");
		query.setParameter("id", id);
		query.executeUpdate();	
		
	}
	
		
}
