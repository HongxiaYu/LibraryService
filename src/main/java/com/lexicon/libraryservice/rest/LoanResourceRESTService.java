package com.lexicon.libraryservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.lexicon.libraryservice.data.BookDAOInterface;
import com.lexicon.libraryservice.data.MemebersInterface;
import com.lexicon.libraryservice.data.LoanDAOInterface;
import com.lexicon.libraryservice.model.Loan;

@Path("/loan")
public class LoanResourceRESTService {

	@Inject
	LoanDAOInterface dao;
	
    @Inject
    BookDAOInterface booksRepository;

    @Inject
    MemebersInterface memebersRepository; 
    
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveLoan(Loan loan) throws URISyntaxException {
		
		Response.ResponseBuilder builder = Response.created(new URI("localhost:8080/LibraryService/rest/loan"));
		
		try {
		dao.persistLoan(loan);
		} catch (Exception e) {
            // Handle generic exceptions
           Map<String, String> responseObj = new HashMap<>();
           responseObj.put("error", e.getMessage());
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

      return builder.build();    
	}

	@POST	
	@Path("/{memberId}/book/{bookId}")	
	public Response saveLoan(@PathParam("memberId") Long memberId, @PathParam("bookId") Long bookId) throws URISyntaxException{
		
		Response.ResponseBuilder builder = Response.created(new URI("localhost:8080/PersonNotes/rest/loan/" + memberId + "/book/" + bookId));
		
		boolean bookExist  = booksRepository.contains(bookId);
	    boolean memberExist  = memebersRepository.contains(memberId);
		//boolean memberExist = true;
		
		if (!bookExist || !memberExist) {
		 builder = Response.status(Response.Status.NOT_FOUND).entity("data error");
		} 
		else
		{
		Loan loan = new Loan(memberId, bookId);	
		
		try {
	    dao.persistLoan(loan);
		} catch (Exception e) {
            // Handle generic exceptions
           Map<String, String> responseObj = new HashMap<>();
           responseObj.put("error", e.getMessage());
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
		} 
	      return builder.build();
	} 	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLoans(){
		return Response.ok(dao.getAllLoans()).build();
	}
}
