package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.core.UriBuilder;

import chirp.model.Post;
import chirp.model.User;

public class PostCollectionRepresentation {

	private Collection<PostRepresentation> postReps = new ArrayList<>();
	private URI self;
	
	public PostCollectionRepresentation(Collection<Post> posts, User user) {
		for(Post post : posts) {
			postReps.add(new PostRepresentation(post, true));
		}
		self  = UriBuilder.fromPath("/post").path(user.getUsername()).build();
	}
	
	public Collection<PostRepresentation> getAll() {
		return Collections.unmodifiableCollection(postReps);
	}
	
	public URI getSelf() {
		return self;
	}
}
