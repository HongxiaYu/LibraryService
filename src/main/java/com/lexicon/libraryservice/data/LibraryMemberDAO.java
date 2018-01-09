package com.lexicon.libraryservice.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lexicon.libraryservice.model.Book;
import com.lexicon.libraryservice.model.LibraryMember;
import com.lexicon.libraryservice.model.Loan;

@Stateless
public class LibraryMemberDAO implements LibraryMemberDAOInterface {
	@Inject
	EntityManager em;

	@Override
	public void persistLibraryMember(LibraryMember member) {
		em.persist(member);
	}

	@Override
	public List<LibraryMember> getAllMembers() {		
		TypedQuery<LibraryMember> query = em.createQuery("SELECT b FROM LibraryMember b", LibraryMember.class);
		return query.getResultList();
	}

	@Override
	public LibraryMember findLibraryMemberById(long id) {
		return em.find(LibraryMember.class, id);
	}


	@Override
	public List<LibraryMember> findLibraryMemberByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LibraryMember> criteria = cb.createQuery(LibraryMember.class);
        Root<LibraryMember> member = criteria.from(LibraryMember.class);
        criteria.select(member).where(cb.equal(member.get("name"), name));
        return em.createQuery(criteria).getResultList();
	}

	@Override
	public void deleteLibraryMemberById(long id) {
//		Book bookTemp = findBookById(id);
//		em.getTransaction().begin();
//		  em.remove(bookTemp);
//		  em.getTransaction().commit();	
		Query query = em.createQuery(
			      "DELETE FROM LibraryMember b WHERE b.id =:Id");
		query.setParameter("Id", id).executeUpdate();	
	}

	@Override
	public void updateLibraryMemberPhoneNumber(long id, String newPhoneNumber) {
		Query query = em.createQuery(
			      "UPDATE LibraryMember b SET b.phoneNumber = :newPhoneNumber WHERE b.id =:Id");
		query.setParameter("newPhoneNumber", newPhoneNumber);
		query.setParameter("Id", id);
		query.executeUpdate();		
	}

	@Override
	public void addLoanToMeber(Long bookId, long memberId) {
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
	public List<Loan> findLibraryMemberLoansById(long id) {
		TypedQuery<Loan> query = em.createQuery("SELECT b FROM Loan b WHERE b.member.id =:memberId", Loan.class);
		query.setParameter("memberId", id);
		return query.getResultList();
	}

}
