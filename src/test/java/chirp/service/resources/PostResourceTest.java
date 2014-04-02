package chirp.service.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.UserRepresentation;

public class PostResourceTest extends JerseyResourceTest<PostResource> {
	
	private UserRepository userRepository = UserRepository.getInstance();
	
	@Before
	public void testInit() {
		userRepository.clear();
	}

	@Test
	public void createPostSuccess() {
		userRepository.createUser("gordon", "Gordon Force");
		Form postForm = new Form().param("content", "Have a nice day!");
		Response rsp = target("/post/gordon").request().post(Entity.form(postForm));
		assertEquals(Response.Status.CREATED.getStatusCode(), rsp.getStatus());
	}
}
