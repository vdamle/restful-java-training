package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chirp.model.Post;
import chirp.model.User;

@XmlRootElement
public class PostCollectionRepresentation {

	private Collection<PostRepresentation> postReps = new ArrayList<>();
	private URI self;
	
	public PostCollectionRepresentation() {
		
	}
	
	public PostCollectionRepresentation(Collection<Post> posts, User user) {
		for(Post post : posts) {
			postReps.add(new PostRepresentation(post, true));
		}
		self  = UriBuilder.fromPath("/post").path(user.getUsername()).build();
	}
	
	@XmlElement
	public Collection<PostRepresentation> getPost() {
		return Collections.unmodifiableCollection(postReps);
	}
	
	@XmlElement
	public URI getSelf() {
		return self;
	}
}
