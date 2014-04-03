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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import chirp.model.DuplicateEntityException;
import chirp.model.NoSuchEntityException;
import chirp.model.Post;
import chirp.model.Timestamp;
import chirp.model.User;
import chirp.model.UserRepository;
import chirp.service.representations.PostRepresentation;
import chirp.service.representations.UserRepresentation;

@Path("/post/{username}")
public class PostResource {

	private UserRepository userRepository = UserRepository.getInstance();

	@POST
	public Response createPost(@PathParam("username") String uName,
						   @FormParam("content") String content) {
		User postUser = userRepository.getUser(uName);
		Post createPost = postUser.createPost(content);

		return Response.created(UriBuilder.fromPath("post").path(uName).path(createPost.getTimestamp().toString()).build()).build();
	}

	@GET
	@Path("{timestamp}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public PostRepresentation getPost(@PathParam("username") String uName,
									  @PathParam("timestamp") String timestamp) {
		Timestamp ts = new Timestamp(timestamp);
		return new PostRepresentation(userRepository.getUser(uName).getPost(ts));
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Collection<PostRepresentation> getAllPosts(@PathParam("username") String uName) {
		User myUser = userRepository.getUser(uName);
		Collection<PostRepresentation> posts = new ArrayList<>();
		
		for(Post post : myUser.getPosts()) {
			posts.add(new PostRepresentation(post));
		}
		return Collections.unmodifiableCollection(posts);
	}
}
