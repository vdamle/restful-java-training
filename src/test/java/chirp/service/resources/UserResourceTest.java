package chirp.service.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.junit.Test;

import chirp.model.User;

public class UserResourceTest extends JerseyResourceTest<UserResource> {

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
		
		/*
		 * creating the user again returns a FORBIDDEN error
		 */
		System.out.println("Sending duplicate request");
		Response rsp2 = target("/user").request().post(Entity.form(userForm));
		assertEquals(Response.Status.FORBIDDEN.getStatusCode(), rsp2.getStatus());
		
		// validate that user was created by a HEAD or GET against the URI
		Response rsp1 = target(username).request().head();
		assertEquals(Response.Status.OK.getStatusCode(), rsp1.getStatus());
		
		// validate that user was created by a HEAD or GET against the URI
		rsp1 = target("/user/joe").request().head();
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), rsp1.getStatus());

	}

}
