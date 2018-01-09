package com.lexicon.libraryservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lexicon.libraryservice.data.LibraryMemberRepository;
import com.lexicon.libraryservice.model.LibraryMember;


import com.lexicon.libraryservice.data.MemberDAO;
import com.lexicon.libraryservice.model.LMember;




/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/lmembers")
public class LibraryMemberResourceRESTService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private LibraryMemberRepository repository;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LibraryMember> listAllMembers() {
		return repository.findAllOrderedByName();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public LibraryMember lookupMemberById(@PathParam("id") long id) {
		LibraryMember member = repository.findById(id);
		if (member == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return member;
	}

	/**
	 * Creates a new member from the values provided. Performs validation, and will
	 * return a JAX-RS response with either 200 ok, or with a map of fields, and
	 * related errors.
	 * @throws URISyntaxException 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMember(LibraryMember member) throws URISyntaxException {
		repository.register(member);
		return Response.created(new URI("localhost:8080/LibraryService/rest/member")).build();
	}


	/**
	 * <p>
	 * Validates the given Member variable and throws validation exceptions based on
	 * the type of error. If the error is standard bean validation errors then it
	 * will throw a ConstraintValidationException with the set of the constraints
	 * violated.
	 * </p>
	 * <p>
	 * If the error is caused because an existing member with the same email is
	 * registered it throws a regular validation exception so that it can be
	 * interpreted separately.
	 * </p>
	 * 
	 * @param member
	 *            Member to be validated
	 * @throws ConstraintViolationException
	 *             If Bean Validation errors exist
	 * @throws ValidationException
	 *             If member with the same email already exists
	 */
	private void validateMember(LibraryMember member) throws ConstraintViolationException, ValidationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<LibraryMember>> violations = validator.validate(member);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}

		// Check the uniqueness of the email address
		if (emailAlreadyExists(member.getEmail())) {
			throw new ValidationException("Unique Email Violation");
		}
	}

	/**
	 * Creates a JAX-RS "Bad Request" response including a map of all violation
	 * fields, and their message. This can then be used by clients to show
	 * violations.
	 * 
	 * @param violations
	 *            A set of violations that needs to be reported
	 * @return JAX-RS response containing all violations
	 */
	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

	/**
	 * Checks if a member with the same email address is already registered. This is
	 * the only way to easily capture the "@UniqueConstraint(columnNames = "email")"
	 * constraint from the Member class.
	 * 
	 * @param email
	 *            The email to check
	 * @return True if the email already exists, and false otherwise
	 */
	public boolean emailAlreadyExists(String email) {
		LibraryMember member = null;
		try {
			member = repository.findByEmail(email);
		} catch (NoResultException e) {
			// ignore
		}
		return member != null;
	}

}
