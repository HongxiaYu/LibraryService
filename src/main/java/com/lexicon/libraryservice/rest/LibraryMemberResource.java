package com.lexicon.libraryservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lexicon.libraryservice.data.LibraryMemberDAOInterface;
import com.lexicon.libraryservice.data.LoanDAOInterface;
import com.lexicon.libraryservice.model.LibraryMember;

@Path("/libraryMember")
public class LibraryMemberResource {
	
	@Inject
	LibraryMemberDAOInterface dao;
	
	@Inject
	LoanDAOInterface loanDao;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveBook(LibraryMember member) throws URISyntaxException {
		dao.persistLibraryMember(member);;
		return Response.created(new URI("localhost:8080/LibraryService/rest/libraryMember")).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLibraryMembers() {
		return Response.ok(dao.getAllMembers()).build();
	}
	
	@GET
	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON))
	@Produces({"Application/JSON", "Application/XML"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLibraryMemberById(@PathParam("id") long id) {
		return Response.ok(dao.findLibraryMemberById(id)).build();
	}
	
	@GET
	@Path("/{id}/loans")
//	@Produces(MediaType.APPLICATION_JSON))
	@Produces({"Application/JSON", "Application/XML"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLibraryMemberLoansById(@PathParam("id") long id) {
		return Response.ok(dao.findLibraryMemberLoansById(id)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteLibraryMember(@PathParam("id") long id) throws URISyntaxException {
		dao.deleteLibraryMemberById(id);
		return Response.created(new URI("localhost:8080/LibraryService/rest/libraryMember/delete/"+id)).build();
	}
	
	@PUT
	@Path("/{id}/phonenumber/{phonenumber}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMemberPhoneNumber(@PathParam("id") long id, @PathParam("phonenumber") String phonenumber) throws URISyntaxException {
		dao.updateLibraryMemberPhoneNumber(id, phonenumber);
		return Response.created(new URI("localhost:8080/LibraryService/rest/book/" + id + "/phonenumber/" + phonenumber)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{memberId}/addloan/book/{bookId}")
	public void saveLoan(@PathParam("bookId") Long bookId, @PathParam("memberId") long memberId) throws URISyntaxException {
		dao.addLoanToMeber(bookId, memberId);
		
//		Response.ResponseBuilder builder = Response.created(new URI("localhost:8080/LibraryService/rest/libraryMember/"+memberId));
//        return builder.build();
	}
	
	@PUT
	@Path("/{id}/omLoan/book/{bookId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBookGenre(@PathParam("id") Long id, @PathParam("bookId") Long bookId) throws URISyntaxException {
		loanDao.updateLoanReturnDate(LocalDate.now().plusDays(30), bookId, id);
		return Response.created(new URI("localhost:8080/LibraryService/rest/libraryMember/" + id + "/omLoan/book/" + bookId)).build();
	}
	
	@DELETE
	@Path("/{id}/returnBook/book/{bookId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response returnBook(@PathParam("id") long id, @PathParam("bookId") Long bookId) throws URISyntaxException {
		loanDao.deleteLoan(id, bookId);
		return Response.created(new URI("localhost:8080/LibraryService/rest/libraryMember/" + id + "/returnBook/book/" + bookId)).build();
	}
	
//	@PUT
//	@Path("/{id}/iCopies/{iCopies}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updateBookBorrowedCopies(@PathParam("id") long id, @PathParam("iCopies") int iCopies) throws URISyntaxException {
//		dao.updateBookBorrowedCopies(id, iCopies);
//		return Response.created(new URI("localhost:8080/LibraryService/rest/book/" + id + "/iCopies/" + iCopies)).build();
//	}	
		
}
