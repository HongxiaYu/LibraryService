package com.lexicon.libraryservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.lexicon.libraryservice.data.LoanDAOInterface;
import com.lexicon.libraryservice.model.Loan;

@Path("/loan")
public class LoanResourceRESTService {

	@Inject
	LoanDAOInterface dao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveLoan(Loan loan) throws URISyntaxException {
		Response.ResponseBuilder builder = Response.created(new URI("localhost:8080/LibraryService/rest/loan"));
		try {
		dao.persistLoan(loan);
//		return Response.ok().build(); // !!!
		} catch (Exception e) {
            // Handle generic exceptions
           Map<String, String> responseObj = new HashMap<>();
           responseObj.put("error", e.getMessage());
          builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

      return builder.build();
    //return Response.created(new URI("localhost:8080/LibraryService/rest/loan")).build(); // !!!
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
//	public List<Person> getAllPersons(){
	public Response getAllLoans(){
		return Response.ok(dao.getAllLoans()).build();
		//return dao.getAllPersons();
	}
}
