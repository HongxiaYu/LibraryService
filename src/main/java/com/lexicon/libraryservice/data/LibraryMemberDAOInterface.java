package com.lexicon.libraryservice.data;

import java.util.List;

import com.lexicon.libraryservice.model.LibraryMember;
import com.lexicon.libraryservice.model.Loan;

public interface LibraryMemberDAOInterface {

	void persistLibraryMember(LibraryMember member);

	List<LibraryMember> getAllMembers();

	LibraryMember findLibraryMemberById(long id);

	List<LibraryMember> findLibraryMemberByName(String name);

	void deleteLibraryMemberById(long id);

	void updateLibraryMemberPhoneNumber(long id, String newPhoneNumber);

	void addLoanToMeber(Long bookId, long memberId);

	List<Loan> findLibraryMemberLoansById(long id);

}
