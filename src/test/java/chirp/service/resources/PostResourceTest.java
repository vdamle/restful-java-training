package chirp.service.resources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.*;

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
	
	@Test
	public void getAllPosts() {
		userRepository.createUser("gordon", "Gordon Force");
		Form postForm = new Form().param("content", "Have a nice day!");
		Response rsp = target("/post/gordon").request().post(Entity.form(postForm));
		Form postForm2 = new Form().param("content", "Summers next!");
		Response rsp2 = target("/post/gordon").request().post(Entity.form(postForm2));
		
		ArrayList<PostRepresentation> posts = target("/post/gordon").request().get(ArrayList.class);
		assertEquals(2, posts.size());
	}
	
	@Test
	public void readPostCollectionSuccess() {
		
	}
}
