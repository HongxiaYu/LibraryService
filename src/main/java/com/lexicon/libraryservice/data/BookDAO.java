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
import com.lexicon.libraryservice.model.Loan;

@Stateless
public class BookDAO implements BookDAOInterface {
	@Inject
	EntityManager em;

	@Override
	public void persistBook(Book book) {
		em.persist(book);
	}

	@Override
	public List<Book> getAllBooks() {		
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}

	@Override
	public Book findBookById(long id) {
		return em.find(Book.class, id);
	}

	@Override
	public Book findBookByISBN(String isbn) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> book = criteria.from(Book.class);
        criteria.select(book).where(cb.equal(book.get("strISBN"), isbn));
        return em.createQuery(criteria).getSingleResult();
	}

	@Override
	public List<Book> findBookByTitle(String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> book = criteria.from(Book.class);
        criteria.select(book).where(cb.equal(book.get("title"), title));
        return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Book> findBookByAuthor(String author) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> book = criteria.from(Book.class);
        criteria.select(book).where(cb.equal(book.get("author"), author));
        return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Book> findBookByGenre(String genre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> book = criteria.from(Book.class);
        criteria.select(book).where(cb.equal(book.get("genre"), genre));
        return em.createQuery(criteria).getResultList();
	}	

	@Override
	public void deleteBookById(long id) {
//		Book bookTemp = findBookById(id);
//		em.getTransaction().begin();
//		em.remove(bookTemp);
//		em.getTransaction().commit();	
		Query query = em.createQuery(
			      "DELETE FROM Book b WHERE b.id =:Id");
		query.setParameter("Id", id).executeUpdate();	
	}

	@Override
	public void updateBookGenre(long id, String genre) {
		Query query = em.createQuery(
			      "UPDATE Book b SET genre = :newgenre WHERE b.id =:Id");
		query.setParameter("newgenre", genre);
		query.setParameter("Id", id);
		query.executeUpdate();		
	}

	@Override
	public void updateBookBorrowedCopies(long id, int iCopies) {		
		Book e = findBookById(id);
		Query query = em.createQuery(
			      "UPDATE Book b SET loanedCopies = :newLoanedCopies WHERE b.id =:Id");
		query.setParameter("newLoanedCopies", e.getAvilableCopies() + iCopies);
		query.setParameter("Id", id);
		query.executeUpdate();
	}
	
	@Override
	public List<Loan> findBookLoansById(long id) {
		TypedQuery<Loan> query = em.createQuery("SELECT b FROM Loan b WHERE b.loanBook.id =:bookId", Loan.class);
		query.setParameter("bookId", id);
		return query.getResultList();
	}	
}
