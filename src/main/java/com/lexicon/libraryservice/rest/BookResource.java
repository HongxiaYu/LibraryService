package com.lexicon.libraryservice.rest;

import java.net.URI;
import java.net.URISyntaxException;

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

import com.lexicon.libraryservice.data.BookDAOInterface;
import com.lexicon.libraryservice.model.Book;

@Path("/book")
public class BookResource {
	
	@Inject
	BookDAOInterface dao;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveBook(Book book) throws URISyntaxException {
		book.setAvilableCopies(book.getCopies());
		dao.persistBook(book);
		return Response.created(new URI("localhost:8080/LibraryService/rest/book")).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBooks() {
		return Response.ok(dao.getAllBooks()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBookById(@PathParam("id") long id) {
		return Response.ok(dao.findBookById(id)).build();
	}
	
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("id") long id) throws URISyntaxException {
		dao.deleteBookById(id);
		return Response.created(new URI("localhost:8080/LibraryService/rest/book/delete/"+id)).build();
	}
	
	@PUT
	@Path("/{id}/genre/{genre}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBookGenre(@PathParam("id") long id, @PathParam("genre") String genre) throws URISyntaxException {
		dao.updateBookGenre(id, genre);
		return Response.created(new URI("localhost:8080/LibraryService/rest/book/" + id + "/genre/" + genre)).build();
	}

	@PUT
	@Path("/{id}/iCopies/{iCopies}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBookBorrowedCopies(@PathParam("id") long id, @PathParam("iCopies") int iCopies) throws URISyntaxException {
		dao.updateBookBorrowedCopies(id, iCopies);
		return Response.created(new URI("localhost:8080/LibraryService/rest/book/" + id + "/iCopies/" + iCopies)).build();
	}
	
	@GET
	@Path("/{id}/loans")
//	@Produces(MediaType.APPLICATION_JSON))
	@Produces({"Application/JSON", "Application/XML"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBookLoansById(@PathParam("id") long id) {
		return Response.ok(dao.findBookLoansById(id)).build();
	}
}
