package chirp.service.representations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import chirp.model.Post;
import chirp.model.User;

@XmlRootElement
public class PostCollectionRepresentation {

	private Collection<PostRepresentation> posts = new ArrayList<>();
	private URI self;
	
	public PostCollectionRepresentation() {
		
	}
	
	public PostCollectionRepresentation(Collection<Post> postList, User user) {
		for(Post post : postList) {
			posts.add(new PostRepresentation(post, true));
		}
		self  = UriBuilder.fromPath("/post").path(user.getUsername()).build();
	}
	
	@JsonCreator
	public PostCollectionRepresentation(@JsonProperty("self") URI self,
										@JsonProperty("posts")Collection<PostRepresentation> postList) {
		this.self = self;
		this.posts = postList;
	}
	
	@XmlElement
	public Collection<PostRepresentation> getPosts() {
		return Collections.unmodifiableCollection(posts);
	}
	
	@XmlElement
	public URI getSelf() {
		return self;
	}
}
