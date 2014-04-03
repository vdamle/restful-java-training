package chirp.service.representations;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import chirp.model.Post;
import chirp.model.Timestamp;
import chirp.model.User;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class PostRepresentation {
	
	private String content;
	private String timestamp;
	private URI self;
	
	public PostRepresentation() { /* For xml representation */ }
	
	@JsonCreator
	public PostRepresentation(@JsonProperty("self") URI self,
							  @JsonProperty("content") String content,
							  @JsonProperty("timestamp") String timestamp) {
		this.self = self;
		this.content = content;
		this.timestamp = timestamp;
	}
	
	public PostRepresentation(Post post, boolean summary) {
		this.self = UriBuilder.fromPath("/post").path(post.getUser().getUsername()).path(post.getTimestamp().toString()).build();
		if (summary == false) {
			this.content = post.getContent();
			this.timestamp = post.getTimestamp().toString();
		}
	}
	
	@XmlElement
	public String getContent() {
		return content;
	}
	
	public void setUsername(String content) {
		this.content = content;
	}
	
	@XmlElement
	public String getTimestamp() {
		return timestamp;
	}
	
	@XmlElement
	public URI getSelf() {
		return self;
	}
}
