package chirp.service.resources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.PostRepresentation;
import chirp.service.representations.UserRepresentation;

public class UserResourceTest extends JerseyResourceTest<UserResource> {
	
	private UserRepository userRepository = UserRepository.getInstance();
	
	@Before
	public void testInit() {
		userRepository.clear();
	}

	@Test
	public void createUserSuccess() {
		Form userForm = new Form().param("realname", "Gordon Force").param("username", "gordon");
		/*
		 * Return type from call below is a 'Response'
		 */
		Response rsp = target("/user").request().post(Entity.form(userForm));
		/*
		 * return code is a 201 (response content created
		 */
		assertEquals(Response.Status.CREATED.getStatusCode(), rsp.getStatus());
		String username = rsp.getLocation().getPath();
		assertEquals("/user/gordon", username);
		
		// validate that user was created by a HEAD or GET against the URI
		Response rsp1 = target(username).request().head();
		assertEquals(Response.Status.OK.getStatusCode(), rsp1.getStatus());
		
		// validate that user was created by a HEAD or GET against the URI
		rsp1 = target("/user/joe").request().head();
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), rsp1.getStatus());

	}
	
	@Test
	public void createUserDuplicate() {
		Form userForm = new Form().param("realname", "Gordon Force").param("username", "gordon");
		/*
		 * Return type from call below is a 'Response'
		 */
		Response rsp = target("/user").request().post(Entity.form(userForm));
		/*
		 * return code is a 201 (response content created
		 */
		assertEquals(Response.Status.CREATED.getStatusCode(), rsp.getStatus());
		//String username = rsp.getLocation().getPath();
		//assertEquals("/user/gordon", username);
		
		/*
		 * creating the user again returns a FORBIDDEN error
		 */
		System.out.println("Sending duplicate request");
		rsp = target("/user").request().post(Entity.form(userForm));
		assertEquals(Response.Status.FORBIDDEN.getStatusCode(), rsp.getStatus());
	}
	
	@Test
	public void getUser() {
		Form userForm = new Form().param("realname", "Gordon Force").param("username", "gordon");
		/*
		 * Return type from call below is a 'Response'
		 */
		Response rsp = target("/user").request().post(Entity.form(userForm));
		/*
		 * return code is a 201 (response content created
		 */
		assertEquals(Response.Status.CREATED.getStatusCode(), rsp.getStatus());
		
		UserRepresentation readUser = target(rsp.getLocation().getPath()).request().get(UserRepresentation.class);
		assertEquals("gordon", readUser.getUsername());
	}
	
	@Test
	public void getAllUsers() {
		Form userForm = new Form().param("realname", "Gordon Force").param("username", "gordon");
		Response rsp = target("/user").request().post(Entity.form(userForm));
		userForm = new Form().param("realname", "Vinod Damle").param("username", "vinod");
		rsp = target("/user").request().post(Entity.form(userForm));
		ArrayList<UserRepresentation> users = target("/user").request().get(ArrayList.class);
		assertEquals(2, users.size());
		
	}

}
