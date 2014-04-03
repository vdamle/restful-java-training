package chirp.service.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.ResponseBuilder;

import chirp.model.DuplicateEntityException;
import chirp.model.NoSuchEntityException;
import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.UserCollectionRepresentation;
import chirp.service.representations.UserRepresentation;

@Path("/user")
public class UserResource {

	private UserRepository userRepository = UserRepository.getInstance();

	@POST
	public Response createUser(@FormParam("username") String uName,
						   @FormParam("realname") String rName) {
		/**
		 * In the absense of an exception mapper, we need to catch exception
		 * everytime we create a user. this adds boiler plate code to check for
		 * various common exceptions when using library services. Instead, jax-rs
		 * provides an exception mapper which we can use -- refer to 
		 * DuplicateEntityExceptionMapper
		 */
		/*
		try {
			userRepository.createUser(uName, rName);
		} catch (DuplicateEntityException e) {
			return Response.status(Response.Status.CONFLICT).build();
		}
		*/
		userRepository.createUser(uName, rName);
		/**
		URI location = URI.create("/user/" + uName);
		
		return Response.created(location).build();
		*/
		return Response.created(UriBuilder.fromPath("user").path(uName).build()).build();
	}
	
	@HEAD
	@Path("{username}")
	public Response checkUser(@PathParam("username") String uName) {
		User myUser = userRepository.getUser(uName);
		
		ResponseBuilder rb = Response.ok();
		
		rb.links(Link.fromUri("/user/" + uName).rel("self").title(myUser.getUsername()).build());
		rb.links(Link.fromUri("/user/").rel("up").build());
		
		return rb.build();
	}

	@GET
	@Path("{username}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserRepresentation getUser(@PathParam("username") String uName) {
		return new UserRepresentation(userRepository.getUser(uName), false);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserCollectionRepresentation getAllUsers() {
		return new UserCollectionRepresentation(userRepository.getUsers());
	}
}
