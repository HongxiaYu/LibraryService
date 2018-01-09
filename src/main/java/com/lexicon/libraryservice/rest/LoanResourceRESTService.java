package com.lexicon.libraryservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.lexicon.libraryservice.data.LoanDAOInterface;



@Path("/loan")
public class LoanResourceRESTService {

	@Inject
	LoanDAOInterface dao;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/book/{bookId}/member/{memberId}")
	public Response saveLoan(@PathParam("bookId") Long bookId, @PathParam("memberId") long memberId) throws URISyntaxException {
		dao.persistLoan(bookId, memberId);		
		return Response.created(new URI("localhost:8080/LibraryService/rest/loan")).build();
	}	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLoans(){
		return Response.ok(dao.getAllLoans()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLoanById(@PathParam("id") Long id){
		return Response.ok(dao.getAllLoanById(id)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("id") Long id) throws URISyntaxException {
		dao.deleteLoanById(id);
		return Response.created(new URI("localhost:8080/LibraryService/rest/loan/"+id)).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBookGenre(@PathParam("id") Long id) throws URISyntaxException {
		dao.updateLoanReturnDateById(LocalDate.now().plusDays(40), id);
		return Response.created(new URI("localhost:8080/LibraryService/rest/loan/" + id )).build();
	}

	
}
